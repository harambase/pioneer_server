/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.18 : Database - pioneer_db
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

/*Table structure for table `Advise` */

DROP TABLE IF EXISTS `Advise`;

CREATE TABLE `Advise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `studentid` varchar(20) NOT NULL COMMENT '学生编号',
  `facultyid` varchar(20) NOT NULL COMMENT '教师编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Advise` */

LOCK TABLES `Advise` WRITE;

UNLOCK TABLES;

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
  `startDate` varchar(20) DEFAULT NULL COMMENT '起时间YYYY-MM-DD',
  `endDate` varchar(20) DEFAULT NULL COMMENT '终时间YYYY-MM-DD',
  `day` varchar(20) DEFAULT NULL COMMENT 'MWF/TTR',
  `startTime` varchar(20) DEFAULT NULL COMMENT '起时间HH:MM:SS',
  `endTime` varchar(20) DEFAULT NULL COMMENT '终时间HH:MM:SS',
  `capa` int(11) DEFAULT NULL COMMENT '最大人数',
  `status` varchar(11) DEFAULT NULL COMMENT '状态',
  `facultyid` varchar(20) NOT NULL COMMENT '教师ID',
  `info` varchar(20) NOT NULL,
  `createtime` varchar(20) DEFAULT NULL,
  `updatetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `Course` */

LOCK TABLES `Course` WRITE;

insert  into `Course`(`id`,`crn`,`name`,`credits`,`precrn`,`couLev`,`couSec`,`startDate`,`endDate`,`day`,`startTime`,`endTime`,`capa`,`status`,`facultyid`,`info`,`createtime`,`updatetime`) values (1,'120170100','test',4,NULL,'100','01','2017-08-11','2017-10-10','m/w/f/sa/','10:00:00','11:00:00',50,'1','9201701102','2017-01','2017-08-10 12:00:00','2017-08-11 16:23:27'),(2,'120170164','Test2',4,NULL,'200','01','2017-09-01','2017-12-31','t/tr/','10:00:00','11:00:00',50,'1','9201701100','2017-01','2017-08-10 15:55:53','2017-08-11 16:13:19'),(3,'120170123','Test3',4,'120170164','300','02','2017-09-01','2017-12-31','t/tr/','10:00:00','11:00:00',50,'1','9201701100','2017-01','2017-08-10 17:06:39','2017-08-10 17:06:39');

UNLOCK TABLES;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `Person` */

LOCK TABLES `Person` WRITE;

insert  into `Person`(`id`,`userid`,`username`,`firstname`,`lastname`,`password`,`info`,`birthday`,`email`,`tel`,`qq`,`weChat`,`dorm`,`gender`,`createTime`,`updateTime`,`status`,`type`) values (1,'9000000000','admin','f','f','123456','','0000-00-00','ZcZXC','','f',NULL,'','male','2017-08-08 07:00:00','2017-08-08 07:00:01','1','a'),(2,'9201701687','admLShilei','Shilei','Lin','adm1201701687','2017-01','1996-11-12','lin.shilei@outlook.com',NULL,'1343214384',NULL,'','male','2017-08-08 12:44:23','2017-08-08 12:44:24','1','a'),(3,'9201701100','testTeacher','first','last','pioneer9201701100','2017-01','1980-01-01','testTeacher@pionner.com',NULL,NULL,NULL,NULL,'female','2017-08-08 13:33:23','2017-08-10 12:44:24','1','f'),(4,'9201701101','testStudent','first1','last1','pioneer9201701101','2017-01','1997-01-01','testStudent@pionner.com',NULL,NULL,NULL,NULL,'female','2017-08-08 13:33:24','2017-08-10 12:22:12','1','s'),(5,'9201701102','testTeacher2','first','last2','pioneer9201701102','2017-01','1980-01-01','testTeacher2@pioneer.com',NULL,NULL,NULL,NULL,'male','2017-08-10 18:00:00','2017-08-10 18:00:01','1','f'),(6,'9201701103','testStudent','first','last3','pioneer9201701103','2017-01','1997-01-01','testStudent@pioneer.com',NULL,NULL,NULL,NULL,'male','2017-08-10 18:00:00','2017-08-10 18:00:01','1','s'),(7,'9201701942','Tdisable','disable','Test','pioneer9201701942','2017-01','1980-07-07','4564@qq.com',NULL,'4564',NULL,'78','male','2017-08-14 14:23:13','2017-08-14 14:30:14','1','s'),(8,'9201701309','ttestStu3','testStu3','test','pioneer9201701309','2017-01','1992-01-01','564654@qq.com',NULL,'456464',NULL,'4654','female','2017-08-14 15:51:55','2017-08-14 15:51:55','1','s');

UNLOCK TABLES;

/*Table structure for table `Student` */

DROP TABLE IF EXISTS `Student`;

CREATE TABLE `Student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `studentid` varchar(100) NOT NULL,
  `max_credits` int(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `Student` */

LOCK TABLES `Student` WRITE;

insert  into `Student`(`id`,`studentid`,`max_credits`) values (1,'9201701101',18),(2,'9201701103',18),(3,'9201701942',18),(4,'9201701309',18);

UNLOCK TABLES;

/*Table structure for table `Transcript` */

DROP TABLE IF EXISTS `Transcript`;

CREATE TABLE `Transcript` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `studentid` varchar(20) NOT NULL COMMENT '学生ID',
  `crn` varchar(20) NOT NULL COMMENT '课程ID',
  `grade` varchar(20) NOT NULL COMMENT '成绩',
  `complete` varchar(20) NOT NULL COMMENT '完成状态',
  `assigntime` varchar(20) NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `Transcript` */

LOCK TABLES `Transcript` WRITE;

insert  into `Transcript`(`id`,`studentid`,`crn`,`grade`,`complete`,`assigntime`) values (1,'9201701103','120170100','A','In Progress','2017-08-14 13:59:47'),(2,'9201701309','120170100','*','In Progress','2017-08-14 17:26:39'),(4,'9201701103','120170164','*','In Progress','2017-08-14 17:40:18');

UNLOCK TABLES;

/*Table structure for table `StudentView` */

DROP TABLE IF EXISTS `StudentView`;

/*!50001 DROP VIEW IF EXISTS `StudentView` */;
/*!50001 DROP TABLE IF EXISTS `StudentView` */;

/*!50001 CREATE TABLE  `StudentView`(
 `id` int(11) unsigned NOT NULL  default '0' ,
 `studentid` varchar(100) NOT NULL ,
 `max_credits` int(20) unsigned NOT NULL ,
 `lastname` varchar(100) NOT NULL ,
 `firstname` varchar(100) NOT NULL ,
 `complete` decimal(32,0) NULL ,
 `incomplete` decimal(32,0) NULL ,
 `inprocess` decimal(32,0) NULL 
)*/;

/*Table structure for table `TranscriptView` */

DROP TABLE IF EXISTS `TranscriptView`;

/*!50001 DROP VIEW IF EXISTS `TranscriptView` */;
/*!50001 DROP TABLE IF EXISTS `TranscriptView` */;

/*!50001 CREATE TABLE  `TranscriptView`(
 `id` int(11) NOT NULL  default '0' ,
 `studentid` varchar(20) NOT NULL ,
 `crn` varchar(20) NOT NULL ,
 `grade` varchar(20) NOT NULL ,
 `complete` varchar(20) NOT NULL ,
 `coursename` varchar(100) NOT NULL ,
 `facultyid` varchar(20) NOT NULL ,
 `sfirst` varchar(100) NOT NULL ,
 `slast` varchar(100) NOT NULL ,
 `ffirst` varchar(100) NOT NULL ,
 `flast` varchar(100) NOT NULL ,
 `credits` int(11) NULL ,
 `assigntime` varchar(20) NOT NULL 
)*/;

/*View structure for view StudentView */

/*!50001 DROP TABLE IF EXISTS `StudentView` */;
/*!50001 DROP VIEW IF EXISTS `StudentView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `StudentView` AS select `s`.`id` AS `id`,`s`.`studentid` AS `studentid`,`s`.`max_credits` AS `max_credits`,`p`.`lastname` AS `lastname`,`p`.`firstname` AS `firstname`,sum(`t1`.`credits`) AS `complete`,sum(`t2`.`credits`) AS `incomplete`,sum(`t3`.`credits`) AS `inprocess` from ((((`Student` `s` join `Person` `p`) join `TranscriptView` `t1`) join `TranscriptView` `t2`) join `TranscriptView` `t3`) where ((`p`.`userid` = `s`.`studentid`) and (`t1`.`studentid` = `s`.`studentid`) and (`t2`.`studentid` = `s`.`studentid`) and (`t3`.`studentid` = `s`.`studentid`)) group by `s`.`studentid` */;

/*View structure for view TranscriptView */

/*!50001 DROP TABLE IF EXISTS `TranscriptView` */;
/*!50001 DROP VIEW IF EXISTS `TranscriptView` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `TranscriptView` AS select `t`.`id` AS `id`,`t`.`studentid` AS `studentid`,`t`.`crn` AS `crn`,`t`.`grade` AS `grade`,`t`.`complete` AS `complete`,`c`.`name` AS `coursename`,`c`.`facultyid` AS `facultyid`,`p1`.`firstname` AS `sfirst`,`p1`.`lastname` AS `slast`,`p2`.`firstname` AS `ffirst`,`p2`.`lastname` AS `flast`,`c`.`credits` AS `credits`,`t`.`assigntime` AS `assigntime` from (((`Transcript` `t` join `Course` `c`) join `Person` `p1`) join `Person` `p2`) where ((`t`.`crn` = `c`.`crn`) and (`p1`.`userid` = `t`.`studentid`) and (`p2`.`userid` = `c`.`facultyid`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
