CREATE TABLE `bj_xm_sgjd_ztjgsg` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `dtmbgrq` date DEFAULT NULL COMMENT '报告日期',
  `intsgwzd` bigint(20) DEFAULT NULL COMMENT '施工位置（栋）:栋楼表id',
  `intsgwzc` bigint(20) DEFAULT NULL COMMENT '施工位置（层）：层表id',
  `dtmjzqrq` date DEFAULT NULL COMMENT '浇筑砼日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='施工进度-主体结构施工';

