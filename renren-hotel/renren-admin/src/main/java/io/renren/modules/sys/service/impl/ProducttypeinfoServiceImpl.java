package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ProducttypeinfoDao;
import io.renren.modules.sys.entity.ProducttypeinfoEntity;
import io.renren.modules.sys.service.ProducttypeinfoService;


@Service("producttypeinfoService")
public class ProducttypeinfoServiceImpl extends ServiceImpl<ProducttypeinfoDao, ProducttypeinfoEntity> implements ProducttypeinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProducttypeinfoEntity> page = this.page(
                new Query<ProducttypeinfoEntity>().getPage(params),
                new QueryWrapper<ProducttypeinfoEntity>()
        );

        return new PageUtils(page);
    }

}
