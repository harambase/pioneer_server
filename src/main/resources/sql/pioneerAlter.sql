create table `pioneer_2.0`.`PIN`(
   `id` int(11) NOT NULL AUTO_INCREMENT , 
   `pin` int(11) NOT NULL , 
   `facultyid` varchar(11) , 
   `studentid` varchar(20) , 
   `role` int(11) NOT NULL COMMENT '1：选课，2：成绩', 
   `startTime` varchar(20) NOT NULL , 
   `endTime` varchar(20) NOT NULL , 
   `createTime` varchar(20) , 
   PRIMARY KEY (`id`)
 )