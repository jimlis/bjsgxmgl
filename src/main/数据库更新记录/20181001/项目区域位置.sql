CREATE TABLE `bj_xm_qyjwz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fcbz` tinyint(4) DEFAULT '1' COMMENT '逻辑废除：0（废除），1（正常）',
  `gxsj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改新增删除时间',
  `intxmid` bigint(20) DEFAULT NULL COMMENT '项目id:bj_xmjb表',
  `chrqy` varchar(255) DEFAULT NULL COMMENT '区域',
  `chrwz` varchar(255) DEFAULT NULL COMMENT '位置',
  `chrgdjt` varchar(255) DEFAULT NULL COMMENT '轨道交通',
  `chrzwzk` varchar(500) DEFAULT NULL COMMENT '周围状况',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='所在区域及位置';

