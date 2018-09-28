package com.zj.project.xmjb.service;


import com.zj.platform.common.web.service.BaseService;
import com.zj.project.xmjb.domain.XmjbDO;

/**
 * 
 * <pre>
 * 项目基本，所有项目相关表的主表
 * </pre>
 * <small> 2018-09-12 23:09:04 | lijun</small>
 */
public interface XmjbService extends BaseService<XmjbDO> {
    /**
     * 保存项目基本信息
     * @param xmjbDO 项目对象
     * @param fileIds 附件ids
     * @param ldJson 新增楼栋json
     *  @param  delLdIds 删除楼栋ids 以逗号隔开
     */
    void  saveXmjb(XmjbDO xmjbDO, String fileIds, String ldJson, String delLdIds);
}
