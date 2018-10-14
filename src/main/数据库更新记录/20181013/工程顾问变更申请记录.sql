CREATE TABLE `bj_xm_bgsqjl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intbgsqlx` tinyint(4) DEFAULT NULL COMMENT '变更申请类型：1（顾问变更）2（工程变更）3（其他）',
  `chrbgsqbh` varchar(100) DEFAULT NULL COMMENT '变更申请编号',
  `chrbgsqmc` varchar(100) DEFAULT NULL COMMENT '变更申请名称',
  `intsfqd` tinyint(4) DEFAULT '0' COMMENT '是否取代之前变更申请：1（是）0（否）',
  `chrbgxq` varchar(255) DEFAULT NULL COMMENT '变更详情',
  `intgqyx` float DEFAULT NULL COMMENT '工期影响',
  `intbggs` float DEFAULT NULL COMMENT '变更估算',
  `chrbz` varchar(255) DEFAULT NULL COMMENT '备注',
  `chrbgsqwj` varchar(255) DEFAULT NULL COMMENT '变更申请文件：文件地址，多个逗号隔开',
  `intsplczt` varchar(10) DEFAULT NULL COMMENT '审批流程状态：code码表',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程/顾问工作变更申请记录';

