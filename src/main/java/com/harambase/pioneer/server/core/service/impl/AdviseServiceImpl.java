package com.harambase.pioneer.server.core.service.impl;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.server.core.dao.base.AdviseDao;
import com.harambase.pioneer.server.core.dao.repository.AdviseRepository;
import com.harambase.pioneer.server.core.pojo.base.Advise;
import com.harambase.pioneer.server.core.pojo.view.AdviseView;
import com.harambase.pioneer.server.core.service.AdviseService;
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
public class AdviseServiceImpl implements AdviseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AdviseRepository adviseRepository;

    private final AdviseDao adviseDao;

    @Autowired
    public AdviseServiceImpl(AdviseRepository adviseRepository, AdviseDao adviseDao) {
        this.adviseRepository = adviseRepository;
        this.adviseDao = adviseDao;
    }

    @Override
    public HaramMessage advisingList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                     String studentid, String facultyid) {
        try {
            HaramMessage message = new HaramMessage();

            switch (Integer.parseInt(orderColumn)) {
                case 0:
                    orderColumn = "sname";
                    break;
                case 1:
                    orderColumn = "fname";
                    break;
                case 2:
                    orderColumn = "status";
                    break;
                case 3:
                    orderColumn = "updateTime";
                    break;
                case 4:
                    orderColumn = "operator";
                    break;
                default:
                    orderColumn = "updateTime";
                    break;
            }

            long totalSize = adviseDao.getCountByMapPageSearchOrdered(facultyid, studentid, search);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<AdviseView> courseViewList = adviseDao.getByMapPageSearchOrdered(facultyid, studentid, search,
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


    @Override
    public HaramMessage assignMentor(Advise advise) {

        try {
            int count = adviseRepository.countByFacultyIdAndStudentId(advise.getFacultyId(), advise.getStudentId());
            if (count != 0)
                return ReturnMsgUtil.custom(FlagDict.ADVISE_DUPLICATE);

            Advise a = adviseRepository.save(advise);
            return a != null ? ReturnMsgUtil.success(a) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public HaramMessage removeMentor(Integer id) {
        try {
            adviseRepository.delete(id);
            int count = adviseRepository.countById(id);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage updateAdvise(Integer id, String studentId, String facultyId) {

        try {
            Advise advise = adviseRepository.findOne(id);

            advise.setId(id);
            advise.setStudentId(studentId);
            advise.setFacultyId(facultyId);
            advise.setUpdateTime(DateUtil.DateToStr(new Date()));
            Advise newAdvise = adviseRepository.save(advise);

            return ReturnMsgUtil.success(newAdvise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage getMentor(Integer id) {
        try {
            AdviseView advise = adviseDao.findOne(id);
            return ReturnMsgUtil.success(advise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
