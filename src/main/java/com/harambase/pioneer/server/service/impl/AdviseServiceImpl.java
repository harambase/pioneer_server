package com.harambase.pioneer.server.service.impl;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.server.dao.base.AdvisorDao;
import com.harambase.pioneer.server.pojo.base.Advise;
import com.harambase.pioneer.server.dao.base.AdviseDao;
import com.harambase.pioneer.server.dao.repository.AdviseRepository;
import com.harambase.pioneer.server.pojo.view.AdviseView;
import com.harambase.pioneer.server.pojo.view.AdvisorView;
import com.harambase.pioneer.server.service.AdviseService;
import com.harambase.pioneer.common.support.util.DateUtil;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
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
    private final AdvisorDao advisorDao;

    @Autowired
    public AdviseServiceImpl(AdviseRepository adviseRepository, AdviseDao adviseDao,
                             AdvisorDao advisorDao) {
        this.adviseRepository = adviseRepository;
        this.adviseDao = adviseDao;
        this.advisorDao = advisorDao;
    }

    @Override
    public ResultMap advisingList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                     String studentId, String facultyId, String info) {
        try {
            ResultMap message = new ResultMap();

            long totalSize = adviseDao.getCountByMapPageSearchOrdered(facultyId, studentId, info, search);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<AdviseView> adviseViewList = adviseDao.getByMapPageSearchOrdered(facultyId, studentId, info, search,
                    page.getCurrentIndex(), page.getPageSize(), order, orderColumn);

            message.setData(adviseViewList);
            message.put("page", page);
            message.setMsg(SystemConst.SUCCESS.getMsg());
            message.setCode(SystemConst.SUCCESS.getCode());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();

        }

    }

    @Override
    public ResultMap assignMentor(Advise advise) {

        try {
            int count = adviseRepository.countByFacultyIdAndStudentId(advise.getFacultyId(), advise.getStudentId());
            if (count != 0)
                return ReturnMsgUtil.custom(SystemConst.ADVISE_DUPLICATE);

            Advise a = adviseRepository.save(advise);
            return a != null ? ReturnMsgUtil.success(a) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public ResultMap removeMentor(Integer id) {
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
    public ResultMap updateAdvise(Integer id, Advise advise) {

        try {
            advise.setId(id);
            advise.setUpdateTime(DateUtil.DateToStr(new Date()));
            Advise newAdvise = adviseRepository.save(advise);
            return ReturnMsgUtil.success(newAdvise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap getMentor(Integer id) {
        try {
            AdviseView advise = adviseDao.findOne(id);
            return ReturnMsgUtil.success(advise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap getAdviseByStudentId(String studentId) {
        try {
            Advise advise = adviseRepository.findOneByStudentId(studentId);
            return ReturnMsgUtil.success(advise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap advisorList(String currentPage, String pageSize, String search, String order, String orderColumn, String status) {
        try {
            ResultMap message = new ResultMap();

            long totalSize = advisorDao.getAdvisorCountByMapPageSearchOrdered(status, search);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<AdvisorView> advisorViewList = advisorDao.getAdvisorByMapPageSearchOrdered(status, search,
                    page.getCurrentIndex(), page.getPageSize(), order, orderColumn);

            message.setData(advisorViewList);
            message.put("page", page);
            message.setMsg(SystemConst.SUCCESS.getMsg());
            message.setCode(SystemConst.SUCCESS.getCode());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();

        }


    }
}
