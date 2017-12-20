package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.repository.AdviseRepository;
import com.harambase.support.util.DateUtil;
import com.harambase.support.util.PageUtil;
import com.harambase.pioneer.dao.mapper.AdviseMapper;
import com.harambase.pioneer.pojo.Advise;
import com.harambase.pioneer.service.AdviseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdviseServiceImpl implements AdviseService{

    private final AdviseMapper adviseMapper;
    private final AdviseRepository adviseRepository;

    @Autowired
    public AdviseServiceImpl(AdviseMapper adviseMapper, AdviseRepository adviseRepository){
        this.adviseMapper = adviseMapper;
        this.adviseRepository = adviseRepository;
    }


    @Override
    public HaramMessage advisingList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                     String studentid, String facultyid) {
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
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("studentid", studentid);
            param.put("facultyid", facultyid);
            if(StringUtils.isEmpty(studentid))
                param.put("studentid", null);
            if(StringUtils.isEmpty(facultyid))
                param.put("facultyid", null);
            if(StringUtils.isEmpty(search))
                param.put("search", null);

//            totalSize = adviseMapper.getAdvisingCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

//            param.put("currentIndex", page.getCurrentIndex());
//            param.put("pageSize",  page.getPageSize());
//            param.put("order",  order);
//            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
//            List<Advise> msgs = adviseMapper.getAdvisingByMapPageSearchOrdered(param);

            Pageable pageable;
            if(StringUtils.isEmpty(order) || order.toLowerCase().equals("desc")){
                pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.DESC, orderColumn);
            }else{
//                pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.ASC, orderColumn);
                pageable = new PageRequest(0, 15, Sort.Direction.ASC, orderColumn);
            }
            List<Advise> msgs = adviseRepository.findAll(pageable).getContent();


            message.setData(msgs);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        }catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage updateAdvise(Integer id, String studentId, String facultyId) {

        HaramMessage haramMessage = new HaramMessage();
        try {
            Advise advise = new Advise();
            advise.setId(id);
            advise.setStudentid(studentId);
            advise.setFacultyid(facultyId);
            advise.setUpdateTime(DateUtil.DateToStr(new Date()));
            Advise newAdvise = adviseRepository.save(advise);
            if(newAdvise != null) {
                haramMessage.setData(newAdvise);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage assignMentor(Advise advise) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            advise.setUpdateTime(DateUtil.DateToStr(new Date()));
            int count = adviseRepository.countByFacultyidAndStudentid(advise.getFacultyid(), advise.getStudentid());
            if(count != 0){
                haramMessage.setCode(FlagDict.ADVISE_DUPLICATE.getV());
                haramMessage.setMsg(FlagDict.ADVISE_DUPLICATE.getM());
                return haramMessage;
            }
            Advise a = adviseRepository.save(advise);
            if(a != null) {
                haramMessage.setData(a);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage removeMentor(Integer id) {
        adviseRepository.delete(id);
        HaramMessage haramMessage = new HaramMessage();
        haramMessage.setCode(FlagDict.SUCCESS.getV());
        haramMessage.setMsg(FlagDict.SUCCESS.getM());
        return haramMessage;
    }

    @Override
    public HaramMessage getMentor(Integer id) {
        HaramMessage haramMessage = new HaramMessage();
        Advise advise = adviseRepository.findOne(id);
        haramMessage.setData(advise);
        haramMessage.setCode(FlagDict.SUCCESS.getV());
        haramMessage.setMsg(FlagDict.SUCCESS.getM());
        return haramMessage;
    }
}
