package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ProductinfoDao;
import io.renren.modules.sys.entity.ProductinfoEntity;
import io.renren.modules.sys.service.ProductinfoService;


@Service("productinfoService")
public class ProductinfoServiceImpl extends ServiceImpl<ProductinfoDao, ProductinfoEntity> implements ProductinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductinfoEntity> page = this.page(
                new Query<ProductinfoEntity>().getPage(params),
                new QueryWrapper<ProductinfoEntity>()
        );

        return new PageUtils(page);
    }

}
