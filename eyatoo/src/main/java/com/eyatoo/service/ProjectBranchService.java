package com.eyatoo.service;

import com.eyatoo.pojo.ProjectBranch;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProjectBranchService {

    //根据传入的projectMedicalAdvertisementId查询项目详情的分店信息
    List<ProjectBranch> findFenDianList(Integer projectMedicalAdvertisementId);

    //根据传入的projectMedicalAdvertisementId查询项目详情关联门诊下的所有分店信息(在门诊介绍里的门诊简介显示)
    List<ProjectBranch> findFenDainAll(Integer outpatientPresentationsId);


    //首页门诊推荐模块根据传入的用户经纬度查询附近的门诊
    List<ProjectBranch> findvicinity(BigDecimal minlng,
                                     BigDecimal maxlng,
                                     BigDecimal minlat,
                                     BigDecimal maxlat);

    //根据id获得门诊基本信息
    ProjectBranch getOneBranchById(Integer id);

    //获取所有坐诊门诊
    List<ProjectBranch> getAllBranch();

    //根据分店id,修改分店信息
    int update(ProjectBranch projectBranch);

    //根据传入的项目id查询到门诊id(不是分店!)
    ProjectBranch findMZidBypmaId(Integer pmaId);
}
