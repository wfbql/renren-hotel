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

import io.renren.modules.sys.entity.CustomercheckininfoEntity;
import io.renren.modules.sys.service.CustomercheckininfoService;
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
@RequestMapping("sys/customercheckininfo")
public class CustomercheckininfoController {
    @Autowired
    private CustomercheckininfoService customercheckininfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:customercheckininfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customercheckininfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderid}")
    @RequiresPermissions("sys:customercheckininfo:info")
    public R info(@PathVariable("orderid") String orderid){
        CustomercheckininfoEntity customercheckininfo = customercheckininfoService.getById(orderid);

        return R.ok().put("customercheckininfo", customercheckininfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:customercheckininfo:save")
    public R save(@RequestBody CustomercheckininfoEntity customercheckininfo){
        customercheckininfoService.save(customercheckininfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:customercheckininfo:update")
    public R update(@RequestBody CustomercheckininfoEntity customercheckininfo){
        ValidatorUtils.validateEntity(customercheckininfo);
        customercheckininfoService.updateById(customercheckininfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:customercheckininfo:delete")
    public R delete(@RequestBody String[] orderids){
        customercheckininfoService.removeByIds(Arrays.asList(orderids));

        return R.ok();
    }

}
