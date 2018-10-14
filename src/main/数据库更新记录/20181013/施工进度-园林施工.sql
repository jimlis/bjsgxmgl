CREATE TABLE `bj_xm_sgjd_ylsg` (
  `id` bigint(20) NOT  NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  `intlx` varchar(10) DEFAULT NULL COMMENT '园林施工类型id：code码配置',
  `intwcl` float DEFAULT NULL COMMENT '完成量（百分比）',
  `chrwcqk` varchar(255) DEFAULT NULL COMMENT '完成情况：文件地址，多个英文逗号隔开',
  `chrzb` varchar(500) DEFAULT NULL COMMENT '备注',
  `dtmsprq` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审批日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='施工进度-园林施工';

