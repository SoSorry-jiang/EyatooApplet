package com.eyatoo.service;

import com.eyatoo.pojo.BillDetailed;
import com.eyatoo.pojo.Page;

import java.util.List;

public interface BillDetailedService {
    //当资金流入，或流出时，添加一条账目明细
    Integer addBillDetailedByBranch(BillDetailed billDetailed);

    //根据条件查看所有账目明细
    List<BillDetailed> getAllBillDetailed(Integer branchId,Integer outPreId, String beforeDate, String lastDate,Integer start,Integer end);
    Page pageAllBillDetailed(Integer branchId,Integer outPreId, String beforeDate, String lastDate,Page page);
}
