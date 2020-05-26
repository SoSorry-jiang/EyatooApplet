package com.eyatoo.dao;

import com.eyatoo.pojo.OutpatientPresentations;
import com.eyatoo.pojo.ProjectBranch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OutpatientPresentationsMapper {

    //根据条件查询门诊(前端已调用,要求只是查询全部但不完整,后期可以更改)
    List<OutpatientPresentations> getmenzhen(@Param("outpatientName") String outpatientName,
                                             @Param("chainId") Integer chainId,
                                             @Param("hospitalTypeId") Integer hospitalTypeId);

    //根据条件查询门诊
    List<OutpatientPresentations> getmenzhenbytj(@Param("outpatientName") String outpatientName,
                                                 @Param("chainId") Integer chainId,
                                                 @Param("hospitalTypeId") Integer hospitalTypeId,
                                                 @Param("distanceId") Integer distanceId,
                                                 @Param("minlng") BigDecimal minlng,
                                                 @Param("maxlng") BigDecimal maxlng,
                                                 @Param("minlat") BigDecimal minlat,
                                                 @Param("maxlat") BigDecimal maxlat);

    //根据门诊id查找小程序图标
    String getIconPathByBranch(Integer branchId);

    //添加门诊
    int add(OutpatientPresentations outpatientPresentations);

    //门诊修改(配合pc门诊端页面注册时候的资质图片添加)
    int zizhiupdate(OutpatientPresentations OutpatientPresentations);

    //根据门诊id查询门诊信息
    OutpatientPresentations getmzbyoptId(Integer optId);
}