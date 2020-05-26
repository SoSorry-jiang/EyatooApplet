package com.eyatoo.controller;

import com.eyatoo.wxutil.RandomNum;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class sendMsgController {

     //注册时发送手机验证码
     @RequestMapping("/sendMsg")
     public Map<String,Object> sendMsg(BigInteger phone, HttpServletRequest request, HttpServletResponse response){
         Map<String,Object> map = new HashMap<>();
         YunpianClient clnt = new YunpianClient("b2b68ac1cff9f0c68a2681e2d5a35a26").init();
         String randomNum = RandomNum.createRandNum();
         //发送短信API
//         Map<String, String> param = clnt.newParam(3);
//         param.put(YunpianClient.MOBILE,phone.toString());
//         param.put(YunpianClient.TEXT, "【贵州益牙兔口腔门诊部有限公司】您的验证码是"+randomNum+"。如非本人操作，请忽略本短信");
//         param.put(YunpianClient.DURATION,"60");
//         Result<SmsSingleSend> r = clnt.sms().single_send(param);
//         System.out.println("返回结果"+r);
         clnt.close();
//         System.out.println(randomNum);
//         HttpSession session = request.getSession(true);
//         session.setAttribute("Code",randomNum);
         Cookie cookie = new Cookie("Code",randomNum);
         cookie.setMaxAge(60*10);
         Cookie cookie2 = new Cookie("phone",phone.toString());
         cookie2.setMaxAge(60*10);
         response.addCookie(cookie);
         response.addCookie(cookie2);
//       this.removeAttrbute(cookie,response);
//       this.removeAttrbute(cookie,response,randomNum);
         map.put("Code",randomNum);
         map.put("Phone",phone.toString());
         return map;
}

     //验证手机验证码
//     @RequestMapping("/yzMsg")
//    public String yzMsg(String yzMsg, HttpServletRequest request,HttpServletResponse response){
//         Cookie[] cookies = request.getCookies();
//         for(Cookie cookie : cookies){
//             if(cookie.getName().equals("Code")){
//                 if(cookie.getValue() == null){
//                     return "false";
//                 }
//                 if(cookie.getValue().equals(yzMsg)) {
//                     return "SUCCESS";
//                 }
//             }
//         }
//         return "false";
//     }

     //定时注销手机验证码
//    private void removeAttrbute(final Cookie cookie,final HttpServletResponse response) {
//        final Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // 删除session中存的验证码
//                if(cookie.getName().equals("Code")) {
//                    cookie.setMaxAge(0);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                    System.out.println("注销成功！" + cookie.getValue());
//                }
//                timer.cancel();
//            }
//        },10 * 1000);
//    }

}
