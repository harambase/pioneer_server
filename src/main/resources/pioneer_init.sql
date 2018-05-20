USE `pioneer_2.0`;

TRUNCATE advise;
TRUNCATE assignment;
TRUNCATE course;
TRUNCATE message;
TRUNCATE person;
TRUNCATE pin;
TRUNCATE ROLE;
TRUNCATE student;
TRUNCATE tempadvise;
TRUNCATE tempcourse;
TRUNCATE tempuser;
TRUNCATE transcript;

INSERT  INTO `person`(`user_id`,`username`,`first_name`,`last_name`,`password`,`status`,`info`,`type`,`role_id`,`birthday`,`email`,`tel`,`qq`,`we_chat`,`dorm`,`gender`,`create_time`,`update_time`,`address`,`comment`,`profile`,`user_info`)
VALUES
('9201701000','root','admin','system','$2a$10$UcsU6n.N0fyLrDgonZm5O.E8L5HSaXSaU.Ei5yftD21h117FOeedK','1','2017-01','s/f/a/','0/1/2/3/4/5/6/7/','1980-01-01','admin@pioneer.edu','13220184951','1343214384','1343214384','','male','2017-08-08 07:00:00','2018-03-28 22:16:23',NULL,'请勿删除','','');

INSERT  INTO `role`(`id`,`role_id`,`role_name`,`role_code`)
VALUES
  (1, 0, '用户', 'user'),
  (2, 1, '管理员', 'admin'),
  (3, 2, '教务', 'teach'),
  (4, 3, '后勤', 'logistic'),
  (5, 4, '系统', 'system'),
  (6, 5, '学生', 'student'),
  (7, 6, '教师', 'faculty'),
  (8, 7, '导师', 'advisor');