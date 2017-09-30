alter table `pioneer_2.0`.`TempUser` 
   add column `status` varchar(20) NULL after `userid`, 
   add column `createtime` varchar(50) NULL after `user_json`, 
   add column `updatetime` varchar(50) NULL after `createtime`,
   add column `operator` varchar(20) NULL after `updatetime`;

alter table `pioneer_2.0`.`TempCourse`
   add column `status` varchar(20) NULL after `crn`,
   add column `createtime` varchar(50) NULL after `course_json`,
   add column `updatetime` varchar(50) NULL after `createtime`,
   change `json` `course_json` text NOT NULL,
   add column `operator` varchar(20) NULL after `updatetime`;