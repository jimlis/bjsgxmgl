-- 菜单SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    VALUES ('1', '施工进度-基础施工扩展', '/project/xmSgjdJcsgKz', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '查看', null, 'project:xmSgjdJcsgKz:xmSgjdJcsgKz', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '新增', null, 'project:xmSgjdJcsgKz:add', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '修改', null, 'project:xmSgjdJcsgKz:edit', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '删除', null, 'project:xmSgjdJcsgKz:remove', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '批量删除', null, 'project:xmSgjdJcsgKz:batchRemove', '2', null, '6';
