package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.dao.CheckoutinfoDao;
import io.renren.modules.sys.entity.CheckoutinfoEntity;
import io.renren.modules.sys.service.CheckoutinfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("checkoutinfoService")
public class CheckoutinfoServiceImpl extends ServiceImpl<CheckoutinfoDao, CheckoutinfoEntity> implements CheckoutinfoService {

    @Override
        public PageUtils queryPage(Map<String, Object> params) {
            IPage<CheckoutinfoEntity> page = this.page(
                    new Query<CheckoutinfoEntity>().getPage(params),
                    new QueryWrapper<CheckoutinfoEntity>()
            );

            return new PageUtils(page);
    }

}
