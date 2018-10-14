CREATE TABLE `bj_xm_zlqxbg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` tinyint(4) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intqxlx` tinyint(4) DEFAULT NULL COMMENT '质量缺陷类型：1(土建)2（机电）3（装修）4（园林）5（其他）',
  `chrqxms` varchar(255) DEFAULT NULL COMMENT '质量缺陷描述',
  `chrqxwz` varchar(255) DEFAULT NULL COMMENT '质量缺陷位置',
  `chrbz` varchar(500) DEFAULT NULL COMMENT '备注',
  `intsgdw` bigint(20) DEFAULT NULL COMMENT '施工负责单位：单位名单表id',
  `dtmtzrq` date DEFAULT NULL COMMENT '通知施工方日期',
  `dtmzgwcrq` date DEFAULT NULL COMMENT '整改完成日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='质量缺陷报告';

