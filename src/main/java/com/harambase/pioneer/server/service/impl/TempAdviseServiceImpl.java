package com.harambase.pioneer.server.service.impl;

import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.common.support.util.DateUtil;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
import com.harambase.pioneer.server.dao.base.TempAdviseDao;
import com.harambase.pioneer.server.dao.repository.MessageRepository;
import com.harambase.pioneer.server.dao.repository.TempAdviseRepository;
import com.harambase.pioneer.server.pojo.base.TempAdvise;
import com.harambase.pioneer.server.pojo.base.TempCourse;
import com.harambase.pioneer.server.service.TempAdviseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TempAdviseServiceImpl implements TempAdviseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TempAdviseRepository tempAdviseRepository;
    private final MessageRepository messageRepository;

    private final TempAdviseDao tempAdviseDao;

    @Autowired
    public TempAdviseServiceImpl(TempAdviseRepository tempAdviseRepository,
                                 MessageRepository messageRepository,
                                 TempAdviseDao tempAdviseDao) {
        this.tempAdviseRepository = tempAdviseRepository;
        this.messageRepository = messageRepository;
        this.tempAdviseDao = tempAdviseDao;
    }

    @Override
    public ResultMap register(String studentId, String facultyIds) {
        try {
            TempAdvise newTempAdvise;
            TempAdvise existAdvise = tempAdviseRepository.findByStudentId(studentId);
            if (existAdvise != null) {
                existAdvise.setFacultyIds(facultyIds);
                existAdvise.setUpdateTime(DateUtil.DateToStr(new Date()));
                newTempAdvise = tempAdviseRepository.save(existAdvise);
            } else {
                TempAdvise tempAdvise = new TempAdvise();
                tempAdvise.setUpdateTime(DateUtil.DateToStr(new Date()));
                tempAdvise.setCreateTime(DateUtil.DateToStr(new Date()));
                tempAdvise.setStudentId(studentId);
                tempAdvise.setFacultyIds(facultyIds);
                tempAdvise.setStatus("1");
                newTempAdvise = tempAdviseRepository.save(tempAdvise);
            }

            return newTempAdvise == null ? ReturnMsgUtil.fail() : ReturnMsgUtil.success(newTempAdvise);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap deleteTempAdviseById(Integer id) {
        try {
            tempAdviseRepository.delete(id);
            int count = tempAdviseRepository.countById(id);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap get(String studentId) {
        try {
            TempAdvise tempAdvise = tempAdviseRepository.findByStudentId(studentId);
            return ReturnMsgUtil.success(tempAdvise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap tempAdviseList(String currentPage, String pageSize, String search, String order, String orderColumn) {
        ResultMap message = new ResultMap();
        try {

            long totalSize = tempAdviseDao.getCountByMapPageSearchOrdered(search);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<TempAdvise> tempAdvise = tempAdviseDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn);

            message.setData(tempAdvise);
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
    public ResultMap updateTempAdvise(Integer id, TempAdvise tempAdvise) {
        try {
            tempAdvise.setId(id);
            TempAdvise newTempAdvise = tempAdviseRepository.save(tempAdvise);
            return ReturnMsgUtil.success(newTempAdvise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
