package com.eyatoo.service.impl;

import com.eyatoo.dao.AccountsDao;
import com.eyatoo.pojo.Accounts;
import com.eyatoo.pojo.Page;
import com.eyatoo.service.AccountsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountsServiceImpl implements AccountsService {
   @Resource
    private AccountsDao accountsDao;

    @Override
    public List<Accounts> getOneUserAccounts(Integer id) {
        List<Accounts> accounts = null;
        try {
            accounts = accountsDao.getOneUserAccounts(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Accounts> getDoctorAccountStatus(Integer doctorId, Integer[] status) {
        List<Accounts> accounts = new ArrayList<>();
        try {
            accounts =   accountsDao.getDoctorAccountStatus(doctorId,status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Accounts> getUserAccountStatus(Integer userId, Integer status) {
        List<Accounts> accounts = new ArrayList<>();
        try {
          accounts =   accountsDao.getUserAccountStatus(userId,status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Accounts selectUserCoummityAccounts(Integer projectId, Integer userId) {
        Accounts accounts = null;
        try {
            accounts = accountsDao.selectUserCoummityAccounts(projectId,userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Accounts> getAllClinicOrdersByBranchId(Integer branchId,Integer[] status,Integer outPreId,String appointedTime,String beforeDate,String afterDate) {
        List<Accounts> accounts = null;
        try {
             accounts = accountsDao.getAllClinicOrdersByBranchId(branchId,status,outPreId,appointedTime,beforeDate,afterDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Map<String,Object>> getAppointedTimeAccounts(Integer typeId, Integer branchId, String beforeDate, String afterDate,Integer start,Integer end) {
        List<Map<String,Object>> accounts = new ArrayList<>();
        try {
           accounts = accountsDao.getAppointedTimeAccounts(typeId, branchId, beforeDate, afterDate,null,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Map<String, Object>> getFinancialAccounts(Integer branchId, Integer outPreId,Integer forwardStatus,Integer forwardType, String Month,Integer start,Integer end) {
        List<Map<String,Object>> accounts = new ArrayList<>();
        try {
            accounts = accountsDao.getFinancialAccounts(branchId, outPreId,forwardStatus,forwardType, Month,start,end);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Map<String,Object> getAllUserUsedProject(Integer id,Integer doctorId) {
        Map<String,Object> accounts = new HashMap<>();
        try {
            accounts = accountsDao.getAllUserUsedProject(id,doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Accounts> getOneUserDisTreatment(Integer id) {
        List<Accounts> accounts = new ArrayList<>();
        try {
            accounts = accountsDao.getOneUserDisTreatment(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Integer addUserAccounts(Accounts accounts) {
        Integer isOK = 0;
        try {
            isOK = accountsDao.addUserAccounts(accounts);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Accounts getUserLastOneOfProjectBy(Integer userId, Integer agentId) {
        Accounts accounts = new Accounts();
        try {
              accounts = accountsDao.getUserLastOneOfProjectBy(userId, agentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Accounts> getUserAllProjectBy(Integer userId, Integer agentId) {
        List<Accounts> accounts = new ArrayList<>();
        try {
              accounts = accountsDao.getUserAllProjectBy(userId, agentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Integer updateTreatmentCode(String id, String codePath) {
        Integer isOK = 0;
        try {
             isOK = accountsDao.updateTreatmentCode(id, codePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Accounts getOneAccountsById(String id) {
        Accounts accounts = new Accounts();
        try {
             accounts = accountsDao.getOneAccountsById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }


    @Override
    public Integer updateAccountsStatus(String id, Integer status) {
        Integer isOK = 0;
        try {
             isOK = accountsDao.updateAccountsStatus(id, status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<Accounts> getOneUserIsTreatment(Integer id) {
        List<Accounts> accounts = new ArrayList<>();
        try {
            accounts = accountsDao.getOneUserIsTreatment(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }


    @Override
    public Page pageAppointedTimeAccounts(Integer typeId, Integer branchId, String beforeDate, String afterDate, Page page) {
        try {
            Integer start = (page.getStart()-1)*page.getSize();
            Integer end = page.getSize();
            List<Map<String,Object>> accountsList = accountsDao.getAppointedTimeAccounts(typeId, branchId, beforeDate, afterDate,start,end);
            page.setAllMsg(accountsList);
        }catch (Exception e){
            e.printStackTrace();
         }
        return page;
    }

    @Override
    public Integer updateMedTime(String id, String medTime) {
        Integer isOK = 0;
        try {
             isOK = accountsDao.updateMedTime(id, medTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Page pageFinancialAccounts(Integer branchId, Integer outPreId, Integer forwardStatus,Integer forwardType, String Month, Page page) {
        try {
            Integer start = (page.getStart()-1)*page.getSize();
            Integer end = page.getSize();
            List<Map<String,Object>> accountsList = accountsDao.getFinancialAccounts(branchId,outPreId,forwardStatus,forwardType,Month,start,end);
            page.setAllMsg(accountsList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public Integer updateForwardStatusByOneOutPre(Integer outPreId, Integer status, Integer payType, String beforeDate, String afterDate) {
        Integer isOK = 0;
        try {
           isOK = accountsDao.updateForwardStatusByOneOutPre(outPreId, status, payType, beforeDate, afterDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<Map<String, Object>> getOneOutpreAllReadyOrder(Integer outPreId,String accId) {
        List<Map<String,Object>> list = new ArrayList<>();
        try {
             list = accountsDao.getOneOutpreAllReadyOrder(outPreId,accId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
