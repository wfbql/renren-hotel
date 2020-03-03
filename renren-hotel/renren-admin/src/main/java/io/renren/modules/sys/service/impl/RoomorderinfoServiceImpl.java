package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.RoomorderinfoDao;
import io.renren.modules.sys.entity.RoomorderinfoEntity;
import io.renren.modules.sys.service.RoomorderinfoService;


@Service("roomorderinfoService")
public class RoomorderinfoServiceImpl extends ServiceImpl<RoomorderinfoDao, RoomorderinfoEntity> implements RoomorderinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoomorderinfoEntity> page = this.page(
                new Query<RoomorderinfoEntity>().getPage(params),
                new QueryWrapper<RoomorderinfoEntity>()
        );

        return new PageUtils(page);
    }

}
