package com.eyatoo.service.impl;

import com.eyatoo.dao.BillDetailedDao;
import com.eyatoo.pojo.BillDetailed;
import com.eyatoo.pojo.Page;
import com.eyatoo.service.BillDetailedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BillDetailedServiceImpl implements BillDetailedService {
    @Resource
    private BillDetailedDao billDetailedDao;

    @Override
    public Integer addBillDetailedByBranch(BillDetailed billDetailed) {
        Integer isOK = 0;
        try {
             isOK= billDetailedDao.addBillDetailedByBranch(billDetailed);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<BillDetailed> getAllBillDetailed(Integer branchId,Integer outPreId, String beforeDate, String lastDate,Integer start,Integer end) {
        List<BillDetailed> billDetaileds = new ArrayList<>();
        try {
             billDetaileds = billDetailedDao.getAllBillDetailed(branchId,outPreId,beforeDate,lastDate,start,end);
        }catch (Exception e){
            e.printStackTrace();
        }
        return billDetaileds;
    }

    @Override
    public Page pageAllBillDetailed(Integer branchId, Integer outPreId, String beforeDate, String lastDate, Page page) {
        try {
            Integer start = (page.getStart()-1)*page.getSize();
            Integer end = page.getSize();
            List<BillDetailed> accountsList = billDetailedDao.getAllBillDetailed(branchId,outPreId,beforeDate,lastDate,start,end);
            page.setAllMsg(accountsList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return page;
    }
}
