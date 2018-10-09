CREATE TABLE `bj_xm_dwmd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目id:bj_xmjb表',
  `intxh` tinyint(4) DEFAULT NULL COMMENT '序号',
  `intlxmd` varbinary(10) DEFAULT NULL COMMENT '类型名单：1（顾问单位），2（施工单位），3（其他工作单位名称）',
  `intdwlxid` varbinary(10) DEFAULT NULL COMMENT '单位类型id：code表码',
  `chrdwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基本信息-所有单位名单';

