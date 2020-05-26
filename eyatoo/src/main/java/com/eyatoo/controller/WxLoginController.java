package com.eyatoo.controller;

import com.eyatoo.utils.CommonUtil;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;


@RestController
@RequestMapping("/wxAuth")
public class WxLoginController {
    private static String APPID = "wx1fb7498f5acf11ef";//填你自己的
    private static String APPSECRET = "a5f0cc73d2b8ca36b09fca3171b423a8";//填你自己的

//
//    /**
//     * 用于给微信验证token
//     *
//     * @param request
//     * @param response
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping("/checkToken")
//    public String checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // 微信加密签名
//        String signature = request.getParameter("signature");
//        // 时间戳
//        String timestamp = request.getParameter("timestamp");
//        // 随机数
//        String nonce = request.getParameter("nonce");
//        // 随机字符串
//        String echostr = request.getParameter("echostr");
//
//        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
//            System.out.println("校验token成功");
//            return echostr;
//        } else {
//            System.out.println("校验token不成功");
//            return null;
//        }
//    }

//
//    /**
//     * 用于获取出回调地址  （引导用户调用此接口，成功后自动调取回调地址然后取出用户信息）
//     *
//     * @param response*
//     * @throws IOException
//     */
//    @RequestMapping("/login")
//    public void wxLogin(HttpServletResponse response) throws IOException {
//        //请求获取code的回调地址
//        //用线上环境的域名或者用内网穿透，不能用ip
//        String callBack = "https://eyatoo.cn/eyatoo/wxAuth/callBack";//域名填你自己的
//
//        //请求地址
//        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
//                "?appid=" + APPID +
//                "&redirect_uri=" + URLEncoder.encode(callBack,"UTF-8") +
//                "&response_type=code" +
//                "&scope=snsapi_userinfo" +
//                "&state=STATE#wechat_redirect";
//
//        System.out.println(url);
//        //重定向
//        response.sendRedirect(url);
//    }


//    /**
//     * 回调方法
//     *
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    //	回调方法
//    @RequestMapping("/callBack")
//    public String wxCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String code = request.getParameter("code");
//
//        //获取access_token
//        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
//                "appid=" + APPID +
//                "&secret=" + APPSECRET +
//                "&js_code=" + code +
//                "&grant_type=authorization_code";
//
//        String result = HttpClientUtil.doGet(url);
//        return result;
//    }

    @RequestMapping(value = "/getOpenId", method = RequestMethod.GET) // 获取用户信息
    public Object getOpenId(@RequestParam("code") String code) {
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        try {
            if (StringUtils.isBlank(code)) {
                System.out.println("code为空");
            } else {
                String requestUrl = WX_URL.replace("APPID",APPID).replace("SECRET", APPSECRET)
                        .replace("JSCODE", code).replace("authorization_code","authorization_code");
                JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
                if (jsonObject != null) {
                    try {
                        // 业务操作
                        String openid = jsonObject.getString("openid");
//                        wechatService.selectUserByOpenId(openid, headurl, nickname, sex, country, province, city);
                        return jsonObject;
                    } catch (Exception e) {
                        System.out.println("业务操作失败");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("code无效");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "错误";
    }



    @RequestMapping("/getPhoneNumber")
    public Object getPhoneNumber(String encryptedData, String session_key, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.getDecoder().decode(session_key);
        // 偏移量
        byte[] ivByte = Base64.getDecoder().decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return com.alibaba.fastjson.JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}