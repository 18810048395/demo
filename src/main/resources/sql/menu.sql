/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.163.10
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 192.168.163.10:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/03/2022 18:07:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL COMMENT '菜单id',
  `pid` bigint NULL DEFAULT NULL COMMENT '菜单父id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '主菜单1', '2022-03-07 16:59:31', 'yh');
INSERT INTO `menu` VALUES (2, 1, '子菜单1', '2022-03-07 17:00:09', 'iaohei');
INSERT INTO `menu` VALUES (3, 1, '子菜单2', '2022-03-07 17:00:41', 'xiaobai');
INSERT INTO `menu` VALUES (5, 3, '末级菜单2', '2022-03-07 17:01:42', 'xiaobai');

SET FOREIGN_KEY_CHECKS = 1;
