package com.eyatoo.service.impl;

import com.eyatoo.dao.OutpatientPresentationsMapper;
import com.eyatoo.pojo.OutpatientPresentations;
import com.eyatoo.pojo.ProjectBranch;
import com.eyatoo.service.OutpatientPresentationsService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OutpatientPresentationsServiceImpl implements OutpatientPresentationsService {

    @Autowired
    private OutpatientPresentationsMapper outpatientPresentationsMapper;

    @Override
    public List<OutpatientPresentations> findmenzhen(String outpatientName,
                                                     Integer chainId,
                                                     Integer hospitalTypeId) {
        List<OutpatientPresentations> optList = null;
        try {
            optList = outpatientPresentationsMapper.getmenzhen(outpatientName,chainId,hospitalTypeId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return optList;
    }

    @Override
    public List<OutpatientPresentations> findmenzhenbytj(String outpatientName, Integer chainId, Integer hospitalTypeId,
                                                     Integer distanceId, BigDecimal minlng, BigDecimal maxlng,
                                                     BigDecimal minlat, BigDecimal maxlat) {
        List<OutpatientPresentations> list = null;
        try {
            list = outpatientPresentationsMapper.getmenzhenbytj(outpatientName,chainId,hospitalTypeId,distanceId,
                                                                  minlng,  maxlng,  minlat,  maxlat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public XSSFWorkbook show(String outpatientName,
                             Integer chainId,
                             Integer hospitalTypeId) {
        List<OutpatientPresentations> list = outpatientPresentationsMapper.getmenzhen(outpatientName,chainId,hospitalTypeId);//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("OutpatientPresentations");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("序号");//第一列
        titleRow.createCell(1).setCellValue("门诊编号");
        titleRow.createCell(2).setCellValue("门诊名称");
        titleRow.createCell(3).setCellValue("门诊图标");
        titleRow.createCell(4).setCellValue("筛选表是否连锁id");
        int cell = 1;
        for (OutpatientPresentations opt : list) {
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(opt.getId());//将数据库的数据遍历出来
            row.createCell(2).setCellValue(opt.getOutpatientName());
            row.createCell(3).setCellValue(opt.getIcon());
            row.createCell(4).setCellValue(opt.getChainId());
            cell++;
        }
        return wb;
    }


    @Override
    public String getIconPathByBranch(Integer branchId) {
        String path = null;
        try{
            path = outpatientPresentationsMapper.getIconPathByBranch(branchId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public int add(OutpatientPresentations outpatientPresentations) {
        int num = 0;
        try {
            num = outpatientPresentationsMapper.add(outpatientPresentations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int zizhiupdate(OutpatientPresentations OutpatientPresentations) {
        int num = 0;
        try {
            num = outpatientPresentationsMapper.zizhiupdate(OutpatientPresentations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public OutpatientPresentations findmzbyoptId(Integer optId) {
        OutpatientPresentations opt = null;
        try {
            opt = outpatientPresentationsMapper.getmzbyoptId(optId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opt;
    }
}