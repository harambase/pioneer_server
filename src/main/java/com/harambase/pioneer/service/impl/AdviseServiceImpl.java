package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.repository.base.AdviseRepository;
import com.harambase.pioneer.dao.repository.view.AdviseViewRepository;
import com.harambase.pioneer.pojo.base.Advise;
import com.harambase.pioneer.pojo.view.AdviseView;
import com.harambase.pioneer.service.AdviseService;
import com.harambase.support.util.DateUtil;
import com.harambase.support.util.PageUtil;
import com.harambase.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdviseServiceImpl implements AdviseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AdviseRepository adviseRepository;
    private final AdviseViewRepository adviseViewRepository;

    @Autowired
    public AdviseServiceImpl(AdviseRepository adviseRepository, AdviseViewRepository adviseViewRepository) {
        this.adviseRepository = adviseRepository;
        this.adviseViewRepository = adviseViewRepository;
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

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));

            Pageable pageable;

            if (StringUtils.isEmpty(order) || order.toLowerCase().equals("desc")) {
                pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.DESC, orderColumn);
            } else {
                pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.ASC, orderColumn);
            }

            List<AdviseView> adviseList;

            if (StringUtils.isNotEmpty(studentid)) {
                adviseList = adviseViewRepository.findWithStudentId(search, search, search, search, studentid, pageable).getContent();
            } else if (StringUtils.isNotEmpty(facultyid)) {
                adviseList = adviseViewRepository.findWithFacultyId(search, search, search, search, facultyid, pageable).getContent();
            } else if (StringUtils.isNotEmpty(search)) {
                adviseList = adviseViewRepository.findSearchOnly(search, search, search, search, pageable).getContent();
            } else {
                adviseList = adviseViewRepository.findAll(pageable).getContent();
            }
            page.setTotalRows(adviseList.size());

            message.setData(adviseList);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());

            return message;
        } catch (NumberFormatException e) {
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
            AdviseView advise = adviseViewRepository.findOne(id);
            return ReturnMsgUtil.success(advise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
