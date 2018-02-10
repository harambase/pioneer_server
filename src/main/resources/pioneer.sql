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

/*Table structure for table `advise` */

DROP TABLE IF EXISTS `advise`;

CREATE TABLE `advise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `student_id` varchar(20) NOT NULL COMMENT '学生编号',
  `faculty_id` varchar(20) NOT NULL COMMENT '教师编号',
  `status` varchar(20) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  `operator_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `advise` */

LOCK TABLES `advise` WRITE;

insert  into `advise`(`id`,`student_id`,`faculty_id`,`status`,`update_time`,`operator_id`) values (2,'9201701942','9201701100','1','2017-09-30 10:27:38','9201701000'),(4,'9201701309','9201701100','1','2017-09-30 10:27:38','9201701000'),(5,'9201701310','9201701100','1','2017-11-06 15:35:19','9201701000'),(6,'9201701101','9201701100','1','2017-09-30 10:27:38','9201701000'),(9,'9201701784','9201701102','1','2017-09-30 10:27:38','9201701000'),(10,'9201701942','9201702937','1','2017-11-06 16:01:53','9201701000');

UNLOCK TABLES;

/*Table structure for table `assignment` */

DROP TABLE IF EXISTS `assignment`;

CREATE TABLE `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assignment_id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `due_date` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `info` varchar(50) DEFAULT NULL,
  `attachment` text,
  `submission` text,
  `extend_column_1` varchar(255) DEFAULT NULL,
  `extend_column_2` varchar(255) DEFAULT NULL,
  `extend_column_3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `assignment` */

LOCK TABLES `assignment` WRITE;

UNLOCK TABLES;

/*Table structure for table `authority` */

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `authority` */

LOCK TABLES `authority` WRITE;

insert  into `authority`(`id`,`name`) values (1,'ROLE_USER'),(2,'ROLE_ADMIN');

UNLOCK TABLES;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `crn` varchar(20) NOT NULL COMMENT '课程编号',
  `name` varchar(100) NOT NULL COMMENT '课程名',
  `credits` int(11) DEFAULT NULL COMMENT '学分',
  `precrn` varchar(20) DEFAULT NULL COMMENT '要求课程',
  `level` varchar(11) DEFAULT NULL COMMENT '课程等级',
  `section` varchar(11) DEFAULT NULL COMMENT '课程Section',
  `classroom` varchar(100) DEFAULT NULL COMMENT '教室',
  `start_date` varchar(20) DEFAULT NULL COMMENT '起时间YYYY-MM-DD',
  `end_date` varchar(20) DEFAULT NULL COMMENT '终时间YYYY-MM-DD',
  `day` varchar(20) DEFAULT NULL COMMENT 'MWF/TTR',
  `start_time` varchar(20) DEFAULT NULL COMMENT '起时间HH:MM:SS',
  `end_time` varchar(20) DEFAULT NULL COMMENT '终时间HH:MM:SS',
  `capacity` int(11) DEFAULT NULL COMMENT '最大人数',
  `faculty_id` varchar(20) NOT NULL COMMENT '教师ID',
  `info` varchar(20) NOT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  `comment` text COMMENT '备注',
  `course_info` text,
  `assignment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

LOCK TABLES `course` WRITE;

insert  into `course`(`id`,`crn`,`name`,`credits`,`precrn`,`level`,`section`,`classroom`,`start_date`,`end_date`,`day`,`start_time`,`end_time`,`capacity`,`faculty_id`,`info`,`create_time`,`update_time`,`comment`,`course_info`,`assignment`) values (2,'120170164','Test2',4,'/','203','01','小教室','2017-09-01','2017-12-31','t/tr/','10:00:00','11:00:00',20,'9201701840','2017-01','2017-08-10 15:55:53','2018-01-03 17:21:37','qwerq2','',NULL),(3,'120170123','Test3',4,'120170164/','300','02','小教室','2017-09-01','2017-12-31','t/tr/','10:00:00','11:00:00',50,'9201701000','2017-01','2017-08-10 17:06:39','2017-08-10 17:06:39',NULL,NULL,NULL),(6,'120170174','upper',4,'/','499','01',NULL,'2017-06-01','2017-06-02','m/w/f/','11:00:00','12:00:00',1,'9201701102','2017-01','2017-08-18 16:51:05','2017-08-18 16:51:05',NULL,NULL,NULL),(7,'120170149','delete',4,'/','100','01','小教室','2017-09-01','2017-12-31','m/t/w/tr/f/','10:00:00','11:00:00',20,'9201701000','2017-01','2017-09-03 00:04:50','2017-09-03 00:04:50','qwe',NULL,NULL),(8,'120170125','test4',4,'120170164/','100','01','阶梯教室','2017-09-01','2017-12-31','m/w/f/','10:00:00','11:00:00',50,'9201701848','2017-01','2017-09-03 14:05:52','2017-09-03 14:05:52','',NULL,NULL),(9,'120170289','新课程',2,'120170164/120170125/','100','01','as','2018-10-01','2018-10-30','sa/s/','06:00:00','06:00:00',41,'9201701000','2017-02','2017-10-09 10:59:04','2017-12-21 12:49:07',NULL,NULL,NULL),(10,'120180238','Math2',1,'','123','12','1','2018-01-10','2018-01-31','s/sa/f/tr/','09:00:00','15:00:00',123,'9201701000','2018-02','2018-01-10 21:05:49','2018-01-10 21:05:49','asdfasdfasd','{\"path\":\"/static/upload/document/courseInfo/1/7/276d6ff8-97da-4f3d-a8d8-2a9935357d55.doc\",\"size\":45056,\"name\":\"先锋课程描述.doc\",\"type\":\"doc\"}',NULL),(11,'120170218','Computer Science',4,'','499','01','1','2018-01-10','2018-01-10','t/w/tr/','09:00:00','06:00:00',12,'9201701100','2017-02','2018-01-10 21:15:50','2018-01-10 21:15:51','asfasfdd','{\"path\":\"/static/upload/document/courseInfo/1/7/03406b18-6863-43f5-9bf9-bfdce9edc1c6.doc\",\"size\":45056,\"name\":\"先锋课程描述.doc\",\"type\":\"doc\"}',NULL);

UNLOCK TABLES;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sender_id` varchar(50) NOT NULL COMMENT '发送者ID',
  `subject` varchar(20) DEFAULT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `receiver_id` text COMMENT '接收者ID',
  `body` text COMMENT '内容',
  `status` varchar(10) DEFAULT NULL COMMENT '已读、未读、草稿',
  `date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `attachment` varchar(50) DEFAULT NULL,
  `tag` varchar(50) DEFAULT NULL,
  `labels` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

LOCK TABLES `message` WRITE;

insert  into `message`(`id`,`sender_id`,`subject`,`title`,`receiver_id`,`body`,`status`,`date`,`attachment`,`tag`,`labels`) values (49,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702937的请求注册信息','read','2017-09-20 13:37:44',NULL,'重要','important'),(50,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201703122的请求注册信息','read','2017-09-20 13:39:29',NULL,'紧急','important'),(54,'9201701000','用户注册','账户信息','9201702937/','您的接收到来自管理员的一条消息:你的用户已成功创建','unread','2017-09-22 16:58:04',NULL,'紧急','important'),(55,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702574的请求注册信息','read','2017-09-25 17:05:47',NULL,'紧急','important'),(56,'9201701000','用户注册','账户信息','9201703122/','您的接收到来自管理员的一条消息:你的用户已成功创建','unread','2017-09-25 17:07:45',NULL,'紧急','important'),(57,'9201701000','用户注册','账户信息','9201702574/','您的接收到来自管理员的一条消息:你的用户已成功创建','unread','2017-09-25 17:07:58',NULL,'紧急','important'),(58,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702884的请求注册信息','read','2017-09-25 17:09:52',NULL,'紧急','important'),(59,'9201701000','用户注册','账户信息','9201702884/','您的接收到来自管理员的一条消息:你的用户已成功创建','unread','2017-09-25 17:10:21',NULL,'紧急','important'),(60,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702887的请求注册信息','read','2017-09-30 10:19:10',NULL,'紧急','important'),(61,'9201701000','用户注册','账户信息','9201702887/','您的接收到来自管理员的一条消息:你的用户已成功创建','unread','2017-09-30 10:22:02',NULL,'紧急','important'),(62,'9201701000','用户注册','账户信息','9201702887/','您的接收到来自管理员的一条消息:你的用户已成功创建','unread','2017-09-30 10:26:52',NULL,'紧急','important'),(63,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702745的请求注册信息','saved','2017-09-30 11:40:30',NULL,'紧急',''),(64,'9201701000','用户注册','账户信息','9201702745/','您的接收到来自管理员的一条消息:你的用户已成功创建','saved','2017-09-30 11:42:36',NULL,'紧急',''),(65,'9201701000','用户注册','账户信息','9201702887/','您的接收到来自管理员的一条消息:你的用户已成功创建','saved','2017-09-30 11:49:34',NULL,'紧急',''),(66,'9201701000','用户注册','账户信息','9201702544/','您的接收到来自管理员的一条消息:你的用户已成功创建','unread','2017-09-30 11:51:45',NULL,'紧急',''),(67,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702226的请求注册信息','read','2017-09-30 13:54:41',NULL,'紧急',''),(68,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702370的请求注册信息','read','2017-10-11 15:53:59',NULL,'紧急',''),(69,'9201701000','用户注册','注册信息','9201701000/','注意!接收到来自9201702201的请求注册信息','trashed','2017-10-11 15:57:59',NULL,'紧急',''),(70,'9201701000','用户注册','注册信息','9201701000/9201702887/','注意!接收到来自9201702622的请求注册信息','saved','2017-10-11 15:59:21',NULL,'紧急',''),(77,'9201701000','Test','Test','9201701687/','TEST','unread','2017-11-22 22:21:32',NULL,'重要',NULL),(78,'9201701000','Test','Test','9201701000/','TEST','unread','2017-11-22 22:21:32',NULL,'重要',NULL),(79,'9201701000','TEST','TEST','9201701687/','TEST','unread','2017-11-22 22:22:17',NULL,'重要',NULL),(80,'9201701000','Test2','Test2','9201701261/','Test2','unread','2017-11-22 22:33:46',NULL,'重要',NULL),(81,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自9201702920的请求注册信息','UNREAD','2017-12-29 10:09:58',NULL,'work','inbox/important/'),(82,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820180117的请求注册信息','UNREAD','2018-01-10 11:40:50',NULL,'work','inbox/important/'),(83,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170211的请求注册信息','UNREAD','2018-01-10 17:10:41',NULL,'work','inbox/important/'),(84,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820180120的请求注册信息','UNREAD','2018-01-10 20:11:33',NULL,'work','inbox/important/'),(85,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170254的请求注册信息','UNREAD','2018-01-10 20:15:44',NULL,'work','inbox/important/'),(86,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820180230的请求注册信息','UNREAD','2018-01-10 20:19:54',NULL,'work','inbox/important/'),(87,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170339的请求注册信息','UNREAD','2018-01-10 20:22:49',NULL,'work','inbox/important/'),(88,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170359的请求注册信息','UNREAD','2018-01-10 20:25:25',NULL,'work','inbox/important/'),(89,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170349的请求注册信息','UNREAD','2018-01-10 20:27:23',NULL,'work','inbox/important/'),(90,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170396的请求注册信息','UNREAD','2018-01-10 20:28:14',NULL,'work','inbox/important/'),(91,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170258的请求注册信息','UNREAD','2018-01-10 20:33:09',NULL,'work','inbox/important/'),(92,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170352的请求注册信息','UNREAD','2018-01-10 20:35:52',NULL,'work','inbox/important/'),(93,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820170356的请求注册信息','UNREAD','2018-01-10 20:38:14',NULL,'work','inbox/important/');

UNLOCK TABLES;

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `user_id` varchar(20) NOT NULL COMMENT '主编号',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `first_name` varchar(100) NOT NULL COMMENT '名',
  `last_name` varchar(100) NOT NULL COMMENT '姓',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `status` varchar(20) NOT NULL COMMENT '状态',
  `info` varchar(100) NOT NULL COMMENT '信息',
  `type` varchar(20) NOT NULL COMMENT '属性',
  `role_id` varchar(50) DEFAULT NULL,
  `birthday` varchar(100) DEFAULT NULL COMMENT '生日',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `tel` varchar(100) DEFAULT NULL COMMENT '电话号',
  `qq` varchar(100) DEFAULT NULL COMMENT 'qq号',
  `we_chat` varchar(100) DEFAULT NULL COMMENT '微信号',
  `dorm` varchar(100) DEFAULT NULL COMMENT '宿舍号',
  `gender` varchar(20) NOT NULL COMMENT '性别',
  `create_time` varchar(100) NOT NULL COMMENT '创建时间',
  `update_time` varchar(100) NOT NULL COMMENT '修改时间',
  `base_info` text,
  `comment` text COMMENT '备注',
  `profile` text COMMENT '头像',
  `user_info` text,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `person` */

LOCK TABLES `person` WRITE;

insert  into `person`(`user_id`,`username`,`first_name`,`last_name`,`password`,`status`,`info`,`type`,`role_id`,`birthday`,`email`,`tel`,`qq`,`we_chat`,`dorm`,`gender`,`create_time`,`update_time`,`base_info`,`comment`,`profile`,`user_info`,`address`) values ('9201701000','root','admin','system','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/f/a/','0/1/','1980-01-01','admin@pioneer.edu','13220184951','1343214384','1343214384','','male','2017-08-08 07:00:00','2018-01-15 14:11:26',NULL,'请勿删除','{\"path\":\"/image/profile/1/6/7660d888-2539-4e56-a44d-1d2185595916.png\",\"size\":15842,\"name\":\"01.png\",\"type\":\"png\"}','{\"path\":\"/document/userInfo/5/9/c9cd23e0-44c0-403c-b214-699b79fe0ab0.xlsx\",\"size\":10498,\"name\":\"checkLis-20170112.xlsx\",\"type\":\"xlsx\"}',NULL),('9201701100','teacher1','teacher','1','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','f/','0/2/6/7/','1980-01-01','testTeacher@pionner.com','12323412342','123124','9201701000',NULL,'female','2017-08-08 13:33:23','2018-01-17 14:42:23',NULL,'asdsadfsdf','{\"path\":\"/image/profile/13/14/085785d1-3041-403a-8d76-51e0d98e503b.jpg\",\"size\":11476,\"name\":\"timg_副本.jpg\",\"type\":\"jpg\"}',NULL,NULL),('9201701101','student','student','0','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/','0/5/','1997-01-01','testStudent@pionner.com',NULL,NULL,NULL,NULL,'female','2017-08-08 13:33:24','2017-09-25 16:17:47',NULL,NULL,NULL,NULL,NULL),('9201701102','teacher3','teacher','3','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','f/a/','0/1/','1980-01-01','testTeacher2@pioneer.com',NULL,NULL,NULL,NULL,'male','2017-08-10 18:00:00','2017-09-25 16:18:45',NULL,NULL,NULL,NULL,NULL),('9201701261','student6','student','6','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/','0/','1992-01-01','123124',NULL,'1123',NULL,'123','female','2017-09-02 16:12:49','2017-09-25 16:18:21',NULL,'new Student',NULL,NULL,NULL),('9201701309','student3','student','3','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/','0/','1992-01-01','564654@qq.com',NULL,'456464',NULL,'4654','female','2017-08-14 15:51:55','2017-09-25 16:18:34',NULL,NULL,NULL,NULL,NULL),('9201701310','student4','student','4','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/','0/','1992-01-01','54849684@qq.com','13385241458',NULL,NULL,NULL,'male','2017-08-14 15:51:55','2017-09-25 16:18:31',NULL,NULL,NULL,NULL,NULL),('9201701687','admin2','admin2','system','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','a/','0/1/','1996-11-12','lin.shilei@outlook.com',NULL,'1343214384',NULL,'','male','2017-08-08 12:44:23','2017-09-25 16:16:28',NULL,NULL,NULL,NULL,NULL),('9201701784','student5','student','5','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/','0/','1996-12-31','1111@ee.edu',NULL,'123',NULL,'123','male','2017-09-01 21:23:00','2017-09-25 16:18:28',NULL,NULL,NULL,NULL,NULL),('9201701840','adminTeacher1','admin','teacher1','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','0','2017-01','f/a/','0/','1998-05-23','lisi@pioneer.edu','123','1234123','weca','12','female','2017-09-02 16:10:12','2017-09-25 16:18:25',NULL,'first',NULL,NULL,NULL),('9201701848','teacher4','teacher','4','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','f/','0/','1997-08-12','7897@qq.com',NULL,'7987','7897','7894','female','2017-09-03 13:32:21','2017-09-25 16:18:18',NULL,'新老师',NULL,NULL,NULL),('9201701876','student6','student','6','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/','0/','2000-09-17','sadfasf@qq.com',NULL,'sadfasd','67897','546','female','2017-09-03 13:56:50','2017-09-25 16:18:15',NULL,'',NULL,NULL,NULL),('9201701942','student2','student','2','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/','0/','1980-07-07','4564@qq.com',NULL,'4564',NULL,'78','male','2017-08-14 14:23:13','2017-09-25 16:18:38',NULL,NULL,NULL,NULL,NULL),('9201702544','teacher4','teacher','4','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-02','f/','0/','2017-09-07','asdfasfasdfasdf@qq.com',NULL,'123213324',NULL,NULL,'male','2017-09-30 11:51:45','2017-09-30 11:51:45',NULL,'',NULL,NULL,NULL),('9201702574','studentTeacher1','student','teacher1','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-02','s/f/','0/','2017-09-25','asdfasfasdfasdf@qq.com',NULL,'61244423',NULL,NULL,'male','2017-09-25 17:07:58','2017-09-25 17:07:58',NULL,'',NULL,NULL,NULL),('9201702745','admin3','admin','3','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-02','a/','0/','2017-09-07','adsfa@qq.com',NULL,'456498784784',NULL,NULL,'female','2017-09-30 11:42:36','2017-09-30 11:42:36',NULL,'',NULL,NULL,NULL),('9201702884','student7','student','7','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-02','s/','0/','2017-09-20','asdfasdfaf@qq.com',NULL,'659785412',NULL,NULL,'male','2017-09-25 17:10:21','2017-09-25 17:10:21',NULL,'',NULL,NULL,NULL),('9201702887','student8','student','8','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-02','s/','0/','2017-09-07','asdfasfasdfasdf@qq.com',NULL,'123213324',NULL,NULL,'male','2017-09-30 10:22:02','2017-09-30 10:22:02',NULL,'',NULL,NULL,NULL),('9201702937','adminTeacher2','admin','teacher2','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-02','f/a/','0/','2017-09-08','1343214384@qq.com',NULL,'1343214384',NULL,NULL,'male','2017-09-22 16:57:59','2017-09-25 16:18:11',NULL,'',NULL,NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `pin` */

DROP TABLE IF EXISTS `pin`;

CREATE TABLE `pin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pin` int(11) NOT NULL,
  `info` varchar(20) NOT NULL,
  `faculty_id` varchar(11) DEFAULT NULL,
  `student_id` varchar(20) DEFAULT NULL,
  `role` int(11) NOT NULL COMMENT '1：选课，2：成绩',
  `start_time` varchar(20) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `remark` text,
  `owner` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `pin` */

LOCK TABLES `pin` WRITE;

insert  into `pin`(`id`,`pin`,`info`,`faculty_id`,`student_id`,`role`,`start_time`,`end_time`,`create_time`,`remark`,`owner`) values (1,996864,'2017-01','9201701000',NULL,2,'2017-11-07 00:00:00','2017-11-25 00:00:00','2017-11-07 12:57:11','Test','system,admin'),(2,436668,'2017-01','9201701100',NULL,2,'2017-11-07 00:00:00','2017-11-07 00:00:00','2017-11-07 12:57:11','Test','1,teacher'),(3,123456,'2017-02',NULL,'9201701000',1,'2017-11-07 00:00:00','2018-11-25 00:00:00','2017-11-07 12:57:11','Test','system,admin'),(4,602351,'2017-01','9201701102',NULL,2,'2017-11-07 00:00:00','2017-11-07 00:00:00','2017-11-07 12:57:11','Test','3,teacher'),(5,979142,'2017-01',NULL,'9201701101',1,'2017-11-07 00:00:00','2017-11-07 00:00:00','2017-11-07 12:57:11','Test','0,student'),(6,609280,'2017-01','9201701848',NULL,2,'2017-11-07 00:00:00','2017-11-07 00:00:00','2017-11-07 12:57:11','Test','4,teacher'),(7,680038,'2017-01',NULL,'9201701103',1,'2017-11-07 00:00:00','2017-11-07 00:00:00','2017-11-07 12:57:11','Test',NULL),(8,978866,'2017-01',NULL,'9201701942',1,'2017-11-07 00:00:00','2017-11-07 00:00:00','2017-11-07 12:57:11','Test','2,student');

UNLOCK TABLES;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) DEFAULT NULL COMMENT '序号',
  `role_name` varchar(255) DEFAULT NULL COMMENT '管理名称',
  `role_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `role` */

LOCK TABLES `role` WRITE;

insert  into `role`(`id`,`role_id`,`role_name`,`role_code`) values (8,0,'用户','user'),(9,1,'管理员','admin'),(10,2,'教务','teach'),(11,3,'后勤','logistic'),(12,4,'系统','system'),(13,5,'学生','student'),(14,6,'教师','faculty'),(15,7,'导师','advisor');

UNLOCK TABLES;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` varchar(20) NOT NULL,
  `max_credits` int(11) NOT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

LOCK TABLES `student` WRITE;

insert  into `student`(`student_id`,`max_credits`,`update_time`) values ('9201701000',12,NULL),('9201701101',12,NULL),('9201701261',18,NULL),('9201701309',18,NULL),('9201701310',18,NULL),('9201701784',18,NULL),('9201701876',12,NULL),('9201701942',18,NULL),('9201702574',12,NULL),('9201702884',12,NULL),('9201703122',12,NULL);

UNLOCK TABLES;

/*Table structure for table `tempadvise` */

DROP TABLE IF EXISTS `tempadvise`;

CREATE TABLE `tempadvise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(255) DEFAULT NULL,
  `faculty_ids` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tempadvise` */

LOCK TABLES `tempadvise` WRITE;

UNLOCK TABLES;

/*Table structure for table `tempcourse` */

DROP TABLE IF EXISTS `tempcourse`;

CREATE TABLE `tempcourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crn` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `course_json` text NOT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `faculty_id` varchar(255) DEFAULT NULL,
  `operator_id` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `tempcourse` */

LOCK TABLES `tempcourse` WRITE;

insert  into `tempcourse`(`id`,`crn`,`status`,`course_json`,`create_time`,`faculty_id`,`operator_id`,`update_time`) values (1,'820180117','0','{\"level\":\"100\",\"endDate\":\"2018-01-29\",\"precrn\":\"120170125/\",\"section\":\"01\",\"classroom\":\"1\",\"capacity\":\"40\",\"credits\":\"4\",\"name\":\"English\",\"startTime\":\"06:00:00\",\"comment\":\"awerawer\",\"endTime\":\"07:00:00\",\"day\":\"t/tr/f/\",\"startDate\":\"2018-01-10\",\"info\":\"2018-01\"}','2018-01-10 11:40:50','9201701100',NULL,'2018-01-10 11:40:50'),(2,'820170211','0','{\"level\":\"111\",\"endDate\":\"2018-02-06\",\"precrn\":\"120170125/\",\"section\":\"12\",\"classroom\":\"1\",\"capacity\":\"11\",\"facultyId\":\"\",\"credits\":\"1\",\"name\":\"sadf\",\"startTime\":\"06:00:00\",\"comment\":\"sadfas\",\"endTime\":\"07:00:00\",\"courseInfo\":\"{\\\"path\\\":\\\"/static/upload/document/courseInfo/1/0/f30bf2e1-a3a4-46fc-a86d-40fbb048ea59.pdf\\\",\\\"size\\\":1561,\\\"name\\\":\\\"1.pdf\\\",\\\"type\\\":\\\"pdf\\\"}\",\"day\":\"sa/s/\",\"crn\":\"\",\"startDate\":\"2018-01-26\",\"info\":\"2017-02\"}','2018-01-10 17:10:41','9201701000','9201701000','2018-01-10 17:46:39'),(4,'820170254','0','{\"level\":\"111\",\"endDate\":\"2018-01-31\",\"precrn\":\"\",\"section\":\"02\",\"classroom\":\"2\",\"capacity\":\"50\",\"facultyId\":\"\",\"credits\":\"4\",\"name\":\"Math\",\"startTime\":\"06:00:00\",\"comment\":\"sdfasf\",\"endTime\":\"07:00:00\",\"courseInfo\":\"\",\"day\":\"t/w/tr/\",\"crn\":\"\",\"startDate\":\"2018-01-10\",\"info\":\"2017-02\"}','2018-01-10 20:15:44','9201701000',NULL,'2018-01-10 20:15:44'),(5,'820180230','1','{\"level\":\"123\",\"endDate\":\"2018-01-31\",\"precrn\":\"\",\"section\":\"12\",\"classroom\":\"1\",\"capacity\":\"123\",\"facultyId\":\"9201701000\",\"credits\":\"1\",\"name\":\"Math2\",\"startTime\":\"09:00:00\",\"comment\":\"asdfasdfasd\",\"endTime\":\"15:00:00\",\"courseInfo\":{\"path\":\"/static/upload/document/courseInfo/1/7/276d6ff8-97da-4f3d-a8d8-2a9935357d55.doc\",\"size\":45056,\"name\":\"先锋课程描述.doc\",\"type\":\"doc\"},\"day\":\"s/sa/f/tr/\",\"crn\":\"\",\"startDate\":\"2018-01-10\",\"info\":\"2018-02\"}','2018-01-10 20:19:54','9201701000','9201701000','2018-01-10 21:05:49'),(6,'820170339','0','{\"level\":\"123\",\"endDate\":\"2018-01-05\",\"precrn\":\"\",\"section\":\"12\",\"classroom\":\"213\",\"capacity\":\"1232\",\"facultyId\":\"\",\"credits\":\"1\",\"name\":\"123\",\"startTime\":\"09:00:00\",\"comment\":\"sadfasdfasdf\",\"endTime\":\"09:00:00\",\"courseInfo\":\"\",\"day\":\"sa/s/tr/w/\",\"crn\":\"\",\"startDate\":\"2018-01-03\",\"info\":\"2017-03\"}','2018-01-10 20:22:49','9201701000',NULL,'2018-01-10 20:22:49'),(7,'820170359','0','{\"level\":\"123\",\"endDate\":\"2018-01-05\",\"precrn\":\"\",\"section\":\"12\",\"classroom\":\"213\",\"capacity\":\"1232\",\"facultyId\":\"\",\"credits\":\"1\",\"name\":\"123\",\"startTime\":\"09:00:00\",\"comment\":\"sadfasdfasdf\",\"endTime\":\"09:00:00\",\"courseInfo\":\"\",\"day\":\"sa/s/tr/w/\",\"crn\":\"\",\"startDate\":\"2018-01-03\",\"info\":\"2017-03\"}','2018-01-10 20:25:25','9201701000',NULL,'2018-01-10 20:25:25'),(8,'820170349','-1','{\"level\":\"\",\"endDate\":\"\",\"precrn\":\"\",\"section\":\"\",\"classroom\":\"\",\"capacity\":\"\",\"facultyId\":\"9201701000\",\"credits\":\"\",\"name\":\"sadfas\",\"startTime\":\"\",\"comment\":\"信息不完整\",\"endTime\":\"\",\"courseInfo\":\"\",\"day\":\"\",\"crn\":\"\",\"startDate\":\"\",\"info\":\"2017-03\"}','2018-01-10 20:27:23','9201701000','9201701000','2018-01-10 21:00:58'),(9,'820170396','0','{\"level\":\"\",\"endDate\":\"\",\"precrn\":\"\",\"section\":\"\",\"classroom\":\"\",\"capacity\":\"\",\"facultyId\":\"\",\"credits\":\"\",\"name\":\"asdf\",\"startTime\":\"\",\"comment\":\"\",\"endTime\":\"\",\"courseInfo\":\"\",\"day\":\"\",\"crn\":\"\",\"startDate\":\"\",\"info\":\"2017-03\"}','2018-01-10 20:28:14','9201701000',NULL,'2018-01-10 20:28:14'),(10,'820170258','0','{\"level\":\"\",\"endDate\":\"\",\"precrn\":\"\",\"section\":\"\",\"classroom\":\"\",\"capacity\":\"\",\"facultyId\":\"\",\"credits\":\"\",\"name\":\"asdfasdf\",\"startTime\":\"\",\"comment\":\"asdfasfd\",\"endTime\":\"\",\"courseInfo\":\"\",\"day\":\"\",\"crn\":\"\",\"startDate\":\"\",\"info\":\"2017-02\"}','2018-01-10 20:33:09','9201701000',NULL,'2018-01-10 20:33:09'),(11,'820170352','0','{\"level\":\"\",\"endDate\":\"\",\"precrn\":\"\",\"section\":\"\",\"classroom\":\"\",\"capacity\":\"\",\"facultyId\":\"\",\"credits\":\"\",\"name\":\"asfa\",\"startTime\":\"\",\"comment\":\"sdfasdfasdf\",\"endTime\":\"\",\"courseInfo\":\"{\\\"path\\\":\\\"/static/upload/document/courseInfo/1/7/42f904b7-e8a9-4ca4-9137-59e585952dd0.doc\\\",\\\"size\\\":45056,\\\"name\\\":\\\"先锋课程描述.doc\\\",\\\"type\\\":\\\"doc\\\"}\",\"day\":\"m/t/\",\"crn\":\"\",\"startDate\":\"\",\"info\":\"2017-03\"}','2018-01-10 20:35:52','9201701000',NULL,'2018-01-10 20:36:44'),(12,'820170356','0','{\"level\":\"123\",\"endDate\":\"\",\"precrn\":\"\",\"section\":\"11\",\"classroom\":\"\",\"capacity\":\"12\",\"facultyId\":\"\",\"credits\":\"1\",\"name\":\"asdfasdf\",\"startTime\":\"\",\"comment\":\"asfd\",\"endTime\":\"\",\"courseInfo\":\"{\\\"path\\\":\\\"/static/upload/document/courseInfo/1/7/bb8c52b1-4f8f-4b82-80fb-27f3264d23f3.doc\\\",\\\"size\\\":45056,\\\"name\\\":\\\"先锋课程描述.doc\\\",\\\"type\\\":\\\"doc\\\"}\",\"day\":\"\",\"crn\":\"\",\"startDate\":\"\",\"info\":\"2017-03\"}','2018-01-10 20:38:14','9201701000',NULL,'2018-01-10 20:38:20');

UNLOCK TABLES;

/*Table structure for table `tempuser` */

DROP TABLE IF EXISTS `tempuser`;

CREATE TABLE `tempuser` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT '0（处理中）,1（通过）,-1(拒绝)',
  `user_json` text NOT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `operator_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tempuser` */

LOCK TABLES `tempuser` WRITE;

insert  into `tempuser`(`id`,`user_id`,`status`,`user_json`,`create_time`,`update_time`,`operator_id`) values (10,'9201702887','0','{\"qq\":\"123213324\",\"birthday\":\"2017-09-07\",\"firstName\":\"asdf\",\"password\":\"79f9f338abd398d724e15a17e2c6ee62\",\"gender\":\"male\",\"tel\":\"23141234123\",\"email\":\"asdfasfasdfasdf@qq.com\",\"info\":\"2017-02\",\"lastName\":\"sadf\"}','2017-09-30 10:27:38','2017-09-30 11:51:45','9201701000'),(11,'9201702745','1','{\"qq\":\"456498784784\",\"birthday\":\"2017-09-07\",\"firstName\":\"asdfasdf\",\"password\":\"533e37ee96dfcd0ed7639d69fc590095\",\"gender\":\"female\",\"tel\":\"21316584798\",\"email\":\"adsfa@qq.com\",\"info\":\"2017-02\",\"lastName\":\"asdfsad\"}','2017-09-30 11:40:30','2017-09-30 11:42:36','9201701000'),(12,'9201702226','-1','{\"qq\":\"78545678786545\",\"birthday\":\"2017-09-12\",\"firstName\":\"asdf\",\"password\":\"8e580c77b8d2fc2cdde5163039c07b4a\",\"gender\":\"female\",\"tel\":\"54657847543\",\"email\":\"asdf@qq.com\",\"info\":\"2017-02\",\"lastName\":\"asdfsad\"}','2017-09-30 13:54:41','2017-09-30 13:55:00','9201701000'),(13,'9201702370','0','{\"qq\":\"258476\",\"birthday\":\"2017-10-12\",\"firstName\":\"wang\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"gender\":\"female\",\"tel\":\"125635874\",\"email\":\"adminn@admin.com\",\"info\":\"2017-02\",\"lastName\":\"shit\"}','2017-10-11 15:53:59','2017-10-11 15:53:59','9201701000'),(14,'9201702201','0','{\"qq\":\"5546844\",\"birthday\":\"2017-10-03\",\"firstName\":\"wang\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"gender\":\"female\",\"tel\":\"8648486\",\"email\":\"admin1@ad.com\",\"info\":\"2017-02\",\"lastName\":\"shit\"}','2017-10-11 15:57:59','2017-10-11 15:57:59','9201701000'),(15,'9201702622','0','{\"qq\":\"5546844\",\"birthday\":\"2017-10-03\",\"firstName\":\"wang\",\"password\":\"d41d8cd98f00b204e9800998ecf8427e\",\"gender\":\"female\",\"tel\":\"8648486\",\"email\":\"admin1@ad.com\",\"info\":\"2017-02\",\"lastName\":\"shit\"}','2017-10-11 15:59:21','2017-10-11 15:59:21','9201701000'),(16,'9201702920','0','{\"qq\":\"691217991\",\"birthday\":\"2017-12-20\",\"lastName\":\"林\",\"firstName\":\"石磊\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"gender\":\"m\",\"tel\":\"12345678943\",\"email\":\"9201701000@asdasdf\",\"info\":\"2017-02\"}','2017-12-29 10:09:58','2017-12-29 10:09:58',NULL);

UNLOCK TABLES;

/*Table structure for table `transcript` */

DROP TABLE IF EXISTS `transcript`;

CREATE TABLE `transcript` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `student_id` varchar(20) NOT NULL COMMENT '学生ID',
  `crn` varchar(20) NOT NULL COMMENT '课程ID',
  `grade` varchar(20) NOT NULL COMMENT '成绩',
  `complete` varchar(20) NOT NULL COMMENT '完成状态',
  `assign_time` varchar(20) NOT NULL COMMENT '时间',
  `operator_id` varchar(50) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `FKnvmw209jvvfrlikqijuqa3xxi` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `transcript` */

LOCK TABLES `transcript` WRITE;

insert  into `transcript`(`id`,`student_id`,`crn`,`grade`,`complete`,`assign_time`,`operator_id`) values (15,'9201701101','120170164','B','1','2017-11-06 17:02:44','9201701000'),(16,'9201701103','120170164','A','0','2017-08-24 09:34:40','9201701000'),(17,'9201701942','120170164','*','0','2017-08-23 17:47:54','9201701000'),(18,'9201701309','120170164','B','1','2017-08-24 09:35:14','9201701000'),(19,'9201701310','120170164','*','0','2017-09-01 20:10:41','9201701000'),(20,'9201701942','120170123','*','0','2017-09-02 14:46:23','9201701000'),(21,'9201701103','120170123','*','0','2017-09-02 14:46:24','9201701000'),(22,'9201701101','120170123','*','0','2017-09-02 14:46:27','9201701000'),(23,'9201701101','120170125','*','0','2017-09-03 14:07:15','9201701000'),(24,'9201701876','120170125','F','-1','2017-09-03 14:07:27','9201701000'),(26,'9201701784','120170164','*','0','2017-10-16 20:58:45','9201701000'),(33,'9201701000','120170164','*','0','2017-11-23 17:58:52','9201701000'),(34,'9201701101','120170289','*','0','2017-12-21 11:42:41','9201701000');

UNLOCK TABLES;

/*Table structure for table `user_authority` */

DROP TABLE IF EXISTS `user_authority`;

CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  KEY `FKgvxjs381k6f48d5d2yi11uh89` (`authority_id`),
  KEY `FKhi46vu7680y1hwvmnnuh4cybx` (`user_id`),
  CONSTRAINT `FKgvxjs381k6f48d5d2yi11uh89` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`),
  CONSTRAINT `FKhi46vu7680y1hwvmnnuh4cybx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_authority` */

LOCK TABLES `user_authority` WRITE;

insert  into `user_authority`(`user_id`,`authority_id`) values (1,1),(2,1),(2,2);

UNLOCK TABLES;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

LOCK TABLES `users` WRITE;

insert  into `users`(`id`,`email`,`enabled`,`first_name`,`last_name`,`last_password_reset_date`,`password`,`phone_number`,`username`) values (1,'user@example.com','','Fan','Jin','2017-10-01 21:58:59','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',NULL,'user'),(2,'admin@example.com','','Jing','Xiao','2017-10-01 18:57:59','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',NULL,'admin');

UNLOCK TABLES;

/* Function  structure for function  `get_complete_credits` */

/*!50003 DROP FUNCTION IF EXISTS `get_complete_credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_complete_credits`(sid varchar(100)) RETURNS int(11)
BEGIN
	DECLARE credits INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM transcriptview t WHERE t.student_id = sid AND t.complete = '1';
	
	if counts > 0 then
		SELECT SUM(t.credits) INTO credits FROM transcriptview t WHERE t.student_id = sid and t.complete = '1';
	end if;	
	
	RETURN credits;
END */$$
DELIMITER ;

/* Function  structure for function  `get_course_date` */

/*!50003 DROP FUNCTION IF EXISTS `get_course_date` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_course_date`(crn VARCHAR(20)) RETURNS varchar(100) CHARSET utf8
BEGIN
	
	DECLARE startdate VARCHAR(100);
	DECLARE enddate VARCHAR(100);
	DECLARE ctime VARCHAR(100);
	
	SELECT c.start_date, c.end_date INTO startdate,enddate FROM course c WHERE c.crn = crn;
	SET ctime = CONCAT(startdate," to ",enddate);
	
	
	RETURN ctime ;
    END */$$
DELIMITER ;

/* Function  structure for function  `get_course_status` */

/*!50003 DROP FUNCTION IF EXISTS `get_course_status` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_course_status`(crn VARCHAR(100)) RETURNS int(11)
BEGIN
	DECLARE enddate VARCHAR(100);
	DECLARE startdate VARCHAR(100);
	DECLARE status INT(11);
	
	SELECT c.end_date, c.start_date INTO enddate, startdate FROM course c WHERE c.crn = crn;
	
	IF UNIX_TIMESTAMP(NOW())>= UNIX_TIMESTAMP(enddate) THEN
		SET status = -1;
	elseIF UNIX_TIMESTAMP(NOW())>= UNIX_TIMESTAMP(startdate) THEN
		set status = 0;
	ELSE
		SET status = 1;
	END IF;
	
	RETURN status ;
    END */$$
DELIMITER ;

/* Function  structure for function  `get_course_time` */

/*!50003 DROP FUNCTION IF EXISTS `get_course_time` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_course_time`(crn VARCHAR(20)) RETURNS varchar(100) CHARSET utf8
BEGIN
	DECLARE starttime VARCHAR(100);
	DECLARE endtime VARCHAR(100);
	DECLARE ctime VARCHAR(100);
	
	SELECT c.start_time, c.end_time  INTO starttime,endtime FROM course c WHERE c.crn = crn;
	SET ctime = CONCAT(starttime,"-",endtime);
	
	
	RETURN ctime ;
	
    END */$$
DELIMITER ;

/* Function  structure for function  `get_in_progress_credits` */

/*!50003 DROP FUNCTION IF EXISTS `get_in_progress_credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_in_progress_credits`(sid VARCHAR(100)) RETURNS int(11)
BEGIN
	DECLARE credits INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM transcriptview t WHERE t.student_id = sid AND t.complete = '0';
	
	IF counts > 0 THEN
		SELECT SUM(t.credits) INTO credits FROM transcriptview t WHERE t.student_id = sid AND t.complete = '0';
	END IF;	
	
	RETURN credits;
	
    END */$$
DELIMITER ;

/* Function  structure for function  `get_name` */

/*!50003 DROP FUNCTION IF EXISTS `get_name` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_name`(id varchar(20)) RETURNS varchar(100) CHARSET utf8
BEGIN
	
	DECLARE firstname varchar(100) CHARSET utf8;
	DECLARE lastname  VARCHAR(100) CHARSET utf8;
	declare fname varchar(100) CHARSET utf8;
	
	SELECT p.first_name, p.last_name INTO firstname, lastname FROM person p WHERE p.user_id = id;
	SET fname = CONCAT(lastname,",",firstname);
	
	
	RETURN fname;
    END */$$
DELIMITER ;

/* Function  structure for function  `get_not_complete_credits` */

/*!50003 DROP FUNCTION IF EXISTS `get_not_complete_credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_not_complete_credits`(sid VARCHAR(100)) RETURNS int(11)
BEGIN
	DECLARE credits INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM transcriptview t WHERE t.student_id = sid AND t.complete = '-1';
	
	IF counts > 0 THEN
		SELECT SUM(t.credits) INTO credits FROM transcriptview t WHERE t.student_id = sid AND t.complete = '-1';
	END IF;	
		
    return credits;		
    END */$$
DELIMITER ;

/* Function  structure for function  `get_remain_capa` */

/*!50003 DROP FUNCTION IF EXISTS `get_remain_capa` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_remain_capa`(crn VARCHAR(20)) RETURNS int(11)
BEGIN
	
	DECLARE remain INT DEFAULT 0;
	DECLARE counts INT DEFAULT 0;
	DECLARE capa INT DEFAULT 0;
	
	SELECT COUNT(*) INTO counts FROM transcriptview t WHERE t.crn = crn;
	SElect c.capacity INTO capa FROM course c WHERE c.crn = crn;
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
 `student_id` varchar(20) NOT NULL ,
 `sname` varchar(100) NULL ,
 `faculty_id` varchar(20) NOT NULL ,
 `fname` varchar(100) NULL ,
 `status` varchar(20) NULL ,
 `update_time` varchar(20) NULL ,
 `operator_id` varchar(50) NULL ,
 `oname` varchar(100) NULL 
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
 `precrn` varchar(20) NULL ,
 `level` varchar(11) NULL ,
 `section` varchar(11) NULL ,
 `classroom` varchar(100) NULL ,
 `start_date` varchar(20) NULL ,
 `end_date` varchar(20) NULL ,
 `day` varchar(20) NULL ,
 `start_time` varchar(20) NULL ,
 `end_time` varchar(20) NULL ,
 `capacity` int(11) NULL ,
 `info` varchar(20) NOT NULL ,
 `create_time` varchar(20) NULL ,
 `update_time` varchar(20) NULL ,
 `comment` text NULL ,
 `course_info` text NULL ,
 `faculty_id` varchar(20) NOT NULL ,
 `remain` int(11) NULL ,
 `faculty` varchar(100) NULL ,
 `date` varchar(100) NULL ,
 `time` varchar(100) NULL ,
 `status` int(11) NULL 
)*/;

/*Table structure for table `messageview` */

DROP TABLE IF EXISTS `messageview`;

/*!50001 DROP VIEW IF EXISTS `messageview` */;
/*!50001 DROP TABLE IF EXISTS `messageview` */;

/*!50001 CREATE TABLE  `messageview`(
 `id` int(20) NOT NULL  default '0' ,
 `sender_id` varchar(50) NOT NULL ,
 `receiver_id` text NULL ,
 `subject` varchar(20) NULL ,
 `title` varchar(255) NULL ,
 `body` text NULL ,
 `status` varchar(10) NULL ,
 `date` varchar(50) NULL ,
 `pic` text NULL ,
 `tel` varchar(100) NULL ,
 `email` varchar(100) NULL ,
 `attachment` varchar(50) NULL ,
 `labels` varchar(50) NULL ,
 `tag` varchar(50) NULL ,
 `sender` varchar(100) NULL ,
 `receiver` text NULL 
)*/;

/*Table structure for table `studentview` */

DROP TABLE IF EXISTS `studentview`;

/*!50001 DROP VIEW IF EXISTS `studentview` */;
/*!50001 DROP TABLE IF EXISTS `studentview` */;

/*!50001 CREATE TABLE  `studentview`(
 `student_id` varchar(20) NOT NULL ,
 `max_credits` int(11) NOT NULL ,
 `status` varchar(20) NOT NULL ,
 `sname` varchar(100) NULL ,
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
 `student_id` varchar(20) NOT NULL ,
 `crn` varchar(20) NOT NULL ,
 `cname` varchar(100) NOT NULL ,
 `credits` int(11) NULL ,
 `grade` varchar(20) NOT NULL ,
 `complete` varchar(20) NOT NULL ,
 `faculty_id` varchar(20) NOT NULL ,
 `info` varchar(20) NOT NULL ,
 `sname` varchar(100) NULL ,
 `fname` varchar(100) NULL ,
 `date` varchar(100) NULL ,
 `time` varchar(100) NULL ,
 `day` varchar(20) NULL ,
 `assign_time` varchar(20) NOT NULL ,
 `operator_id` varchar(50) NOT NULL ,
 `oname` varchar(100) NULL 
)*/;

/*View structure for view adviseview */

/*!50001 DROP TABLE IF EXISTS `adviseview` */;
/*!50001 DROP VIEW IF EXISTS `adviseview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `adviseview` AS select `a`.`id` AS `id`,`a`.`student_id` AS `student_id`,`get_name`(`a`.`student_id`) AS `sname`,`a`.`faculty_id` AS `faculty_id`,`get_name`(`a`.`faculty_id`) AS `fname`,`a`.`status` AS `status`,`a`.`update_time` AS `update_time`,`a`.`operator_id` AS `operator_id`,`get_name`(`a`.`operator_id`) AS `oname` from `advise` `a` */;

/*View structure for view courseview */

/*!50001 DROP TABLE IF EXISTS `courseview` */;
/*!50001 DROP VIEW IF EXISTS `courseview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `courseview` AS select `c`.`id` AS `id`,`c`.`crn` AS `crn`,`c`.`name` AS `name`,`c`.`credits` AS `credits`,`c`.`precrn` AS `precrn`,`c`.`level` AS `level`,`c`.`section` AS `section`,`c`.`classroom` AS `classroom`,`c`.`start_date` AS `start_date`,`c`.`end_date` AS `end_date`,`c`.`day` AS `day`,`c`.`start_time` AS `start_time`,`c`.`end_time` AS `end_time`,`c`.`capacity` AS `capacity`,`c`.`info` AS `info`,`c`.`create_time` AS `create_time`,`c`.`update_time` AS `update_time`,`c`.`comment` AS `comment`,`c`.`course_info` AS `course_info`,`c`.`faculty_id` AS `faculty_id`,`get_remain_capa`(`c`.`crn`) AS `remain`,`get_name`(`c`.`faculty_id`) AS `faculty`,`get_course_date`(`c`.`crn`) AS `date`,`get_course_time`(`c`.`crn`) AS `time`,`get_course_status`(`c`.`crn`) AS `status` from `course` `c` */;

/*View structure for view messageview */

/*!50001 DROP TABLE IF EXISTS `messageview` */;
/*!50001 DROP VIEW IF EXISTS `messageview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `messageview` AS select `m`.`id` AS `id`,`m`.`sender_id` AS `sender_id`,`m`.`receiver_id` AS `receiver_id`,`m`.`subject` AS `subject`,`m`.`title` AS `title`,`m`.`body` AS `body`,`m`.`status` AS `status`,`m`.`date` AS `date`,`s`.`profile` AS `pic`,`s`.`tel` AS `tel`,`s`.`email` AS `email`,`m`.`attachment` AS `attachment`,`m`.`labels` AS `labels`,`m`.`tag` AS `tag`,`get_name`(`m`.`sender_id`) AS `sender`,`m`.`receiver_id` AS `receiver` from (`message` `m` join `person` `s`) where (`s`.`user_id` = `m`.`sender_id`) */;

/*View structure for view studentview */

/*!50001 DROP TABLE IF EXISTS `studentview` */;
/*!50001 DROP VIEW IF EXISTS `studentview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `studentview` AS select `s`.`student_id` AS `student_id`,`s`.`max_credits` AS `max_credits`,`p`.`status` AS `status`,`get_name`(`s`.`student_id`) AS `sname`,`get_complete_credits`(`s`.`student_id`) AS `complete`,`get_in_progress_credits`(`s`.`student_id`) AS `progress`,`get_not_complete_credits`(`s`.`student_id`) AS `incomplete` from (`student` `s` join `person` `p`) where (`p`.`user_id` = `s`.`student_id`) */;

/*View structure for view transcriptview */

/*!50001 DROP TABLE IF EXISTS `transcriptview` */;
/*!50001 DROP VIEW IF EXISTS `transcriptview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `transcriptview` AS select `t`.`id` AS `id`,`t`.`student_id` AS `student_id`,`t`.`crn` AS `crn`,`c`.`name` AS `cname`,`c`.`credits` AS `credits`,`t`.`grade` AS `grade`,`t`.`complete` AS `complete`,`c`.`faculty_id` AS `faculty_id`,`c`.`info` AS `info`,`get_name`(`p1`.`user_id`) AS `sname`,`get_name`(`p2`.`user_id`) AS `fname`,`get_course_date`(`t`.`crn`) AS `date`,`get_course_time`(`t`.`crn`) AS `time`,`c`.`day` AS `day`,`t`.`assign_time` AS `assign_time`,`t`.`operator_id` AS `operator_id`,`get_name`(`t`.`operator_id`) AS `oname` from (((`transcript` `t` join `course` `c`) join `person` `p1`) join `person` `p2`) where ((`t`.`crn` = `c`.`crn`) and (`p1`.`user_id` = `t`.`student_id`) and (`p2`.`user_id` = `c`.`faculty_id`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
