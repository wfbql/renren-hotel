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

import io.renren.modules.sys.entity.CheckoutinfoEntity;
import io.renren.modules.sys.service.CheckoutinfoService;
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
@RequestMapping("sys/checkoutinfo")
public class CheckoutinfoController {
    @Autowired
    private CheckoutinfoService checkoutinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:checkoutinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = checkoutinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderid}")
    @RequiresPermissions("sys:checkoutinfo:info")
    public R info(@PathVariable("orderid") String orderid){
        CheckoutinfoEntity checkoutinfo = checkoutinfoService.getById(orderid);

        return R.ok().put("checkoutinfo", checkoutinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:checkoutinfo:save")
    public R save(@RequestBody CheckoutinfoEntity checkoutinfo){
        checkoutinfoService.save(checkoutinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:checkoutinfo:update")
    public R update(@RequestBody CheckoutinfoEntity checkoutinfo){
        ValidatorUtils.validateEntity(checkoutinfo);
        checkoutinfoService.updateById(checkoutinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:checkoutinfo:delete")
    public R delete(@RequestBody String[] orderids){
        checkoutinfoService.removeByIds(Arrays.asList(orderids));

        return R.ok();
    }

}
