/*
 Navicat Premium Data Transfer

 Source Server         : biubiu_aliyun
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 114.215.149.153:3306
 Source Schema         : db_web

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 21/04/2020 16:20:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '用户年龄',
  `sex` int(0) NULL DEFAULT NULL COMMENT '用户性别，1：男，0：女',
  `tel` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `valid` int(0) NULL DEFAULT NULL COMMENT '是否有效，1：有效，0：无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
INSERT INTO `tb_users` VALUES (1, '张三', 21, 1, '15566936985', '深圳市南山区', '2020-04-20 13:13:07', NULL, 0);
INSERT INTO `tb_users` VALUES (2, '李四', 18, 0, '15566482385', '长沙市雨花区', '2020-04-20 13:19:21', NULL, 0);
INSERT INTO `tb_users` VALUES (3, '王五', 20, 0, '15596321985', '深圳松山湖区', '2020-04-20 13:19:21', NULL, 0);
INSERT INTO `tb_users` VALUES (4, '赵六', 26, 1, '13256949652', '深圳市南山区', '2020-04-20 13:19:21', NULL, 1);
INSERT INTO `tb_users` VALUES (5, '十七', 22, 0, '15561474985', '上海市黄浦区', '2020-04-20 13:19:21', NULL, 1);
INSERT INTO `tb_users` VALUES (6, '阿什', 25, 1, '15562242985', '深圳市黄浦区', '2020-04-20 13:19:21', NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
