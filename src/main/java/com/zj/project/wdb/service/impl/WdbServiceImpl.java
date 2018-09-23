package com.zj.project.wdb.service.impl;


import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.wdb.dao.WdbDao;
import com.zj.project.wdb.domain.WdbDO;
import com.zj.project.wdb.service.WdbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2018-09-09 20:40:48 | lijun</small>
 */
@Service
public class WdbServiceImpl extends BaseServiceImpl<WdbDao, WdbDO> implements WdbService {

    @Autowired
    private FileService fileService;
    /**
     * 保存文档信息
     * @param wdbDO 文档对象
     * @param fileIds 相关联的附件ids 多个以逗号隔开
     */
    @Override
    public  void  saveWdbxx(WdbDO wdbDO, String fileIds) throws  Exception{
                Long id=wdbDO.getId();

                wdbDO.setFcbz(1);
                //修改文档基本信息
                if(null==id){// 新增

                }else{// 保存

                }

                if(null==id){
                    save(wdbDO);
                }else {
                    updateById(wdbDO);
                }

                //关联附件对象
                if(StringUtils.isNotEmpty(fileIds)){
                         String[] fileArr=fileIds.trim().split(",");
                         for (String fileid : fileArr){
                                if(StringUtils.isNotEmpty(fileid.trim())){
                                    FileDO fileDO=new FileDO();
                                    fileDO.setId(Long.parseLong(fileid.trim()));
                                    fileDO.setBusType("bj_wdb");
                                    fileDO.setBusId(wdbDO.getId());
                                    fileDO.setType(wdbDO.getType());
                                    fileService.updateById(fileDO);
                                }
                         }
                }
    }
}
