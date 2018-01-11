package com.harambase.pioneer.server.core.service.impl;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.server.core.dao.base.TranscriptDao;
import com.harambase.pioneer.server.core.dao.repository.TranscriptRepository;
import com.harambase.pioneer.server.core.pojo.base.Transcript;
import com.harambase.pioneer.server.core.pojo.view.TranscriptView;
import com.harambase.pioneer.server.core.service.TranscriptService;
import com.harambase.pioneer.support.util.DateUtil;
import com.harambase.pioneer.support.util.PageUtil;
import com.harambase.pioneer.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TranscriptServiceImpl implements TranscriptService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TranscriptRepository transcriptRepository;
    private final TranscriptDao transcriptDao;

    @Autowired
    public TranscriptServiceImpl(TranscriptRepository transcriptRepository, TranscriptDao transcriptDao) {
        this.transcriptRepository = transcriptRepository;
        this.transcriptDao = transcriptDao;
    }

    @Override
    public HaramMessage updateGrade(Integer id, Transcript transcript) {
        try {
            transcript.setId(id);
            transcript.setAssignTime(DateUtil.DateToStr(new Date()));
            Transcript newTranscript = transcriptRepository.save(transcript);
            return newTranscript != null ? ReturnMsgUtil.success(newTranscript) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage transcriptList(String currentPage, String pageSize, String search, String order, 
                                       String orderColumn, String studentId, String crn, String info) {

        HaramMessage message = new HaramMessage();

        switch (Integer.parseInt(orderColumn)) {
            case 0:
                orderColumn = "student_id";
                break;
            case 1:
                orderColumn = "crn";
                break;
            case 2:
                orderColumn = "grade";
                break;
            case 3:
                orderColumn = "complete";
                break;
            case 4:
                orderColumn = "assign_time";
                break;
            case 5:
                orderColumn = "sname";
                break;
            case 6:
                orderColumn = "cname";
                break;
            default:
                orderColumn = "id";
                break;
        }
        try {

            long totalSize = transcriptDao.getCountByMapPageSearchOrdered(search, studentId, crn, info);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<TranscriptView> transcriptViews = transcriptDao.getByMapPageSearchOrdered(page.getCurrentIndex(),
                    page.getPageSize(), search, order, orderColumn, studentId, crn, info);

            message.setData(transcriptViews);
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
