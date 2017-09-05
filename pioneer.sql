/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.17-log : Database - pioneer_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pioneer_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pioneer_db`;

/*Table structure for table `advise` */

DROP TABLE IF EXISTS `advise`;

CREATE TABLE `advise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `studentid` varchar(20) NOT NULL COMMENT '学生编号',
  `facultyid` varchar(20) NOT NULL COMMENT '教师编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `advise` */

LOCK TABLES `advise` WRITE;

insert  into `advise`(`id`,`studentid`,`facultyid`) values (1,'9201701103','9201701102'),(2,'9201701942','9201701100'),(4,'9201701309','9201701100'),(5,'9201701310','9201701100'),(6,'9201701101','9201701100'),(7,'9201701103','9201701100'),(8,'9201701310','9201701102'),(9,'9201701784','9201701102');

UNLOCK TABLES;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
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

/*Data for the table `course` */

LOCK TABLES `course` WRITE;

insert  into `course`(`id`,`crn`,`name`,`credits`,`precrn`,`couLev`,`couSec`,`classroom`,`startDate`,`endDate`,`day`,`startTime`,`endTime`,`capa`,`facultyid`,`info`,`createtime`,`updatetime`,`comment`) values (2,'120170164','Test2',4,NULL,'203','01','小教室','2017-09-01','2017-12-31','t/tr/','10:00:00','11:00:00',20,'9201701840','2017-01','2017-08-10 15:55:53','2017-09-03 00:00:12','qwerq2'),(3,'120170123','Test3',4,'120170164','300','02',NULL,'2017-09-01','2017-12-31','t/tr/','10:00:00','11:00:00',50,'9201701100','2017-01','2017-08-10 17:06:39','2017-08-10 17:06:39',NULL),(6,'120170174','upper',4,'','499','01',NULL,'2017-06-01','2017-06-02','m/w/f/','11:00:00','12:00:00',1,'9201701102','2017-01','2017-08-18 16:51:05','2017-08-18 16:51:05',NULL),(7,'120170149','delete',4,'','100','01','小教室','2017-09-01','2017-12-31','m/t/w/tr/f/','10:00:00','11:00:00',20,'9201701100','2017-01','2017-09-03 00:04:50','2017-09-03 00:04:50','qwe'),(8,'120170125','test4',4,'120170164','100','01','阶梯教室','2017-09-01','2017-12-31','m/w/f/','10:00:00','11:00:00',50,'9201701848','2017-01','2017-09-03 14:05:52','2017-09-03 14:05:52','');

UNLOCK TABLES;

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
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

/*Data for the table `person` */

LOCK TABLES `person` WRITE;

insert  into `person`(`id`,`userid`,`username`,`firstname`,`lastname`,`password`,`info`,`birthday`,`email`,`tel`,`qq`,`weChat`,`dorm`,`gender`,`createTime`,`updateTime`,`status`,`type`,`comment`) values (1,'9000000000','admin','system','adminitor','123456','','1955-01-01','admin@pioneer.edu','1333222111','123456','asdfasda','456','male','2017-08-08 07:00:00','2017-09-02 17:12:19','1','s/f/a/','asdgasdgdsfgs'),(2,'9201701687','admLShilei','Shilei','Lin','adm1201701687','2017-01','1996-11-12','lin.shilei@outlook.com',NULL,'1343214384',NULL,'','male','2017-08-08 12:44:23','2017-08-08 12:44:24','1','a/',NULL),(3,'9201701100','testTeacher','first','last','pioneer9201701100','2017-01','1980-01-01','testTeacher@pionner.com',NULL,NULL,NULL,NULL,'female','2017-08-08 13:33:23','2017-08-10 12:44:24','1','f/',NULL),(4,'9201701101','testStudent','first1','last1','pioneer9201701101','2017-01','1997-01-01','testStudent@pionner.com',NULL,NULL,NULL,NULL,'female','2017-08-08 13:33:24','2017-08-10 12:22:12','1','s/',NULL),(5,'9201701102','testTeacher2','first','last2','pioneer9201701102','2017-01','1980-01-01','testTeacher2@pioneer.com',NULL,NULL,NULL,NULL,'male','2017-08-10 18:00:00','2017-09-02 16:26:47','1','f/a/',NULL),(6,'9201701103','testStudent','first','last3','pioneer9201701103','2017-01','1997-01-01','testStudent@pioneer.com',NULL,NULL,NULL,NULL,'male','2017-08-10 18:00:00','2017-09-02 16:27:56','1','s/',NULL),(7,'9201701942','Tdisable','disable','Test','pioneer9201701942','2017-01','1980-07-07','4564@qq.com',NULL,'4564',NULL,'78','male','2017-08-14 14:23:13','2017-09-02 16:28:09','1','s/',NULL),(8,'9201701309','ttestStu3','testStu3','test','pioneer9201701309','2017-01','1992-01-01','564654@qq.com',NULL,'456464',NULL,'4654','female','2017-08-14 15:51:55','2017-09-02 16:28:12','1','s/',NULL),(9,'9201701310','rick','KOKIA','ERI','pioneer9201701310','2017-01','1992-01-01','54849684@qq.com','13385241458',NULL,NULL,NULL,'male','2017-08-14 15:51:55','2017-09-02 23:13:04','1','s/',NULL),(10,'9201701784','zhengsan784','三','张','Pioneer9201701784','2017-01','1996-12-31','1111@ee.edu',NULL,'123',NULL,'123','male','2017-09-01 21:23:00','2017-09-02 16:28:24','1','s/',NULL),(11,'9201701840','lili840','四','李','Pioneer9201701840','2017-01','1998-05-23','lisi@pioneer.edu','123','1234123','weca','12','f','2017-09-02 16:10:12','2017-09-03 13:35:28','0','f/a/','first'),(12,'9201701261','meimeili261','梅梅','李','Pioneer9201701261','2017-01','1992-01-01','123124',NULL,'1123',NULL,'123','f','2017-09-02 16:12:49','2017-09-02 16:12:49','1','s/','new Student'),(13,'9201701848','ww184','五','王','Pioneer9201701848','2017-01','1997-08-12','7897@qq.com',NULL,'7987','7897','7894','f','2017-09-03 13:32:21','2017-09-03 13:32:21','1','f/','新老师'),(14,'9201701876','ew876','二','王','Pioneer9201701876','2017-01','2000-09-17','sadfasf@qq.com',NULL,'sadfasd','67897','546','f','2017-09-03 13:56:50','2017-09-03 13:56:50','1','s/','');

UNLOCK TABLES;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `studentid` varchar(100) NOT NULL,
  `max_credits` int(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

LOCK TABLES `student` WRITE;

insert  into `student`(`id`,`studentid`,`max_credits`) values (1,'9201701101',18),(2,'9201701103',18),(3,'9201701942',18),(4,'9201701309',18),(5,'9201701310',18),(6,'9201701784',18),(7,'9201701261',18),(8,'9201701876',12);

UNLOCK TABLES;

/*Table structure for table `transcript` */

DROP TABLE IF EXISTS `transcript`;

CREATE TABLE `transcript` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `studentid` varchar(20) NOT NULL COMMENT '学生ID',
  `crn` varchar(20) NOT NULL COMMENT '课程ID',
  `grade` varchar(20) NOT NULL COMMENT '成绩',
  `complete` varchar(20) NOT NULL COMMENT '完成状态',
  `assigntime` varchar(20) NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `transcript` */

LOCK TABLES `transcript` WRITE;

insert  into `transcript`(`id`,`studentid`,`crn`,`grade`,`complete`,`assigntime`) values (15,'9201701101','120170164','B','In Progress','2017-08-25 16:03:22'),(16,'9201701103','120170164','A','In Progress','2017-08-24 09:34:40'),(17,'9201701942','120170164','*','In Progress','2017-08-23 17:47:54'),(18,'9201701309','120170164','B','Complete','2017-08-24 09:35:14'),(19,'9201701310','120170164','*','In Progress','2017-09-01 20:10:41'),(20,'9201701942','120170123','*','In Progress','2017-09-02 14:46:23'),(21,'9201701103','120170123','*','In Progress','2017-09-02 14:46:24'),(22,'9201701101','120170123','*','In Progress','2017-09-02 14:46:27'),(23,'9201701101','120170125','*','In Progress','2017-09-03 14:07:15'),(24,'9201701876','120170125','*','In Progress','2017-09-03 14:07:27');

UNLOCK TABLES;

/* Function  structure for function  `Get_Complete_Credits` */

/*!50003 DROP FUNCTION IF EXISTS `Get_Complete_Credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `Get_Complete_Credits`(sid varchar(100)) RETURNS int(11)
BEGIN
	DECLARE credits INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM TranscriptView t WHERE t.studentid = sid AND t.complete = 'Complete';
	
	if counts > 0 then
		SELECT SUM(t.credits) INTO credits FROM TranscriptView t WHERE t.studentid = sid and t.complete = 'Complete';
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
	
	SELECT COUNT(*) INTO counts FROM TranscriptView t WHERE t.studentid = sid AND t.complete = 'In Progress';
	
	IF counts > 0 THEN
		SELECT SUM(t.credits) INTO credits FROM TranscriptView t WHERE t.studentid = sid AND t.complete = 'In Progress';
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
	
	SELECT COUNT(*) INTO counts FROM TranscriptView t WHERE t.studentid = sid AND t.complete = 'Not Complete';
	
	IF counts > 0 THEN
		SELECT SUM(t.credits) INTO credits FROM TranscriptView t WHERE t.studentid = sid AND t.complete = 'Not Complete';
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

/*Table structure for table `adviseview` */

DROP TABLE IF EXISTS `adviseview`;

/*!50001 DROP VIEW IF EXISTS `adviseview` */;
/*!50001 DROP TABLE IF EXISTS `adviseview` */;

/*!50001 CREATE TABLE  `adviseview`(
 `id` int(11) NOT NULL  default '0' ,
 `studentid` varchar(20) NOT NULL ,
 `sfirst` varchar(100) NOT NULL ,
 `slast` varchar(100) NOT NULL ,
 `facultyid` varchar(20) NOT NULL ,
 `ffirst` varchar(100) NOT NULL ,
 `flast` varchar(100) NOT NULL 
)*/;

/*Table structure for table `courseview` */

DROP TABLE IF EXISTS `courseview`;

/*!50001 DROP VIEW IF EXISTS `courseview` */;
/*!50001 DROP TABLE IF EXISTS `courseview` */;

/*!50001 CREATE TABLE  `courseview`(
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

/*Table structure for table `studentview` */

DROP TABLE IF EXISTS `studentview`;

/*!50001 DROP VIEW IF EXISTS `studentview` */;
/*!50001 DROP TABLE IF EXISTS `studentview` */;

/*!50001 CREATE TABLE  `studentview`(
 `id` int(11) unsigned NOT NULL  default '0' ,
 `studentid` varchar(100) NOT NULL ,
 `max_credits` int(20) unsigned NOT NULL ,
 `lastname` varchar(100) NOT NULL ,
 `firstname` varchar(100) NOT NULL ,
 `complete` int(11) NULL ,
 `progress` int(11) NULL ,
 `incomplete` int(11) NULL 
)*/;

/*Table structure for table `transcriptview` */

DROP TABLE IF EXISTS `transcriptview`;

/*!50001 DROP VIEW IF EXISTS `transcriptview` */;
/*!50001 DROP TABLE IF EXISTS `transcriptview` */;

/*!50001 CREATE TABLE  `transcriptview`(
 `id` int(11) NOT NULL  default '0' ,
 `studentid` varchar(20) NOT NULL ,
 `sfirst` varchar(100) NOT NULL ,
 `slast` varchar(100) NOT NULL ,
 `crn` varchar(20) NOT NULL ,
 `coursename` varchar(100) NOT NULL ,
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

/*View structure for view adviseview */

/*!50001 DROP TABLE IF EXISTS `adviseview` */;
/*!50001 DROP VIEW IF EXISTS `adviseview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `adviseview` AS select `a`.`id` AS `id`,`a`.`studentid` AS `studentid`,`p1`.`firstname` AS `sfirst`,`p1`.`lastname` AS `slast`,`a`.`facultyid` AS `facultyid`,`p2`.`firstname` AS `ffirst`,`p2`.`lastname` AS `flast` from ((`advise` `a` join `person` `p1`) join `person` `p2`) where ((`p1`.`userid` = `a`.`studentid`) and (`p2`.`userid` = `a`.`facultyid`)) */;

/*View structure for view courseview */

/*!50001 DROP TABLE IF EXISTS `courseview` */;
/*!50001 DROP VIEW IF EXISTS `courseview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `courseview` AS select `c`.`id` AS `id`,`c`.`crn` AS `crn`,`c`.`name` AS `name`,`c`.`credits` AS `credits`,`c`.`couLev` AS `couLev`,`c`.`couSec` AS `couSec`,`c`.`capa` AS `capa`,`Get_Remain_Capa`(`c`.`crn`) AS `remain`,`c`.`facultyid` AS `facultyid`,`Get_Name`(`c`.`facultyid`) AS `faculty`,`Get_Course_Date`(`c`.`crn`) AS `date`,`Get_Course_Time`(`c`.`crn`) AS `Time`,`Get_Course_Status`(`c`.`crn`) AS `status`,`c`.`day` AS `day`,`c`.`updatetime` AS `updatetime` from `course` `c` */;

/*View structure for view studentview */

/*!50001 DROP TABLE IF EXISTS `studentview` */;
/*!50001 DROP VIEW IF EXISTS `studentview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `studentview` AS select `s`.`id` AS `id`,`s`.`studentid` AS `studentid`,`s`.`max_credits` AS `max_credits`,`p`.`lastname` AS `lastname`,`p`.`firstname` AS `firstname`,`Get_Complete_Credits`(`s`.`studentid`) AS `complete`,`Get_In_Progress_Credits`(`s`.`studentid`) AS `progress`,`Get_Not_Complete_Credits`(`s`.`studentid`) AS `incomplete` from (`student` `s` join `person` `p`) where (`p`.`userid` = `s`.`studentid`) */;

/*View structure for view transcriptview */

/*!50001 DROP TABLE IF EXISTS `transcriptview` */;
/*!50001 DROP VIEW IF EXISTS `transcriptview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `transcriptview` AS select `t`.`id` AS `id`,`t`.`studentid` AS `studentid`,`p1`.`firstname` AS `sfirst`,`p1`.`lastname` AS `slast`,`t`.`crn` AS `crn`,`c`.`name` AS `coursename`,`c`.`credits` AS `credits`,`t`.`grade` AS `grade`,`t`.`complete` AS `complete`,`c`.`facultyid` AS `facultyid`,`p2`.`firstname` AS `ffirst`,`p2`.`lastname` AS `flast`,`Get_Course_Date`(`t`.`crn`) AS `date`,`Get_Course_Time`(`t`.`crn`) AS `time`,`c`.`day` AS `day`,`t`.`assigntime` AS `assigntime` from (((`transcript` `t` join `course` `c`) join `person` `p1`) join `person` `p2`) where ((`t`.`crn` = `c`.`crn`) and (`p1`.`userid` = `t`.`studentid`) and (`p2`.`userid` = `c`.`facultyid`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
