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

import io.renren.modules.sys.entity.ManagertypeinfoEntity;
import io.renren.modules.sys.service.ManagertypeinfoService;
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
@RequestMapping("sys/managertypeinfo")
public class ManagertypeinfoController {
    @Autowired
    private ManagertypeinfoService managertypeinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:managertypeinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = managertypeinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{managertype}")
    @RequiresPermissions("sys:managertypeinfo:info")
    public R info(@PathVariable("managertype") Integer managertype){
        ManagertypeinfoEntity managertypeinfo = managertypeinfoService.getById(managertype);

        return R.ok().put("managertypeinfo", managertypeinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:managertypeinfo:save")
    public R save(@RequestBody ManagertypeinfoEntity managertypeinfo){
        managertypeinfoService.save(managertypeinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:managertypeinfo:update")
    public R update(@RequestBody ManagertypeinfoEntity managertypeinfo){
        ValidatorUtils.validateEntity(managertypeinfo);
        managertypeinfoService.updateById(managertypeinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:managertypeinfo:delete")
    public R delete(@RequestBody Integer[] managertypes){
        managertypeinfoService.removeByIds(Arrays.asList(managertypes));

        return R.ok();
    }

}
