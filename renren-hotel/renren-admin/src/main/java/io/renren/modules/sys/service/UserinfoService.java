package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.UserinfoEntity;

import java.util.Map;

/**
 * �û���Ϣ��
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:45:38
 */
public interface UserinfoService extends IService<UserinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

