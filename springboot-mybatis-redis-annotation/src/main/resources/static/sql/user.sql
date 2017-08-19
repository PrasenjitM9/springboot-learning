/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : springbootdb

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-08-19 12:55:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('246ccb0278c44d15839206a11509f517', 'redis测试', '20', '2017-08-19');
INSERT INTO `user` VALUES ('4b28bc0f06384aa49774a86a2fda083b', '22222222222', '123', '2017-08-10');
INSERT INTO `user` VALUES ('894128409735712769', '王健', '12', '2017-08-06');
INSERT INTO `user` VALUES ('894129427441635330', '李灯明', '12', '2017-08-07');
INSERT INTO `user` VALUES ('8a9e7f605ef545919ccb986ed040488a', '李灯明2', '12', '2017-08-07');
