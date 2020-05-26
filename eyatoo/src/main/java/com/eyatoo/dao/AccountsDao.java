package com.eyatoo.dao;

import com.eyatoo.pojo.Accounts;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AccountsDao {
    //根据id查找单个账单信息
    Accounts getOneAccountsById(String id);

    //用户下单添加一条记录
    Integer addUserAccounts(Accounts accounts);

    //单个用户查看自己账户
    List<Accounts> getOneUserAccounts(Integer id);

    //用户查看自己所有未就诊的订单
    List<Accounts> getOneUserDisTreatment(Integer id);

    //医生查看自己订单状态
    List<Accounts> getDoctorAccountStatus(Integer doctorId,Integer[] status);

    //用户查看自己订单状态
    List<Accounts> getUserAccountStatus(Integer userId,Integer status);

    //查找单个日志里的项目和医生id
    Accounts selectUserCoummityAccounts(Integer projectId,Integer userId);

    //根据分店id 或 加上交易状态 查询所有交易记录
    List<Accounts> getAllClinicOrdersByBranchId(@Param("branchId") Integer branchId,@Param("status") Integer[] status,Integer outPreId,String appointedTime,String beforeDate,String afterDate);

    //根据诊疗项目类型，和时间， 每天结算订单
    List<Map<String,Object>> getAppointedTimeAccounts(Integer typeId, Integer branchId, String beforeDate, String afterDate,Integer start,Integer end);

    //门诊端 账单管理-财务管理
    List<Map<String,Object>> getFinancialAccounts(Integer branchId,Integer outPreId,Integer forwardStatus,Integer forwardType,String Month,Integer start,Integer end);

    //用户端， 根据商品id，查看所有已做过的项目单个信息
    Map<String,Object> getAllUserUsedProject(Integer id,Integer doctorId);

    //获取该用户使用经纪人优惠卷后最后一次就诊记录
    Accounts getUserLastOneOfProjectBy(Integer userId,Integer agentId);

    //获取用户使用过经纪人优惠卷的所有就诊记录
    List<Accounts> getUserAllProjectBy(Integer userId,Integer agentId);

    //用户付款后，生成就诊码
    Integer updateTreatmentCode(String id,String codePath);

    //更新订单状态
    Integer updateAccountsStatus(String id,Integer status);

    //用户查看自己所有已就诊的订单
    List<Accounts> getOneUserIsTreatment(Integer id);

    //确定就诊时间时更新 账单表的就诊状态
    Integer updateMedTime(String id,String medTime);

    //总后台 确定一个时间段进行打款时，更新账单表的打款状态
    Integer updateForwardStatusByOneOutPre(Integer outPreId,Integer status,Integer payType,String beforeDate,String afterDate);

    //门诊端 查询一个门诊所有预约的信息
    List<Map<String,Object>> getOneOutpreAllReadyOrder(Integer outPreId,String accId);
}
