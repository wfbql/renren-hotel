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

import io.renren.modules.sys.entity.RoominfoEntity;
import io.renren.modules.sys.service.RoominfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:47:08
 */
@RestController
@RequestMapping("sys/roominfo")
public class RoominfoController {
    @Autowired
    private RoominfoService roominfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:roominfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roominfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roomid}")
    @RequiresPermissions("sys:roominfo:info")
    public R info(@PathVariable("roomid") String roomid){
        RoominfoEntity roominfo = roominfoService.getById(roomid);

        return R.ok().put("roominfo", roominfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:roominfo:save")
    public R save(@RequestBody RoominfoEntity roominfo){
        roominfoService.save(roominfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:roominfo:update")
    public R update(@RequestBody RoominfoEntity roominfo){
        ValidatorUtils.validateEntity(roominfo);
        roominfoService.updateById(roominfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:roominfo:delete")
    public R delete(@RequestBody String[] roomids){
        roominfoService.removeByIds(Arrays.asList(roomids));

        return R.ok();
    }

}
