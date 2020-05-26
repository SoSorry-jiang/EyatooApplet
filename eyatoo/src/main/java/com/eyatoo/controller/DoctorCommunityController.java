package com.eyatoo.controller;

import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import com.eyatoo.utils.Random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DoctorCommunityController {

    @Autowired
    private DoctorCommunityService doctorCommunityService;

    @Autowired
    private DoctorCommunityCommentService doctorCommunityCommentService;

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private UserPraiseService userPraiseService;

    @Resource
    private UserConcernService userConcernService;

    @Resource
    private DoctorService doctorService;

    //加载医生帖子
    @RequestMapping("/loginDoctorCommunity")
    public List<Map<String,Object>>  loginDoctorCommunity(@RequestParam(defaultValue = "0")String tagId,Integer userId){
        List<Map<String,Object>> paramList = new ArrayList<>();
        List<DoctorCommunity> doctorCommunities = doctorCommunityService.getAllDoctorCommunityByTag(tagId);
        for (DoctorCommunity doctorCommunity:doctorCommunities) {
            Map<String,Object> map = new HashMap<>();
            //根据id加载单个日志所有评论
            List<DoctorCommunityComment> doctorCommunityCommentList = doctorCommunityCommentService.getAllCommunityCommentById(doctorCommunity.getId());
            List<DoctorCommunityCommentReply> doctorCommunityCommentReplyList = doctorCommunityCommentService.getAllReplyListByCommunityId(doctorCommunity.getId());
            //评论总数
            Integer countComment  = doctorCommunityCommentList.size()+ doctorCommunityCommentReplyList.size();
            map.put("id",doctorCommunity.getId());
            map.put("doctorId",doctorCommunity.getDoctorId());
            map.put("doctorName",doctorCommunity.getDoctorName());
            map.put("doctorAvator",doctorCommunity.getDoctorAvator());
            map.put("pictureA",doctorCommunity.getPictureA());
            map.put("pictureB",doctorCommunity.getPictureB());
            map.put("pictureC",doctorCommunity.getPictureC());
            map.put("title",doctorCommunity.getTitle());
            map.put("praisePoints",doctorCommunity.getPraisePoints());
            map.put("projectTagId",doctorCommunity.getProjectTagId());
            map.put("countComment",countComment);
            //加载该用户是否已经点赞过
            UserPraise userPraise = new UserPraise();
            userPraise.setUserId(userId);
            userPraise.setDoctorCommunityId(doctorCommunity.getId());
            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
            map.put("isPraise",isPraise);
            //加载该用户是否已经关注过
            Integer isConcerned = userConcernService.isConcernedOrNot(userId, null,null, doctorCommunity.getDoctorId());
            map.put("isConcerned",isConcerned);
            map.put("browseVolume",doctorCommunity.getBrowseVolume());
            paramList.add(map);
        }
        return paramList;
    }

    //加载单个所有医生帖子,既医生详情页点击‘我的帖子’
    @RequestMapping("/loginOneDoctorCommunity")
    public List<DoctorCommunity> loginOneDoctorCommunity(Integer doctorId){
        List<DoctorCommunity> doctorCommunities = doctorCommunityService.getOneOfDoctorCommunitById(doctorId);
        return doctorCommunities;
    }


    //医生发布帖子
    @RequestMapping("/addDoctorCommunity")
    public Integer addDoctorCommunity(DoctorCommunity doctorCommunity){
        Doctor doctor = doctorService.getOneOfDoctorById(doctorCommunity.getDoctorId());
        doctorCommunity.setDoctorAvator(doctor.getDoctorAvator());
        doctorCommunity.setDoctorName(doctor.getDoctorName());
        if(doctorCommunity.getPictureA() == null  || doctorCommunity.getPictureA().equals("undefined"))
            doctorCommunity.setPictureA(null);
        if(doctorCommunity.getPictureB() == null  || doctorCommunity.getPictureB().equals("undefined"))
            doctorCommunity.setPictureB(null);
        if(doctorCommunity.getPictureC() == null  || doctorCommunity.getPictureC().equals("undefined") )
            doctorCommunity.setPictureC(null);
        if(doctorCommunity.getPictureD() == null || doctorCommunity.getPictureD().equals("undefined")  )
            doctorCommunity.setPictureD(null);
        if(doctorCommunity.getPictureE() == null ||  doctorCommunity.getPictureE().equals("undefined") )
            doctorCommunity.setPictureE(null);
       Integer isOK = doctorCommunityService.addDoctorCommunity(doctorCommunity);
       return  isOK;
    }


    //根据id取得单个医生帖子详情
    @RequestMapping("/getOneDoctorCommunity")
    public Map<String,Object> getOneDoctorCommunity(Integer id,Integer userId){
        doctorCommunityService.updateBrowseVolume(id);
        Map<String,Object> partarms = new HashMap<>();
        //获取帖子详情
        DoctorCommunity doctorCommunity = doctorCommunityService.getOneDoctorCommunityById(id);

        //获取该帖子下所有评论和回复列表
        List<DoctorCommunityComment> doctorCommunityComments = doctorCommunityCommentService.getAllCommunityCommentById(doctorCommunity.getId());
        List<DoctorCommunityComment> doctorCommunityCommentList = new ArrayList<>();
        doctorCommunity.setCommentCount(doctorCommunityComments.size());
        //判断是否点赞过
        for (DoctorCommunityComment doctorCommunityComment:doctorCommunityComments) {
              UserPraise userPraise = new UserPraise();
              userPraise.setUserId(userId);
              userPraise.setDoctorCommunityCommentId(doctorCommunityComment.getId());
              Integer isPraise = userPraiseService.loginUserPraise(userPraise);
              doctorCommunityComment.setIsPraise(isPraise);
              doctorCommunityCommentList.add(doctorCommunityComment);
        }
        List<DoctorCommunityCommentReply> doctorCommunityCommentReplies = doctorCommunityCommentService.getAllReplyListByCommunityId(doctorCommunity.getId());
        List<DoctorCommunityCommentReply> doctorCommunityCommentReplyList = new ArrayList<>();
        //判断是否点赞过
        for (DoctorCommunityCommentReply doctorCommunityCommentReply:doctorCommunityCommentReplies) {
            UserPraise userPraise = new UserPraise();
            userPraise.setUserId(userId);
            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
            doctorCommunityCommentReply.setIsPraise(isPraise);
            doctorCommunityCommentReplyList.add(doctorCommunityCommentReply);
        }
        partarms.put("doctorCommunity",doctorCommunity);
        partarms.put("doctorCommunityCommentList",doctorCommunityCommentList);
        partarms.put("doctorCommunityCommentReplyList",doctorCommunityCommentReplyList);
        return partarms;
    }
}
