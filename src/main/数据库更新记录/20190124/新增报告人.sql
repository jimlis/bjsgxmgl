ALTER TABLE `bj_xm_clybspjl`
ADD COLUMN `intbgrid`  bigint(20) NULL DEFAULT NULL COMMENT '报告人id' AFTER `chrspzt`,
ADD COLUMN `chrbgrmc`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报告人名称' AFTER `intbgrid`;

