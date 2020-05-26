package com.eyatoo.controller;


import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import com.eyatoo.utils.Random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCommentService userCommentService;

    @Autowired
    private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private UserCommunityService userCommunityService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserConcernService userConcernService;

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private ProjectBranchService projectBranchService;

    @Autowired
    private DoctorService doctorService;

    private WxChat wxChat = new WxChat();
    //添加用户
    @RequestMapping("/addUser")
    private  User addUser(User user) throws IOException {
        if(userService.isHaveUser(user.getOpenId()) == null){
            //随机生成用户id
            Integer userId = RandomUtil.lessNumber();
            user.setId(userId);
            userService.addUser(user);
            wxChat.addChatUser(user.getId().toString(),user.getName(),user.getTxPhoto());
            return userService.findById(user.getId());
        }else{
            return userService.isHaveUser(user.getOpenId());
        }
    }

    //加载用户基本信息
    @RequestMapping("/loginUser")
    public Map<String,Object> getUserMsg(@RequestParam(defaultValue = "0")Integer id){
        Map<String,Object> parameterMap = new HashMap<>();
        if(id == 0){
            System.out.println("请先登录后再试！");
            parameterMap.put("msg","false");
            return parameterMap;
        }
        //获取用户基本信息
        User user = userService.findById(id);
        wxChat.updateUserData(user.getId().toString(),null,null,0,user.getJjrLevel());
        //加载用户 ‘关注’‘粉丝’‘日志’‘问答’
           //获得当前用户日志总数
        Integer countCommunityCount = userCommunityService.getOneUserLog(id).size();
           //获取当前用户问答总数
        Integer countQuestionCount = questionService.getOneUserQuestions(id).size();
           //获取当前用户粉丝总数
        Integer FansCount = userConcernService.getUserConcerntionList(id).size();
           //获取当前用户关注对象
        Integer ConcernedCount = userConcernService.getUserConcernedList(id).size();

        System.out.println(FansCount+"!!!and!!"+userConcernService.getUserConcernedList(id));
        System.out.println("取得参数成功！");
        parameterMap.put("msg","success");
        parameterMap.put("user",user);
        parameterMap.put("countCommunityCount",countCommunityCount);
        parameterMap.put("countQuestionCount",countQuestionCount);
        parameterMap.put("FansCount",FansCount);
        parameterMap.put("ConcernedCount",ConcernedCount);
        return parameterMap;
    }

    //当用户点击‘我的收藏’时，加载一个用户收藏的所有内容
    @RequestMapping("/loginOneUserCollection")
    public List<ProjectMedicalAdvertisement> getUserCollectedById(@RequestParam(defaultValue = "0")Integer id){
         if(id == 0){
             System.out.println("请先登录后查看您的收藏内容！");
             return null;
         }
        List<ProjectMedicalAdvertisement> smdList = projectMedicalAdvertisementService.findUserCollectedMsgById(id);
        return smdList;
    }

    //当用户点击‘我的评论’时，加载一个用户所有评论的内容和项目
    @RequestMapping("/loginOneUserComments")
    public List<Map<String,Object>> loginOneUserComments(@RequestParam(defaultValue = "0")Integer id){
        List<Map<String,Object>> parameterList = new ArrayList<>();
//        if(id == 0){
//            System.out.println("请先登录后查看您的评论内容！");
//            parameterMap.put("msg","false");
//            return parameterMap;
//        }else {
                List<UserComment> ucommentsList = userCommentService.getAllCommentsByUserId(id);
                for (UserComment userComment:ucommentsList) {
                    Map<String,Object> parameterMap = new HashMap<>();
                    ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(userComment.getCommentArticlesId());
                              parameterMap.put("userCommentId",userComment.getId());
                              parameterMap.put("proAvator",projectMedicalAdvertisement.getPath());
                              parameterMap.put("proTitle",projectMedicalAdvertisement.getTitle());
                              parameterMap.put("proPlace",projectMedicalAdvertisement.getStoreName());
                              parameterMap.put("score",userComment.getScore());
                              parameterMap.put("comment",userComment.getComments());
                              parameterList.add(parameterMap);
                }
            return parameterList;
        }


    //当用户想改变自己的名称和手机号时
    @RequestMapping("/updateUserPhoneOrName")
    public Integer updateUserPhoneOrName( String name, BigInteger phone, Integer id) throws IOException {
        Integer num = userService.updateUserAvatorOrName(null,name,phone,id);
        if(num != 0){
            userService.updateOtherTablesMsg(null,name,id);
            wxChat.updateUserData(id.toString(),name,null,null,null);
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        return num;
    }

    @RequestMapping("/updateUserAvator")
    public Integer updateUserAvator(MultipartFile path1,Integer id) throws IOException {
        String txPath = "";
        if (!path1.isEmpty()) {
            //使用随机数将上传的文件名称修改
            String FileName = RandomUtil.number() + ".jpg";
            //上传的保存路径
            String filePath = ftpConfig.getServerPath() + "User";
            //封装上传文件位置的全路径
            File targetFile = new File(filePath, FileName);
            //把本地文件上传到封装上传文件位置的全路径
            path1.transferTo(targetFile);
            txPath = ftpConfig.getImageBaseUrl() + "User/" + FileName;
        }
        Integer num = userService.updateUserAvatorOrName(txPath,null,null,id);
        if(num != 0){
            userService.updateOtherTablesMsg(txPath,null,id);
            wxChat.updateUserData(id.toString(),null,txPath,null,null);
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        return num;
    }

    //用户查看自己的预约
    @RequestMapping("loginUserOrder")                                            //status  0:未就诊 1:已就诊 2:未付款
    public List<Map<String,Object>> loginUserOrder(Integer userId,@RequestParam(defaultValue = "0")Integer status){

        List<Map<String,Object>> parterMap = new ArrayList<>();
        List<Accounts> disAccounts = new ArrayList<>();
        if(status == 0) {
            disAccounts = accountsService.getOneUserDisTreatment(userId);
        }else if(status == 1){
            disAccounts = accountsService.getOneUserIsTreatment(userId);
        }else if (status == 2){
            disAccounts = accountsService.getUserAccountStatus(userId,1);
        }
        for (Accounts accounts:disAccounts){
            Map<String,Object> orderMap = new HashMap<>();
            ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(accounts.getProjectId());
            Doctor doctor = doctorService.getOneOfDoctorById(accounts.getDoctorId());
            ProjectBranch projectBranch = projectBranchService.getOneBranchById(accounts.getBranchId());
            orderMap.put("doctorAvator",doctor.getDoctorAvator());
            orderMap.put("doctorName",doctor.getDoctorName());
            orderMap.put("accPlace",projectBranch.getStoreName());
            orderMap.put("accNumber",accounts.getNumber());
            orderMap.put("accId",accounts.getId());
            orderMap.put("proId",projectMedicalAdvertisement.getId());
            orderMap.put("proName",projectMedicalAdvertisement.getTitle());
            orderMap.put("proAvator",projectMedicalAdvertisement.getPath());
            orderMap.put("proPlace",projectMedicalAdvertisement.getStoreName());
            orderMap.put("proSales",projectMedicalAdvertisement.getSalesVolume());
            orderMap.put("proMoney",accounts.getFinallayMoney());
            orderMap.put("proStatus",accounts.getStatus());
            orderMap.put("proOrderTime",accounts.getMedicalTime());
            parterMap.add(orderMap);
        }
        return parterMap;
    }


    //更新二维码图标
    @RequestMapping("/updateQRcode")
    public Integer updateQRcode(Integer userId,String path){
        return userService.updateQRcode(userId,path);
    }

}
