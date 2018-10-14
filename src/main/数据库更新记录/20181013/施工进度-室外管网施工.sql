CREATE TABLE `bj_xm_sgjd_swgwsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `chrcsdtz` varchar(255) DEFAULT NULL COMMENT '最新版csd图纸:文件地址id，逗号分隔多个文件',
  `dtmsprq` date DEFAULT NULL COMMENT 'csd图纸通过审批日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  `intlxid` varchar(10) DEFAULT NULL COMMENT '室外管网类型id：code码配置',
  `chrsgqy` varchar(255) DEFAULT NULL COMMENT '施工区域',
  `intwcl` float DEFAULT NULL COMMENT '完成量（百分比）',
  `chrwcqk` varchar(255) DEFAULT NULL COMMENT '完成情况：文件地址，多个英文逗号隔开',
  `chrbz` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='施工进度-室外管网施工';

