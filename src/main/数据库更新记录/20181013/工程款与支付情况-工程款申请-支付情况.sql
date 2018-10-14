CREATE TABLE `bj_xm_gckyzfqk` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(11) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intdwlx` tinyint(4) DEFAULT NULL COMMENT '单位类型：1（顾问单位）2（施工单位）3（其他单位）',
  `intdwmcid` bigint(11) DEFAULT NULL COMMENT '单位名称：单位名单表id',
  `intbcsqqs` smallint(6) DEFAULT NULL COMMENT '本次申请期数',
  `intbqsqje` float DEFAULT NULL COMMENT '本期申请金额',
  `intbqhsffje` float DEFAULT NULL COMMENT '本期核实发放金额',
  `inthtje` float DEFAULT NULL COMMENT '合同金额',
  `chrbz` varchar(255) DEFAULT NULL COMMENT '备注',
  `chrzfzs` varchar(255) DEFAULT NULL COMMENT '支付证书：文件地址，多个以英文逗号隔开',
  `intsplcztid` varchar(10) DEFAULT NULL COMMENT '审批流程状态：code码配置',
  `intbgrid` bigint(11) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程款与支付情况:工程款申请/支付情况';

