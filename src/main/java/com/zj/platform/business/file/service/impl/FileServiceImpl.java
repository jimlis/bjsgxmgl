package com.zj.platform.business.file.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.file.dao.FileDao;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.util.FileUtil;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class FileServiceImpl extends BaseServiceImpl<FileDao, FileDO> implements FileService {

    @Autowired
    private ConfigurableEnvironment environment;

    /**
     * <pre>
     * 上传文件到硬盘
     * </pre>、
     * @param file 文件对象
     * @param  busType 业务表名
     * @return
     */
    @Override
    public FileDO uploadFile(MultipartFile file,String busType)  throws Exception{
        String uploadPath=environment.getProperty("uploadPath");
        if(StringUtils.isEmpty(uploadPath)) throw  new CommonException("请配置上传文件的路径（uploadPath）");
        LocalDate localDate=LocalDate.now();
        uploadPath=uploadPath+ File.separator+"wdb"+File.separator+localDate.getYear()+File.separator+
                localDate.getMonth().getValue();
        File folder=new File(uploadPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        Long currentTimeMillis=System.currentTimeMillis();
        String fileName=file.getOriginalFilename();
        String name=file.getName();
        String[] fileArr=fileName.split("\\.");
        String prefix=fileArr[0];
        String suffix=fileArr.length>=2?fileArr[1]:"";

        String newFileName=prefix+"_"+ UUID.randomUUID().toString().replace("-","")+"."+suffix;
        String url= File.separator+"wdb"+File.separator+localDate.getYear()+File.separator+
                localDate.getMonth().getValue()+File.separator+newFileName;
        File createfile=new File(uploadPath+File.separator+newFileName);
        if(!createfile.exists()){
            createfile.createNewFile();
        }
        file.transferTo(createfile);

        FileDO fileDO=new FileDO();
        UserDO userDO= ShiroUtils.getSysUser();
        fileDO.setBusType(StringUtils.isEmpty(busType)?"bj_wdb":busType);
        fileDO.setFileName(fileName);
        fileDO.setFileSize(file.getSize());
        fileDO.setCreateUserId(userDO.getId());
        fileDO.setCreateUserName(userDO.getName());
        fileDO.setCreateDeptId(userDO.getDeptId());
        fileDO.setCreateDeptName(userDO.getDeptName());
        fileDO.setCreateDate(new Date());
        fileDO.setUrl(url);
        save(fileDO);
        return fileDO;
    }

    @Override
    public void downFile(Long id, HttpServletRequest request, HttpServletResponse response) {
        FileDO fileDO=getById(id);
        if(fileDO!=null){
            String uploadPath=environment.getProperty("uploadPath");
            String url=fileDO.getUrl();
            String filePath=uploadPath+url;
            try (InputStream in = new FileInputStream(new File(filePath))) {
                FileUtil.writeFj(request,response,fileDO.getFileName(),in);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<FileDO> queryList(FileDO fileDO) {
        return baseMapper.queryList(fileDO);
    }

    /**
     * 根据busType、busId、type获取文件集合
     * @param busType 业务表名称
     * @param busId 对应业务id
     * @param type 文件类型
     * @return List<FileDO>
     */
    @Override
    public List<FileDO> queryFileDOList(String busType,Long busId,String  type ){
        FileDO fileDO=new FileDO();
        fileDO.setBusType(busType);
        fileDO.setBusId(busId);
        fileDO.setType(type);
        QueryWrapper<FileDO> queryWrapper=new QueryWrapper<FileDO>(fileDO).orderByDesc("createDate");
        return  list(queryWrapper);
    }
}
