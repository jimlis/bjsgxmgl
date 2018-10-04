--
CREATE TABLE `bj_xm_zpjl` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `dtmbgrq` date DEFAULT NULL COMMENT '报告日期',
  `intbglb` varchar(10) DEFAULT NULL COMMENT '1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度）',
  `chrpswz` varchar(255) DEFAULT NULL COMMENT '根据页面提交存储',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基本信息-照片记录';


CREATE TABLE `bj_xm_zpms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `intsslx` varchar(10) DEFAULT NULL COMMENT '所属类型：1（照片记录），2（基础施工），3（主体结构施工），4（样板施工记录），5（质量缺陷报告），6（安全报告）7（巡查与验收）',
  `intzpssid` bigint(20) DEFAULT NULL COMMENT '照片所属id:根据所属类型不同找不同表id',
  `chrzpms` varchar(255) DEFAULT NULL COMMENT '照片描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基本信息-照片描述';


