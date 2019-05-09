/*
 Navicat Premium Data Transfer

 Source Server         : 本地开发
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost:3306
 Source Schema         : hongchen

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 09/05/2019 16:34:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_operator_log`;
CREATE TABLE `admin_operator_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `story` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `after_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_operator_log
-- ----------------------------
INSERT INTO `admin_operator_log` VALUES (71, 39, 'lichongda', '启动定时器', '[20]', '2018-10-31 15:19:34');
INSERT INTO `admin_operator_log` VALUES (73, 39, 'lichongda', '修改服务器信息', '[{\"id\":1}]', '2018-10-31 23:11:29');
INSERT INTO `admin_operator_log` VALUES (74, 39, 'lichongda', '修改服务器信息', '[{\"id\":1}]', '2018-10-31 23:11:34');
INSERT INTO `admin_operator_log` VALUES (75, 39, 'lichongda', '修改服务器信息', '[{\"id\":1}]', '2018-10-31 23:12:37');
INSERT INTO `admin_operator_log` VALUES (76, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"2\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"3\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:54:22');
INSERT INTO `admin_operator_log` VALUES (77, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"23\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:54:26');
INSERT INTO `admin_operator_log` VALUES (78, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"34\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:54:40');
INSERT INTO `admin_operator_log` VALUES (79, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"sdf\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:56:11');
INSERT INTO `admin_operator_log` VALUES (80, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"2\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:56:58');
INSERT INTO `admin_operator_log` VALUES (81, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"sdf\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:57:27');
INSERT INTO `admin_operator_log` VALUES (82, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"sdf\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:57:30');
INSERT INTO `admin_operator_log` VALUES (83, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"sdf\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:57:33');
INSERT INTO `admin_operator_log` VALUES (84, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"sdf\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"1\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:59:46');
INSERT INTO `admin_operator_log` VALUES (85, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"sdf\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-10-31 23:59:54');
INSERT INTO `admin_operator_log` VALUES (86, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"id\":1,\"keyName\":\"2\",\"name\":\"sdf\",\"remarks\":\"\",\"serverAccount\":\"1\",\"serverIp\":\"sdf\",\"serverPassword\":\"2\",\"serverPort\":\"1\"}]', '2018-11-01 00:00:06');
INSERT INTO `admin_operator_log` VALUES (87, 39, 'lichongda', '添加远程服务器', '[{\"command\":\"3\",\"connectionType\":0,\"createTime\":1541002650924,\"id\":2,\"keyName\":\"3\",\"name\":\"33\",\"remarks\":\"\",\"serverAccount\":\"3\",\"serverIp\":\"33\",\"serverPassword\":\"\",\"serverPort\":\"33\",\"updateTime\":1541002650924}]', '2018-11-01 00:17:39');
INSERT INTO `admin_operator_log` VALUES (88, 39, 'lichongda', '添加远程服务器', '[{\"command\":\"3\",\"connectionType\":0,\"createTime\":1541002658979,\"id\":3,\"keyName\":\"3\",\"name\":\"33\",\"remarks\":\"\",\"serverAccount\":\"3\",\"serverIp\":\"33\",\"serverPassword\":\"\",\"serverPort\":\"33\",\"updateTime\":1541002658979}]', '2018-11-01 00:17:39');
INSERT INTO `admin_operator_log` VALUES (89, 39, 'lichongda', '添加远程服务器', '[{\"command\":\"ls /usr/local/yougou/logistics/lsp/WEB-INF/lib/log*\",\"connectionType\":1,\"createTime\":1541002717434,\"id\":4,\"keyName\":\"dd\",\"name\":\"lsp\",\"remarks\":\"dsf\",\"serverAccount\":\"develop\",\"serverIp\":\"10.240.4.39\",\"serverPassword\":\"\",\"serverPort\":\"22\",\"updateTime\":1541002717434}]', '2018-11-01 00:18:37');
INSERT INTO `admin_operator_log` VALUES (90, 39, 'lichongda', '添加远程服务器', '[{\"command\":\"3\",\"connectionType\":0,\"createTime\":1541063202948,\"id\":5,\"keyName\":\"3\",\"name\":\"3\",\"remarks\":\"\",\"serverAccount\":\"3\",\"serverIp\":\"3\",\"serverPassword\":\"\",\"serverPort\":\"3\",\"updateTime\":1541063202948}]', '2018-11-01 17:06:43');
INSERT INTO `admin_operator_log` VALUES (91, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"\",\"connectionType\":0,\"id\":4,\"keyName\":\"Identity\",\"name\":\"lsp\",\"remarks\":\"dsf\",\"serverAccount\":\"develop\",\"serverIp\":\"10.240.4.39\",\"serverPassword\":\"\",\"serverPort\":22}]', '2018-11-01 18:28:13');
INSERT INTO `admin_operator_log` VALUES (92, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"ls /usr/local/yougou/logistics/lsp/WEB-INF/lib/log*\",\"connectionType\":0,\"id\":4,\"keyName\":\"Identity\",\"name\":\"lsp\",\"remarks\":\"dsf\",\"serverAccount\":\"develop\",\"serverIp\":\"10.240.4.39\",\"serverPassword\":\"\",\"serverPort\":22}]', '2018-11-02 10:23:31');
INSERT INTO `admin_operator_log` VALUES (93, 39, 'lichongda', '修改服务器信息', '[{\"command\":\"ls /usr/local/yougou/logistics/lsp/WEB-INF/lib/log*\",\"connectionType\":1,\"id\":4,\"keyName\":\"Identity\",\"name\":\"lsp\",\"remarks\":\"dsf\",\"serverAccount\":\"develop\",\"serverIp\":\"10.240.4.39\",\"serverPassword\":\"\",\"serverPort\":22}]', '2018-11-02 10:49:43');
INSERT INTO `admin_operator_log` VALUES (94, 39, 'lichongda', '添加远程服务器', '[{\"command\":\"2\",\"connectionType\":0,\"createTime\":1541131522408,\"id\":5,\"keyName\":\"2\",\"name\":\"22\",\"remarks\":\"2\",\"serverAccount\":\"2\",\"serverIp\":\"22\",\"serverPassword\":\"2\",\"serverPort\":2,\"updateTime\":1541131522408}]', '2018-11-02 12:05:22');
INSERT INTO `admin_operator_log` VALUES (95, 39, 'lichongda', '启动定时器', '[20]', '2018-11-07 17:53:17');
INSERT INTO `admin_operator_log` VALUES (96, 39, 'lichongda', '停止定时器', '[20]', '2018-11-07 17:53:32');
INSERT INTO `admin_operator_log` VALUES (97, 39, 'lichongda', '添加远程服务器', '[{\"command\":\"pwd\",\"connectionType\":0,\"createTime\":1541586034555,\"id\":5,\"keyName\":\"\",\"name\":\"开发测试服务器\",\"remarks\":\"测试\",\"serverAccount\":\"root\",\"serverIp\":\"10.234.6.209\",\"serverPassword\":\"devwl#018\",\"serverPort\":22,\"updateTime\":1541586034555}]', '2018-11-07 18:20:35');

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `permission_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `permission_resource` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限资源',
  `permission_description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限资源',
  `permission_type_id` int(11) NOT NULL,
  `status` bit(1) NOT NULL,
  `permission_parentId` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`permission_id`) USING BTREE,
  UNIQUE INDEX `permission_resource`(`permission_resource`) USING BTREE,
  INDEX `fk_permission_parent`(`permission_parentId`) USING BTREE,
  INDEX `permission_type_id`(`permission_type_id`) USING BTREE,
  CONSTRAINT `permission_type_id` FOREIGN KEY (`permission_type_id`) REFERENCES `admin_permission_type` (`permission_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (1, '所有权限', '', '/**', '所有权限', 1, b'0', NULL, '2017-03-29 11:19:52');
INSERT INTO `admin_permission` VALUES (2, '系统管理', '   ', 'system:*', NULL, 1, b'0', 1, '2017-03-29 11:20:29');
INSERT INTO `admin_permission` VALUES (3, '日志管理', 'html/admin/operator_log.html', 'log:*', '日志管理', 1, b'0', 2, '2017-03-29 11:23:34');
INSERT INTO `admin_permission` VALUES (5, '用户管理', '无', 'user:*', '用户管理', 1, b'0', 1, '2017-03-30 17:32:30');
INSERT INTO `admin_permission` VALUES (6, '删除', 'del', 'log:del', '日志删除', 2, b'0', 3, '2017-04-01 14:21:52');
INSERT INTO `admin_permission` VALUES (8, '权限管理', '', 'privg:*', '权限管理', 1, b'0', 1, '2017-04-01 15:24:09');
INSERT INTO `admin_permission` VALUES (9, '角色管理', 'html/admin/role.html', 'role:queryList', '角色管理', 1, b'0', 8, '2017-04-01 15:25:47');
INSERT INTO `admin_permission` VALUES (10, '添加', 'add', 'role:add', '添加角色', 2, b'0', 9, '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES (11, '删除', 'del', 'role:del', '删除角色', 2, b'0', 9, '2017-04-01 15:27:41');
INSERT INTO `admin_permission` VALUES (12, '修改', 'edit', 'role:edit', '修改角色', 2, b'0', 9, '2017-04-01 15:28:14');
INSERT INTO `admin_permission` VALUES (13, '用户列表', 'html/admin/userList.html', 'user:query', '用户管理', 1, b'0', 5, '2017-04-07 15:34:56');
INSERT INTO `admin_permission` VALUES (14, '添加', 'add', 'user:add', '用户添加', 2, b'0', 13, '2017-04-07 17:00:24');
INSERT INTO `admin_permission` VALUES (15, '修改 ', 'edit', 'user:edit', '用户修改', 2, b'0', 13, '2017-04-07 17:00:53');
INSERT INTO `admin_permission` VALUES (16, '删除', 'del', 'user:del', '用户删除', 2, b'0', 13, '2017-04-07 17:01:24');
INSERT INTO `admin_permission` VALUES (17, '菜单管理', '12314535435dfffffffdddddddddddddddddddddssssssssssssssssssssssssssssssssddddddddddddffffffffffffffddddddddddddd', 'menu:*', NULL, 1, b'0', 1, '2017-04-10 10:26:36');
INSERT INTO `admin_permission` VALUES (18, '菜单列表', 'html/admin/menuList.html', 'menu:query', '菜单列表', 1, b'0', 17, '2017-04-10 10:28:32');
INSERT INTO `admin_permission` VALUES (19, '添加', 'add', 'menu:add', '添加菜单', 2, b'0', 18, '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES (20, '删除', 'del', 'menu:del', '删除菜单', 2, b'0', 18, '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES (21, '修改', 'edit', 'menu:edit', '修改菜单', 2, b'0', 18, '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES (46, '文章管理', 'edit', 'Article:*', 'Article', 2, b'1', 3, '2017-04-13 00:19:56');
INSERT INTO `admin_permission` VALUES (47, '文章列表', 'dsf', 'listArticel', 'listArticel', 1, b'1', 46, '2017-04-13 00:20:38');
INSERT INTO `admin_permission` VALUES (51, '任务管理', '', 'quartz:*', '定时任务', 1, b'1', 1, '2017-04-13 17:18:15');
INSERT INTO `admin_permission` VALUES (52, '定时列表', 'html/admin/quartzList.html', 'quartz:query*', 'quartz', 1, b'1', 51, '2017-04-13 17:19:06');
INSERT INTO `admin_permission` VALUES (58, '定时分组', 'html/admin/quartzGroup.html', 'quartzGroup:query', NULL, 1, b'1', 51, '2017-04-24 17:17:32');
INSERT INTO `admin_permission` VALUES (59, '服务器管理', '无', 'server:*', '服务器管理', 1, b'1', 1, '2018-10-31 21:55:05');
INSERT INTO `admin_permission` VALUES (61, '机器ip', 'html/admin/remoteSsh.html', 'server:query', '远程服务器ip管理', 1, b'1', 59, '2018-10-31 21:58:11');

-- ----------------------------
-- Table structure for admin_permission_type
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission_type`;
CREATE TABLE `admin_permission_type`  (
  `permission_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_type_description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL,
  PRIMARY KEY (`permission_type_id`) USING BTREE,
  UNIQUE INDEX `permission_type_name`(`permission_type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_permission_type
-- ----------------------------
INSERT INTO `admin_permission_type` VALUES (1, '功能', '功能', '2017-03-30 14:49:11');
INSERT INTO `admin_permission_type` VALUES (2, '按钮', '按钮', '2017-03-30 14:49:31');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色说明',
  `status` bit(1) NOT NULL COMMENT '角色状态',
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, '超级管理员', '超级管理员无需分配任何角色', b'1', '2018-10-31 22:05:06');
INSERT INTO `admin_role` VALUES (2, '系统管理', '水电费2', b'1', '2017-04-24 17:03:55');

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  INDEX `fk_rolepermission_permission`(`permission_id`) USING BTREE,
  INDEX `fk_rolepermission_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_rolepermission_permission` FOREIGN KEY (`permission_id`) REFERENCES `admin_permission` (`permission_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_rolepermission_role` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES (1, 1);
INSERT INTO `admin_role_permission` VALUES (2, 8);
INSERT INTO `admin_role_permission` VALUES (2, 9);
INSERT INTO `admin_role_permission` VALUES (2, 10);
INSERT INTO `admin_role_permission` VALUES (2, 12);
INSERT INTO `admin_role_permission` VALUES (2, 6);
INSERT INTO `admin_role_permission` VALUES (2, 5);
INSERT INTO `admin_role_permission` VALUES (2, 13);
INSERT INTO `admin_role_permission` VALUES (2, 15);
INSERT INTO `admin_role_permission` VALUES (2, 14);
INSERT INTO `admin_role_permission` VALUES (2, 16);
INSERT INTO `admin_role_permission` VALUES (2, 17);
INSERT INTO `admin_role_permission` VALUES (2, 18);
INSERT INTO `admin_role_permission` VALUES (2, 19);
INSERT INTO `admin_role_permission` VALUES (2, 21);
INSERT INTO `admin_role_permission` VALUES (2, 20);
INSERT INTO `admin_role_permission` VALUES (2, 11);
INSERT INTO `admin_role_permission` VALUES (2, 3);
INSERT INTO `admin_role_permission` VALUES (2, 2);
INSERT INTO `admin_role_permission` VALUES (2, 6);
INSERT INTO `admin_role_permission` VALUES (2, 51);
INSERT INTO `admin_role_permission` VALUES (2, 52);
INSERT INTO `admin_role_permission` VALUES (2, 46);
INSERT INTO `admin_role_permission` VALUES (2, 58);
INSERT INTO `admin_role_permission` VALUES (2, 59);
INSERT INTO `admin_role_permission` VALUES (2, 61);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_password` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `user_status` bit(1) NOT NULL COMMENT '用户状态',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '用户部门',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (12, 'lichongda2', 'fsdfh', '123456', '2017-04-24 17:01:25', b'1', NULL);
INSERT INTO `admin_user` VALUES (13, 'sdfsd', 'fsdf', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-12 16:05:11', b'1', NULL);
INSERT INTO `admin_user` VALUES (14, 'sdfs', 'fsdf', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-12 16:05:15', b'1', NULL);
INSERT INTO `admin_user` VALUES (23, 'cvzxvxz', 'vxcvbcb', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-12 16:06:21', b'1', NULL);
INSERT INTO `admin_user` VALUES (25, 'test11', 'test11', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 00:17:30', b'1', NULL);
INSERT INTO `admin_user` VALUES (27, '多舒服舒服', '电风扇', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 10:01:42', b'1', NULL);
INSERT INTO `admin_user` VALUES (28, '士大夫士大夫', '士大夫士大夫', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 10:02:33', b'1', NULL);
INSERT INTO `admin_user` VALUES (29, 'haha', 'haha', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 16:59:28', b'1', NULL);
INSERT INTO `admin_user` VALUES (34, 'lichongda3', '123123', '123', '2017-04-19 17:38:07', b'1', NULL);
INSERT INTO `admin_user` VALUES (37, 'dfsdf', 'sdfs', 'fsdfsf', '2017-04-20 13:53:25', b'1', NULL);
INSERT INTO `admin_user` VALUES (38, 'sdfsfa', 'sdfs', '123456', '2017-04-20 13:54:24', b'1', NULL);
INSERT INTO `admin_user` VALUES (39, 'lichongda', 'dsfs', '123456', '2018-10-31 22:51:20', b'1', NULL);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_userrole_user`(`user_id`) USING BTREE,
  INDEX `fk_userrole_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_userrole_role` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_userrole_user` FOREIGN KEY (`user_id`) REFERENCES `admin_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (12, 1);
INSERT INTO `admin_user_role` VALUES (13, 2);
INSERT INTO `admin_user_role` VALUES (29, 2);
INSERT INTO `admin_user_role` VALUES (39, 2);

-- ----------------------------
-- Table structure for quartz_schedulejob
-- ----------------------------
DROP TABLE IF EXISTS `quartz_schedulejob`;
CREATE TABLE `quartz_schedulejob`  (
  `schedule_job_id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_job_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `schedule_job_group_id` int(11) NULL DEFAULT 0,
  `status` bit(1) NOT NULL DEFAULT b'0',
  `schedule_job_description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `create_time` datetime(0) NULL,
  `schedule_job_cron_expression` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `schedule_job_method` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `schedule_job_class` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`schedule_job_id`) USING BTREE,
  UNIQUE INDEX `schedule_job_name`(`schedule_job_name`) USING BTREE,
  INDEX `schedule_job_group_id`(`schedule_job_group_id`) USING BTREE,
  CONSTRAINT `schedule_job_group_id` FOREIGN KEY (`schedule_job_group_id`) REFERENCES `quartz_schedulejob_group` (`schedule_job_groupId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of quartz_schedulejob
-- ----------------------------
INSERT INTO `quartz_schedulejob` VALUES (20, '测试', 1, b'1', '都是粉色的2es', '2017-04-17 15:32:24', '* 1-59 * * * ?', 'main', 'com.hongchen.controller.TestScheduleJob');

-- ----------------------------
-- Table structure for quartz_schedulejob_group
-- ----------------------------
DROP TABLE IF EXISTS `quartz_schedulejob_group`;
CREATE TABLE `quartz_schedulejob_group`  (
  `schedule_job_groupId` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_job_group_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `schedule_job_group_description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `status` bit(1) NOT NULL DEFAULT b'1',
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`schedule_job_groupId`) USING BTREE,
  UNIQUE INDEX `schedule_job_group_name`(`schedule_job_group_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of quartz_schedulejob_group
-- ----------------------------
INSERT INTO `quartz_schedulejob_group` VALUES (1, '红尘', '测试', b'0', '2017-04-13 16:34:11');
INSERT INTO `quartz_schedulejob_group` VALUES (3, '嘎嘎', 'adsf', b'1', '2017-04-13 16:34:28');
INSERT INTO `quartz_schedulejob_group` VALUES (6, '34234sdfs', 'sdf', b'1', '2017-04-17 14:21:15');
INSERT INTO `quartz_schedulejob_group` VALUES (7, 'sdfsdfgdfg', 'fsdfdffg', b'1', '2017-04-21 15:31:12');
INSERT INTO `quartz_schedulejob_group` VALUES (8, 'dfd', 'dsfsdf', b'1', '2017-04-21 15:36:12');
INSERT INTO `quartz_schedulejob_group` VALUES (9, 'sdf', 'sdfsd', b'1', '2017-04-21 15:38:48');

-- ----------------------------
-- Table structure for remote_ssh
-- ----------------------------
DROP TABLE IF EXISTS `remote_ssh`;
CREATE TABLE `remote_ssh`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `command` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '命令',
  `key_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密钥名称',
  `connection_type` int(3) NOT NULL DEFAULT 0 COMMENT '连接类型: 0密码连接 1密钥连接',
  `server_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '服务密码',
  `server_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '服务器账号',
  `server_port` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '服务器端口',
  `server_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '服务器地址',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of remote_ssh
-- ----------------------------
INSERT INTO `remote_ssh` VALUES (4, 'lsp', 'ls /usr/local/yougou/logistics/lsp/WEB-INF/lib/log*', 'Identity', 1, NULL, 'develop', '22', '10.240.4.39', 'dsf', '2018-11-01 00:18:37', '2018-11-01 00:18:37', NULL, NULL);
INSERT INTO `remote_ssh` VALUES (5, '开发测试服务器', 'pwd', NULL, 0, 'devwl#018', 'root', '22', '10.234.6.209', '测试', '2018-11-07 18:20:35', '2018-11-07 18:20:35', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
