-- 菜单SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    VALUES ('1', '施工进度-二次结构装修-其他', '/project/xmSgjdEcjgzxQt', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '查看', null, 'project:xmSgjdEcjgzxQt:xmSgjdEcjgzxQt', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '新增', null, 'project:xmSgjdEcjgzxQt:add', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '修改', null, 'project:xmSgjdEcjgzxQt:edit', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '删除', null, 'project:xmSgjdEcjgzxQt:remove', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '批量删除', null, 'project:xmSgjdEcjgzxQt:batchRemove', '2', null, '6';
