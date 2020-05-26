package com.eyatoo.service.impl;

import com.eyatoo.dao.RemittanceDao;
import com.eyatoo.pojo.Remittance;
import com.eyatoo.service.RemittanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RemittanceServiceImpl implements RemittanceService {

    @Resource
    private RemittanceDao remittanceDao;

    @Override
    public Integer addRemittance(Remittance remittance) {
        Integer isOK = 0;
        try {
            isOK = remittanceDao.addRemittance(remittance);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<Remittance> getAllRemittance(Integer branchId, Integer outPreId, String remittanceTime) {
        List<Remittance> remittances = new ArrayList<>();
        try {
              remittances = remittanceDao.getAllRemittance(branchId, outPreId, remittanceTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return remittances;
    }
}
