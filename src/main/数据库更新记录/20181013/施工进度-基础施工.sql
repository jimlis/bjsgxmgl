
CREATE TABLE `bj_xm_sgjd_jcsg` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmbgrq` date DEFAULT NULL COMMENT '报告日期',
  `intsgwzid` bigint(20) DEFAULT NULL COMMENT '施工位置：栋楼表id',
  `intjclx` tinyint(4) DEFAULT NULL COMMENT '基础类型：1（独立基础），2（筏板），3（桩基础）',
  `intzjczsl` smallint(6) DEFAULT NULL COMMENT '如果基础类型为桩基础则需要填总数量',
  `intzjcwcl` smallint(6) DEFAULT NULL COMMENT '如果基础类型为桩基础则需要填完成数量',
  `intwcl` float DEFAULT NULL COMMENT '完成量（百分比）',
  `dtmjzqrq` date DEFAULT NULL COMMENT '浇筑砼日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基本信息-施工进度-基础施工';

