package com.harambase.pioneer.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.server.dao.base.CourseDao;
import com.harambase.pioneer.server.dao.base.StudentDao;
import com.harambase.pioneer.server.dao.repository.CourseRepository;
import com.harambase.pioneer.server.helper.TimeValidate;
import com.harambase.pioneer.server.pojo.base.Course;
import com.harambase.pioneer.server.pojo.base.Transcript;
import com.harambase.pioneer.server.pojo.dto.Option;
import com.harambase.pioneer.server.pojo.view.CourseView;
import com.harambase.pioneer.server.service.CourseService;
import com.harambase.pioneer.server.dao.repository.TranscriptRepository;
import com.harambase.pioneer.common.support.util.DateUtil;
import com.harambase.pioneer.common.support.util.IDUtil;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository courseRepository;
    private final TranscriptRepository transcriptRepository;

    private final CourseDao courseDao;
    private final StudentDao studentDao;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             TranscriptRepository transcriptRepository,
                             CourseDao courseDao, StudentDao studentDao) {
        this.transcriptRepository = transcriptRepository;
        this.courseRepository = courseRepository;
        this.courseDao = courseDao;
        this.studentDao = studentDao;
    }

    @Override
    public HaramMessage addCourse(Course course) {

        try {
            course.setCreateTime(DateUtil.DateToStr(new Date()));
            course.setUpdateTime(DateUtil.DateToStr(new Date()));
            String facultyid = course.getFacultyId();

            //生成CRN
            String info = course.getInfo();
            List<CourseView> courses = courseDao.findAllCoursesViewByInfo(info);
            String crn = IDUtil.genCRN(info);
            CourseView cv;
            for (int i = 0; i < courses.size(); i++) {
                cv = courses.get(i);
                if (crn.equals(cv.getCrn())) {
                    crn = IDUtil.genCRN(info);
                    i = 0;
                }
            }
            course.setCrn(crn);

            if (!course.getFacultyId().equals(IDUtil.ROOT)) {
                //检查教师时间冲突
                if (TimeValidate.isTimeConflict(courseDao.findCourseViewByFacultyId(facultyid), courseDao.findByCrn(crn))) {
                    return ReturnMsgUtil.custom(FlagDict.TIME_CONFLICT);
                }
                course.setFacultyId(facultyid);
            }

            //插入课程
            Course newCourse = courseRepository.save(course);
            return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public HaramMessage delete(String crn) {
        try {
            Course course = courseRepository.findByCrn(crn);
            courseRepository.delete(course);
            int count = courseRepository.countCourseByCrn(crn);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage update(String crn, Course course) {
        try {
            course.setCrn(crn);
            course.setUpdateTime(DateUtil.DateToStr(new Date()));
            Course newCourse = courseRepository.save(course);
            return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage getCourseByCrn(String crn) {
        try {
            CourseView courseView = courseDao.findByCrn(crn);
            return ReturnMsgUtil.success(courseView);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage getCourseBySearch(String search, String status) {
        try {
            List<CourseView> results = courseDao.findTop5ByStatusAndSearch(search, status);
            return ReturnMsgUtil.success(results);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage assignFac2Cou(String crn, String facultyId) {

        try {
            if (!facultyId.equals(IDUtil.ROOT)) {
                //检查时间冲突
                if (TimeValidate.isTimeConflict(courseDao.findCourseViewByFacultyId(facultyId), courseDao.findByCrn(crn))) {
                    return ReturnMsgUtil.custom(FlagDict.TIME_CONFLICT);
                }
            }

            Course course = courseRepository.findByCrn(crn);
            course.setFacultyId(facultyId);
            course.setUpdateTime(DateUtil.DateToStr(new Date()));

            Course newCourse = courseRepository.save(course);
            return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage addStu2Cou(String crn, String studentId, Option option) {

        try {
            CourseView courseView = courseDao.findByCrn(crn);

            //检查课程状态
            Integer status = courseView.getStatus();
            if (status == -1) {
                return ReturnMsgUtil.custom(FlagDict.COURSE_DISABLED);
            }

            //检查时间冲突
            if (!option.isTime() && TimeValidate.isTimeConflict(courseDao.findCourseViewByStudentId("", studentId), courseView)) {
                return ReturnMsgUtil.custom(FlagDict.TIME_CONFLICT);
            }

            //检查课程容量
            int remain = courseView.getRemain();
            if (remain <= 0 && !option.isCapacity()) {
                return ReturnMsgUtil.custom(FlagDict.MAX_CAPACITY);
            }

            //检查预选
            String[] preCrns = courseView.getPrecrn().split("/");
            for (String preCrn : preCrns) {
                int count = transcriptRepository.countByStudentIdAndCrnAndComplete(studentId, preCrn, "1");
                if (count != 1 && !option.isPrereq()) {
                    return ReturnMsgUtil.custom(FlagDict.UNMET_PREREQ);
                }
            }

            Transcript transcript = new Transcript();
            transcript.setComplete("0");
            transcript.setGrade("*");
            transcript.setCrn(crn);
            transcript.setStudentId(studentId);
            transcript.setOperatorId(IDUtil.ROOT);
            transcript.setAssignTime(DateUtil.DateToStr(new Date()));

            //检查复选
            int count = transcriptRepository.countByStudentIdAndCrn(studentId, crn);
            if (count != 0) {
                return ReturnMsgUtil.custom(FlagDict.REPEAT);
            }

            //保存
            Transcript newTranscript = transcriptRepository.save(transcript);
            return newTranscript != null ? ReturnMsgUtil.success(newTranscript) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage removeStuFromCou(String crn, String studentId) {
        try {
            transcriptRepository.deleteTranscriptByStudentIdAndCrn(studentId, crn);
            int count = transcriptRepository.countByStudentIdAndCrn(studentId, crn);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage preCourseList(String crn) {

        try {
            String[] precrns = courseDao.findByCrn(crn).getPrecrn().split("/");
            List<CourseView> preCourses = new ArrayList<>();
            CourseView cv2;

            for (String precrn : precrns) {
                cv2 = courseDao.findByCrn(precrn);
                if (cv2 != null)
                    preCourses.add(cv2);
            }

            return ReturnMsgUtil.success(preCourses);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage reg2Course(String studentId, JSONObject choiceList) {

        try {
            Map<String, Object> result = new HashMap<>();
            List<String> failList = new ArrayList<>();
            List<String> choices = (List<String>) choiceList.get("choiceList");
            for (String crn : choices) {
                if (StringUtils.isEmpty(crn))
                    continue;
                CourseView courseView = courseDao.findByCrn(crn);
                String failInfo = "CRN：" + crn + ", 课程名：" + courseView.getName() + "，失败原因:";

                //检查课程状态
                Integer status = courseView.getStatus();
                if (status == -1) {
                    failList.add(failInfo + FlagDict.COURSE_DISABLED.getM());
                    continue;
                }
                //检查时间冲突
                if (TimeValidate.isTimeConflict(courseDao.findCourseViewByStudentId("", studentId), courseView)) {
                    failList.add(failInfo + FlagDict.TIME_CONFLICT.getM());
                    continue;
                }
                //检查课程容量
                int remain = courseView.getRemain();
                if (remain <= 0) {
                    failList.add(failInfo + FlagDict.MAX_CAPACITY.getM());
                    continue;
                }

                //检查预选
                boolean pre = true;
                String[] preCrns = courseView.getPrecrn().split("/");
                for (String preCrn : preCrns) {
                    int count = transcriptRepository.countByStudentIdAndCrnAndComplete(studentId, preCrn, "1");
                    if (count != 1) {
                        failList.add(failInfo + FlagDict.UNMET_PREREQ.getM());
                        pre = false;
                        break;
                    }
                }
                if (!pre)
                    continue;

                //检查复选
                int count = transcriptRepository.countByStudentIdAndCrn(studentId, crn);
                if (count != 0) {
                    failList.add(failInfo + FlagDict.REPEAT.getM());
                    continue;
                }

                Transcript transcript = new Transcript();
                transcript.setAssignTime(DateUtil.DateToStr(new Date()));
                transcript.setComplete("0");
                transcript.setGrade("*");
                transcript.setCrn(crn);
                transcript.setStudentId(studentId);
                transcript.setOperatorId(IDUtil.ROOT);

                //保存
                Transcript newTranscript = transcriptRepository.save(transcript);
                if (newTranscript == null) {
                    failList.add(failInfo + FlagDict.FAIL.getM());
                }

            }
            result.put("failList", failList);
            return ReturnMsgUtil.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public HaramMessage courseTreeList(String facultyid, String info) {

        try {
            List<Map<String, String>> infoList = new ArrayList<>();
            Set<String> infoSet = new HashSet<>();
            Map<String, String> infoMap;

            List<LinkedHashMap> courseViewList =
                    courseDao.getByMapPageSearchOrdered(facultyid, info, "", 0, Integer.MAX_VALUE, "desc", "crn");

            for (LinkedHashMap course : courseViewList) {
                infoSet.add((String) course.get("info"));
            }
            for (String i : infoSet) {
                infoMap = new HashMap<>();
                infoMap.put("node", "true");
                infoMap.put("info", i);

                infoList.add(infoMap);
            }

            List<Object> treeList = new ArrayList<>();
            treeList.addAll(infoList);
            treeList.addAll(courseViewList);

            return ReturnMsgUtil.success(treeList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage courseListInfo(String search) {
        try {
            List<String> infoList = courseRepository.getInfoList(search);
            return ReturnMsgUtil.success(infoList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage studentList(String crn, String search) {
        try {
            List<LinkedHashMap> studentList = studentDao.getStudentList(crn, search);
            return ReturnMsgUtil.success(studentList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage courseList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                   String facultyid, String info) {
        try {
            HaramMessage message = new HaramMessage();
            switch (Integer.parseInt(orderColumn)) {
                case 0:
                    orderColumn = "id";
                    break;
                case 1:
                    orderColumn = "crn";
                    break;
                case 2:
                    orderColumn = "name";
                    break;
                case 3:
                    orderColumn = "capacity";
                    break;
                case 4:
                    orderColumn = "remain";
                    break;
                case 5:
                    orderColumn = "status";
                    break;
                case 6:
                    orderColumn = "date";
                    break;
                case 7:
                    orderColumn = "time";
                    break;
                case 8:
                    orderColumn = "day";
                    break;
                case 9:
                    orderColumn = "faculty";
                    break;
                default:
                    orderColumn = "update_time";
                    break;
            }

            long totalSize = courseDao.getCountByMapPageSearchOrdered(facultyid, info, search);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<LinkedHashMap> courseViewList = courseDao.getByMapPageSearchOrdered(facultyid, info, search,
                    page.getCurrentIndex(), page.getPageSize(), order, orderColumn);

            message.setData(courseViewList);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

}
