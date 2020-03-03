package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.ProducttypeinfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:45:38
 */
public interface ProducttypeinfoService extends IService<ProducttypeinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

