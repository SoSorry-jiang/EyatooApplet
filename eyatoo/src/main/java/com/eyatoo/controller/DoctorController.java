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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

//@Controller
@RestController
public class DoctorController {
      @Autowired
      private DoctorService doctorService;

      @Autowired
      private DoctorCommunityService doctorCommunityService;

      @Autowired
      private DoctorBranchService doctorBranchService;

      @Autowired
      private ProjectBranchService projectBranchService;

      @Autowired
      private DoctorProjectService doctorProjectService;

      @Autowired
      private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

      @Autowired
      private  AccountsService accountsService;

      @Autowired
      private UserService userService;


      @Autowired
      private DoctorCaseService doctorCaseService;

      @Autowired
      private DoctorAnswerService doctorAnswerService;

      @Autowired
      private DoctorConcernService doctorConcernService;

      @Autowired
      private FTPConfig ftpConfig;

      @Autowired
      private OutpatientPresentationsService outpatientPresentationsService;

      @Autowired
      private UserConcernService userConcernService;

      @Autowired
      private DoctorWorkDateService doctorWorkDateService;

      @Autowired
      private UserCommentService userCommentService;

      private WxChat wxChat = new WxChat();

      //医生登录
      @RequestMapping("DoctorLogin")
      private Map<String,Object> DoctorLogin(String phone,String password){
          Map<String,Object> map = new HashMap<>();
          Integer isOk =  doctorService.DoctorLogin(phone,password);
          if (isOk != 0){
              Doctor doctor = doctorService.yzDoctorPhone(phone);
              map.put("doctorId",doctor.getId());
          }
          map.put("msg",isOk);
          return map;
      }

      //医生忘记密码
      @RequestMapping("forgetDoctorPsw")
      private String forgetDoctorPsw(String phone,String password){
           Doctor doctor = doctorService.yzDoctorPhone(phone);
           Integer isHave = 0;
           if (doctor != null){
               isHave = 1;
           }
           if(isHave == 0){
              //当医生没注册，却点击忘记密码时
              return "haveNoDoctor";
           }else if(isHave != 0){
               doctorService.DoctorUpdatePsd(phone, password);
               //修改成功
               return  "modifySuccess";
           }
           return "出错";
     }

    //验证手机验证码
    @RequestMapping("/yzDoctorPhone")
    public Integer yzDoctorPhone(String phone){
        Doctor doctor = doctorService.yzDoctorPhone(phone);
        Integer isHave = 0;
        if (doctor != null){
            isHave = 1;
        }
        return isHave;
    }

    //添加医生
    @RequestMapping("/addDoctor")
    private Integer addDoctor(Doctor doctor,String doctorSpecializeString) throws Exception{
//        if(doctor.getIdCardBack().equals("undefined"))
//            doctor.setIdCardBack(null);
//        if(doctor.getRegisterRecord().equals("undefined"))
//            doctor.setRegisterRecord(null);
         if(doctor.getIsOurLandLocked() == 1){
             doctor.setIdCardBack(null);
             doctor.setRegisterRecord(null);
         }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        Integer nowYear = Integer.parseInt(sdf.format(date));
        Integer doctorStartYear = Integer.parseInt(sdf.format(sdf.parse(doctor.getStartWorkTime())));
        Integer workYear = nowYear - doctorStartYear;
        doctor.setDoctorWorkYear(workYear);
        doctor.setDoctorStatus(0);

        Integer doctorId = RandomUtil.lessNumber();
        doctor.setId(doctorId);
        Integer isOK = doctorService.addDoctor(doctor);
        String[] doctorSpecializeStrings = doctorSpecializeString.split("   ");
        DoctorSpecialize doctorSpecialize = new DoctorSpecialize();
        doctorSpecialize.setDoctorId(doctor.getId());
        for (int i=1;i<doctorSpecializeStrings.length;i++){
            if(i == 1){
                doctorSpecialize.setSpecializeMajorA(doctorSpecializeStrings[i]);
             }
            if(i == 2){
                doctorSpecialize.setSpecializeMajorB(doctorSpecializeStrings[i]);
            }
            if(i == 3){
                doctorSpecialize.setSpecializeMajorC(doctorSpecializeStrings[i]);
            }
            if(i == 4){
                doctorSpecialize.setSpecializeMajorD(doctorSpecializeStrings[i]);
            }
            if(i == 5){
                doctorSpecialize.setSpecializeMajorE(doctorSpecializeStrings[i]);
            }
            if(i == 6){
                doctorSpecialize.setSpecializeMajorF(doctorSpecializeStrings[i]);
            }
        }
        Integer isOk2 = doctorService.addDoctorMajor(doctorSpecialize);
        if(isOK != 0 && isOk2 != 0){
            wxChat.addChatUser(doctor.getId().toString(),doctor.getDoctorAvator(),doctor.getDoctorName());
            return 1;
        }else {
            return 0;
        }
    }


      //加载医生基本信息
     @RequestMapping("/loginDoctor")
     private Map<String,Object> loginDoctor(Integer doctorId){
         Doctor doctor = doctorService.getOneOfDoctorById(doctorId);
//         User user = userService.findById(doctor.getBindUserId());
         wxChat.updateUserData(doctor.getId().toString(),null,null,1,doctor.getJjrLevel());
         Map<String,Object> paramMap = new HashMap<>();
         //获取当前用户粉丝总数
         Integer FansCount = doctorConcernService.getDoctorConcerntionList(doctorId).size();
         //获取当前用户关注对象总数
         Integer ConcernedCount = doctorConcernService.getDoctorConcernedList(doctorId).size();
         //查看点赞总数
         BigInteger praiseCount = BigInteger.valueOf(0);
         List<DoctorAnswer> doctorAnswers = doctorAnswerService.getOneDoctorAnswer(doctorId);
         if(doctorAnswers.size() != 0) {
             BigInteger answerPraise = BigInteger.valueOf(0);
             for (DoctorAnswer doctorAnswer : doctorAnswers) {
                 answerPraise = answerPraise.add(doctorAnswer.getPraiseCount());
             }
             praiseCount = praiseCount.add(answerPraise);
         }

         List<DoctorCase> doctorCases = doctorCaseService.getOneDoctorAllCase(doctorId);
         if(doctorCases.size() != 0) {
             BigInteger caseCount = BigInteger.valueOf(0);
             for (DoctorCase doctorCase : doctorCases) {
                 caseCount = caseCount.add(doctorCase.getPraiseCount());
             }
             praiseCount = praiseCount.add(caseCount);
         }


         List<DoctorCommunity> doctorCommunities = doctorCommunityService.getOneOfDoctorCommunitById(doctorId);
         if(doctorCommunities.size() != 0) {
             BigInteger communityCount = BigInteger.valueOf(0);
             for (DoctorCommunity doctorCommunity : doctorCommunities) {
                 communityCount = communityCount.add(doctorCommunity.getPraisePoints());
             }
             praiseCount =  praiseCount.add(communityCount);
         }

         //加载负责的订单
             //加载预约的订单总数
         Integer orderCount  = accountsService.getDoctorAccountStatus(doctor.getId(),new Integer[]{2}).size();
             //加载就诊总数
         Integer mediaedCount =accountsService.getDoctorAccountStatus(doctor.getId(),new Integer[]{3}).size();
             //加载已完成总数
         Integer finshedCount =accountsService.getDoctorAccountStatus(doctor.getId(),new Integer[]{4,5,6}).size();
             //加载已评论总数
         Integer alreadyCommentCount = accountsService.getDoctorAccountStatus(doctorId,new Integer[]{5}).size() + accountsService.getDoctorAccountStatus(doctorId,new Integer[]{6}).size();

         paramMap.put("doctor",doctor);
//       paramMap.put("user",user);
         paramMap.put("FansCount",FansCount);
         paramMap.put("praiseCount",praiseCount);
         paramMap.put("ConcernedCount",ConcernedCount);
         paramMap.put("orderCount",orderCount);
         paramMap.put("mediaedCount",mediaedCount);
         paramMap.put("finshedCount",finshedCount);
         paramMap.put("alreadyCommentCount",alreadyCommentCount);
         return  paramMap;
     }


      //在用户端 首页-项目详情里面查看所有可以做这个项目的医生列表 或者 不带参数单纯查询所有医生
      @RequestMapping("/getAllDoctor")
      public List<Map<String,Object>> getAllDoctor(@RequestParam(defaultValue = "0") String proDo,@RequestParam(defaultValue = "")String name
              ,@RequestParam(defaultValue = "")String education,@RequestParam(defaultValue = "")String orderBy,Integer distanceId
              ,@RequestParam(defaultValue = "0")double lon, @RequestParam(defaultValue = "0")double lat,Integer userId){
          double minlat = 0;
          double maxlat = 0;
          double minlng = 0;
          double maxlng = 0;
        if(lon != 0 && lat != 0){
              double r = 6371;//地球半径千米
              double dis = 25; //这里固定半径25km
              double dlng = 2*Math.asin(Math.sin(dis/(2*r))/Math.cos(lat*Math.PI/180));
              dlng = dlng*180/Math.PI;//角度转为弧度
              double dlat = dis/r;
              dlat = dlat*180/Math.PI;
              minlat = lat - dlat;
              maxlat = lat + dlat;
              minlng = lon - dlng; 
              maxlng = lon + dlng;
          }
          List<Integer> idList = new ArrayList<>();
          if (!proDo.equals("0")){
              idList = doctorProjectService.selectDoctorDoPro(proDo);
          }else {
              idList = null;
          }
          if(education.equals("不限")){
              education = null;
          }
          if(orderBy.equals("不限")){
              orderBy = null;
          }
          List<Doctor> doctorList = doctorService.getAllDoctor(idList,name,education,orderBy,distanceId,BigDecimal.valueOf(minlng), BigDecimal.valueOf(maxlng), BigDecimal.valueOf(minlat), BigDecimal.valueOf(maxlat));
          //获取医生坐诊列表
          List<Map<String,Object>> paramList = new ArrayList<>();
          if(doctorList.size() != 0){
          for (Doctor doctor:doctorList) {
              Integer branchId = doctorBranchService.getOneDoctorWorkPlace(doctor.getId());
              ProjectBranch projectBranch =  projectBranchService.getOneBranchById(branchId);
              Map<String,Object> paramterMap = new HashMap<>();
              paramterMap.put("id",doctor.getId());
              paramterMap.put("doctorAvator",doctor.getDoctorAvator());
              paramterMap.put("doctorName",doctor.getDoctorName());
              paramterMap.put("doctorEducation",doctor.getDoctorEducation());
              paramterMap.put("doctorTag",doctor.getDoctorTag());
              paramterMap.put("sex",doctor.getSex());
              paramterMap.put("doctorJob",doctor.getDoctorJob());
              paramterMap.put("doctorWorkYear",doctor.getDoctorWorkYear());
              paramterMap.put("communityCount",doctor.getCommunityCount());
              paramterMap.put("answerCount",doctor.getAnswerCount());
              Integer caseCount = doctorCaseService.getOneDoctorAllCase(doctor.getId()).size();
              paramterMap.put("caseCount",caseCount);
              //加载该用户是否已经关注过
              Integer isConcerned = userConcernService.isConcernedOrNot(userId, null,null, doctor.getId());
              paramterMap.put("isConcerned",isConcerned);
              if(projectBranch == null){
                  paramterMap.put("workPlaceName","暂无");
              }else{
                  paramterMap.put("workPlaceName",projectBranch.getStoreName());
              }
              paramList.add(paramterMap);
          }
          }
          return  paramList;
      }

      //当用户查看医生时根据id查询单个医生详情
      @RequestMapping("/getOneDoctor")
    public Map<String,Object>  getOneDoctor(Integer id,Integer userId){
        Map<String,Object> paramterMap = new HashMap<>();

        //获取医生基本信息
        Doctor doctor = doctorService.getOneOfDoctorById(id);
        //获取医生帖子总数
        Integer countCommunity = doctorCommunityService.getOneOfDoctorCommunitById(id).size();
        //获取医生预约总数
        Integer countOrder  = accountsService.getDoctorAccountStatus(doctor.getId(),new Integer[]{2}).size();
        //获取医生案例总数
        Integer countCase = doctorCaseService.getOneDoctorAllCase(id).size();
        //获取医生问答总数
        Integer countQuestionAnswer = doctorAnswerService.getOneDoctorAnswer(id).size();
        //获取医生坐诊医院
        List<Integer>  idList =  doctorBranchService.getBranchByDoctor(id);
        List<Map<String,Object>> branchList = new ArrayList();
          for (Integer branchId:idList) {
            ProjectBranch projectBranch = projectBranchService.getOneBranchById(branchId);
            Map<String,Object> branchMap = new HashMap<>();
            branchMap.put("projectBranchIcon",outpatientPresentationsService.getIconPathByBranch(projectBranch.getId()));
            branchMap.put("storename",projectBranch.getStoreName());
            branchMap.put("phone",projectBranch.getPhone());
            branchMap.put("workingday",projectBranch.getWorkingday());
            branchMap.put("businesshours",projectBranch.getBusinesshours());
            branchMap.put("address",projectBranch.getAddress());
            branchList.add(branchMap);
          }
//        List<ProjectBranch>  branchList = new ArrayList<>();
//        for (Integer branchId:idList) {
//            ProjectBranch projectBranch = projectBranchService.getOneBranchById(branchId);
//            branchList.add(projectBranch);
//        }

        //获取医生坐诊项目
        List<DoctorProject> doctorProjects = doctorProjectService.selectProFormDoctorById(id);
        List<Integer> proIdList = new ArrayList<>();
        for (DoctorProject doctorProject:doctorProjects) {
            proIdList.add(doctorProject.getDoctorProjectId());
        }

        List<ProjectMedicalAdvertisement> projectMedicalAdvertisements = new ArrayList<>();
        for (Integer proId:proIdList) {
            ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(proId);
            projectMedicalAdvertisements.add(projectMedicalAdvertisement);
        }

        //加载该用户是否已经关注过
        Integer isConcerned = userConcernService.isConcernedOrNot(userId, null,null, doctor.getId());

        paramterMap.put("isConcerned",isConcerned);
        paramterMap.put("countCommunity",countCommunity);
        paramterMap.put("countOrder",countOrder);
        paramterMap.put("countCase",countCase);
        paramterMap.put("countQuestionAnswer",countQuestionAnswer);
//        paramterMap.put("branchList",branchList);
        paramterMap.put("branchList",branchList);
        paramterMap.put("projectMedicalAdvertisements",projectMedicalAdvertisements);
        paramterMap.put("doctor",doctor);
        return  paramterMap;
    }


      //查看医生所负责的订单 和 该订单下的评论
      @RequestMapping("/getDoctorAllAccounts")
      private List<Map<String,Object>> getDoctorAllAccounts(Integer doctorId,Integer[] status){
           List<Map<String,Object>> msgList = new ArrayList<>();
           //获取所有订单信息
           List<Accounts> accounts = accountsService.getDoctorAccountStatus(doctorId,status);
          if(accounts == null || accounts.size() == 0){
              Map<String,Object> partarmMap = new HashMap<>();
              partarmMap.put("msg","noData");
              msgList.add(partarmMap);
              return msgList;
          }
              for (Accounts account:accounts) {
              Map<String,Object> partarmMap = new HashMap<>();
              User user = userService.findById(account.getUserId());
              ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(account.getProjectId());
              UserComment userComment = userCommentService.getAllDoctorCommentisById(account.getDoctorId(),account.getProjectId(),account.getUserId());
              partarmMap.put("userId",user.getId());
              partarmMap.put("userName",user.getName());
              partarmMap.put("userAvator",user.getTxPhoto());
              partarmMap.put("proAvator",projectMedicalAdvertisement.getPath());
              partarmMap.put("proName",projectMedicalAdvertisement.getTitle());
              partarmMap.put("proMoney",account.getShouldPayMoney());
              partarmMap.put("proStroeName",projectMedicalAdvertisement.getStoreName());
              partarmMap.put("proSale",projectMedicalAdvertisement.getSalesVolume());
              //判断是否有评论
              if(userComment != null) {
                  partarmMap.put("userCommentScore", userComment.getScore());
                  partarmMap.put("userCommentContent", userComment.getComments());
                  partarmMap.put("userPhotoA", userComment.getPictureA());
                  partarmMap.put("userPhotoB", userComment.getPictureB());
                  partarmMap.put("userPhotoC", userComment.getPictureC());
                  partarmMap.put("userPhotoD", userComment.getPictureD());
                  partarmMap.put("userPhotoE", userComment.getPictureE());
              }else {
                  //如果没有，返回noUserComment给前端判断
                  partarmMap.put("msg","noUserComment");
              }
              //当状态值等于‘就诊中’ 或者‘待就诊（预约）’ 时，查询 就诊门诊和门诊地址
               if(status[0]  == 3 ){
                  ProjectBranch projectBranch = projectBranchService.getOneBranchById(account.getBranchId());
                  partarmMap.put("branchName",projectBranch.getStoreName());
                  partarmMap.put("branchAddress",projectBranch.getAddress());
               }
               //判断是否已确定就诊时间
               if(account.getMedicalTime() != null){
                   ProjectBranch projectBranch = projectBranchService.getOneBranchById(account.getBranchId());
                   partarmMap.put("branchName",projectBranch.getStoreName());
                   partarmMap.put("branchAddress",projectBranch.getAddress());
                   partarmMap.put("startTime",account.getMedicalTime());
               }else {
                   //如果没有，返回noMedTime给前端判断
                   partarmMap.put("msg","noMedTime");
               }

              msgList.add(partarmMap);
           }
          return msgList;
      }

      //当医生点击 ‘我的’--‘诊疗项目’时，显示所有该医生负责的项目
        @RequestMapping("/getOneDoctorAllProject")
        private List<ProjectMedicalAdvertisement> getOneDoctorAllProject(Integer doctorId){
             List<DoctorProject> proIdList = doctorProjectService.selectProFormDoctorById(doctorId);
             List<ProjectMedicalAdvertisement> projectMedicalAdvertisements = new ArrayList<>();
             for (DoctorProject doctorProject:proIdList) {
                ProjectMedicalAdvertisement projectMedicalAdvertisement = projectMedicalAdvertisementService.findJiBen(doctorProject.getDoctorProjectId());
                projectMedicalAdvertisements.add(projectMedicalAdvertisement);
             }
             return projectMedicalAdvertisements;
        }

        //加载一个 大门诊下所有医生
        @RequestMapping("getBranchAllDoctor")
        private List<Map<String,Object>> getBranchAllDoctor(Integer outPreId,Integer branchId){
            List<Doctor> doctorAllList = doctorService.getOutpatientPresentationsDoctor(outPreId,branchId);
            //获取医生坐诊列表
            List<Map<String,Object>> paramList = new ArrayList<>();
            if(doctorAllList.size() != 0){
                for (Doctor doctor:doctorAllList) {
                    Map<String,Object> paramterMap = new HashMap<>();
                    Integer branchId1 = doctorBranchService.getOneDoctorWorkPlace(doctor.getId());
                    if(branchId1 == null){
                        paramterMap.put("workPlaceName","暂无");
                    }else{
                        ProjectBranch projectBranch =  projectBranchService.getOneBranchById(branchId1);
                        paramterMap.put("workPlaceName",projectBranch.getStoreName());
                    }
                    paramterMap.put("id",doctor.getId());
                    paramterMap.put("doctorAvator",doctor.getDoctorAvator());
                    paramterMap.put("doctorName",doctor.getDoctorName());
                    paramterMap.put("doctorEducation",doctor.getDoctorEducation());
                    paramterMap.put("doctorTag",doctor.getDoctorTag());
                    paramterMap.put("sex",doctor.getSex());
                    paramterMap.put("doctorJob",doctor.getDoctorJob());
                    paramterMap.put("doctorWorkYear",doctor.getDoctorWorkYear());
                    paramterMap.put("communityCount",doctor.getCommunityCount());
                    paramterMap.put("answerCount",doctor.getAnswerCount());
                    Integer fansCount = doctorConcernService.getDoctorConcernedList(doctor.getId()).size();
                    paramterMap.put("fansCount",fansCount);
                    Integer caseCount = doctorCaseService.getOneDoctorAllCase(doctor.getId()).size();
                    paramterMap.put("caseCount",caseCount);

                    paramList.add(paramterMap);
                }
            }
          return  paramList;
        }


      //门诊端 根据条件查找医生
      @RequestMapping("BranchGetAllDoctorByCondition")
      private Object BranchGetAllDoctorByCondition(String education,Integer beforeWorkYear,Integer lastWorkDate,String major,Integer sex,String orderBy){
         List<Doctor> doctors = doctorService.branchGetAllDoctor(education, beforeWorkYear, lastWorkDate, major, sex, orderBy);
          for (Doctor doctor:doctors
               ) {
              System.out.println(doctor.getDoctorName());
          }
         return doctors;
      }


    //门诊端删除医生
      @RequestMapping("delBranchDoctor")
      private  Integer delBranchDoctor(Integer doctorId,@RequestParam(defaultValue = "0") Integer branchId,@RequestParam(defaultValue = "0")Integer outPrsId){
          return  doctorBranchService.delDoctorInBranch(doctorId, branchId, outPrsId);
      }

      //用户端 查询单个项目下可以做该项目的医生详情信息
    @RequestMapping("findOneProjectDoctor")
    private  List<Map<String,Object>> findOneProjectDoctor(Integer articlesId){
        List<Map<String,Object>> paramterList = doctorService.findOneProjectDoctor(articlesId);
        return paramterList;
    }

    //当医生想改变自己的名称和手机号时
    @RequestMapping("/updateDoctorPhoneOrName")
    public Integer updateDoctorPhoneOrName( String name, BigInteger phone, Integer id) throws IOException {
        Integer num = doctorService.updateDoctorAvatorOrName(null,name,phone,id);
        if(num != 0){
            doctorService.updateDoctorOtherTablesMsg(null,name,id);
            wxChat.updateUserData(id.toString(),name,null,null,null);
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        return num;
    }

    //当医生想改变自己的头像时
    @RequestMapping("/updateDoctorAvator")
    public Integer updateDoctorAvator(MultipartFile path1, Integer id) throws IOException {
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
        Integer num =doctorService.updateDoctorAvatorOrName(txPath,null,null,id);
        if(num != 0){
            doctorService.updateDoctorOtherTablesMsg(txPath,null,id);
            wxChat.updateUserData(id.toString(),null,txPath,null,null);
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        return num;
    }

    //当医生点击 ‘我的门诊’时
    @RequestMapping("getOneDoctorAllOutPre")
    private List<Map<String,Object>> getOneDoctorAllOutPre(Integer doctorId){
          return doctorService.getOneDoctorAllOutPre(doctorId);
    }

}
