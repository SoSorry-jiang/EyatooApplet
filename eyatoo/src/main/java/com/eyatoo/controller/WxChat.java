package com.eyatoo.controller;

import com.eyatoo.pojo.User;
import com.eyatoo.service.UserService;
import com.eyatoo.utils.HttpClientUtil;
import com.eyatoo.utils.Random.RandomUtil;
import com.eyatoo.utils.TLSSigAPIv2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@RestController
public class WxChat {
    public static final int SDKAPPID = 1400321201;
    private static final long EXPIRETIME = 604800;
    private static final String SECRETKEY = "c19d8a7378920fb6deb0b1421beedb2994d267f5b3d072e5e1b842197039870a";
    private String identifier = "administrator";
    //获取UserSig工具类
    TLSSigAPIv2 tlsSigAPIv2 = new TLSSigAPIv2(SDKAPPID,SECRETKEY);
    //已获取到的UserSig
    private String adminUserSig="eJwtzLEOgjAYBOB36WzI39aaSuKiTohhQAkrsUX-IFjaiqjx3SXAeN9d7ktOcRp02pKQsADIYsyodOOxxJELVWODztvCP*w8cKoqjEFFQroE4IwyoFOje4NWDy6EYAAwqcd6NMmE4Iyv5xe8Dv-5UVEa39rz-VVFScK7g4x8H7n9p92u0ncOmcz0jptnd9mQ3x9WuzUl";

    private ThreadController threadController = new ThreadController();

    @Autowired
    private UserService userService;

    @RequestMapping("testChat")
    private void  test(Object object) throws Exception{
        List<User> userList = new ArrayList<>();
        Thread thread1 = new Thread("线程1"){
            public void run(){
                User user = new User();
                user.setId(1923698163);
                user.setName("giaoge");
                user.setOpenId("adagqeasagqw");
                user.setTxPhoto("giasidaboq");
                user.setPhone(new BigInteger("123151231"));
                synchronized (userList){
                    userList.add(user);
                    System.out.println(Thread.currentThread().getName()+"已处理数据并添加进数组了");
                    try {
                        userList.notify();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread2 = new Thread("线程2"){
            public void run(){
                synchronized (userList){
                    while (userList.size() == 0){
                        try {
                            userList.wait();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Integer isOK = userService.addUser(userList.get(0));
                     System.out.println(Thread.currentThread().getName()+"从数据库取出的数据为：‘"+isOK+"’");
                }
            }
        };
        thread1.start();
        thread2.start();
    }

    //根据用户id获取userSig
    @RequestMapping("getUserSig")
    private String getUserSig(String userId){
        String userSig = tlsSigAPIv2.genSig(userId,EXPIRETIME);
        return userSig;
    }

    //注册聊天用户
    @RequestMapping("addChatUser")
    public String addChatUser(String userId,String nickName,String FaceUrl){
        String random = RandomUtil.LetterNumber();
        Map<String,Object> map = new HashMap<>();
        //用户名
        map.put("Identifier",userId);
        map.put("Nick",nickName);
        map.put("FaceUrl",FaceUrl);
        JSONObject jsonObject = new JSONObject(map);

        String url = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?sdkappid="+SDKAPPID+
                "&identifier="+identifier +"&usersig="+adminUserSig+"&random="+random+"&contenttype=json";
        String result = HttpClientUtil.doPostJson(url,jsonObject.toString());
        System.out.println(result);
        return result;
    }

    //设置用户资料
    @RequestMapping("updateUserData")
    public String updateUserData(String userId,String NickName,String FaceUrl,Integer job,Integer level){
        String random = RandomUtil.LetterNumber();
        Map<String,Object> map = new HashMap<>();
        //设置修改的用户id
        map.put("From_Account",userId);
           //设置需要修改的字段list
           List<Map<String,Object>> dataList = new ArrayList<>();
           //昵称
           if(NickName != null){
               Map<String,Object> dataMap = new HashMap<>();
               dataMap.put("Tag","Tag_Profile_IM_Nick");
               dataMap.put("Value",NickName);
               dataList.add(dataMap);
           }
           //头像
           if(FaceUrl != null) {
               Map<String, Object> dataMap2 = new HashMap<>();
               dataMap2.put("Tag", "Tag_Profile_IM_Image");
               dataMap2.put("Value", FaceUrl);
               dataList.add(dataMap2);
           }
           //所属类型 0：User Or 1：doctor 2：门诊
           if(job != null) {
               Map<String, Object> dataMap3 = new HashMap<>();
               dataMap3.put("Tag", "Tag_Profile_IM_Role");
               dataMap3.put("Value", job);
               dataList.add(dataMap3);
           }
           //用户经纪人等级
           if(level != null) {
               Map<String, Object> dataMap4 = new HashMap<>();
               dataMap4.put("Tag", "Tag_Profile_IM_Level");
               dataMap4.put("Value", level);
               dataList.add(dataMap4);
           }
        map.put("ProfileItem",dataList);
        JSONObject jsonObject = new JSONObject(map);
        String url = "https://console.tim.qq.com/v4/profile/portrait_set?sdkappid="+SDKAPPID+
                "&identifier="+identifier +"&usersig="+adminUserSig+"&random="+random+"&contenttype=json";
        String result = HttpClientUtil.doPostJson(url,jsonObject.toString());
        System.out.println(jsonObject.toString());
        System.out.println(result);
        return result;
    }


    //获取用户在线状态
    @RequestMapping("loginUserStatus")
    public String loginUserStatus(String[] userId){
        String random = RandomUtil.LetterNumber();
        Map<String,Object> map = new HashMap<>();
//        String[] userId = new String[100];
//        userId[0] = "supperBoy";
//        userId[1] = "大帅比";
        map.put("To_Account",userId);
        JSONObject jsonObject = new JSONObject(map);
        String url = "https://console.tim.qq.com/v4/openim/querystate?sdkappid="+SDKAPPID+"&identifier="+
                identifier+"&usersig="+adminUserSig+"&random="+random+"&contenttype=json";
        String result = HttpClientUtil.doPostJson(url,jsonObject.toString());
        return result;
    }

    //检查账号是否已经导入进
    @RequestMapping("checkUser")
    public String checkUser(String[] userId) throws Exception{
        String random = RandomUtil.LetterNumber();
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> userList = new ArrayList<>();

        for (Integer i=0;i<userId.length;i++) {
            Map<String,Object> userIdMap = new HashMap<>();
            userIdMap.put("UserID",userId[i]);
            userList.add(userIdMap);
        }
        map.put("CheckItem",userList);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toString());
        String url = "https://console.tim.qq.com/v4/im_open_login_svc/account_check?sdkappid="+SDKAPPID+"&identifier="+
                identifier+"&usersig="+adminUserSig+"&random="+random+"&contenttype=json";
        String result = HttpClientUtil.doPostJson(url,jsonObject.toString());
        return result;
    }

    //读取在聊天系统里的用户资料
    @RequestMapping("loginUserData")
    public String loginUserData(String[] userids){
        String random = RandomUtil.LetterNumber();
        Map<String,Object> map = new HashMap<>();
        List<String> userIdList = new ArrayList<>();
        List<String> userNeedMsgList = new ArrayList<>();
        for (String userId:userids){
            userIdList.add(userId);
        }
        userNeedMsgList.add("Tag_Profile_IM_Nick");
        userNeedMsgList.add("Tag_Profile_IM_Image");
        userNeedMsgList.add("Tag_Profile_IM_Level");
        userNeedMsgList.add("Tag_Profile_IM_Role");

        map.put("TagList",userNeedMsgList);
        map.put("To_Account",userIdList);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toString());
        String url = "https://console.tim.qq.com/v4/profile/portrait_get?sdkappid="+SDKAPPID+"&identifier="+
                identifier+"&usersig="+adminUserSig+"&random="+random+"&contenttype=json";
        String result = HttpClientUtil.doPostJson(url,jsonObject.toString());
        return result;
    }

    //单发单聊消息
    @RequestMapping("WxMiniSendMsg")
    public String WxMiniSendMsg(String userId,String sendUserId,String msgType,String Content){
        String random = RandomUtil.LetterNumber();

        Integer msgRandom = RandomUtil.lessNumber();
        //请求包参数数组
        Map<String,Object> map = new HashMap<>();
        map.put("From_Account",userId);
        map.put("To_Account",sendUserId);
        map.put("MsgRandom",msgRandom);
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        map.put("MsgTimeStamp",Integer.parseInt(dateStr));

        List<Map<String,Object>> MsgBody = new ArrayList<>();
        Map<String ,Object> contentMap = new HashMap<>();
        contentMap.put("MsgType",msgType);
          //创建内容参数数组
          if(msgType.equals("TIMTextElem")){
              Map<String,Object> map1 = new HashMap<>();
              map1.put("Text",Content);
              contentMap.put("MsgContent",map1);
          }
//        contentMap.put("MsgContent",textList);

        MsgBody.add(contentMap);
        map.put("MsgBody",MsgBody);

        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toString());
        String url = "https://console.tim.qq.com/v4/openim/sendmsg?sdkappid="+SDKAPPID+"&identifier="+
                identifier+"&usersig="+adminUserSig+"&random="+random+"&contenttype=json";
        String result = HttpClientUtil.doPostJson(url,jsonObject.toString());
        return result;
    }


}
