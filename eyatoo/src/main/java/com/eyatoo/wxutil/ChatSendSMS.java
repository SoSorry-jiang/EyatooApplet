package com.eyatoo.wxutil;

import com.eyatoo.DemoApplication;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.boot.SpringApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ChatSendSMS {

    public static void main(String[] args) {
       //初始化clnt,使用单例方式
//        YunpianClient clnt = new YunpianClient("b2b68ac1cff9f0c68a2681e2d5a35a26").init();
//        String randomNum = RandomNum.createRandNum();
//        //发送短信API
//        Map<String, String> param = clnt.newParam(2);
//        param.put(YunpianClient.MOBILE, "15608505654");
//        param.put(YunpianClient.TEXT, "【贵州益牙兔口腔门诊部有限公司】您的验证码是"+randomNum+"。如非本人操作，请忽略本短信");
//        Result<SmsSingleSend> r = clnt.sms().single_send(param);
//        System.out.println("返回结果"+r);
//        clnt.close();
//        System.out.println(randomNum);
//            try {
//                Thread.sleep(60000);
//                randomNum = null;
//                System.out.println(randomNum);
//                 System.out.println("验证码已过期;");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

       //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*
       //释放clnt


    }


}
