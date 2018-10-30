-- 菜单SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    VALUES ('1', '审批流程状态', '/project/splcZt', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '查看', null, 'project:splcZt:splcZt', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '新增', null, 'project:splcZt:add', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '修改', null, 'project:splcZt:edit', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '删除', null, 'project:splcZt:remove', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '批量删除', null, 'project:splcZt:batchRemove', '2', null, '6';
