package com.harambase.pioneer.server.service.impl;

import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.common.support.util.*;
import com.harambase.pioneer.server.dao.base.ContractDao;
import com.harambase.pioneer.server.dao.repository.*;
import com.harambase.pioneer.server.pojo.base.Course;
import com.harambase.pioneer.server.pojo.base.Contract;
import com.harambase.pioneer.server.pojo.base.Student;
import com.harambase.pioneer.server.service.ContractService;
import com.harambase.pioneer.server.service.ContractService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ContractRepository contractRepository;

    //Only for search and paging functionality
    private final ContractDao contractDao;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, ContractDao contractDao) {
        this.contractRepository = contractRepository;
        this.contractDao = contractDao;
    }

    @Override
    public ResultMap addContract(Contract contract) {

        try {
            String contractId;
            String info = contract.getInfo();

            List<Contract> contractList = contractRepository.findByInfo(info);

            contractId = IDUtil.genContractID(contract.getInitDate());

            for (int i = 0; i < contractList.size(); i++) {
                Contract c = contractList.get(i);
                if (contractId.equals(c.getContractId())) {
                    contractId = IDUtil.genContractID(contract.getInitDate());
                    i = 0;
                }
            }
            contract.setContractId(contractId);
            contract.setCreateTime(DateUtil.DateToStr(new Date()));
            contract.setUpdateTime(DateUtil.DateToStr(new Date()));

            Contract newContract = contractRepository.save(contract);
            return newContract != null ? ReturnMsgUtil.success(newContract) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public ResultMap removeContract(String contractId) {
        try {
            Contract contract = contractRepository.findOne(contractId);
            contractRepository.delete(contract);
            int count = contractRepository.countByContractId(contractId);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap update(String contractId, Contract contract) {
        try {
            contract.setContractId(contractId);
            contract.setUpdateTime(DateUtil.DateToStr(new Date()));
            Contract newContract = contractRepository.save(contract);
            return newContract != null ? ReturnMsgUtil.success(newContract) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap getContract(String contractId) {
        try {
            Contract contract = contractRepository.findOne(contractId);
            return ReturnMsgUtil.success(contract);
        } catch (Exception e) {
            logger.error(e.toString());
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap contractList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                  String type, String status) {
        ResultMap message = new ResultMap();
        try {

            long totalSize = contractDao.getCountByMapPageSearchOrdered(search, type, status);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<Contract> contractList = contractDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, type, status);

            message.setData(contractList);
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
    public ResultMap listContracts(String search, String type, String status, String maxLength) {
        try {
            List<Contract> contracts = contractDao.getContractBySearch(search, type, status, maxLength);
            return ReturnMsgUtil.success(contracts);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap count(String type) {
        try {
            int count = contractRepository.countByType(type);
            return ReturnMsgUtil.success(count);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

}
