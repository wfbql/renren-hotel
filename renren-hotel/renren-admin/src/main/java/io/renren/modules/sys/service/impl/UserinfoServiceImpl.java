package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.UserinfoDao;
import io.renren.modules.sys.entity.UserinfoEntity;
import io.renren.modules.sys.service.UserinfoService;


@Service("userinfoService")
public class UserinfoServiceImpl extends ServiceImpl<UserinfoDao, UserinfoEntity> implements UserinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserinfoEntity> page = this.page(
                new Query<UserinfoEntity>().getPage(params),
                new QueryWrapper<UserinfoEntity>()
        );

        return new PageUtils(page);
    }

}
