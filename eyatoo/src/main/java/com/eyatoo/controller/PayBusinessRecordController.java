package com.eyatoo.controller;


import com.eyatoo.pojo.Page;
import com.eyatoo.pojo.PayBusinessRecord;
import com.eyatoo.service.AccountsService;
import com.eyatoo.service.PayBusinessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PayBusinessRecordController {

    @Autowired
    private PayBusinessRecordService payBusinessRecordService;

    @Autowired
    private AccountsService accountsService;

    //总后台 确定一个时间区给门诊打款
    @RequestMapping("payBusinessToOutPre")
    private Integer payBusinessToOutPre(PayBusinessRecord payBusinessRecord){
        Integer isOK = payBusinessRecordService.addPayBusinessRecord(payBusinessRecord);
        if(isOK != 0){
             Integer isOkk = accountsService.updateForwardStatusByOneOutPre(payBusinessRecord.getPayOutpreId(),2,payBusinessRecord.getPayType(),payBusinessRecord.getAccountBeforeDate(),payBusinessRecord.getAccountAfterDate());
             return  isOkk;
        }
        return 0;
    }

    //门诊端 获取一个门诊的打款记录
    @RequestMapping("getOneOutpreAllPayBusinessRecord")
    private Map<String,Object> getOneOutpreAllPayBusinessRecord(Integer outPreId,Integer zhType,String Month,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo){
        Map<String,Object> msgMap = new HashMap<>();
        if(Month != null){
            Month  = Month+"-1";
        }
            //获取门诊所有打款记录的条数
            List<PayBusinessRecord> payBusinessRecordList1 = payBusinessRecordService.getOneOutpreAllPayBusiness(outPreId, zhType, Month,null,null);
            Page page = new Page();
            page.setSize(7);
            page.setStart(pageNo);
            Integer totalCount = payBusinessRecordList1.size();
            page.setTotalCount(totalCount);

            Page msgPage = payBusinessRecordService.pageOneOutpreAllPayBusiness(outPreId,zhType,Month,page);
            //获取月份获取已结算订单
            List<Map<String,Object>> allAccountList1 = accountsService.getFinancialAccounts(null, outPreId, 2,null, null,null,null);
            //获取一个月份门诊所有订单
            List<Map<String,Object>> allAccountList3 = accountsService.getFinancialAccounts(null, outPreId, null,null, null,null,null);
            //总需要打款
            BigDecimal allMoney = new BigDecimal("0.00");
            for (Map<String,Object> map:allAccountList3) {
                allMoney = allMoney.add((BigDecimal)map.get("finallay_money"));
            }
            //已打款金额
            BigDecimal ydkMoney = new BigDecimal("0.00");
            for (Map<String,Object> map:allAccountList1) {
                ydkMoney = ydkMoney.add((BigDecimal)map.get("finallay_money"));
            }
            //应打款金额
            BigDecimal ShouldDkMoney =  allMoney.subtract(ydkMoney);
            msgMap.put("ShouldDkMoney",ShouldDkMoney);
            msgMap.put("ydkMoney",ydkMoney);
            msgMap.put("allMoney",allMoney);
            msgMap.put("PageNo",msgPage.getStart());
            msgMap.put("countPage",msgPage.getTotalPage());
            msgMap.put("countMsg",msgPage.getTotalCount());
            msgMap.put("list",msgPage.getAllMsg());

        return msgMap;
    }
}
