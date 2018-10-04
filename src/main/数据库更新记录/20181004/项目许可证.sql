CREATE TABLE `bj_xm_xkz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目id',
  `intxkzlx` varchar(10) DEFAULT NULL COMMENT '许可证类型:1（规划许可证），2（施工许可证）',
  `chrxkzbh` varchar(255) DEFAULT NULL COMMENT '许可证编号',
  `chrffbm` varchar(255) DEFAULT NULL COMMENT '发放部门',
  `dtmfzrq` date DEFAULT NULL COMMENT '发证日期',
  `dtmyxrq` date DEFAULT NULL COMMENT '有效截止日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基本信息-许可证';
