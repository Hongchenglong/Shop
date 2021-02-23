/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : oeongshop

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 23/02/2021 15:31:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oeong_cart
-- ----------------------------
DROP TABLE IF EXISTS `oeong_cart`;
CREATE TABLE `oeong_cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_p_filename` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cart_p_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cart_p_price` decimal(10, 2) NULL DEFAULT NULL,
  `cart_quantity` int(11) NULL DEFAULT NULL,
  `cart_p_stock` int(11) NULL DEFAULT NULL,
  `cart_p_id` int(11) NULL DEFAULT NULL,
  `cart_u_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cart_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oeong_cart
-- ----------------------------
INSERT INTO `oeong_cart` VALUES (6, '5acb3627N8191c867.jpg', 'Python编程技术', 120.00, 5, 77, 10, 'zhangsan', 1);
INSERT INTO `oeong_cart` VALUES (7, '99742c91174d3d7a.jpg', 'Java从入门到精通', 68.00, 3, 99, 5, 'zhangsan', 1);
INSERT INTO `oeong_cart` VALUES (8, 'ccc877ab66ae0b5e.jpg', 'Java精彩编程', 106.00, 2, 555, 9, 'zhangsan', 1);
INSERT INTO `oeong_cart` VALUES (9, '5624b582Nbc01af5b.jpg', 'Java编程思想', 109.00, 3, 3223, 4, 'zhangsan', 1);
INSERT INTO `oeong_cart` VALUES (10, '592bf167Naf49f7f6.jpg', '深入理解Java虚拟机', 89.00, 1, 55, 8, 'zhangsan', 1);
INSERT INTO `oeong_cart` VALUES (11, '0bf587e1b7d5de74.jpg', '细说PHP 第四版', 158.00, 2, 3000, 1, 'zhangsan', 1);
INSERT INTO `oeong_cart` VALUES (16, '99742c91174d3d7a.jpg', 'Java从入门到精通', 68.00, 5, 99, 5, 'admin', 1);
INSERT INTO `oeong_cart` VALUES (19, 'php4.jpg', '细说PHP 第四版', 47.00, 4, 3000, 12, 'admin', 1);

-- ----------------------------
-- Table structure for oeong_category
-- ----------------------------
DROP TABLE IF EXISTS `oeong_category`;
CREATE TABLE `oeong_category`  (
  `CATE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CATE_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CATE_PARENT_ID` decimal(10, 0) NOT NULL,
  PRIMARY KEY (`CATE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oeong_category
-- ----------------------------
INSERT INTO `oeong_category` VALUES (12, 'Java图书', 0);
INSERT INTO `oeong_category` VALUES (13, 'Python图书', 0);
INSERT INTO `oeong_category` VALUES (14, 'PHP图书', 0);
INSERT INTO `oeong_category` VALUES (15, 'Go语言图书', 0);
INSERT INTO `oeong_category` VALUES (16, 'Linux图书', 0);
INSERT INTO `oeong_category` VALUES (17, 'JavaSE图书', 12);
INSERT INTO `oeong_category` VALUES (18, 'JAVAEE图书', 12);
INSERT INTO `oeong_category` VALUES (19, 'JavaEE框架', 12);
INSERT INTO `oeong_category` VALUES (20, 'Python基础', 13);
INSERT INTO `oeong_category` VALUES (21, 'Python人工智能', 13);
INSERT INTO `oeong_category` VALUES (22, 'PHP基础', 14);
INSERT INTO `oeong_category` VALUES (23, 'PHP高级', 14);
INSERT INTO `oeong_category` VALUES (24, 'Linux系统', 16);
INSERT INTO `oeong_category` VALUES (25, 'Linux网络服务', 16);
INSERT INTO `oeong_category` VALUES (26, 'Linux运维', 16);
INSERT INTO `oeong_category` VALUES (28, 'Java从入门到放弃', 12);
INSERT INTO `oeong_category` VALUES (29, 'Java从入门到入坟', 12);

-- ----------------------------
-- Table structure for oeong_product
-- ----------------------------
DROP TABLE IF EXISTS `oeong_product`;
CREATE TABLE `oeong_product`  (
  `PRODUCT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PRODUCT_DESCRIPTION` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PRODUCT_PRICE` decimal(10, 2) NOT NULL,
  `PRODUCT_STOCK` decimal(10, 0) NULL DEFAULT NULL,
  `PRODUCT_FID` decimal(10, 0) NULL DEFAULT NULL,
  `PRODUCT_CID` decimal(10, 0) NULL DEFAULT NULL,
  `PRODUCT_FILENAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`PRODUCT_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oeong_product
-- ----------------------------
INSERT INTO `oeong_product` VALUES (1, '细说PHP 第四版', '这本书很好， 是一个不错书', 158.00, 3000, 14, 23, '0bf587e1b7d5de74.jpg');
INSERT INTO `oeong_product` VALUES (2, 'PHP学习手册', '自学可以使用的手册，很全面', 51.00, 21, 14, 22, '592fb45cNac23b876.jpg');
INSERT INTO `oeong_product` VALUES (3, 'PHP项目案例', '通过这本书，可以完成PHP的项目案例', 104.00, 55, 14, 23, '56f63c22Nbb294bc0.jpg');
INSERT INTO `oeong_product` VALUES (4, 'Java编程思想', '这是一个好书呀', 109.00, 3223, 12, 17, '5624b582Nbc01af5b.jpg');
INSERT INTO `oeong_product` VALUES (5, 'Java从入门到精通', '新手使用的好书', 68.00, 99, 12, 17, '99742c91174d3d7a.jpg');
INSERT INTO `oeong_product` VALUES (6, 'Java经典案例', '这本书很好， 在是一个不错书', 88.00, 55, 12, 17, '74a5a5dcd83ecc09.jpg');
INSERT INTO `oeong_product` VALUES (7, 'Java核心技术', '这个书对Java介绍的很全面', 128.00, 120, 12, 17, '57e09a03Nc57334e1.jpg');
INSERT INTO `oeong_product` VALUES (8, '深入理解Java虚拟机', '对新手很有用的书', 89.00, 55, 12, 17, '592bf167Naf49f7f6.jpg');
INSERT INTO `oeong_product` VALUES (9, 'Java精彩编程', '这个例子很多，很全面', 106.00, 555, 12, 17, 'ccc877ab66ae0b5e.jpg');
INSERT INTO `oeong_product` VALUES (10, 'Python编程技术', '这个学Ptyhon很好', 120.00, 77, 13, 20, '5acb3627N8191c867.jpg');
INSERT INTO `oeong_product` VALUES (11, 'Java从入门到精通', 'Java入门必读', 47.00, 3000, 12, 28, '99742c91174d3d7a.jpg');
INSERT INTO `oeong_product` VALUES (12, '细说PHP 第四版', 'PHP入门必读', 47.00, 3000, 14, 22, 'php4.jpg');

-- ----------------------------
-- Table structure for oeong_user
-- ----------------------------
DROP TABLE IF EXISTS `oeong_user`;
CREATE TABLE `oeong_user`  (
  `USER_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_SEX` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_BIRTHDAY` datetime(0) NULL DEFAULT NULL,
  `USER_IDENTITY_CODE` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_EMAIL` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_MOBILE` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_STATUS` decimal(6, 0) NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oeong_user
-- ----------------------------
INSERT INTO `oeong_user` VALUES ('2', 'chenglong hong', 'admin', 'T', '1998-09-29 00:00:00', NULL, NULL, NULL, '福州', 1);
INSERT INTO `oeong_user` VALUES ('admin', 'chenglong hong', 'admin', 'T', '1998-09-29 00:00:00', NULL, NULL, NULL, '福州', 2);
INSERT INTO `oeong_user` VALUES ('admin1', 'admin', '123', 'T', '2019-10-08 00:00:00', NULL, 'aaa@bbb.com', '222222', '4323323232323232', 2);
INSERT INTO `oeong_user` VALUES ('admin2', 'admin', '123', 'T', '2019-10-08 00:00:00', NULL, 'aaa@bbb.com', '222222', '4323323232323232', 2);
INSERT INTO `oeong_user` VALUES ('admin3', 'admin', '123', 'T', '2019-10-08 00:00:00', NULL, 'aaa@bbb.com', '222222', '4323323232323232', 2);
INSERT INTO `oeong_user` VALUES ('admin4', 'admin', '123', 'T', '2019-10-08 00:00:00', NULL, 'aaa@bbb.com', '222222', '4323323232323232', 2);
INSERT INTO `oeong_user` VALUES ('admin5', 'admin', '123', 'T', '2019-10-08 00:00:00', NULL, 'aaa@bbb.com', '222222', '4323323232323232', 2);
INSERT INTO `oeong_user` VALUES ('admin6', 'admin', '123', 'T', '2019-10-08 00:00:00', NULL, 'aaa@bbb.com', '222222', '4323323232323232', 2);
INSERT INTO `oeong_user` VALUES ('admin666', 'chenglong hong', '666', 'F', '2021-02-19 00:00:00', NULL, '12313', '17720762997', '福州', 1);
INSERT INTO `oeong_user` VALUES ('admin7', 'admin', '123', 'T', '2019-10-08 00:00:00', NULL, 'aaa@bbb.com', '222222', '4323323232323232', 2);
INSERT INTO `oeong_user` VALUES ('hello123', 'hello', '111', 'T', '2019-10-15 00:00:00', NULL, 'aaa@oeong.com', '121212121', 'aaaaaaaaaaaaaaaaa', 1);
INSERT INTO `oeong_user` VALUES ('hello2', 'hello', '111', 'F', '2019-10-08 00:00:00', NULL, '88888', '88888', '8888888888', 1);
INSERT INTO `oeong_user` VALUES ('hello3', 'rewreww', 'www', 'T', '2019-10-08 00:00:00', NULL, 'retrewtrew', 'trewtrewt', 'rewtrewtew', 1);
INSERT INTO `oeong_user` VALUES ('lisi5', '李四', '111', 'T', '2019-11-05 00:00:00', NULL, 'lisi@oeong.com', '15801684888', '北京西二期软件园', 1);
INSERT INTO `oeong_user` VALUES ('lisi6', '李四', '111', 'T', '2019-11-05 00:00:00', NULL, 'lisi@oeong.com', '15801684888', '北京西二期软件园', 1);
INSERT INTO `oeong_user` VALUES ('lisi67', '李四', '111', 'T', '2019-11-05 00:00:00', NULL, 'lisi@oeong.com', '15801684888', '北京西二期软件园', 1);
INSERT INTO `oeong_user` VALUES ('nobody', '', '', 'F', '2021-02-07 00:00:00', NULL, '', '', '', 1);
INSERT INTO `oeong_user` VALUES ('qewqewq', 'qwerty', 'wq', 'T', '2019-11-11 00:00:00', NULL, 'dddsds', 'dsdsds', 'dsdsds', 1);
INSERT INTO `oeong_user` VALUES ('wangwu', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('wangwu1', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('wangwu2', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('wangwu3', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('wangwu4', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('wangwu5', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('wangwu6', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('wangwu67', '王五', '111', 'T', '2019-11-01 00:00:00', NULL, 'wangwu@oeong.com', '18810090000', '中关村软件园', 1);
INSERT INTO `oeong_user` VALUES ('zhangsan5', '张三', '111', 'T', '2019-11-08 00:00:00', NULL, 'zhangsan@oeong.com', '1192121221', '北京西二期软件园', 1);
INSERT INTO `oeong_user` VALUES ('zhangsan6', '张三', '111', 'T', '2019-11-08 00:00:00', NULL, 'zhangsan@oeong.com', '1192121221', '北京西二期软件园', 1);
INSERT INTO `oeong_user` VALUES ('zhangsan67', '张三', '111', 'T', '2019-11-08 00:00:00', NULL, 'zhangsan@oeong.com', '1192121221', '北京西二期软件园', 1);

SET FOREIGN_KEY_CHECKS = 1;
