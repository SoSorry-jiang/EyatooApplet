package com.eyatoo.controller;

import com.eyatoo.pojo.ProjectBranch;
import com.eyatoo.service.ProjectBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ProjectBranch")
public class ProjectBranchController {

    @Autowired
    private ProjectBranchService projectBranchService;

    //根据传入的projectMedicalAdvertisementId查询项目详情的分店信息
    @RequestMapping(value = "findFenDianList",method = RequestMethod.POST)
    public Map<String,Object> findFenDianList(Integer projectMedicalAdvertisementId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",projectBranchService.findFenDianList(projectMedicalAdvertisementId));
        return map;
    }

    /**
     * 首页门诊推荐模块根据传入的用户经纬度查询附近的门诊
     * radii:半径KM
     * lon:经度
     * lat:纬度
     */
    /*public Map<String,Object> findnearby(double radii, double lon, double lat){
        Map<String,Object> map = new HashMap<String,Object>();
        double r = 6371;//地球半径千米
        double dis = radii;
        double dlng = 2*Math.asin(Math.sin(dis/(2*r))/Math.cos(lat*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat = lat-dlat;
        double maxlat = lat+dlat;
        double minlng = lon -dlng;
        double maxlng = lon + dlng;
        map.put("list",projectBranchService.findvicinity(BigDecimal.valueOf(minlng), BigDecimal.valueOf(maxlng), BigDecimal.valueOf(minlat), BigDecimal.valueOf(maxlat)));
        return map;
    }*/
    @PostMapping("nearby")
    public Map<String,Object> findnearby(double lon, double lat){
        Map<String,Object> map = new HashMap<String,Object>();
        double r = 6371;//地球半径千米
        double dis = 25; //这里固定半径25km
        double dlng = 2*Math.asin(Math.sin(dis/(2*r))/Math.cos(lat*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat = lat - dlat;
        double maxlat = lat + dlat;
        double minlng = lon - dlng;
        double maxlng = lon + dlng;
        map.put("list",projectBranchService.findvicinity(BigDecimal.valueOf(minlng), BigDecimal.valueOf(maxlng), BigDecimal.valueOf(minlat), BigDecimal.valueOf(maxlat)));
        return map;
    }


    @PostMapping("findFenDianAll")
    public Map<String,Object> findFenDianAll(Integer optId){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("fendian",projectBranchService.findFenDainAll(optId));
        return map;
    }


    //跳转到上传文件的页面
    @RequestMapping(value = "/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String,Object> update(ProjectBranch projectBranch){
        Map<String,Object> map = new HashMap<>();
        projectBranch.setUpdateTime(new Date());
        if (projectBranchService.update(projectBranch)==1){
            map.put("result","ok");
        }else {
            map.put("result","no");
        }
        return map;
    }
}