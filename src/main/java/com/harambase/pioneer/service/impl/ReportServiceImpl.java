//package com.harambase.pioneer.service.impl;
//
//import com.harambase.common.HaramMessage;
//import com.harambase.pioneer.service.ReportService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReportServiceImpl implements ReportService {
//
//    @Override
//    public HaramMessage studentTranscriptReport(String studentid){
//        try {
//            String seme = "";
//            if(semeBelong.charAt(5) == '1'){
//                seme = "Spring";
//            }
//            else if(semeBelong.charAt(5) == '2'){
//                seme = "Summer";
//            }
//            else if(semeBelong.charAt(5) == '3'){
//                seme = "Fall";
//            }
//
//            String semeInfo = semeBelong + " (which refers to "
//                    + semeBelong.substring(0,4) + " " + seme + " semester)";
//
//            File fl = new File(id +"_"+semeBelong+"_Grade_Report.csv");
//            fl.createNewFile();
//            List<Course> stuCouList = SRSFunctions.makeCoursesList(id);
//            double credits = 0;
//            int index = 0, i = 4, j = 0;
//            double pass = 0 , notPass = 0, drop = 0, total = 0;
//            double passRate = 0, failRate = 0;
//            String[] info = new String[500];
//            Student stu = SRSFunctions.makeAStudent(id);
//            info[0] = "Student ID,Student Name,Student Earned Credits";
//            info[1] = id + "," + stu.getName() + "," + stu.getCredits();
//            info[2] = "Inquire For " + semeInfo + " courses grade.";
//            info[3] = "Data Format: ,Course crn,Course Name,Course Credits,Course Grade,Course Completion,Notification";
//            while(stuCouList.listIterator(index).hasNext()){
//                String crn = stuCouList.get(index).getCrn();
//                String semeCou = crn.substring(0, 6);
//                if(semeCou.equals(semeBelong)){
//                    Transcript tran = SRSFunctions.makeATranscript(id, crn);
//                    if(!tran.getGrade().equals("N/A")){
//                        if(tran.getGrade().equals("W")){
//                            info[i] = "," + crn + "," + stuCouList.get(index).getName()+","+stuCouList.get(index).getCredits()
//                                    + "," + tran.getGrade()
//                                    + "," + tran.getComplete() + ",The Student has dropped this course. Not included in passing "
//                                    + "rate calculation";
//                            drop++;
//                        }
//                        else if (tran.getGrade().equals("F")){
//                            info[i] = "," + crn + "," + stuCouList.get(index).getName()+","+stuCouList.get(index).getCredits()
//                                    + "," + tran.getGrade()
//                                    + "," + tran.getComplete() + ",The Student has failed this course.";
//                            notPass++;
//                            total++;
//                            credits = Double.parseDouble(stuCouList.get(index).getCredits()) + credits;
//                        }
//                        else{
//                            info[i] ="," + crn + "," + stuCouList.get(index).getName() + "," + stuCouList.get(index).getCredits()
//                                    +","+tran.getGrade() + "," + tran.getComplete();
//                            pass++;
//                            total++;
//                            credits = Double.parseDouble(stuCouList.get(index).getCredits()) + credits;
//                        }
//                        i++;
//                    }
//                    index++;
//                }
//            }
//            if(total!= 0){
//                passRate = (pass/total);
//                failRate = (notPass/total);
//            }
//            info[i] = "Genreal grade information about this Semester:, Total Registered Courses Amount = " + total
//                    + ", Total Passed Courses Amount = " + pass  + ", Total Failed Courses Amount = " + notPass
//                    + ", Total Dropped Courses Amount = " + drop + ", Pass rate = " + passRate + ", Failed rate = "
//                    + failRate;
//            i++;
//            info[i] = "Student Registered Credits = " + credits + "Student Earned Credits = " + stu.getCredits();
//            i++;
//            PrintWriter pw = new PrintWriter(fl);
//            while(j<i){
//                pw.println(info[j]);
//                j++;
//            }
//            pw.close();
//
//    }
//}
