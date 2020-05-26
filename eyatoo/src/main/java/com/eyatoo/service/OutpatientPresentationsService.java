package com.eyatoo.service;

import com.eyatoo.pojo.OutpatientPresentations;
import com.eyatoo.pojo.ProjectBranch;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigDecimal;
import java.util.List;

public interface OutpatientPresentationsService {

    //查询所有门诊
    List<OutpatientPresentations> findmenzhen(String outpatientName,
                                              Integer chainId,
                                              Integer hospitalTypeId);

    //根据条件查询门诊
    List<OutpatientPresentations> findmenzhenbytj(String outpatientName,
                                                  Integer chainId,
                                                  Integer hospitalTypeId,
                                                  Integer distanceId,
                                                  BigDecimal minlng,
                                                  BigDecimal maxlng,
                                                  BigDecimal minlat,
                                                  BigDecimal maxlat);

    //测试导出数据为exel格式
    public XSSFWorkbook show(String outpatientName,
                             Integer chainId,
                             Integer hospitalTypeId);

    //根据门诊id查找小程序图标
    String getIconPathByBranch(Integer branchId);

    //添加门诊
    int add(OutpatientPresentations outpatientPresentations);

    //门诊修改(配合pc门诊端页面注册时候的资质图片添加)
    int zizhiupdate(OutpatientPresentations OutpatientPresentations);

    //根据门诊id查询门诊信息
    OutpatientPresentations findmzbyoptId(Integer optId);
}