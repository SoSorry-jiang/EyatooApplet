package com.eyatoo.controller;


import com.eyatoo.pojo.DoctorWorkDate;
import com.eyatoo.pojo.ProjectBranch;
import com.eyatoo.service.DoctorBranchService;
import com.eyatoo.service.DoctorWorkDateService;
import com.eyatoo.service.OutpatientPresentationsService;
import com.eyatoo.service.ProjectBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class DoctorWorkDateController {
    @Autowired
    private DoctorWorkDateService doctorWorkDateService;

    @Autowired
    private DoctorBranchService doctorBranchService;

    @Autowired
    private ProjectBranchService projectBranchService;

    @Autowired
    private OutpatientPresentationsService outpatientPresentationsService;

    //医生选择日期确定就诊时更新数据库信息
    @RequestMapping("addDoctorWorkDate")
    private Integer addDoctorWorkDate(DoctorWorkDate doctorWorkDate){
        Integer isOK = 0;
        Integer isHave = doctorWorkDateService.getCountByDoctorWorkDate(doctorWorkDate.getDoctorId());
        if(isHave != 0){
            doctorBranchService.updateDoctorWorkPlace(doctorWorkDate.getBranchId(),doctorWorkDate.getDoctorId(),1);
            isOK = doctorWorkDateService.updateDoctorWorkDate(doctorWorkDate);
        }else {
            doctorBranchService.updateDoctorWorkPlace(doctorWorkDate.getBranchId(),doctorWorkDate.getDoctorId(),1);
            isOK = doctorWorkDateService.addDoctorWorkDate(doctorWorkDate);
        }

        return isOK;
    }


    //获取一个医生的所有坐诊地址
    @RequestMapping("loginOneDoctorBranch")
    private List<Map<String,Object>> loginOneDoctorBranch(Integer doctorId){
        //获取医生坐诊医院
        List<Integer>  idList =  doctorBranchService.getBranchByDoctor(doctorId);
        List<Map<String,Object>> branchList = new ArrayList();
        if(idList.size() == 0){
            Map<String,Object> branchMap = new HashMap<>();
            branchList.add(branchMap);
            branchMap.put("msg","haveNoBranch");
            return branchList;
        }

        for (Integer branchId:idList) {
            ProjectBranch projectBranch = projectBranchService.getOneBranchById(branchId);
            Map<String,Object> branchMap = new HashMap<>();
            branchMap.put("branchId",projectBranch.getId());
            branchMap.put("projectBranchIcon",outpatientPresentationsService.getIconPathByBranch(projectBranch.getId()));
            branchMap.put("storename",projectBranch.getStoreName());
            branchMap.put("msg","success");
            branchList.add(branchMap);
        }

        return branchList;
    }

    //查询医生就诊时间信息
    @RequestMapping("getDoctorWorkDate")
    private Map<String,Object> getDoctorWorkDate(Integer doctorId) throws Exception{
//          long nd = 1000 * 24 * 60 * 60;//每天毫秒数

          Map<String,Object> doctorWorkDateMap = new HashMap<>();
          DoctorWorkDate doctorWorkDate = doctorWorkDateService.getDefaultDoctorWorkDate(doctorId);
          if(doctorWorkDate != null) {
              Date date = new Date();
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              //判断结束就诊日期是否已经超过 超过就删除
              if(doctorWorkDateService.yzIsWorkDate(doctorWorkDate.getDoctorId(),simpleDateFormat.format(date)) != 0){
                  doctorWorkDateService.delDoctorWorkDate(doctorWorkDate.getId());
                  doctorWorkDateMap.put("branchName","暂无");
                  doctorWorkDateMap.put("workStartTime","暂无");
                  doctorWorkDateMap.put("workEndTime","暂无");
              }else {
                  ProjectBranch projectBranch = projectBranchService.getOneBranchById(doctorWorkDate.getBranchId());
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date startDate =simpleDateFormat.parse(doctorWorkDate.getStartTime());
//            Date endDate = simpleDateFormat.parse(doctorWorkDate.getEndTime());
//            long diff = endDate.getTime() - startDate.getTime();
//            long day = diff / nd;   // 计算差多少天
                  doctorWorkDateMap.put("branchName", projectBranch.getStoreName());
                  doctorWorkDateMap.put("workStartTime", doctorWorkDate.getStartTime());
                  doctorWorkDateMap.put("workEndTime", doctorWorkDate.getEndTime());
//          doctorWorkDateMap.put("workHowDay",day);
              }
          }else {
              //没有数据
              doctorWorkDateMap.put("branchName","暂无");
              doctorWorkDateMap.put("workStartTime","暂无");
              doctorWorkDateMap.put("workEndTime","暂无");
          }
          return  doctorWorkDateMap;
    }

}
