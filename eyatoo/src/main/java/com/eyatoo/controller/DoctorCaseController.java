package com.eyatoo.controller;

import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.Doctor;
import com.eyatoo.pojo.DoctorCase;
import com.eyatoo.service.DoctorCaseService;
import com.eyatoo.service.DoctorCommunityCommentService;
import com.eyatoo.service.DoctorService;
import com.eyatoo.utils.Random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class DoctorCaseController {
    @Autowired
    private DoctorCaseService doctorCaseService;

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private DoctorCommunityCommentService doctorCommunityCommentService;

    @Autowired
    private DoctorCaseService doctorAnswer;

    @Autowired
    private DoctorService doctorService;

    //医生发布案例
    @RequestMapping("/addDoctorCase")
    private Integer addDoctorCase(@RequestParam(defaultValue = "0") String upId, DoctorCase doctorCase, MultipartFile[] path) throws IOException {
        String b = null;
            for (MultipartFile path1: path) {
            if (!path1.isEmpty()) {
                //使用随机数将上传的文件名称修改
                String FileName = RandomUtil.number() + ".jpg";
                //上传的保存路径
                String filePath = ftpConfig.getServerPath() + "DoctorCase";
                //封装上传文件位置的全路径
                File targetFile = new File(filePath, FileName);
                //把本地文件上传到封装上传文件位置的全路径
                path1.transferTo(targetFile);
                b = ftpConfig.getImageBaseUrl() + "DoctorCase/" + FileName;
            }
        }
        Doctor doctor = doctorService.getOneOfDoctorById(doctorCase.getDoctorId());
        doctorCase.setDoctorAvator(doctor.getDoctorAvator());
        doctorCase.setDoctorName(doctor.getDoctorName());
        if(!upId.equals("0")){  //说明是修改 通过upId获取数据库数据，把此次上传的图片设置设置到after 在执行更新
             doctorCaseService.updatePhoto(Integer.parseInt(upId),b);
            return Integer.parseInt(upId);
        }else{
            //说明是新增设置before值返回新增数据的id
            doctorCase.setBeforePhoto(b);
        }
        doctorCaseService.addDoctorCase(doctorCase);
        return  doctorCase.getId();
    }

    //医生删除自己的案例
    @RequestMapping("/deleteDoctorCase")
    private Integer deleteDoctorCase(Integer id){
        Integer isTrue = doctorCommunityCommentService.deleteOneCommunityAllComment(id);
        if (isTrue != 0){
            Integer isOk = doctorCaseService.deleteDoctorCase(id);
            System.out.println("成功");
            return isOk;
        }
           return 0;
    }


    //获得一个医生所有的案例
    @RequestMapping("/getOneDoctorAllCase")
    private List<DoctorCase> getOneDoctorCase(Integer doctorId){
        List<DoctorCase> doctorCases= doctorCaseService.getOneDoctorAllCase(doctorId);
        return doctorCases;
    }

    //点击单个案例进去
    @RequestMapping("/getOneDoctorCase")
    private DoctorCase getOneCase(Integer id){
        DoctorCase doctorCase = doctorCaseService.getOneCase(id);
        return doctorCase;
    }

}
