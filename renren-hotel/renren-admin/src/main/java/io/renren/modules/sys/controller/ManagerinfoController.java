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

import io.renren.modules.sys.entity.ManagerinfoEntity;
import io.renren.modules.sys.service.ManagerinfoService;
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
@RequestMapping("sys/managerinfo")
public class ManagerinfoController {
    @Autowired
    private ManagerinfoService managerinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:managerinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = managerinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{managerid}")
    @RequiresPermissions("sys:managerinfo:info")
    public R info(@PathVariable("managerid") String managerid){
        ManagerinfoEntity managerinfo = managerinfoService.getById(managerid);

        return R.ok().put("managerinfo", managerinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:managerinfo:save")
    public R save(@RequestBody ManagerinfoEntity managerinfo){
        managerinfoService.save(managerinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:managerinfo:update")
    public R update(@RequestBody ManagerinfoEntity managerinfo){
        ValidatorUtils.validateEntity(managerinfo);
        managerinfoService.updateById(managerinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:managerinfo:delete")
    public R delete(@RequestBody String[] managerids){
        managerinfoService.removeByIds(Arrays.asList(managerids));

        return R.ok();
    }

}
