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

import io.renren.modules.sys.entity.RoomorderinfoEntity;
import io.renren.modules.sys.service.RoomorderinfoService;
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
@RequestMapping("sys/roomorderinfo")
public class RoomorderinfoController {
    @Autowired
    private RoomorderinfoService roomorderinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:roomorderinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roomorderinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderid}")
    @RequiresPermissions("sys:roomorderinfo:info")
    public R info(@PathVariable("orderid") String orderid){
        RoomorderinfoEntity roomorderinfo = roomorderinfoService.getById(orderid);

        return R.ok().put("roomorderinfo", roomorderinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:roomorderinfo:save")
    public R save(@RequestBody RoomorderinfoEntity roomorderinfo){
        roomorderinfoService.save(roomorderinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:roomorderinfo:update")
    public R update(@RequestBody RoomorderinfoEntity roomorderinfo){
        ValidatorUtils.validateEntity(roomorderinfo);
        roomorderinfoService.updateById(roomorderinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:roomorderinfo:delete")
    public R delete(@RequestBody String[] orderids){
        roomorderinfoService.removeByIds(Arrays.asList(orderids));

        return R.ok();
    }

}
