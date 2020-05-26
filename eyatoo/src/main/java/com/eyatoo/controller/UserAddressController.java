package com.eyatoo.controller;

import com.eyatoo.pojo.Doctor;
import com.eyatoo.pojo.User;
import com.eyatoo.pojo.UserAddress;
import com.eyatoo.service.DoctorService;
import com.eyatoo.service.UserAddressService;
import com.eyatoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserAddressController {
   @Autowired
    private UserAddressService userAddressService;

   @Autowired
   private UserService userService;

   @Autowired
   private DoctorService doctorService;

   //当用户或医生添加地址的时候
   @RequestMapping("/userAddAddress")
    public Integer userAddAddress(UserAddress userAddress){

       return userAddressService.userAddAddress(userAddress);
   }

   //当用户或医生编辑地址的时候
    @RequestMapping("/userUpdateAddress")
    public  Integer userUpdateAddress(UserAddress userAddress){
       return userAddressService.userUpdateAddress(userAddress);
    }

    //当用户删除地址的时候
    @RequestMapping("/userDelAddress")
    public Integer userDelAddress(Integer id,Integer userId){
       User user = userService.findById(userId);
       if(user.getUserDefaultAddress() == id) {
           userService.updateUserDefaultAddress(0, userId);
       }
        return  userAddressService.userDelAddress(id);
    }

    //当用户更改自己的默认地址的时候
    @RequestMapping("/updateDefaultAddress")
    public Integer updateDefaultAddress(Integer addressId,Integer userId){
       Integer isOk = userService.updateUserDefaultAddress(addressId,userId);
       return isOk;
    }
    //当医生更改自己的默认地址的时候
    @RequestMapping("/updateDoctorDefaultAddress")
    public Integer updateDoctorDefaultAddress(Integer addressId,Integer doctorId){
        Integer isOk = doctorService.updateDoctorDefaultAddress(addressId,doctorId);
        return isOk;
    }

    //当用户查看自己地址的时候
    @RequestMapping("/loginUserAddress")
    public List<UserAddress> loginUserAddress(Integer userId){
        List<UserAddress> userAddressList = userAddressService.getOneUserAddresses(userId);
        return userAddressList;
    }
    //当医生查看自己的地址的时候
    @RequestMapping("/loginDoctorAddress")
    private  List<UserAddress> loginDoctorAddress(Integer doctorId){
       List<UserAddress> userAddresses = userAddressService.getOneDoctorAddresses(doctorId);
       return userAddresses;
    }

    //查看单个地址 和查看是否为默认地址
    @RequestMapping("/loginOneAddress")
    public Map<String,Object>  loginOneAddress(Integer id,@RequestParam(defaultValue = "0")Integer userId,@RequestParam(defaultValue = "0")Integer doctorId){
        Map<String,Object> map = new HashMap<>();
        if(userId != 0) {
            User user = userService.findById(userId);
            map.put("defaultAddressId", user.getUserDefaultAddress());
        }
        if(doctorId != 0){
            Doctor doctor = doctorService.getOneOfDoctorById(doctorId);
            map.put("defaultAddressId",doctor.getDoctorDefaultAddress());
        }
       map.put("address",userAddressService.getOneAddress(id));
       return map;
    }

}
