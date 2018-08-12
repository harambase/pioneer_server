/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.5-10.2.6-MariaDB : Database - pioneer_2.0
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
  `info` varchar(255) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  `operator_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `advise` */

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
  `attachment` text DEFAULT NULL,
  `submission` text DEFAULT NULL,
  `extend_column_1` varchar(255) DEFAULT NULL,
  `extend_column_2` varchar(255) DEFAULT NULL,
  `extend_column_3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `assignment` */

/*Table structure for table `contract` */

DROP TABLE IF EXISTS `contract`;

CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_id` varchar(20) NOT NULL COMMENT '主编号',
  `owner_id` varchar(20) DEFAULT NULL COMMENT '用户名',
  `oname` varchar(100) DEFAULT NULL COMMENT '名',
  `info` varchar(100) DEFAULT NULL COMMENT '姓',
  `type` varchar(100) DEFAULT NULL COMMENT '密码',
  `init_date` varchar(100) DEFAULT NULL COMMENT '状态',
  `expire_date` varchar(100) DEFAULT NULL COMMENT '信息',
  `operator_id` varchar(100) DEFAULT NULL COMMENT '生日',
  `create_time` varchar(100) NOT NULL COMMENT '创建时间',
  `update_time` varchar(100) NOT NULL COMMENT '修改时间',
  `comment` text DEFAULT NULL COMMENT '备注',
  `contract_info` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `contract` */

insert  into `contract`(`id`,`contract_id`,`owner_id`,`oname`,`info`,`type`,`init_date`,`expire_date`,`operator_id`,`create_time`,`update_time`,`comment`,`contract_info`) values (15,'201808032488','9201701000','TEST2','2018-01','300','2018-08-03','2018-09-05','9201701000','2018-08-12 15:19:27','2018-08-12 15:19:27','asdasdfsdfasdf','');

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
  `comment` text DEFAULT NULL COMMENT '备注',
  `course_info` text DEFAULT NULL,
  `assignment` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`crn`,`name`,`credits`,`precrn`,`level`,`section`,`classroom`,`start_date`,`end_date`,`day`,`start_time`,`end_time`,`capacity`,`faculty_id`,`info`,`create_time`,`update_time`,`comment`,`course_info`,`assignment`) values (1,'120180157','英语基础课',1,'','100','课程','小教室','2018-03-01','2018-07-15','m/','09:00:00','11:00:00',5,'9201801590','2018-01','2018-08-03 01:55:41','2018-08-03 01:55:41','','',NULL),(2,'120180151','计算机编程',2,'','100','兴趣小组','小教室','2018-03-01','2018-07-15','t/f/','19:30:00','21:00:00',5,'9201801590','2018-01','2018-08-03 16:17:52','2018-08-03 17:36:06','','{\"path\":\"/pioneer/document/courseInfo/1/7/d72a49b3-6c71-47b5-822f-052cda43e68e.doc\",\"size\":45056,\"name\":\"先锋课程描述.doc\",\"type\":\"doc\"}',NULL),(6,'120180143','美术',1,'','100','课程','二楼','2018-03-01','2018-07-15','m/','11:00:00','11:00:00',20,'9201701000','2018-01','2018-08-03 20:53:05','2018-08-03 20:53:05','','{\"path\":\"/pioneer/document/courseInfo/0/9/b1957212-614d-4b29-b8f9-53b4a98474e9.png\",\"size\":4767916,\"name\":\"第三新成都.png\",\"type\":\"png\"}',NULL),(7,'120180167','项目式英语',2,'','100','课程','大教室','2018-03-01','2018-07-15','tr/','14:00:00','16:00:00',20,'9201701000','2018-01','2018-08-03 22:13:05','2018-08-03 22:13:05','','{\"path\":\"/pioneer/document/courseInfo/0/1/5d75f650-80a4-4253-87de-aed52bb8a37d.png\",\"size\":4767916,\"name\":\"第三新成都 (1).png\",\"type\":\"png\"}',NULL),(8,'120180125','Healthy Cooking',2,'','100','课程','学习中心','2018-03-01','2018-07-15','w/','14:00:00','16:00:00',25,'9201701000','2018-01','2018-08-03 22:19:11','2018-08-03 22:19:11','','{\"path\":\"/pioneer/document/courseInfo/0/1/ccf138df-1ba0-427b-a4da-f30ba21e082c.png\",\"size\":4767916,\"name\":\"第三新成都 (1).png\",\"type\":\"png\"}',NULL),(9,'120180150','理化实验',2,'','100','课程','','2018-03-01','2018-07-15','w/','14:00:00','16:00:00',10,'9201501154','2018-01','2018-08-05 21:55:03','2018-08-05 21:55:03','','',NULL),(10,'120180189','导师课',2,'','100','课程','','2018-03-01','2018-07-15','t/','14:00:00','16:00:00',10,'9201502803','2018-01','2018-08-08 16:47:02','2018-08-08 16:47:02','','',NULL);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sender_id` varchar(50) NOT NULL COMMENT '发送者ID',
  `subject` varchar(20) DEFAULT NULL COMMENT '主题',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `receiver_id` text DEFAULT NULL COMMENT '接收者ID',
  `body` text DEFAULT NULL COMMENT '内容',
  `status` varchar(10) DEFAULT NULL COMMENT '已读(read)、未读(unread)、草稿(draft)、删除(delete)',
  `date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `attachment` varchar(50) DEFAULT NULL COMMENT '附件',
  `tag` varchar(50) DEFAULT NULL COMMENT '标签组1（教务、系统、教学、普通）',
  `labels` varchar(50) DEFAULT NULL COMMENT '标签组2（重要、一般、紧急）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`sender_id`,`subject`,`title`,`receiver_id`,`body`,`status`,`date`,`attachment`,`tag`,`labels`) values (1,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户Lin, Shilei(8201801811)的注册申请！请尽快处理，谢谢！','unread','2018-07-30 00:20:25',NULL,'系统','重要'),(2,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户卢, 苇(8201801512)的注册申请！请尽快处理，谢谢！','unread','2018-07-30 00:22:05',NULL,'系统','重要'),(3,'9201701000','用户申请','用户创建','9201801590','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 00:05:14',NULL,'系统','重要'),(5,'9201701000','用户申请','用户创建','9201801164','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 00:06:47',NULL,'系统','重要'),(7,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户TEST, TEST2(8201801191)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 14:53:59',NULL,'系统','重要'),(8,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户虞, 晨煦(8201502790)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 15:08:46',NULL,'系统','重要'),(9,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户虞, 晨煦(8201502309)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 15:10:36',NULL,'系统','重要'),(10,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户何, 子宸(8201502712)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 15:18:54',NULL,'系统','重要'),(11,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户林, 永泰(8201603902)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 15:19:24',NULL,'系统','重要'),(12,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户傅, 天煜(8201702687)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 15:27:50',NULL,'系统','重要'),(13,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户姜, 旭(8201601902)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 17:04:01',NULL,'系统','重要'),(14,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户黄, 昊南(8201602144)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 17:12:11',NULL,'系统','重要'),(15,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户颜, 子睿(8201803437)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 18:16:32',NULL,'系统','重要'),(16,'9201701000','用户申请','用户创建','9201502825','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 19:11:06',NULL,'系统','重要'),(17,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 虞, 晨煦(9201502825) 批准已通过！','read','2018-08-02 19:11:06',NULL,'系统','重要'),(18,'9201701000','用户申请','用户创建','9201502154','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 20:20:35',NULL,'系统','重要'),(19,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 何, 子宸(9201502154) 批准已通过！','unread','2018-08-02 20:20:35',NULL,'系统','重要'),(20,'9201701000','用户申请','用户创建','9201603838','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 20:21:03',NULL,'系统','重要'),(21,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 林, 永泰(9201603838) 批准已通过！','unread','2018-08-02 20:21:03',NULL,'系统','重要'),(22,'9201701000','用户申请','用户创建','9201702636','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 20:21:31',NULL,'系统','重要'),(23,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 傅, 天煜(9201702636) 批准已通过！','unread','2018-08-02 20:21:31',NULL,'系统','重要'),(24,'9201701000','用户申请','用户创建','9201601212','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 20:22:00',NULL,'系统','重要'),(25,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 姜, 旭(9201601212) 批准已通过！','unread','2018-08-02 20:22:00',NULL,'系统','重要'),(26,'9201701000','用户申请','用户创建','9201602165','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 20:23:11',NULL,'系统','重要'),(27,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 黄, 昊南(9201602165) 批准已通过！','unread','2018-08-02 20:23:11',NULL,'系统','重要'),(28,'9201701000','用户申请','用户创建','9201803113','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 20:23:34',NULL,'系统','重要'),(29,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 颜, 子睿(9201803113) 批准已通过！','unread','2018-08-02 20:23:34',NULL,'系统','重要'),(30,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户罗, 配合(8201402742)的注册申请！请尽快处理，谢谢！','unread','2018-08-02 21:58:37',NULL,'系统','重要'),(31,'9201701000','用户申请','用户创建','9201402537','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-02 22:04:55',NULL,'系统','重要'),(32,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 罗, 培洪(9201402537) 批准已通过！','unread','2018-08-02 22:04:55',NULL,'系统','重要'),(33,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820180132的请求注册信息','UNREAD','2018-08-02 22:36:13',NULL,'work','inbox/important/'),(34,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820180190的请求注册信息','UNREAD','2018-08-02 22:58:41',NULL,'work','inbox/important/'),(35,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820180157的请求注册信息','UNREAD','2018-08-02 23:02:25',NULL,'work','inbox/important/'),(36,'9000000000','用户注册','注册信息','9000000000','注意!接收到来自820180143的请求注册信息','UNREAD','2018-08-03 21:01:05',NULL,'work','inbox/important/'),(37,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户唐, 振明(8201501290)的注册申请！请尽快处理，谢谢！','unread','2018-08-04 13:09:40',NULL,'系统','重要'),(38,'9201701000','用户申请','用户创建','9201501154','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-04 14:01:09',NULL,'系统','重要'),(39,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 唐, 振明(9201501154) 批准已通过！','unread','2018-08-04 14:01:09',NULL,'系统','重要'),(41,'9201701000','识别码','成绩录入识别码','9201701000','您接收到来自系统的一条消息:来自用户 9201501154 的成绩录入识别码368212 已生成！','unread','2018-08-04 16:55:02',NULL,'系统','重要'),(42,'9201701000','成绩录入','成绩录入的识别码(PIN)的信息','9201501154','您收到一条来自教务的信息：您用于本学期成绩录入的识别码（PIN）为：567722，有效期为：2018-08-04 00:00:00至2018-09-01 00:00:00, 如有问题请与教务人员联系！','unread','2018-08-04 18:23:53',NULL,'教务','紧急'),(43,'9201701000','识别码','成绩录入识别码','9201701000','您接收到来自系统的一条消息:来自用户 9201501154 的成绩录入识别码567722 已生成！','unread','2018-08-04 18:23:53',NULL,'系统','重要'),(44,'9201701000','成绩录入','成绩录入的识别码(PIN)的信息','9201701000','您收到一条来自教务的信息：您用于本学期成绩录入的识别码（PIN）为：270788，有效期为：2018-08-03 00:00:00至2018-09-01 00:00:00, 如有问题请与教务人员联系！','unread','2018-08-04 18:34:18',NULL,'教务','紧急'),(45,'9201701000','成绩录入','成绩录入的识别码(PIN)的信息','9201701000','您收到一条来自教务的信息：您用于本学期成绩录入的识别码（PIN）为：270788，有效期为：2018-08-03 00:00:00至2018-09-01 00:00:00, 如有问题请与教务人员联系！','unread','2018-08-04 18:36:31',NULL,'教务','紧急'),(46,'9201701000','成绩录入','成绩录入的识别码(PIN)的信息','9201701000','您收到一条来自教务的信息：您用于本学期成绩录入的识别码（PIN）为：270788，有效期为：2018-08-03 00:00:00至2018-09-01 00:00:00, 如有问题请与教务人员联系！','unread','2018-08-04 18:38:11',NULL,'教务','紧急'),(47,'9201701000','成绩录入','成绩录入的识别码(PIN)的信息','9201801590','您收到一条来自教务的信息：您用于本学期成绩录入的识别码（PIN）为：508205，有效期为：2018-08-04 00:00:00至2018-09-01 00:00:00, 如有问题请与教务人员联系！','unread','2018-08-04 18:39:35',NULL,'教务','紧急'),(48,'9201701000','识别码','成绩录入识别码','9201701000','您接收到来自系统的一条消息:来自用户 9201801590 的成绩录入识别码508205 已生成！','read','2018-08-04 18:39:35',NULL,'系统','重要'),(49,'9201701000','成绩录入','成绩录入的识别码(PIN)的信息','9201701000','您收到一条来自教务的信息：您用于本学期成绩录入的识别码（PIN）为：794500，有效期为：2018-08-04 00:00:00至2018-09-01 00:00:00, 如有问题请与教务人员联系！','read','2018-08-04 18:39:46',NULL,'教务','紧急'),(50,'9201701000','识别码','成绩录入识别码','9201701000','您接收到来自系统的一条消息:来自用户 9201701000 的成绩录入识别码794500 已生成！','read','2018-08-04 18:39:46',NULL,'系统','重要'),(51,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户布彦, 隆之(8201502148)的注册申请！请尽快处理，谢谢！','unread','2018-08-07 20:11:49',NULL,'系统','重要'),(52,'9201701000','用户申请','用户创建','9201502803','您接收到来自系统的一条消息:您的账户已创建！欢迎来到先锋！','unread','2018-08-07 20:13:54',NULL,'系统','重要'),(53,'9201701000','用户申请','批准操作成功','9201701000','您接收到来自系统的一条消息:来自用户 布彦, 隆之(9201502803) 批准已通过！','unread','2018-08-07 20:13:54',NULL,'系统','重要'),(54,'9201701000','用户申请','用户申请','','您接收到来自系统的一条消息:收到来自用户冯, 梅(8201801447)的注册申请！请尽快处理，谢谢！','unread','2018-08-08 20:45:28',NULL,'系统','重要');

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
  `last_login_time` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `comment` text DEFAULT NULL COMMENT '备注',
  `profile` text DEFAULT NULL COMMENT '头像',
  `user_info` text DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `person` */

insert  into `person`(`user_id`,`username`,`first_name`,`last_name`,`password`,`status`,`info`,`type`,`role_id`,`birthday`,`email`,`tel`,`qq`,`we_chat`,`dorm`,`gender`,`create_time`,`update_time`,`last_login_time`,`address`,`comment`,`profile`,`user_info`) values ('9201402537','phl537','培洪','罗','$2a$10$QjTPbQilxGnDgIGe9UnUf.86x8lpecqpx/gLM.WhxizNcR7s8N95i','1','2014-02','f/','2/0/6/7/','2000-10-23T16:00:00.000Z','806246566@qq.com','18073309685','806246566',NULL,NULL,'undetermined','2018-08-02 22:04:55','2018-08-02 22:04:55',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/7/6/238f0912-c926-436c-a735-4fba8220a150.jpg\",\"size\":87244,\"name\":\"lph_2.jpg\",\"type\":\"jpg\"}',NULL),('9201501154','zmt154','振明','唐','$2a$10$oWXyKK1yKsrxmoty1dIJ1uR4MudzWL6dpnQFEwuoDFtaBxWVxY2C6','1','2015-01','f/','0/6/','2018-08-10T16:00:00.000Z','shizi@xianfengedu.org','13625104371','55175350',NULL,NULL,'male','2018-08-04 14:01:09','2018-08-04 14:01:09','2018-08-05 22:51:25',NULL,'','{\"path\":\"/pioneer/image/profile/9/0/ea563cc6-b0c0-42f6-b195-aaee052fad58.jpg\",\"size\":91493,\"name\":\"tzm_1.jpg\",\"type\":\"jpg\"}',NULL),('9201502154','zch154','子宸','何','$2a$10$dp.pXjaFmhlVRCWug4epFOFIFxJ1k8v3FqUAjIzGUOWGRR2oN5Dne','1','2015-02','s/','0/5/','2002-05-25T16:00:00.000Z','2194910972@qq.com','13541081269','2194910972',NULL,NULL,'male','2018-08-02 20:20:35','2018-08-03 20:05:09',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/2/11/c28aed14-97b3-4b91-a247-207893b87d4c.JPG\",\"size\":136615,\"name\":\"IMG_3669.JPG\",\"type\":\"JPG\"}',NULL),('9201502803','lzby803','隆之','布彦','$2a$10$Bjai/8x3x7IcQVqTGBInyeTOgKXEqngrqmBvqg3.7XCdZcf/cS6Hm','1','2015-02','f/','0/6/2/7/','2018-08-19T16:00:00.000Z','772380576@qq.com','18628047206','772380576',NULL,NULL,'male','2018-08-07 20:13:54','2018-08-07 20:13:54',NULL,NULL,'',NULL,NULL),('9201502825','cxy825','晨煦','虞','$2a$10$fSsZ7b28OYNBRf8wfyL6/uVg5r4mxcJG3uKm5vxECZY6p82yoiADW','1','2015-02','s/','0/5/','2002-08-15T16:00:00.000Z','815237261@qq.com','18969765952','815237261',NULL,NULL,'male','2018-08-02 19:11:05','2018-08-02 20:19:29',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/0/10/e71b1592-2c9c-4cb7-9290-4df0929c81ef.jpg\",\"size\":127399,\"name\":\"ycx_1.jpg\",\"type\":\"jpg\"}',NULL),('9201601212','xj212','旭','姜','$2a$10$Nhq/SIfBA0X/0pd/MpxkL../.qzGvePv17hKBzcxi.EmIru47GzTG','1','2016-01','s/','0/5/','2002-02-08T16:00:00.000Z','1832993047@qq.com','13962263766','1832993047',NULL,NULL,'male','2018-08-02 20:22:00','2018-08-04 00:11:35',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/0/13/6b809be4-c034-4f90-b8b0-ee1f2385c075.jpg\",\"size\":142974,\"name\":\"jx_1.jpg\",\"type\":\"jpg\"}',NULL),('9201602165','hnh165','昊南','黄','$2a$10$1CQprNLhqL8VnY4GB3n1DOwpn21w2ekW/9Ujy99nuJFn5v/2B5dRO','1','2016-02','s/','0/5/','2001-01-03T16:00:00.000Z','hhn1227007212@outlook.vom','17358532484','1227007212',NULL,NULL,'male','2018-08-02 20:23:11','2018-08-02 20:23:11',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/0/11/ac3c87a8-a65c-4073-b24a-cc07a9d70034.jpg\",\"size\":121621,\"name\":\"hhn_1.jpg\",\"type\":\"jpg\"}',NULL),('9201603838','ytl838','永泰','林','$2a$10$DCBwMEgvsbaHT/Gl/z3lZOb/BRBLbtD5ojsQgK.XFag6GjqiwDx9K','1','2016-03','s/','0/5/','2001-11-13T16:00:00.000Z','837099769@qq.com','13548084920','837099799',NULL,NULL,'male','2018-08-02 20:21:03','2018-08-02 20:21:03','2018-08-05 20:32:30',NULL,'','{\"path\":\"/pioneer/image/profile/10/10/c0f00597-1bd3-44a4-9aa8-6ce84b3ca185.jpg\",\"size\":139070,\"name\":\"lyt_2.jpg\",\"type\":\"jpg\"}',NULL),('9201701000','root','管理员','先锋','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/f/a/','0/1/2/3/4/5/6/7/5/6/7/','1996-11-12','691217991@qq.com','13668284849','691217991','1343214384','','undetermined','2017-08-08 07:00:00','2018-08-02 19:58:18','2018-08-12 15:34:40','成都市新都区斑竹园镇北欧知识城云兴路8号','请勿删除','{\"path\":\"/pioneer/image/profile/9/9/0f130949-3466-44be-8c5f-ee1dd5d59201.jpg\",\"size\":9276,\"name\":\"dcf_1.jpg\",\"type\":\"jpg\"}',''),('9201702636','tyf636','天煜','傅','$2a$10$.8aN0HRMVgPBB6aVj/AtN.2lkF4JEC1vR5jjCRDEJwIJUdNPUKlgm','1','2017-02','s/','0/5/','2004-03-24T16:00:00.000Z','1840691987@qq.com','13682458147','1840691987',NULL,NULL,'female','2018-08-02 20:21:31','2018-08-03 16:18:24',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/13/10/a690d88f-cd9e-41db-a9bf-fe21e2d2d4f2.jpg\",\"size\":117728,\"name\":\"fty_1.jpg\",\"type\":\"jpg\"}',NULL),('9201801164','wl164','苇','卢','$2a$10$iTWNcWhOgtNHe2775lAPPeM2mCnw26xEg7S6xTkehA4So0cJh8ofu','1','2018-01','f/','0/3/6/7/','1993-07-08T16:00:00.000Z','85800327@qq.com','15884468203','85800327',NULL,NULL,'male','2018-08-02 00:06:46','2018-08-02 20:18:37',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/13/2/3206ac43-58c9-45c4-876b-8fbfd6391140.jpg\",\"size\":92972,\"name\":\"lw_1.jpg\",\"type\":\"jpg\"}',NULL),('9201801590','ShileiLin590','石磊','林','$2a$10$g674S57aeqEnYbcMZs/ki.s7H2EOUQIdwUxfWPPpOdMC7QU9eEdcG','1','2018-01','f/','0/6/2/7/','2018-06-25T16:00:00.000Z','lin.shilei@outlook.com','13668284849','691217991',NULL,'2606','male','2018-08-02 00:05:14','2018-08-03 15:50:22',NULL,'Weiyi Rd,HUangtianba, qingyang district','','{\"path\":\"/pioneer/image/profile/7/2/c6a9a5a8-0403-4914-9936-0930ca11f8a1.jpg\",\"size\":109132,\"name\":\"lsl_1.jpg\",\"type\":\"jpg\"}','{\"path\":\"/pioneer/document/userInfo/4/10/4f57e665-5062-4787-ba9a-301d74b7a113.png\",\"size\":1947443,\"name\":\"第三新成都2_1.png\",\"type\":\"png\"}'),('9201803113','zry113','子睿','颜','$2a$10$ZaXm4EYeo0/DdP/FkmSc9Oy3eM2pSt7sHrUOPEnawbHqgAw9Gh1n6','1','2018-03','s/','0/5/','2001-03-31T16:00:00.000Z','857855091@qq.com','18980852467','857855091',NULL,NULL,'male','2018-08-02 20:23:34','2018-08-02 20:23:34',NULL,NULL,'','{\"path\":\"/pioneer/image/profile/15/0/f78efc0f-3e15-4d65-abec-977d7e031f0f.jpg\",\"size\":9276,\"name\":\"mzj_1.jpg\",\"type\":\"jpg\"}',NULL);

/*Table structure for table `pin` */

DROP TABLE IF EXISTS `pin`;

CREATE TABLE `pin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pin` int(11) NOT NULL,
  `info` varchar(20) NOT NULL,
  `owner_id` varchar(20) NOT NULL,
  `role` int(11) NOT NULL COMMENT '1：选课，2：成绩',
  `start_time` varchar(20) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `remark` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `pin` */

insert  into `pin`(`id`,`pin`,`info`,`owner_id`,`role`,`start_time`,`end_time`,`create_time`,`remark`) values (3,567722,'2018-01','9201501154',2,'2018-08-03 00:00:00','2018-09-02 00:00:00','2018-08-04 18:23:46','TEST2'),(4,508205,'2018-01','9201801590',2,'2018-08-04 00:00:00','2018-09-01 00:00:00','2018-08-04 18:39:35',''),(5,794500,'2018-01','9201701000',2,'2018-08-04 00:00:00','2018-09-01 00:00:00','2018-08-04 18:39:46','');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) DEFAULT NULL COMMENT '序号',
  `role_name` varchar(255) DEFAULT NULL COMMENT '管理名称',
  `role_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`role_id`,`role_name`,`role_code`) values (1,0,'用户','user'),(2,1,'管理员','admin'),(3,2,'教务','teach'),(4,3,'后勤','logistic'),(5,4,'系统','system'),(6,5,'学生','student'),(7,6,'教师','faculty'),(8,7,'导师','advisor');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` varchar(20) NOT NULL,
  `max_credits` int(11) NOT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `contract_info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`student_id`,`max_credits`,`update_time`,`contract_info`) values ('9201502154',12,'2018-08-02 20:20:35',NULL),('9201502825',12,'2018-08-02 19:11:06',NULL),('9201601212',12,'2018-08-02 20:22:00',NULL),('9201602165',12,'2018-08-02 20:23:11',NULL),('9201603838',12,'2018-08-02 20:21:03',NULL),('9201702636',12,'2018-08-03 16:18:24',NULL),('9201803113',12,'2018-08-02 20:23:34',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tempcourse` */

insert  into `tempcourse`(`id`,`crn`,`status`,`course_json`,`create_time`,`faculty_id`,`operator_id`,`update_time`) values (1,'820180132','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"二楼\",\"capacity\":\"20\",\"facultyId\":\"9201701000\",\"credits\":\"1\",\"name\":\"美术\",\"startTime\":\"11:00:00\",\"comment\":\"\",\"endTime\":\"11:00:00\",\"courseInfo\":\"{\\\"path\\\":\\\"/pioneer/document/courseInfo/0/9/b1957212-614d-4b29-b8f9-53b4a98474e9.png\\\",\\\"size\\\":4767916,\\\"name\\\":\\\"第三新成都.png\\\",\\\"type\\\":\\\"png\\\"}\",\"day\":\"m/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-02 22:36:13','9201701000','9201701000','2018-08-03 20:53:05'),(2,'820180190','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"小教室\",\"capacity\":\"5\",\"facultyId\":\"9201801590\",\"credits\":\"1\",\"name\":\"英语基础课\",\"startTime\":\"09:00:00\",\"comment\":\"\",\"endTime\":\"11:00:00\",\"courseInfo\":\"\",\"day\":\"m/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-02 22:58:41','9201701000','9201701000','2018-08-03 01:55:41'),(3,'820180157','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"兴趣小组\",\"classroom\":\"小教室\",\"capacity\":\"5\",\"facultyId\":\"9201801590\",\"credits\":\"2\",\"name\":\"计算机编程\",\"startTime\":\"19:30:00\",\"comment\":\"\",\"endTime\":\"21:00:00\",\"courseInfo\":\"\",\"day\":\"t/f/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-02 23:02:25','9201701000','9201701000','2018-08-03 16:17:59'),(4,'820180143','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"大教室\",\"capacity\":\"20\",\"facultyId\":\"9201701000\",\"credits\":\"2\",\"name\":\"项目式英语\",\"startTime\":\"14:00:00\",\"comment\":\"\",\"endTime\":\"16:00:00\",\"courseInfo\":\"{\\\"path\\\":\\\"/pioneer/document/courseInfo/0/1/5d75f650-80a4-4253-87de-aed52bb8a37d.png\\\",\\\"size\\\":4767916,\\\"name\\\":\\\"第三新成都 (1).png\\\",\\\"type\\\":\\\"png\\\"}\",\"day\":\"tr/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-03 21:01:05','9201701000','9201701000','2018-08-03 22:13:05'),(5,'820180153','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"学习中心\",\"capacity\":\"25\",\"facultyId\":\"9201701000\",\"credits\":\"2\",\"name\":\"Healthy Cooking\",\"startTime\":\"14:00:00\",\"comment\":\"\",\"endTime\":\"16:00:00\",\"courseInfo\":\"{\\\"path\\\":\\\"/pioneer/document/courseInfo/0/1/ccf138df-1ba0-427b-a4da-f30ba21e082c.png\\\",\\\"size\\\":4767916,\\\"name\\\":\\\"第三新成都 (1).png\\\",\\\"type\\\":\\\"png\\\"}\",\"day\":\"w/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-03 22:17:19','9201701000','9201701000','2018-08-03 22:19:11'),(6,'820180180','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"\",\"capacity\":\"10\",\"facultyId\":\"9201501154\",\"credits\":\"2\",\"name\":\"理化实验\",\"startTime\":\"14:00:00\",\"comment\":\"\",\"endTime\":\"16:00:00\",\"courseInfo\":\"\",\"day\":\"w/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-05 21:52:25','9201501154','9201701000','2018-08-05 21:55:03'),(7,'820180155','0','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"大教室\",\"capacity\":\"20\",\"facultyId\":\"9201502803\",\"credits\":\"2\",\"name\":\"恋爱与情感\",\"startTime\":\"09:00:00\",\"comment\":\"\",\"endTime\":\"11:00:00\",\"courseInfo\":\"\",\"day\":\"f/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-08 16:39:00','9201701000',NULL,'2018-08-08 16:39:00'),(8,'820180166','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"\",\"capacity\":\"10\",\"facultyId\":\"9201502803\",\"credits\":\"2\",\"name\":\"导师课\",\"startTime\":\"14:00:00\",\"comment\":\"\",\"endTime\":\"16:00:00\",\"courseInfo\":\"\",\"day\":\"t/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-08 16:45:07','9201701000','9201701000','2018-08-08 16:47:02'),(9,'820180132','1','{\"level\":\"100\",\"endDate\":\"2018-07-15\",\"precrn\":\"\",\"section\":\"课程\",\"classroom\":\"\",\"capacity\":\"20\",\"facultyId\":\"9201801590\",\"credits\":\"2\",\"name\":\"导师课\",\"startTime\":\"14:00:00\",\"comment\":\"\",\"endTime\":\"16:00:00\",\"courseInfo\":\"\",\"day\":\"t/\",\"crn\":\"\",\"startDate\":\"2018-03-01\",\"info\":\"2018-01\"}','2018-08-08 16:46:29','9201701000','9201701000','2018-08-08 16:46:48');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tempuser` */

insert  into `tempuser`(`id`,`user_id`,`status`,`user_json`,`create_time`,`update_time`,`operator_id`) values (1,'8201801811','1','{\"qq\":\"691217991\",\"birthday\":\"2018-06-25T16:00:00.000Z\",\"lastName\":\"Lin\",\"firstName\":\"Shilei\",\"password\":\"$2a$10$g674S57aeqEnYbcMZs/ki.s7H2EOUQIdwUxfWPPpOdMC7QU9eEdcG\",\"gender\":\"male\",\"profile\":\"{\\\"path\\\":\\\"/pioneer/image/profile/7/2/4802f5bc-88f9-4b9b-86a1-9df3cceb83e3.jpg\\\",\\\"size\\\":2013298,\\\"name\\\":\\\"lsl_1.jpg\\\",\\\"type\\\":\\\"jpg\\\"}\",\"tel\":\"13668284849\",\"comment\":\"\",\"email\":\"lin.shilei@outlook.com\",\"info\":\"2018-01\",\"userId\":\"8201801811\",\"dorm\":\"2606\",\"address\":\"Weiyi Rd,HUangtianba, qingyang district\",\"status\":\"1\",\"type\":\"f/a/\",\"roleId\":\"0/1/6/7/\"}','2018-07-30 00:20:22','2018-08-02 00:05:14','9201701000'),(2,'8201801512','1','{\"qq\":\"85800327\",\"birthday\":\"1993-07-08T16:00:00.000Z\",\"lastName\":\"卢\",\"firstName\":\"苇\",\"password\":\"$2a$10$iTWNcWhOgtNHe2775lAPPeM2mCnw26xEg7S6xTkehA4So0cJh8ofu\",\"gender\":\"male\",\"tel\":\"15884468203\",\"comment\":\"\",\"email\":\"85800327@qq.com\",\"info\":\"2018-01\",\"userId\":\"8201801512\",\"profile\":{\"path\":\"/pioneer/image/profile/13/2/5c16dcc0-4cf0-43f8-b61e-ef0803588143.jpg\",\"size\":1436670,\"name\":\"lw_1.jpg\",\"type\":\"jpg\"},\"status\":\"1\",\"type\":\"f/\",\"roleId\":\"0/3/6/7/\"}','2018-07-30 00:22:05','2018-08-02 00:06:47','9201701000'),(4,'8201502790','1','{\"qq\":\"815237261\",\"birthday\":\"2002-08-15T16:00:00.000Z\",\"lastName\":\"虞\",\"firstName\":\"晨煦\",\"password\":\"$2a$10$fSsZ7b28OYNBRf8wfyL6/uVg5r4mxcJG3uKm5vxECZY6p82yoiADW\",\"gender\":\"male\",\"profile\":\"{\\\"path\\\":\\\"/pioneer/image/profile/0/10/ccdf6715-acbd-4821-8d41-f38978b1f2ba.jpg\\\",\\\"size\\\":2019603,\\\"name\\\":\\\"ycx_1.jpg\\\",\\\"type\\\":\\\"jpg\\\"}\",\"tel\":\"18969765952\",\"comment\":\"\",\"email\":\"815237261@qq.com\",\"info\":\"2015-02\",\"userId\":\"8201502790\",\"status\":\"1\",\"type\":\"s/\",\"roleId\":\"0/5/\"}','2018-08-02 15:08:46','2018-08-02 19:11:06','9201701000'),(6,'8201502712','1','{\"qq\":\"2194910972\",\"birthday\":\"2002-05-25T16:00:00.000Z\",\"lastName\":\"何\",\"firstName\":\"子宸\",\"password\":\"$2a$10$dp.pXjaFmhlVRCWug4epFOFIFxJ1k8v3FqUAjIzGUOWGRR2oN5Dne\",\"gender\":\"male\",\"profile\":{\"path\":\"/pioneer/image/profile/3/1/666de485-f191-45d8-8665-19b9700cf19f.jpg\",\"size\":130090,\"name\":\"hzc_1.jpg\",\"type\":\"jpg\"},\"tel\":\"13541081269\",\"comment\":\"\",\"email\":\"2194910972@qq.com\",\"info\":\"2015-02\",\"userId\":\"8201502712\",\"status\":\"1\",\"type\":\"s/\",\"roleId\":\"0/5/\"}','2018-08-02 15:18:54','2018-08-02 20:20:35','9201701000'),(7,'8201603902','1','{\"qq\":\"837099799\",\"birthday\":\"2001-11-13T16:00:00.000Z\",\"lastName\":\"林\",\"firstName\":\"永泰\",\"password\":\"$2a$10$DCBwMEgvsbaHT/Gl/z3lZOb/BRBLbtD5ojsQgK.XFag6GjqiwDx9K\",\"gender\":\"male\",\"tel\":\"13548084920\",\"comment\":\"\",\"email\":\"837099769@qq.com\",\"info\":\"2016-03\",\"userId\":\"8201603902\",\"profile\":{\"path\":\"/pioneer/image/profile/10/10/c0f00597-1bd3-44a4-9aa8-6ce84b3ca185.jpg\",\"size\":139070,\"name\":\"lyt_2.jpg\",\"type\":\"jpg\"},\"status\":\"1\",\"type\":\"s/\",\"roleId\":\"0/5/\"}','2018-08-02 15:19:24','2018-08-02 20:21:03','9201701000'),(8,'8201702687','1','{\"qq\":\"1840691987\",\"birthday\":\"2004-03-24T16:00:00.000Z\",\"lastName\":\"傅\",\"firstName\":\"天煜\",\"password\":\"$2a$10$.8aN0HRMVgPBB6aVj/AtN.2lkF4JEC1vR5jjCRDEJwIJUdNPUKlgm\",\"gender\":\"female\",\"tel\":\"13682458147\",\"comment\":\"\",\"email\":\"1840691987@qq.com\",\"info\":\"2017-02\",\"userId\":\"8201702687\",\"profile\":{\"path\":\"/pioneer/image/profile/13/10/a690d88f-cd9e-41db-a9bf-fe21e2d2d4f2.jpg\",\"size\":117728,\"name\":\"fty_1.jpg\",\"type\":\"jpg\"},\"type\":\"\",\"roleId\":\"0/\"}','2018-08-02 15:27:50','2018-08-02 20:21:31','9201701000'),(9,'8201601902','1','{\"qq\":\"1832993047\",\"birthday\":\"2002-02-08T16:00:00.000Z\",\"lastName\":\"姜\",\"firstName\":\"旭\",\"password\":\"$2a$10$Nhq/SIfBA0X/0pd/MpxkL../.qzGvePv17hKBzcxi.EmIru47GzTG\",\"gender\":\"male\",\"tel\":\"13962263766\",\"comment\":\"\",\"email\":\"1832993047@qq.com\",\"info\":\"2016-01\",\"userId\":\"8201601902\",\"profile\":{\"path\":\"/pioneer/image/profile/0/11/06a217f5-6cbc-46b6-8e0a-9ddeb8aaf165.jpg\",\"size\":121621,\"name\":\"hhn_1.jpg\",\"type\":\"jpg\"},\"status\":\"1\",\"type\":\"s/\",\"roleId\":\"0/5/\"}','2018-08-02 17:04:01','2018-08-02 20:22:00','9201701000'),(10,'8201602144','1','{\"qq\":\"1227007212\",\"birthday\":\"2001-01-03T16:00:00.000Z\",\"lastName\":\"黄\",\"firstName\":\"昊南\",\"password\":\"$2a$10$1CQprNLhqL8VnY4GB3n1DOwpn21w2ekW/9Ujy99nuJFn5v/2B5dRO\",\"gender\":\"male\",\"tel\":\"17358532484\",\"comment\":\"\",\"email\":\"hhn1227007212@outlook.vom\",\"info\":\"2016-02\",\"userId\":\"8201602144\",\"profile\":{\"path\":\"/pioneer/image/profile/0/11/ac3c87a8-a65c-4073-b24a-cc07a9d70034.jpg\",\"size\":121621,\"name\":\"hhn_1.jpg\",\"type\":\"jpg\"},\"status\":\"1\",\"type\":\"s/\",\"roleId\":\"0/5/\"}','2018-08-02 17:12:11','2018-08-02 20:23:11','9201701000'),(11,'8201803437','1','{\"qq\":\"857855091\",\"birthday\":\"2001-03-31T16:00:00.000Z\",\"lastName\":\"颜\",\"firstName\":\"子睿\",\"password\":\"$2a$10$ZaXm4EYeo0/DdP/FkmSc9Oy3eM2pSt7sHrUOPEnawbHqgAw9Gh1n6\",\"gender\":\"male\",\"tel\":\"18980852467\",\"comment\":\"\",\"email\":\"857855091@qq.com\",\"info\":\"2018-03\",\"userId\":\"8201803437\",\"profile\":{\"path\":\"/pioneer/image/profile/15/0/f78efc0f-3e15-4d65-abec-977d7e031f0f.jpg\",\"size\":9276,\"name\":\"mzj_1.jpg\",\"type\":\"jpg\"},\"status\":\"1\",\"type\":\"s/\",\"roleId\":\"0/5/\"}','2018-08-02 18:16:31','2018-08-02 20:23:34','9201701000'),(12,'8201402742','1','{\"qq\":\"806246566\",\"birthday\":\"2000-10-23T16:00:00.000Z\",\"lastName\":\"罗\",\"firstName\":\"培洪\",\"password\":\"$2a$10$QjTPbQilxGnDgIGe9UnUf.86x8lpecqpx/gLM.WhxizNcR7s8N95i\",\"gender\":\"undetermined\",\"tel\":\"18073309685\",\"comment\":\"\",\"email\":\"806246566@qq.com\",\"info\":\"2014-02\",\"userId\":\"8201402742\",\"profile\":{\"path\":\"/pioneer/image/profile/7/6/238f0912-c926-436c-a735-4fba8220a150.jpg\",\"size\":87244,\"name\":\"lph_2.jpg\",\"type\":\"jpg\"},\"status\":\"1\",\"type\":\"f/\",\"roleId\":\"2/0/6/7/\"}','2018-08-02 21:58:37','2018-08-02 22:04:55','9201701000'),(13,'8201501290','1','{\"qq\":\"55175350\",\"birthday\":\"2018-08-10T16:00:00.000Z\",\"lastName\":\"唐\",\"firstName\":\"振明\",\"password\":\"$2a$10$oWXyKK1yKsrxmoty1dIJ1uR4MudzWL6dpnQFEwuoDFtaBxWVxY2C6\",\"gender\":\"male\",\"tel\":\"13625104371\",\"comment\":\"\",\"email\":\"shizi@xianfengedu.org\",\"info\":\"2015-01\",\"userId\":\"8201501290\",\"profile\":{\"path\":\"/pioneer/image/profile/9/0/ea563cc6-b0c0-42f6-b195-aaee052fad58.jpg\",\"size\":91493,\"name\":\"tzm_1.jpg\",\"type\":\"jpg\"},\"status\":\"1\",\"type\":\"f/\",\"roleId\":\"0/6/\"}','2018-08-04 13:09:40','2018-08-04 14:01:09','9201701000'),(14,'8201502148','1','{\"qq\":\"772380576\",\"birthday\":\"2018-08-19T16:00:00.000Z\",\"lastName\":\"布彦\",\"firstName\":\"隆之\",\"password\":\"$2a$10$Bjai/8x3x7IcQVqTGBInyeTOgKXEqngrqmBvqg3.7XCdZcf/cS6Hm\",\"gender\":\"male\",\"tel\":\"18628047206\",\"comment\":\"\",\"email\":\"772380576@qq.com\",\"info\":\"2015-02\",\"userId\":\"8201502148\",\"status\":\"1\",\"type\":\"f/\",\"roleId\":\"0/6/2/7/\"}','2018-08-07 20:11:49','2018-08-07 20:13:54','9201701000'),(15,'8201801447','0','{\"qq\":\"352300400\",\"birthday\":\"\",\"lastName\":\"冯\",\"firstName\":\"梅\",\"password\":\"$2a$10$Cufts4WIhqc/FNJCc2xLgOUwMX25iZJU3F67xPau2OPxLeATv7GEW\",\"gender\":\"female\",\"tel\":\"13350079282\",\"comment\":\"\",\"email\":\"352300400@qq.com\",\"info\":\"2018-01\"}','2018-08-08 20:45:28','2018-08-08 20:45:28',NULL);

/*Table structure for table `transcript` */

DROP TABLE IF EXISTS `transcript`;

CREATE TABLE `transcript` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `student_id` varchar(20) NOT NULL COMMENT '学生ID',
  `crn` varchar(20) NOT NULL COMMENT '课程ID',
  `grade` varchar(20) NOT NULL COMMENT '成绩',
  `credit` double DEFAULT NULL,
  `complete` varchar(20) NOT NULL COMMENT '完成状态',
  `assign_time` varchar(20) NOT NULL COMMENT '时间',
  `operator_id` varchar(50) NOT NULL COMMENT '修改人',
  `remark` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnvmw209jvvfrlikqijuqa3xxi` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `transcript` */

insert  into `transcript`(`id`,`student_id`,`crn`,`grade`,`credit`,`complete`,`assign_time`,`operator_id`,`remark`) values (1,'9201602165','120180157','AB',1,'1','2018-08-03 16:45:59','9201701000','该同学表现良好！'),(2,'9201502825','120180157','AB',1,'1','2018-08-03 16:46:03','9201701000','该同学表现良好！'),(3,'9201603838','120180157','AB',1,'1','2018-08-03 16:46:07','9201701000','该同学表现良好！'),(5,'9201502154','120180157','AB',0.5,'1','2018-08-03 16:46:11','9201701000','该同学表现良好！'),(7,'9201702636','120180151','AB',2,'1','2018-08-03 16:45:14','9201701000','该同学表现良好！'),(8,'9201502825','120180125','*',0,'0','2018-08-03 22:19:54','9201701000',NULL),(9,'9201803113','120180125','*',0,'0','2018-08-03 22:20:04','9201701000',NULL),(10,'9201602165','120180125','*',0,'0','2018-08-03 22:20:45','9201701000',NULL),(11,'9201603838','120180125','*',0,'0','2018-08-03 22:20:56','9201701000',NULL),(12,'9201601212','120180125','*',0,'0','2018-08-03 22:21:11','9201701000',NULL),(13,'9201702636','120180150','*',0,'0','2018-08-05 22:56:43','9201701000',NULL),(14,'9201502154','120180189','*',0,'0','2018-08-08 16:50:31','9201701000',NULL),(15,'9201502825','120180189','*',0,'0','2018-08-08 16:50:46','9201701000',NULL),(16,'9201702636','120180189','*',0,'0','2018-08-08 16:51:01','9201701000',NULL),(17,'9201803113','120180189','*',0,'0','2018-08-08 16:51:13','9201701000',NULL),(18,'9201602165','120180189','*',0,'0','2018-08-08 16:51:23','9201701000',NULL);

/* Function  structure for function  `get_complete_credits` */

/*!50003 DROP FUNCTION IF EXISTS `get_complete_credits` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_complete_credits`(sid varchar(100)) RETURNS int(11)
BEGIN

	DECLARE credits DOUBLE DEFAULT 0.0;

	DECLARE counts INT DEFAULT 0;

	

	SELECT COUNT(*) INTO counts FROM transcriptview t WHERE t.student_id = sid;

	

	if counts > 0 then

		SELECT SUM(t.credit) INTO credits FROM transcriptview t WHERE t.student_id = sid;

	end if;	

	

	RETURN credits;

END */$$
DELIMITER ;

/* Function  structure for function  `get_contract_status` */

/*!50003 DROP FUNCTION IF EXISTS `get_contract_status` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `get_contract_status`(contract_id VARCHAR(100)) RETURNS int(11)
BEGIN
	DECLARE enddate VARCHAR(100);
	DECLARE startdate VARCHAR(100);
	DECLARE status INT(11);
	
	SELECT c.expire_date, c.init_date INTO enddate, startdate FROM contract c WHERE c.contract_id = contract_id;
	
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

	DECLARE credits DOUBLE DEFAULT 0;

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

	DECLARE credits DOUBLE DEFAULT 0.0;

	DECLARE counts INT DEFAULT 0;

	

	SELECT COUNT(*) INTO counts FROM transcriptview t WHERE t.student_id = sid AND t.complete = '-1';

	

	IF counts > 0 THEN

		SELECT SUM(t.credits) INTO credits FROM transcriptview t WHERE t.student_id = sid AND t.complete = '-1';

	END IF;	

		

    return credits;		

    END */$$
DELIMITER ;

/* Function  structure for function  `get_num_student` */

/*!50003 DROP FUNCTION IF EXISTS `get_num_student` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `get_num_student`(fid varchar(100)) RETURNS int(11)
begin 
    DECLARE num INT DEFAULT 0;
    SELECT count(*) into num from advise a where a.faculty_id=fid and a.status=1;
    return num;
  end */$$
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
 `info` varchar(255) NOT NULL ,
 `oname` varchar(100) NULL 
)*/;

/*Table structure for table `advisorview` */

DROP TABLE IF EXISTS `advisorview`;

/*!50001 DROP VIEW IF EXISTS `advisorview` */;
/*!50001 DROP TABLE IF EXISTS `advisorview` */;

/*!50001 CREATE TABLE  `advisorview`(
 `user_id` varchar(20) NOT NULL ,
 `name` varchar(100) NULL ,
 `num_student` int(11) NULL ,
 `update_time` varchar(100) NOT NULL ,
 `profile` text NULL 
)*/;

/*Table structure for table `contractview` */

DROP TABLE IF EXISTS `contractview`;

/*!50001 DROP VIEW IF EXISTS `contractview` */;
/*!50001 DROP TABLE IF EXISTS `contractview` */;

/*!50001 CREATE TABLE  `contractview`(
 `id` int(11) NOT NULL  default '0' ,
 `contract_id` varchar(20) NOT NULL ,
 `owner_id` varchar(20) NULL ,
 `oname` varchar(100) NULL ,
 `info` varchar(100) NULL ,
 `type` varchar(100) NULL ,
 `init_date` varchar(100) NULL ,
 `expire_date` varchar(100) NULL ,
 `operator_id` varchar(100) NULL ,
 `create_time` varchar(100) NOT NULL ,
 `update_time` varchar(100) NOT NULL ,
 `comment` text NULL ,
 `contract_info` text NULL ,
 `status` int(11) NULL ,
 `opname` varchar(100) NULL 
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
 `status` int(11) NULL ,
 `profile` text NULL 
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
 `receiver` varchar(100) NULL 
)*/;

/*Table structure for table `pinview` */

DROP TABLE IF EXISTS `pinview`;

/*!50001 DROP VIEW IF EXISTS `pinview` */;
/*!50001 DROP TABLE IF EXISTS `pinview` */;

/*!50001 CREATE TABLE  `pinview`(
 `user_id` varchar(20) NOT NULL ,
 `id` int(11) NOT NULL  default '0' ,
 `pin` int(11) NOT NULL ,
 `info` varchar(20) NOT NULL ,
 `owner_id` varchar(20) NOT NULL ,
 `role` int(11) NOT NULL ,
 `start_time` varchar(20) NOT NULL ,
 `end_time` varchar(20) NOT NULL ,
 `create_time` varchar(20) NULL ,
 `remark` text NULL ,
 `profile` text NULL ,
 `oname` varchar(100) NULL 
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
 `credit` double NULL ,
 `faculty_id` varchar(20) NOT NULL ,
 `info` varchar(20) NOT NULL ,
 `sname` varchar(100) NULL ,
 `fname` varchar(100) NULL ,
 `date` varchar(100) NULL ,
 `time` varchar(100) NULL ,
 `day` varchar(20) NULL ,
 `assign_time` varchar(20) NOT NULL ,
 `operator_id` varchar(50) NOT NULL ,
 `oname` varchar(100) NULL ,
 `remark` text NULL ,
 `sprofile` text NULL ,
 `fprofile` text NULL 
)*/;

/*View structure for view adviseview */

/*!50001 DROP TABLE IF EXISTS `adviseview` */;
/*!50001 DROP VIEW IF EXISTS `adviseview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `adviseview` AS select `a`.`id` AS `id`,`a`.`student_id` AS `student_id`,`get_name`(`a`.`student_id`) AS `sname`,`a`.`faculty_id` AS `faculty_id`,`get_name`(`a`.`faculty_id`) AS `fname`,`a`.`status` AS `status`,`a`.`update_time` AS `update_time`,`a`.`operator_id` AS `operator_id`,`a`.`info` AS `info`,`get_name`(`a`.`operator_id`) AS `oname` from `advise` `a` */;

/*View structure for view advisorview */

/*!50001 DROP TABLE IF EXISTS `advisorview` */;
/*!50001 DROP VIEW IF EXISTS `advisorview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `advisorview` AS select distinct `p`.`user_id` AS `user_id`,`get_name`(`p`.`user_id`) AS `name`,`get_num_student`(`p`.`user_id`) AS `num_student`,`p`.`update_time` AS `update_time`,`p`.`profile` AS `profile` from `person` `p` where `p`.`role_id` like '%7%' */;

/*View structure for view contractview */

/*!50001 DROP TABLE IF EXISTS `contractview` */;
/*!50001 DROP VIEW IF EXISTS `contractview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `contractview` AS select `c`.`id` AS `id`,`c`.`contract_id` AS `contract_id`,`c`.`owner_id` AS `owner_id`,`c`.`oname` AS `oname`,`c`.`info` AS `info`,`c`.`type` AS `type`,`c`.`init_date` AS `init_date`,`c`.`expire_date` AS `expire_date`,`c`.`operator_id` AS `operator_id`,`c`.`create_time` AS `create_time`,`c`.`update_time` AS `update_time`,`c`.`comment` AS `comment`,`c`.`contract_info` AS `contract_info`,`get_contract_status`(`c`.`contract_id`) AS `status`,`get_name`(`p1`.`user_id`) AS `opname` from (`contract` `c` join `person` `p1`) where `p1`.`user_id` = `c`.`operator_id` */;

/*View structure for view courseview */

/*!50001 DROP TABLE IF EXISTS `courseview` */;
/*!50001 DROP VIEW IF EXISTS `courseview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `courseview` AS select `c`.`id` AS `id`,`c`.`crn` AS `crn`,`c`.`name` AS `name`,`c`.`credits` AS `credits`,`c`.`precrn` AS `precrn`,`c`.`level` AS `level`,`c`.`section` AS `section`,`c`.`classroom` AS `classroom`,`c`.`start_date` AS `start_date`,`c`.`end_date` AS `end_date`,`c`.`day` AS `day`,`c`.`start_time` AS `start_time`,`c`.`end_time` AS `end_time`,`c`.`capacity` AS `capacity`,`c`.`info` AS `info`,`c`.`create_time` AS `create_time`,`c`.`update_time` AS `update_time`,`c`.`comment` AS `comment`,`c`.`course_info` AS `course_info`,`c`.`faculty_id` AS `faculty_id`,`get_remain_capa`(`c`.`crn`) AS `remain`,`get_name`(`c`.`faculty_id`) AS `faculty`,`get_course_date`(`c`.`crn`) AS `date`,`get_course_time`(`c`.`crn`) AS `time`,`get_course_status`(`c`.`crn`) AS `status`,`person`.`profile` AS `profile` from (`course` `c` join `person` on(`c`.`faculty_id` = `person`.`user_id`)) */;

/*View structure for view messageview */

/*!50001 DROP TABLE IF EXISTS `messageview` */;
/*!50001 DROP VIEW IF EXISTS `messageview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `messageview` AS select `m`.`id` AS `id`,`m`.`sender_id` AS `sender_id`,`m`.`receiver_id` AS `receiver_id`,`m`.`subject` AS `subject`,`m`.`title` AS `title`,`m`.`body` AS `body`,`m`.`status` AS `status`,`m`.`date` AS `date`,`s`.`profile` AS `pic`,`s`.`tel` AS `tel`,`s`.`email` AS `email`,`m`.`attachment` AS `attachment`,`m`.`labels` AS `labels`,`m`.`tag` AS `tag`,`get_name`(`m`.`sender_id`) AS `sender`,`get_name`(`m`.`receiver_id`) AS `receiver` from (`message` `m` join `person` `s`) where `s`.`user_id` = `m`.`sender_id` */;

/*View structure for view pinview */

/*!50001 DROP TABLE IF EXISTS `pinview` */;
/*!50001 DROP VIEW IF EXISTS `pinview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `pinview` AS select `person`.`user_id` AS `user_id`,`p`.`id` AS `id`,`p`.`pin` AS `pin`,`p`.`info` AS `info`,`p`.`owner_id` AS `owner_id`,`p`.`role` AS `role`,`p`.`start_time` AS `start_time`,`p`.`end_time` AS `end_time`,`p`.`create_time` AS `create_time`,`p`.`remark` AS `remark`,`person`.`profile` AS `profile`,`get_name`(`p`.`owner_id`) AS `oname` from (`pin` `p` join `person` on(convert(`p`.`owner_id` using utf8) = `person`.`user_id`)) */;

/*View structure for view studentview */

/*!50001 DROP TABLE IF EXISTS `studentview` */;
/*!50001 DROP VIEW IF EXISTS `studentview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `studentview` AS select `s`.`student_id` AS `student_id`,`s`.`max_credits` AS `max_credits`,`p`.`status` AS `status`,`get_name`(`s`.`student_id`) AS `sname`,`get_complete_credits`(`s`.`student_id`) AS `complete`,`get_in_progress_credits`(`s`.`student_id`) AS `progress`,`get_not_complete_credits`(`s`.`student_id`) AS `incomplete` from (`student` `s` join `person` `p`) where `p`.`user_id` = `s`.`student_id` */;

/*View structure for view transcriptview */

/*!50001 DROP TABLE IF EXISTS `transcriptview` */;
/*!50001 DROP VIEW IF EXISTS `transcriptview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `transcriptview` AS select `t`.`id` AS `id`,`t`.`student_id` AS `student_id`,`t`.`crn` AS `crn`,`c`.`name` AS `cname`,`c`.`credits` AS `credits`,`t`.`grade` AS `grade`,`t`.`complete` AS `complete`,`t`.`credit` AS `credit`,`c`.`faculty_id` AS `faculty_id`,`c`.`info` AS `info`,`get_name`(`p1`.`user_id`) AS `sname`,`get_name`(`p2`.`user_id`) AS `fname`,`get_course_date`(`t`.`crn`) AS `date`,`get_course_time`(`t`.`crn`) AS `time`,`c`.`day` AS `day`,`t`.`assign_time` AS `assign_time`,`t`.`operator_id` AS `operator_id`,`get_name`(`t`.`operator_id`) AS `oname`,`t`.`remark` AS `remark`,`p1`.`profile` AS `sprofile`,`p2`.`profile` AS `fprofile` from (((`transcript` `t` join `course` `c`) join `person` `p1`) join `person` `p2`) where `t`.`crn` = `c`.`crn` and `p1`.`user_id` = `t`.`student_id` and `p2`.`user_id` = `c`.`faculty_id` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
