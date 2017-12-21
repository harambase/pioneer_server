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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MonitorServiceImpl implements MonitorService{

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
    }

    @Override
    public HaramMessage getRelationChart() {

        List<Person> personList = personRepository.findAll();
        List<CourseView> courseViewList = courseViewRepository.findAll();
        List<Transcript> transcriptList = transcriptRepository.findAll();
        List<Advise> adviseList = adviseRepository.findAll();

        String xml = StaticGexfGraph.graphGenerator(personList, courseViewList, transcriptList, adviseList);

        return ReturnMsgUtil.success(xml);
    }
}