package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ManagertypeinfoDao;
import io.renren.modules.sys.entity.ManagertypeinfoEntity;
import io.renren.modules.sys.service.ManagertypeinfoService;


@Service("managertypeinfoService")
public class ManagertypeinfoServiceImpl extends ServiceImpl<ManagertypeinfoDao, ManagertypeinfoEntity> implements ManagertypeinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ManagertypeinfoEntity> page = this.page(
                new Query<ManagertypeinfoEntity>().getPage(params),
                new QueryWrapper<ManagertypeinfoEntity>()
        );

        return new PageUtils(page);
    }

}
