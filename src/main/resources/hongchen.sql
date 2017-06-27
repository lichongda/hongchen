/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : hongchen

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-04-25 09:29:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_operator_log`;
CREATE TABLE `admin_operator_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `story` varchar(100) NOT NULL,
  `after_content` varchar(1000) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_operator_log
-- ----------------------------
INSERT INTO `admin_operator_log` VALUES ('9', '1', '李崇达', '停止定时器分组', '[4]', '2017-04-17 13:45:32');
INSERT INTO `admin_operator_log` VALUES ('10', '1', '李崇达', '停止定时器分组', '[4]', '2017-04-17 13:48:36');
INSERT INTO `admin_operator_log` VALUES ('12', '1', '李崇达', '停止定时器分组', '[4]', '2017-04-17 13:52:51');
INSERT INTO `admin_operator_log` VALUES ('13', '1', '李崇达', '停止定时器分组', '[1]', '2017-04-17 13:58:11');
INSERT INTO `admin_operator_log` VALUES ('14', '1', '李崇达', '启动定时器', '[1]', '2017-04-17 13:58:42');
INSERT INTO `admin_operator_log` VALUES ('15', '1', '李崇达', '停止定时器分组', '[1]', '2017-04-17 13:58:51');
INSERT INTO `admin_operator_log` VALUES ('16', '1', '李崇达', '停止定时器分组', '[6]', '2017-04-17 14:21:20');
INSERT INTO `admin_operator_log` VALUES ('19', '1', '李崇达', '停止定时器', '[18]', '2017-04-17 14:49:29');
INSERT INTO `admin_operator_log` VALUES ('21', '1', '李崇达', '启动定时器', '[3]', '2017-04-17 15:00:00');
INSERT INTO `admin_operator_log` VALUES ('23', '1', '李崇达', '停止定时器', '[20]', '2017-04-17 15:01:37');
INSERT INTO `admin_operator_log` VALUES ('24', '1', '李崇达', '停止定时器', '[20]', '2017-04-17 15:01:38');
INSERT INTO `admin_operator_log` VALUES ('25', '1', '李崇达', '停止定时器', '[20]', '2017-04-17 15:02:24');
INSERT INTO `admin_operator_log` VALUES ('26', '1', '李崇达', '启动定时器', '[1]', '2017-04-17 15:04:07');
INSERT INTO `admin_operator_log` VALUES ('27', '1', '李崇达', '重启定时器', '[1]', '2017-04-17 15:04:21');
INSERT INTO `admin_operator_log` VALUES ('28', '1', '李崇达', '停止定时器', '[1]', '2017-04-17 15:04:56');
INSERT INTO `admin_operator_log` VALUES ('29', '1', '李崇达', '启动定时器', '[1]', '2017-04-17 15:05:26');
INSERT INTO `admin_operator_log` VALUES ('30', '1', '李崇达', '停止定时器', '[1]', '2017-04-17 15:05:41');
INSERT INTO `admin_operator_log` VALUES ('33', '1', '李崇达', '修改定时器信息', '[{\"createTime\":1492413929451,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-17 15:25:29');
INSERT INTO `admin_operator_log` VALUES ('34', '1', '李崇达', '修改定时器信息', '[{\"createTime\":1492413991458,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2e\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-17 15:26:31');
INSERT INTO `admin_operator_log` VALUES ('35', '1', '李崇达', '修改定时器信息', '[{\"createTime\":1492413996937,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2e\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-17 15:26:37');
INSERT INTO `admin_operator_log` VALUES ('36', '1', '李崇达', '修改定时器信息', '[{\"createTime\":1492414055514,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2e\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-17 15:27:36');
INSERT INTO `admin_operator_log` VALUES ('37', '1', '李崇达', '修改定时器信息', '[{\"createTime\":1492414181737,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2e\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-17 15:29:42');
INSERT INTO `admin_operator_log` VALUES ('38', '1', '李崇达', '修改定时器信息', '[{\"createTime\":1492414344082,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2e\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-17 15:32:24');
INSERT INTO `admin_operator_log` VALUES ('39', '1', '李崇达', '修改定时器信息', '[{\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2e\",\"scheduleJobGroupId\":3,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-17 15:33:20');
INSERT INTO `admin_operator_log` VALUES ('41', '1', '李崇达', '启动定时器', '[1]', '2017-04-17 15:35:28');
INSERT INTO `admin_operator_log` VALUES ('42', '1', '李崇达', '停止定时器', '[1]', '2017-04-17 15:35:55');
INSERT INTO `admin_operator_log` VALUES ('43', '1', '李崇达', '停止定时器分组', '[1]', '2017-04-17 16:20:25');
INSERT INTO `admin_operator_log` VALUES ('44', '1', '李崇达', '启动定时器', '[20]', '2017-04-17 17:14:45');
INSERT INTO `admin_operator_log` VALUES ('45', '1', '李崇达', '停止定时器', '[20]', '2017-04-17 17:14:53');
INSERT INTO `admin_operator_log` VALUES ('46', '1', '李崇达', '修改定时器信息', '[{\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2esdf\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-21 14:33:51');
INSERT INTO `admin_operator_log` VALUES ('47', '1', '李崇达', '修改定时器信息', '[{\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"都是粉色的2es\",\"scheduleJobGroupId\":1,\"scheduleJobId\":20,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"测试\"}]', '2017-04-21 14:33:55');
INSERT INTO `admin_operator_log` VALUES ('48', '1', '李崇达', '添加定时器', '[{\"createTime\":1492756453260,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"\",\"scheduleJobGroupId\":1,\"scheduleJobId\":21,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"ss\",\"status\":1}]', '2017-04-21 14:34:18');
INSERT INTO `admin_operator_log` VALUES ('49', '1', '李崇达', '修改定时器信息', '[{\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"0\",\"scheduleJobGroupId\":3,\"scheduleJobId\":21,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"ss\"}]', '2017-04-21 14:34:28');
INSERT INTO `admin_operator_log` VALUES ('50', '1', '李崇达', '添加定时器', '[{\"createTime\":1492756499411,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"sdfsdf\",\"scheduleJobGroupId\":1,\"scheduleJobId\":22,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"dfgdfg\",\"status\":1}]', '2017-04-21 14:35:07');
INSERT INTO `admin_operator_log` VALUES ('51', '1', '李崇达', '启动定时器', '[22]', '2017-04-21 14:36:27');
INSERT INTO `admin_operator_log` VALUES ('52', '1', '李崇达', '停止定时器', '[22]', '2017-04-21 14:37:09');
INSERT INTO `admin_operator_log` VALUES ('53', '1', '李崇达', '启动定时器', '[22]', '2017-04-21 14:37:18');
INSERT INTO `admin_operator_log` VALUES ('54', '1', '李崇达', '停止定时器', '[22]', '2017-04-21 14:37:23');
INSERT INTO `admin_operator_log` VALUES ('55', '1', '李崇达', '启动定时器', '[20]', '2017-04-21 14:54:19');
INSERT INTO `admin_operator_log` VALUES ('56', '1', '李崇达', '同步定时器', '[]', '2017-04-21 15:00:57');
INSERT INTO `admin_operator_log` VALUES ('57', '1', '李崇达', '停止定时器', '[20]', '2017-04-21 15:01:08');
INSERT INTO `admin_operator_log` VALUES ('58', '1', '李崇达', '同步定时器', '[]', '2017-04-21 15:01:12');
INSERT INTO `admin_operator_log` VALUES ('59', '1', '李崇达', '停止定时器分组', '[7]', '2017-04-21 15:31:23');
INSERT INTO `admin_operator_log` VALUES ('60', '1', '李崇达', '停止定时器分组', '[7]', '2017-04-21 15:32:09');
INSERT INTO `admin_operator_log` VALUES ('61', '1', '李崇达', '停止定时器分组', '[1]', '2017-04-21 15:39:22');
INSERT INTO `admin_operator_log` VALUES ('62', '1', '李崇达', '添加定时器', '[{\"createTime\":1493023995987,\"scheduleJobClass\":\"com.hongchen.controller.TestScheduleJob\",\"scheduleJobCronExpression\":\"* 1-59 * * * ?\",\"scheduleJobDescription\":\"\",\"scheduleJobGroupId\":1,\"scheduleJobId\":23,\"scheduleJobMethod\":\"main\",\"scheduleJobName\":\"aasdsa\",\"status\":1}]', '2017-04-24 16:53:16');
INSERT INTO `admin_operator_log` VALUES ('63', '1', '李崇达', '停止定时器分组', '[1]', '2017-04-24 16:55:15');
INSERT INTO `admin_operator_log` VALUES ('64', '1', '李崇达', '启动定时器', '[20]', '2017-04-24 17:18:15');
INSERT INTO `admin_operator_log` VALUES ('65', '1', '李崇达', '停止定时器', '[20]', '2017-04-24 17:18:26');

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(50) NOT NULL COMMENT '名称',
  `permission_url` varchar(255) DEFAULT '',
  `permission_resource` varchar(250) NOT NULL COMMENT '权限资源',
  `permission_description` varchar(250) DEFAULT NULL COMMENT '权限资源',
  `permission_type_id` int(11) NOT NULL,
  `status` bit(1) NOT NULL,
  `permission_parentId` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `permission_resource` (`permission_resource`),
  KEY `fk_permission_parent` (`permission_parentId`),
  KEY `permission_type_id` (`permission_type_id`),
  CONSTRAINT `permission_type_id` FOREIGN KEY (`permission_type_id`) REFERENCES `admin_permission_type` (`permission_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES ('1', '所有权限', '', '/**', '所有权限', '1', '\0', null, '2017-03-29 11:19:52');
INSERT INTO `admin_permission` VALUES ('2', '系统管理', '   ', 'system:*', null, '1', '\0', '1', '2017-03-29 11:20:29');
INSERT INTO `admin_permission` VALUES ('3', '日志管理', 'html/admin/operator_log.html', 'log:*', '日志管理', '1', '\0', '2', '2017-03-29 11:23:34');
INSERT INTO `admin_permission` VALUES ('4', '登陆管理', '#', 'login:*', '登陆管理', '1', '\0', '2', '2017-03-30 16:49:32');
INSERT INTO `admin_permission` VALUES ('5', '用户管理', '无', 'user:*', '用户管理', '1', '\0', '1', '2017-03-30 17:32:30');
INSERT INTO `admin_permission` VALUES ('6', '删除', 'del', 'log:del', '日志删除', '2', '\0', '3', '2017-04-01 14:21:52');
INSERT INTO `admin_permission` VALUES ('8', '权限管理', '', 'privg:*', '权限管理', '1', '\0', '1', '2017-04-01 15:24:09');
INSERT INTO `admin_permission` VALUES ('9', '角色管理', 'html/admin/role.html', 'role:queryList', '角色管理', '1', '\0', '8', '2017-04-01 15:25:47');
INSERT INTO `admin_permission` VALUES ('10', '添加', 'add', 'role:add', '添加角色', '2', '\0', '9', '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES ('11', '删除', 'del', 'role:del', '删除角色', '2', '\0', '9', '2017-04-01 15:27:41');
INSERT INTO `admin_permission` VALUES ('12', '修改', 'edit', 'role:edit', '修改角色', '2', '\0', '9', '2017-04-01 15:28:14');
INSERT INTO `admin_permission` VALUES ('13', '用户列表', 'html/admin/userList.html', 'user:query', '用户管理', '1', '\0', '5', '2017-04-07 15:34:56');
INSERT INTO `admin_permission` VALUES ('14', '添加', 'add', 'user:add', '用户添加', '2', '\0', '13', '2017-04-07 17:00:24');
INSERT INTO `admin_permission` VALUES ('15', '修改 ', 'edit', 'user:edit', '用户修改', '2', '\0', '13', '2017-04-07 17:00:53');
INSERT INTO `admin_permission` VALUES ('16', '删除', 'del', 'user:del', '用户删除', '2', '\0', '13', '2017-04-07 17:01:24');
INSERT INTO `admin_permission` VALUES ('17', '菜单管理', '12314535435dfffffffdddddddddddddddddddddssssssssssssssssssssssssssssssssddddddddddddffffffffffffffddddddddddddd', 'menu:*', null, '1', '\0', '1', '2017-04-10 10:26:36');
INSERT INTO `admin_permission` VALUES ('18', '菜单列表', 'html/admin/menuList.html', 'menu:query', '菜单列表', '1', '\0', '17', '2017-04-10 10:28:32');
INSERT INTO `admin_permission` VALUES ('19', '添加', 'add', 'menu:add', '添加菜单', '2', '\0', '18', '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES ('20', '删除', 'del', 'menu:del', '删除菜单', '2', '\0', '18', '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES ('21', '修改', 'edit', 'menu:edit', '修改菜单', '2', '\0', '18', '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES ('46', '文章管理', 'edit', 'Article:*', 'Article', '2', '', '3', '2017-04-13 00:19:56');
INSERT INTO `admin_permission` VALUES ('47', '文章列表', 'dsf', 'listArticel', 'listArticel', '1', '', '46', '2017-04-13 00:20:38');
INSERT INTO `admin_permission` VALUES ('51', '任务管理', '', 'quartz:*', '定时任务', '1', '', '1', '2017-04-13 17:18:15');
INSERT INTO `admin_permission` VALUES ('52', '定时列表', 'html/admin/quartzList.html', 'quartz:query*', 'quartz', '1', '', '51', '2017-04-13 17:19:06');
INSERT INTO `admin_permission` VALUES ('58', '定时分组', 'html/admin/quartzGroup.html', 'quartzGroup:query', null, '1', '', '51', '2017-04-24 17:17:32');

-- ----------------------------
-- Table structure for admin_permission_type
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission_type`;
CREATE TABLE `admin_permission_type` (
  `permission_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_type_name` varchar(50) NOT NULL,
  `permission_type_description` varchar(250) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`permission_type_id`),
  UNIQUE KEY `permission_type_name` (`permission_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_permission_type
-- ----------------------------
INSERT INTO `admin_permission_type` VALUES ('1', '功能', '功能', '2017-03-30 14:49:11');
INSERT INTO `admin_permission_type` VALUES ('2', '按钮', '按钮', '2017-03-30 14:49:31');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_description` varchar(250) DEFAULT '' COMMENT '角色说明',
  `status` bit(1) NOT NULL COMMENT '角色状态',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '超级管理员', '超级管理员无需分配任何角色', '', '2017-04-21 17:33:13');
INSERT INTO `admin_role` VALUES ('2', '系统管理', '水电费2', '', '2017-04-24 17:03:55');

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  KEY `fk_rolepermission_permission` (`permission_id`),
  KEY `fk_rolepermission_role` (`role_id`),
  CONSTRAINT `fk_rolepermission_permission` FOREIGN KEY (`permission_id`) REFERENCES `admin_permission` (`permission_id`),
  CONSTRAINT `fk_rolepermission_role` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES ('1', '1');
INSERT INTO `admin_role_permission` VALUES ('2', '8');
INSERT INTO `admin_role_permission` VALUES ('2', '9');
INSERT INTO `admin_role_permission` VALUES ('2', '10');
INSERT INTO `admin_role_permission` VALUES ('2', '12');
INSERT INTO `admin_role_permission` VALUES ('2', '6');
INSERT INTO `admin_role_permission` VALUES ('2', '5');
INSERT INTO `admin_role_permission` VALUES ('2', '13');
INSERT INTO `admin_role_permission` VALUES ('2', '15');
INSERT INTO `admin_role_permission` VALUES ('2', '14');
INSERT INTO `admin_role_permission` VALUES ('2', '16');
INSERT INTO `admin_role_permission` VALUES ('2', '17');
INSERT INTO `admin_role_permission` VALUES ('2', '18');
INSERT INTO `admin_role_permission` VALUES ('2', '19');
INSERT INTO `admin_role_permission` VALUES ('2', '21');
INSERT INTO `admin_role_permission` VALUES ('2', '20');
INSERT INTO `admin_role_permission` VALUES ('2', '11');
INSERT INTO `admin_role_permission` VALUES ('2', '3');
INSERT INTO `admin_role_permission` VALUES ('2', '2');
INSERT INTO `admin_role_permission` VALUES ('2', '6');
INSERT INTO `admin_role_permission` VALUES ('2', '51');
INSERT INTO `admin_role_permission` VALUES ('2', '52');
INSERT INTO `admin_role_permission` VALUES ('2', '4');
INSERT INTO `admin_role_permission` VALUES ('2', '46');
INSERT INTO `admin_role_permission` VALUES ('2', '58');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `user_password` varchar(250) NOT NULL COMMENT '用户密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_status` bit(1) NOT NULL COMMENT '用户状态',
  `department_id` int(11) DEFAULT NULL COMMENT '用户部门',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('12', 'lichongda2', 'fsdfh', '123456', '2017-04-24 17:01:25', '', null);
INSERT INTO `admin_user` VALUES ('13', 'sdfsd', 'fsdf', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-12 16:05:11', '', null);
INSERT INTO `admin_user` VALUES ('14', 'sdfs', 'fsdf', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-12 16:05:15', '', null);
INSERT INTO `admin_user` VALUES ('23', 'cvzxvxz', 'vxcvbcb', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-12 16:06:21', '', null);
INSERT INTO `admin_user` VALUES ('25', 'test11', 'test11', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 00:17:30', '', null);
INSERT INTO `admin_user` VALUES ('27', '多舒服舒服', '电风扇', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 10:01:42', '', null);
INSERT INTO `admin_user` VALUES ('28', '士大夫士大夫', '士大夫士大夫', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 10:02:33', '', null);
INSERT INTO `admin_user` VALUES ('29', 'haha', 'haha', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-04-13 16:59:28', '', null);
INSERT INTO `admin_user` VALUES ('34', 'lichongda3', '123123', '123', '2017-04-19 17:38:07', '', null);
INSERT INTO `admin_user` VALUES ('37', 'dfsdf', 'sdfs', 'fsdfsf', '2017-04-20 13:53:25', '', null);
INSERT INTO `admin_user` VALUES ('38', 'sdfsfa', 'sdfs', '123456', '2017-04-20 13:54:24', '', null);
INSERT INTO `admin_user` VALUES ('39', 'lichongda', 'dsfs', '123456', '2017-04-24 16:57:31', '', null);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_userrole_user` (`user_id`),
  KEY `fk_userrole_role` (`role_id`),
  CONSTRAINT `fk_userrole_role` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`role_id`),
  CONSTRAINT `fk_userrole_user` FOREIGN KEY (`user_id`) REFERENCES `admin_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES ('12', '1');
INSERT INTO `admin_user_role` VALUES ('13', '2');
INSERT INTO `admin_user_role` VALUES ('29', '2');
INSERT INTO `admin_user_role` VALUES ('39', '2');

-- ----------------------------
-- Table structure for quartz_schedulejob
-- ----------------------------
DROP TABLE IF EXISTS `quartz_schedulejob`;
CREATE TABLE `quartz_schedulejob` (
  `schedule_job_id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_job_name` varchar(250) NOT NULL DEFAULT '0',
  `schedule_job_group_id` int(11) DEFAULT '0',
  `status` bit(1) NOT NULL DEFAULT b'0',
  `schedule_job_description` varchar(250) DEFAULT '0',
  `create_time` datetime NOT NULL,
  `schedule_job_cron_expression` varchar(250) NOT NULL DEFAULT '0',
  `schedule_job_method` varchar(250) NOT NULL,
  `schedule_job_class` varchar(250) NOT NULL,
  PRIMARY KEY (`schedule_job_id`),
  UNIQUE KEY `schedule_job_name` (`schedule_job_name`),
  KEY `schedule_job_group_id` (`schedule_job_group_id`),
  CONSTRAINT `schedule_job_group_id` FOREIGN KEY (`schedule_job_group_id`) REFERENCES `quartz_schedulejob_group` (`schedule_job_groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_schedulejob
-- ----------------------------
INSERT INTO `quartz_schedulejob` VALUES ('20', '测试', '1', '', '都是粉色的2es', '2017-04-17 15:32:24', '* 1-59 * * * ?', 'main', 'com.hongchen.controller.TestScheduleJob');

-- ----------------------------
-- Table structure for quartz_schedulejob_group
-- ----------------------------
DROP TABLE IF EXISTS `quartz_schedulejob_group`;
CREATE TABLE `quartz_schedulejob_group` (
  `schedule_job_groupId` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_job_group_name` varchar(250) NOT NULL DEFAULT '0',
  `schedule_job_group_description` varchar(250) DEFAULT '0',
  `status` bit(1) NOT NULL DEFAULT b'1',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`schedule_job_groupId`),
  UNIQUE KEY `schedule_job_group_name` (`schedule_job_group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_schedulejob_group
-- ----------------------------
INSERT INTO `quartz_schedulejob_group` VALUES ('1', '红尘', '测试', '\0', '2017-04-13 16:34:11');
INSERT INTO `quartz_schedulejob_group` VALUES ('3', '嘎嘎', 'adsf', '', '2017-04-13 16:34:28');
INSERT INTO `quartz_schedulejob_group` VALUES ('6', '34234sdfs', 'sdf', '', '2017-04-17 14:21:15');
INSERT INTO `quartz_schedulejob_group` VALUES ('7', 'sdfsdfgdfg', 'fsdfdffg', '', '2017-04-21 15:31:12');
INSERT INTO `quartz_schedulejob_group` VALUES ('8', 'dfd', 'dsfsdf', '', '2017-04-21 15:36:12');
INSERT INTO `quartz_schedulejob_group` VALUES ('9', 'sdf', 'sdfsd', '', '2017-04-21 15:38:48');
