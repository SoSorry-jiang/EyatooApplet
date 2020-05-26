package com.eyatoo.controller;

import com.eyatoo.pojo.DoctorProject;
import com.eyatoo.service.DoctorProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorProjectController {

    @Autowired
    private DoctorProjectService doctorProjectService;


    //当门诊端 添加医生做的项目信息
    @RequestMapping("addDoctorDoProject")
    private Integer addDoctorDoProject(DoctorProject[] doctorProjects){
        Integer isOK = 0;
        if(doctorProjects.length != 0){
            for (DoctorProject doctorProject:doctorProjects) {
                isOK = doctorProjectService.addProjectDoctor(doctorProject);
            }
        }
        return  isOK;
    }


}
