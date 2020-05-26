package com.eyatoo.dao;

import com.eyatoo.pojo.PayBusinessRecord;

import java.util.List;
import java.util.Map;

public interface PayBusinessRecordDao {
    //给门诊打款
    Integer addPayBusinessRecord(PayBusinessRecord payBusinessRecord);

    //门诊端  查找打款记录
    List<PayBusinessRecord> getOneOutpreAllPayBusiness(Integer outPreId, Integer type, String Month,Integer start,Integer end);

}
