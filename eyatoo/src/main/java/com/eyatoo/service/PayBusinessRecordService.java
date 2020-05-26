package com.eyatoo.service;

import com.eyatoo.pojo.Page;
import com.eyatoo.pojo.PayBusinessRecord;

import java.util.List;

public interface PayBusinessRecordService {
    //给门诊打款
    Integer addPayBusinessRecord(PayBusinessRecord payBusinessRecord);

    //门诊端  查找打款记录
    List<PayBusinessRecord> getOneOutpreAllPayBusiness(Integer outPreId, Integer type, String Month,Integer start,Integer end);
    //分页上面的方法
    Page pageOneOutpreAllPayBusiness(Integer outPreId, Integer type, String Month,Page page);
}
