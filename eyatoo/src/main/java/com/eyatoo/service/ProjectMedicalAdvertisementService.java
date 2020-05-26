package com.eyatoo.service;

import com.eyatoo.pojo.ProjectMedicalAdvertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMedicalAdvertisementService {

    List<ProjectMedicalAdvertisement> findList(String medicalTreatmentProjectName,
                                               String city,
                                               String priceSort,
                                               String salesVolumeSort,
                                               Integer outpatientTypeId,
                                               Integer technologyTypeId,
                                               Integer otherTypeId,
                                               Integer MinimumPrice,
                                               Integer TopPrice);

    ProjectMedicalAdvertisement findJiBen(int id);

    List<ProjectMedicalAdvertisement> findZLXiangMu(int id);

    //获得所有商品id
    List<Integer> getAllProjectId();

    //根据用户id 查询该用户收藏的诊疗项目的详细信息
    List<ProjectMedicalAdvertisement> findUserCollectedMsgById(@Param("id") Integer id);

}
