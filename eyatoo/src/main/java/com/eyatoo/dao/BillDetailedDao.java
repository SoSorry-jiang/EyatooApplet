package com.eyatoo.dao;

import com.eyatoo.pojo.BillDetailed;

import java.util.Date;
import java.util.List;

public interface BillDetailedDao {
    //根据条件查看所有账目明细
    List<BillDetailed> getAllBillDetailed(Integer branchId,Integer outPreId,String beforeDate,String lastDate,Integer start,Integer end);
    //当资金流入，或流出时，添加一条账目明细
    Integer addBillDetailedByBranch(BillDetailed billDetailed);
}
