CREATE TABLE `bj_xm_zfxcyzxys` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intxclb` varchar(10) DEFAULT NULL COMMENT '巡查类别：code码',
  `intxcbm` varchar(10) DEFAULT NULL COMMENT '巡查部门：code码',
  `chrxcry` varchar(100) DEFAULT NULL COMMENT '巡查人员',
  `dtmxcrq` date DEFAULT NULL COMMENT '巡查日期',
  `chrzb` varchar(500) DEFAULT NULL COMMENT '备注',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='政府巡查与专项验收：政府部门巡查/专项验收记录';

