package com.zj.platform.business.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.dict.dao.DictDao;
import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.dict.service.DictService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;



@Service
public class DictServiceImpl extends BaseServiceImpl<DictDao, DictDO> implements DictService {
    @Autowired
    private DictDao dictDao;

    @Override
    public List<DictDO> listType() {
        return dictDao.listType();
    }

    @Override
    public String getName(String type, String value) {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type",type).eq("value",value);
        DictDO selectOne = dictDao.selectOne(queryWrapper);
        return selectOne == null ? "" : selectOne.getName();
    }

    @Override
    public List<DictDO> getHobbyList(UserDO userDO) {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type","hobby");
        List<DictDO> hobbyList = dictDao.selectList(queryWrapper);

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (DictDO hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<DictDO> getSexList() {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type","sex");
        return dictDao.selectList(queryWrapper);
    }

}
