//package com.harambase.pioneer.service.impl;
//
//import com.harambase.common.Config;
//import com.harambase.common.HaramMessage;
//import com.harambase.common.constant.FlagDict;
//import com.harambase.pioneer.pojo.view.TranscriptView;
//import com.harambase.support.util.DateUtil;
//import com.harambase.pioneer.dao.mapper.PersonMapper;
//import com.harambase.pioneer.dao.mapper.StudentMapper;
//import com.harambase.pioneer.dao.mapper.TranscriptMapper;
//import com.harambase.pioneer.pojo.base.Person;
//import com.harambase.pioneer.pojo.view.StudentView;
//import com.harambase.pioneer.service.ReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.*;
//import java.util.List;
//
//@Service
//public class ReportServiceImpl implements ReportService {
//
//    private final TranscriptMapper transcriptMapper;
//    private final PersonMapper personMapper;
//    private final StudentMapper studentMapper;
//
//    @Autowired
//    public ReportServiceImpl(TranscriptMapper transcriptMapper, PersonMapper personMapper, StudentMapper studentMapper){
//        this.transcriptMapper = transcriptMapper;
//        this.studentMapper = studentMapper;
//        this.personMapper = personMapper;
//    }
//
//    @Override
//    public HaramMessage studentTranscriptReport(String studentid){
//
//        FileOutputStream fos = null;
//        String filePath = Config.TEMP_FILE_PATH + studentid + ".pdf";
//        try {
//
//
//            StringBuilder exportInfoSb = new StringBuilder();
//
//            List<TranscriptView> transcriptViewList = transcriptMapper.studentTranscripts(studentid);
//            Person student = personMapper.selectByUserId(studentid);
//            StudentView studentViewView = studentMapper.creditsDetail(studentid);
//
//            if(transcriptViewList != null && transcriptViewList.size()>0) {
//                exportInfoSb.append("先锋学校学生成绩单\n")
//                            .append("生成时间:" + DateUtil.DateToStr(new Date())+"\n")
//                            .append("---------------------------------------------\n")
//                            .append("学生信息")
//                            .append("姓名：" + student.getLastname() + ", " + student.getFirstname() + "\n")
//                            .append("生日：" + student.getBirthday() + "\n")
//                            .append("----------------------------------------------\n")
//                            .append("成绩信息");
//
//                Set<String> infoSet = new HashSet<>();
//                List<TranscriptView> usedTranscript;
//                for (TranscriptView transcriptView: transcriptViewList){
//                    infoSet.add(transcriptView.getInfo());
//                }
//                String semeterInfo = "";
//                for (String info : infoSet) {
//                    usedTranscript = new ArrayList<>();
//                    semeterInfo += "学期：" + info + "\n";
//                    semeterInfo += "学生状态：\n";
//                    semeterInfo += "课程,课程名,教师,成绩,学分,总学时\n";
//
//                    for(TranscriptView transcriptView: transcriptViewList){
//                        if(transcriptView.getInfo().equals(info)){
//                            semeterInfo += transcriptView.getCrn() + ","
//                                    + transcriptView.getCoursename() + ","
//                                    + transcriptView.getFname() + ","
//                                    + transcriptView.getGrade() + ","
//                                    + transcriptView.getCredits() + ","
//                                    + "  " + "\n";
//
//                        }
//                        usedTranscript.add(transcriptView);
//                    }
//
//                    transcriptViewList.removeAll(usedTranscript);
//                }
//                exportInfoSb.append(semeterInfo);
////                fos.write(exportInfoSb.toString().getBytes("UTF-8"));
//                fos = new FileOutputStream(new File(filePath), true);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            HaramMessage restMessage = new HaramMessage();
//            restMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
//            return restMessage;
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        HaramMessage restMessage = new HaramMessage();
//        restMessage.setCode(FlagDict.SUCCESS.getV());
//        restMessage.setData(filePath);
//        return restMessage;
//    }
//}
