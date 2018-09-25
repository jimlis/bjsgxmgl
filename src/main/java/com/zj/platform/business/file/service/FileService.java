package com.zj.platform.business.file.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.common.web.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件
 */
public interface FileService extends BaseService<FileDO> {

    /**
     * <pre>
     * 上传文件到硬盘
     * </pre>、
     * @param file 文件对象
     * @param  busType 业务表名
     * @return FileDO 文件对象
     *
     */
    FileDO uploadFile (MultipartFile file,String busType) throws Exception;

    /**
     * 根据附件id下载附件
     * @param id
     * @param request
     * @param response
     */
    void downFile(Long id, HttpServletRequest request, HttpServletResponse response);


    List<FileDO> queryList(FileDO fileDO);
}
