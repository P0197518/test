/*
 Navicat Premium Data Transfer

 Source Server         : MariaDB
 Source Server Type    : MariaDB
 Source Server Version : 100605 (10.6.5-MariaDB)
 Source Host           : 127.0.0.1:3306
 Source Schema         : easyui-admin

 Target Server Type    : MariaDB
 Target Server Version : 100605 (10.6.5-MariaDB)
 File Encoding         : 65001

 Date: 31/10/2024 17:25:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gender
-- ----------------------------
DROP TABLE IF EXISTS `gender`;
CREATE TABLE `gender`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别名称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '性别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gender
-- ----------------------------
INSERT INTO `gender` VALUES (1, '男', '/css/themes/icons/male.png');
INSERT INTO `gender` VALUES (2, '女', '/css/themes/icons/female.png');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '页面地址',
  `type` tinyint(3) UNSIGNED NOT NULL COMMENT '类型（0-目录；1-菜单）',
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父菜单id',
  `pxh` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('20210609155649', '菜单管理', 'icon-remove', '', 0, '', NULL);
INSERT INTO `menu` VALUES ('20211113014324', '菜单列表', 'icon-script', '/html/menu_list.html', 1, '20210609155649', NULL);
INSERT INTO `menu` VALUES ('20211113135935', '角色-菜单列表', 'icon-script', '/html/role_menu_list.html', 1, '20210609155649', NULL);
INSERT INTO `menu` VALUES ('20231122055834', '权限管理', 'icon-shield', '', 0, '', NULL);
INSERT INTO `menu` VALUES ('20231122060854', '权限列表', 'icon-script', '/html/permission_list.html', 1, '20231122055834', NULL);
INSERT INTO `menu` VALUES ('20231122195740', '角色-权限列表', 'icon-script', '/html/role_permission_list.html', 1, '20231122055834', NULL);
INSERT INTO `menu` VALUES ('20231122213701', '用户管理', 'icon-user', '', 0, '', NULL);
INSERT INTO `menu` VALUES ('20231122213744', '用户列表', 'icon-script', '/html/user_list.html', 1, '20231122213701', NULL);
INSERT INTO `menu` VALUES ('20231122213900', '用户-角色列表', 'icon-script', '/html/user_role_list.html', 1, '20231122213701', NULL);
INSERT INTO `menu` VALUES ('20240118024438', '数据管理', 'icon-large-chart', '', 0, '', NULL);
INSERT INTO `menu` VALUES ('20240118024530', '歌曲列表', 'icon-script', '/html/song_list.html', 1, '20240118024438', NULL);
INSERT INTO `menu` VALUES ('20240318211953', '词语列表', 'icon-script', '/html/word_list.html', 1, '20240118024438', NULL);
INSERT INTO `menu` VALUES ('20240514143436', '角色列表', 'icon-script', '/html/role_list.html', 1, '20231122213701', NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限值',
  `type` tinyint(3) UNSIGNED NOT NULL COMMENT '权限类型（父权限/子权限）',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口路径',
  `method` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求方式（0-get；1-post）',
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级权限ID',
  `anonymity` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '是否允许匿名访问',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('easyui-admin_CacheController', '缓存管理', 'cache:*', 0, '/cache', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_CacheController_location', '清理应用的缓存', 'cache:clean', 1, '/cache/clean', 1, 'easyui-admin_CacheController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_GenderController', '性别管理', 'gender:*', 0, '/gender', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_GenderController_selectAll', '查询全部性别', 'gender:selectAll', 1, '/gender/selectAll', 0, 'easyui-admin_GenderController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_GenderController_selectById', '通过ID查询性别', 'gender:selectById', 1, '/gender/selectById', 0, 'easyui-admin_GenderController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController', '菜单管理', 'menu:*', 0, '/menu', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_deleteById', '通过id删除菜单', 'menu:deleteById', 1, '/menu/deleteById', 1, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_insert', '添加菜单', 'menu:insert', 1, '/menu/insert', 1, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_listMenus', '查询用户的侧栏菜单', 'menu:listMenus', 1, '/menu/listMenus', 0, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_listTree', '查询用户的菜单树', 'menu:listTree', 1, '/menu/listTree', 0, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_listTreeGrid', '查询菜单树形网格', 'menu:listTreeGrid', 1, '/menu/listTreeGrid', 0, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_selectAll', '查询全部菜单', 'menu:selectAll', 1, '/menu/selectAll', 0, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_selectById', '通过id查询菜单信息', 'menu:selectById', 1, '/menu/selectById', 0, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_selectByPage', '分页查询菜单列表', 'menu:selectByPage', 1, '/menu/selectByPage', 0, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_selectDirectory', '查询全部目录', 'menu:selectDirectory', 1, '/menu/selectDirectory', 0, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_MenuController_updateById', '通过id修改菜单信息', 'menu:updateById', 1, '/menu/updateById', 1, 'easyui-admin_MenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController', '权限管理', 'permission:*', 0, '/permission', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_deleteById', '通过id删除权限', 'permission:deleteById', 1, '/permission/deleteById', 1, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_insert', '添加权限', 'permission:insert', 1, '/permission/insert', 1, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_resources', '初始化权限列表', 'permission:resources', 1, '/permission/resources', 1, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_selectAll', '查询全部权限', 'permission:selectAll', 1, '/permission/selectAll', 0, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_selectById', '通过id查询权限', 'permission:selectById', 1, '/permission/selectById', 0, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_selectByPage', '分页查询权限列表', 'permission:selectByPage', 1, '/permission/selectByPage', 1, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_selectByType', '通过类型查询权限列表', 'permission:selectByType', 1, '/permission/selectByType', 0, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_PermissionController_updateById', '通过id修改权限信息', 'permission:updateById', 1, '/permission/updateById', 1, 'easyui-admin_PermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleController', '角色管理', 'role:*', 0, '/role', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_RoleController_deleteById', '通过id删除角色', 'role:deleteById', 1, '/role/deleteById', 1, 'easyui-admin_RoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleController_insert', '添加角色', 'role:insert', 1, '/role/insert', 1, 'easyui-admin_RoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleController_selectAll', '查询全部角色', 'role:selectAll', 1, '/role/selectAll', 0, 'easyui-admin_RoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleController_selectById', '通过id查询角色信息', 'role:selectById', 1, '/role/selectById', 0, 'easyui-admin_RoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleController_selectByPage', '分页查询角色列表', 'role:selectByPage', 1, '/role/selectByPage', 1, 'easyui-admin_RoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleController_updateById', '通过id修改角色信息', 'role:updateById', 1, '/role/updateById', 1, 'easyui-admin_RoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleMenuController', '角色菜单管理', 'role_menu:*', 0, '/role_menu', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_RoleMenuController_deleteById', '通过id删除角色菜单', 'role_menu:deleteById', 1, '/role_menu/deleteById', 1, 'easyui-admin_RoleMenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleMenuController_insert', '添加角色菜单', 'role_menu:insert', 1, '/role_menu/insert', 1, 'easyui-admin_RoleMenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleMenuController_selectByPage', '分页查询角色菜单列表', 'role_menu:selectByPage', 1, '/role_menu/selectByPage', 1, 'easyui-admin_RoleMenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RoleMenuController_updateById', '通过id修改角色菜单信息', 'role_menu:updateById', 1, '/role_menu/updateById', 1, 'easyui-admin_RoleMenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController', '角色权限管理', 'role_permission:*', 0, '/role_permission', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController_deleteById', '通过id删除角色权限', 'role_permission:deleteById', 1, '/role_permission/deleteById', 1, 'easyui-admin_RolePermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController_distribute', '为角色分配权限', 'role_permission:distribute', 1, '/role_permission/distribute', 1, 'easyui-admin_RolePermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController_init', '初始化角色权限', 'role_permission:init', 1, '/role_permission/init', 1, 'easyui-admin_RolePermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController_insert', '添加角色权限', 'role_permission:insert', 1, '/role_permission/insert', 1, 'easyui-admin_RolePermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController_listTree', '查询角色的权限树', 'role_permission:listTree', 1, '/role_permission/listTree', 0, 'easyui-admin_RolePermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController_selectByPage', '分页查询角色权限列表', 'role_permission:selectByPage', 1, '/role_permission/selectByPage', 1, 'easyui-admin_RolePermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_RolePermissionController_updateById', '通过id修改角色权限信息', 'role_permission:updateById', 1, '/role_permission/updateById', 1, 'easyui-admin_RolePermissionController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_SongController', '歌曲管理', 'song:*', 0, '/song', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_SongController_deleteById', '通过ID删除歌曲', 'song:deleteById:{id}', 1, '/song/deleteById/{id}', 1, 'easyui-admin_SongController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_SongController_export', '导出歌曲列表到excel表格', 'song:export', 1, '/song/export', 0, 'easyui-admin_SongController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_SongController_importData', '从excel导入歌曲', 'song:import', 1, '/song/import', 1, 'easyui-admin_SongController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_SongController_insert', '添加歌曲', 'song:insert', 1, '/song/insert', 1, 'easyui-admin_SongController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_SongController_selectByPage', '分页查询歌曲列表', 'song:selectByPage', 1, '/song/selectByPage', 1, 'easyui-admin_SongController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_SongController_updateById', '通过ID修改歌曲信息', 'song:updateById', 1, '/song/updateById', 1, 'easyui-admin_SongController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController', '用户管理', 'user:*', 0, '/user', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_deleteById', '通过ID删除用户', 'user:deleteById', 1, '/user/deleteById', 1, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_getLogin', '获取登录的用户信息', 'user:getLogin', 1, '/user/getLogin', 0, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_insert', '添加用户', 'user:insert', 1, '/user/insert', 1, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_login', '登录认证', 'user:login', 1, '/user/login', 1, 'easyui-admin_UserController', 1);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_logout', '安全退出', 'user:logout', 1, '/user/logout', 1, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_selectAll', '查询全部用户', 'user:selectAll', 1, '/user/selectAll', 0, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_selectById', '通过id查询用户信息', 'user:selectById', 1, '/user/selectById', 0, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_selectByPage', '分页查询用户列表', 'user:selectByPage', 1, '/user/selectByPage', 1, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_updateById', '通过ID修改用户信息', 'user:updateById', 1, '/user/updateById', 1, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserController_updatePassword', '修改用户的密码', 'user:updatePassword', 1, '/user/updatePassword', 1, 'easyui-admin_UserController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserMenuController', '用户菜单管理', 'user_menu:*', 0, '/user_menu', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_UserMenuController_control', '控制菜单显示', 'user_menu:control', 1, '/user_menu/control', 1, 'easyui-admin_UserMenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserMenuController_init', '初始化用户菜单', 'user_menu:init', 1, '/user_menu/init', 0, 'easyui-admin_UserMenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserMenuController_update', '修改用户菜单信息', 'user_menu:update', 1, '/user_menu/update', 1, 'easyui-admin_UserMenuController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserRoleController', '用户角色管理', 'user_role:*', 0, '/user_role', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_UserRoleController_deleteById', '通过id删除用户角色', 'user_role:deleteById', 1, '/user_role/deleteById', 1, 'easyui-admin_UserRoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserRoleController_insert', '添加用户角色', 'user_role:insert', 1, '/user_role/insert', 1, 'easyui-admin_UserRoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserRoleController_selectByPage', '分页查询用户角色列表', 'user_role:selectByPage', 1, '/user_role/selectByPage', 1, 'easyui-admin_UserRoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_UserRoleController_updateById', '通过id修改用户角色信息', 'user_role:updateById', 1, '/user_role/updateById', 1, 'easyui-admin_UserRoleController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_WordController', '性别管理', 'word:*', 0, '/word', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_WordController_deleteById', '通过ID查询性别', 'word:deleteById', 1, '/word/deleteById', 1, 'easyui-admin_WordController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_WordController_insert', '查询全部性别', 'word:insert', 1, '/word/insert', 1, 'easyui-admin_WordController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_WordController_selectByPage', '查询全部性别', 'word:selectByPage', 1, '/word/selectByPage', 1, 'easyui-admin_WordController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_WordController_updateById', '查询全部性别', 'word:updateById', 1, '/word/updateById', 1, 'easyui-admin_WordController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_WordTypeController', '词语类型管理', 'word_type:*', 0, '/word_type', 0, NULL, NULL);
INSERT INTO `permission` VALUES ('easyui-admin_WordTypeController_selectAll', '查询全部词语类型', 'word_type:selectAll', 1, '/word_type/selectAll', 0, 'easyui-admin_WordTypeController', 0);
INSERT INTO `permission` VALUES ('easyui-admin_WordTypeController_selectById', '通过ID查询词语类型', 'word_type:selectById', 1, '/word_type/selectById', 0, 'easyui-admin_WordTypeController', 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '自定义排序序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '最高权限，拥有系统所有权限', 0);
INSERT INTO `role` VALUES (2, '系统管理员', '拥有系统设置相关权限', 100);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id，数据来源于role表的主键',
  `menu_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id，数据来源于menu表的主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, '20210609155649');
INSERT INTO `role_menu` VALUES (2, 1, '20231122055834');
INSERT INTO `role_menu` VALUES (3, 1, '20231122213701');
INSERT INTO `role_menu` VALUES (4, 1, '20240118024438');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id',
  `permission_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 374 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (302, 1, 'easyui-admin_GenderController_selectAll');
INSERT INTO `role_permission` VALUES (303, 1, 'easyui-admin_GenderController_selectById');
INSERT INTO `role_permission` VALUES (304, 1, 'easyui-admin_MenuController_deleteById');
INSERT INTO `role_permission` VALUES (305, 1, 'easyui-admin_MenuController_insert');
INSERT INTO `role_permission` VALUES (306, 1, 'easyui-admin_MenuController_listMenus');
INSERT INTO `role_permission` VALUES (307, 1, 'easyui-admin_MenuController_listTree');
INSERT INTO `role_permission` VALUES (308, 1, 'easyui-admin_MenuController_listTreeGrid');
INSERT INTO `role_permission` VALUES (309, 1, 'easyui-admin_MenuController_selectAll');
INSERT INTO `role_permission` VALUES (310, 1, 'easyui-admin_MenuController_selectById');
INSERT INTO `role_permission` VALUES (311, 1, 'easyui-admin_MenuController_selectByPage');
INSERT INTO `role_permission` VALUES (312, 1, 'easyui-admin_MenuController_selectDirectory');
INSERT INTO `role_permission` VALUES (313, 1, 'easyui-admin_MenuController_updateById');
INSERT INTO `role_permission` VALUES (314, 1, 'easyui-admin_PermissionController_deleteById');
INSERT INTO `role_permission` VALUES (315, 1, 'easyui-admin_PermissionController_insert');
INSERT INTO `role_permission` VALUES (316, 1, 'easyui-admin_PermissionController_resources');
INSERT INTO `role_permission` VALUES (317, 1, 'easyui-admin_PermissionController_selectAll');
INSERT INTO `role_permission` VALUES (318, 1, 'easyui-admin_PermissionController_selectById');
INSERT INTO `role_permission` VALUES (319, 1, 'easyui-admin_PermissionController_selectByPage');
INSERT INTO `role_permission` VALUES (320, 1, 'easyui-admin_PermissionController_selectByType');
INSERT INTO `role_permission` VALUES (321, 1, 'easyui-admin_PermissionController_updateById');
INSERT INTO `role_permission` VALUES (322, 1, 'easyui-admin_RoleController_deleteById');
INSERT INTO `role_permission` VALUES (323, 1, 'easyui-admin_RoleController_insert');
INSERT INTO `role_permission` VALUES (324, 1, 'easyui-admin_RoleController_selectAll');
INSERT INTO `role_permission` VALUES (325, 1, 'easyui-admin_RoleController_selectById');
INSERT INTO `role_permission` VALUES (326, 1, 'easyui-admin_RoleController_selectByPage');
INSERT INTO `role_permission` VALUES (327, 1, 'easyui-admin_RoleController_updateById');
INSERT INTO `role_permission` VALUES (328, 1, 'easyui-admin_RoleMenuController_deleteById');
INSERT INTO `role_permission` VALUES (329, 1, 'easyui-admin_RoleMenuController_insert');
INSERT INTO `role_permission` VALUES (330, 1, 'easyui-admin_RoleMenuController_selectByPage');
INSERT INTO `role_permission` VALUES (331, 1, 'easyui-admin_RoleMenuController_updateById');
INSERT INTO `role_permission` VALUES (332, 1, 'easyui-admin_RolePermissionController_deleteById');
INSERT INTO `role_permission` VALUES (339, 1, 'easyui-admin_SongController_deleteById');
INSERT INTO `role_permission` VALUES (340, 1, 'easyui-admin_SongController_export');
INSERT INTO `role_permission` VALUES (341, 1, 'easyui-admin_SongController_importData');
INSERT INTO `role_permission` VALUES (342, 1, 'easyui-admin_SongController_insert');
INSERT INTO `role_permission` VALUES (343, 1, 'easyui-admin_SongController_selectByPage');
INSERT INTO `role_permission` VALUES (344, 1, 'easyui-admin_SongController_updateById');
INSERT INTO `role_permission` VALUES (345, 1, 'easyui-admin_UserController_deleteById');
INSERT INTO `role_permission` VALUES (346, 1, 'easyui-admin_UserController_getLogin');
INSERT INTO `role_permission` VALUES (347, 1, 'easyui-admin_UserController_insert');
INSERT INTO `role_permission` VALUES (348, 1, 'easyui-admin_UserController_login');
INSERT INTO `role_permission` VALUES (349, 1, 'easyui-admin_UserController_logout');
INSERT INTO `role_permission` VALUES (350, 1, 'easyui-admin_UserController_selectAll');
INSERT INTO `role_permission` VALUES (351, 1, 'easyui-admin_UserController_selectById');
INSERT INTO `role_permission` VALUES (352, 1, 'easyui-admin_UserController_selectByPage');
INSERT INTO `role_permission` VALUES (353, 1, 'easyui-admin_UserController_updateById');
INSERT INTO `role_permission` VALUES (354, 1, 'easyui-admin_UserController_updatePassword');
INSERT INTO `role_permission` VALUES (355, 1, 'easyui-admin_UserMenuController_control');
INSERT INTO `role_permission` VALUES (356, 1, 'easyui-admin_UserMenuController_init');
INSERT INTO `role_permission` VALUES (357, 1, 'easyui-admin_UserMenuController_update');
INSERT INTO `role_permission` VALUES (358, 1, 'easyui-admin_UserRoleController_deleteById');
INSERT INTO `role_permission` VALUES (359, 1, 'easyui-admin_UserRoleController_insert');
INSERT INTO `role_permission` VALUES (360, 1, 'easyui-admin_UserRoleController_selectByPage');
INSERT INTO `role_permission` VALUES (361, 1, 'easyui-admin_UserRoleController_updateById');
INSERT INTO `role_permission` VALUES (362, 1, 'easyui-admin_WordController_deleteById');
INSERT INTO `role_permission` VALUES (363, 1, 'easyui-admin_WordController_insert');
INSERT INTO `role_permission` VALUES (364, 1, 'easyui-admin_WordController_selectByPage');
INSERT INTO `role_permission` VALUES (365, 1, 'easyui-admin_WordController_updateById');
INSERT INTO `role_permission` VALUES (366, 1, 'easyui-admin_WordTypeController_selectAll');
INSERT INTO `role_permission` VALUES (367, 1, 'easyui-admin_WordTypeController_selectById');
INSERT INTO `role_permission` VALUES (368, 1, 'easyui-admin_RolePermissionController_distribute');
INSERT INTO `role_permission` VALUES (369, 1, 'easyui-admin_RolePermissionController_init');
INSERT INTO `role_permission` VALUES (370, 1, 'easyui-admin_RolePermissionController_insert');
INSERT INTO `role_permission` VALUES (371, 1, 'easyui-admin_RolePermissionController_listTree');
INSERT INTO `role_permission` VALUES (372, 1, 'easyui-admin_RolePermissionController_selectByPage');
INSERT INTO `role_permission` VALUES (373, 1, 'easyui-admin_RolePermissionController_updateById');

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌曲编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌曲名',
  `singer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌手',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲文件URL',
  `uploaded` tinyint(4) NOT NULL DEFAULT 0 COMMENT '歌曲文件是否存在',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES ('20210522153649', '贩卖日落', '蓝心羽', NULL, 0, '', '2024-05-14 12:09:58');
INSERT INTO `song` VALUES ('20210522153812', '宠坏', '李俊佑、潘柚彤', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522153941', '爱的魔法', '金莎', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522154139', '多肉少女', '赵芷彤Cassie', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522154331', '爱一点', '王力宏、章子怡', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522154653', '换世奇恋', '排骨教主', NULL, 0, '《画江湖之换世门生 原声带》', NULL);
INSERT INTO `song` VALUES ('20210522154751', '镜心之歌', '邵夷贝', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522154945', '诺言', '陈洁丽', NULL, 0, '《百变机兽之洛洛历险记》动画ED', NULL);
INSERT INTO `song` VALUES ('20210522155118', '无别', '张信哲', NULL, 0, '《天官赐福》动画OP', NULL);
INSERT INTO `song` VALUES ('20210522155349', '快乐星猫', '牛奶咖啡', NULL, 0, '《快乐星猫》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210522160205', '起风了', '周深', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522161509', '星辰觉醒', '屠化冰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522184153', '可不可以撩', '曹懵萌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522184207', '心动的感觉', '醋醋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522184449', '百花香', '魏新雨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522184618', '陨落', '不是花火呀', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522185212', '花开半夏', '爱朵女孩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522190138', '白月光', '张信哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522191452', '小幸运', '田馥甄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522192302', '大天蓬', '李袁杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522192641', '飞鸟和蝉', '任然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210522193453', '爱，存在', '你的好上好佳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210525233923', '喜欢你', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210525233936', '勇气', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210530193908', '一爱难求', '徐佳莹', NULL, 0, '《扶摇》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210530194109', '桃花诺', '邓紫棋', NULL, 0, '《上古情歌》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210530194410', '枕上书', '董贞', NULL, 0, '《三生三世枕上书》网络剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210530194546', '千年', '金志文、吉克隽逸', NULL, 0, '《天乩之白蛇传说》网络剧插曲', NULL);
INSERT INTO `song` VALUES ('20210530194901', '古画', '鞠婧祎', NULL, 0, '《如意芳霏》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210530211557', '魔法城堡', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601181447', '最美的期待', '周笔畅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195425', '晴天', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195830', '稻香', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195838', '七里香', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195852', '告白气球', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195903', '听妈妈的话', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195918', '青花瓷', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195931', '给我一首歌的时间', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601195941', '明明就', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200013', '搁浅', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200019', '珊瑚海', '周杰伦、Lara梁心颐', NULL, 0, '《十一月的萧郎》', NULL);
INSERT INTO `song` VALUES ('20210601200042', '兰亭序', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200111', '说好的幸福呢', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200116', '发如雪', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200212', '烟花易冷', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200231', '简单爱', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200243', '美人鱼', '林俊杰', NULL, 0, '《第二天堂》', NULL);
INSERT INTO `song` VALUES ('20210601200304', '东风破', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200312', '安静', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200328', '爱在西元前', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200342', '龙卷风', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200353', '甜甜的', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200407', '千里之外', '周杰伦、费玉清', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200429', '菊花台', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200455', '蜗牛', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200823', '可惜没如果', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200842', '修炼爱情', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601200912', 'Always Online', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201433', '等一分钟', '徐誉滕', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201449', '醉赤壁', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201613', '背对背拥抱', '林俊杰', NULL, 0, '《爱情睡醒了》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210601201651', '她说', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201714', '不潮不用花钱', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201726', '一千年以后', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201742', '爱不会绝迹', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201752', '曹操', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201819', '江南', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201902', '小酒窝', '蔡卓妍、林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601201920', '爱要怎么说出口', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202232', '匆匆那年', '王菲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202251', '泡沫', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202445', '爱丫爱丫', 'By2', NULL, 0, '《爱情是从告白开始的》电视剧原声带', NULL);
INSERT INTO `song` VALUES ('20210601202557', '玫瑰花的葬礼', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202625', '断桥残雪', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202637', '庐州月', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202653', '清明雨上', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202659', '素颜', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202712', '城府', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202728', '幻听', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202740', '你若成风', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202751', '千百度', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202808', '天龙八部之宿敌', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202846', '燕归巢', '张靓颖、张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202900', '全球变冷', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601202912', '山水之间', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601203051', '破茧', '张韶涵', NULL, 0, '《斗罗大陆》动画第107集ED', NULL);
INSERT INTO `song` VALUES ('20210601203136', '不舍', '徐佳莹', NULL, 0, '《斗罗大陆》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601203307', '此时此刻与你', '大酥', NULL, 0, '《妖怪名单 第二季》主题曲', NULL);
INSERT INTO `song` VALUES ('20210601203343', '光与信仰', '血纯茗雅', NULL, 0, '《妖怪名单 第一季》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601203422', '破茧而出的光芒', '血纯茗雅', NULL, 0, '《妖怪名单 第一季》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601203443', '爱在身边', 'MOMOKO', NULL, 0, '《妖怪名单》', NULL);
INSERT INTO `song` VALUES ('20210601203530', '红', '任然', NULL, 0, '《妖怪名单之苏九儿》电影主题曲', NULL);
INSERT INTO `song` VALUES ('20210601203815', '思无邪', '阿敏', NULL, 0, '《妖怪名单之苏九儿》电影土·宣传曲', NULL);
INSERT INTO `song` VALUES ('20210601204033', '有你的未来', 'MOMOKO', NULL, 0, '《妖怪名单》', NULL);
INSERT INTO `song` VALUES ('20210601204118', '沉香', '王贰浪', NULL, 0, '《妖怪名单之苏九儿》电影宣传曲', NULL);
INSERT INTO `song` VALUES ('20210601204140', '星落', '周思涵', NULL, 0, '《妖怪名单之苏九儿》电影宣传曲', NULL);
INSERT INTO `song` VALUES ('20210601204217', '酒醉三巡', '尹熙水', NULL, 0, '《妖怪名单之苏九儿》电影木·宣传曲', NULL);
INSERT INTO `song` VALUES ('20210601204321', '悲别', '王贰浪', NULL, 0, '《妖怪名单之苏九儿》电影概念宣传曲', NULL);
INSERT INTO `song` VALUES ('20210601204418', '她的故事', '毛若琼', NULL, 0, '《妖怪名单之苏九儿》电影合欢人物主题曲', NULL);
INSERT INTO `song` VALUES ('20210601204503', '轮回 ·他', '邓鼓', NULL, 0, '《妖怪名单之苏九儿》电影封无夜人物主题曲', NULL);
INSERT INTO `song` VALUES ('20210601204532', '毒药', 'Mars毒药', NULL, 0, '《妖怪名单之苏九儿》电影妖王人物主题曲', NULL);
INSERT INTO `song` VALUES ('20210601204817', '等', '毛若琼', NULL, 0, '《妖怪名单之苏九儿》电影苏九儿人物主题曲', NULL);
INSERT INTO `song` VALUES ('20210601204949', '长恨歌', '王瑞淇', NULL, 0, '《妖怪名单之苏九儿》电影召唤曲', NULL);
INSERT INTO `song` VALUES ('20210601205025', '河童', '王金金', NULL, 0, '《妖怪名单之苏九儿》电影金·宣传曲', NULL);
INSERT INTO `song` VALUES ('20210601205214', '幼安', '阿敏', NULL, 0, '《妖怪名单之苏九儿》电影概念先行曲', NULL);
INSERT INTO `song` VALUES ('20210601205256', '远行', '张迁', NULL, 0, '《妖怪名单 第二季》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601205521', '不败的英雄', '唐俊迪', NULL, 0, '《铠甲勇士刑天》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210601205925', '光的战士', '陈致逸', NULL, 0, '《铠甲勇士》电视剧第52集插曲', NULL);
INSERT INTO `song` VALUES ('20210601205953', '穿越曙光', '唐丹', NULL, 0, '《铠甲勇士刑天》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210601210159', '生生世世爱', '吴雨霏', NULL, 0, '《仙剑奇侠传3》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210601210221', '忘记时间', '胡歌', NULL, 0, '《仙剑奇侠传3》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210601210243', '偏爱', '张芸京', NULL, 0, '《仙剑奇侠传3》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210601210312', '六月的雨', '胡歌', NULL, 0, '《仙剑奇侠传》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210601210351', '一直很安静', '阿桑', NULL, 0, '《仙剑奇侠传》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210601210448', '终于明白', '动力火车', NULL, 0, '《仙剑奇侠传》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210601210603', '仙剑问情', '萧人凤', NULL, 0, '《仙剑奇侠传3外传·问情篇》游戏主题曲', NULL);
INSERT INTO `song` VALUES ('20210601210626', '花与剑', 'JS', NULL, 0, '《仙剑奇侠传》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210601210727', '千年缘', '心然', NULL, 0, '《仙剑奇侠传4》游戏非官方同人曲', NULL);
INSERT INTO `song` VALUES ('20210601210758', '千年泪', '董贞', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601210846', '情醉', '董贞、盛威', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210601211014', '繁花', '董真', NULL, 0, '《三生三世十里桃花》电视剧原声带', NULL);
INSERT INTO `song` VALUES ('20210601211100', '流恋', '吴奇隆、严艺丹', NULL, 0, '《新白发魔女传》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210601211306', '梦回还', '呦猫UNEKO', NULL, 0, '《狐妖小红娘 王权篇》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601211409', '若当来世', '冥月、Mario', NULL, 0, '《狐妖小红娘 月红篇》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210601211619', '人间白首', '呦猫UNEKO', NULL, 0, '《狐妖小红娘 竹业篇》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601211706', '落空', '落萱', NULL, 0, '《狐妖小红娘 金晨曦篇》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601211802', '此彼绘卷', '林和夜', NULL, 0, '《狐妖小红娘王权篇》动画第20、27集插曲', NULL);
INSERT INTO `song` VALUES ('20210601211835', '铭记', '呦猫UNEKO', NULL, 0, '《狐妖小红娘 千颜篇》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601211942', '东流', '绯村柯北、灰老板', NULL, 0, '《狐妖小红娘 下沙篇》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601212221', '金色', 'Kinoko蘑菇', NULL, 0, '《狐妖小红娘 沐天城篇》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601212308', '满庭芳', 'Mr.mo', NULL, 0, '《狐妖小红娘 竹业篇》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601212338', '愿我', '呦猫UNEKO', NULL, 0, '《狐妖小红娘 南国篇》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601212429', '我还记得', 'Shymie', NULL, 0, '《狐妖小红娘 尾生篇》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601212522', '铃舟', '匀子', NULL, 0, '《狐妖小红娘 月红篇》动画第16集插曲', NULL);
INSERT INTO `song` VALUES ('20210601212551', '寄', 'JMJ', NULL, 0, '《狐妖小红娘 竹业篇》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601212655', '相聚万年树', '林和夜', NULL, 0, '《狐妖小红娘 下沙篇》动画第6集插曲', NULL);
INSERT INTO `song` VALUES ('20210601212808', '相思树下', 'YNJ谢悦', NULL, 0, '《狐妖小红娘》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210601213110', '万水依山', '叫ぶ獣', NULL, 0, '《狐妖小红娘 月红篇》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210601213214', '下沙', '桂子油', NULL, 0, '《狐妖小红娘 下沙篇》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601213316', '不易不移', 'Kinoko蘑菇', NULL, 0, '《狐妖小红娘 金晨曦篇》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601213446', '雪年轮', '苏尚卿', NULL, 0, '《狐妖小红娘 千颜篇》动画第62集插曲', NULL);
INSERT INTO `song` VALUES ('20210601213520', '君路', '大酥', NULL, 0, '《狐妖小红娘 北山妖帝篇》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601213602', '岩心', '大帝', NULL, 0, '《狐妖小红娘 月红篇》动画第46集插曲', NULL);
INSERT INTO `song` VALUES ('20210601213716', '未断', '陈爽朗、王志毅', NULL, 0, '《狐妖小红娘 沐天城篇》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601213829', '时之风', '方晓东', NULL, 0, '《狐妖小红娘 尾生篇》动画概念主题曲', NULL);
INSERT INTO `song` VALUES ('20210601213923', '不忘', '张恋歌', NULL, 0, '《狐妖小红娘 北山妖帝篇》动画第45集插曲', NULL);
INSERT INTO `song` VALUES ('20210601214021', '围城', 'Kinoko蘑菇、Mr.mo', NULL, 0, '《狐妖小红娘 南国篇》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601214105', '盘根', '白止', NULL, 0, '《狐妖小红娘 千颜篇》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601214150', '竹亭', '南偿', NULL, 0, '《狐妖小红娘 竹业篇》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601214317', '瞳染', '蓮莉', NULL, 0, '《狐妖小红娘 砂雪篇》动画OP', NULL);
INSERT INTO `song` VALUES ('20210601214424', '绝处风雪', '无问、D.rui、池年', NULL, 0, '《狐妖小红娘 月红篇》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601214549', '刻印', '朱梓溶', NULL, 0, '《狐妖小红娘 千颜篇》动画ED', NULL);
INSERT INTO `song` VALUES ('20210601214630', '雁归辞', '依平、无欢', NULL, 0, '《狐妖小红娘》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601214724', '不醉', '余七趁', NULL, 0, '《狐妖小红娘》动画插曲', NULL);
INSERT INTO `song` VALUES ('20210601214949', '思如雪', '董贞', NULL, 0, '《画江湖之不良人3》动画第21集插曲', NULL);
INSERT INTO `song` VALUES ('20210601215118', '爱你让我像孩子一样', '俞灏明', NULL, 0, '《因为爱情有奇迹》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210601215206', '忐忑的距离', '萌学园', NULL, 0, '《萌学园4 时空战役》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210601215336', '月光', '胡彦斌', NULL, 0, '《秦时明月·百步飞剑》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210601215451', '枫林残忆', '伍华、余文靖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210602181846', '远方', '郁可唯', NULL, 0, '《古剑奇谭》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210602181910', '剑心', '张杰', NULL, 0, '《古剑奇谭》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210602181950', '剑伤', '李易峰', NULL, 0, '《古剑奇谭》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210602182019', '恋人歌歌', '胡彦斌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210602182119', '爱你没错', '张信哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210602182633', '双骄', '金志文', NULL, 0, '《绝代双骄 2020版》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210602182658', '拆心', '刘惜君', NULL, 0, '《绝代双骄 2020版》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210602182822', '红尘不悔', '陆虎', NULL, 0, '《绝代双骄 2020版》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210602183035', '翅膀', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210602183228', '独白', '周深', NULL, 0, '《天乩之白蛇传说》网络剧插曲', NULL);
INSERT INTO `song` VALUES ('20210602183255', '幸福在梦中', '赵艺', NULL, 0, '《大话西游之爱你一万年》网络剧紫霞仙子角色曲', NULL);
INSERT INTO `song` VALUES ('20210602183412', '凉凉', '杨宗纬、张碧晨', NULL, 0, '《三生三世十里桃花》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210602183449', '爱河', '神马乐园', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210602183752', '为爱追寻', '朱倩汐JING', NULL, 0, '《梦幻西游2》游戏主题曲', NULL);
INSERT INTO `song` VALUES ('20210602183831', '恋西游', 'TFBOYS', NULL, 0, '《梦幻西游2：化境飞升》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210602183910', '入梦', 'SNH48', NULL, 0, '《梦幻西游3：雷怒危机》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210606174210', '留香', '吴奇隆', NULL, 0, '《新白发魔女传》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210606174229', '无常', '吴奇隆', NULL, 0, '《新白发魔女传》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210606174317', '无·果', '严艺丹', NULL, 0, '《新白发魔女传》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210606175139', '十年', '陈奕迅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210606175219', '十一年', '邱永传', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210606175333', '勿忘我', '刘庭羽', NULL, 0, '《天天有喜2之人间有爱》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210606175435', '为了爱', '刘庭羽、陆昱霖', NULL, 0, '《天天有喜》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210606175551', '望爱', '穆婷婷', NULL, 0, '《天天有喜》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210606175629', '梦一场', '刘一祯', NULL, 0, '《天天有喜2之人间有爱》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210606175907', '城门', '陈浩民、韩元元', NULL, 0, '《活佛济公第3部》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210606175931', '胭脂泪', '刘依纯', NULL, 0, '《活佛济公第2部》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210606175957', '忘了算了', '范怡文', NULL, 0, '《活佛济公第3部》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210606180019', '最后的爱', '刘依纯', NULL, 0, '《活佛济公第2部》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210606180205', '你的笑脸', '唐妞', NULL, 0, '《蓝猫龙骑团》动画ED', NULL);
INSERT INTO `song` VALUES ('20210606180333', '不怕', '赵蕾、赵蓓', NULL, 0, '《神兵小将》动画片尾曲', NULL);
INSERT INTO `song` VALUES ('20210606180905', '梦的光点', '王心凌', NULL, 0, '《神兵小将1》动画OP', NULL);
INSERT INTO `song` VALUES ('20210607015143', '灯火', '龙飞龙泽', NULL, 0, '《毛驴县令》电影主题曲', NULL);
INSERT INTO `song` VALUES ('20210607015413', '爱出发', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607015443', '青春修炼手册', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607015507', '宠爱', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607015539', '大梦想家', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607015640', '信仰之名', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607015701', '想唱就唱', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607015717', '少年说', 'TFBOYS', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607015905', '筝语', '卓舒晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607020110', '明天过后', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607020212', '不可说', '霍建华、赵丽颖', NULL, 0, '《花千骨》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210607020408', '年轮', '张碧晨', NULL, 0, '《花千骨》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210607020727', '关山酒', '等什么君', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607020941', '爱在心中', '孙晔', NULL, 0, '《东方神娃第2部》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210607021033', '可惜不是你', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607021049', '日不落', '蔡依林', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607022951', '缘来', '潘辰、王铮亮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607023038', '缘来', '张炜', NULL, 0, '《神医大道公前传》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210607023124', '仙女湖', '徐千雅', NULL, 0, '《仙女湖》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210607023205', '高飞', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607141602', '后来遇见他', '胡66', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607141656', '世界那么大还是遇见你', '程响', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607142539', '后来', '刘若英', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607142657', '有点甜', '汪苏泷、BY2', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607142746', '平凡之路', '朴树', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607142910', '谪仙', '伊格赛听、叶里', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607143019', '你的答案', '阿冗', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607143402', '娃娃脸', '后弦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607143451', '小可爱', '向梦园、徐木子', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607143557', '大鱼', '周深', NULL, 0, '《大鱼海棠》电影印象曲', NULL);
INSERT INTO `song` VALUES ('20210607143713', '星辰大海', '黄霄芸', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607143751', '踏山河', '是七叔呢', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607143821', '云与海', '阿YueYue', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607143842', '解开', '曹洋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607214920', '初恋未满', '张含韵、曹轩宾', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210607215029', '老人与海', '海鸣威、吴琼', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210608162857', '爱', '小虎队', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210608162917', '恋人未满', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210608165411', '我透明', '唐嫣', NULL, 0, '《爱情睡醒了》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210608170005', '我的歌声里', '庄心妍', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210608170053', '奇迹再现', '毛毛', NULL, 0, '《迪迦奥特曼》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210608170331', '天下', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210608170546', '爱如潮水', '张信哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210608171323', '你从未离去', '白挺', NULL, 0, '《熊出没2雪岭熊风》电影主题曲', NULL);
INSERT INTO `song` VALUES ('20210608171416', '彩虹', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210608171449', '爱的供养', '杨幂', NULL, 0, '《宫锁心玉》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210608172039', '永远永远', '李翊君', NULL, 0, '《风云雄霸天下》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210611114058', '雨花石', '李玉刚、石头', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611114123', '爱是你我', '云朵、刀郎', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611114256', '你我', '陈晓、陈妍希', NULL, 0, '《新神雕侠侣》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210611222556', '归寻', '等什么君', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611222628', '寂寞咖啡', '王欣婷、蔡晓', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611222645', '戏影', '彭十六', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611222705', '永不失联的爱', '王靖雯不胖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611222720', '夏天的风', '蓝心羽', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611222750', '诺言', '李翊君', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611223309', '年少有为', '李荣浩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210611235357', '三国恋', 'Tank', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210613150907', '落日与鲸', '鬼鬼Gmer', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210613152400', '下雨天', '南拳妈妈', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210613153023', '四季予你', '程响', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210613153108', '南山雪', '叶里', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210613153132', '夏恋', 'Ototaze', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210613155709', '倾城一笑', '艾辰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210613155744', '离人殇', '赵方婧、音阙诗听', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210615000847', '绿色', '陈雪凝', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210615003504', '世间美好', '夏艺韩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210615003810', '没有什么不同', '曲婉婷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210615003901', '心愿', '四个女生', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210615004032', '神话', '韩红', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210615004059', '星月神话', '金莎', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210615011730', '原点', '西单女孩', NULL, 0, '《画江湖之不良人2》动画ED', NULL);
INSERT INTO `song` VALUES ('20210617234337', '一眼万年', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234407', '布格拉广场', '蔡依林、周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234526', '今天你要嫁给我', '蔡依林、陶喆', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234601', '五月天', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234629', '波斯猫', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234654', '不想长大', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234720', '中国话', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234737', 'Super Star', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210617234855', '爱的哲学', '牟凡', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210621230521', '来迟', '戴羽彤', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210621230712', '对不起，我爱你', '赵宥乔、陈知远', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210621230929', '失控', '井迪儿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210622200206', '落空', '印子月', NULL, 0, '《旋风少女第2季》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210622200222', '单车', '陈奕迅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210624200656', 'you are my sunshine', 'Angelika Vee', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210624200752', '三生三世', '张杰', NULL, 0, '《三生三世十里桃花》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210624200948', 'you are beautiful', 'James Blunt', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210624201117', 'Baby', 'Justin Bieber、Lundacris', NULL, 0, '《QQ飞车》手游插曲', NULL);
INSERT INTO `song` VALUES ('20210624201248', 'In The End', 'LINKIN PAPK', NULL, 0, '《QQ飞车》手游插曲', NULL);
INSERT INTO `song` VALUES ('20210624201442', '无限速', '本息、阿悄', NULL, 0, '《QQ飞车》游戏“SCC超级联赛”主题曲', NULL);
INSERT INTO `song` VALUES ('20210624201757', 'Right Now(Na Na Na)', 'Akon', NULL, 0, '《QQ飞车》手游插曲', NULL);
INSERT INTO `song` VALUES ('20210624202348', '骄傲的选择', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210624202440', '全心出发', '小橘子、镜', NULL, 0, '《QQ飞车》手游三周年主题曲', NULL);
INSERT INTO `song` VALUES ('20210624205346', 'Ta', '胖虎', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210624205411', '开始懂了', '孙燕姿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210707184539', '爱在一起创作的原声', '爱在一起', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210707184652', '半生雪', '七叔（叶泽浩）', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210710222743', '借过', '印子月', NULL, 0, '《旋风少女》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210710223059', '那个远方', '陈楚生', NULL, 0, '《旋风少女》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210710224218', '燃烧吧青春', '何洁', NULL, 0, '《旋风少女》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210710224424', '星星', '牛奶咖啡', NULL, 0, '《旋风少女》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210710225113', '如果可以', '胡夏', NULL, 0, '《旋风少女》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210710225313', '痛快', '金玟岐', NULL, 0, '《旋风少女》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210713230210', '不是因为寂寞才想你', 'T.R.Y', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210713231209', '净土', '孙楠', NULL, 0, '《木府风云》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210715134726', '不再联系', '程响', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715134808', '你还要我怎样', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715140403', '新娘不是我', '程响', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715140440', '入戏太深', '马旭东', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715140509', '后会无期', '徐良、汪苏泷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715151946', '别想她', '高进', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715152049', '虹之间', '金贵晟', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715152115', '愿得一人心', '李行亮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715152157', '爱的双重魔力', 'By2', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210715152620', '当你孤单你会想起谁', '张栋梁', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716183438', '你在看孤独的风景', '本兮、单小源', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716183554', '为你写诗', '吴克群', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716183658', '一个人', '夏婉安', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716183717', '突然好想你', '五月天', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716183805', '不爱又何必纠缠', '阿夏、威仔', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716183846', '童话', '光良', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716184134', '认真的雪', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716195343', '有何不可', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210716213338', '预谋', '许佳慧', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210718233746', '全世界宣布爱你', '孙子涵、李潇潇', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210722152300', '潮汐（Natural）', '安苏羽、傅梦彤', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210722153248', '圆', 'AGA', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210818181834', '白月花红', '李宏毅', NULL, 0, '《狐妖小红娘·月红2》动画片尾曲', NULL);
INSERT INTO `song` VALUES ('20210818182851', '转轮', '匀子', NULL, 0, '《狐妖小红娘·月红2》动画主题曲', NULL);
INSERT INTO `song` VALUES ('20210818182955', '坠落星空', '小星星Aurora', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210818183314', '白鸽', '你的上好佳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210818183407', '微微', '傅如乔', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210818183422', '无人之岛', '任然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210818183439', '星空剪影', '蓝心羽', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210820224354', '夜空中最亮的星', '逃跑计划', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210822172529', '和光同尘', '周深', NULL, 0, '《大江大河2》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20210822172628', '寂寞沙洲冷', '周传雄', NULL, 0, '《星空下的传说》', NULL);
INSERT INTO `song` VALUES ('20210822172657', '倔强', '五月天', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210822172719', '不安', '钟纯妍', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210822172737', '我和你', '皮卡丘多多', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210822172753', '岁月神偷', '金玟岐', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210826024509', '让我为你唱一首歌', '张翰、朱梓骁、魏晨、俞灏明', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210826024855', '错位时空', '夏文娜', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210829032726', '尘埃', '林小珂', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210829032854', '隔岸观火', 'Li-2c', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210829033225', '知否知否', '胡夏、郁可唯', NULL, 0, '《知否知否应是绿肥红瘦》电视剧原声带', NULL);
INSERT INTO `song` VALUES ('20210829033425', '追光者', '岑宁儿', NULL, 0, '《夏至未至》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210829034743', '冲动', '印子月', NULL, 0, '《旋风少女第2季》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20210829034950', '爱之光', '本兮', NULL, 0, '《旋风少女2季》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210829035656', '忘了牵手', '牛奶咖啡', NULL, 0, '《旋风少女》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210829040433', '树叶的光', '徐菲', NULL, 0, '《旋风少女2季》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20210907225543', '囚鸟', '彭羚', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210911041342', 'My Way(AlbumVersion)', '张敬轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210912041848', '光年之外', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20210912074632', '画心', '张靓颖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211023201003', '迷人的危险', '蔡黄汝', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211023201154', '曲中人', 'HITA', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211023201416', '牵丝戏', '银临、Aki阿杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211023201530', '你的承诺', '海鸣威', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211023201704', '邂逅', '云菲菲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211023202253', '少年', '梦然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211023203215', 'Believe', 'Frederic Delarue', NULL, 0, '纯音乐', NULL);
INSERT INTO `song` VALUES ('20211023203317', '眼泪Tears', 'Daydream', NULL, 0, '钢琴曲', NULL);
INSERT INTO `song` VALUES ('20211030131100', '千千万万', '深海鱼子酱', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211030131147', '醒不来的梦', '回小仙', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211031193934', '甜甜咸咸', '赵芷彤Cassie', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211031200158', '春庭雪', '等什么君', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108222914', '孤城', '洛先生', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223021', '我们的爱', 'F.I.R.飞儿乐园', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223036', '心不由己', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223053', '注定', '周笔畅、白举纲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223102', '星辰', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223223', '缺氧', '安苏羽', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223242', '佛系少女', '冯提莫', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223259', '恋人心', '魏新雨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108223340', '去年夏天', '王大毛', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108224957', '追', '香香', NULL, 0, '《追鱼传奇》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20211108232017', '有一种爱叫做放手', '阿木', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211108232635', '世界上的另一个我', '宋亚轩、刘耀文', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110213501', '爱囚', '庄心妍', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110213830', '黄昏', '周传雄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110214338', '一笑江湖', '闻人听書_', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110214403', '动物世界', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110214434', '不如', '秦海清', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110214552', '凤御九天', '凌之轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110214643', '风中的承诺', '李翊君', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211110214711', '别问我是谁', '王馨平', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211112190645', '一生所爱', '吉克隽逸', NULL, 0, '《大话西游之爱你一万年》网络剧主题曲', NULL);
INSERT INTO `song` VALUES ('20211114213128', '烟火', '陈翔', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114213310', '花海', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114213407', '像小时候一样', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114213612', '流星雨', 'F4', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114213919', '只是朋友', '无情学长', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114214228', '天外来物', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114214456', '李白', '李荣浩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114214859', '金玉良缘', '李琦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114215414', '归去来兮', '叶炫清', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114215457', '不浪漫罪名', '王杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114215759', '让我欢喜让我忧', '周华健', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211114232637', '落日与晚风', 'IN-K、王忻辰、苏星婕', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211115230947', '仰望星空', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116195221', '明天，你好', '牛奶咖啡', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116203620', '默', '那英', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116205230', '勿忘心安', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116205453', '外面的世界', '莫文蔚', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116205715', '红豆', '王菲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116210110', '执迷不悟', '孟西', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116210147', '造梦人', '金玟岐', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116211314', '丑八怪', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116211335', '演员', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211116212211', '逆流成河', '金南玲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211119154959', '阳光总在风雨后', '许美静', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211119160519', 'You are my sunshine', 'Angelika Vee', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211119160636', '水手', '郑智化', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211119160907', '天使的翅膀', '徐誉滕', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211121192028', '万有引力', '汪苏泷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211121194705', '真英雄', '张卫健', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211201194631', '北极星的眼泪', '陈栋梁', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211201194657', '秋天不回来', '王强', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211201194845', '犯贱', '徐良', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211201195944', '我的梦', '张靓颖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211201200016', '不分手的恋爱', '汪苏泷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203234737', '时间煮雨', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203234822', '虞兮叹', '深蓝儿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235024', '坠', '添儿呗', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235155', '太迟', '王茗', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235216', '燕无歇', '蒋雪儿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235247', '爱的代价', '张艾嘉', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235454', '诛仙', '凤九、和尔盟', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235521', '小棉袄', '单夕', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235540', '再见', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235600', '勇气', '棉子', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235918', '因为爱情', '陈奕迅、王菲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211203235946', '雨花泪', '董贞', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000046', '情罪', '董贞、盛威', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000131', '逆战', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000151', '我很快乐', '刘惜君', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000217', '坏孩子', '许嵩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000433', '爱很美', 'Sara、刘佳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000531', '无心', '排骨教主', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000606', '死心塌地', '夹子道', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211204000812', '没有什么比失去更难过', '千竦嫣', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211207225217', '以后的以后', '庄心妍', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211207225243', '梦醒时分', '陈淑桦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211207225316', '雨天', '呆呆破', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211221214941', '斗罗大陆·海神的眼泪', '苏忘川', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20211221215043', '风舞', '纯音乐', NULL, 0, '《斗罗大陆》', NULL);
INSERT INTO `song` VALUES ('20211221215929', '几生欢', '杨紫', NULL, 0, '《天乩之白蛇传说》网络剧插曲', NULL);
INSERT INTO `song` VALUES ('20211221220141', '流年', '何洁', NULL, 0, '《天乩之白蛇传说》网络剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20211221220242', '凡心', '董贞', NULL, 0, '《天乩之白蛇传说》网络剧插曲', NULL);
INSERT INTO `song` VALUES ('20211221220308', '弱水', '任贤齐', NULL, 0, '《天乩之白蛇传说》网络剧主题曲', NULL);
INSERT INTO `song` VALUES ('20211222233412', '从头再来', '刘晓', NULL, 0, '《绝唱1》', NULL);
INSERT INTO `song` VALUES ('20211225212029', '因为爱所以爱', '谢霆锋', NULL, 0, '《了解》', NULL);
INSERT INTO `song` VALUES ('20211225213035', '梦里水乡', '江珊', NULL, 0, '《歌声伴随您》', NULL);
INSERT INTO `song` VALUES ('20211226195930', '一路生花', '温奕心', NULL, 0, '《一路生花》', NULL);
INSERT INTO `song` VALUES ('20211229215051', '我们的明天', '鹿晗', NULL, 0, '《重返20岁》电影原声带', NULL);
INSERT INTO `song` VALUES ('20211229215548', '莫问归期', '蒋雪儿', NULL, 0, '《莫问归期》', NULL);
INSERT INTO `song` VALUES ('20211229221707', '刚刚好', '薛之谦', NULL, 0, '《初学者》', NULL);
INSERT INTO `song` VALUES ('20211230133504', '此生不换', '青鸟飞鱼', NULL, 0, '《仙剑奇侠传3》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20220101212137', '爱你没错', '张信哲', NULL, 0, '《古剑奇谭》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20220102205933', '这就是爱', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220108235522', '遇见', '张燕姿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220109214430', '煮酒', '希涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220109214831', '如一', '不是花火呀、小田音乐社', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220109215714', '幻想', '赖美云、黄霄雲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220109224849', '爱就一个字', '张信哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220109225242', '清空', '王忻辰、苏星婕', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220113222151', '过火', '张信哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220403232924', '浪子闲话', '花僮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220410191653', '最初的梦想', '范玮琪', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220410192358', '毕业季', '贺劲轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220410194554', '心痛2009', '欢子', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220416191642', '说散就散', 'JC 陈咏桐', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220426233155', '忽然之间', '莫文蔚', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220426235926', '隐形的翅膀', '张韶涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000057', '一个人挺好', '杨小壮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000128', '雨爱', '杨丞琳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000206', '不说再见', '好妹妹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000235', '最美的瞬间', '真瑞', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000305', '最美的太阳', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000413', '不甘', '小乐哥（王唯乐）', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000450', '剑魂', '鱼多余', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000608', '满目星辰皆是你', 'LKer林柯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000653', '或许', '程今', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000717', '不配念旧', '何文宇', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000812', '最后的人', '杨小壮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000859', '有没有一首歌会让你想起我', '周华健', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427000921', '五十年以后', '海来阿木', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427001051', '以爱为囚', '莫叫弟弟', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427001219', '那些年', '胡夏', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427001249', '你有没有想过', '徐浩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427001312', '梦里的星', '陈楚生', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427002631', '白月光与朱砂痣', '大籽', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427002715', '伤心城市', '云菲菲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427002817', '红尘酒一壶', '希涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427003056', '弱点', '贺敬轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427003621', '谢谢你，曾用心骗过我', '任夏', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220427162116', '记事本', '陈慧琳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220430145519', '坏女孩', '徐良、小凌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220502124728', '蒲公英的约定', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220502124753', '我的未来不是梦', '胡彦斌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220502124819', '第一次爱的人', '王心凌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220502184157', '七秒钟的记忆', '徐良、孙羽幽', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220502185548', '我们说好的', '张靓颖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220503191146', '宁夏', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220503193156', '嘉宾', '张远', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504031838', '你的微笑', 'FIR飞儿乐园', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504031930', '说谎', '林宥嘉', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504032010', '和你一样', '李宇春', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504032040', '月牙湾', 'FIR飞儿乐园', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504032411', '独家记忆', '陈小春', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504033050', '陪你度过漫长岁月', '陈奕迅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504033232', '淋雨一起走', '张韶涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504033322', '该死的温柔', '马天宇', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504034246', '会呼吸的痛', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504151530', '光荣', 'BOBO', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504203313', '愿你', '黄静美', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220504203516', '海市蜃楼', '三叔说', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220508154250', '崇拜', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220510235606', '清风徐来', '王菲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220510235658', '欧若拉', '张韶涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220604213448', '我们都是好孩子', '王筝', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220630111246', '从别后', 'AZA微唱团', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220703162350', '大艺术家', '蔡依林', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220703162447', '我们的纪念', '李雅微', NULL, 0, '《放羊的星星》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20220710023600', '心之火', 'FIR飞儿乐园、彭佳慧', NULL, 0, '《花千骨》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20220710023636', '生死相随', '崔子格、杨培安', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710023658', '表白', '萧亚轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710023808', '王妃', '萧敬腾', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710023924', '同一首歌', '刘畅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024000', '孤城烟火', '季彦霖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024125', '寂寞在唱什么歌', 'Riyo橙', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024338', '盗墓笔记·十年人间', '李常超（Lao乾妈）', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024443', '爱到万年', '刘庭羽、蒲巴甲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024556', '千年之恋', 'F.I.R.飞儿乐园', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024625', '体面', '于文文', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024818', '无虞', '李紫婷、井胧', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024856', '没有如果', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710024931', 'I Miss You', '罗百吉、宝贝', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710025012', '你瞒我瞒', '陈柏宇', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710025048', '迷途的孤岛', '胡彦斌、张碧晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710025218', '如愿', '王菲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710025238', '做你的猫', '尚文婷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710025311', '思慕', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710025401', '不染', '毛不易', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710025903', '爱情公寓', '娄艺潇、王传君、李金铭、陈赫、孙艺洲、邓家佳、金世佳', NULL, 0, '《爱情公寓3》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20220710025954', '靠近', '罗震环', NULL, 0, '《爱情公寓3》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20220710030103', '像风一样', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030141', '多幸运', '韩安旭', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030159', '你就不要想起我', '田馥甄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030229', '终于等到你', '张靓颖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030529', '你笑起真好看', '李昕融、樊桐舟、李凯稠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030618', '我们都一样', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030741', '再见', '张震岳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030813', '成全', '刘若英', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030844', '怒放的生命', '汪峰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710030938', '睫毛弯弯', '王心凌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031015', '寂寞在唱歌', '阿桑', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031037', '雨蝶', '李翊君', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031113', '再回首', '黑鸭子组合', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031128', '手放开', '李圣杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031201', '侧脸', '于果', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031231', '青春不打烊', '郭静', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031250', '背叛', '曹格', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031312', '信仰', '张信哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031340', '断点', '张敬轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031406', '风起时', '胡歌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031502', '秋殇别恋', '牙牙乐、格子兮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031538', '苦笑', '汪苏泷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031628', '和平分手', '徐良、Britneylee小暖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031702', '触碰纯白', '单色凌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031752', '你还欠我一个拥抱', '后弦、Sara', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031821', '红色高跟鞋', '蔡健雅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710031924', 'Lydia', 'F.I.R.飞儿乐园', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032224', '情难独钟', '吴珊珊', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032249', '秋天你别离开我', '尤小米', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032308', '当你老了', '莫文蔚', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032332', '味道', '辛晓琪', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032406', '知足', '五月天', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032441', '分手假期', '钟洁、李佳思', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032459', '独角戏', '许茹芸', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032546', '丁香花', '唐磊', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032609', '回心转意', '黑龙', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032628', '星语心愿', '张柏芝', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032807', '冰吻', '王健', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032836', '裂缝中的阳光', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710032943', '被风吹过的夏天', '金莎、林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710033030', '阿依莫', '阿吉太组合', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710033053', '说爱你', '蔡依林', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710033152', '口是心非', '张雨生', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710033218', '给我一个理由忘记', 'A-Lin', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710033315', '下个，路口，见', '李宇春', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220710033440', 'My Heart Will Go On', 'Céline Dion、James Horner', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823191312', '过客', '周思涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823191503', '彩虹天堂', '刘畊宏', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823191537', '我们', '陈奕迅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823191611', '来不及勇敢', '周深', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823191730', '有多少爱可以重来', '黄仲昆', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823191943', '越长大越孤单', '牛奶咖啡', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192012', '左边', '杨丞琳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192048', '哥只是一个传说', '陈旭', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192114', '飞舞', '王冰洋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192142', '红装', '徐良、阿悄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192220', '空港', '戴爱玲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192239', '童年', '张艾嘉', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192259', '枫', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192324', '时光正好', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192350', '无人认领', '小阿七', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192423', '你曾是少年', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192649', '剩下的盛夏', 'TFBOYS、嘻游记', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823192803', '连名带姓', '张惠妹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823193120', '身骑白马', '刘佳莹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823193313', '越来越不懂', '蔡健雅', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823193429', '梦里花', '张韶涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823193607', '不将就', '李荣浩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823194131', '白色风车', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823194455', '半糖主义', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823194705', '微微一笑很倾城', '杨洋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823194804', '暖暖', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823195501', '那个男孩', '汪苏泷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823195549', '情话微甜', '李朝、王圣锋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823195629', '一次就好', '杨宗纬', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823195810', '绿光', '孙燕姿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220823195947', 'Love Love Love', '蔡依林', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220825144711', '我好想你', '潘广益', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220825144831', '麻雀', '李荣浩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220825144915', '叹云兮', '鞠婧祎', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220825145102', '陪我去流浪', '阿悄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220825145134', '爱上你', 'BY2', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220825145202', '天下无双', '张靓颖', NULL, 0, '《神雕侠侣》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20220826200207', '美丽的神话', '孙楠、韩红', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220826200224', '是你', '梦然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220826200258', '不变的音乐', '王绎龙', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220826200329', '咱们结婚吧', '齐晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220826200355', '纸短情长', '烟把儿乐队', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220826200421', '超级喜欢你', '金南玲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220826200456', '八年的爱', '冰冰超人', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220826200529', '勇敢爱', 'Mi2', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220828025656', '我的楼兰', '云朵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830011955', '不知所措', '王靖雯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012050', '空空如也', '任然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012124', '如果这就是爱情', '张靓颖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012215', '我怀念的', '孙燕姿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012259', '有没有人告诉你', '陈楚生', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012348', '外滩十八号', '袁成杰、戚薇', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012453', '猜不透', '丁当', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012510', '在他乡', '水木年华', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220830012537', '不得不爱', '潘玮柏、弦子', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220905223042', '约定', '周蕙', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220905223514', '心雪', '邓颖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220905223619', '只若初见', '张德伊玲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220905223724', '突然想起你', '萧亚轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220906221938', '这个夏天', '威仔、夏婉安', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220909210214', '那年错过的爱情', '白小白', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220909212827', '逆光', '孙燕姿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220910021557', '洛先生', '孤城', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220910021718', '七日七日晴', '许慧欣', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220912200953', '春娇与志明', '街道办GDC、欧阳耀莹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220913222727', '学猫叫', '潘友彤、陈锋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220913222746', '最后我们没在一起', '白小白', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220927234645', '往后余生', '马良、孙茜茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220928000750', '缘分一道桥', '王力宏、谭维维', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220928000839', '你的酒馆对我打了烊', '陈雪凝', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220928000933', '骄傲的少年', '南征北战NZBZ', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220928000957', '勋章', '鹿晗', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220928001022', '后来的你在哪', '范茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20220928212041', '滚烫的青春', '王源', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221011213356', '笑柄', '陈小满', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221011214012', '玫瑰少年', '周深、GAI周延', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221011224135', '春三月', '司南', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221011225722', '善变', '王靖雯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221101001112', '凉城', '任然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221101001157', '多想在平庸的生活拥抱你', '隔壁老樊', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221101220602', '漫长的告白', '陈元汐', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221102232804', '冬眠', '司南', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221102232834', '9420', '麦小兜', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221102232947', '落在生命里的光', '尹昔眠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221102233927', '满天星辰不及你', 'yccc', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221102234602', '天下有情人', '唐艺', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221102235955', '像鱼', '王贰浪', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221105212921', '相见恨晚', '半吨兄弟', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221105212944', '光的方向', '张碧晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221105213004', '老男孩', '筷子兄弟', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221105213200', '追梦赤子心', 'GALA乐队', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221105213722', '光明', '谭艳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221105213942', '雪落下的声音', '陆虎', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221202231801', '寻人启事', '徐佳莹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221202232233', '少年泪', '王梓钰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221203003654', '雪下的时候', '乔佳旭', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221203003804', '无药可愈', '胡小斐', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221203003941', '落在生命的光', '尹昔眠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221203004012', '消愁', '毛不易', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221203151255', '青花', '周传雄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221203154318', '关不上的窗', '周传雄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221203184826', '当你', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221204202711', '樱花草', 'Sweety', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221204204552', '再见只是陌生人', '庄心妍', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20221204210042', '做我老婆好不好', '徐腾誉', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230101014728', '大海', '张雨生', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230101015009', '只对你有感觉', '飞轮海、田馥甄', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230101015049', '烟火', '光良', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230101015135', '恋爱面板', '锦零', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230101015221', '守护者我的光', '李巍V仔', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230101015308', '你的眼睛像星星', '郭正正、园C', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230104004128', '期待你的爱', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230104004237', '倒数', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230105183335', '九张机', '叶炫清', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230105183404', '可不可以', '张紫豪', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230108112042', '替我照顾她', '胡夏', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230108112101', '三寸天堂', '严艺丹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230108112154', '时光背对着我', '鱼闪闪BLING', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230108112219', '广东爱情故事', '广东雨神', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230108112428', '多喜欢你', '小贱', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230108112937', '情深深雨濛濛', '杨胖雨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230124220413', '热带雨林', 'S.H.E', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230124220525', '曾经你说', '赵乃吉', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230124220611', '迷失在梦中', '韩可可', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230124220644', '生僻字', '陈柯宇', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230124220722', '黎明前的黑暗', '孟颖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230124220753', '你若三冬', '阿悠悠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230202222834', '裹着心的光', '林俊杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208085355', '镜中渊', '周林枫', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208085434', '等你归来', '程响', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208085544', '千年之恋', '双笙（陈元汐）', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208085617', '人间惊鸿客', '叶里', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208085719', '骁', '井胧、井迪儿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208085807', '天问', '摩登兄弟刘宇宁', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208085851', '手心', '林俊杰、邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208090000', '伯虎说', '伯爵Johnny、唐伯虎Annie', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208090305', '落差', '王忻辰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208090421', '不仅仅是喜欢', '孙语赛、萧全', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230208090451', '沙漠骆驼', '展展与罗罗', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230213065854', '下山', '麦小兜', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230213065929', '再也不会遇见第二个她', '李哈哈', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230213070002', '尽头', '赵方婧', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230301033754', '找到你是我最伟大的成功', '古巨基', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230301042321', '说好了不见面', '小贱', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230301042412', '星月为媒', '王茗', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310171712', '从前', '董贞、王敬轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310180219', 'Far Away From Home', 'Groove Coverage', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310180354', 'God Is a Girl', 'Groove Coverage', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310180434', 'Sunshine Girl', 'mounmoon', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310180532', 'Beautiful Now', 'Zedd、Jon Bellion', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310180555', 'She', 'Groove Coverage', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310180647', 'Apologize', 'Timbaland、OneRepublic', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310181036', 'Cry On My Shoulder', 'Daniel Küblböck', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310181130', 'Poker Face', 'Lady Gaga', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310181252', 'Good Time', 'Owl City、carly Rae Jepsen', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310181343', 'Trouble Is Friend', 'Lenka', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310181435', 'Dream It Possible', 'Delacey', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230310181503', '从前', '安苏羽', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230312030905', '后来我们的爱', '陆杰awr', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322085203', '重返地球', '黄霄雲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322085911', '终会与你同行', '白挺', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322085945', '心中的另一个自己', '翟亚楠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322090122', '我和你', '皮卡丘多多', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322091050', '并肩', '丁钰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322091139', '勇往直前', '洪辰HUNG', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322091216', '这样爱了', '张婧', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230322091237', '恋爱画板', '锦零', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230324080521', '彼岸花', '周深', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230324080549', '镜中人', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230324080623', '不弃不离', '丁于', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230403082335', '突然之间', '莫文蔚', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230403082409', '对方正在输入', '电流妹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230403082548', '小雨天气', '嘿人李逵Noisemakers、徐梦圆', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230403082638', '转呀转呀', '锦零', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230403082726', '热爱105℃的你', '阿肆', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230403082918', '暧昧', '杨丞琳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412175625', '听说你', '于冬然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412180914', '心跳回应', '辛雯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412181016', '把回忆拼好给你', 'cici_', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412181101', '江湖之间', '曹雨航', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412181147', '听众', '王志心', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412181238', '在你的身边', '盛哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412181305', '像雨吹起了风', 'Liko、庄淇文29', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412182750', '星与岛', '南葵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412183905', '星河陨落', '霍含蕾Rea', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412212834', '嫁', 'L(桃籽)、周林枫、三楠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230412212948', '红黑', '小星星Aurora', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230425121252', '相拥星空', '张洛', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230425121400', '一个深爱的女孩', '本兮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230425121416', '酸酸甜甜就是我', '张含韵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230425122636', '对着天空说爱你', '莫小滢', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230425122656', '躲避的爱', '小小', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230425122721', '雪', '杜婧荧、王艺翔', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230425123113', '爱啦啦', '海楠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230430024622', '奇妙能力歌', '陈粒', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230430025256', '彩虹的微笑', '王心凌', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230515100601', '水中花(Live)', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230515101615', '慢冷', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230521091306', '快乐小神仙', '天唱组合', NULL, 0, '《快乐星球1》电视剧主题曲', NULL);
INSERT INTO `song` VALUES ('20230521091423', '月亮船', '王英姿', NULL, 0, '《快乐星球3》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20230521093442', '小小少年', '天唱组合', NULL, 0, '《快乐星球1》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20230521093645', '梦幻童年', '天唱组合', NULL, 0, '《快乐星球1》电视剧片尾曲', NULL);
INSERT INTO `song` VALUES ('20230521100316', '路过人间', '郁可唯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230707221954', '奔赴星空', '尹昔眠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230707225305', '银河和星斗', 'yihuik苡慧', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230709031543', '入阵曲', '五月天', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230715033446', '眼泪的错觉', '王露凝、乔海清', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802122319', '地老天荒', '张丹峰', NULL, 0, '《花千骨》电视剧原声带', NULL);
INSERT INTO `song` VALUES ('20230802122425', '往事随风', '齐秦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802122729', '难诀别', '弦子、ycccc', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802122845', '蔓延', '许美静', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802122918', '第一次', '光良', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802123058', '我超喜欢你', '欧阳朵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802123128', '恋爱的猫', '月小妞', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802123256', '骄傲的你', '尹昔眠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230802123341', '勉为其难', '王冕', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230822191716', '倒带', '蔡依林', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230822192055', '错的人', '萧亚轩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230822193447', '阴天', '莫文蔚', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230822215824', '赤伶', '执素兮', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230822220348', '山楂树之恋', '大能人', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230902030903', '直到世界尽头', '张杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230906185438', '开往早晨的午夜', '张碧晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230906185524', '曾经守候', '张碧晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230906185614', '给未来的自己', '梁静茹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20230925223008', '失衡', '梁琪清', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231006181259', '哭砂', '张惠妹', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231006181317', '小小', '容祖儿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231013232337', '花开忘忧', '周深', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231017163512', '贪杯琥珀', '阿YueYue', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231017163541', '晚风作酒', 'L (桃籽) 、周林枫、三楠', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231103090132', '卿清叙', '圈9', NULL, 0, '《猫妖的诱惑》动画OP', NULL);
INSERT INTO `song` VALUES ('20231110004454', '再见的，不见的', '郁可唯', NULL, 0, '《我的女友是机器人》电影主题曲', NULL);
INSERT INTO `song` VALUES ('20231203192229', '明天', '朱心怡', NULL, 0, '《斗破苍穹》决战云岚片尾曲', NULL);
INSERT INTO `song` VALUES ('20231224212947', '想你时风起', '单依纯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224213833', '只要平凡', '张杰、张碧晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224215310', '像我这样的人', '毛不易', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224215455', '化身孤岛的鲸', '周深', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224220919', '看得最远的地方', '毛不易', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224221829', '有你的夏日来信', 'Liko、庄淇文29', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224223222', '多远都要在一起', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224223423', '越是', '向思思', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20231224223856', '爱的回归线', '陈韵若、陈每文', NULL, 0, '', '2024-04-15 23:16:28');
INSERT INTO `song` VALUES ('20240107202513', '哪里都是你', '队长', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240107202627', '说好不哭', '周杰伦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240107202730', '晚风遇见你', '陆杰', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240107202933', '爱的不是我', '尹姝贻', NULL, 0, '《程序员那么可爱》电视剧插曲', NULL);
INSERT INTO `song` VALUES ('20240121203611', '当', '动力火车', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121204351', '左半边翅膀', '许飞', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121204352', '我会好好的', '印子月', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121204353', '房间', '刘瑞琦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121204354', '意外', '薛之谦', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121204355', '唯爱', '詹雯婷', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121204356', '逐光', '韩富Richchard', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121204357', '雀跃', '任然', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121210928', '透明', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211012', '沈园外', '阿YueYue、戾格', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211126', '时光背面的我', '刘至佳、韩瞳', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211214', '无名之辈', '陈雪燃', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211318', '心墙', '郭静', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211358', '挥着翅膀的女孩', '容祖儿', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211420', '句号', '邓紫棋', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211440', '帝都', '萌萌哒天团', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240121211657', '夏天', '李玖哲', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240207203433', 'New Boy', '房东的猫', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240302160645', '宿', '廖静媛', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240302160802', '回忆那么伤', '孙子涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240302160845', '最美情侣', '白小白', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240302160928', '我会等', '承恒', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240302161005', '最熟悉的陌生人', '小倩', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240310014041', '篇章', '张韶涵', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240310014055', '选择失忆', '季彦霖', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240310014147', '不要忘记我爱你', '张碧晨', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240310014212', '如果爱忘了', '汪苏泷、单依纯', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240329190556', '我期待的不是雪', '张妙格', NULL, 0, NULL, NULL);
INSERT INTO `song` VALUES ('20240415224609', '会开花的云', '周深深', NULL, 0, '', '2024-04-15 22:57:52');
INSERT INTO `song` VALUES ('20240415225754', '会开花的云', '王樾安', NULL, 0, '', '2024-04-15 22:58:56');
INSERT INTO `song` VALUES ('20240415230247', '你曾满眼温柔', '张洛一', NULL, 0, '', '2024-04-15 23:03:08');
INSERT INTO `song` VALUES ('20240415230314', '缺月', '袁艺非', NULL, 0, '', '2024-04-15 23:03:51');
INSERT INTO `song` VALUES ('20240419195136', '虐心', '徐良、孙羽幽', NULL, 0, '', '2024-04-19 19:51:59');
INSERT INTO `song` VALUES ('20240419195208', '醉清风（2005版）', '弦子', NULL, 0, '', '2024-04-19 19:52:36');
INSERT INTO `song` VALUES ('20240419195309', '走着走着就散了', '庄心妍', NULL, 0, '', '2024-04-19 19:53:48');
INSERT INTO `song` VALUES ('20240507141318', '笼', '张碧晨', NULL, 0, '', '2024-05-07 14:13:50');
INSERT INTO `song` VALUES ('20240507141408', '给电影人的情书', '单依纯', NULL, 0, '', '2024-05-07 14:14:25');
INSERT INTO `song` VALUES ('20240507141428', '生活总该迎着光亮', '周深', NULL, 0, '', '2024-05-07 14:14:48');
INSERT INTO `song` VALUES ('20240514155502', '故事关于你', '陆三三', NULL, 0, '', '2024-05-14 15:55:18');
INSERT INTO `song` VALUES ('20240514155540', '小美满', '周深', NULL, 0, '', '2024-05-14 15:55:46');
INSERT INTO `song` VALUES ('20240514155815', '希望你被这个世界爱着', '戴羽彤', NULL, 0, '', '2024-05-14 15:58:31');
INSERT INTO `song` VALUES ('20240514155903', '故事还长', '云汐', NULL, 0, '', '2024-05-14 16:00:57');
INSERT INTO `song` VALUES ('20240723094255', 'Ring Ring Ring', '不是火花呀', NULL, 0, '', '2024-07-23 09:43:20');
INSERT INTO `song` VALUES ('20240723094321', '恋爱告急', '旺仔小乔', NULL, 0, '', '2024-07-23 09:43:47');
INSERT INTO `song` VALUES ('20240723094407', '好像恋爱了', '纳豆nado', NULL, 0, '', '2024-07-23 09:44:29');
INSERT INTO `song` VALUES ('20240723094436', '近我者甜', '猫扑风铃', NULL, 0, '', '2024-07-23 09:44:57');
INSERT INTO `song` VALUES ('20240723094503', '99%', 'Jin、大柯', NULL, 0, '', '2024-07-23 09:45:25');
INSERT INTO `song` VALUES ('20240723094530', '动心', 'Joysaaaa', NULL, 0, '', '2024-07-23 09:45:47');
INSERT INTO `song` VALUES ('20240804135304', '花', '鞠婧祎', NULL, 0, '', '2024-08-04 13:53:25');
INSERT INTO `song` VALUES ('20240804135328', '热恋冰淇淋', 'yihuik苡慧', NULL, 0, '', '2024-08-04 13:54:45');
INSERT INTO `song` VALUES ('20240804135505', '我曾遇到一束光', '叶斯纯', NULL, 0, '', '2024-08-04 13:55:35');
INSERT INTO `song` VALUES ('20240804135547', '双世缘', '柏凝', NULL, 0, '', '2024-08-04 13:56:03');
INSERT INTO `song` VALUES ('20240902122144', '悬崖之花', '毛潇曼', NULL, 0, '《是王者啊》动画片尾曲', '2024-09-02 12:22:57');
INSERT INTO `song` VALUES ('20240902122258', '趁年少', '卡修Rui', NULL, 0, '《斗破苍穹3》动画主题曲', '2024-09-02 12:24:27');
INSERT INTO `song` VALUES ('20241031172053', '璀璨冒险人', '周深', NULL, 0, '', '2024-10-31 17:21:30');
INSERT INTO `song` VALUES ('20241031172135', '风起云涌', '熊思嘉', NULL, 0, '', '2024-10-31 17:22:10');
INSERT INTO `song` VALUES ('20241031172211', '一瞬的永恒', '袁小藏、程清文', NULL, 0, '', '2024-10-31 17:23:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `enable` tinyint(4) UNSIGNED NOT NULL COMMENT '是否启用（0-未启用；1-启用中）',
  `gender` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '性别，数据来源于性别代码表（gender）的主键',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上一次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('mhxy1218', '沐雨橙风ιε', '16666666666', 'mumu', '82c9b8426ed242df828fe4435f17de17b35b7877722c1103bef6eef35575ed0a', 1, 2, '2024-05-13 20:59:47');

-- ----------------------------
-- Table structure for user_menu
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pxh` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `display` tinyint(4) UNSIGNED NOT NULL COMMENT '是否显示（0-隐藏；1-显示）',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID，数据来源于user表的主键',
  `menu_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID，数据来源于menu表的主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-菜单表：用于控制用户菜单显示' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
INSERT INTO `user_menu` VALUES (1, 3000, 1, 'mhxy1218', '20211113014324');
INSERT INTO `user_menu` VALUES (2, 3000, 1, 'mhxy1218', '20211113135935');
INSERT INTO `user_menu` VALUES (4, 2000, 1, 'mhxy1218', '20231122060854');
INSERT INTO `user_menu` VALUES (5, 2000, 1, 'mhxy1218', '20231122195740');
INSERT INTO `user_menu` VALUES (6, 1000, 1, 'mhxy1218', '20231122213744');
INSERT INTO `user_menu` VALUES (7, 1000, 1, 'mhxy1218', '20231122213900');
INSERT INTO `user_menu` VALUES (8, 4000, 1, 'mhxy1218', '20240118024530');
INSERT INTO `user_menu` VALUES (9, 4000, 1, 'mhxy1218', '20240318211953');
INSERT INTO `user_menu` VALUES (10, NULL, 1, 'mhxy1218', '20240514143436');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id，数据来源于role表的主键',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id，数据来源于user表的主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 'mhxy1218');

-- ----------------------------
-- Table structure for word
-- ----------------------------
DROP TABLE IF EXISTS `word`;
CREATE TABLE `word`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '词语',
  `type` int(10) UNSIGNED NOT NULL COMMENT '词语类型，数据来源于word_type表的主键',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '注释',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of word
-- ----------------------------
INSERT INTO `word` VALUES (1, '邯郸学步', 1, '比喻模仿人不到家，反而把原来自己会的东西忘了。');
INSERT INTO `word` VALUES (2, '纸上谈兵', 1, '比喻空谈理论，不能解决实际问题。');
INSERT INTO `word` VALUES (3, '如火如荼', 1, '形容旺盛、热烈或激烈。');
INSERT INTO `word` VALUES (4, '屡见不鲜', 1, '指多次见到，并不稀奇，强调不新奇。');
INSERT INTO `word` VALUES (5, '层出不穷', 1, '指一次又一次地出现，没有穷尽，强调不断出现。');
INSERT INTO `word` VALUES (6, '经久不衰', 1, '指持续很长的时间而不衰减，强调持久。');
INSERT INTO `word` VALUES (7, '顺势而为', 1, '做事要顺应潮流，不要逆势而行。');
INSERT INTO `word` VALUES (8, '乘势而上', 1, '利用有利的形势而加紧完成某事。');
INSERT INTO `word` VALUES (9, '急流勇进', 1, '在险境中积极进取，不退缩。');
INSERT INTO `word` VALUES (10, '逆流而上', 1, '迎着困难而上。');
INSERT INTO `word` VALUES (11, '运筹帷幄', 1, '在帐幕中谋划军机，拟定作战计划。');
INSERT INTO `word` VALUES (12, '历久弥新', 1, '经历长久的时间而更加鲜活，更加有活力。');
INSERT INTO `word` VALUES (13, '披荆斩棘', 1, '扫除前进的困难和阻碍。');
INSERT INTO `word` VALUES (14, '一枝独秀', 1, '在同类事物中最为突出优秀。');
INSERT INTO `word` VALUES (15, '脱颖而出', 1, '本领全部显露出来。');
INSERT INTO `word` VALUES (16, '拨云见日', 1, '比喻冲破黑暗，见到光明，也比喻疑团消除，心里顿时明白。');
INSERT INTO `word` VALUES (17, '浮渣泛起', 1, '比喻已经绝迹了的腐朽、陈旧事物又重新出现。');
INSERT INTO `word` VALUES (18, '支离破碎', 1, '形容事物零散破碎，不成整体。');
INSERT INTO `word` VALUES (19, '量体裁衣', 1, '按照身材裁剪衣服，比喻根据实际情况办事。');
INSERT INTO `word` VALUES (20, '亦步亦趋', 1, '指学生紧紧追随老师；比喻没有主见，处处追随模仿。');
INSERT INTO `word` VALUES (21, '春风化雨', 1, '适宜草木生长的风雨，多用在人或事，比喻良好的熏陶和教育。');
INSERT INTO `word` VALUES (22, '另眼相看', 1, '用另一种眼光看待，指看待某个人（或某种人）不同于一般，也指不被重视的人得到重视。');
INSERT INTO `word` VALUES (23, '林林总总', 1, '形容杂乱众多。形容人或事物众多。');
INSERT INTO `word` VALUES (24, '纷繁芜杂', 1, '指多而杂乱，没有条理，形容文章内容芜杂，没有条理，或者是事情杂乱无章，没有头绪。');
INSERT INTO `word` VALUES (25, '只争朝夕', 1, '力争在最短的时间内达到目的。');
INSERT INTO `word` VALUES (26, '一日千里', 1, '进展极快，多形容事物的发展速度。');
INSERT INTO `word` VALUES (27, '万无一失', 1, '非常有把握，绝对不会出差错。');
INSERT INTO `word` VALUES (28, '跌宕起伏', 1, '音调抑扬顿挫、文章富于变化。');
INSERT INTO `word` VALUES (29, '日薄西山', 1, '比喻人到老年或腐朽的事物衰败接近灭亡。');
INSERT INTO `word` VALUES (30, '分毫不差', 1, '没有一点差错。');
INSERT INTO `word` VALUES (31, '精妙绝伦', 1, '精致美妙，无与伦比。一般用来形容建筑、艺术品等水平的高超。');
INSERT INTO `word` VALUES (32, '娓娓动听', 1, '形容善于讲话，使人爱听。');
INSERT INTO `word` VALUES (33, '鱼目混珠', 1, '用假的冒充真的。');
INSERT INTO `word` VALUES (34, '滥竽充数', 1, '形容以次充好。');
INSERT INTO `word` VALUES (35, '道听途说', 1, '形容没有根据的传闻。');
INSERT INTO `word` VALUES (36, '胡编乱造', 1, '没有根据、不合情理的胡乱编造。');
INSERT INTO `word` VALUES (37, '天花乱坠', 1, '形容说话巧妙动听，但虚妄、不切实际。');
INSERT INTO `word` VALUES (38, '拾级而上', 1, '顺着阶梯一步一步地往上走。');
INSERT INTO `word` VALUES (39, '激浊扬清', 1, '形容清除坏的，发扬好的。');
INSERT INTO `word` VALUES (40, '买椟还珠', 1, '指买了装了珍珠的木匣退还了珍珠；比喻取舍不当，次要的东西比主要的还要好。');
INSERT INTO `word` VALUES (41, '沉渣浮起', 1, '比喻已经绝迹了的腐朽、陈旧事物又重新出现。');
INSERT INTO `word` VALUES (42, '纤尘不染', 1, '比喻丝毫不受坏习惯、坏风气的影响。');
INSERT INTO `word` VALUES (43, '皮里阳秋', 1, '藏在心里说不出来的言论。');
INSERT INTO `word` VALUES (44, '更弦易辙', 1, '比喻改变方法或态度。');
INSERT INTO `word` VALUES (45, '无出左右', 1, '没有能超越它的。');
INSERT INTO `word` VALUES (46, '鸠占鹊巢', 1, '比喻强占别人的房屋、土地、产业等。');
INSERT INTO `word` VALUES (47, '海晏河清', 1, '大海平静了，黄河的水清了；比喻天下太平。');
INSERT INTO `word` VALUES (48, '缘木求鱼', 1, '爬上树去抓鱼；比喻行动的方向、方法不对，必将劳而无功。');
INSERT INTO `word` VALUES (50, '闭门造车', 1, '关起门来造车子；比喻自作主张，不合实际。');
INSERT INTO `word` VALUES (52, '朝令夕改', 1, '早晨发布的命令，晚上就改了。比喻经常改变主张和办法，一会儿一个样。');
INSERT INTO `word` VALUES (54, '浑然天成', 1, '形容诗文书画自然完美，无雕刻的痕迹，也形容人的才德完美自然。');
INSERT INTO `word` VALUES (55, '别出心裁', 1, '表示与众不同的新观念或办法。');
INSERT INTO `word` VALUES (56, '三心二意', 1, '意志不坚定，犹豫不决；也指又想这样又想那样，犹豫不定。');
INSERT INTO `word` VALUES (57, '欲盖弥彰', 1, '掩饰坏事的真相，结果反而更明显地暴露出来。');
INSERT INTO `word` VALUES (58, '等量齐观', 1, '指不管事情的差异，同等看待。');
INSERT INTO `word` VALUES (59, '万人空巷', 1, '形容庆祝、欢迎的盛况或新奇事物轰动居民的情景，表达热闹的景象。');
INSERT INTO `word` VALUES (60, '同日而语', 1, '指放在同一时间谈论，侧重同一人或事物在不同时间的比较。');
INSERT INTO `word` VALUES (61, '惊鸿一瞥', 1, '指人只是匆匆看了一眼，却给人留下极深的印象，指美女或所仰慕的女子动人心魄的目光。');
INSERT INTO `word` VALUES (62, '朝三暮四', 1, '是汉语的一则成语，出自《庄子·齐物论》。这则成语原指一养猴人以果子饲养猴子，施以诈术骗猴的故事，后用以比喻变化多端，捉摸不定，反复无常。');
INSERT INTO `word` VALUES (63, '岌岌可危', 1, '形容非常危险，快要倾覆或灭亡。');
INSERT INTO `word` VALUES (64, '独树一帜', 1, '比喻与众不同，自成一家。');
INSERT INTO `word` VALUES (65, '难以为继', 1, '难以继续下去。');
INSERT INTO `word` VALUES (66, '拾人牙慧', 1, '比喻拾取别人的一言半语当作自己的话。');
INSERT INTO `word` VALUES (67, '刻舟求剑', 1, '比喻死守教条，拘泥成法，固执不变通的人。');
INSERT INTO `word` VALUES (68, '登峰造极', 1, '比喻学问、技艺等已达到最高的境界。');
INSERT INTO `word` VALUES (70, '俯拾皆是', 1, '意思是只要低下头来捡取，到处都是，形容多而易得。');
INSERT INTO `word` VALUES (71, '汗牛充栋', 1, '指书运输时牛累得出汗，存放时可堆至屋顶；用来形容藏书非常多。');
INSERT INTO `word` VALUES (72, '越俎代庖', 1, '主祭的人跨过礼器去代替厨师办席，比喻超出自己业务范围去处理别人所管的事。');
INSERT INTO `word` VALUES (73, '望洋兴叹', 1, '原指在伟大的事物面前感叹自己的微小；后比喻因眼界大开而惊奇赞叹或因能力不及而感到无可奈何。');
INSERT INTO `word` VALUES (74, '退避三舍', 1, '原意指为了回避与对方的冲突，主动退让九十里；常用于比喻退让和回避，避免冲突。');
INSERT INTO `word` VALUES (75, '柳暗花明', 1, '形容柳树成荫，繁花似锦的春天景象。比喻在曲折艰辛之后，忽然绝处逢生，另有一番情景。');
INSERT INTO `word` VALUES (76, '有的放矢', 1, '放箭要对准靶子，比喻说话做事有针对性。');
INSERT INTO `word` VALUES (77, '浮光掠影', 1, '比喻观察不细致，学习不深入，印象不深刻。');
INSERT INTO `word` VALUES (78, '水到渠成', 1, '比喻条件成熟，事情自然会成功。');
INSERT INTO `word` VALUES (79, '未雨绸缪', 1, '天还没下雨，先把门窗绑牢。比喻事先做好准备工作。');
INSERT INTO `word` VALUES (80, '不言而喻', 1, '不用说话就能明白，形容道理很明显。');
INSERT INTO `word` VALUES (81, '相得益彰', 1, '指两个人或两件事相互配合，双方的能力和作用更能显现出来。');
INSERT INTO `word` VALUES (82, '进退维谷', 1, '无论是进还是退，都是处在困境之中。形容处境艰难，进退两难。');
INSERT INTO `word` VALUES (83, '捉襟见肘', 1, '形容衣服破烂、困难或处境窘迫。比喻顾此失彼，穷于应付。');
INSERT INTO `word` VALUES (84, '独善其身', 1, '愿意是坐不上官就修养好自身。现指只顾自己，不管别人。');
INSERT INTO `word` VALUES (85, '削足适履', 1, '比喻不合理地迁就现有条件或不顾具体事实，生搬硬套。');
INSERT INTO `word` VALUES (86, '别具一格', 1, '另有一种独特的风格。');
INSERT INTO `word` VALUES (87, '一劳永逸', 1, '比喻把事情办好，以后就可以不再费力了。');
INSERT INTO `word` VALUES (88, '举足轻重', 1, '指处于重要地位，一举一动就足以影响全局。');
INSERT INTO `word` VALUES (89, '举重若轻', 1, '比喻能力强，能够轻松地胜任繁重的工作或处理困难的问题。');
INSERT INTO `word` VALUES (90, '大相径庭', 1, '比喻彼此相差很远或矛盾很大。');
INSERT INTO `word` VALUES (91, '纷至沓来', 1, '比喻接连不断地到来。');
INSERT INTO `word` VALUES (92, '声名鹊起', 1, '名声突然大振，知名度迅速提高。');
INSERT INTO `word` VALUES (93, '空穴来风', 1, '比喻消息和传说毫无根据。');
INSERT INTO `word` VALUES (94, '高瞻远瞩', 1, '站得高，看得远，比喻目光远大。');
INSERT INTO `word` VALUES (95, '首当其冲', 1, '比喻最先受到攻击、遭遇灾害或受到伤害。');
INSERT INTO `word` VALUES (96, '置若罔闻', 1, '放在一边不管，好像没有听见一样；形容听见了而不加理睬。');
INSERT INTO `word` VALUES (97, '方兴未艾', 1, '形容事物正在蓬勃发展或正在处于兴旺阶段。多形容实物正在蓬勃发展。');
INSERT INTO `word` VALUES (98, '津津乐道', 1, '形容对这件事十分地感兴趣，很感兴趣地谈论。强调的是说个不停。');
INSERT INTO `word` VALUES (99, '积重难返', 1, '经过长时间形成的思想作风或习惯，很难改变。');
INSERT INTO `word` VALUES (100, '杀鸡取卵', 1, '比喻贪图眼前的好处二不考虑长远发展。');
INSERT INTO `word` VALUES (101, '殊途同归', 1, '比喻采取不同的方法而得到相同的结果。');
INSERT INTO `word` VALUES (102, '一马当前', 1, '比喻领先，也比喻工作走在群众前面，积极带头。');
INSERT INTO `word` VALUES (103, '如数家珍', 1, '比喻对所讲的事情十分熟悉。');
INSERT INTO `word` VALUES (104, '心无旁骛', 1, '形容心思集中，专心致志。');
INSERT INTO `word` VALUES (105, '深入浅出', 1, '指讲话或文章的内容深刻，语言文字却浅显易懂。');
INSERT INTO `word` VALUES (106, '信手拈来', 1, '随手拿来，多指写文章时能自由纯熟地运用词语或应用典故，用不着怎么思考。');
INSERT INTO `word` VALUES (107, '耳熟能详', 1, '指听得多了，能够说得很清楚、很详细。');
INSERT INTO `word` VALUES (108, '众说纷纭', 1, '人多嘴杂，议论纷纷。');
INSERT INTO `word` VALUES (109, '苦心孤诣', 1, '指苦心钻研，到了别人所达不到的地步。也指为寻求解决问题的办法而煞费苦心。');
INSERT INTO `word` VALUES (110, '功亏一篑', 1, '比喻做事情只差最后一点没能完成。');
INSERT INTO `word` VALUES (111, '功败垂成', 1, '事情就在将要成功的时候遭受了失败。');
INSERT INTO `word` VALUES (112, '大张旗鼓', 1, '形容进攻的声势和规模很大。也形容群众活动声势和规模很大。');
INSERT INTO `word` VALUES (113, '高屋建瓴', 1, '比喻居高临下，有气势。');
INSERT INTO `word` VALUES (114, '势如破竹', 1, '比喻作战或工作节节胜利，毫无阻碍。');
INSERT INTO `word` VALUES (115, '江河日下', 1, '比喻情况一天天地坏下去。');
INSERT INTO `word` VALUES (116, '独当一面', 1, '单独负责一个方面的工作。');
INSERT INTO `word` VALUES (117, '偃旗息鼓', 1, '比喻事情终止或声势减弱。');
INSERT INTO `word` VALUES (118, '相形见绌', 1, '更另一个人或事物比较之后，显出了不足。');
INSERT INTO `word` VALUES (119, '讳莫如深', 1, '把事情隐瞒得很紧。');
INSERT INTO `word` VALUES (120, '明哲保身', 1, '因怕连累自己而回避原则斗争的处事态度。');
INSERT INTO `word` VALUES (121, '蔚为大观', 1, '形容事物美好繁多，给人一种盛大的印象。');
INSERT INTO `word` VALUES (122, '当务之急', 1, '当前继续办理的事情。');
INSERT INTO `word` VALUES (123, '信口开河', 1, '比喻随口乱说一气。');
INSERT INTO `word` VALUES (124, '鞭辟入里', 1, '形容学问切实，也形容分析透彻，切中要害。');
INSERT INTO `word` VALUES (125, '风生水起', 1, '形容事情做得有生气，蓬勃兴旺。');
INSERT INTO `word` VALUES (126, '不落窠臼', 1, '比喻不落俗套，有独创风格（多指文章、作品）。');
INSERT INTO `word` VALUES (127, '穿凿附会', 1, '把讲不通的或不相干的道理、事情硬扯在一起进行解释。');
INSERT INTO `word` VALUES (128, '偷梁换柱', 1, '比喻暗中玩弄手法，以假乱真，以劣代优。');
INSERT INTO `word` VALUES (129, '一筹莫展', 1, '一点计策也施展不出，一点办法也想不出来。');
INSERT INTO `word` VALUES (130, '充耳不闻', 1, '形容有意不听别人的意见。');
INSERT INTO `word` VALUES (131, '分庭抗礼', 1, '比喻双方以平等或对等的关系相处，有时比喻互相对立或搞分裂、闹独立。');
INSERT INTO `word` VALUES (132, '欣欣向荣', 1, '形容草木长得茂盛，比喻事业蓬勃发展，兴旺昌盛。');
INSERT INTO `word` VALUES (133, '李代桃僵', 1, '李树代替桃树而死。原比喻兄弟互相爱护、互相帮助。后转用来比喻互相顶罪或代人受过。');
INSERT INTO `word` VALUES (134, '大包大揽', 1, '把事情、任务等尽量招揽过来。');
INSERT INTO `word` VALUES (135, '取而代之', 1, '夺取别人的地位而由自己代替。也指以某一事物代替另一事物。');
INSERT INTO `word` VALUES (136, '相提并论', 1, '把不同的人或事放在一起谈论或看待。');
INSERT INTO `word` VALUES (137, '混为一谈', 1, '把不同事物混在一起，当做同样的事物谈论。');
INSERT INTO `word` VALUES (138, '左支右绌', 1, '力量不足，应付了这方面，那方面又出了问题。');
INSERT INTO `word` VALUES (139, '顾此失彼', 1, '顾了这个，丢了那个，两者不能同时兼顾。形容忙乱或慌张的情景。');
INSERT INTO `word` VALUES (140, '进退失据', 1, '前进和后退都失去了依据。形容无处容身，也指进退两难。');
INSERT INTO `word` VALUES (141, '无所适从', 1, '不知道怎么办才好。');
INSERT INTO `word` VALUES (142, '力不从心', 1, '心里想做，可是力量够不上。');
INSERT INTO `word` VALUES (143, '一蹴而就', 1, '比喻事情轻而易举，一下子就成功。');
INSERT INTO `word` VALUES (144, '一步登天', 1, '一步踏上青天。比喻一下子就达到很高的境界或程度。有时也用来比喻突然得志，爬上高位。');
INSERT INTO `word` VALUES (145, '妙手偶得', 1, '技术高超的人，偶然间即可得到。也用来形容文学素养很深的人，出于灵感，即可偶然间得到妙语佳作。');
INSERT INTO `word` VALUES (146, '一挥而成（就）', 1, '一动笔就完成了。形容写字、写文章、画画快。');
INSERT INTO `word` VALUES (147, '唾手可得', 1, '动手就可以取得。比喻极容易得到。');
INSERT INTO `word` VALUES (148, '迫在眉睫', 1, '形容事情已到眼前，形势十分紧迫。');
INSERT INTO `word` VALUES (149, '刻不容缓', 1, '形势十分紧迫，一刻也不允许拖延。');
INSERT INTO `word` VALUES (150, '势在必行', 1, '不能躲开或回避；从事情的趋势看，必须采取行动。');
INSERT INTO `word` VALUES (151, '急如星火', 1, '像流星的光从天空中急闪而过。形容非常急促紧迫。');
INSERT INTO `word` VALUES (152, '急不可待', 1, '形容心情急迫或形势紧迫。');
INSERT INTO `word` VALUES (153, '十万火急', 1, '形容事情紧急到了极点（多用于军令、公文、电报等）。');
INSERT INTO `word` VALUES (154, '别具匠心', 1, '在技巧和艺术方面具有与众不同的巧妙构思。');
INSERT INTO `word` VALUES (155, '别开生面', 1, '比喻另外创出一种新的形式或局面。');
INSERT INTO `word` VALUES (156, '匠心独运', 1, '独创性地运用精巧的心思。');
INSERT INTO `word` VALUES (157, '另（独）辟蹊径', 1, '独创一种风格或新的方法。');

-- ----------------------------
-- Table structure for word_type
-- ----------------------------
DROP TABLE IF EXISTS `word_type`;
CREATE TABLE `word_type`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '词语类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of word_type
-- ----------------------------
INSERT INTO `word_type` VALUES (1, '成语');

SET FOREIGN_KEY_CHECKS = 1;
