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

import io.renren.modules.sys.entity.WorkerinfoEntity;
import io.renren.modules.sys.service.WorkerinfoService;
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
@RequestMapping("sys/workerinfo")
public class WorkerinfoController {
    @Autowired
    private WorkerinfoService workerinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:workerinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workerinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{workerid}")
    @RequiresPermissions("sys:workerinfo:info")
    public R info(@PathVariable("workerid") String workerid){
        WorkerinfoEntity workerinfo = workerinfoService.getById(workerid);

        return R.ok().put("workerinfo", workerinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:workerinfo:save")
    public R save(@RequestBody WorkerinfoEntity workerinfo){
        workerinfoService.save(workerinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:workerinfo:update")
    public R update(@RequestBody WorkerinfoEntity workerinfo){
        ValidatorUtils.validateEntity(workerinfo);
        workerinfoService.updateById(workerinfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:workerinfo:delete")
    public R delete(@RequestBody String[] workerids){
        workerinfoService.removeByIds(Arrays.asList(workerids));

        return R.ok();
    }

}
