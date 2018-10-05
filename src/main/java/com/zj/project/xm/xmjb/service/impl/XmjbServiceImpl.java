package com.zj.project.xm.xmjb.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.xm.xmjb.dao.XmjbDao;
import com.zj.project.xm.xmdl.domain.XmDlDO;
import com.zj.project.xm.xmjb.domain.XmjbDO;
import com.zj.project.xm.xmdl.service.XmDlService;
import com.zj.project.xm.xmjb.service.XmjbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 
 * <pre>
 * 项目基本，所有项目相关表的主表
 * </pre>
 * <small> 2018-09-12 23:09:04 | lijun</small>
 */
@Service
public class XmjbServiceImpl extends BaseServiceImpl<XmjbDao, XmjbDO> implements XmjbService {

    @Autowired
    private FileService fileService;

    @Autowired
    private XmDlService xmDlService;
    /**
     * 保存项目基本信息
     * @param xmjbDO 项目对象
     * @param fileIds 附件ids
     * @param ldJson 新增楼栋json
     *  @param  delLdIds 删除楼栋ids 以逗号隔开
     */
    @Override
    public  void  saveXmjb(XmjbDO xmjbDO,String fileIds,String ldJson,String delLdIds){
        UserDO userDO= ShiroUtils.getSysUser();
        Long id=xmjbDO.getId();
        if(null==id){//新增
            xmjbDO.setFcbz(1);
            xmjbDO.setGxsj(new Date());
            xmjbDO.setIntdjrid(userDO.getId());
            xmjbDO.setIntdjrbm(userDO.getDeptId());
            if(xmjbDO.getDtmdjsj()==null){
                xmjbDO.setDtmdjsj(new Date());
            }
            if(StringUtils.isEmpty(xmjbDO.getChrdjrmc())){
                xmjbDO.setChrdjrmc(userDO.getName());
            }
            save(xmjbDO);
            id=xmjbDO.getId();
        }else{//更新
            xmjbDO.setGxsj(new Date());
            updateById(xmjbDO);
        }

        //关联附件对象
        if(StringUtils.isNotEmpty(fileIds)){
            String[] fileArr=fileIds.trim().split(",");
            for (String fileid : fileArr){
                if(StringUtils.isNotEmpty(fileid.trim())){
                    FileDO fileDO=new FileDO();
                    fileDO.setId(Long.parseLong(fileid.trim()));
                    fileDO.setBusType("bj_xmjb");
                    fileDO.setBusId(id);
                    fileService.updateById(fileDO);
                }
            }
        }

        //保存楼栋信息
      if(StringUtils.isNotEmpty(ldJson)){
          Gson gson=new Gson();
                  List<XmDlDO> xmDlDOList=gson.fromJson(ldJson, new TypeToken<List<XmDlDO>>() {}.getType());
                  if(xmDlDOList!=null&&xmDlDOList.size()>0){
                      xmDlDOList.forEach(xmDlDO->{
                           if(xmDlDO.getId()==null){//新增
                               xmDlDO.setIntxmid(xmjbDO.getId());
                               xmDlDO.setFcbz(1);
                               xmDlDO.setGxsj(new Date());
                               xmDlService.save(xmDlDO);
                           }else{//修改
                               xmDlDO.setGxsj(new Date());
                               xmDlService.updateById(xmDlDO);
                           }
                      });
                  }
      }

      //删除楼栋时间
        if(StringUtils.isNotEmpty(delLdIds)){
            delLdIds=delLdIds.trim().endsWith(",")?delLdIds.substring(0,delLdIds.trim().length()-1):delLdIds;
            Arrays.stream(delLdIds.split(",")).forEach(xmdlid->{
                XmDlDO xmDlDO=new XmDlDO();
                xmDlDO.setId(Long.parseLong(xmdlid));
                xmDlDO.setFcbz(0);
                xmDlDO.setGxsj(new Date());
                xmDlService.updateById(xmDlDO);
            });
        }


    }
}
