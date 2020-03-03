package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.RoominfoDao;
import io.renren.modules.sys.entity.RoominfoEntity;
import io.renren.modules.sys.service.RoominfoService;


@Service("roominfoService")
public class RoominfoServiceImpl extends ServiceImpl<RoominfoDao, RoominfoEntity> implements RoominfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoominfoEntity> page = this.page(
                new Query<RoominfoEntity>().getPage(params),
                new QueryWrapper<RoominfoEntity>()
        );

        return new PageUtils(page);
    }

}
