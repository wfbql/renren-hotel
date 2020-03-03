package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.CustomercheckininfoDao;
import io.renren.modules.sys.entity.CustomercheckininfoEntity;
import io.renren.modules.sys.service.CustomercheckininfoService;


@Service("customercheckininfoService")
public class CustomercheckininfoServiceImpl extends ServiceImpl<CustomercheckininfoDao, CustomercheckininfoEntity> implements CustomercheckininfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomercheckininfoEntity> page = this.page(
                new Query<CustomercheckininfoEntity>().getPage(params),
                new QueryWrapper<CustomercheckininfoEntity>()
        );

        return new PageUtils(page);
    }

}
