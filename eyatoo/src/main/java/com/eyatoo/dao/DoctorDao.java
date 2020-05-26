package com.eyatoo.dao;

import com.eyatoo.pojo.Doctor;
import com.eyatoo.pojo.DoctorSpecialize;
import com.eyatoo.pojo.DoctorTag;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface DoctorDao {
    //添加医生
    Integer addDoctor(Doctor doctor);
    //添加医生专业
    Integer addDoctorMajor(DoctorSpecialize doctorSpecialize);
    //根据条件模糊查询，如果没有条件就查询全部
    List<Doctor>  getAllDoctor(@Param("idList") List<Integer> idList, @Param("name") String name, String education, String orderBy,
                               @Param("distanceId") Integer distanceId,
                               @Param("minlng") BigDecimal minlng,
                               @Param("maxlng") BigDecimal maxlng,
                               @Param("minlat") BigDecimal minlat,
                               @Param("maxlat") BigDecimal maxlat);
    //根据医生id获取当前医生的所有信息
    Doctor getOneOfDoctorById(@Param("id") Integer id);
    //获取单个医生所有的标签
    List<DoctorTag> getOneOfDoctorTag(@Param("id") Integer id);
    //获取所有标签
    List<DoctorTag> getAllTag();
    //医生修改他的默认地址
    Integer updateDoctorDefaultAddress(Integer addressId, Integer doctorId);
    //查询门诊或分店下的所有所属医生
    List<Doctor> getOutpatientPresentationsDoctor(Integer outPreId,Integer branchId);
    //门诊端  查找所有医生
    List<Doctor> branchGetAllDoctor(String education, Integer beforeWorkYear, Integer lastWorkDate, String major, Integer sex, String orderBy);
    //查找所有是经纪人的医生
    List<Doctor> findAllAgentDoctor(String name);
    //根据项目id 查找做该项目的医生的详情信息
    List<Map<String,Object>> findOneProjectDoctor(Integer articlesId);

    //更新医生·其他项总数·
    Integer updateCaseCount(Integer id);
    Integer updateCommunityCount(Integer id);
    Integer updateAnswerCount(Integer id);
    Integer updateFans_Count(Integer id);
    Integer updateOrderCount(Integer id);

    //修改用户头像和名称
    Integer updateDoctorAvatorOrName(String filePath, String name, BigInteger phone, Integer id);

    //当医生更新头像或名字时，更新其他表绑定的头像
    void updateDoctorOtherTablesMsg(String userAvator, String userName, Integer id);

    //验证医生电话号码是否已经注册过
    Doctor yzDoctorPhone(String phone);

    //医生登录
    Integer DoctorLogin(String phone,String password);

    //忘记密码
    Integer DoctorUpdatePsd(String phone,String password);

    //更新医生经纪人状态
    Integer updateAgentStatus(Integer doctorId,Integer status);

    //更新医生真实信息
    Integer updateTrueMsg(Integer doctorId,String trueName,String idCard);

    //更新经纪人等级 在医生进行升级经纪人等级的时候
    Integer updateDoctorAgentLevel(Integer doctorId,Integer level);

    //查询医生所有坐诊的门诊
    List<Map<String,Object>> getOneDoctorAllOutPre(Integer doctorId);
}
