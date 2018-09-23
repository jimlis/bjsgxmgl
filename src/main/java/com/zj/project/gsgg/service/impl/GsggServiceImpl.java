package com.zj.project.gsgg.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.api.pojo.vo.GsggDetailsVo;
import com.zj.project.gsgg.dao.GsggDao;
import com.zj.project.gsgg.domain.GsggDO;
import com.zj.project.gsgg.domain.GsggNrDO;
import com.zj.project.gsgg.service.GsggNrService;
import com.zj.project.gsgg.service.GsggService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 
 * <pre>
 * 公司公告
 * </pre>
 * <small> 2018-09-13 20:25:05 | lijun</small>
 */
@Service
public class GsggServiceImpl extends BaseServiceImpl<GsggDao, GsggDO> implements GsggService {
    @Autowired
    private FileService fileService;
    @Autowired
    private GsggNrService gsggNrService;

    /**
     * 保存公司公告信息
     * @param gsggDO 公告基础信息
     * @param chrggnr 公告内容
     * @param  fileIds  相关附件
     */
    @Override
    public  void  saveGsggxx(GsggDO gsggDO, String chrggnr, String fileIds) throws  Exception{
                    Long id=gsggDO.getId();
                    UserDO userDO= ShiroUtils.getSysUser();

                    //修改文档基本信息
                    if(null==id){// 新增
                        gsggDO.setDtmfbsj(new Date());
                        gsggDO.setIntdjrid(userDO.getId());
                        gsggDO.setChrdjrmc(userDO.getName());
                        gsggDO.setIntdjrbm(userDO.getDeptId());
                        gsggDO.setChrdjrbmmc(userDO.getDeptName());
                    }else{// 保存

                    }

                    gsggDO.setFcbz(1);
                    gsggDO.setGxsj(new Date());

                    if(null==id){
                        save(gsggDO);
                    }else {
                        updateById(gsggDO);
                    }
               Long     ggid=gsggDO.getId();
                    //查询内容是否存在
                GsggNrDO gsggNrDO=new GsggNrDO();
                                    gsggNrDO.setIntggid(ggid);
                                    gsggNrDO.setFcbz(1);
                QueryWrapper<GsggNrDO> wrapper = new QueryWrapper<GsggNrDO>(gsggNrDO);
                                    gsggNrDO = gsggNrService.getOne(wrapper);

                                    if(gsggNrDO==null){//不存在就新增
                                        gsggNrDO=new GsggNrDO();
                                        gsggNrDO.setIntggid(ggid);
                                        gsggNrDO.setFcbz(1);
                                        gsggNrDO.setGxsj(new Date());
                                        gsggNrDO.setChrggnr(chrggnr);
                                        gsggNrDO.setChrggnr(chrggnr);
                                        gsggNrService.save(gsggNrDO);
                                    }else{//存在就更新
                                        gsggNrDO.setChrggnr(chrggnr);
                                        gsggNrDO.setGxsj(new Date());
                                        gsggNrService.updateById(gsggNrDO);
                                    }

                    //关联附件对象
                    if(StringUtils.isNotEmpty(fileIds)){
                        String[] fileArr=fileIds.trim().split(",");
                        for (String fileid : fileArr){
                            if(StringUtils.isNotEmpty(fileid.trim())){
                                FileDO fileDO=new FileDO();
                                fileDO.setId(Long.parseLong(fileid.trim()));
                                fileDO.setBusType("bj_gsgg");
                                fileDO.setBusId(gsggDO.getId());
                                fileDO.setType(gsggDO.getChrlx());
                                fileService.updateById(fileDO);
                            }
                        }
                    }
        }

    /**
     * 根据公司公告id获取公告详情信息
     * @param id 公告id
     * @return  GsggDetailsVo 公告详情信息
     */
    @Override
    public GsggDetailsVo getGsggDetailsById(Long id){
        // 公告基本信息
        GsggDO gsggDO=getById(id);

        //查询文本信息
        GsggNrDO gsggNrDO=new GsggNrDO();
        gsggNrDO.setFcbz(1);
        gsggNrDO.setIntggid(id);
        QueryWrapper<GsggNrDO> gsggNrDOEntityWrapper=new QueryWrapper<GsggNrDO>(gsggNrDO);
        gsggNrDO = gsggNrService.getOne(gsggNrDOEntityWrapper);

        //查询公告相关附件信息
        FileDO fileDO=new FileDO();
        fileDO.setBusId(id);
        fileDO.setBusType("bj_gsgg");
        QueryWrapper<FileDO> fileDOEntityWrapper=new QueryWrapper<FileDO>(fileDO);
        List<FileDO> fileDOList=fileService.list(fileDOEntityWrapper);

        GsggDetailsVo gsggDetailsVo=new GsggDetailsVo();
        gsggDetailsVo.setId(gsggDO.getId());
        gsggDetailsVo.setChrbt(gsggDO.getChrbt());
        gsggDetailsVo.setDtmfbsj(gsggDO.getDtmfbsj());
        gsggDetailsVo.setChrdjrmc(gsggDO.getChrdjrmc());
        gsggDetailsVo.setChrdjrbmmc(gsggDO.getChrdjrbmmc());
        gsggDetailsVo.setChrggnr(gsggNrDO.getChrggnr());
        gsggDetailsVo.setFileList(fileDOList);
        return  gsggDetailsVo;
        }
}
