package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ManagerinfoDao;
import io.renren.modules.sys.entity.ManagerinfoEntity;
import io.renren.modules.sys.service.ManagerinfoService;


@Service("managerinfoService")
public class ManagerinfoServiceImpl extends ServiceImpl<ManagerinfoDao, ManagerinfoEntity> implements ManagerinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ManagerinfoEntity> page = this.page(
                new Query<ManagerinfoEntity>().getPage(params),
                new QueryWrapper<ManagerinfoEntity>()
        );

        return new PageUtils(page);
    }

}
