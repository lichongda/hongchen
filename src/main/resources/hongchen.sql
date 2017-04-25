/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : hongchen

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-04-01 17:34:22
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_operator_log
-- ----------------------------
INSERT INTO `admin_operator_log` VALUES ('1', '1', '张三', '添加数据', '士大夫士大夫', '2017-03-31 16:29:07');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES ('1', '所有权限', '', '/*', '所有权限', '1', '\0', null, '2017-03-29 11:19:52');
INSERT INTO `admin_permission` VALUES ('2', '系统管理', '', 'system:*', null, '1', '\0', '1', '2017-03-29 11:20:29');
INSERT INTO `admin_permission` VALUES ('3', '日志管理', 'html/admin/operator_log.html', 'log:*', '日志管理', '1', '\0', '2', '2017-03-29 11:23:34');
INSERT INTO `admin_permission` VALUES ('4', '登陆管理', '#', 'login:*', '登陆管理', '1', '\0', '2', '2017-03-30 16:49:32');
INSERT INTO `admin_permission` VALUES ('5', '用户管理', '', 'user:*', '用户管理', '1', '\0', '1', '2017-03-30 17:32:30');
INSERT INTO `admin_permission` VALUES ('6', '删除', 'del', 'log:del', '日志删除', '2', '\0', '3', '2017-04-01 14:21:52');
INSERT INTO `admin_permission` VALUES ('7', '修改', 'edit', 'log:edit', '修改日志', '2', '\0', '3', '2017-04-01 14:54:51');
INSERT INTO `admin_permission` VALUES ('8', '权限管理', '', 'privg:*', '权限管理', '1', '\0', '1', '2017-04-01 15:24:09');
INSERT INTO `admin_permission` VALUES ('9', '角色管理', 'html/admin/role.html', 'role:*', '角色管理', '1', '\0', '8', '2017-04-01 15:25:47');
INSERT INTO `admin_permission` VALUES ('10', '添加', 'add', 'role:add', '添加角色', '2', '\0', '9', '2017-04-01 15:27:02');
INSERT INTO `admin_permission` VALUES ('11', '删除', 'del', 'role:del', '删除角色', '2', '\0', '9', '2017-04-01 15:27:41');
INSERT INTO `admin_permission` VALUES ('12', '修改', 'edit', 'role:edit', '修改角色', '2', '\0', '9', '2017-04-01 15:28:14');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '超级管理员', '所有角色', '', '2017-03-29 11:17:00');
INSERT INTO `admin_role` VALUES ('2', '系统管理', '管理菜单', '', '2017-03-29 11:17:43');

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
INSERT INTO `admin_role_permission` VALUES ('2', '2');
INSERT INTO `admin_role_permission` VALUES ('2', '6');
INSERT INTO `admin_role_permission` VALUES ('2', '3');
INSERT INTO `admin_role_permission` VALUES ('2', '4');
INSERT INTO `admin_role_permission` VALUES ('2', '5');
INSERT INTO `admin_role_permission` VALUES ('2', '7');
INSERT INTO `admin_role_permission` VALUES ('2', '8');
INSERT INTO `admin_role_permission` VALUES ('2', '9');
INSERT INTO `admin_role_permission` VALUES ('2', '10');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', '管理员', '31f123ba21dc3d21a9b54a7a78f65829', '2017-03-28 15:40:55', '', null);
INSERT INTO `admin_user` VALUES ('2', 'lichongda', '李崇达', 'cb410f35c4e8d8f8ce74d1233a8311e8', '2017-03-28 16:45:56', '', null);

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
INSERT INTO `admin_user_role` VALUES ('1', '1');
INSERT INTO `admin_user_role` VALUES ('2', '2');
