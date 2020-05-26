package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorDao;
import com.eyatoo.pojo.Doctor;
import com.eyatoo.pojo.DoctorSpecialize;
import com.eyatoo.pojo.DoctorTag;
import com.eyatoo.service.DoctorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.print.Doc;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorDao doctorDao;

    @Override
    public Integer addDoctor(Doctor doctor) {
        Integer isOk = 0;
        try {
            isOk = doctorDao.addDoctor(doctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public List<Doctor> getAllDoctor(List<Integer> idList, String name, String education, String orderBy,Integer distanceId,  BigDecimal minlng, BigDecimal maxlng, BigDecimal minlat, BigDecimal maxlat) {
        List<Doctor> doctors = new ArrayList<>();
        try {
             doctors = doctorDao.getAllDoctor(idList, name, education, orderBy,distanceId,minlng, maxlng, minlat, maxlat);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public Doctor getOneOfDoctorById(Integer id){
        Doctor doctor = null;
        try {
            doctor = doctorDao.getOneOfDoctorById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public List<DoctorTag> getOneOfDoctorTag(Integer id) {
        List<DoctorTag> doctorTagList =null;
        try {
              doctorTagList = doctorDao.getOneOfDoctorTag(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorTagList;
    }

    @Override
    public List<DoctorTag> getAllDoctorTag() {
        List<DoctorTag> doctorTagList = null;
        try {
            doctorTagList = doctorDao.getAllTag();
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorTagList;
    }

    @Override
    public Integer updateDoctorDefaultAddress(Integer addressId, Integer doctorId) {
        Integer isOk = 0;
        try {
           isOk = doctorDao.updateDoctorDefaultAddress(addressId,doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public List<Doctor> getOutpatientPresentationsDoctor(Integer outPreId,Integer branchId) {
        List<Doctor> doctorList = null;
        try {
            doctorList = doctorDao.getOutpatientPresentationsDoctor(outPreId,branchId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorList;
    }


    @Override
    public List<Doctor> branchGetAllDoctor(String education, Integer beforeWorkYear, Integer lastWorkDate, String major, Integer sex, String orderBy) {
        List<Doctor> doctors = null;
        try {
               doctors = doctorDao.branchGetAllDoctor(education, beforeWorkYear, lastWorkDate, major, sex, orderBy);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Doctor> findAllAgentDoctor(String name) {
        List<Doctor> doctors = null;
        try {
            doctors = doctorDao.findAllAgentDoctor(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Map<String, Object>> findOneProjectDoctor(Integer articlesId) {
        List<Map<String,Object>> parList = null;
        try {
               parList = doctorDao.findOneProjectDoctor(articlesId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return parList;
    }

    @Override
    public Integer updateCaseCount(Integer id) {
        Integer isOK= 0;
        try {
             isOK = doctorDao.updateCaseCount(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateCommunityCount(Integer id) {
        Integer isOK= 0;
        try {
            isOK = doctorDao.updateCommunityCount(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateAnswerCount(Integer id) {
        Integer isOK= 0;
        try {
            isOK = doctorDao.updateAnswerCount(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateFans_Count(Integer id) {
        Integer isOK= 0;
        try {
            isOK = doctorDao.updateFans_Count(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateOrderCount(Integer id) {
        Integer isOK= 0;
        try {
            isOK = doctorDao.updateOrderCount(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateDoctorAvatorOrName(String filePath, String name, BigInteger phone, Integer id) {
        Integer isOK = 0;
        try {
           isOK = doctorDao.updateDoctorAvatorOrName(filePath, name, phone, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public void updateDoctorOtherTablesMsg(String userAvator, String userName, Integer id) {
        try {
            doctorDao.updateDoctorOtherTablesMsg(userAvator, userName, id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer addDoctorMajor(DoctorSpecialize doctorSpecialize) {
        Integer isOk = 0;
        try {
          isOk = doctorDao.addDoctorMajor(doctorSpecialize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Doctor yzDoctorPhone(String phone) {
        Doctor isOK = null;
        try {
           isOK = doctorDao.yzDoctorPhone(phone);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer DoctorLogin(String phone, String password) {
        Integer isOK = 0;
        try {
            isOK = doctorDao.DoctorLogin(phone, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer DoctorUpdatePsd(String phone,String password) {
        Integer isOK = 0;
        try {
             isOK = doctorDao.DoctorUpdatePsd(phone,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateAgentStatus(Integer doctorId, Integer status) {
        Integer isOK = 0;
        try {
            isOK = doctorDao.updateAgentStatus(doctorId, status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateTrueMsg(Integer doctorId, String trueName, String idCard) {
        Integer isOK = 0;
        try {
            isOK = doctorDao.updateTrueMsg(doctorId,trueName,idCard);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateDoctorAgentLevel(Integer doctorId, Integer level) {
        Integer isOK = 0;
        try {
           isOK = doctorDao.updateDoctorAgentLevel(doctorId, level);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<Map<String, Object>> getOneDoctorAllOutPre(Integer doctorId) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        try {
            mapList = doctorDao.getOneDoctorAllOutPre(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapList;
    }
}
