package com.harambase.pioneer.service.Impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.mapper.StudentMapper;
import com.harambase.pioneer.pojo.Student;
import com.harambase.pioneer.pojo.dto.StudentView;
import com.harambase.pioneer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by linsh on 7/12/2017.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    @Override
    public HaramMessage transcriptDetail(String studentid) {
       HaramMessage haramMessage = new HaramMessage();
       try{
           StudentView sv = studentMapper.transcriptDetail(studentid);
           haramMessage.setData(sv);
           haramMessage.setCode(FlagDict.SUCCESS.getV());
           haramMessage.setMsg(FlagDict.SUCCESS.getM());
           return haramMessage;
       }catch(Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
       }

    }

    @Override
    public HaramMessage update(Student student) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            int ret = studentMapper.updateByPrimaryKey(student);
            if(ret == 1) {
                haramMessage.setData(student);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
                return haramMessage;
            }else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
                return haramMessage;
            }
        }catch(Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }
}
