package com.eyatoo.service.impl;

import com.eyatoo.dao.UserAddressDao;
import com.eyatoo.pojo.UserAddress;
import com.eyatoo.service.UserAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Resource
    private UserAddressDao userAdressDao;

    @Override
    public Integer userAddAddress(UserAddress userAddress) {
        Integer isOK = 0;
        try {
            isOK = userAdressDao.userAddAddress(userAddress);
        }catch (Exception e){

        }
        return isOK;
    }

    @Override
    public Integer userUpdateAddress(UserAddress userAddress) {
        Integer isOK = 0;
        try {
            isOK = userAdressDao.userUpdateAddress(userAddress);
        }catch (Exception e){

        }
        return isOK;
    }

    @Override
    public List<UserAddress> getOneUserAddresses(Integer userId) {
        List<UserAddress> userAddressList = new ArrayList<>();
        try {
             userAddressList = userAdressDao.getOneUserAddresses(userId);
        }catch (Exception e){
              e.printStackTrace();
        }
        return userAddressList;
    }

    @Override
    public Integer userDelAddress(Integer id) {

        Integer isOK = 0;
        try {
            isOK = userAdressDao.userDelAddress(id);
        }catch (Exception e){

        }
        return isOK;
    }

    @Override
    public List<UserAddress> getOneDoctorAddresses(Integer doctorId) {
        List<UserAddress> userAddresses = new ArrayList<>();
        try {
            userAddresses = userAdressDao.getOneDoctorAddresses(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userAddresses;
    }

    @Override
    public UserAddress getOneAddress(Integer id) {
        UserAddress userAddress = new UserAddress();
        try {
          userAddress = userAdressDao.getOneAddress(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userAddress;
    }
}
