package com.eyatoo.service.impl;

import com.eyatoo.dao.ProjectMedicalAdvertisementDao;
import com.eyatoo.pojo.ProjectMedicalAdvertisement;
import com.eyatoo.service.ProjectMedicalAdvertisementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//商品详情
@Service
public class ProjectMedicalAdvertisementServiceImpl implements ProjectMedicalAdvertisementService {

    @Resource
    private ProjectMedicalAdvertisementDao projectMedicalAdvertisementDao;


    @Override
    public List<ProjectMedicalAdvertisement> findList(String medicalTreatmentProjectName,
                                                      String city,
                                                      String priceSort,String salesVolumeSort,
                                                      Integer outpatientTypeId,
                                                      Integer technologyTypeId,Integer otherTypeId,
                                                      Integer MinimumPrice,Integer TopPrice)
    {
        List<ProjectMedicalAdvertisement> pList = null;
        try {
            pList = projectMedicalAdvertisementDao.getList(medicalTreatmentProjectName,city,priceSort,salesVolumeSort,outpatientTypeId,technologyTypeId,otherTypeId,MinimumPrice,TopPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pList;
    }

    @Override
    public ProjectMedicalAdvertisement findJiBen(int id) {
        ProjectMedicalAdvertisement pr = null;
        try {
            pr = projectMedicalAdvertisementDao.getJiBen(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pr;
    }

    @Override
    public List<ProjectMedicalAdvertisement> findZLXiangMu(int id) {
        List<ProjectMedicalAdvertisement> pr = null;
        try {
            pr = projectMedicalAdvertisementDao.getZLXiangMu(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    @Override
    public List<Integer> getAllProjectId() {
        List<Integer> projectIdList = null;
        try {
            projectIdList = projectMedicalAdvertisementDao.getAllProjectId();
        }catch (Exception e){
            e.printStackTrace();
        }
        return projectIdList;
    }

    @Override
    public List<ProjectMedicalAdvertisement> findUserCollectedMsgById(Integer id) {
        List<ProjectMedicalAdvertisement> smaList = null;
        try {
            smaList = projectMedicalAdvertisementDao.findUserCollectedMsgById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return smaList;
    }
}