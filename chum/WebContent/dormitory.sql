/*
 Navicat Premium Data Transfer

 Source Server         : local5
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : dormitory

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 15/06/2021 16:26:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `Admin_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Admin_Username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Admin_Password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Admin_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Admin_Sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Admin_Tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Admin_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '管理员', '男', '15878568954');

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `Building_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Building_Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Building_Introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Building_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, '西-5-男', '帅哥集中营');
INSERT INTO `building` VALUES (2, '西-6-男', '大佬集中地');
INSERT INTO `building` VALUES (3, '东-9-女', '最美楼栋');
INSERT INTO `building` VALUES (4, '东-10-女', '赛比貂蝉');

-- ----------------------------
-- Table structure for domitory
-- ----------------------------
DROP TABLE IF EXISTS `domitory`;
CREATE TABLE `domitory`  (
  `Domitory_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Domitory_BuildingID` int(11) DEFAULT NULL,
  `Domitory_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Domitory_Type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Domitory_Number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Domitory_Tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Domitory_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of domitory
-- ----------------------------
INSERT INTO `domitory` VALUES (1, 1, '101', '合格寝室', '4', '17584587596');
INSERT INTO `domitory` VALUES (2, 1, '401', '文明寝室', '6', '17858468542');
INSERT INTO `domitory` VALUES (3, 2, '201', '不合格寝室', '4', '18574589563');
INSERT INTO `domitory` VALUES (4, 2, '305', '特优寝室', '4', '18547584586');
INSERT INTO `domitory` VALUES (5, 3, '302', '合格寝室', '4', '18574586952');
INSERT INTO `domitory` VALUES (6, 3, '501', '文明寝室', '6', '18596523653');
INSERT INTO `domitory` VALUES (7, 4, '301', '特优寝室', '6', '18545632548');
INSERT INTO `domitory` VALUES (8, 4, '306', '文明寝室', '5', '18458768953');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `Log_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Log_StudentID` int(11) DEFAULT NULL,
  `Log_TeacherID` int(11) DEFAULT NULL,
  `Log_Date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Log_Remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Log_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, 3, 1, '2021-04-01', '网吧通宵');
INSERT INTO `log` VALUES (2, 4, 1, '2021-04-02', '外出跑路');
INSERT INTO `log` VALUES (3, 3, 1, '2021-04-15', '跑了');
INSERT INTO `log` VALUES (4, 4, 1, '2021-04-16', '溜啦');
INSERT INTO `log` VALUES (5, 3, 1, '2021-04-24', '跑到贼快');
INSERT INTO `log` VALUES (6, 4, 1, '2021-04-10', '我带我不');
INSERT INTO `log` VALUES (7, 5, 4, '2021-04-10', '份未发');
INSERT INTO `log` VALUES (8, 6, 4, '2021-04-17', '分为氛围粉色');
INSERT INTO `log` VALUES (9, 7, 4, '2021-04-10', '给i有覅有');
INSERT INTO `log` VALUES (10, 10, 2, '2021-04-01', '分为各位');
INSERT INTO `log` VALUES (11, 10, 2, '2021-04-02', 'v啊v企鹅');

-- ----------------------------
-- Table structure for outlog
-- ----------------------------
DROP TABLE IF EXISTS `outlog`;
CREATE TABLE `outlog`  (
  `Out_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Out_StudentID` int(11) DEFAULT NULL,
  `Out_Date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Out_Remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Out_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of outlog
-- ----------------------------
INSERT INTO `outlog` VALUES (1, 1, '2021-04-28', '毕业啦');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Student_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Student_DomitoryID` int(11) DEFAULT NULL,
  `Student_Username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Student_Password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Student_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Student_Sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Student_Class` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Student_State` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Student_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 0, '16103330101', '123456', '安琪拉', '女', '计161', '迁出');
INSERT INTO `student` VALUES (2, 7, '16103330102', '123456', 'wzx', '女', '计161', '入住');
INSERT INTO `student` VALUES (3, 1, '16103330103', '123456', '白起', '男', '计161', '入住');
INSERT INTO `student` VALUES (4, 2, '16103330104', '123456', '百里守约', '男', '计162', '入住');
INSERT INTO `student` VALUES (5, 4, '16103330105', '123456', '东皇太一', '男', '计161', '入住');
INSERT INTO `student` VALUES (6, 3, '17103330101', '123456', '欧阳富贵', '男', '计171', '入住');
INSERT INTO `student` VALUES (7, 4, '17103330102', '123456', '王天马', '男', '计171', '入住');
INSERT INTO `student` VALUES (8, NULL, '17103330103', '123456', '李大伟', '男', '计171', '未入住');
INSERT INTO `student` VALUES (9, NULL, '17103330104', '123456', '吴金善', '女', '计172', '删除');
INSERT INTO `student` VALUES (10, 5, '17103330105', '123456', '张田田', '女', '计172', '删除');

-- ----------------------------
-- Table structure for tb
-- ----------------------------
DROP TABLE IF EXISTS `tb`;
CREATE TABLE `tb`  (
  `TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TB_TeacherID` int(11) DEFAULT NULL,
  `TB_BuildingID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TB_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb
-- ----------------------------
INSERT INTO `tb` VALUES (1, 1, 1);
INSERT INTO `tb` VALUES (3, 4, 2);
INSERT INTO `tb` VALUES (4, 5, 2);
INSERT INTO `tb` VALUES (5, 2, 3);
INSERT INTO `tb` VALUES (6, 6, 4);
INSERT INTO `tb` VALUES (7, 4, 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `Teacher_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Teacher_Username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Teacher_Password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Teacher_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Teacher_Sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Teacher_Tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Teacher_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '202101', '123456', '令狐冲', '男', '18748568954');
INSERT INTO `teacher` VALUES (2, '202102', '123456', '岳灵珊', '女', '15878456985');
INSERT INTO `teacher` VALUES (3, '202103', '123456', '岳不群', '男', '18715869542');
INSERT INTO `teacher` VALUES (4, '202104', '123456', '田伯光', '男', '18745869854');
INSERT INTO `teacher` VALUES (5, '202105', '123456', '林平之', '男', '18574857896');
INSERT INTO `teacher` VALUES (6, '202106', '123456', '赵敏', '女', '15874589658');

SET FOREIGN_KEY_CHECKS = 1;
