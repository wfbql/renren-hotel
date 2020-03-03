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

import io.renren.modules.sys.entity.OrderiteminfoEntity;
import io.renren.modules.sys.service.OrderiteminfoService;
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
@RequestMapping("sys/orderiteminfo")
public class OrderiteminfoController {
    @Autowired
    private OrderiteminfoService orderiteminfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:orderiteminfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderiteminfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderitemid}")
    @RequiresPermissions("sys:orderiteminfo:info")
    public R info(@PathVariable("orderitemid") String orderitemid){
        OrderiteminfoEntity orderiteminfo = orderiteminfoService.getById(orderitemid);

        return R.ok().put("orderiteminfo", orderiteminfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:orderiteminfo:save")
    public R save(@RequestBody OrderiteminfoEntity orderiteminfo){
        orderiteminfoService.save(orderiteminfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:orderiteminfo:update")
    public R update(@RequestBody OrderiteminfoEntity orderiteminfo){
        ValidatorUtils.validateEntity(orderiteminfo);
        orderiteminfoService.updateById(orderiteminfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:orderiteminfo:delete")
    public R delete(@RequestBody String[] orderitemids){
        orderiteminfoService.removeByIds(Arrays.asList(orderitemids));

        return R.ok();
    }

}
