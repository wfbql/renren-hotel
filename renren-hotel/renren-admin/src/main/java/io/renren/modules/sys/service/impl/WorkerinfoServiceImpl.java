package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.WorkerinfoDao;
import io.renren.modules.sys.entity.WorkerinfoEntity;
import io.renren.modules.sys.service.WorkerinfoService;


@Service("workerinfoService")
public class WorkerinfoServiceImpl extends ServiceImpl<WorkerinfoDao, WorkerinfoEntity> implements WorkerinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorkerinfoEntity> page = this.page(
                new Query<WorkerinfoEntity>().getPage(params),
                new QueryWrapper<WorkerinfoEntity>()
        );

        return new PageUtils(page);
    }

}
