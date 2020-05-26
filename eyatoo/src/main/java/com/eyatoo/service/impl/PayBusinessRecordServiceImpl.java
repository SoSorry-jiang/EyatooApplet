package com.eyatoo.service.impl;

import com.eyatoo.dao.PayBusinessRecordDao;
import com.eyatoo.pojo.Page;
import com.eyatoo.pojo.PayBusinessRecord;
import com.eyatoo.service.PayBusinessRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PayBusinessRecordServiceImpl implements PayBusinessRecordService {

    @Resource
    private PayBusinessRecordDao payBusinessRecordDao;

    @Override
    public Integer addPayBusinessRecord(PayBusinessRecord payBusinessRecord) {
        Integer isOk = 0;
        try {
            isOk = payBusinessRecordDao.addPayBusinessRecord(payBusinessRecord);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public List<PayBusinessRecord> getOneOutpreAllPayBusiness(Integer outPreId, Integer type, String Month,Integer start,Integer end) {
        List<PayBusinessRecord> payBusinessRecords = new ArrayList<>();
        try {
            payBusinessRecords = payBusinessRecordDao.getOneOutpreAllPayBusiness(outPreId, type, Month,null,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return payBusinessRecords;
    }

    @Override
    public Page pageOneOutpreAllPayBusiness(Integer outPreId, Integer type, String Month, Page page) {
        try {
            Integer start = (page.getStart()-1)*page.getSize();
            Integer end = page.getSize();
            List<PayBusinessRecord> accountsList = payBusinessRecordDao.getOneOutpreAllPayBusiness(outPreId, type, Month,start,end);
            page.setAllMsg(accountsList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return page;
    }
}
