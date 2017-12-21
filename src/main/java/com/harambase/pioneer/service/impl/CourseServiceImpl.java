package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.common.helper.TimeValidate;
import com.harambase.pioneer.dao.repository.base.CourseRepository;
import com.harambase.pioneer.dao.repository.base.TranscriptRepository;
import com.harambase.pioneer.dao.repository.view.CourseViewRepository;
import com.harambase.pioneer.pojo.base.Course;
import com.harambase.pioneer.pojo.base.Transcript;
import com.harambase.pioneer.pojo.dto.Option;
import com.harambase.pioneer.pojo.view.CourseView;
import com.harambase.pioneer.service.CourseService;
import com.harambase.support.util.DateUtil;
import com.harambase.support.util.IDUtil;
import com.harambase.support.util.PageUtil;
import com.harambase.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseViewRepository courseViewRepository;
    private final TranscriptRepository transcriptRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseViewRepository courseViewRepository,
                             TranscriptRepository transcriptRepository) {
        this.transcriptRepository = transcriptRepository;
        this.courseRepository = courseRepository;
        this.courseViewRepository = courseViewRepository;
    }

    @Override
    public HaramMessage create(Course course) {

        course.setCreatetime(DateUtil.DateToStr(new Date()));
        course.setUpdatetime(DateUtil.DateToStr(new Date()));
        String facultyid = course.getFacultyid();

        //生成CRN
        String info = course.getInfo();
        List<CourseView> courses = courseViewRepository.findAllCoursesViewByInfo(info);
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

        if(!course.getFacultyid().equals(IDUtil.ROOT)) {
            //检查教师时间冲突
            if (TimeValidate.isTimeConflict(courseViewRepository.findCourseViewByFacultyid(facultyid), courseViewRepository.findByCrn(crn))) {
                return ReturnMsgUtil.custom(FlagDict.TIMECONFLICT);
            }
            course.setFacultyid(facultyid);
        }

        //插入课程
        Course newCourse = courseRepository.save(course);
        return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();

    }

    @Override
    public HaramMessage delete(String crn) {
        Course course = courseRepository.findByCrn(crn);
        courseRepository.delete(course);
        int count = courseRepository.countCourseByCrn(crn);
        return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage update(Course course) {
        course.setUpdatetime(DateUtil.DateToStr(new Date()));
        Course newCourse = courseRepository.save(course);
        return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage getCourseByCrn(String crn) {
        CourseView courseView = courseViewRepository.findByCrn(crn);
        return ReturnMsgUtil.success(courseView);
    }

    @Override
    public HaramMessage getCourseBySearch(String search, String status) {
        List<CourseView> results = courseViewRepository.findTop5ByStatusAndSearch(status, search);
        return ReturnMsgUtil.success(results);
    }

    @Override
    public HaramMessage assignFac2Cou(String crn, String facultyId) {

        if(!facultyId.equals(IDUtil.ROOT)) {
            //检查时间冲突
            if (TimeValidate.isTimeConflict(courseViewRepository.findCourseViewByFacultyid(facultyId), courseViewRepository.findByCrn(crn))) {
                return ReturnMsgUtil.custom(FlagDict.TIMECONFLICT);
            }
        }

        Course course = courseRepository.findByCrn(crn);
        course.setFacultyid(facultyId);
        course.setUpdatetime(DateUtil.DateToStr(new Date()));

        Course newCourse = courseRepository.save(course);
        return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage addStu2Cou(String crn, String studentId, Option option) {

        CourseView courseView = courseViewRepository.findByCrn(crn);

        //检查课程状态
        String status = courseView.getStatus();
        if (status.equals("-1")) {
            return ReturnMsgUtil.custom(FlagDict.COURSE_DISABLED);
        }

        //检查时间冲突
        if (!option.isTime() && TimeValidate.isTimeConflict(courseViewRepository.findCourseViewByStudentId(studentId), courseView)) {
            return ReturnMsgUtil.custom(FlagDict.TIMECONFLICT);
        }

        //检查课程容量
        int remain = courseView.getRemain();
        if (remain <= 0 && !option.isCapacity()) {
            return ReturnMsgUtil.custom(FlagDict.MAX_CAPACITY);
        }

        //检查预选
        String[] preCrns = courseView.getPrecrn().split("/");
        for (String preCrn : preCrns) {
            int count = transcriptRepository.countByStudentidAndCrnAndComplete(studentId, preCrn, "1");
            if (count != 1 && !option.isPrereq()) {
                return ReturnMsgUtil.custom(FlagDict.UNMET_PREREQ);
            }
        }

        Transcript transcript = new Transcript();
        transcript.setComplete("0");
        transcript.setGrade("*");
        transcript.setCrn(crn);
        transcript.setStudentid(studentId);
        transcript.setOperator(IDUtil.ROOT);
        transcript.setAssigntime(DateUtil.DateToStr(new Date()));

        //检查复选
        int count = transcriptRepository.countByStudentidAndCrn(studentId, crn);
        if (count != 0) {
            return ReturnMsgUtil.custom(FlagDict.REPEAT);
        }

        //保存
        Transcript newTranscript = transcriptRepository.save(transcript);
        return newTranscript != null ? ReturnMsgUtil.success(newTranscript) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage removeStuFromCou(String crn, String studentid) {
        transcriptRepository.deleteTranscriptByStudentidAndCrn(studentid, crn);
        int count = transcriptRepository.countByStudentidAndCrn(studentid, crn);
        return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage countActiveCourse() {
        int count = courseViewRepository.countAllByStatus("1");
        return ReturnMsgUtil.success(count);
    }

    @Override
    public HaramMessage preCourseList(String crn) {

        String[] precrns = courseViewRepository.findByCrn(crn).getPrecrn().split("/");
        List<CourseView> preCourses = new ArrayList<>();
        CourseView cv2;

        for (String precrn : precrns) {
            cv2 = courseViewRepository.findByCrn(precrn);
            if (cv2 != null)
                preCourses.add(cv2);
        }

        return ReturnMsgUtil.success(preCourses);
    }

    @Override
    public HaramMessage reg2Course(String studentId, String[] choices) {

        Map<String, Object> result = new HashMap<>();
        List<String> failList = new ArrayList<>();

        for (String crn : choices) {

            CourseView courseView = courseViewRepository.findByCrn(crn);
            String failInfo = "CRN：" + crn + ", 课程名：" + courseView.getName() + "，失败原因:";

            //检查课程状态
            String status = courseView.getStatus();
            if (status.equals("-1")) {
                failList.add(failInfo + FlagDict.COURSE_DISABLED.getM());
                continue;
            }
            //检查时间冲突
            if (TimeValidate.isTimeConflict(courseViewRepository.findCourseViewByStudentId(studentId), courseView)) {
                failList.add(failInfo + FlagDict.TIMECONFLICT.getM());
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
                int count = transcriptRepository.countByStudentidAndCrnAndComplete(studentId, preCrn, "1");
                if (count != 1) {
                    failList.add(failInfo + FlagDict.UNMET_PREREQ.getM());
                    pre = false;
                    break;
                }
            }
            if (!pre)
                continue;

            //检查复选
            int count = transcriptRepository.countByStudentidAndCrn(studentId, crn);
            if (count != 0) {
                failList.add(failInfo + FlagDict.REPEAT.getM());
                continue;
            }

            Transcript transcript = new Transcript();
            transcript.setAssigntime(DateUtil.DateToStr(new Date()));
            transcript.setComplete("0");
            transcript.setGrade("*");
            transcript.setCrn(crn);
            transcript.setStudentid(studentId);
            transcript.setOperator(IDUtil.ROOT);

            //保存
            Transcript newTranscript = transcriptRepository.save(transcript);
            if (newTranscript == null) {
                failList.add(failInfo + FlagDict.FAIL.getM());
            }

        }
        result.put("failList", failList);
        return ReturnMsgUtil.success(result);

    }

    @Override
    public HaramMessage courseTreeList(String facultyid, String info) {

        List<Map<String, String>> infoList = new ArrayList<>();
        Set<String> infoSet = new HashSet<>();
        Map<String, String> infoMap;
        List<CourseView> courseViewList;
        String search = "";
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.DESC, "crn");

        if (StringUtils.isNotEmpty(facultyid) && StringUtils.isNotEmpty(info)) {
            courseViewList = courseViewRepository.findWithFacultyIdAndInfo(search, search, search, search, search, search, search, search, search, search, search, search, search, facultyid, info, pageable).getContent();
        } else if (StringUtils.isNotEmpty(info)) {
            courseViewList = courseViewRepository.findWithInfo(search, search, search, search, search, search, search, search, search, search, search, search, search, info, pageable).getContent();
        } else if (StringUtils.isNotEmpty(facultyid)) {
            courseViewList = courseViewRepository.findWithFacultyId(search, search, search, search, search, search, search, search, search, search, search, search, search, facultyid, pageable).getContent();
        } else {
            courseViewList = courseViewRepository.findAll(pageable).getContent();
        }

        for (CourseView course : courseViewList) {
            infoSet.add(course.getInfo());
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
    }

    @Override
    public HaramMessage courseList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                   String facultyid, String info) {
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
                orderColumn = "coulev";
                break;
            case 4:
                orderColumn = "cousec";
                break;
            case 5:
                orderColumn = "capa";
                break;
            case 6:
                orderColumn = "remain";
                break;
            case 7:
                orderColumn = "status";
                break;
            case 8:
                orderColumn = "date";
                break;
            case 9:
                orderColumn = "time";
                break;
            case 10:
                orderColumn = "day";
                break;
            case 11:
                orderColumn = "faculty";
                break;
            default:
                orderColumn = "updatetime";
                break;
        }

        Page page = new Page();
        page.setCurrentPage(PageUtil.getcPg(currentPage));
        page.setPageSize(PageUtil.getLimit(pageSize));

        Pageable pageable;
        if (StringUtils.isEmpty(order) || order.toLowerCase().equals("desc")) {
            pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.DESC, orderColumn);
        } else {
            pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.ASC, orderColumn);
        }

        List<CourseView> courseViewList;

        if (StringUtils.isNotEmpty(facultyid) && StringUtils.isNotEmpty(info)) {
            courseViewList = courseViewRepository.findWithFacultyIdAndInfo(search, search, search, search, search, search, search, search, search, search, search, search, search, facultyid, info, pageable).getContent();
        } else if (StringUtils.isNotEmpty(info)) {
            courseViewList = courseViewRepository.findWithInfo(search, search, search, search, search, search, search, search, search, search, search, search, search, info, pageable).getContent();
        } else if (StringUtils.isNotEmpty(facultyid)) {
            courseViewList = courseViewRepository.findWithFacultyId(search, search, search, search, search, search, search, search, search, search, search, search, search, facultyid, pageable).getContent();
        } else if (StringUtils.isNotEmpty(search)) {
            courseViewList = courseViewRepository.findSearchOnly(search, search, search, search, search, search, search, search, search, search, search, search, search, pageable).getContent();
        } else {
            courseViewList = courseViewRepository.findAll(pageable).getContent();
        }
        page.setTotalRows(courseViewList.size());

        message.setData(courseViewList);
        message.put("page", page);
        message.setMsg(FlagDict.SUCCESS.getM());
        message.setCode(FlagDict.SUCCESS.getV());
        return message;

    }

}
