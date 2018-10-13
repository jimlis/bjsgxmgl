CREATE TABLE `bj_xm_dl_cs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `栋楼id` bigint(20) DEFAULT NULL COMMENT '栋楼id：bj_xm_dl表',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `chrcmc` varchar(100) DEFAULT NULL COMMENT '层名称',
  `chrcjs` varchar(255) DEFAULT NULL COMMENT '层介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基本信息-栋楼-层数,记录每层情况';

