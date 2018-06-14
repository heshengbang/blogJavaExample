/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-11-02 22:45:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `DEPT_ID` int(11) NOT NULL,
  `DEPT_NAME` varchar(255) NOT NULL,
  `DEPT_NO` varchar(20) NOT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`),
  UNIQUE KEY `DEPT_NO` (`DEPT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('10', 'ACCOUNTING', 'D10', 'NEW YORK');
INSERT INTO `department` VALUES ('20', 'RESEARCH', 'D20', 'DALLAS');
INSERT INTO `department` VALUES ('30', 'SALES', 'D30', 'CHICAGO');
INSERT INTO `department` VALUES ('40', 'OPERATIONS', 'D40', 'BOSTON');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `EMP_ID` bigint(20) NOT NULL,
  `EMP_NAME` varchar(50) NOT NULL,
  `EMP_NO` varchar(20) NOT NULL,
  `HIRE_DATE` date NOT NULL,
  `IMAGE` longblob,
  `JOB` varchar(30) NOT NULL,
  `SALARY` float NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `MNG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`),
  UNIQUE KEY `EMP_NO` (`EMP_NO`),
  KEY `FK75C8D6AE269A3C9` (`DEPT_ID`),
  KEY `FK75C8D6AE6106A42` (`EMP_ID`),
  KEY `FK75C8D6AE13C12F64` (`MNG_ID`),
  CONSTRAINT `FK75C8D6AE13C12F64` FOREIGN KEY (`MNG_ID`) REFERENCES `employee` (`EMP_ID`),
  CONSTRAINT `FK75C8D6AE269A3C9` FOREIGN KEY (`DEPT_ID`) REFERENCES `department` (`DEPT_ID`),
  CONSTRAINT `FK75C8D6AE6106A42` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('7369', 'SMITH', 'E7369', '1980-12-17', null, 'CLERK', '800', '20', '7902');
INSERT INTO `employee` VALUES ('7499', 'ALLEN', 'E7499', '1981-02-20', null, 'SALESMAN', '1600', '30', '7698');
INSERT INTO `employee` VALUES ('7521', 'WARD', 'E7521', '1981-02-22', null, 'SALESMAN', '1250', '30', '7698');
INSERT INTO `employee` VALUES ('7566', 'JONES', 'E7566', '1981-04-02', null, 'MANAGER', '2975', '20', '7839');
INSERT INTO `employee` VALUES ('7654', 'MARTIN', 'E7654', '1981-09-28', null, 'SALESMAN', '1250', '30', '7698');
INSERT INTO `employee` VALUES ('7698', 'BLAKE', 'E7698', '1981-05-01', null, 'MANAGER', '2850', '30', '7839');
INSERT INTO `employee` VALUES ('7782', 'CLARK', 'E7782', '1981-06-09', null, 'MANAGER', '2450', '30', '7839');
INSERT INTO `employee` VALUES ('7788', 'SCOTT', 'E7788', '1987-04-19', null, 'ANALYST', '3000', '20', '7566');
INSERT INTO `employee` VALUES ('7839', 'KING', 'E7839', '1981-11-17', null, 'PRESIDENT', '5000', '10', null);
INSERT INTO `employee` VALUES ('7844', 'TURNER', 'E7844', '1981-09-08', null, 'SALESMAN', '1500', '30', '7698');
INSERT INTO `employee` VALUES ('7876', 'ADAMS', 'E7876', '1987-05-23', null, 'CLERK', '1100', '20', '7698');
INSERT INTO `employee` VALUES ('7900', 'ADAMS', 'E7900', '1981-12-03', null, 'CLERK', '950', '30', '7698');
INSERT INTO `employee` VALUES ('7902', 'FORD', 'E7902', '1981-12-03', null, 'ANALYST', '3000', '20', '7566');
INSERT INTO `employee` VALUES ('7934', 'MILLER', 'E7934', '1982-01-23', null, 'CLERK', '1300', '10', '7698');

-- ----------------------------
-- Table structure for `salary_grade`
-- ----------------------------
DROP TABLE IF EXISTS `salary_grade`;
CREATE TABLE `salary_grade` (
  `GRADE` int(11) NOT NULL,
  `HIGH_SALARY` float NOT NULL,
  `LOW_SALARY` float NOT NULL,
  PRIMARY KEY (`GRADE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary_grade
-- ----------------------------
INSERT INTO `salary_grade` VALUES ('1', '9999', '3001');

-- ----------------------------
-- Table structure for `timekeeper`
-- ----------------------------
DROP TABLE IF EXISTS `timekeeper`;
CREATE TABLE `timekeeper` (
  `Timekeeper_Id` varchar(36) NOT NULL,
  `Date_Time` datetime NOT NULL,
  `In_Out` char(1) NOT NULL,
  `EMP_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`Timekeeper_Id`),
  KEY `FK744D9BFF6106A42` (`EMP_ID`),
  CONSTRAINT `FK744D9BFF6106A42` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timekeeper
-- ----------------------------
