package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.OrderiteminfoDao;
import io.renren.modules.sys.entity.OrderiteminfoEntity;
import io.renren.modules.sys.service.OrderiteminfoService;


@Service("orderiteminfoService")
public class OrderiteminfoServiceImpl extends ServiceImpl<OrderiteminfoDao, OrderiteminfoEntity> implements OrderiteminfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderiteminfoEntity> page = this.page(
                new Query<OrderiteminfoEntity>().getPage(params),
                new QueryWrapper<OrderiteminfoEntity>()
        );

        return new PageUtils(page);
    }

}
