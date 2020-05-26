package com.eyatoo.controller;

import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ProjectMedicalAdvertisement")
public class ProjectMedicalAdvertisementController {

    @Autowired
    private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

    @Autowired
    private DoctorProjectService doctorProjectService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private ProjectBranchService projectBranchService;

    /**
     * medicalTreatmentProjectName:首页选择的诊疗项目名称
     * * city:城市
     * * priceSort:价格，传入值为priceUp(上)或者priceDwon(下)
     * * salesVolumeSort:销售量,传入值为salesVolumeUp(上)或者salesVolumeDown(下)
     * * outpatientTypeId:门诊类型表id
     * * technologyTypeId:技术类型表id
     * * otherTypeId:其他类型id
     * * MinimumPrice:最低价
     * * TopPrice:最高价
     */
    @RequestMapping(value = "findList", method = RequestMethod.POST)
    public Map<String, Object> findList(String medicalTreatmentProjectName,
                                        String city, String priceSort, String salesVolumeSort,
                                        Integer outpatientTypeId, Integer technologyTypeId, Integer otherTypeId,
                                        Integer MinimumPrice, Integer TopPrice) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", projectMedicalAdvertisementService.findList(medicalTreatmentProjectName, city, priceSort,
                salesVolumeSort, outpatientTypeId, technologyTypeId, otherTypeId, MinimumPrice, TopPrice));
        return map;
    }

    // 点击单个项目进去后显示可以做该项目的医生列表和价格
    @RequestMapping(value = "findProDoctor")
    public Map<String, Object> findList(Integer id) {
        //foreach（doctor doctor：doctorsList）{
        //      var doctorMsg = doctor；
        //      foreach（project pro：doctorProjects）{
        //      if(doctor.id == pro.doctorId){
        //               doctorMsg = doctorMsg+pro.getmoney();
        //             }
        //       }
        //       foreach（branch branch：projectBranches）{
        //          if（doctor.hospitalId == branch.getId）{
        //             doctorMsg = doctorMsg+branch.getName();
        //            }
        //       }
        // }
        Map<String, Object> map = new HashMap<>();
        List<DoctorProject> doctorProjects = doctorProjectService.selectDoctorFormProById(id);
        List<Integer> doctorIdList = new ArrayList<>();
        for (DoctorProject doctorProject : doctorProjects) {
            doctorIdList.add(doctorProject.getDoctorId());
        }
        List<Doctor> doctors = new ArrayList<>();
        if (doctorIdList.size() != 0) {
            for (Integer doctorId : doctorIdList) {
                Doctor doctor = doctorService.getOneOfDoctorById(doctorId);
                doctors.add(doctor);
            }
        }
        List<ProjectBranch> projectBranches = projectBranchService.getAllBranch();

        map.put("projectBranches", projectBranches);
        map.put("doctorProjects", doctorProjects);
        map.put("doctorsList", doctors);
        return map;
    }

    //用户添加日志时，加载该用户所购买过的项目
    @RequestMapping("/loginUserProject")
    public Map<String, Object> getUserBuyProject(Integer userId) {
        Map<String, Object> paramterMap = new HashMap<>();
        List<Accounts> prodList = accountsService.getOneUserAccounts(userId);
        List<Doctor> doctors = new ArrayList<>();
        List<ProjectMedicalAdvertisement> projectMedicalAdvertisements = new ArrayList<>();
        for (Accounts accounts : prodList) {
            ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(accounts.getProjectId());
            Doctor doctor = doctorService.getOneOfDoctorById(accounts.getDoctorId());
            projectMedicalAdvertisements.add(projectMedicalAdvertisement);
            doctors.add(doctor);
        }
        paramterMap.put("prodList", prodList);
        paramterMap.put("projectMedicalAdvertisements", projectMedicalAdvertisements);
        paramterMap.put("doctors", doctors);
        //先循环 proList 然后拿出其中一列的 projectId 和 projectMedicalAdvertisements
        //中的 id 对比， 同时 拿 prolist 的 doctorId 和 projectMedicalAdvertisements 对比
        return paramterMap;
    }

}