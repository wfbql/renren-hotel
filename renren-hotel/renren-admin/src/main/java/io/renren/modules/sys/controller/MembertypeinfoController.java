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

import io.renren.modules.sys.entity.MembertypeinfoEntity;
import io.renren.modules.sys.service.MembertypeinfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:45:38
 */
@RestController
@RequestMapping("sys/membertypeinfo")
public class MembertypeinfoController {
    @Autowired
    private MembertypeinfoService membertypeinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:membertypeinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = membertypeinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{membertypeid}")
    @RequiresPermissions("sys:membertypeinfo:info")
    public R info(@PathVariable("membertypeid") String membertypeid){
        MembertypeinfoEntity membertypeinfo = membertypeinfoService.getById(membertypeid);

        return R.ok().put("membertypeinfo", membertypeinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:membertypeinfo:save")
    public R save(@RequestBody MembertypeinfoEntity membertypeinfo){
        membertypeinfoService.save(membertypeinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:membertypeinfo:update")
    public R update(@RequestBody MembertypeinfoEntity membertypeinfo){
        ValidatorUtils.validateEntity(membertypeinfo);
        membertypeinfoService.updateById(membertypeinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:membertypeinfo:delete")
    public R delete(@RequestBody String[] membertypeids){
        membertypeinfoService.removeByIds(Arrays.asList(membertypeids));

        return R.ok();
    }

}
