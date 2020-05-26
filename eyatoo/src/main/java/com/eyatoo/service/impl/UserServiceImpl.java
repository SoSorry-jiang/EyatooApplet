package com.eyatoo.service.impl;

import com.eyatoo.dao.UserDao;
import com.eyatoo.pojo.User;
import com.eyatoo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao udao;

    @Override
    public User findById(Integer id) {
        return udao.getOneMsgById(id);
    }


    @Override
    public Integer updateUserAvatorOrName(String filePath, String name, BigInteger phone, Integer id) {
          Integer isOK = 0;
        try {
            isOK = udao.updateUserAvatorOrName(filePath,name,phone,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateUserDefaultAddress(Integer addressId, Integer userId) {
        Integer isOK = 0;
        try {
            isOK = udao.updateUserDefaultAddress(addressId,userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer addUser(User user) {
        Integer isOk =0;
        try {
            isOk = udao.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateQRcode(Integer userId, String path) {
        Integer isOk = 0;
        try {
              isOk = udao.updateQRcode(userId, path);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateTrueMsg(Integer userId, String trueName, String idCard) {
        Integer isOK = 0;
        try{
           isOK = udao.updateTrueMsg(userId, trueName, idCard);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<User> fingAllAgentByName(String name) {
        List<User> users = new ArrayList<>();
        try {
            users = udao.findAllAgentByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User isHaveUser(String openId) {
        User isHave = new User();
        try {
            isHave = udao.isHaveUser(openId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isHave;
    }

    @Override
    public void updateOtherTablesMsg(String userAvator, String userName, Integer id) {
        try {
           udao.updateOtherTablesMsg(userAvator, userName, id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer updateAgentStatus(Integer id, Integer status) {
        Integer isOK = 0;
        try {
            isOK = udao.updateAgentStatus(id,status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateAgentLevel(Integer userId,Integer level) {
        Integer isOK = 0;
        try {
              isOK = udao.updateAgentLevel(userId,level);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateUserIsReceiveNewPackage(Integer userId) {
        Integer isOK = 0;
        try {
            isOK = udao.updateUserIsReceiveNewPackage(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }
}
