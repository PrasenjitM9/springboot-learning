/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : bocon_attendance

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2019-04-01 14:55:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `card_data`
-- ----------------------------
DROP TABLE IF EXISTS `card_data`;
CREATE TABLE `card_data` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '唯一标识',
  `user_id` varchar(36) NOT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `card_time` datetime DEFAULT NULL COMMENT '打卡时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card_data
-- ----------------------------
INSERT INTO `card_data` VALUES ('7861614492b34c9d8f7601a2480f12e3', '24', '王健', '2019-03-12 13:49:52');
INSERT INTO `card_data` VALUES ('fc9fb9861f7d4124819224c0a6a1f8b3', '24', '王健', '2019-03-12 08:39:41');

-- ----------------------------
-- Table structure for `emp`
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1', '李成', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('13', '王海霞', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('14', '杨继邦', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('15', '王美红', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('16', '邓石顺', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('2', '黄超明', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('21', '罗旅', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('22', '卢仕喜', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('23', '曾龙平', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('24', '王健', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('26', '康正华', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('27', '黄华斌', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('30', '陈振友', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('31', '黄健林', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('32', '梁财', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('35', '赖叶华', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('36', '吴泽光', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('37', '陈汝卿', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('39', '高翔', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('4', '李灯明', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('40', '成建强', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('41', '李玉山', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('44', '凌雄辉', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('45', '吴强生', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('46', '廖林', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('47', '谭承海', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('48', '陈诗祺', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('49', '赵卓建', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('5', '何富', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('50', '邵学平', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('56', '张小凡', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('57', '罗雷君', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('59', '马佳炫', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('6', '林德华', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('62', '康定香', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('63', '陈少燕', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('64', '刘超', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('65', '刘长和', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('66', '谢翔宇', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('67', '黄宏江', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('68', '赵利萍', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('69', '罗金慧', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('70', '龙新娣', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('71', '聂培荣', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('72', '梁嘉豪', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('73', '刘远征', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('74', '董淑成', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('75', '赖小科', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('76', '罗燕财', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('77', '张其耀', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('78', '黄汶轩', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('79', '谭建琼', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('80', '麦锦辉', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('81', '范文鸳', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('82', '梁和平', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('83', '杨颖茹', '2019-04-01 14:13:43', null);
INSERT INTO `emp` VALUES ('9', '陈嘉辉', '2019-04-01 14:13:43', null);

-- ----------------------------
-- Table structure for `sys_all_holiday`
-- ----------------------------
DROP TABLE IF EXISTS `sys_all_holiday`;
CREATE TABLE `sys_all_holiday` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `holiday_date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_all_holiday
-- ----------------------------
INSERT INTO `sys_all_holiday` VALUES ('0022593b42c44028824861e6b2cb62ea', '周末', '2018-09-08');
INSERT INTO `sys_all_holiday` VALUES ('01949889c2c1493f9da2d36a4eb4f337', '周末', '2018-08-19');
INSERT INTO `sys_all_holiday` VALUES ('024d1540a55e4c0997e834d16be21c61', '劳动节', '2017-05-01');
INSERT INTO `sys_all_holiday` VALUES ('025da30ff4a148beb4406ab01d4e1084', '周末', '2018-07-14');
INSERT INTO `sys_all_holiday` VALUES ('02ed6d74d8ec4efa963fbefdc01e5c61', '周末', '2019-04-07');
INSERT INTO `sys_all_holiday` VALUES ('03128a32a7254d1abd38c08f4b3b130b', '周末', '2017-07-02');
INSERT INTO `sys_all_holiday` VALUES ('0326ae01522348fe8232e47704a9c470', '周末', '2017-10-28');
INSERT INTO `sys_all_holiday` VALUES ('04d7c09a3d5246d0b966de566311844b', '周末', '2017-11-05');
INSERT INTO `sys_all_holiday` VALUES ('04e0a45281d34d6db7329fb47e0e3391', '周末', '2018-12-15');
INSERT INTO `sys_all_holiday` VALUES ('05645d776b904bf29e57bb3e93f9f6a5', '周末', '2019-02-17');
INSERT INTO `sys_all_holiday` VALUES ('05ff42c5cd8440619ea5dd84df1f5a5a', '周末', '2018-11-24');
INSERT INTO `sys_all_holiday` VALUES ('079fa2d46e594d9bb695a76364705e81', '周末', '2017-11-26');
INSERT INTO `sys_all_holiday` VALUES ('088dce47ef1f47f1976fba0cac317eaf', '春节', '2018-02-21');
INSERT INTO `sys_all_holiday` VALUES ('08a89e6683914097b56138600a982022', '周末', '2018-12-23');
INSERT INTO `sys_all_holiday` VALUES ('097d63db186a482789f40e6f0d37dfbb', '周末', '2019-04-20');
INSERT INTO `sys_all_holiday` VALUES ('098e0b49487c41679b5b0e68c9d0e712', '周末', '2018-01-27');
INSERT INTO `sys_all_holiday` VALUES ('0a4f41a798464e88b848c82f8650fa6c', '春节', '2018-02-13');
INSERT INTO `sys_all_holiday` VALUES ('0a682ed69b7d4c2ea4c2a1445fe41fb9', '周末', '2019-06-15');
INSERT INTO `sys_all_holiday` VALUES ('0ab6d71f133b467b8ca3c29c69c017eb', '周末', '2017-08-27');
INSERT INTO `sys_all_holiday` VALUES ('0b22c49ebfff4220b95bf9a5824d88d3', '周末', '2019-05-12');
INSERT INTO `sys_all_holiday` VALUES ('0b58dc8e130f45f1b6080c3f587296e5', '周末', '2019-01-20');
INSERT INTO `sys_all_holiday` VALUES ('0b89bbc8ca4045d08dcb221f26db4546', '周末', '2019-02-10');
INSERT INTO `sys_all_holiday` VALUES ('0bcdff0066114f5d8374f366a996c1b2', '周末', '2017-12-09');
INSERT INTO `sys_all_holiday` VALUES ('0d57da76ab99464ebd0ceea30473415d', '国庆节', '2017-10-05');
INSERT INTO `sys_all_holiday` VALUES ('0dd7964352064f3b85618f3167ca6485', '周末', '2017-07-09');
INSERT INTO `sys_all_holiday` VALUES ('0f7a7e9d19b64becae9938c0de96a413', '周末', '2018-01-28');
INSERT INTO `sys_all_holiday` VALUES ('0fc43ad38cf943c7a4b4b982b06ac29e', '周末', '2017-10-29');
INSERT INTO `sys_all_holiday` VALUES ('10457a55d3f14a8d8a7a38539bb8e29a', '周末', '2017-09-10');
INSERT INTO `sys_all_holiday` VALUES ('11e15d446c3b4219ac00524ba4a9ceb7', '劳动节', '2019-05-03');
INSERT INTO `sys_all_holiday` VALUES ('121218ef56ef47a1bf1d58b6a0b121b9', '周末', '2019-11-02');
INSERT INTO `sys_all_holiday` VALUES ('12248d39d4304d29af49635a81921ec6', '周末', '2017-11-04');
INSERT INTO `sys_all_holiday` VALUES ('127dde2cfb764c279e2f7c06d37bdf6b', '周末', '2017-05-28');
INSERT INTO `sys_all_holiday` VALUES ('13c69af606cd4ebd9e0f92c9cfbaca84', '周末', '2017-07-02');
INSERT INTO `sys_all_holiday` VALUES ('14ad716132204abf9d419fdf13c8e28c', '周末', '2018-02-03');
INSERT INTO `sys_all_holiday` VALUES ('154410b9f62c422082c0547f08d8d0d5', '周末', '2017-07-16');
INSERT INTO `sys_all_holiday` VALUES ('1584c0f5f8bf4137ae8f8d6eac127b53', '周末', '2019-07-27');
INSERT INTO `sys_all_holiday` VALUES ('172c2e946c9f4f60a1a76f30393b2e31', '周末', '2019-08-17');
INSERT INTO `sys_all_holiday` VALUES ('179be27e19374cfbbaa3356747e0ea68', '元旦节', '2018-01-01');
INSERT INTO `sys_all_holiday` VALUES ('17f5abc10e2f11e9b009e8039a3502a2', '元旦节', '2018-12-31');
INSERT INTO `sys_all_holiday` VALUES ('18059d7954744b489188abce849a32de', '周末', '2018-02-17');
INSERT INTO `sys_all_holiday` VALUES ('1871f086a5c646c9b27cf886da64bf89', '周末', '2017-10-21');
INSERT INTO `sys_all_holiday` VALUES ('189e3a69ee9f446faf2ca6c6c4383555', '周末', '2017-06-18');
INSERT INTO `sys_all_holiday` VALUES ('1b657e8317b7451d9f9c73228e457f38', '周末', '2017-12-24');
INSERT INTO `sys_all_holiday` VALUES ('1c244cbf84a240f786e7fb62d219ab4b', '周末', '2017-08-20');
INSERT INTO `sys_all_holiday` VALUES ('1d94a43b245b40fb9506567ef9c9cd61', '周末', '2017-06-25');
INSERT INTO `sys_all_holiday` VALUES ('1f56dfd8170b4a60b42698e2e6e6c37b', '周末', '2018-06-30');
INSERT INTO `sys_all_holiday` VALUES ('1f59496c3b8a4498842fa0e0e162b6f8', '端午节', '2017-05-29');
INSERT INTO `sys_all_holiday` VALUES ('1f625a04edc747f0a70cb1a262a597ba', '国庆节', '2019-10-01');
INSERT INTO `sys_all_holiday` VALUES ('20a0c177fb9441738596d3a42ab76ed6', '周末', '2018-08-05');
INSERT INTO `sys_all_holiday` VALUES ('21735d5de17c45a193cd0bdfc1acf655', '周末', '2019-11-09');
INSERT INTO `sys_all_holiday` VALUES ('21983cc1131b442ba8cfa396db34d2d8', '周末', '2017-09-30');
INSERT INTO `sys_all_holiday` VALUES ('21bb752d52df423985afd8aa139a1ecb', '周末', '2019-01-19');
INSERT INTO `sys_all_holiday` VALUES ('22c3d00b6fe241a4af02373e8e986592', '周末', '2019-12-28');
INSERT INTO `sys_all_holiday` VALUES ('23e8cc34339444bab15f8718a5fd5e82', '周末', '2018-09-01');
INSERT INTO `sys_all_holiday` VALUES ('242c7b8f5dc64789b6237aaefda1798c', '周末', '2017-12-09');
INSERT INTO `sys_all_holiday` VALUES ('25ec6de610cc49f8b9f70596d6c3cd19', '周末', '2017-12-24');
INSERT INTO `sys_all_holiday` VALUES ('280bab6d70034f1baa78cc30d753c314', '周末', '2017-10-07');
INSERT INTO `sys_all_holiday` VALUES ('283fa51f0c3c443690d5ea74215fc20d', '周末', '2018-09-22');
INSERT INTO `sys_all_holiday` VALUES ('287b2dd491654c71af6c6997712b2240', '周末', '2018-06-02');
INSERT INTO `sys_all_holiday` VALUES ('287d769203e24deea3f54019ca3ef743', '周末', '2019-11-23');
INSERT INTO `sys_all_holiday` VALUES ('28ce99dd674e4759a5a59e3be63f26af', '周末', '2018-06-17');
INSERT INTO `sys_all_holiday` VALUES ('2964fa9693e6456d841473bc966fad73', '周末', '2019-02-23');
INSERT INTO `sys_all_holiday` VALUES ('29a9d55083b146b89e5aba7b11c6ff40', '周末', '2017-09-24');
INSERT INTO `sys_all_holiday` VALUES ('2abcd6eb334142238b9e78a1ed8f0efd', '国庆节', '2017-10-03');
INSERT INTO `sys_all_holiday` VALUES ('2b98db5b44a54e9d9da2bca3d2f1b27a', '周末', '2019-11-24');
INSERT INTO `sys_all_holiday` VALUES ('2c5a280dc7db470194b7579663274333', '周末', '2019-08-31');
INSERT INTO `sys_all_holiday` VALUES ('2c820ff740054238956dec22a511842d', '周末', '2019-08-03');
INSERT INTO `sys_all_holiday` VALUES ('2cb947fafe5141eba2de204dd7fd1628', '中秋节', '2018-09-24');
INSERT INTO `sys_all_holiday` VALUES ('2d13d1fde67d4af69aafa13d1207e6e7', '周末', '2017-12-16');
INSERT INTO `sys_all_holiday` VALUES ('2d436c68ed4d469fbda6302cc3783706', '周末', '2017-08-13');
INSERT INTO `sys_all_holiday` VALUES ('2e3a4c5132154d4aab4eb1a43d0d075b', '周末', '2017-05-13');
INSERT INTO `sys_all_holiday` VALUES ('2e653d2df52f49ffae379beb2020c8ab', '周末', '2019-07-13');
INSERT INTO `sys_all_holiday` VALUES ('2e680fe448944f15bca314091281c051', '周末', '2019-02-24');
INSERT INTO `sys_all_holiday` VALUES ('2f668bcb24d54a31abcbc896de70df5a', '周末', '2017-05-07');
INSERT INTO `sys_all_holiday` VALUES ('30d935a260b74a47ade735d0e14e4d50', '周末', '2017-06-03');
INSERT INTO `sys_all_holiday` VALUES ('334972a0c9e341589705935731a76206', '春节', '2018-02-20');
INSERT INTO `sys_all_holiday` VALUES ('33508e51ae2e4e5bbb4c01c868bb4621', '周末', '2018-02-18');
INSERT INTO `sys_all_holiday` VALUES ('34121d9e09254460805d0a691f6d6fd2', '清明节', '2019-04-05');
INSERT INTO `sys_all_holiday` VALUES ('3506f51f33334138a422f141165dae5c', '周末', '2019-05-04');
INSERT INTO `sys_all_holiday` VALUES ('35b50eb729b64495b291259bf2fa4d3c', '周末', '2019-06-08');
INSERT INTO `sys_all_holiday` VALUES ('364df47b4cfd44bdb04d2d3306109fef', '周末', '2019-04-06');
INSERT INTO `sys_all_holiday` VALUES ('378d7442f94b438990527355a5e42085', '周末', '2017-12-16');
INSERT INTO `sys_all_holiday` VALUES ('37d4771a5666420fac8f29519eb7129e', '周末', '2017-09-03');
INSERT INTO `sys_all_holiday` VALUES ('38a88b8ef1094c8cbd970343f22ecaf7', '周末', '2017-10-15');
INSERT INTO `sys_all_holiday` VALUES ('3932f93795bc4b53b02e7b542073f2a2', '周末', '2017-11-19');
INSERT INTO `sys_all_holiday` VALUES ('397979fca8234ebcb1bd5c198fb9d4e7', '周末', '2017-07-22');
INSERT INTO `sys_all_holiday` VALUES ('39c3aecf105a4b13a69cccb4a8f88497', '周末', '2018-09-23');
INSERT INTO `sys_all_holiday` VALUES ('3a244cc9007b4ee6ad65a6d0bb3acc8a', '春节', '2019-02-05');
INSERT INTO `sys_all_holiday` VALUES ('3c864a66dca64b9f855500a60a6dd9fb', '周末', '2018-06-24');
INSERT INTO `sys_all_holiday` VALUES ('3cdad701e0404fbb9507c3ca4d95064f', '周末', '2018-07-07');
INSERT INTO `sys_all_holiday` VALUES ('3cebfc402fb8445fb4dd0a82ed129dee', '周末', '2019-04-27');
INSERT INTO `sys_all_holiday` VALUES ('3e08b936e2364afaa9fa6d3f9a468f06', '周末', '2017-05-14');
INSERT INTO `sys_all_holiday` VALUES ('3e5a41a54b184d4cab394749b6cd3d2e', '周末', '2018-05-27');
INSERT INTO `sys_all_holiday` VALUES ('3f182a36ebac49b493eeeb82e89d8268', '周末', '2018-03-03');
INSERT INTO `sys_all_holiday` VALUES ('404bf19c18fb43b589691b9958d7b19b', '周末', '2019-08-10');
INSERT INTO `sys_all_holiday` VALUES ('40e2d373c4d24e11ab746570df99db75', '周末', '2018-02-10');
INSERT INTO `sys_all_holiday` VALUES ('40e91c9bad2149c7b262c89be0825c88', '周末', '2017-11-12');
INSERT INTO `sys_all_holiday` VALUES ('40f083afd1844abeb3dc5b3e2c9f07ef', '周末', '2017-09-09');
INSERT INTO `sys_all_holiday` VALUES ('413ad8e151414ce3a2b1278838c81d0e', '周末', '2017-09-16');
INSERT INTO `sys_all_holiday` VALUES ('41a69844a57746e3a0826d299bf81ed8', '周末', '2017-08-06');
INSERT INTO `sys_all_holiday` VALUES ('41b2b5a35eae4ef6ad386e189f58fdb5', '周末', '2017-05-07');
INSERT INTO `sys_all_holiday` VALUES ('421339a771fb4dcd84a5cfba5ce24831', '周末', '2018-11-03');
INSERT INTO `sys_all_holiday` VALUES ('42f26fb36c2a4c3fa965cb4a9be1caed', '周末', '2018-01-14');
INSERT INTO `sys_all_holiday` VALUES ('4324df2c825a43d89249ac262115c8c2', '元旦节', '2019-01-01');
INSERT INTO `sys_all_holiday` VALUES ('433851bc33504d2abccf8ebb09b2b324', '周末', '2018-07-28');
INSERT INTO `sys_all_holiday` VALUES ('434c1e33cbc246fa9defc1c385b9e4fe', '国庆节', '2018-10-05');
INSERT INTO `sys_all_holiday` VALUES ('4472a0fe24d04bd0b7b4be252ef282f5', '周末', '2017-10-01');
INSERT INTO `sys_all_holiday` VALUES ('44b1ece8e6f64c44a0f4faa08ad2d36a', '周末', '2019-06-22');
INSERT INTO `sys_all_holiday` VALUES ('452da93d3cbd410c86803b45f11cbc95', '周末', '2017-07-22');
INSERT INTO `sys_all_holiday` VALUES ('4597442cfbe744f0bfe4be1f987d582f', '周末', '2017-12-17');
INSERT INTO `sys_all_holiday` VALUES ('46d78bd60bd84fc38de3c2c25132d817', '周末', '2019-11-30');
INSERT INTO `sys_all_holiday` VALUES ('4815b3ea8aa64e7cb8d846692db2a8b9', '周末', '2017-05-20');
INSERT INTO `sys_all_holiday` VALUES ('4915a6303868490288d0a708b42ae3ac', '周末', '2017-07-15');
INSERT INTO `sys_all_holiday` VALUES ('49c8267edec44224a46420a39be1f633', '周末', '2018-01-13');
INSERT INTO `sys_all_holiday` VALUES ('49e8991877f5464ebfc0f630fe50ef1a', '周末', '2018-11-11');
INSERT INTO `sys_all_holiday` VALUES ('4aefcc3facb14fbf9212082a381ae013', '周末', '2019-03-24');
INSERT INTO `sys_all_holiday` VALUES ('4b7f47abf6bc4f768702334a815fbbfe', '周末', '2018-08-11');
INSERT INTO `sys_all_holiday` VALUES ('4bb9ae88117a44f4bb9a7745fe0c4d87', '周末', '2018-10-28');
INSERT INTO `sys_all_holiday` VALUES ('4c008526584a4008b992dc6c29e73699', '周末', '2018-04-01');
INSERT INTO `sys_all_holiday` VALUES ('4cf183b781704b1795f8bb279e8bdff3', '周末', '2018-06-10');
INSERT INTO `sys_all_holiday` VALUES ('4d2c56c2241a4133b2235a25eb1c932d', '周末', '2017-09-10');
INSERT INTO `sys_all_holiday` VALUES ('4d6916667750464f9e7dd619fd8e3022', '周末', '2017-08-20');
INSERT INTO `sys_all_holiday` VALUES ('4db26a004fbf421e970615d1092a27a5', '周末', '2019-02-03');
INSERT INTO `sys_all_holiday` VALUES ('4ea341b005ad4d0aba37bce28b8936ff', '周末', '2017-10-22');
INSERT INTO `sys_all_holiday` VALUES ('4f91cfa753864c8495cc64a5462b5566', '周末', '2017-05-06');
INSERT INTO `sys_all_holiday` VALUES ('4facfdea47f94daa9add905ffb75682b', '周末', '2017-06-18');
INSERT INTO `sys_all_holiday` VALUES ('5049c5d776bc4f9595beab11ba72561a', '周末', '2017-11-25');
INSERT INTO `sys_all_holiday` VALUES ('5126ded2fe954010b26bf67137c85f18', '周末', '2018-01-21');
INSERT INTO `sys_all_holiday` VALUES ('5137bc2464624082a67d348ae95ba4dc', '周末', '2017-06-10');
INSERT INTO `sys_all_holiday` VALUES ('51473854caa34f9292c969021645ab0b', '周末', '2017-06-17');
INSERT INTO `sys_all_holiday` VALUES ('5183ab660cdc499bafa1b4fcaac545ab', '春节', '2018-02-22');
INSERT INTO `sys_all_holiday` VALUES ('52e0a8164ffd4ad98c020f4a1bc78976', '周末', '2017-11-12');
INSERT INTO `sys_all_holiday` VALUES ('53aa06e7c3e74954b66fa1f401cea525', '春节', '2019-02-04');
INSERT INTO `sys_all_holiday` VALUES ('5421ae60edf744f8879e84c105b4ae50', '周末', '2019-05-18');
INSERT INTO `sys_all_holiday` VALUES ('563cd9587d664b49ab4e5e758ce52d12', '周末', '2017-07-29');
INSERT INTO `sys_all_holiday` VALUES ('571186a5949147b690a3cd6676bc06f3', '周末', '2018-05-20');
INSERT INTO `sys_all_holiday` VALUES ('58b2f447b6974299a4f8292052a31496', '周末', '2018-01-20');
INSERT INTO `sys_all_holiday` VALUES ('5986deb7ae6745ebb34f039336f63567', '周末', '2018-11-18');
INSERT INTO `sys_all_holiday` VALUES ('59d19c9a0a0a4ee4b73eda4a36b3575d', '周末', '2017-12-23');
INSERT INTO `sys_all_holiday` VALUES ('5b7e484f5e2e49eebfb5a98c274d65e3', '周末', '2019-03-02');
INSERT INTO `sys_all_holiday` VALUES ('5de92932bbf14f638f56f3c483be2359', '周末', '2018-12-16');
INSERT INTO `sys_all_holiday` VALUES ('5e005690eb27481c9ddf652dea5bb624', '周末', '2018-11-10');
INSERT INTO `sys_all_holiday` VALUES ('5e45db4f91b243629819ff290e446fc5', '周末', '2018-04-14');
INSERT INTO `sys_all_holiday` VALUES ('5e5d4293840e4ded9d581542ef2342c5', '周末', '2018-09-02');
INSERT INTO `sys_all_holiday` VALUES ('5e6768c83bdd4ba5993affbe7ae7c035', '周末', '2017-11-05');
INSERT INTO `sys_all_holiday` VALUES ('5f7f2d0d880c4cc7a7ee1585eef21737', '周末', '2019-07-28');
INSERT INTO `sys_all_holiday` VALUES ('606f95f98a6a408db00ec2a7e64ce593', '周末', '2018-04-07');
INSERT INTO `sys_all_holiday` VALUES ('6108e2ef16f44865a1b6ef42eebddd64', '周末', '2017-12-30');
INSERT INTO `sys_all_holiday` VALUES ('61267af5ba9f4187bfde56ff5e3228d4', '中秋节', '2019-09-13');
INSERT INTO `sys_all_holiday` VALUES ('617c1c81f1934adead15a1d0ed769ecd', '周末', '2017-09-09');
INSERT INTO `sys_all_holiday` VALUES ('6192c2f100a2459290e8d98a0586c91c', '周末', '2017-08-26');
INSERT INTO `sys_all_holiday` VALUES ('6195d3c0b1ee4aa0a55624806f437c21', '周末', '2019-02-16');
INSERT INTO `sys_all_holiday` VALUES ('61a6f292999349658084d826306c7357', '周末', '2017-09-23');
INSERT INTO `sys_all_holiday` VALUES ('628d0bd283a245178769967623834589', '周末', '2018-07-15');
INSERT INTO `sys_all_holiday` VALUES ('631d38c8f0e74e9cabab5a21728137b2', '周末', '2017-09-02');
INSERT INTO `sys_all_holiday` VALUES ('635dfa01ce334226b67e179b959c72ae', '周末', '2017-05-14');
INSERT INTO `sys_all_holiday` VALUES ('636eb08589264d38afc3b17ebc78cc8d', '周末', '2017-06-25');
INSERT INTO `sys_all_holiday` VALUES ('6383c37f2d40426bbc210c77c917cb68', '周末', '2018-07-21');
INSERT INTO `sys_all_holiday` VALUES ('657212f592da481fbd4be16625ffcf58', '周末', '2017-12-17');
INSERT INTO `sys_all_holiday` VALUES ('65cc5ebc6e594070abb7e06639b952d9', '周末', '2019-10-05');
INSERT INTO `sys_all_holiday` VALUES ('65dfd6a81ad1499e907c3caa54272b60', '周末', '2019-12-22');
INSERT INTO `sys_all_holiday` VALUES ('65e7e6d97db54f9789c92a5d2e66401d', '国庆节', '2018-10-03');
INSERT INTO `sys_all_holiday` VALUES ('661ef1eb548046d8a337b0a5f6ffda90', '周末', '2019-11-10');
INSERT INTO `sys_all_holiday` VALUES ('6699779c58464c298ab1f904646bc48e', '周末', '2017-06-03');
INSERT INTO `sys_all_holiday` VALUES ('66d4c914b0374596a0355db77db6b7f3', '周末', '2019-03-23');
INSERT INTO `sys_all_holiday` VALUES ('672e6b2c838a4746b7ec40fdbbffc916', '春节', '2019-02-08');
INSERT INTO `sys_all_holiday` VALUES ('68689b1036484dc8bc1ef560865a1e98', '周末', '2017-12-03');
INSERT INTO `sys_all_holiday` VALUES ('6914f8e08edb4bd8a26f9910f18d0576', '周末', '2018-10-20');
INSERT INTO `sys_all_holiday` VALUES ('6958471123e64144a5a04d52a37d50d9', '周末', '2019-01-13');
INSERT INTO `sys_all_holiday` VALUES ('69d92b27f7cf42e5b4181c152500f24c', '春节', '2019-02-07');
INSERT INTO `sys_all_holiday` VALUES ('6a24209a61c24a61a205111cd3453496', '周末', '2017-10-15');
INSERT INTO `sys_all_holiday` VALUES ('6a42de334a73483783dc1c2e9dd0f85b', '周末', '2019-12-15');
INSERT INTO `sys_all_holiday` VALUES ('6a9bf9576d6c48a2990086b37f966921', '周末', '2018-10-14');
INSERT INTO `sys_all_holiday` VALUES ('6aa14f9f10754629bf0cab7b7d56093f', '周末', '2019-09-07');
INSERT INTO `sys_all_holiday` VALUES ('6c2cf1e4a1434484a4739e24e5ad0b74', '周末', '2017-12-02');
INSERT INTO `sys_all_holiday` VALUES ('6d24cbe908414d7da6adb6bde370f1fd', '周末', '2017-08-12');
INSERT INTO `sys_all_holiday` VALUES ('6df1fbe1588e4ea9aab7cca09bf9e51f', '周末', '2018-08-18');
INSERT INTO `sys_all_holiday` VALUES ('6f22b24105194ab4a5399fa1ea8c511f', '周末', '2017-05-28');
INSERT INTO `sys_all_holiday` VALUES ('710cbd33952541988e8fbe05a9bc8a64', '周末', '2018-11-17');
INSERT INTO `sys_all_holiday` VALUES ('71dc508543954ceb8363d4e41b5f49eb', '周末', '2019-03-30');
INSERT INTO `sys_all_holiday` VALUES ('7307299a66d44c56a4bb5571f20f2bc2', '周末', '2017-09-23');
INSERT INTO `sys_all_holiday` VALUES ('73e9b899297b4bc1b4a697dfa2f8ae59', '周末', '2019-10-06');
INSERT INTO `sys_all_holiday` VALUES ('74c7cb92736e41b98eb049cb2c3da7dc', '周末', '2019-06-23');
INSERT INTO `sys_all_holiday` VALUES ('74e886cd70c342ffaadfef75401f6af6', '周末', '2019-08-18');
INSERT INTO `sys_all_holiday` VALUES ('75c1c7968df649f1a4cd01a7cbf393ca', '周末', '2018-09-16');
INSERT INTO `sys_all_holiday` VALUES ('79337d9aab3d4e2887b12e24488a4fe8', '周末', '2019-04-14');
INSERT INTO `sys_all_holiday` VALUES ('79e1ef717e514e1a95e198c77816eea5', '周末', '2018-03-25');
INSERT INTO `sys_all_holiday` VALUES ('7a3db14c4c644f25955fc7fab07288a8', '国庆节', '2019-10-07');
INSERT INTO `sys_all_holiday` VALUES ('7c32c4bb07a343f6b0050acfdc5dd442', '周末', '2019-03-17');
INSERT INTO `sys_all_holiday` VALUES ('7c6bddd76304439bbb2d0488a5f75426', '周末', '2019-06-02');
INSERT INTO `sys_all_holiday` VALUES ('7d5384a54c5d422d80089e5147c720fb', '周末', '2019-10-26');
INSERT INTO `sys_all_holiday` VALUES ('7db168adbcf54ca2b18ce76540fc6d50', '周末', '2019-04-21');
INSERT INTO `sys_all_holiday` VALUES ('7de75c9de6f24595abff304e599285d8', '周末', '2019-09-28');
INSERT INTO `sys_all_holiday` VALUES ('7e5567eb1339405e91d30f8f4841f96b', '周末', '2017-08-05');
INSERT INTO `sys_all_holiday` VALUES ('7e60e6906ba046539bed1c3b81399993', '周末', '2017-07-08');
INSERT INTO `sys_all_holiday` VALUES ('7ed08ec07af9439a98cecdbeb91c6782', '劳动节', '2019-05-01');
INSERT INTO `sys_all_holiday` VALUES ('807f9680ca624d7c8c5f97b5341294f9', '周末', '2018-08-25');
INSERT INTO `sys_all_holiday` VALUES ('80bbcdc7aea24f8cb865dc7aeed6e3b0', '周末', '2019-09-22');
INSERT INTO `sys_all_holiday` VALUES ('818d6ad327ec44f4acbca37938bc541e', '周末', '2018-02-04');
INSERT INTO `sys_all_holiday` VALUES ('81d03dbdc666410e92ce620e5b1eb109', '周末', '2019-01-12');
INSERT INTO `sys_all_holiday` VALUES ('832f82b0f1f0410bb0ecdf339db2a519', '端午节', '2017-05-30');
INSERT INTO `sys_all_holiday` VALUES ('83871332196b4346b895cfee155786db', '国庆节', '2019-10-04');
INSERT INTO `sys_all_holiday` VALUES ('83cd1d66887446e6af6cc7178903212e', '劳动节', '2018-05-01');
INSERT INTO `sys_all_holiday` VALUES ('83edce6063634d8886c61a345a8c139c', '周末', '2017-10-07');
INSERT INTO `sys_all_holiday` VALUES ('84705b2155f6456f89587b7533b7d426', '周末', '2019-01-26');
INSERT INTO `sys_all_holiday` VALUES ('849ff3f8ae764c138fa2f07be376499c', '周末', '2018-10-21');
INSERT INTO `sys_all_holiday` VALUES ('850314144695415096f21cb5d70cdc6d', '周末', '2019-10-20');
INSERT INTO `sys_all_holiday` VALUES ('8506069f1e9443b6aadbd2d63a95eae4', '周末', '2019-07-20');
INSERT INTO `sys_all_holiday` VALUES ('85aa0b0d0334423c990acde9bf5e2572', '周末', '2017-10-29');
INSERT INTO `sys_all_holiday` VALUES ('875d74538db548b8b016cb4aba31c4ab', '周末', '2019-03-16');
INSERT INTO `sys_all_holiday` VALUES ('87d069a0a12f4c7999715696262ee1de', '周末', '2019-08-11');
INSERT INTO `sys_all_holiday` VALUES ('87d094a954c74d65be3c43bc4de5c04b', '国庆节', '2019-10-03');
INSERT INTO `sys_all_holiday` VALUES ('883edbe840d34d858008e0ec19853b3e', '周末', '2017-07-16');
INSERT INTO `sys_all_holiday` VALUES ('88b4ab31174f4a198bd42319615c2655', '清明节', '2018-04-05');
INSERT INTO `sys_all_holiday` VALUES ('89b66abdda764b6ca87dfcf8f164276c', '春节', '2018-02-15');
INSERT INTO `sys_all_holiday` VALUES ('8a26394038f546c0b5ff3ee0b8953b2b', '周末', '2017-12-23');
INSERT INTO `sys_all_holiday` VALUES ('8b375b75cce8413e84b52214b97aa748', '周末', '2019-12-01');
INSERT INTO `sys_all_holiday` VALUES ('8ceaef6549664b3fa83ece4acaf3c798', '周末', '2017-06-17');
INSERT INTO `sys_all_holiday` VALUES ('8d49d0151eec418eaeabb90a8d9819af', '周末', '2017-12-31');
INSERT INTO `sys_all_holiday` VALUES ('8ea16ec0d7f841b688df18e59766f8e7', '周末', '2017-06-04');
INSERT INTO `sys_all_holiday` VALUES ('8efc345a1f21416c8ebea076761f0229', '周末', '2019-07-06');
INSERT INTO `sys_all_holiday` VALUES ('8f089716cf6b4be59fff1ed35545b2bc', '周末', '2018-03-18');
INSERT INTO `sys_all_holiday` VALUES ('8fbf73cb320e4831b291f2c5e2fc57fe', '周末', '2017-06-04');
INSERT INTO `sys_all_holiday` VALUES ('923c7926831742208db271103a1bfce4', '周末', '2018-10-06');
INSERT INTO `sys_all_holiday` VALUES ('94473a15ed894e1cac8b7b54278ba62f', '周末', '2019-06-29');
INSERT INTO `sys_all_holiday` VALUES ('9466d6b090ba4a8c8e0217a677f825d1', '周末', '2018-04-15');
INSERT INTO `sys_all_holiday` VALUES ('9481239d7e1b4a7fa3caf73b6facbbc3', '周末', '2017-10-22');
INSERT INTO `sys_all_holiday` VALUES ('94a47892d9f6427f87d1d44dedb3c2f8', '周末', '2019-08-24');
INSERT INTO `sys_all_holiday` VALUES ('958cbc57783b41d69d214c666db15201', '周末', '2018-05-12');
INSERT INTO `sys_all_holiday` VALUES ('979cf2475ee141c0997c7af2bbd03a68', '国庆节', '2019-10-02');
INSERT INTO `sys_all_holiday` VALUES ('97a6222a851943a3b204f66e16e5f0eb', '周末', '2018-05-26');
INSERT INTO `sys_all_holiday` VALUES ('9a9e7a810f2a4b6099198459e299f2a0', '周末', '2017-11-18');
INSERT INTO `sys_all_holiday` VALUES ('9b62b5166a5a457997e4e6895b9e5be6', '周末', '2019-05-26');
INSERT INTO `sys_all_holiday` VALUES ('9cd73fd40ba04bbb92e0f178ad2f65fa', '周末', '2018-07-22');
INSERT INTO `sys_all_holiday` VALUES ('9d2092413c67464f9bf95f91d592d917', '周末', '2017-10-14');
INSERT INTO `sys_all_holiday` VALUES ('9f495aa0ccca47308812896cc5ceeafd', '周末', '2017-10-28');
INSERT INTO `sys_all_holiday` VALUES ('9fbf4277eb2c4b0b846da6d0e6efe53f', '周末', '2017-12-30');
INSERT INTO `sys_all_holiday` VALUES ('a1b8203631b24d67aca4cceee25974d0', '周末', '2019-01-06');
INSERT INTO `sys_all_holiday` VALUES ('a25bb9c47ddf4b1bacb8837e1ac0aed2', '周末', '2017-12-31');
INSERT INTO `sys_all_holiday` VALUES ('a26fe0ff72ca46e7bfb6375e4c5717ab', '周末', '2019-02-02');
INSERT INTO `sys_all_holiday` VALUES ('a3084e44098344a48879f04b2099cd00', '春节', '2019-02-06');
INSERT INTO `sys_all_holiday` VALUES ('a363d45cf0bb4998906efe9c2395fd42', '周末', '2019-05-25');
INSERT INTO `sys_all_holiday` VALUES ('a3fcdbee0fe7407abfa52f1cfbcecd4c', '周末', '2018-07-01');
INSERT INTO `sys_all_holiday` VALUES ('a40440ad7cc94aefa7a0945ca69ae809', '周末', '2017-07-15');
INSERT INTO `sys_all_holiday` VALUES ('a46cf278de11441092cd01db27d549c3', '周末', '2018-01-06');
INSERT INTO `sys_all_holiday` VALUES ('a46d3cda57a04b9a93a20802e3aff869', '国庆节', '2018-10-04');
INSERT INTO `sys_all_holiday` VALUES ('a5557f37d1674af38ad7e35c2cff960e', '周末', '2018-06-03');
INSERT INTO `sys_all_holiday` VALUES ('a6d4ac7ef2c240de92519f8657b8a5ba', '周末', '2017-11-19');
INSERT INTO `sys_all_holiday` VALUES ('a738da945ff34f1fa639dc7a92c2d11c', '周末', '2017-07-08');
INSERT INTO `sys_all_holiday` VALUES ('a7d82ca7e9534f368256fef47f31bbd2', '周末', '2019-09-15');
INSERT INTO `sys_all_holiday` VALUES ('a94ba99c43d64068955ad46e6cff833b', '周末', '2019-08-25');
INSERT INTO `sys_all_holiday` VALUES ('a97cdd13006c4b96b72ca414387c082a', '周末', '2017-06-11');
INSERT INTO `sys_all_holiday` VALUES ('a9cf3e65052c4b2885de6a4b569a9135', '周末', '2017-09-03');
INSERT INTO `sys_all_holiday` VALUES ('aaa1003d763b426eac8b06ab88ca712c', '周末', '2018-11-04');
INSERT INTO `sys_all_holiday` VALUES ('abcbb7e74c0a4581918f64ac8b27cad0', '周末', '2017-06-24');
INSERT INTO `sys_all_holiday` VALUES ('abece574493541d499b514d353603c25', '周末', '2018-09-09');
INSERT INTO `sys_all_holiday` VALUES ('ac53036214594534b171dc3c26c3925c', '周末', '2019-10-19');
INSERT INTO `sys_all_holiday` VALUES ('acdb68be68bd4463af1b6e8b74aac162', '周末', '2019-09-21');
INSERT INTO `sys_all_holiday` VALUES ('ace476e5149742a6a06ed813b4579424', '周末', '2017-10-21');
INSERT INTO `sys_all_holiday` VALUES ('ad2a305da3e74d78894074b06c156fd6', '清明节', '2018-04-06');
INSERT INTO `sys_all_holiday` VALUES ('ad6809dd9ca341f4b5688b147ca24818', '春节', '2018-02-19');
INSERT INTO `sys_all_holiday` VALUES ('adf6711100aa4571ac78350340e26a6b', '国庆节', '2018-10-02');
INSERT INTO `sys_all_holiday` VALUES ('ae19bc745f2140089ba2375a4ecf6e02', '周末', '2017-08-26');
INSERT INTO `sys_all_holiday` VALUES ('ae39fd7df0624d82a2aa6b39cdba5d14', '周末', '2019-06-30');
INSERT INTO `sys_all_holiday` VALUES ('ae5d845c385648c99f79ef63304e2e72', '周末', '2018-06-09');
INSERT INTO `sys_all_holiday` VALUES ('aedbd7a495704df180069f78abe60cd9', '春节', '2018-02-16');
INSERT INTO `sys_all_holiday` VALUES ('af683ffec5264557bec960b00234c01b', '周末', '2019-11-16');
INSERT INTO `sys_all_holiday` VALUES ('af7bcba40d734c24b3b6144413581983', '周末', '2017-09-17');
INSERT INTO `sys_all_holiday` VALUES ('afc4839a751c40c3bb9130334ccbf490', '端午节', '2019-06-07');
INSERT INTO `sys_all_holiday` VALUES ('afde00c544e740f482c753383dcb63f1', '端午节', '2018-06-18');
INSERT INTO `sys_all_holiday` VALUES ('b02405fb2efb4a4c9bd6f4a8369c4b02', '周末', '2017-12-10');
INSERT INTO `sys_all_holiday` VALUES ('b09a1a507e984dddbf12cb7c1cff19cd', '周末', '2019-07-21');
INSERT INTO `sys_all_holiday` VALUES ('b0d0a7114c0448839e2c0b7bb3233118', '周末', '2017-08-05');
INSERT INTO `sys_all_holiday` VALUES ('b259ecfbcb3b48f1b9e738b5e11ade1b', '周末', '2017-09-30');
INSERT INTO `sys_all_holiday` VALUES ('b315c6b73235400b8b3227effac288bc', '周末', '2018-01-07');
INSERT INTO `sys_all_holiday` VALUES ('b4408c7be05949ecba01a26c9b54e751', '国庆节', '2018-10-01');
INSERT INTO `sys_all_holiday` VALUES ('b5e07f80e7594bc2abf32a1a11dd40a9', '劳动节', '2019-05-02');
INSERT INTO `sys_all_holiday` VALUES ('b60815aac72a4ee0868329a910e51a77', '周末', '2018-02-25');
INSERT INTO `sys_all_holiday` VALUES ('b72f2c14a0dc4cdeb5b802ca8f2fd635', '周末', '2019-06-01');
INSERT INTO `sys_all_holiday` VALUES ('b77baabd603a4645adffe154be676747', '周末', '2017-07-23');
INSERT INTO `sys_all_holiday` VALUES ('b799a1fc067d41b9abf52ee46a334d09', '周末', '2018-05-13');
INSERT INTO `sys_all_holiday` VALUES ('b7d5c4a4ff324fcb9a84a01de918218c', '周末', '2019-01-05');
INSERT INTO `sys_all_holiday` VALUES ('b8c38984cebe42af992d77ab3dad8b90', '周末', '2018-03-17');
INSERT INTO `sys_all_holiday` VALUES ('b91e0d5fb3ba44ebb0a0ef8e65ccebe6', '周末', '2018-05-19');
INSERT INTO `sys_all_holiday` VALUES ('b96b2f6bc32646d59f5bde40c512d18c', '周末', '2018-12-08');
INSERT INTO `sys_all_holiday` VALUES ('b9aaf8c919c04cf89138e0f9e49e4b4a', '周末', '2017-11-26');
INSERT INTO `sys_all_holiday` VALUES ('b9c0a9f5bbb24d7990f1dae72579b351', '周末', '2017-08-13');
INSERT INTO `sys_all_holiday` VALUES ('ba0a9ec32c614a89bcdeae7497e395bc', '周末', '2017-08-12');
INSERT INTO `sys_all_holiday` VALUES ('bad8f1a5806e45808b8b698adafbd2e0', '周末', '2018-08-04');
INSERT INTO `sys_all_holiday` VALUES ('bbc486ec641342b0a027c2cedc35df5b', '周末', '2019-02-09');
INSERT INTO `sys_all_holiday` VALUES ('bbe0e8284cde4b83bfc3f626d97b6dc1', '周末', '2019-03-31');
INSERT INTO `sys_all_holiday` VALUES ('bbf17e9779dd4546b6fbdd215a9c5e0e', '周末', '2019-12-08');
INSERT INTO `sys_all_holiday` VALUES ('bd824bd9200b4d83aab69a61a124f450', '周末', '2017-05-20');
INSERT INTO `sys_all_holiday` VALUES ('be19de34bef7461499b91d08882d50f3', '周末', '2017-10-14');
INSERT INTO `sys_all_holiday` VALUES ('be2dc80dae324acdbf8d49aa098ca975', '周末', '2019-05-19');
INSERT INTO `sys_all_holiday` VALUES ('bee56be0463c4848ae69214d2b3c0df9', '周末', '2017-09-24');
INSERT INTO `sys_all_holiday` VALUES ('bf8e37e65e40433b88b6dd57cb004a2e', '周末', '2019-05-11');
INSERT INTO `sys_all_holiday` VALUES ('c0c5f1fe05f74a36a040569d8af64b71', '周末', '2018-12-30');
INSERT INTO `sys_all_holiday` VALUES ('c1cab1e6d3d247519a01870f6991b80f', '周末', '2018-03-24');
INSERT INTO `sys_all_holiday` VALUES ('c27f7277cdc8428386ba89eda4a4efe0', '周末', '2017-10-08');
INSERT INTO `sys_all_holiday` VALUES ('c2df7c155e8b45be9561aca5336e7161', '周末', '2019-06-16');
INSERT INTO `sys_all_holiday` VALUES ('c32c6e7a89c549cea96c828f39889a6f', '周末', '2017-11-11');
INSERT INTO `sys_all_holiday` VALUES ('c3ce8bbf2f7b46c1977dde56c87d197f', '周末', '2018-07-29');
INSERT INTO `sys_all_holiday` VALUES ('c42e52225973473f9764677854242edd', '周末', '2017-07-23');
INSERT INTO `sys_all_holiday` VALUES ('c5387226ecfe48309e6438e49060a6e4', '国庆节', '2017-10-04');
INSERT INTO `sys_all_holiday` VALUES ('c579bbdc26fd4f6bb4c952cf32e3c2fd', '周末', '2018-08-26');
INSERT INTO `sys_all_holiday` VALUES ('c583f986812547dcacee379fc46ea20f', '周末', '2018-07-08');
INSERT INTO `sys_all_holiday` VALUES ('c64032fe0e304d3f994df0bd929f8966', '国庆节', '2017-10-02');
INSERT INTO `sys_all_holiday` VALUES ('c6d89f185ac440a78753dc795444b5a6', '周末', '2019-03-03');
INSERT INTO `sys_all_holiday` VALUES ('c712a563c2924c91a3a045e9d4909656', '周末', '2017-10-08');
INSERT INTO `sys_all_holiday` VALUES ('c76da72c259441ad9ea3bfe541bfe74f', '周末', '2017-07-09');
INSERT INTO `sys_all_holiday` VALUES ('c7ec1265d6b648cbbdf56d25f4cf9bcb', '春节', '2019-02-01');
INSERT INTO `sys_all_holiday` VALUES ('c8110a86ebf84092846be795b989b2a6', '周末', '2017-12-10');
INSERT INTO `sys_all_holiday` VALUES ('c84bdf8fe90d4f719ccddcd9daec2089', '国庆节', '2017-10-06');
INSERT INTO `sys_all_holiday` VALUES ('c8959e99ac044d49af7c6d28dbb4a9e3', '周末', '2019-10-13');
INSERT INTO `sys_all_holiday` VALUES ('c8c670f98bab49c3a91fc32e4216883f', '周末', '2017-09-16');
INSERT INTO `sys_all_holiday` VALUES ('c900f8f4488041faba939c45c48581b5', '周末', '2019-12-29');
INSERT INTO `sys_all_holiday` VALUES ('c95753381de648259a01f8ff7f6ea8a1', '周末', '2018-04-22');
INSERT INTO `sys_all_holiday` VALUES ('ca414dd2f1ef4594a7bd9cf1126a7ac8', '周末', '2018-03-31');
INSERT INTO `sys_all_holiday` VALUES ('ca56d50d833746bb9a46e15f008f740c', '周末', '2018-06-16');
INSERT INTO `sys_all_holiday` VALUES ('cc64dab33ad147dea210bc7946806f3f', '周末', '2017-08-19');
INSERT INTO `sys_all_holiday` VALUES ('cc9e454a987d4c439fb086fd4d2a20b8', '劳动节', '2018-04-30');
INSERT INTO `sys_all_holiday` VALUES ('cdf7cab9b81f4ea3a67f1568210fc6fa', '周末', '2019-06-09');
INSERT INTO `sys_all_holiday` VALUES ('ce9f21899a4640ada45901ec23ea4a62', '周末', '2018-10-27');
INSERT INTO `sys_all_holiday` VALUES ('d066a213737e4d3bba56b7ec05fadccc', '周末', '2019-09-08');
INSERT INTO `sys_all_holiday` VALUES ('d0cd7fce89b140c89defcbcf5aabcd90', '周末', '2017-07-29');
INSERT INTO `sys_all_holiday` VALUES ('d2c0c959b4874bfbb0a6c336289a97a6', '周末', '2018-10-07');
INSERT INTO `sys_all_holiday` VALUES ('d398840e835547f1a1cd5e9d230d518e', '周末', '2017-08-27');
INSERT INTO `sys_all_holiday` VALUES ('d4af77ab77464fa1a0e380c202070ec7', '周末', '2019-09-01');
INSERT INTO `sys_all_holiday` VALUES ('d4ece42df82942f799a92e52833189fd', '周末', '2017-07-01');
INSERT INTO `sys_all_holiday` VALUES ('d51b23be42a14e559c50ceebaf38fe0d', '周末', '2017-05-06');
INSERT INTO `sys_all_holiday` VALUES ('d58498c3cb984c0192caa1faf0aa7e43', '周末', '2017-09-17');
INSERT INTO `sys_all_holiday` VALUES ('d6d29ac7071242768571d8b60c03a00d', '周末', '2017-07-30');
INSERT INTO `sys_all_holiday` VALUES ('d702529ac49a447cb322fabcc46ece48', '周末', '2019-11-17');
INSERT INTO `sys_all_holiday` VALUES ('d741238c45564f17ab1efd092f2a6166', '周末', '2019-12-07');
INSERT INTO `sys_all_holiday` VALUES ('d74ee0fd9eb041b3ba3f1124525beb0f', '周末', '2019-12-14');
INSERT INTO `sys_all_holiday` VALUES ('d796ab80842949dc88fcf3ab6c7787d6', '周末', '2018-12-29');
INSERT INTO `sys_all_holiday` VALUES ('d92893b71b46417482ef7d4c2b7301cd', '周末', '2018-12-01');
INSERT INTO `sys_all_holiday` VALUES ('d92e36e4b577430b9275da19a02b43d2', '周末', '2018-05-05');
INSERT INTO `sys_all_holiday` VALUES ('d9a6276f3db7403fbeb66c6067a2a678', '周末', '2019-08-04');
INSERT INTO `sys_all_holiday` VALUES ('d9a684903343473b9d9508ff094168cd', '周末', '2019-03-10');
INSERT INTO `sys_all_holiday` VALUES ('dc92cb2a566a43c9b94082655d1f1b71', '周末', '2019-04-13');
INSERT INTO `sys_all_holiday` VALUES ('dd28cb5e37354b91a4113c6d09dedd3a', '周末', '2019-07-14');
INSERT INTO `sys_all_holiday` VALUES ('dd3e9071af554c2aa1dc63909ad2aaa8', '周末', '2017-11-25');
INSERT INTO `sys_all_holiday` VALUES ('ddb15945f3b047589e5544b03091a87f', '周末', '2017-06-11');
INSERT INTO `sys_all_holiday` VALUES ('de087f3933ed471182948a33d1287d97', '周末', '2017-07-30');
INSERT INTO `sys_all_holiday` VALUES ('decf3d66f323433997342fdd94a67eff', '周末', '2018-08-12');
INSERT INTO `sys_all_holiday` VALUES ('dfa181ecd1b04022b0127e8c80e819ca', '周末', '2018-09-15');
INSERT INTO `sys_all_holiday` VALUES ('e0b82ac2a3d042549c37ffff74bb2f00', '周末', '2018-12-09');
INSERT INTO `sys_all_holiday` VALUES ('e1bbda8cda0a41d890cd8eeee81774fd', '周末', '2018-03-10');
INSERT INTO `sys_all_holiday` VALUES ('e1f9c908f70d44339ba5113bbf3c7b24', '周末', '2017-09-02');
INSERT INTO `sys_all_holiday` VALUES ('e300ce1114b44f29ae379af431a26a64', '周末', '2018-12-22');
INSERT INTO `sys_all_holiday` VALUES ('e4189ac3238240d5a7fa9544b38cd445', '周末', '2017-05-21');
INSERT INTO `sys_all_holiday` VALUES ('e437ee8c63c3427698ae32d3bdf14c5e', '周末', '2017-05-21');
INSERT INTO `sys_all_holiday` VALUES ('e49c37e89bd345c98e78aa25d51be8ed', '周末', '2017-06-24');
INSERT INTO `sys_all_holiday` VALUES ('e4d610d793c04eeda3df0f69ad83066c', '周末', '2019-09-14');
INSERT INTO `sys_all_holiday` VALUES ('e63f70a33b3c4100859dd1ecbc7670c7', '周末', '2017-11-04');
INSERT INTO `sys_all_holiday` VALUES ('e6bcf362b2ad4077995c64edfa5b5d8a', '周末', '2019-01-27');
INSERT INTO `sys_all_holiday` VALUES ('e73b2f1a4da34046bd2e15464343f0d5', '周末', '2019-10-27');
INSERT INTO `sys_all_holiday` VALUES ('e85f1f271ce64c54b0458fa1f005b33f', '周末', '2019-07-07');
INSERT INTO `sys_all_holiday` VALUES ('e900ef78a9fe4591b111b7eee04bac7f', '周末', '2017-11-11');
INSERT INTO `sys_all_holiday` VALUES ('e92e4b59944c4bebb1462f8674861060', '周末', '2017-08-19');
INSERT INTO `sys_all_holiday` VALUES ('eaa1c2fed2a04a0b960f6cb130a1d9cb', '周末', '2018-05-06');
INSERT INTO `sys_all_holiday` VALUES ('eb0914f84a9140cfa6321909433aee91', '周末', '2018-12-02');
INSERT INTO `sys_all_holiday` VALUES ('eb2ed64d5b97487f83a1f6a05a92036e', '周末', '2017-07-01');
INSERT INTO `sys_all_holiday` VALUES ('eda62accc0f54d08845025c297b10a55', '周末', '2019-12-21');
INSERT INTO `sys_all_holiday` VALUES ('ee18a07b2ef34f84a527290a27325212', '周末', '2017-05-13');
INSERT INTO `sys_all_holiday` VALUES ('ef6f6a467c5a4bef8937fb871efba7fd', '周末', '2018-06-23');
INSERT INTO `sys_all_holiday` VALUES ('ef9f71648d844c2e95841e32d9fd37eb', '周末', '2017-10-01');
INSERT INTO `sys_all_holiday` VALUES ('efe206f63952424f9a5685cf52f82bdf', '周末', '2017-11-18');
INSERT INTO `sys_all_holiday` VALUES ('f132f3a856a640808ab7e3bec30cd145', '周末', '2018-11-25');
INSERT INTO `sys_all_holiday` VALUES ('f19d049cd071498c8b67ce43cc4e85ba', '周末', '2018-10-13');
INSERT INTO `sys_all_holiday` VALUES ('f1c232c9ce2547c3b4782166c87b2955', '周末', '2018-04-21');
INSERT INTO `sys_all_holiday` VALUES ('f50db46094e34d498aed271e3e0a7dd9', '周末', '2018-03-11');
INSERT INTO `sys_all_holiday` VALUES ('f5f7a8717b56411cb05b1953358f1e45', '周末', '2018-04-29');
INSERT INTO `sys_all_holiday` VALUES ('f6fede0cb3114c71bbead1e09a55f928', '周末', '2017-12-03');
INSERT INTO `sys_all_holiday` VALUES ('f709a78e82a643599b7d38918e2a356b', '周末', '2017-08-06');
INSERT INTO `sys_all_holiday` VALUES ('f7616774aade477bbb1d358d415b13d4', '周末', '2019-03-09');
INSERT INTO `sys_all_holiday` VALUES ('f99101c80533434787bc58be2316c824', '周末', '2019-11-03');
INSERT INTO `sys_all_holiday` VALUES ('fca9779041b842fbbfab279b3e74b41d', '周末', '2018-03-04');
INSERT INTO `sys_all_holiday` VALUES ('fe878f90a2a54abaaa6025d491f74646', '春节', '2018-02-14');
INSERT INTO `sys_all_holiday` VALUES ('ffee50ecd7614467bd717c75be48aad6', '周末', '2017-06-10');
INSERT INTO `sys_all_holiday` VALUES ('ffefd57b1f4b4cf0a6c6ffa19236af46', '周末', '2017-12-02');
