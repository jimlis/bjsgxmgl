package com.zj.project.gsgg.service;


import com.zj.platform.common.web.service.BaseService;
import com.zj.project.api.pojo.vo.GsggDetailsVo;
import com.zj.project.gsgg.domain.GsggDO;

/**
 * 
 * <pre>
 * 公司公告
 * </pre>
 * <small> 2018-09-13 20:25:05 | lijun</small>
 */
public interface GsggService extends BaseService<GsggDO> {

    /**
     * 保存公司公告信息
     * @param gsggDO 公告基础信息
     * @param chrggnr 公告内容
     * @param  fileIds  相关附件
     */
    void  saveGsggxx(GsggDO gsggDO, String chrggnr, String fileIds) throws Exception;

    /**
     * 根据公司公告id获取公告详情信息
     * @param id 公告id
     * @return  GsggDetailsVo 公告详情信息
     */
    GsggDetailsVo getGsggDetailsById(Long id);
}
