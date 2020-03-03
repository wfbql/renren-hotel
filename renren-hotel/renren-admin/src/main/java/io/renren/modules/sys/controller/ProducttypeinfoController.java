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

import io.renren.modules.sys.entity.ProducttypeinfoEntity;
import io.renren.modules.sys.service.ProducttypeinfoService;
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
@RequestMapping("sys/producttypeinfo")
public class ProducttypeinfoController {
    @Autowired
    private ProducttypeinfoService producttypeinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:producttypeinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = producttypeinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{producttypeid}")
    @RequiresPermissions("sys:producttypeinfo:info")
    public R info(@PathVariable("producttypeid") Integer producttypeid){
        ProducttypeinfoEntity producttypeinfo = producttypeinfoService.getById(producttypeid);

        return R.ok().put("producttypeinfo", producttypeinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:producttypeinfo:save")
    public R save(@RequestBody ProducttypeinfoEntity producttypeinfo){
        producttypeinfoService.save(producttypeinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:producttypeinfo:update")
    public R update(@RequestBody ProducttypeinfoEntity producttypeinfo){
        ValidatorUtils.validateEntity(producttypeinfo);
        producttypeinfoService.updateById(producttypeinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:producttypeinfo:delete")
    public R delete(@RequestBody Integer[] producttypeids){
        producttypeinfoService.removeByIds(Arrays.asList(producttypeids));

        return R.ok();
    }

}
