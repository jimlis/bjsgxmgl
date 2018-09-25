package com.zj.platform.business.file.dao;


import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.common.web.dao.Dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 文件
 */
public interface FileDao extends Dao<FileDO> {

    List<FileDO> queryList(FileDO fileDO);
}
