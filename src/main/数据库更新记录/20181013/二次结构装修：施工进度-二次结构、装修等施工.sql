CREATE TABLE `bj_xm_sgjd_ecjgzx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目基本信息id',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `dtmgxrq` date DEFAULT NULL COMMENT '更新日期',
  `intdid` bigint(20) DEFAULT NULL COMMENT '栋楼id：栋楼表id',
  `chrbz` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='二次结构装修：施工进度-二次结构、装修等施工';



CREATE TABLE `bj_xm_sgjd_ecjgzx_wcl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intecjgzxid` bigint(20) DEFAULT NULL COMMENT '二次结构、装修施工id:bj_sgjd_ecjgzx表id',
  `intxh` smallint(6) DEFAULT NULL COMMENT '序号',
  `intlc` smallint(6) DEFAULT NULL COMMENT '楼层',
  `intmc` float DEFAULT NULL COMMENT '门窗（百分比）',
  `intfs` float DEFAULT NULL COMMENT '防水（百分比）',
  `intnbw` float DEFAULT NULL COMMENT '内保温（百分比）',
  `intqt` float DEFAULT NULL COMMENT '砌体（百分比）',
  `intzx` float DEFAULT NULL COMMENT '装修（百分比）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='施工进度-二次结构、装修等施工-完成量';

