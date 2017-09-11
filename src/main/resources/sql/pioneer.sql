/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.18 : Database - pioneer_2.0
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pioneer_2.0` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `pioneer_2.0`;

/*Table structure for table `Advise` */

DROP TABLE IF EXISTS `Advise`;

CREATE TABLE `Advise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `Studentid` varchar(20) NOT NULL COMMENT '学生编号',
  `facultyid` varchar(20) NOT NULL COMMENT '教师编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `Course` */

DROP TABLE IF EXISTS `Course`;

CREATE TABLE `Course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `crn` varchar(20) NOT NULL COMMENT '课程编号',
  `name` varchar(100) NOT NULL COMMENT '课程名',
  `credits` int(11) DEFAULT NULL COMMENT '学分',
  `precrn` varchar(20) DEFAULT NULL COMMENT '要求课程',
  `couLev` varchar(11) DEFAULT NULL COMMENT '课程等级',
  `couSec` varchar(11) DEFAULT NULL COMMENT '课程Section',
  `classroom` varchar(100) DEFAULT NULL COMMENT '教室',
  `startDate` varchar(20) DEFAULT NULL COMMENT '起时间YYYY-MM-DD',
  `endDate` varchar(20) DEFAULT NULL COMMENT '终时间YYYY-MM-DD',
  `day` varchar(20) DEFAULT NULL COMMENT 'MWF/TTR',
  `startTime` varchar(20) DEFAULT NULL COMMENT '起时间HH:MM:SS',
  `endTime` varchar(20) DEFAULT NULL COMMENT '终时间HH:MM:SS',
  `capa` int(11) DEFAULT NULL COMMENT '最大人数',
  `facultyid` varchar(20) NOT NULL COMMENT '教师ID',
  `info` varchar(20) NOT NULL,
  `createtime` varchar(20) DEFAULT NULL,
  `updatetime` varchar(20) DEFAULT NULL,
  `comment` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `Message` */

DROP TABLE IF EXISTS `Message`;

CREATE TABLE `Message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `senderid` varchar(50) NOT NULL COMMENT '发送者ID',
  `receiverid` varchar(50) DEFAULT NULL COMMENT '接收者ID',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `status` varchar(10) DEFAULT NULL COMMENT '已读、未读、草稿',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Table structure for table `Person` */

DROP TABLE IF EXISTS `Person`;

CREATE TABLE `Person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userid` varchar(20) NOT NULL COMMENT '主编号',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `firstname` varchar(100) NOT NULL COMMENT '名',
  `lastname` varchar(100) NOT NULL COMMENT '姓',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `info` varchar(100) NOT NULL COMMENT '信息',
  `birthday` varchar(100) DEFAULT NULL COMMENT '生日',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `tel` varchar(100) DEFAULT NULL COMMENT '电话号',
  `qq` varchar(100) DEFAULT NULL COMMENT 'qq号',
  `weChat` varchar(100) DEFAULT NULL COMMENT '微信号',
  `dorm` varchar(100) DEFAULT NULL COMMENT '宿舍号',
  `gender` varchar(20) NOT NULL COMMENT '性别',
  `createTime` varchar(100) NOT NULL COMMENT '创建时间',
  `updateTime` varchar(100) NOT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL COMMENT '状态',
  `type` varchar(20) NOT NULL COMMENT '属性',
  `comment` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Table structure for table `Role` */

DROP TABLE IF EXISTS `Role`;

CREATE TABLE `Role` (
  `id` int(11) NOT NULL,
  `userid` varchar(20) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `Student` */

DROP TABLE IF EXISTS `Student`;

CREATE TABLE `Student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Studentid` varchar(100) NOT NULL,
  `max_credits` int(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `TempCourse` */

DROP TABLE IF EXISTS `TempCourse`;

CREATE TABLE `TempCourse` (
  `id` int(11) NOT NULL,
  `crn` varchar(20) NOT NULL,
  `json` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `TempUser` */

DROP TABLE IF EXISTS `TempUser`;

CREATE TABLE `TempUser` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) NOT NULL,
  `user_json` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `Transcript` */

DROP TABLE IF EXISTS `Transcript`;

CREATE TABLE `Transcript` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `Studentid` varchar(20) NOT NULL COMMENT '学生ID',
  `crn` varchar(20) NOT NULL COMMENT '课程ID',
  `grade` varchar(20) NOT NULL COMMENT '成绩',
  `complete` varchar(20) NOT NULL COMMENT '完成状态',
  `assigntime` varchar(20) NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/* Function  structure for function  `Get_Complete_Credits` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Complete_Credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Complete_Credits`(sid varchar(100)) RETURNS int(11)
BEGIN
	DECLARE credits INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM TranscriptView t WHERE t.Studentid = sid AND t.complete = 'Complete';
	
	if counts > 0 then
		SELECT SUM(t.credits) INTO credits FROM TranscriptView t WHERE t.Studentid = sid and t.complete = 'Complete';
	end if;	
	
	RETURN credits;
END */$$
DELIMITER ;

/* Function  structure for function  `Get_Course_Date` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Course_Date` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Course_Date`(crn VARCHAR(20)) RETURNS varchar(100) CHARSET utf8
BEGIN
	
	DECLARE startdate VARCHAR(100);
	DECLARE enddate VARCHAR(100);
	DECLARE ctime VARCHAR(100);
	
	SELECT c.startdate, c.enddate INTO startdate,enddate FROM Course c WHERE c.crn = crn;
	SET ctime = CONCAT(startdate," to ",enddate);
	
	
	RETURN ctime ;
    END */$$
DELIMITER ;

/* Function  structure for function  `Get_Course_Status` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Course_Status` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Course_Status`(crn varchar(100)) RETURNS int(11)
BEGIN
	DECLARE enddate VARCHAR(100);
	DECLARE STATUS INT(11);
	
	SELECT c.enddate INTO enddate FROM Course c WHERE c.crn = crn;
	
	IF UNIX_TIMESTAMP(NOW())>= UNIX_TIMESTAMP(enddate) THEN
		SET STATUS = 0;
	else
		set status = 1;
	END IF;
	
	RETURN STATUS ;
    END */$$
DELIMITER ;

/* Function  structure for function  `Get_Course_Time` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Course_Time` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Course_Time`(crn VARCHAR(20)) RETURNS varchar(100) CHARSET utf8
BEGIN
	DECLARE starttime VARCHAR(100);
	DECLARE endtime VARCHAR(100);
	DECLARE ctime VARCHAR(100);
	
	SELECT c.starttime, c.endtime  INTO starttime,endtime FROM Course c WHERE c.crn = crn;
	SET ctime = CONCAT(starttime,"-",endtime);
	
	
	RETURN ctime ;
	
    END */$$
DELIMITER ;

/* Function  structure for function  `Get_In_Progress_Credits` */

/*!50003 DROP FUNCTION IF EXISTS `Get_In_Progress_Credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_In_Progress_Credits`(sid VARCHAR(100)) RETURNS int(11)
BEGIN
	DECLARE credits INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM TranscriptView t WHERE t.Studentid = sid AND t.complete = 'In Progress';
	
	IF counts > 0 THEN
		SELECT SUM(t.credits) INTO credits FROM TranscriptView t WHERE t.Studentid = sid AND t.complete = 'In Progress';
	END IF;	
	
	RETURN credits;
	
    END */$$
DELIMITER ;

/* Function  structure for function  `Get_Name` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Name` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Name`(id varchar(20)) RETURNS varchar(100) CHARSET utf8
BEGIN
	
	DECLARE firstname varchar(100);
	DECLARE lastname  VARCHAR(100);
	declare fname varchar(100);
	
	SELECT p.firstname, p.lastname INTO firstname, lastname FROM Person p WHERE p.userid = id;
	SET fname = CONCAT(lastname,",",firstname);
	
	
	RETURN fname;
    END */$$
DELIMITER ;

/* Function  structure for function  `Get_Not_Complete_Credits` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Not_Complete_Credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Not_Complete_Credits`(sid VARCHAR(100)) RETURNS int(11)
BEGIN
	DECLARE credits INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM TranscriptView t WHERE t.Studentid = sid AND t.complete = 'Not Complete';
	
	IF counts > 0 THEN
		SELECT SUM(t.credits) INTO credits FROM TranscriptView t WHERE t.Studentid = sid AND t.complete = 'Not Complete';
	END IF;	
		
    return credits;		
    END */$$
DELIMITER ;

/* Function  structure for function  `Get_Remain_Capa` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Remain_Capa` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Remain_Capa`(crn VARCHAR(20)) RETURNS int(11)
BEGIN
	
	DECLARE remain INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	DECLARE capa INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM TranscriptView t WHERE t.crn = crn;
	SElect c.capa INTO capa FROM Course c WHERE c.crn = crn;
	SET remain = capa - counts;
	
	RETURN remain;
    END */$$
DELIMITER ;

/*Table structure for table `AdviseView` */

DROP TABLE IF EXISTS `AdviseView`;

/*!50001 DROP VIEW IF EXISTS `AdviseView` */;
/*!50001 DROP TABLE IF EXISTS `AdviseView` */;

/*!50001 CREATE TABLE  `AdviseView`(
 `id` int(11) NOT NULL  default '0' ,
 `Studentid` varchar(20) NOT NULL ,
 `sfirst` varchar(100) NOT NULL ,
 `slast` varchar(100) NOT NULL ,
 `facultyid` varchar(20) NOT NULL ,
 `ffirst` varchar(100) NOT NULL ,
 `flast` varchar(100) NOT NULL 
)*/;

/*Table structure for table `CourseView` */

DROP TABLE IF EXISTS `CourseView`;

/*!50001 DROP VIEW IF EXISTS `CourseView` */;
/*!50001 DROP TABLE IF EXISTS `CourseView` */;

/*!50001 CREATE TABLE  `CourseView`(
 `id` int(11) NOT NULL  default '0' ,
 `crn` varchar(20) NOT NULL ,
 `name` varchar(100) NOT NULL ,
 `credits` int(11) NULL ,
 `couLev` varchar(11) NULL ,
 `couSec` varchar(11) NULL ,
 `capa` int(11) NULL ,
 `remain` int(11) NULL ,
 `facultyid` varchar(20) NOT NULL ,
 `faculty` varchar(100) NULL ,
 `date` varchar(100) NULL ,
 `Time` varchar(100) NULL ,
 `status` int(11) NULL ,
 `day` varchar(20) NULL ,
 `updatetime` varchar(20) NULL 
)*/;

/*Table structure for table `StudentView` */

DROP TABLE IF EXISTS `StudentView`;

/*!50001 DROP VIEW IF EXISTS `StudentView` */;
/*!50001 DROP TABLE IF EXISTS `StudentView` */;

/*!50001 CREATE TABLE  `StudentView`(
 `id` int(11) unsigned NOT NULL  default '0' ,
 `Studentid` varchar(100) NOT NULL ,
 `max_credits` int(20) unsigned NOT NULL ,
 `lastname` varchar(100) NOT NULL ,
 `firstname` varchar(100) NOT NULL ,
 `complete` int(11) NULL ,
 `progress` int(11) NULL ,
 `incomplete` int(11) NULL 
)*/;

/*Table structure for table `TranscriptView` */

DROP TABLE IF EXISTS `TranscriptView`;

/*!50001 DROP VIEW IF EXISTS `TranscriptView` */;
/*!50001 DROP TABLE IF EXISTS `TranscriptView` */;

/*!50001 CREATE TABLE  `TranscriptView`(
 `id` int(11) NOT NULL  default '0' ,
 `Studentid` varchar(20) NOT NULL ,
 `sfirst` varchar(100) NOT NULL ,
 `slast` varchar(100) NOT NULL ,
 `crn` varchar(20) NOT NULL ,
 `Coursename` varchar(100) NOT NULL ,
 `credits` int(11) NULL ,
 `grade` varchar(20) NOT NULL ,
 `complete` varchar(20) NOT NULL ,
 `facultyid` varchar(20) NOT NULL ,
 `ffirst` varchar(100) NOT NULL ,
 `flast` varchar(100) NOT NULL ,
 `date` varchar(100) NULL ,
 `time` varchar(100) NULL ,
 `day` varchar(20) NULL ,
 `assigntime` varchar(20) NOT NULL 
)*/;

/*View structure for view AdviseView */

/*!50001 DROP TABLE IF EXISTS `AdviseView` */;
/*!50001 DROP VIEW IF EXISTS `AdviseView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `AdviseView` AS select `a`.`id` AS `id`,`a`.`Studentid` AS `Studentid`,`p1`.`firstname` AS `sfirst`,`p1`.`lastname` AS `slast`,`a`.`facultyid` AS `facultyid`,`p2`.`firstname` AS `ffirst`,`p2`.`lastname` AS `flast` from ((`Advise` `a` join `Person` `p1`) join `Person` `p2`) where ((`p1`.`userid` = `a`.`Studentid`) and (`p2`.`userid` = `a`.`facultyid`)) */;

/*View structure for view CourseView */

/*!50001 DROP TABLE IF EXISTS `CourseView` */;
/*!50001 DROP VIEW IF EXISTS `CourseView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `CourseView` AS select `c`.`id` AS `id`,`c`.`crn` AS `crn`,`c`.`name` AS `name`,`c`.`credits` AS `credits`,`c`.`couLev` AS `couLev`,`c`.`couSec` AS `couSec`,`c`.`capa` AS `capa`,`Get_Remain_Capa`(`c`.`crn`) AS `remain`,`c`.`facultyid` AS `facultyid`,`Get_Name`(`c`.`facultyid`) AS `faculty`,`Get_Course_Date`(`c`.`crn`) AS `date`,`Get_Course_Time`(`c`.`crn`) AS `Time`,`Get_Course_Status`(`c`.`crn`) AS `status`,`c`.`day` AS `day`,`c`.`updatetime` AS `updatetime` from `Course` `c` */;

/*View structure for view StudentView */

/*!50001 DROP TABLE IF EXISTS `StudentView` */;
/*!50001 DROP VIEW IF EXISTS `StudentView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `StudentView` AS select `s`.`id` AS `id`,`s`.`Studentid` AS `Studentid`,`s`.`max_credits` AS `max_credits`,`p`.`lastname` AS `lastname`,`p`.`firstname` AS `firstname`,`Get_Complete_Credits`(`s`.`Studentid`) AS `complete`,`Get_In_Progress_Credits`(`s`.`Studentid`) AS `progress`,`Get_Not_Complete_Credits`(`s`.`Studentid`) AS `incomplete` from (`Student` `s` join `Person` `p`) where (`p`.`userid` = `s`.`Studentid`) */;

/*View structure for view TranscriptView */

/*!50001 DROP TABLE IF EXISTS `TranscriptView` */;
/*!50001 DROP VIEW IF EXISTS `TranscriptView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `TranscriptView` AS select `t`.`id` AS `id`,`t`.`Studentid` AS `Studentid`,`p1`.`firstname` AS `sfirst`,`p1`.`lastname` AS `slast`,`t`.`crn` AS `crn`,`c`.`name` AS `Coursename`,`c`.`credits` AS `credits`,`t`.`grade` AS `grade`,`t`.`complete` AS `complete`,`c`.`facultyid` AS `facultyid`,`p2`.`firstname` AS `ffirst`,`p2`.`lastname` AS `flast`,`Get_Course_Date`(`t`.`crn`) AS `date`,`Get_Course_Time`(`t`.`crn`) AS `time`,`c`.`day` AS `day`,`t`.`assigntime` AS `assigntime` from (((`Transcript` `t` join `Course` `c`) join `Person` `p1`) join `Person` `p2`) where ((`t`.`crn` = `c`.`crn`) and (`p1`.`userid` = `t`.`Studentid`) and (`p2`.`userid` = `c`.`facultyid`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
