ALTER TABLE `bj_xm_clybspjl`
ADD COLUMN `chrspjg`  varchar(1000) NULL COMMENT '审批结果' AFTER `chrppmc`;

ALTER TABLE `bj_xm_bgsqjl`
ADD COLUMN `chrspjg`  varchar(1000) NULL COMMENT '审批结果' AFTER `intbgthid`;

ALTER TABLE `bj_xm_gckyzfqk`
ADD COLUMN `chrspjg`  varchar(1000) NULL COMMENT '审批结果' AFTER `chrbgrmc`;
