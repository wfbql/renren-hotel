package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.MembertypeinfoDao;
import io.renren.modules.sys.entity.MembertypeinfoEntity;
import io.renren.modules.sys.service.MembertypeinfoService;


@Service("membertypeinfoService")
public class MembertypeinfoServiceImpl extends ServiceImpl<MembertypeinfoDao, MembertypeinfoEntity> implements MembertypeinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MembertypeinfoEntity> page = this.page(
                new Query<MembertypeinfoEntity>().getPage(params),
                new QueryWrapper<MembertypeinfoEntity>()
        );

        return new PageUtils(page);
    }

}
