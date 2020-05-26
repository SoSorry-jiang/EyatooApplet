package com.eyatoo.controller;


import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import com.eyatoo.utils.Random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserCommunityController {

    @Resource
    private UserCommunityService userCommunityService;

    @Resource
    private UserCommunityCommentService userCommunityCommentService;

    @Resource
    private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private UserService userService;

    @Resource
    private UserPraiseService userPraiseService;

    @Resource
    private UserConcernService userConcernService;

    //用户发表日志
    @RequestMapping("/addCommunity")
    public Integer newCommunity(@RequestParam(defaultValue = "0") Integer upId,UserCommunity userCommunity, MultipartFile[] path) throws IOException {
        String b = null;
        for (MultipartFile path1: path) {
            if (!path1.isEmpty()) {
                //使用随机数将上传的文件名称修改
                String FileName = RandomUtil.number() + ".jpg";
                //上传的保存路径
                String filePath = ftpConfig.getServerPath() + "UserCommunity";
                //封装上传文件位置的全路径
                File targetFile = new File(filePath, FileName);
                //把本地文件上传到封装上传文件位置的全路径
                path1.transferTo(targetFile);
                b = ftpConfig.getImageBaseUrl() + "UserCommunity/" + FileName;
            }
        }
        User user = userService.findById(userCommunity.getUserId());
        userCommunity.setUserName(user.getName());
        userCommunity.setUserAvator(user.getTxPhoto());
        System.out.println(upId);
        if(upId != 0){  //说明是修改 通过upId获取数据库数据，把此次上传的图片设置设置到after 在执行更新
            userCommunityService.updatePhoto(b,upId);
            return upId;
        }else{
            //说明是新增设置before值返回新增数据的id
            userCommunity.setBeforePhoto(b);
        }
        userCommunityService.addCommunity(userCommunity);
        return  userCommunity.getId();
    }
//    public Integer newCommunity(UserCommunity userCommunity, MultipartFile[] path) throws IOException {
//        Integer inum = 1;
//        String a =  null;
//        String b = null;
//        for (MultipartFile path1: path) {
//            if (!path1.isEmpty()) {
//                //使用随机数将上传的文件名称修改
//                String FileName = RandomUtil.number() + ".jpg";
//                //上传的保存路径
//                String filePath = ftpConfig.getServerPath() + "DoctorCommunity";
//                //封装上传文件位置的全路径
//                File targetFile = new File(filePath, FileName);
//                //把本地文件上传到封装上传文件位置的全路径
//                path1.transferTo(targetFile);
//                if(inum == 1)
//                    a = ftpConfig.getImageBaseUrl() + "DoctorCommunity/" + FileName;
//                    userCommunity.setBeforePhoto(ftpConfig.getImageBaseUrl() + "DoctorCommunity/" + FileName);
//                if(inum == 2)
//                    b = ftpConfig.getImageBaseUrl() + "DoctorCommunity/" + FileName;
//                    userCommunity.setAfterPhoto(ftpConfig.getImageBaseUrl() + "DoctorCommunity/" + FileName);
//                inum++;
//            }
//        }
//        User user = userService.findById(userCommunity.getUserId());
//        userCommunity.setUserName(user.getName());
//        userCommunity.setUserAvator(user.getTxPhoto());
//        Integer num = userCommunityService.addCommunity(userCommunity);
//        return num;
//    }


    //加载社区界面
    @RequestMapping("/loginCommunity")
    public List<Map<String,Object>> loginCommunity(@RequestParam(defaultValue = "0") String tagId,Integer userId) {
        if (tagId.equals("0")) {
            tagId = "";
        }
        List<Map<String,Object>> paramList = new ArrayList<>();
        List<UserCommunity> userCommunityList = userCommunityService.getAllCommunity(tagId);
        for (UserCommunity userCommunity:userCommunityList) {
            Map<String,Object> map = new HashMap<>();
            //根据id加载单个日志所有评论
            List<UserCommunityComment> userCommunityCommentList = userCommunityCommentService.getAllCommunityCommentById(userCommunity.getId());
            List<UserCommunityCommentReply> userCommunityCommentReplyList = userCommunityCommentService.getAllReplyListByCommunityId(userCommunity.getId());
            //评论总数
            Integer countComment = userCommunityCommentList.size();
            map.put("countComment",countComment);
            map.put("id",userCommunity.getId());
            map.put("beforePhoto",userCommunity.getBeforePhoto());
            map.put("afterPhoto",userCommunity.getAfterPhoto());
            map.put("projectId",userCommunity.getProjectId());
            map.put("userId",userCommunity.getUserId());
            map.put("doctorId",userCommunity.getDoctorId());
            map.put("content",userCommunity.getContent());
            map.put("praisePoints",userCommunity.getPraisePoints());
            map.put("browse",userCommunity.getBrowse());
            map.put("userName",userCommunity.getUserName());
            map.put("userAvator",userCommunity.getUserAvator());
            //加载该用户是否已经点赞过
            UserPraise userPraise = new UserPraise();
            userPraise.setUserId(userId);
            userPraise.setUserCommunityId(userCommunity.getId());
            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
            map.put("isPraise",isPraise);
            //加载该用户是否已经关注过
            Integer isConcerned = userConcernService.isConcernedOrNot(userId, null, userCommunity.getUserId(), null);
            map.put("isConcerned",isConcerned);
            paramList.add(map);
        }
        return paramList;
    }

    //根据日志id加载单个日志
    @RequestMapping("/getOneCommunity")
    public Map<String, Object> getOneCommunity(Integer id,Integer userId) {
        Map<String, Object> parameterMap = new HashMap<>();

        UserCommunity userCommunity = userCommunityService.getOneCommunity(id);
        //加载项目信息
        ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(userCommunity.getProjectId());
        Doctor projectDoctor = doctorService.getOneOfDoctorById(userCommunity.getDoctorId());

        //根据id加载单个日志所有评论
        List<UserCommunityComment> userCommunityCommentLists = userCommunityCommentService.getAllCommunityCommentById(id);
        List<UserCommunityComment> userCommunityCommentList = new ArrayList<>();
        //判断用户是否已点赞该评论
        for (UserCommunityComment userCommunityComment:userCommunityCommentLists) {
              UserPraise userPraise = new UserPraise();
              userPraise.setUserId(userId);
              userPraise.setUserCommunityCommentId(userCommunityComment.getId());
              Integer isPraise = userPraiseService.loginUserPraise(userPraise);
              userCommunityComment.setIsPraise(isPraise);
              userCommunityCommentList.add(userCommunityComment);
        }
        List<UserCommunityCommentReply> userCommunityCommentReplyLists = userCommunityCommentService.getAllReplyListByCommunityId(id);
        List<UserCommunityCommentReply> userCommunityCommentReplyList = new ArrayList<>();
        //判断用户是否已点赞该评论
        for (UserCommunityCommentReply userCommunityCommentReply:userCommunityCommentReplyLists) {
            UserPraise userPraise = new UserPraise();
            userPraise.setUserId(id);
            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
            userCommunityCommentReply.setIsPraise(isPraise);
            userCommunityCommentReplyList.add(userCommunityCommentReply);
        }
        //评论总数
        Integer countComment = userCommunityCommentList.size()+ userCommunityCommentReplyList.size();
        parameterMap.put("countComment",countComment);
        parameterMap.put("projectMedicalAdvertisement", projectMedicalAdvertisement);
        parameterMap.put("projectDoctor", projectDoctor);
        parameterMap.put("userCommunityCommentList", userCommunityCommentList);
        parameterMap.put("userCommunity", userCommunity);
        parameterMap.put("userCommunityCommentReplyList", userCommunityCommentReplyList);
        System.out.println(parameterMap.get("userCommunityCommentReplyList"));
        return parameterMap;
    }


    //当用户单击 ‘我的---日志’时，根据用户id查询当前用户所有日志
    @RequestMapping("/getOneUserCommunity")
    public List<Map<String,Object>> getOneUserCommunity(Integer id,Integer nowUserId) {
        List<Map<String,Object>> paramList = new ArrayList<>();
        List<UserCommunity> userCommunityList = userCommunityService.getOneUserLog(id);
        for (UserCommunity userCommunity:userCommunityList) {
            Map<String,Object> map = new HashMap<>();
            //根据id加载单个日志所有评论
            List<UserCommunityComment> userCommunityCommentList = userCommunityCommentService.getAllCommunityCommentById(userCommunity.getId());
            List<UserCommunityCommentReply> userCommunityCommentReplyList = userCommunityCommentService.getAllReplyListByCommunityId(userCommunity.getId());
            //评论总数
            Integer countComment = userCommunityCommentList.size()+ userCommunityCommentReplyList.size();
            map.put("countComment",countComment);
            map.put("id",userCommunity.getId());
            map.put("beforePhoto",userCommunity.getBeforePhoto());
            map.put("afterPhoto",userCommunity.getAfterPhoto());
            map.put("projectId",userCommunity.getProjectId());
            map.put("userId",userCommunity.getUserId());
            map.put("doctorId",userCommunity.getDoctorId());
            map.put("content",userCommunity.getContent());
            map.put("praisePoints",userCommunity.getPraisePoints());
            map.put("browse",userCommunity.getBrowse());
            map.put("userName",userCommunity.getUserName());
            map.put("userAvator",userCommunity.getUserAvator());
            //加载该用户是否已经点赞过
            UserPraise userPraise = new UserPraise();
            userPraise.setUserId(nowUserId);
            userPraise.setUserCommunityId(userCommunity.getId());
            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
            map.put("isPraise",isPraise);
            //加载该用户是否已经关注过
            Integer isConcerned = userConcernService.isConcernedOrNot(nowUserId, null, userCommunity.getUserId(), null);
            map.put("isConcerned",isConcerned);
            paramList.add(map);
        }
        return paramList;
    }

    //当医生查看 自己相关日志时显示
    @RequestMapping("/getAboutDoctorCommunity")
    public List<UserCommunity> getAboutDoctorCommunity(Integer doctorId) {
        List<UserCommunity> aboutDoctorCommunity = userCommunityService.getAboutDoctorCommunity(doctorId);
        return aboutDoctorCommunity;
    }
}