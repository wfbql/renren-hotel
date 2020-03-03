package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.ProductinfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-02-04 20:58:47
 */
public interface ProductinfoService extends IService<ProductinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

