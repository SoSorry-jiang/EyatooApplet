package com.eyatoo.dao;

import com.eyatoo.pojo.ProjectBranch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProjectBranchDao {

    //根据传入的projectMedicalAdvertisementId查询关联项目详情的分店信息
    List<ProjectBranch> getFenDianList(@Param("projectMedicalAdvertisementId") Integer projectMedicalAdvertisementId);

    //根据传入的outpatientPresentationsId(门诊id)查询项目详情关联门诊下的所有分店信息(在门诊介绍里的门诊简介显示)
    List<ProjectBranch> getFenDainAll(@Param("outpatientPresentationsId") Integer outpatientPresentationsId);

    //首页门诊推荐模块根据传入的用户经纬度查询附近的门诊
    List<ProjectBranch> getvicinity(@Param("minlng") BigDecimal minlng,
                                    @Param("maxlng") BigDecimal maxlng,
                                    @Param("minlat") BigDecimal minlat,
                                    @Param("maxlat") BigDecimal maxlat);

    //根据id获得门诊基本信息
    ProjectBranch getOneBranchById(@Param("id") Integer id);

    //获取所有坐诊门诊
    List<ProjectBranch> getAllBranch();

    //根据分店id,修改分店信息
    int update(ProjectBranch projectBranch);

    //根据传入的项目id查询到门诊id(不是分店!)
    ProjectBranch getMZidBypmaId(@Param("projectMedicalAdvertisementId") Integer pmaId);
}