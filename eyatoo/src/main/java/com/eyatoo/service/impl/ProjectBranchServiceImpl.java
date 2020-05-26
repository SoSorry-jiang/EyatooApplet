package com.eyatoo.service.impl;

import com.eyatoo.dao.ProjectBranchDao;
import com.eyatoo.pojo.ProjectBranch;
import com.eyatoo.service.ProjectBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectBranchServiceImpl implements ProjectBranchService {

    @Autowired
    private ProjectBranchDao projectBranchMapper;

    @Override
    public List<ProjectBranch> findFenDianList(Integer projectMedicalAdvertisementId) {
        List<ProjectBranch> plist = null;
        try {
           plist = projectBranchMapper.getFenDianList(projectMedicalAdvertisementId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return plist;
    }

    @Override
    public List<ProjectBranch> findFenDainAll(Integer outpatientPresentationsId) {
        List<ProjectBranch> list = null;
        try {
            list = projectBranchMapper.getFenDainAll(outpatientPresentationsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ProjectBranch> findvicinity(BigDecimal minlng, BigDecimal maxlng, BigDecimal minlat, BigDecimal maxlat) {
        return projectBranchMapper.getvicinity(minlng,maxlng,minlat,maxlat);
    }

    @Override
    public ProjectBranch getOneBranchById(Integer id) {
        ProjectBranch projectBranch = null;
        try {
            projectBranch = projectBranchMapper.getOneBranchById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return projectBranch;
    }

    @Override
    public List<ProjectBranch> getAllBranch() {
        List<ProjectBranch> projectBranches = new ArrayList<>();
        try {
            projectBranches = projectBranchMapper.getAllBranch();
        }catch (Exception e){
            e.printStackTrace();
        }
        return projectBranches;
    }

    @Override
    public int update(ProjectBranch projectBranch) {
        int num = 0;
        try {
            num = projectBranchMapper.update(projectBranch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public ProjectBranch findMZidBypmaId(Integer pmaId) {
        ProjectBranch pb = null;
        try {
            pb = projectBranchMapper.getMZidBypmaId(pmaId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pb;
    }
}
