package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.MapParam;
import com.harambase.pioneer.dao.repository.base.*;
import com.harambase.pioneer.dao.repository.view.CourseViewRepository;
import com.harambase.pioneer.pojo.base.Advise;
import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.pojo.base.Transcript;
import com.harambase.pioneer.pojo.view.CourseView;
import com.harambase.pioneer.service.MonitorService;
import com.harambase.support.charts.StaticGexfGraph;
import com.harambase.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MonitorServiceImpl implements MonitorService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonRepository personRepository;
    private final AdviseRepository adviseRepository;
    private final CourseViewRepository courseViewRepository;
    private final TranscriptRepository transcriptRepository;

    @Autowired
    public MonitorServiceImpl(PersonRepository personRepository, AdviseRepository adviseRepository, CourseViewRepository courseViewRepository,
                             TranscriptRepository transcriptRepository) {
        this.personRepository = personRepository;
        this.adviseRepository = adviseRepository;
        this.courseViewRepository = courseViewRepository;
        this.transcriptRepository = transcriptRepository;
    }

    @Override
    public HaramMessage userChart() {

        try {
            //统计用户种类
            List<Map<String, String>> data1 = new ArrayList<>();

            int s = personRepository.countByTypeAndStatus("s", "1");
            int f = personRepository.countByTypeAndStatus("f", "1");
            int a = personRepository.countByTypeAndStatus("a", "1");

            data1.add(MapParam.pieChartValue(String.valueOf(s), "StudentView"));
            data1.add(MapParam.pieChartValue(String.valueOf(f), "Faculty"));
            data1.add(MapParam.pieChartValue(String.valueOf(a), "Administrator"));


            //统计性别
            List<Map<String, String>> data2 = new ArrayList<>();
            int male = personRepository.countByGenderAndStatus("male", "1");
            int female = personRepository.countByGenderAndStatus("female", "1");

            data2.add(MapParam.pieChartValue(String.valueOf(male), "Male"));
            data2.add(MapParam.pieChartValue(String.valueOf(female), "Female"));

            HaramMessage message = new HaramMessage();
            message.put("dataBeast", data1);
            message.put("xAxisData", data2);

            return message;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage getSystemCount() {
        Map<String, Integer> data = new HashMap<>();

        int student = personRepository.countByTypeAndStatus("s", "1");
        int faculty = personRepository.countByTypeAndStatus("f", "1");
        int course  = courseViewRepository.countAllByStatus("1");

        data.put("student",student);
        data.put("faculty",faculty);
        data.put("course", course);

        return ReturnMsgUtil.success(data);
    }

    @Override
    public HaramMessage getRelationChart() {

        try {
            List<Person> personList = personRepository.findAll();
            List<CourseView> courseViewList = courseViewRepository.findAll();
            List<Transcript> transcriptList = transcriptRepository.findAll();
            List<Advise> adviseList = adviseRepository.findAll();

            String xml = StaticGexfGraph.graphGenerator(personList, courseViewList, transcriptList, adviseList);

            return ReturnMsgUtil.success(xml);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
