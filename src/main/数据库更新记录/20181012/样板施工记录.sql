CREATE TABLE `bj_xm_ybsgjl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `intxh` tinyint(4) DEFAULT NULL COMMENT '序号',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `dtmwcrq` date DEFAULT NULL COMMENT '样板施工完成日期',
  `intyblx` varchar(10) DEFAULT NULL COMMENT '现场样板类型code码：1（土建），2（机电），3（装修），4（园林），5（其他）',
  `chrybms` varchar(255) DEFAULT NULL COMMENT '样板描述',
  `chrybwz` varchar(255) DEFAULT NULL COMMENT '样板位置',
  `dtmsprq` date DEFAULT NULL COMMENT '通过审批日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  `chrbz` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='样板施工记录';

