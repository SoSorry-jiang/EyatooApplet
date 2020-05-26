package com.eyatoo.controller;

import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import com.eyatoo.utils.QRCodeUtil;
import org.apache.ibatis.ognl.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

    //用户申请经纪人成功或失败
    @RequestMapping("updateUserAgentStatus")
    private Map<String,Object> updateUserAgentStatus(Integer userId,Integer status){
        Map<String,Object> paramMap = new HashMap<>();
        if(status == 1){
            userService.updateAgentStatus(userId,status);
            paramMap.put("msg","success");
        }
        else if (status == 3){
            userService.updateAgentStatus(userId, status);
            paramMap.put("msg","fail");
        }
        else {
            paramMap.put("msg","错误");
        }
        return paramMap;
    }

   //医生申请经纪人成功或失败
    @RequestMapping("updateDoctorAgentStatus")
    private Map<String,Object> updateDoctorAgentStatus(Integer doctorId,Integer status){
        Map<String,Object> paramMap = new HashMap<>();
        if(status == 1){
            doctorService.updateAgentStatus(doctorId,status);
            paramMap.put("msg","success");
        }
        else if (status == 3){
            doctorService.updateAgentStatus(doctorId,status);
            paramMap.put("msg","fail");
        }
        else {
            paramMap.put("msg","错误");
        }
        return paramMap;
    }

    //邀请界面加载
    @RequestMapping("/invitation")
    public Map<String ,Object> loginInvtation(@RequestParam(defaultValue = "0") Integer id) throws Exception {
        Map<String ,Object> parameterList = new HashMap<>();
        //判定用户是否为经纪人
        User isAgent = userService.findById(id);
        if(isAgent.getIsAgent() == 1){
            parameterList.put("msg","success");
        }else if(isAgent.getIsAgent() == 3){
            //审核失败，重新审核
            parameterList.put("msg","rehearing");
        }
        else if(isAgent.getIsAgent() == 2){
            //审核中
            parameterList.put("msg","examining");
        }else if(isAgent.getIsAgent() == 0){
            //该用户不是经纪人，需申请！申请后方可邀请其他用户
            parameterList.put("msg","notAgent");
        }
       return parameterList;
    }

    //医生邀请界面加载
    @RequestMapping("/invitationDoctorLogin")
    public Map<String ,Object> invitationDoctorLogin(@RequestParam(defaultValue = "0") Integer id) throws Exception {
        Map<String ,Object> parameterList = new HashMap<>();
        //判定用户是否为经纪人
        Doctor isAgent = doctorService.getOneOfDoctorById(id);
        if(isAgent.getIsAgent() == 1){
            parameterList.put("msg","success");
        }else if(isAgent.getIsAgent() == 3){
            //审核失败，重新审核
            parameterList.put("msg","rehearing");
        }
        else if(isAgent.getIsAgent() == 2){
            //审核中
            parameterList.put("msg","examining");
        }else if(isAgent.getIsAgent() == 0){
            //该用户不是经纪人，需申请！申请后方可邀请其他用户
            parameterList.put("msg","notAgent");
        }
        return parameterList;
    }


    //生成经纪人二维码
    @RequestMapping("/getAgentQRCode")
    public String getAgentQRCode(Integer userId) throws Exception {
        User user = userService.findById(userId);
        if(user.getQrCode() == null){
        Random dom = new Random();
        String haomiaoshus = String.valueOf(System.currentTimeMillis());
        String fileName = "QRcode"+dom.nextInt(100)+haomiaoshus+".jpg";
//        String filepath = ftpConfig.getServerPath()+"\\User\\InviteQRcode"+fileName;
        String imgPath = userService.findById(userId).getTxPhoto();
        String filepath = ftpConfig.getServerPath()+"User/InviteQRcode/"+fileName;
        String requestPath = "https://eyatoo.cn/eyatoo/userScanningQRCode?codeUserId="+userId;
        QRCodeUtil.encode(requestPath,imgPath,filepath,true);
        String dataBasePath = ftpConfig.getImageBaseUrl()+"User/InviteQRcode/"+fileName;
        Integer isOk = userService.updateQRcode(userId,dataBasePath);
        if(isOk == 0)
        return "false";
        else
        return dataBasePath;
        }else{
            return user.getQrCode();
        }
    }

    //生成医生经纪人二维码
    @RequestMapping("/getDoctorAgentQRCode")
    public String getDoctorAgentQRCode(Integer doctorId) throws Exception {
        Doctor doctor = doctorService.getOneOfDoctorById(doctorId);
        if(doctor.getQrCode() == null){
            Random dom = new Random();
            String haomiaoshus = String.valueOf(System.currentTimeMillis());
            String fileName = "QRcode"+dom.nextInt(100)+haomiaoshus+".jpg";
//        String filepath = ftpConfig.getServerPath()+"\\User\\InviteQRcode"+fileName;
            String imgPath = userService.findById(doctorId).getTxPhoto();
            String filepath = ftpConfig.getServerPath()+"Doctor/InviteQRcode/"+fileName;
            String requestPath = "https://eyatoo.cn/eyatoo/userScanningQRCode?codeUserId="+doctorId;
            QRCodeUtil.encode(requestPath,imgPath,filepath,true);
            String dataBasePath = ftpConfig.getImageBaseUrl()+"Doctor/InviteQRcode/"+fileName;
            Integer isOk = userService.updateQRcode(doctorId,dataBasePath);
            if(isOk == 0)
                return "false";
            else
                return dataBasePath;
        }else{
            return doctor.getQrCode();
        }
    }


    //用户提交 身份证信息后，上传数据库
    @RequestMapping("/updateTrueMsg")
    public Integer updateTrueMsg(Integer userId,String trueName,String idCard){
        userService.updateAgentStatus(userId,2);
       return userService.updateTrueMsg(userId, trueName, idCard);
    }

    //医生提交 身份证信息后，上传数据库
    @RequestMapping("/updateDoctorTrueMsg")
    public Integer updateDoctorTrueMsg(Integer doctorId,String trueName,String idCard){
        doctorService.updateAgentStatus(doctorId,2);
        return doctorService.updateTrueMsg(doctorId, trueName, idCard);
    }

    //用户扫码二维码后
    @RequestMapping("/userScanningQRCode")
    public String userScanningQRCode(Integer codeUserId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String invitedPrsId = request.getParameter("userId");
        if (invitedPrsId == null){
            response.sendRedirect("/eyatoo/QrCodeError");
            return "redirect";
        }else if(invitedPrsId.equals(codeUserId.toString())){
            return  "不能自己邀请自己";
        }else if(invitationService.judgeUser(codeUserId,Integer.parseInt(invitedPrsId)) > 0){
            return "您已被邀请";
        }
        Invited invited = new Invited();
        invited.setInvitedPrsId(Integer.parseInt(invitedPrsId));
        invited.setUserId(codeUserId);
        Integer isOk = invitationService.addInvitedUser(invited);
        return "扫描成功";
    }

    //用户使用外部扫一扫挑战至错误界面
    @RequestMapping("/QrCodeError")
    public ModelAndView QrCodeError(){
        ModelAndView model = new ModelAndView();
        model.setViewName("QrCodeError");
        return model;
    }


    //根据就诊状态查看用户邀请的人
    @RequestMapping("/loginUserInvitation")
    public Map<String,Object> loginUserInvitation(Integer userId,@RequestParam(defaultValue = "0")Integer status){
        Map<String,Object> objectMap = new HashMap<>();
        //获取被改用户邀请的人的数组
        List<Invited> invitedList = invitationService.getAllInvitedPs(userId,status);
        //获取所有被邀请人名字和总数量
        List<User> invitedUser = new ArrayList<>();
        for (Invited invited:invitedList) {
            User intUser = userService.findById(invited.getInvitedPrsId());
            invitedUser.add(intUser);
        }
        if(status == 1){
            List<User> isTreamentUser = new ArrayList<>();
            for (User user:invitedUser){
                Accounts accounts = accountsService.getUserLastOneOfProjectBy(user.getId(),userId);
                if(accounts != null ) {
                    ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(accounts.getProjectId());
                    user.setLastTreatmentProjectTitle(projectMedicalAdvertisement.getTitle());
                    isTreamentUser.add(user);
                }
            }
            invitedUser = isTreamentUser;
        }
        //获取未就诊用户
        Integer disTreatmentCount = invitationService.getAllInvitedPs(userId,0).size();
        //获取已就诊用户
        Integer isTreatmentCount = invitationService.getAllInvitedPs(userId,1).size();
        objectMap.put("disTreatmentCount",disTreatmentCount);
        objectMap.put("isTreatmentCount",isTreatmentCount);
        objectMap.put("invitedUser",invitedUser);
        return objectMap;
    }

    //根据名字查找经纪人
    @RequestMapping("/findAgentByName")
    public Map<String,Object> findAgentByName(@RequestParam(defaultValue = "") String name){
        Map<String,Object> parameterList = new HashMap<>();
        List<User> users = userService.fingAllAgentByName(name);
        List<Doctor> doctors = doctorService.findAllAgentDoctor(name);
        parameterList.put("userAgent",users);
        parameterList.put("doctorAgent",doctors);
        return parameterList;
    }
}
