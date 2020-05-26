package com.eyatoo.controller;

import com.eyatoo.pojo.Remittance;
import com.eyatoo.service.RemittanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RemittanceController {
   @Autowired
   private RemittanceService remittanceService;

    //财务管理 -- 账单管理 -- 打款记录
   @RequestMapping("getAllRemittance")
   private List<Remittance> getAllRemittance(@RequestParam(defaultValue = "0")Integer branchId, @RequestParam(defaultValue = "0") Integer outPreId, String remittanceTime){
       return remittanceService.getAllRemittance(branchId, outPreId, remittanceTime);
   }

    //添加打款记录
   @RequestMapping("addRemittance")
    private Integer addRemittance(Remittance remittance){
       return remittanceService.addRemittance(remittance);
   }
}
