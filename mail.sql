/*
Navicat MySQL Data Transfer

Source Server         : master
Source Server Version : 50556
Source Host           : 192.168.142.131:3306
Source Database       : mail

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-01-04 09:22:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mail_send1
-- ----------------------------
DROP TABLE IF EXISTS `mail_send1`;
CREATE TABLE `mail_send1` (
  `SEND_ID` varchar(40) NOT NULL,
  `SEND_TO` varchar(40) NOT NULL,
  `SEND_MAIL` varchar(40) DEFAULT NULL,
  `SEND_CONTENT` varchar(400) NOT NULL,
  `SEND_PRIORITY` decimal(10,0) NOT NULL,
  `SEND_COUNT` decimal(10,0) NOT NULL,
  `SEND_STATUS` varchar(10) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `VERSION` decimal(10,0) NOT NULL,
  `UPDATE_BY` varchar(40) NOT NULL,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SEND_USER_ID` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`SEND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for mail_send2
-- ----------------------------
DROP TABLE IF EXISTS `mail_send2`;
CREATE TABLE `mail_send2` (
  `SEND_ID` varchar(40) NOT NULL,
  `SEND_TO` varchar(40) NOT NULL,
  `SEND_MAIL` varchar(40) DEFAULT NULL,
  `SEND_CONTENT` varchar(400) NOT NULL,
  `SEND_PRIORITY` decimal(10,0) NOT NULL,
  `SEND_COUNT` decimal(10,0) NOT NULL,
  `SEND_STATUS` varchar(10) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `VERSION` decimal(10,0) NOT NULL,
  `UPDATE_BY` varchar(40) NOT NULL,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SEND_USER_ID` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`SEND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_send2
-- ----------------------------

-- ----------------------------
-- Table structure for mst_dict
-- ----------------------------
DROP TABLE IF EXISTS `mst_dict`;
CREATE TABLE `mst_dict` (
  `ID` varchar(40) NOT NULL,
  `CODE` varchar(40) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `STATUS` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mst_dict
-- ----------------------------
INSERT INTO `mst_dict` VALUES ('1', 'goodCategory', '物品分类', '1');
INSERT INTO `mst_dict` VALUES ('2', 'express', '快递', '1');
INSERT INTO `mst_dict` VALUES ('3', 'water', '水', '0');
INSERT INTO `mst_dict` VALUES ('4', 'tea', '茶', '0');
INSERT INTO `mst_dict` VALUES ('5', 'orange', '橘子', '1');
INSERT INTO `mst_dict` VALUES ('6', 'apple', '苹果', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(40) NOT NULL,
  `PASSWORD` varchar(40) NOT NULL,
  `REAL_NAME` varchar(40) NOT NULL,
  `MAIL` varchar(40) NOT NULL,
  `PHONE_NUMBER` varchar(40) DEFAULT NULL,
  `CREATE_BY` varchar(40) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_BY` varchar(40) NOT NULL,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
