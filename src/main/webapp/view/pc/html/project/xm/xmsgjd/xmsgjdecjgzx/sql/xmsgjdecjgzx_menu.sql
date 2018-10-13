-- 菜单SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    VALUES ('1', '二次结构装修：施工进度-二次结构、装修等施工', '/project/xmSgjdEcjgzx', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '查看', null, 'project:xmSgjdEcjgzx:xmSgjdEcjgzx', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '新增', null, 'project:xmSgjdEcjgzx:add', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '修改', null, 'project:xmSgjdEcjgzx:edit', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '删除', null, 'project:xmSgjdEcjgzx:remove', '2', null, '6';
INSERT INTO `sys_menu` (`parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`)
    SELECT @parentId, '批量删除', null, 'project:xmSgjdEcjgzx:batchRemove', '2', null, '6';
