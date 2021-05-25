/*
 Navicat Premium Data Transfer

 Source Server         : 139.159.147.237
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 139.159.147.237:3306
 Source Schema         : tests

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 13/05/2020 23:42:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `des` longtext,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------
BEGIN;
INSERT INTO `demo` VALUES (1, '2020-05-13 23:27:34', 'java是第一语言', '2020-05-13 23:28:14', 'java', '跟阿牛学java');
INSERT INTO `demo` VALUES (2, '2020-05-13 23:27:34', 'javaee', '2020-05-13 23:28:14', '开发必备', '跟阿牛学java');
INSERT INTO `demo` VALUES (3, '2020-05-13 23:27:34', 'javaee', '2020-05-13 23:28:14', '开发必备', '跟阿牛学java');
INSERT INTO `demo` VALUES (6, '2020-05-13 23:27:34', 'javaee', '2020-05-13 23:28:14', '开发必备', '跟阿牛学java');
INSERT INTO `demo` VALUES (10, '2020-05-13 23:27:34', 'javaee', '2020-05-13 23:28:14', '开发必备', '跟阿牛学java');
INSERT INTO `demo` VALUES (11, '2020-05-13 23:27:34', 'javaee', '2020-05-13 23:28:14', '开发必备', '跟阿牛学java');
INSERT INTO `demo` VALUES (13, '2020-05-13 23:27:34', 'javaee', '2020-05-13 23:28:14', '开发必备', '跟阿牛学java');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
