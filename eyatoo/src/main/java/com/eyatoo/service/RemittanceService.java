package com.eyatoo.service;

import com.eyatoo.pojo.Remittance;

import java.util.List;

public interface RemittanceService {
    //添加打款记录
    Integer addRemittance(Remittance remittance);
    //财务管理 -- 账单管理 -- 打款记录
    List<Remittance> getAllRemittance(Integer branchId, Integer outPreId, String remittanceTime);
}
