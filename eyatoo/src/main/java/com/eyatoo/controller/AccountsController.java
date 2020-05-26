package com.eyatoo.controller;

import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import com.eyatoo.utils.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class AccountsController {
   @Autowired
    private AccountsService accountsService;

   @Autowired
   private UserService userService;

   @Autowired
   private DoctorService doctorService;

   @Autowired
   private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

   @Autowired
   private ProjectBranchService projectBranchService;

   @Autowired
   private DoctorProjectService doctorProjectService;

   @Autowired
   private BillDetailedService billDetailedService;

   @Autowired
   private FTPConfig ftpConfig;

   //用户或医生下单购买项目
    @RequestMapping("/addUserOrDoctorAccounts")
    private Integer addUserOrDoctorAccounts(Accounts accounts){
//                    #{projectId},
//                    #{doctorId},
//                    #{userId},
//                    #{branchId},
//                    #{Status},
//                    #{number},
//        null ,
//                    #{Remarks},
//                    #{finallayMoney},
//                    #{shouldPayMoney},
//                    #{marketingMoney},
//                    #{couponAgentId});
        //用户下单时自动获得原价格15%的平台服务费
        BigDecimal ptfw = accounts.getFinallayMoney().multiply(new BigDecimal("0.15"));
        accounts.setTechnologySupplyMoney(ptfw);
        accounts.setFinallyPayOutpreMoney(accounts.getFinallayMoney().subtract(ptfw));

        //当前端穿过来的值为　时，默认为０
         if(accounts.getMarketingMoney().equals("undefined") || accounts.getMarketingMoney().equals("")){
             accounts.setMarketingMoney(new BigDecimal("0"));
         }else if(accounts.getCouponAgentId().equals("undefined") || accounts.getCouponAgentId().equals("")){
             accounts.setCouponAgentId(0);
         }

        if(accounts.getStatus() != 1){
            //添加一条账目明细
            BillDetailed billDetailed = new BillDetailed();
            billDetailed.setTransactionBranchId(accounts.getBranchId());
            billDetailed.setTransactionRemarks("项目结算");
            billDetailed.setTransactionAddMoney(accounts.getFinallayMoney());
            billDetailed.setTransactionTime(new Date());
            billDetailedService.addBillDetailedByBranch(billDetailed);
        }
        Integer isOk = accountsService.addUserAccounts(accounts);
        return isOk;
    }

   //门诊端 数据管理
    @RequestMapping("getAppointedTimeAccounts")
    private Map<String,Object> getAppointedTimeAccounts(Integer typeId,Integer branchId,String beforeDate,String afterDate,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo){
          Map<String,Object> map = new HashMap<>();

          List<Map<String,Object>> accountsList = accountsService.getAppointedTimeAccounts(typeId, branchId, beforeDate, afterDate,null,null);
          Page page = new Page();
          page.setSize(7);
          page.setStart(pageNo);
          Integer totalCount = accountsList.size();
          page.setTotalCount(totalCount);

          Page msgPage = accountsService.pageAppointedTimeAccounts(typeId, branchId, beforeDate, afterDate,page);
          map.put("accList",msgPage.getAllMsg());
          map.put("PageNo",msgPage.getStart());
          map.put("countPage",msgPage.getTotalPage());
          map.put("countMsg",msgPage.getTotalCount());
          return map;
    }

   //门诊端 数据管理————查看明细
   @RequestMapping("getClinicOrders")
    private  List<Map<String,Object>>  getClinicOrders(Integer branchId, String appointedTime){
       List<Accounts> accounts = accountsService.getAllClinicOrdersByBranchId(branchId,null,0,appointedTime,null,null);
       List<Map<String,Object>> parmterList = new ArrayList<>();
       if(accounts.size() != 0) {
           for (Accounts account : accounts) {
               User user = userService.findById(account.getUserId());
               ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(account.getProjectId());
               ProjectBranch projectBranch = projectBranchService.getOneBranchById(account.getBranchId());
               double proMoney = doctorProjectService.getMoneyByDoctorAndBranchId(account.getDoctorId(), account.getProjectId());

               Map<String, Object> map = new HashMap<>();
               map.put("userName", user.getName());
               map.put("userPhone", user.getPhone());
               map.put("createDate", account.getCreateTime());
               map.put("proName", projectMedicalAdvertisement.getTitle());
               map.put("proMoney", proMoney);
               map.put("finallyMoney", account.getFinallayMoney());
               map.put("branchName", projectBranch.getStoreName());
               map.put("remarks", account.getRemarks());
               parmterList.add(map);
           }
       }

       return parmterList;
   }

   //门诊端查看自己的诊疗记录
   @RequestMapping("getTreatmentRecord")
    private List<Map<String ,Object>> getTreatmentRecord(@RequestParam(defaultValue = "0") Integer branchId,@RequestParam(defaultValue = "0") Integer outPreId,@RequestParam(defaultValue = "4,5,6") Integer[] status,String beforeDate,String afterDate){
       List<Map<String,Object>> parmterList = new ArrayList<>();
       List<Accounts> accountsList = accountsService.getAllClinicOrdersByBranchId(branchId,status,outPreId,null,beforeDate,afterDate);
       for (Accounts accounts: accountsList) {
           ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(accounts.getProjectId());
           ProjectBranch projectBranch = projectBranchService.getOneBranchById(accounts.getBranchId());
           double shouldPayMoney = doctorProjectService.getMoneyByDoctorAndBranchId(accounts.getDoctorId(),accounts.getProjectId());
           BigDecimal lastPayMoney = accounts.getFinallayMoney();
           Date finallyDate = accounts.getCreateTime();
           String Remark = accounts.getRemarks();
           Map<String ,Object> map = new HashMap<>();
           map.put("projectMedicalAdvertisement",projectMedicalAdvertisement.getTitle());
           map.put("projectBranch",projectBranch.getStoreName());
           map.put("shouldPayMoney",shouldPayMoney);
           map.put("lastPayMoney",lastPayMoney);
           map.put("yydate",accounts.getMedicalTime());
           map.put("finallyDate",finallyDate);
           map.put("Remark",Remark);
           parmterList.add(map);
       }
       return parmterList;
   }


   //门诊端查看 收支明细
    @RequestMapping("getAllBill")
    private Map<String,Object> getAllBill(Integer branchId,Integer outPreId,String beforeDate,String lastDate,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo){
        Map<String,Object> msgMap = new HashMap<>();

        List<BillDetailed> billDetailedList =billDetailedService.getAllBillDetailed(branchId,outPreId,beforeDate,lastDate,null,null);
        //总收入
        BigDecimal allInMoney = new BigDecimal("0.00");
        //总支出
        BigDecimal allOutMoney = new BigDecimal("0.00");
        for (BillDetailed billDetailed:billDetailedList) {
            allInMoney = allInMoney.add(billDetailed.getTransactionAddMoney());
            allOutMoney = allOutMoney.add(billDetailed.getTransactionReduceMoney());
            msgMap.put("allInMoney",allInMoney);
            msgMap.put("allOutMoney",allOutMoney);
        }

        Page page = new Page();
        page.setSize(5);
        page.setStart(pageNo);
        Integer totalCount = billDetailedList.size();
        page.setTotalCount(totalCount);

        Page msgPage = billDetailedService.pageAllBillDetailed(branchId,outPreId,beforeDate,lastDate,page);
        msgMap.put("list",msgPage.getAllMsg());
        msgMap.put("PageNo",msgPage.getStart());
        msgMap.put("countPage",msgPage.getTotalPage());
        msgMap.put("countMsg",msgPage.getTotalCount());
        return msgMap;
    }

    //门诊端 财务管理-账单管理
    @RequestMapping("getFinancialAccounts")
    private Map<String,Object> getFinancialAccounts(Integer branchId, Integer outPreId,@RequestParam(defaultValue = "1") Integer forwardStatus, String Month,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,@RequestParam(defaultValue = "0")Integer forwardType){
        Map<String,Object> msgMap = new HashMap<>();
        if(Month != null){
            Month  = Month+"-1";
        }
        List<Map<String,Object>> allAccountList = accountsService.getFinancialAccounts(branchId, outPreId, forwardStatus,forwardType, Month,null,null);
        Integer aclSize = allAccountList.size();
        Page page = new Page();
        page.setSize(7);
        page.setStart(pageNo);
        Integer totalCount = aclSize;
        page.setTotalCount(totalCount);
        Page msgPage = accountsService.pageFinancialAccounts(branchId,outPreId,forwardStatus,forwardType,Month,page);
        List<Map<String,Object>> accountsList = (List<Map<String,Object>>)msgPage.getAllMsg();
        //待结算金额(已结算金额)
        BigDecimal djsMoney = new BigDecimal("0.00");
        //总收入
        BigDecimal zsrMoney = new BigDecimal("0.00");
        //平台服务技术费
        BigDecimal ptfwMoney = new BigDecimal("0.00");
        //营销扣款
        BigDecimal yxkkMoney = new BigDecimal("0.00");
        //其他扣款
        BigDecimal qtMoney = new BigDecimal("0.00");
        for (Map<String,Object> map:allAccountList) {
            djsMoney = djsMoney.add((BigDecimal)map.get("finallay_money"));
            zsrMoney = zsrMoney.add((BigDecimal)map.get("spMoney"));
            ptfwMoney = ptfwMoney.add((BigDecimal)map.get("technology_supply_money"));
            yxkkMoney = yxkkMoney.add((BigDecimal)map.get("marketing_money"));
            qtMoney = qtMoney.add((BigDecimal)map.get("others_money"));
        }


        msgMap.put("list",accountsList);
        msgMap.put("djsMoney",djsMoney);
        msgMap.put("zsrMoney",zsrMoney);
        msgMap.put("ptfwMoney",ptfwMoney);
        msgMap.put("yxkkMoney",yxkkMoney);
        msgMap.put("qtMoney",qtMoney);
        msgMap.put("PageNo",msgPage.getStart());
        msgMap.put("countPage",msgPage.getTotalPage());
        msgMap.put("countMsg",msgPage.getTotalCount());
        return msgMap;
    }

    //用户端 用户添加日志时，加载该用户所购买过的项目
    @RequestMapping("/loginUserProject")
    public List<Map<String,Object>>  getUserBuyProject(Integer userId) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<Accounts> prodList = accountsService.getOneUserAccounts(userId);
        for (Accounts accounts : prodList) {
            Map<String,Object> map = accountsService.getAllUserUsedProject(accounts.getProjectId(),accounts.getDoctorId());
            map.put("createDate",accounts.getCreateTime());
            mapList.add(map);
        }
        return mapList;
    }

    //用户端 确认就诊后加载项目
    @RequestMapping("/loginUserClickProject")
    public Map<String,Object> loginUserClickProject(Integer proId,Integer doctorId){
        Map<String,Object> map = accountsService.getAllUserUsedProject(proId,doctorId);
        return  map;
    }

    //生成就诊码（待完善
    @RequestMapping("/getTreamentCode")
    public String getTreamentCode(String id) throws Exception {
        Accounts accounts = accountsService.getOneAccountsById(id);
        if(accounts.getTreamentcode() == null) {
            Random dom = new Random();

            String haomiaoshus = String.valueOf(System.currentTimeMillis());
            String fileName = "Treamentcode" + dom.nextInt(100) + haomiaoshus + ".jpg";
//        String filepath = ftpConfig.getServerPath()+"\\User\\InviteQRcode"+fileName;
            String imgPath = userService.findById(accounts.getUserId()).getTxPhoto();
            String filepath = ftpConfig.getServerPath() + "User/Treamentcode/" + fileName;
            String requestPath = "成功！";
            QRCodeUtil.encode(requestPath, imgPath, filepath, true);
            String dataBasePath = ftpConfig.getImageBaseUrl() + "User/Treamentcode/" + fileName;
            Integer isOk = accountsService.updateTreatmentCode(id, dataBasePath);
            if (isOk == 0)
                return "false";
            else
                return dataBasePath;
        }else {
            return accounts.getTreamentcode();
        }
    }

    //当门诊端这边确定预约时间的时候，更新账单表的预约状态
    @RequestMapping("updateMedTime")
    private Integer updateMedTime(String id,String medTime){
        return accountsService.updateMedTime(id,medTime);
    }

    //当用户在未付款页面点击付款按钮时，成功就更新账单表状态
    @RequestMapping("paySuccessAndUpdateAccountsStatus")
    private Integer paySuccessAndUpdateAccountsStatus(String id,Integer status){
        return accountsService.updateAccountsStatus(id,status);
    }

    //门诊聊天模块
    //查询一个门诊下的所有预约
    @RequestMapping("getOneOutpreAllReadyOrder")
    private List<Map<String,Object>> getOneOutpreAllReadyOrder(Integer outPreId,String id){
        List<Map<String,Object>> msgList = accountsService.getOneOutpreAllReadyOrder(outPreId,id);
        List<Map<String,Object>> trueMsgList = new ArrayList<>();
        for (Map<String,Object> map:msgList) {
            String agentString = map.get("agentId").toString();
            if(!agentString.equals("0")) {
                Integer agentId = Integer.parseInt(agentString);
                User user = userService.findById(agentId);
                if(user != null){
                    map.put("agentAvator",user.getTxPhoto());
                    map.put("agentLevel",user.getJjrLevel());
                    map.put("agentName",user.getName());
                }else {
                    Doctor doctor = doctorService.getOneOfDoctorById(agentId);
                    map.put("agentAvator",doctor.getDoctorAvator());
                    map.put("agentLevel",doctor.getJjrLevel());
                    map.put("agentName",doctor.getDoctorName());
                }
            }

            String accId = map.get("accId").toString();
            Accounts accounts = accountsService.getOneAccountsById(accId);
            map.put("MedTime",accounts.getMedicalTime());

            trueMsgList.add(map);
        }
        return trueMsgList;
    }
}
