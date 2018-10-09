CREATE TABLE `bj_xm_xmcjdw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目id',
  `chrcjdwmc` varchar(255) DEFAULT NULL COMMENT '建设单位名称',
  `chrdjdwmc` varchar(255) DEFAULT NULL COMMENT '代建单位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目承建各方名称';

