/*
Pioneer Init Databases:
MySQL - 5.7.18 : Database - pioneer_2.0
*********************************************************************
*/

# clear all the tables:
TRUNCATE advise;
TRUNCATE assignment;
TRUNCATE course;
TRUNCATE message;
TRUNCATE person;
TRUNCATE pin;
TRUNCATE role;
TRUNCATE student;
TRUNCATE tempadvise;
TRUNCATE tempcourse;
TRUNCATE tempuser;
TRUNCATE transcript;

#INIT ROOT ACCOUNT:
INSERT INTO `person`
(`user_id`, `username`, `first_name`, `last_name`, `password`, `status`, `info`, `type`, `role_id`, `birthday`, `email`, `tel`, `qq`, `we_chat`, `dorm`, `gender`, `create_time`, `update_time`, `base_info`, `comment`, `profile`, `user_info`, `address`)
VALUES
  ('9201701000', 'root', 'admin', 'system', 'e10adc3949ba59abbe56e057f20f883e', '1', '2017-01', 's/f/a/', '0/1/',
                 '1980-01-01', 'admin@pioneer.edu', '13220184951', '1343214384', '1343214384', '', 'male',
                                                    '1970-01-01 07:00:00', '1970-01-01 14:11:26', NULL, '请勿删除',
                                                    '', '', NULL);

#INIT ROLE PROPERTIES:
INSERT INTO `role` (`id`, `role_id`, `role_name`, `role_code`)
VALUES
  (0, 0, '用户', 'user'),
  (1, 1, '管理员', 'admin'),
  (2, 2, '教务', 'teach'),
  (3, 3, '后勤', 'logistic'),
  (4, 4, '系统', 'system'),
  (5, 5, '学生', 'student'),
  (6, 6, '教师', 'faculty'),
  (7, 7, '导师', 'advisor');
