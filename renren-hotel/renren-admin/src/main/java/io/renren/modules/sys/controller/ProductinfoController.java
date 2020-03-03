package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.ProductinfoEntity;
import io.renren.modules.sys.service.ProductinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-02-04 20:58:47
 */
@RestController
@RequestMapping("sys/productinfo")
public class ProductinfoController {
    @Autowired
    private ProductinfoService productinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:productinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{productid}")
    @RequiresPermissions("sys:productinfo:info")
    public R info(@PathVariable("productid") Integer productid){
        ProductinfoEntity productinfo = productinfoService.getById(productid);

        return R.ok().put("productinfo", productinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:productinfo:save")
    public R save(@RequestBody ProductinfoEntity productinfo){
        productinfoService.save(productinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:productinfo:update")
    public R update(@RequestBody ProductinfoEntity productinfo){
        ValidatorUtils.validateEntity(productinfo);
        productinfoService.updateById(productinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:productinfo:delete")
    public R delete(@RequestBody Integer[] productids){
        productinfoService.removeByIds(Arrays.asList(productids));

        return R.ok();
    }

}
