ALTER TABLE `bj_xm_clybspjl`
ADD COLUMN `chrspzt`  varchar(20) NULL DEFAULT 'wwc' COMMENT '审批状态 ： 未完成-wwc 未通过-wtg 通过-tg' AFTER `chrspjg`;

ALTER TABLE `bj_xm_bgsqjl`
ADD COLUMN `chrspzt`  varchar(20) NULL DEFAULT 'wwc' COMMENT '审批状态 ： 未完成-wwc 未通过-wtg 通过-tg' AFTER `chrspjg`;

ALTER TABLE `bj_xm_gckyzfqk`
ADD COLUMN `chrspzt`  varchar(20) NULL DEFAULT 'wwc' COMMENT '审批状态 ： 未完成-wwc 未通过-wtg 通过-tg' AFTER `chrspjg`;

--小数保存失败
ALTER TABLE `bj_xm_bgsqjl`
MODIFY COLUMN `intgqyx`  float(15,0) NULL DEFAULT NULL COMMENT '工期影响' AFTER `chrbgxq`,
MODIFY COLUMN `intbggs`  float(15,0) NULL DEFAULT NULL COMMENT '变更估算' AFTER `intgqyx`;

