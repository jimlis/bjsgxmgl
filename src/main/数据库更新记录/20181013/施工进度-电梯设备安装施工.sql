CREATE TABLE `bj_xm_sgjd_dtsbazsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intsgwz` bigint(20) DEFAULT NULL COMMENT '施工位置：栋楼表id',
  `chrdtbh` varchar(255) DEFAULT NULL COMMENT '电梯编号',
  `dtmdhrq` date DEFAULT NULL COMMENT '电梯设备到货日期',
  `dtmyjrq` date DEFAULT NULL COMMENT '电梯井道移交日期',
  `dtmwcrq` date DEFAULT NULL COMMENT '电梯轨道完成日期',
  `intwcbl` float DEFAULT NULL COMMENT '电梯门套完成比例(百分比)',
  `intazbl` float DEFAULT NULL COMMENT '机房设备安装比例(百分比)',
  `dtmyxrq` date DEFAULT NULL COMMENT '电梯试运行日期',
  `dtmysrq` date DEFAULT NULL COMMENT '电梯通过验收日期',
  `chrhgzm` varchar(255) DEFAULT NULL COMMENT '电梯验收合格证明:存放文件表id，多个用英文逗号隔开',
  `dtmdqrq` date DEFAULT NULL COMMENT '合格证明到期日期',
  `intbgrid` bigint(20) DEFAULT NULL COMMENT '报告人id',
  `chrbgrmc` varchar(100) DEFAULT NULL COMMENT '报告人名称',
  `chrbz` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='施工进度-电梯设备安装施工';

