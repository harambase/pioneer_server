package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.repository.base.TranscriptRepository;
import com.harambase.pioneer.dao.repository.view.TranscriptViewRepository;
import com.harambase.pioneer.pojo.base.Transcript;
import com.harambase.support.util.DateUtil;
import com.harambase.support.util.PageUtil;
import com.harambase.pioneer.dao.mapper.CourseMapper;
import com.harambase.pioneer.dao.mapper.TranscriptMapper;
import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.service.TranscriptService;
import com.harambase.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TranscriptServiceImpl implements TranscriptService{

    private final TranscriptMapper transcriptMapper;
    private final TranscriptRepository transcriptRepository;
    private final TranscriptViewRepository transcriptViewRepository;

    @Autowired
    public TranscriptServiceImpl(TranscriptMapper transcriptMapper, TranscriptRepository transcriptRepository, TranscriptViewRepository transcriptViewRepository) {
        this.transcriptRepository = transcriptRepository;
        this.transcriptViewRepository = transcriptViewRepository;
        this.transcriptMapper = transcriptMapper;
    }

    @Override
    public HaramMessage updateGrade(Transcript transcript) {

        transcript.setAssigntime(DateUtil.DateToStr(new Date()));
        Transcript newTranscript = transcriptRepository.save(transcript);

        return newTranscript != null ? ReturnMsgUtil.success(newTranscript) : ReturnMsgUtil.fail();
    }


    @Override
    public HaramMessage transcriptList(String currentPage, String pageSize, String search, String order, String orderColumn, String studentid, String crn) {
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 1:
                orderColumn = "studentid";
                break;
            case 2:
                orderColumn = "slast";
                break;
            case 3:
                orderColumn = "sfirst";
                break;
            case 4:
                orderColumn = "crn";
                break;
            case 5:
                orderColumn = "grade";
                break;
            case 6:
                orderColumn = "complete";
                break;
            case 7:
                orderColumn = "facultyid";
                break;
            case 8:
                orderColumn = "assigntime";
                break;
            default:
                orderColumn = "id";
                break;
        }
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("crn", crn);
            param.put("studentid", studentid);


            if (StringUtils.isEmpty(search))
                param.put("search", null);
            if (StringUtils.isEmpty(studentid))
                param.put("studentid", null);
            if (StringUtils.isEmpty(crn))
                param.put("crn", null);

            totalSize = transcriptMapper.getTranscriptCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize", page.getPageSize());
            param.put("order", order);
            param.put("orderColumn", orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<Person> msgs = transcriptMapper.getTranscriptByMapPageSearchOrdered(param);

            message.setData(msgs);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }
}
