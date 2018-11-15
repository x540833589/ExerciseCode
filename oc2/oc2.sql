/*
 Navicat Premium Data Transfer

 Source Server         : myblog
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : oc2

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 26/10/2018 17:59:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `deptID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upOneLevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_CN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_EN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `childNum` int(10) NULL DEFAULT NULL,
  `bu_Head` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deptLevel` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`deptID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('D1000', '0', '总经办', 'General Offiece', 0, '否', '', 1);
INSERT INTO `department` VALUES ('D2000', 'D1000', '人力资源部', 'HR', 0, '是', NULL, 1);
INSERT INTO `department` VALUES ('D4000', 'D1000', '销售部', 'Sales', 0, '是', NULL, 1);
INSERT INTO `department` VALUES ('D5000', 'D1000', '实施部', 'PMO', 0, '是', NULL, 1);
INSERT INTO `department` VALUES ('D5300', 'D5200', '自动化开发', 'Automation', 0, '否', NULL, 2);
INSERT INTO `department` VALUES ('D6000', 'D1000', '解决方案部', 'MES Solution', 0, '是', NULL, 1);
INSERT INTO `department` VALUES ('D7000', 'D1000', '产品研发部', 'Product', 2, '是', NULL, 1);
INSERT INTO `department` VALUES ('D7100', 'D7000', '产品研发部_开发1部', 'Product_Dev_1', 0, '否', NULL, 2);
INSERT INTO `department` VALUES ('D7200', 'D7000', '产品研发部_开发2部', 'Product_Dev_2', 0, '否', NULL, 2);
INSERT INTO `department` VALUES ('D8000', 'D1000', '研发部', 'R&D', 4, '是', NULL, 1);
INSERT INTO `department` VALUES ('D8100', 'D8000', '研发部_开发1部', 'R&D_Dev_1', 3, '否', NULL, 2);
INSERT INTO `department` VALUES ('D8110', 'D8100', '研发部_开发1部_第1小组', 'R&D_Dev_1_Team 1', 0, '否', NULL, 3);
INSERT INTO `department` VALUES ('D8120', 'D8100', '研发部_开发1部_第2小组', 'R&D_Dev_1_Team 2', 0, '否', NULL, 3);
INSERT INTO `department` VALUES ('D8130', 'D8100', '研发部_开发1部_第3小组', 'R&D_Dev_1_Team 3', 0, '否', NULL, 3);
INSERT INTO `department` VALUES ('D8200', 'D8000', '研发部_开发2部', 'R&D_Dev_2', 3, '否', NULL, 2);
INSERT INTO `department` VALUES ('D8210', 'D8200', '研发部_开发2部_第1小组', 'R&D_Dev_2_Team 1', 0, '否', NULL, 3);
INSERT INTO `department` VALUES ('D8220', 'D8200', '研发部_开发2部_第2小组', 'R&D_Dev_2_Team 2', 0, '否', NULL, 3);
INSERT INTO `department` VALUES ('D8230', 'D8200', '研发部_开发2部_第3小组', 'R&D_Dev_2_Team 3', 0, '否', NULL, 3);
INSERT INTO `department` VALUES ('D8300', 'D8000', '研发部_测试部', 'R&D_Test', 0, '否', NULL, 2);

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `position_CN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position_EN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, '总经理', 'General Manager');
INSERT INTO `position` VALUES (2, '实施总监', 'PMO Director');
INSERT INTO `position` VALUES (3, '产品总监', 'Product Director');
INSERT INTO `position` VALUES (4, '项目经理', 'Project Manager');
INSERT INTO `position` VALUES (5, '研发总监', 'R&D Director');
INSERT INTO `position` VALUES (6, '销售总监', 'Sales Director');
INSERT INTO `position` VALUES (7, '开发工程师', 'Software Engineer');
INSERT INTO `position` VALUES (8, '开发组长', 'Development Leader');
INSERT INTO `position` VALUES (9, '人事专员', 'HR Assistant');
INSERT INTO `position` VALUES (10, '测试工程师', 'Test Engineer');
INSERT INTO `position` VALUES (11, '测试主管', 'Test Manager');
INSERT INTO `position` VALUES (12, '开发经理', 'Development Manager');
INSERT INTO `position` VALUES (13, '售前顾问', 'Pre Sales Consultant');
INSERT INTO `position` VALUES (14, '人事经理', 'HR Manager');
INSERT INTO `position` VALUES (15, '前端开发', 'UI Development Engineer');
INSERT INTO `position` VALUES (16, '自动化工程师', 'Automation Engineer');
INSERT INTO `position` VALUES (17, '解决方案总监', 'MES Solution Director');
INSERT INTO `position` VALUES (18, '销售经理', 'Sales Manager');
INSERT INTO `position` VALUES (19, '开发实习生', 'Development  Intern');
INSERT INTO `position` VALUES (20, '解决方案顾问', 'Solution Consultant');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name_CN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name_EN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deptID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `childDeptID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `positionID` int(11) NULL DEFAULT NULL,
  `isHead` int(10) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isActive` int(10) NULL DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `structureDistribution` int(10) NULL DEFAULT NULL COMMENT '标识每个员工的层级级关系的字段，总共有5层\r\n前四位是deptID的后四位，最后一位表示此员工所在层级\r\neg： David---> 10005\r\n          King----> 80004\r\n          Nicole--> 81003\r\n          Amy ----> 81302\r\n          James---> 81301',
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10071 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, '王超', 'David Wang', 'D1000', NULL, 1, 1, 'david_wang@sottop.cn', 1, 'photo/ac5db86cc03e43ff88a906620f9234ba.png', 10005);
INSERT INTO `staff` VALUES (2, '朱威', 'Joe Zhu', 'D5000', NULL, 2, 1, 'joe_zhu@sottop.cn', 1, 'photo/ebdaf9194d4f4084b1d478a7df41d12d.png', 50004);
INSERT INTO `staff` VALUES (3, '梅彩亮', 'Jack Mei', 'D7000', NULL, 3, 1, 'jack_mei@sottop.cn', 1, 'photo/87e13751d70745659efa99b0f5573fbb.png', 70004);
INSERT INTO `staff` VALUES (4, '朱雷', 'Thunder Zhu', 'D5000', NULL, 4, 0, 'thunder_zhu@sottop.cn', 1, 'photo/a7656a1b3fe54a92b91599331ae91795.png', 50003);
INSERT INTO `staff` VALUES (5, '孙彤', 'King Sun', 'D8000', NULL, 5, 1, 'king_sun@sottop.cn', 1, 'photo/a460b23797e142538b2b3a9a030816b1.png', 80004);
INSERT INTO `staff` VALUES (6, '朱铖', 'Alex Zhu', 'D4000', NULL, 6, 1, 'alex_zhu@sottop.cn', 1, 'photo/02e59067f5b44c588c139189b8d152ee.png', 40004);
INSERT INTO `staff` VALUES (7, '王茹', 'Wang Ru', 'D8230', NULL, 7, 0, NULL, 1, 'photo/be6c4a75f6af4cd3be3ca8db985c6f8b.png', 82301);
INSERT INTO `staff` VALUES (8, '施云', 'Amy Shi', 'D8130', NULL, 8, 1, 'amy_shi@sottop.cn', 1, 'photo/a20f55fb22d34c3c927a7173132483ca.png', 81302);
INSERT INTO `staff` VALUES (9, '熊碧恒', 'Hebert Xiong', 'D5000', 'D5300', 4, 1, 'hebert_xiong@sottop.cn', 1, 'photo/26a1e55afd7f4ee8aec02cbcaffd0a36.png', 50003);
INSERT INTO `staff` VALUES (10, '李健', 'Jin Li', 'D8220', NULL, 7, 0, 'jim_li@sottop.cn', 1, 'photo/85d7a30defe64c038f3c7cc374d89168.png', 82201);
INSERT INTO `staff` VALUES (11, '周航', 'Blitz Zhou', 'D8210', NULL, 7, 0, 'blitz_zhou@sottop.cn', 1, 'photo/19a507317fb24de0a2bba8539eacdd4f.png', 82101);
INSERT INTO `staff` VALUES (12, '陈张辉', 'Johnny Chen', 'D8210', NULL, 7, 0, 'johnny_chen@sottop.cn', 1, 'photo/d4dc91e7d48340ff97ffdb49b5001c14.png', 82101);
INSERT INTO `staff` VALUES (13, '张海波', 'Smith Zhang', 'D8220', NULL, 7, 0, 'smith_zhang@sottop.cn', 1, 'photo/2d5e361135714de39b4fbf4d81bbf41a.png', 82201);
INSERT INTO `staff` VALUES (14, '张惠龙', 'Darren Zhang', 'D8210', NULL, 7, 0, 'darren_zhang@sottop.cn', 1, 'photo/9ff8c1a75d744528b2add7db37e8b910.png', 82101);
INSERT INTO `staff` VALUES (15, '洪天强', 'Leo Hong', 'D8220', NULL, 7, 0, 'leo_hong@sottop.cn', 1, 'photo/4e231ae74a46450288f6fcd99d445e20.png', 82201);
INSERT INTO `staff` VALUES (16, '金倩', 'Olivia Jin', 'D2000', NULL, 9, 0, 'olivia_jin@sottop.cn', 1, 'photo/3a6c657481364684845ee3f8a578239e.png', 20003);
INSERT INTO `staff` VALUES (17, '杨炳武', 'Mike Yang', 'D8210', NULL, 7, 0, 'mike_yang@sottop.cn', 1, 'photo/465b712f9d87456d8c3cca4fe01c120d.png', 82101);
INSERT INTO `staff` VALUES (18, '徐艳', 'Jessica Xu', 'D8300', NULL, 10, 0, 'jessica_xu@sottop.cn', 1, 'photo/6497522e94e54598a27c3177023d1795.png', 83002);
INSERT INTO `staff` VALUES (19, '谷晓庆', 'Demo Gu', 'D8110', NULL, 7, 0, 'demo_gu@sottop.cn', 1, 'photo/98a65270b5b543c7b74b9e34545c0bc2.png', 81101);
INSERT INTO `staff` VALUES (20, '张传家', 'Chason Zhang', 'D8210', NULL, 7, 0, 'chason_zhang@sottop.cn', 1, 'photo/6924959c81474656bb8b44f3f18e0101.png', 82101);
INSERT INTO `staff` VALUES (21, '张春原', 'Tim Zhang', 'D8220', NULL, 7, 0, 'tim_zhang@sottop.cn', 1, 'photo/ea7b664fd23b4cd5bbcc1a30c9cbef36.png', 82201);
INSERT INTO `staff` VALUES (22, '杨鹏', 'Alan Yang', 'D5000', NULL, 4, 0, 'alan_yang@sottop.cn', 1, 'photo/59c59cc1b4c941b7ae087d7fa7a3b297.png', 50003);
INSERT INTO `staff` VALUES (23, '李亚男', 'Yana Li', 'D8300', NULL, 11, 1, 'yana_li@sottop.cn', 1, 'photo/789731ac7a1b4c088f88f00a5cccf69e.png', 83003);
INSERT INTO `staff` VALUES (24, '王天伟', 'Willis Wang', 'D5000', NULL, 4, 0, 'willis_wang@sottop.cn', 1, 'photo/37a20a007b9d4dc481b2edabe4280775.png', 50003);
INSERT INTO `staff` VALUES (25, '陈芳', 'Jenny Chen', 'D8130', NULL, 7, 0, 'jenny_chen@sottop.cn', 1, 'photo/97f27e713ef7458e82a3c37215f0395d.png', 81301);
INSERT INTO `staff` VALUES (26, '王强', 'Kevin Wang', 'D8130', NULL, 7, 0, 'kevin_wang@sottop.cn', 1, 'photo/5447c4f897794e7586d59498979037aa.png', 81301);
INSERT INTO `staff` VALUES (27, '苗全', 'Jason Miao', 'D5300', NULL, 16, 0, 'jason_miao@sottop.cn', 1, 'photo/fecf6695032647a4b996e83c52b783e5.png', 53002);
INSERT INTO `staff` VALUES (28, '俞玉婷', 'Nicole Yu', 'D8100', NULL, 12, 1, 'nicole_yu@sottop.cn', 1, 'photo/adc99dbf7451471db2241317ad2946d9.png', 81003);
INSERT INTO `staff` VALUES (29, '沈森森', 'Bosen Shen ', 'D5000', NULL, 4, 0, 'bosen_shen@sottop.cn', 1, 'photo/c4a45e7a56d948c796481bb40485add8.png', 51003);
INSERT INTO `staff` VALUES (30, '杨莉莉', 'Lili Yang', 'D8220', NULL, 7, 0, 'lili_yang@sottop.cn', 1, 'photo/36b33ece4c254bc0bf1b80bd351b7554.png', 82201);
INSERT INTO `staff` VALUES (31, '许仔昊', 'Jacky Xu', 'D4000', NULL, 13, 0, 'jacky_xu@sottop.cn', 1, 'photo/d5ebcfd606fc4219a3cd0e167d0958e1.png', 40003);
INSERT INTO `staff` VALUES (32, '徐盼盼', 'Phyllis Xu', 'D2000', NULL, 14, 1, 'Phyllis_xu@sottop.cn', 1, 'photo/c103701823ad461e86ed97bf83b429be.png', 20004);
INSERT INTO `staff` VALUES (33, '叶俊', 'Tom Ye', 'D8120', NULL, 7, 0, 'tom_ye@sottop.cn', 1, 'photo/0bcdb63c3373441f8dcb7b0214f965ca.png', 81201);
INSERT INTO `staff` VALUES (34, '马壮', 'Brook Ma', 'D8130', NULL, 7, 0, 'brook_ma@sottop.cn', 1, 'photo/cac00d8f9fbc431ead81ae4f75e8dd32.png', 81301);
INSERT INTO `staff` VALUES (35, '孙岩', 'Tony Sun', 'D8120', NULL, 7, 0, 'tony_sun@sottop.cn', 1, 'photo/d90fef4c71d64ffda30fe3009f6f4ada.png', 81201);
INSERT INTO `staff` VALUES (36, '王尚红', 'Jerry Wang', 'D8120', NULL, 7, 0, 'jerry_wang@sottop.cn', 1, 'photo/0a4c2c02fded4ca9986cd5b718b00332.png', 81201);
INSERT INTO `staff` VALUES (37, '孙康乐', 'Allen Sun', 'D8130', NULL, 7, 0, 'allen_sun@sottop.cn', 1, 'photo/7b832e650bdb4d6b98a26ea6a77b450e.png', 81301);
INSERT INTO `staff` VALUES (38, '徐昊', 'Peter Xu', 'D8120', NULL, 7, 0, 'peter_xu@sottop.cn', 1, 'photo/57ee713629214dd2a9e75c1174eb9c51.png', 81201);
INSERT INTO `staff` VALUES (39, '平道威', 'Nero Ping', 'D8230', NULL, 15, 0, 'nero_ping@sottop.cn', 1, 'photo/e0540d0a3e674edd825fa5feb5bf62c6.png', 82301);
INSERT INTO `staff` VALUES (40, '邓迎春', 'Frank Deng', 'D5000', NULL, 4, 0, 'frank_deng@sottop.cn', 1, 'photo/379b506477ac4ba69a0e8ca0e489a084.png', 50003);
INSERT INTO `staff` VALUES (41, '李继伟', 'Levi Li', 'D5000', NULL, 4, 0, 'levi_li@sottop.cn', 1, 'photo/94b64bf7141f4d8b9a7cfa75116f3000.png', 50003);
INSERT INTO `staff` VALUES (42, '刘明明', 'Darren Liu', 'D5300', NULL, 16, 0, 'darren_liu@sottop.cn', 1, 'photo/29dd5b84f414479688428c948d39adee.png', 53002);
INSERT INTO `staff` VALUES (43, '朱凤祥', 'Andy Zhu', 'D7100', NULL, 12, 0, 'andy_zhu@sottop.cn', 1, 'photo/5ed637cde8ad4666a963659eeaaf21bd.png', 71003);
INSERT INTO `staff` VALUES (44, '陈旭', 'Danny Chen', 'D8300', NULL, 10, 0, 'danny_chen@sottop.cn', 1, 'photo/0f917cb922604dce80914115d44ae6f3.png', 83002);
INSERT INTO `staff` VALUES (45, '姜理俊', 'Mike Jiang', 'D7200', NULL, 12, 0, 'mike_jiang@sottop.cn', 1, 'photo/bc2ed942497941f19dbe7d780c573449.png', 72003);
INSERT INTO `staff` VALUES (46, '盛榕', 'Matthew Sheng', 'D8110', NULL, 7, 0, 'matthew_sheng@sottop.cn', 1, 'photo/e85cd1b8b8bc40b199b0612b2b626158.png', 81101);
INSERT INTO `staff` VALUES (47, '张益峰', 'Evan Zhang', 'D5300', NULL, 16, 0, 'evan_zhang@sottop.cn', 1, 'photo/00470dde2f1042d0864ab8ea1926a03d.png', 53002);
INSERT INTO `staff` VALUES (48, '郭亮', 'Gil Guo', 'D6000', NULL, 17, 1, 'gil_guo@sottop.cn', 1, 'photo/e97928ab5d974ae991b709e85250a302.png', 60004);
INSERT INTO `staff` VALUES (49, '宋佳慧', 'Jenny Song', 'D8300', NULL, 10, 0, 'jenny_song@sottop.cn', 1, 'photo/87c1ab19d6874cbd9f13896e34eddd4f.png', 83002);
INSERT INTO `staff` VALUES (50, '钱海涛', 'Barry Qian', 'D8110', NULL, 7, 0, 'barry_qian@sottop.cn', 1, 'photo/f07f10e3f273406a8c72d8965ecbd8b3.png', 81101);
INSERT INTO `staff` VALUES (51, '周魏', 'Anton Zhou', 'D8200', NULL, 12, 1, 'anton_zhou@sottop.cn', 1, 'photo/06a6391088f84a7ea1315d6940ebaf5a.png', 82003);
INSERT INTO `staff` VALUES (52, '施晓琦', 'Uno Shi', 'D8110', NULL, 7, 0, 'uno_shi@sottop.cn', 1, 'photo/23b967e71ea64e7992c406be4bdaf357.png', 81101);
INSERT INTO `staff` VALUES (53, '苏德军', 'Sandro Su', 'D4000', NULL, 18, 0, 'sandro_su@sottop.cn', 1, 'photo/768f0b8702cd4aa19760883fa4e0fe92.png', 40003);
INSERT INTO `staff` VALUES (54, '王庆', 'Winston Wang', 'D8120', NULL, 19, 0, 'winston_wang@sottop.cn', 1, 'photo/8e0ee38bb7f043cfa42f17d9a14739dd.png', 81201);
INSERT INTO `staff` VALUES (55, '李琦', 'Leon Li', 'D8110', NULL, 7, 0, 'leon_li@sottop.cn', 1, 'photo/222c8b0908aa4502ae934b1d0440d642.png', 81101);
INSERT INTO `staff` VALUES (56, '唐淑娟', 'Lucy Tang', 'D6000', NULL, 20, 0, 'lucy_tang@sottop.cn', 1, 'photo/43c11c8da2b743d6b8d9259f1d3c0501.png', 60003);
INSERT INTO `staff` VALUES (57, '陈欢', 'Arthur Chen', 'D8210', NULL, 7, 0, 'arthur_chen@sottop.cn', 1, 'photo/878dc5b9f2844da9bf21ef569e3dc4e4.png', 82101);
INSERT INTO `staff` VALUES (58, '王文谦', 'Anakin Wang', 'D8110', NULL, 19, 0, 'anakin_wang@sottop.cn', 1, 'photo/d4d70b31fdab4903941937d85e1e830c.png', 81101);
INSERT INTO `staff` VALUES (59, '范伟业', 'Bryant Fan', 'D8230', NULL, 15, 0, 'bryant_fan@sottop.cn', 1, 'photo/1cd0c30a82934302b9aace0f742b0e19.png', 82301);
INSERT INTO `staff` VALUES (60, '赵磊', 'Ray Zhao', 'D8120', NULL, 7, 0, 'ray_zhao@sottop.cn', 1, 'photo/3d6bcb2464f84adb9fc68f6154189ee7.png', 81201);
INSERT INTO `staff` VALUES (61, '黄雷', 'Ralap Huang', 'D8130', NULL, 7, 0, 'ralap_huang@sottop.cn', 1, 'photo/24e4ff797a8843c7868cb05c5f760db0.png', 81301);
INSERT INTO `staff` VALUES (62, '陈颖松', 'James Chen', 'D8130', NULL, 7, 0, 'james_chen@sottop.cn', 1, 'photo/a02c56acdf0d4239a51838f374d5149b.png', 81301);
INSERT INTO `staff` VALUES (63, '肖依涛', 'Leo Xiao', 'D8130', NULL, 7, 0, 'leo_xiao@sottop.cn', 1, 'photo/a55046a5e43b4560a267ee6ced1bf904.png', 81301);
INSERT INTO `staff` VALUES (64, '王斌徐', 'Alvis Wang', 'D8120', NULL, 7, 0, 'alvis_wang@sottop.cn', 1, 'photo/1059fe41b53e4d0eacd15fc2001a5c30.png', 81201);
INSERT INTO `staff` VALUES (65, '熊涌杰', 'Bear Xiong', 'D8110', NULL, 7, 0, 'bear_xiong@sottop.cn', 1, 'photo/90b0ceb556d84f0ca316d2fbd1843af2.png', 81101);
INSERT INTO `staff` VALUES (66, '周俊', 'Jerry Zhou', 'D5000', NULL, 4, 0, 'jerry_zhou@sottop.cn', 1, 'photo/9af01fcffd554df5bfba70f7b0b1392e.png', 50003);
INSERT INTO `staff` VALUES (67, '杨传斌', 'Brad Yang', 'D6000', NULL, 20, 0, 'brad_yang@sottop.cn', 1, 'photo/7d091f11700349439f5890afdf76e974.png', 60003);
INSERT INTO `staff` VALUES (9990, NULL, 'Team Leader', 'D8110', NULL, 8, 1, NULL, 0, NULL, 81102);
INSERT INTO `staff` VALUES (9991, NULL, 'Team Leader', 'D8120', NULL, 8, 1, NULL, 0, NULL, 81202);
INSERT INTO `staff` VALUES (9992, NULL, 'Team Leader', 'D8210', NULL, 8, 1, NULL, 0, NULL, 82102);
INSERT INTO `staff` VALUES (9993, NULL, 'Team Leader', 'D8220', NULL, 8, 1, NULL, 0, NULL, 82202);
INSERT INTO `staff` VALUES (9994, NULL, 'Team Leader', 'D8230', NULL, 8, 1, NULL, 0, NULL, 82302);

SET FOREIGN_KEY_CHECKS = 1;
