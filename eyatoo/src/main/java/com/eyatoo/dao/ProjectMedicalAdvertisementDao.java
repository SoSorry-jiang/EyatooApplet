package com.eyatoo.dao;

import com.eyatoo.pojo.ProjectMedicalAdvertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProjectMedicalAdvertisementDao {

    //根据条件查询诊疗项目信息
    List<ProjectMedicalAdvertisement> getList(@Param("medicalTreatmentProjectName") String medicalTreatmentProjectName,
                                              @Param("city") String city,
                                              @Param("priceSort") String priceSort,
                                              @Param("salesVolumeSort") String salesVolumeSort,
                                              @Param("outpatientTypeId") Integer outpatientTypeId,
                                              @Param("technologyTypeId") Integer technologyTypeId,
                                              @Param("otherTypeId") Integer otherTypeId,
                                              @Param("MinimumPrice") Integer MinimumPrice,
                                              @Param("TopPrice") Integer TopPrice);

    //根据在诊疗项目界面点击项目时获取的id查询项目详情的基本信息
    ProjectMedicalAdvertisement getJiBen(@Param("id") int id);

    //根据诊疗项目id查询到门诊介绍里面的诊疗项目
    List<ProjectMedicalAdvertisement> getZLXiangMu(@Param("ProjectMedicalAdvertisementId") int id);

    //获得所有商品id
    List<Integer> getAllProjectId();

    //根据用户id 查询该用户收藏的诊疗项目的详细信息
    List<ProjectMedicalAdvertisement> findUserCollectedMsgById(@Param("id") Integer id);
}