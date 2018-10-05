CREATE TABLE `bj_xm_ghzb` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目id:bj_xmjb',
  `chrzbmc` varchar(255) DEFAULT NULL COMMENT '指标名称',
  `intzbz` float DEFAULT NULL COMMENT '指标值',
  `chrzdw` varchar(50) DEFAULT NULL COMMENT '值单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基本信息-规划指标数据';

