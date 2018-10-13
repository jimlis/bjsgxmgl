CREATE TABLE `bj_xm_clybspjl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` tinyint(4) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intclyblx` tinyint(4) DEFAULT NULL COMMENT '材料样板类型：1（土建）2（机电）3（装修）4（园林）5（其他）',
  `intsgdw` bigint(20) DEFAULT NULL COMMENT '施工负责单位：单位名单表施工类别id',
  `intsfdtp` tinyint(4) DEFAULT NULL COMMENT '是否代替品：1（是），0（否）',
  `chrybmc` varchar(100) DEFAULT NULL COMMENT '材料样板名称',
  `chrybwz` varchar(255) DEFAULT NULL COMMENT '材料使用位置',
  `chrgfbz` varchar(255) DEFAULT NULL COMMENT '规范标准',
  `chrbz` varchar(255) DEFAULT NULL COMMENT '备注',
  `chrcyybwj` varchar(255) DEFAULT NULL COMMENT '材料样板文件：文件地址id，多个英文逗号隔开',
  `intsplczt` varchar(10) DEFAULT NULL COMMENT '审批流程状态：code码配置',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='材料样板审批记录';

CREATE TABLE `bj_xm_clybspjl_jszl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intclybspjlid` bigint(20) DEFAULT NULL COMMENT '材料样板审批记录id:bj_clybspjl表',
  `chrpp` varchar(100) DEFAULT NULL COMMENT '品牌',
  `chrjscl` varchar(500) DEFAULT NULL COMMENT '技术资料',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='材料样板审批记录-品牌及技术资料';

