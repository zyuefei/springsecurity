/*
 Navicat Premium Data Transfer

 Source Server         : 248
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 192.168.11.248:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 09/09/2019 15:42:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for spring_session
-- ----------------------------
DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE `spring_session`  (
  `PRIMARY_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SESSION_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`) USING BTREE,
  UNIQUE INDEX `SPRING_SESSION_IX1`(`SESSION_ID`) USING BTREE,
  INDEX `SPRING_SESSION_IX2`(`EXPIRY_TIME`) USING BTREE,
  INDEX `SPRING_SESSION_IX3`(`PRINCIPAL_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for spring_session_attributes
-- ----------------------------
DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes`  (
  `SESSION_PRIMARY_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`) USING BTREE,
  CONSTRAINT `spring_session_attributes_ibfk_1` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前部门ID',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父部门ID,根节点为00',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门code,两位数字一个部门层级',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `pingyin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称首字母，用于快速搜索',
  `type` int(11) DEFAULT NULL COMMENT '部门类型，1表示正常部门，0表示公司',
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属公司id',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门备注信息',
  `sort` int(11) DEFAULT NULL COMMENT '部门排序',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人所属部门',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者id',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `reserved1` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段1',
  `reserved2` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段2',
  `reserved3` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段3',
  `reserved4` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段4',
  `reserved5` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段5',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('1', '1000000', '', '金坛港公司', 'jssgkjtyxgs', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, ' 金坛港生产管理平台', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('1000000', '0', '', '港口集团', 'jssgkjtyxgs', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('155868823529004', '1', '', '综合办', 'jtld', NULL, NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, ' 金坛港生产管理平台', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('155869019981817', '1', '', '业务部', 'zhsxb,zhxxb,zgsxb,zgxxb', NULL, NULL, NULL, 11, NULL, NULL, NULL, NULL, NULL, ' 金坛港生产管理平台', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('155869021684280', '1', '', '营运操作部', 'zzrsb', NULL, NULL, NULL, 12, NULL, NULL, NULL, NULL, NULL, ' 金坛港生产管理平台', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('155869023088571', '1', '', '安全技术部', 'ghtzb', NULL, NULL, NULL, 13, NULL, NULL, NULL, NULL, NULL, ' 金坛港生产管理平台', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('155869025520045', '1', '', '财务部', 'scywb(zdds),scywb(ztds)', NULL, NULL, NULL, 14, NULL, NULL, NULL, NULL, NULL, ' 金坛港生产管理平台', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('156462921023427', '155868823529004', '', '测试部门', 'csbm', NULL, NULL, NULL, 101, NULL, NULL, NULL, NULL, NULL, ' 金坛港生产管理平台', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('156619968135132', '155868823529004', '', '测试部门2', 'csbm2', NULL, NULL, NULL, 9, NULL, NULL, NULL, NULL, NULL, '金坛港公司', 'jintan.portjs.com', '1', NULL, NULL);
INSERT INTO `t_department` VALUES ('156747846111519', '1', '', '测试部门', 'csbm', NULL, NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('156773857194161', '155868823529004', '', 'test2', 'test2', NULL, NULL, NULL, 11, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('156773858415564', '156619968135132', '', 'test3', 'test3', NULL, NULL, NULL, 12, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('156773859593329', '2', '', 'test2', 'test2', NULL, NULL, NULL, 13, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('156773860232192', '156773859593329', '', 'cccc', 'cccc', NULL, NULL, NULL, 14, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('156774090671871', '2', '', 't21', 't21', NULL, NULL, NULL, 15, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('156774091092917', '156774090671871', '', 'ccc', 'ccc', NULL, NULL, NULL, 16, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('156774205066342', '155869019981817', '', 'cc123', 'cc123', NULL, NULL, NULL, 17, NULL, NULL, NULL, NULL, NULL, '港口集团', 'www.gk.com', '1000000', NULL, NULL);
INSERT INTO `t_department` VALUES ('2', '1000000', '', '新沂港公司', 'jssgkjtyxgs', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, ' 新沂港生产管理平台', 'xinyi.portjs.com', '2', NULL, NULL);

-- ----------------------------
-- Table structure for t_menu_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_resource`;
CREATE TABLE `t_menu_resource`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源菜单表,每两位表示一个层级',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父权限',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单或资源的名字',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `grade` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单等级',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `sort` int(100) DEFAULT NULL COMMENT '排序',
  `reserved1` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `reserved2` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `reserved3` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `reserved4` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限菜单表，基本程序员手动添加' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_menu_resource
-- ----------------------------
INSERT INTO `t_menu_resource` VALUES ('1', '1000000', '菜单树11', '1', NULL, '1', 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('10000', '1', '生产作业', '/shipManage', '一级菜单', 'iconfont icon-ganghangzhiku', 1, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('100000', '1', '管理驾驶舱', '/index', '一级菜单', 'iconfont icon-ganghangzhiku', 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('10100', '10000', '船舶调度', '/shipManage', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('10200', '10000', '船舶单证', '/shipManage', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('10300', '10000', '泊位工班计划', '/shipManage', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('20000', '1', '场图计划', '/', '一级菜单', 'iconfont icon-ganghangzhiku', 2, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('20100', '20000', '堆场计划', '/yardPlan', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('20200', '20000', '场地定义', '/yardManage', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('20300', '20000', '堆场作业', '/yardManage3', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('30000', '1', '统计分析', '/statisticsAnalysis', '一级菜单', 'iconfont icon-ganghangzhiku', 3, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('30100', '30000', '报表模块', '/yardManage', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('40000', '1', '商务收费', '/costAccounting', '一级菜单', 'iconfont icon-ganghangzhiku', 4, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50000', '1', '内河智库', '/userManage', '一级菜单', 'iconfont icon-ganghangzhiku', 5, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50100', '50000', '常用模板', '/downloadDocument', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50101', '50100', '常用模板-删除', 'downloadDocumentDel', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50102', '50100', '常用模板-新建', 'downloadDocumentAdd', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50103', '50100', '常用模板-查看', 'downloadDocumentCheck', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50200', '50000', '常用软件', '/downloadSoft', '二级菜单', '', 1, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50201', '50200', '常用软件-删除', 'softwareDownloadDel', '二级菜单', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50202', '50200', '常用软件-新建', 'softwareDownloadAdd', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50203', '50200', '常用软件-查看', 'softwareDownloadCheck', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50300', '50000', '运营智库', '/projectLibrary', '二级菜单', '', 2, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50301', '50300', '运营智库-删除', '/projectLibraryDel', '二级菜单', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50302', '50300', '运营智库-新建', 'projectLibraryAdd', '二级菜单', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50303', '50300', '运营智库-查看', 'projectLibraryCheck', '二级菜单', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50400', '50000', '规范制度', '/specificationSystem', '二级菜单', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50401', '50400', '规范制度-删除', 'specificationSystemDel', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50402', '50400', '规范制度-新建', 'specificationSystemAdd', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('50403', '50400', '规范制度-查看', 'specificationSystemCheck', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60000', '1', '基础数据', '/basicData', '一级菜单', 'iconfont icon-ganghangzhiku', 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60100', '60000', '船舶信息', '/shipCompany', '二级菜单', '', 2, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60200', '60000', '费用费目', '/feeItem', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60300', '60000', '客户档案', '/customer', '二级菜单', '', 4, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60400', '60000', '员工档案', '/staff', '二级菜单', '', 5, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60500', '60000', '班组管理', '/workTeam', '二级菜单', '', 6, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60600', '60000', '航线管理', '/shipRoute', '二级菜单', '', 7, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60700', '60000', '货类信息管理', '/goodsTypeManage', '二级菜单', '', 8, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60800', '60000', '泊位缆桩', '/bollard', '二级菜单', '', 9, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('60900', '60000', '机械设备管理', '/equipManage', '二级菜单', '', 9, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61000', '60000', '箱类信息', '/containerTypeInfo', '二级菜单', '', 10, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61100', '60000', '箱ISO代码', '/containerISoInfo', '二级菜单', '', 11, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61200', '60000', '箱损信息', '/boxDestroy', '二级菜单', '', 12, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61300', '60000', '箱尺寸', '/boxSize', '二级菜单', '', 13, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61400', '60000', '箱主信息', '/containerOwnerInfo', '二级菜单', '', 14, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61500', '60000', '车辆信息', '/car', '二级菜单', '', 15, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61600', '60000', '港口信息', '/portInformation', '二级菜单', '', 16, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61700', '60000', '国家信息', '/adminCountry', '二级菜单', '', 17, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61800', '60000', '洲际信息', '/adminContinent', '二级菜单', '', 18, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('61900', '60000', '协议维护', '/agreement', '二级菜单', '', 18, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70000', '1', '系统管理', '/systemManage', '一级菜单', 'iconfont icon-xitongguanli', 7, '/2019/6/1560756308012~系统管理.png', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70100', '70000', '数据字典', '/dictionary', '二级菜单', '', 2, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70101', '70100', '数据字典-新建', 'test', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70102', '70100', '数据字典-查看', 'test', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70103', '70100', '数据字典-修改', 'test', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70104', '70100', '数据字典-删除', 'test', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70200', '70000', '组织架构', '/organizationFramework', '二级菜单', '', 3, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70201', '70200', '组织架构-新建', 'organizationFrameworkAdd', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70202', '70200', '组织架构-查看', 'organizationFrameworkCheck', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70300', '70000', '用户管理', '/usersManage', '二级菜单', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70301', '70300', '用户管理-新建', 'userManageAdd', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70302', '70300', '用户管理-查看', 'userManageCheck', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70400', '70000', '角色管理', '/roleManage', '二级菜单', '', 1, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70401', '70400', '角色管理-新建', 'roleManageAdd', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70402', '70400', '角色管理-查看', 'roleManageCheck', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70500', '70000', '操作日志', '/traceLog', '二级菜单', '', 5, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70501', '70500', '操作日志-查看', 'trest', '按钮', '', 0, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70600', '70000', '权限管理', '/menuManage', '二级菜单', '', 7, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70601', '70600', '权限管理-新建', 'test2', '按钮', '', 2, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('70602', '70600', '权限管理-查看', 'test', '按钮', '', 19, '', NULL, NULL, NULL);
INSERT INTO `t_menu_resource` VALUES ('80000', '1', '客户服务', '/customerService', '一级菜单', 'iconfont icon-ganghangzhiku', 8, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
  `role_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `status` int(10) DEFAULT NULL COMMENT '禁用状态0不可用 1可用',
  `sort` int(11) DEFAULT NULL COMMENT '排序字段',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者id',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `reserved1` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段1',
  `reserved2` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段2',
  `reserved3` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段3',
  `reserved4` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段4',
  `reserved5` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段5',
  `reserved6` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段6',
  `reserved7` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段7',
  `reserved8` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备用字段8',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', 'admin', 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单ID',
  `reserved1` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段1',
  `reserved2` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段2',
  `reserved3` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('00d1d8b4fbb64ee5933f5db36aed7130', '1', '50201', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('028df5d0b6d9415fa0d09e49c427c0d6', '1', '50000', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('030f8969a205442d83ae4166a26e3441', '1', '50101', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0b76cccd8f8245aba29048c1c9a4f2aa', '1', '61400', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0d7a6d1a99c746df8bdc1b7ad51dc5ef', '1', '70302', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('21961e9301b445f88832bb0a70f53fb0', '1', '61200', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('2246eaab5f70466d9c9cf96850f6deea', '1', '50302', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('22945dc594a946cfbaa5a1430e06b93b', '1', '60800', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('23eaa462537e41a09912ee5b1af7e4cf', '1', '61900', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('24af804bc886445bb698cc6260e425d2', '1', '50403', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('260c5d894f574090bd99032beaee6f4a', '1', '70601', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('27e3cabe5c4740c3a2ef9b35b7626e8a', '1', '70300', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('2eacd91a21f8489cbd4490ac6f53fb14', '1', '70104', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('2fed8bfc123b409eaf03d3138ce6cb8e', '1', '70000', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('3161bd011446489eb06a9f4e38cb7986', '1', '70402', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('31bfd2fa6224411d9f2f21457e5dd5ff', '1', '70103', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('354013da3cc14d87a483ffc63218ae7d', '1', '50303', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('3be3420f2aef4eedbedce07c72bf4b01', '1', '50301', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('435f1362ab454e18998d52a92e26b655', '1', '60700', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('460096a1949b4613b648664dfa4a0f35', '1', '61600', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('4a51b57d40934310a09db66d1bc02745', '1', '60600', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('4d17196d4761480db47e86290b727ebb', '1', '70201', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('51eb4c03552542e195bccb49512d67dc', '1', '60500', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('5356584d710542f993f32cf81cb18bdb', '1', '60300', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('62eb3d14b5724fbea4d49d61a0f0115d', '1', '70600', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('634427121e0c45fc8edcbd744738cc2c', '1', '70400', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('63f48798f43c4a1a9275bfa7da2dc601', '1', '70401', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('6b033e4b44ff4897aa41676e9333d9b1', '1', '50100', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('6d06dd6e679c4ab9ae3e0805026beb34', '1', '70301', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('6d89ab53c38640248ad2f2626b2d4dfd', '1', '61800', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7c64d43a806b4777bfd5c7a3ca44a050', '1', '50402', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('845e1f8d5b144a309aac52d94ffc7283', '1', '61300', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('846c68f8b3204235a4a4fbf7cf242fbd', '1', '50300', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('949e0503d8884c58974c2c6cfcbff68e', '1', '61500', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9cafcbadf0e143c683f2d922e1cd7edb', '1', '70100', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9df7a9bd031a449c95d359359eaa1897', '1', '61100', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a1e1ddb6d8104bf9b2f357a7dda37d95', '1', '50202', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a3014c6136ca40aab29adbda650d67cb', '1', '60200', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('ac533f9ca5af4eeaac6b94a773017c50', '1', '50102', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('af05121a08d14a08ac42970591bd5717', '1', '60100', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b1fa8e563fbe4596acc17e363666622c', '1', '60000', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b378b987da334ffd881163cc592ffb30', '1', '50401', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b63ad23a0a7e437db972371a44f9759c', '1', '70200', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b86dcd80bb1841edb6e29ff446de0e0b', '1', '70101', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('ba1819f81a3344d39e6ed61dede20468', '1', '61700', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('bd7ceda0157f4bf9bd9859cc8fd92ca0', '1', '61000', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('c3cde30d98264157b0b573d53dd1b729', '1', '50203', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('c9ef87eea248472ca5f7cf1ba219e6ba', '1', '50400', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('d7ca09a1987640b6b22d9c848c7044e8', '1', '70602', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('db062202a3684149aa5fb96371569a5e', '1', '60400', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('dbaa8c7c96ce44aba9a4c4c31ede608b', '1', '50103', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('df39edb25e5643f7bc4df61681ca151a', '1', '70202', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('e92d37166eec4a01b585368168717f18', '1', '50200', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('eec6a8e81eb94c598960f45b6ad98669', '1', '1', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f7b8919569994618a55e505442ccc427', '1', '70500', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f8aa9c621bcc4ec1bab380b5a5aad266', '1', '70501', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('fab65a61249141db98a1f3de4830cd53', '1', '70102', NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('fdddb52e79fb4b89a45e41278c0e6091', '1', '60900', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_trace_log
-- ----------------------------
DROP TABLE IF EXISTS `t_trace_log`;
CREATE TABLE `t_trace_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consume_time` int(11) DEFAULT NULL COMMENT '系统响应耗时',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求url',
  `class_method` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '类、方法位置',
  `request_content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求内容',
  `response_content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '返回内容',
  `remote_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户端地址',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者id',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录密码',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户真实姓名',
  `pingyin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名拼音，用于快速搜索',
  `sex` int(1) NOT NULL COMMENT '性别',
  `idcard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号',
  `birth_date` datetime(0) DEFAULT NULL COMMENT '出生日期',
  `duty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职务',
  `duty_lev` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职级',
  `phonenum` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '固定电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭地址',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `lock` int(1) DEFAULT NULL COMMENT '账号是否冻结状态',
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属公司',
  `unlock_time` datetime(0) DEFAULT NULL COMMENT '下次解冻时间',
  `last_login_time` datetime(0) DEFAULT NULL COMMENT '最近一次的登录时间',
  `last_upd_passwd_time` datetime(0) DEFAULT NULL COMMENT '上次修改密码的时间，后续进行密码过期提醒',
  `history_password` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '历史密码，多条记录使用逗号分隔。',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者id',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????????????,??????????????????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('022980dbecca46e7ae64f0d3e4110e98', 'yh6j2', 'fJebAXHSAqOpSDVpl7udFQ==', 'yh2j', NULL, 'yh2j', 1, '1243344', '2019-05-12 00:00:00', '', '', '110', '110', NULL, NULL, 2, 1, NULL, NULL, NULL, '2019-08-22 11:26:44', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('11f0c18cbdd1487ebddfc371e2e03128', 'yh22j', 'fJebAXHSAqOpSDVpl7udFQ==', 'yh22j', NULL, 'yh22j', 1, '1243344', '2019-05-12 00:00:00', '', '', '110', '110', NULL, NULL, 2, 1, NULL, NULL, NULL, '2019-09-03 19:25:55', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('5c42acc22d154b2b8343981a3de332ec', 'admin', 'RJBwyQ24wUpITQKa40hCUg==', 'admin', NULL, '321081199301043317', 1, NULL, NULL, '', '', '18115124359', '', NULL, NULL, 0, 1, NULL, NULL, '2019-09-06 17:03:38', '2019-08-22 15:55:01', 'RJBwyQ24wUpITQKa40hCUg==,RJBwyQ24wUpITQKa40hCUg==,RJBwyQ24wUpITQKa40hCUg==,fJebAXHSAqOpSDVpl7udFQ==,fJebAXHSAqOpSDVpl7udFQ==,123,123,nullfJebAXHSAqOpSDVpl7udFQ==,RJBwyQ24wUpITQKa40hCUg==,RJBwyQ24wUpITQKa40hCUg==,ggVwQOmWdpihS7lB7ebsBg==,RJBwyQ24wUpITQKa40hCUg==,fJebAXHSAqOpSDVpl7udFQ==,RJBwyQ24wUpITQKa40hCUg==,', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色ID',
  `reserved1` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段1',
  `reserved2` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段2',
  `reserved3` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '保留字段3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('0b400779f5774ae6ac982ddb6aae2fdb', '022980dbecca46e7ae64f0d3e4110e98', '2', NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('0f5673151dd64405aff3d560d1d7c8bb', '11f0c18cbdd1487ebddfc371e2e03128', '2', NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('2ec45953-9fd9-454a-b5d1-4835ac69ed76', '4fb35b7f-d155-4c62-9e2b-17c42fde79bb', '2', NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('8e0446ee-0dc4-4f1c-a9be-9a221a3000be', '05171497-d905-47c3-8e51-74d1173b4597', '2', NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('d58726da-73ee-4591-ab7a-6981a1186909', '6da3309b-40e8-463d-9396-5ec962db3cd1', '2', NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('fb5410767ea24998b74a0e6a3d6b909f', '5c42acc22d154b2b8343981a3de332ec', '1', NULL, NULL, NULL);

-- ----------------------------
-- Function structure for getDepartmentChild
-- ----------------------------
DROP FUNCTION IF EXISTS `getDepartmentChild`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `getDepartmentChild`(parentId VARCHAR(1000)) RETURNS varchar(20000) CHARSET utf8
BEGIN
	DECLARE ptemp LONGTEXT;
	DECLARE ctemp LONGTEXT;
	
	SET ptemp = '#';
	SET ctemp = parentId;
	WHILE ctemp IS NOT NULL DO
		SET ptemp = CONCAT(ptemp,',',ctemp);
		SELECT GROUP_CONCAT(id) INTO ctemp FROM t_department
			WHERE FIND_IN_SET(parent_id,ctemp)>0;
	END WHILE;
	
	RETURN ptemp;
	
    END
;;
delimiter ;

-- ----------------------------
-- Function structure for getMeunChild
-- ----------------------------
DROP FUNCTION IF EXISTS `getMeunChild`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `getMeunChild`(parentId VARCHAR(1000)) RETURNS varchar(20000) CHARSET utf8
BEGIN
	DECLARE ptemp LONGTEXT;
	DECLARE ctemp LONGTEXT;
	
	SET ptemp = '#';
	SET ctemp = parentId;
	WHILE ctemp IS NOT NULL DO
		SET ptemp = CONCAT(ptemp,',',ctemp);
		SELECT GROUP_CONCAT(id) INTO ctemp FROM t_menu_resource
			WHERE FIND_IN_SET(parent_id,ctemp)>0;
	END WHILE;
	
	RETURN ptemp;
	
    END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
