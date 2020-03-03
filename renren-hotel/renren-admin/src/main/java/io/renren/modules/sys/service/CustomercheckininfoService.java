package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.CustomercheckininfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:45:38
 */
public interface CustomercheckininfoService extends IService<CustomercheckininfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

