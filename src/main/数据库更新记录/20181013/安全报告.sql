CREATE TABLE `bj_xm_aqbg` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `chraqwtbs` varchar(255) DEFAULT NULL COMMENT '安全问题描述',
  `chraqwtwz` varchar(255) DEFAULT NULL COMMENT '安全问题位置',
  `chrzb` varchar(255) DEFAULT NULL COMMENT '备注',
  `intsgdw` bigint(20) DEFAULT NULL COMMENT '施工负责单位：单位名单表施工类别id',
  `dtmtzrq` date DEFAULT NULL COMMENT '通知施工方日期',
  `dtmwczgrq` date DEFAULT NULL COMMENT '完成整改日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='安全报告';

