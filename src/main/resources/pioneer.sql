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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pioneer_2.0` /*!40100 DEFAULT CHARACTER SET utf8 */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `advise` WRITE;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `assignment` */

LOCK TABLES `assignment` WRITE;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `course` WRITE;

UNLOCK TABLES;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `message` WRITE;

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

LOCK TABLES `person` WRITE;

#INIT ROOT ACCOUNT:
INSERT INTO `person`
(`user_id`, `username`, `first_name`, `last_name`, `password`, `status`, `info`, `type`, `role_id`, `birthday`, `email`, `tel`, `qq`, `we_chat`, `dorm`, `gender`, `create_time`, `update_time`, `base_info`, `comment`, `profile`, `user_info`, `address`)
VALUES
  ('9201701000', 'root', 'admin', 'system', 'e10adc3949ba59abbe56e057f20f883e', '1', '2017-01', 'a/', '0/1/',
   '1980-01-01', 'admin@pioneer.edu', '13220184951', '1343214384', '1343214384', '', 'male',
  '1970-01-01 07:00:00', '1970-01-01 14:11:26', NULL, '请勿删除','', '', NULL);


UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pin` WRITE;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tempcourse` */

LOCK TABLES `tempcourse` WRITE;

UNLOCK TABLES;

/*Table structure for table `tempuser` */

DROP TABLE IF EXISTS `tempuser`;

CREATE TABLE `tempuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT '0（处理中）,1（通过）,-1(拒绝)',
  `user_json` text NOT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `operator_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tempuser` */

LOCK TABLES `tempuser` WRITE;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `transcript` WRITE;

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
 `id` int(11) NOT NULL default '0',
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
