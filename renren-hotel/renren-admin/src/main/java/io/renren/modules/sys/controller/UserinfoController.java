package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.UserinfoEntity;
import io.renren.modules.sys.service.UserinfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * �û���Ϣ��
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:45:38
 */
@RestController
@RequestMapping("sys/userinfo")
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:userinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    @RequiresPermissions("sys:userinfo:info")
    public R info(@PathVariable("userid") String userid){
        UserinfoEntity userinfo = userinfoService.getById(userid);

        return R.ok().put("userinfo", userinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:userinfo:save")
    public R save(@RequestBody UserinfoEntity userinfo){
        userinfoService.save(userinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:userinfo:update")
    public R update(@RequestBody UserinfoEntity userinfo){
        ValidatorUtils.validateEntity(userinfo);
        userinfoService.updateById(userinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:userinfo:delete")
    public R delete(@RequestBody String[] userids){
        userinfoService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
