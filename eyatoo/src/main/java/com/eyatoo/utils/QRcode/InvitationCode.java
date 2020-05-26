//package com.eyatoo.utils.QRcode;
//
//import com.alibaba.fastjson.JSONObject;
//import com.eyatoo.config.FTPConfig;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//import static com.google.zxing.client.j2se.MatrixToImageWriter.writeToPath;
//
///**
// * 邀请码创建类
// */
//public class InvitationCode {
//
//
//    public static void generateQRcode(JSONObject jsonObject, FTPConfig ftpConfig) throws WriterException, IOException {
//        //生成一个二维码
//        //定义一个json格式的字符串,使用fastjson
//        //2.给jsonObject对象中存放数据
//        jsonObject.put("id","1");
//        jsonObject.put("company","5555");
//        jsonObject.put("author","2222");
//        jsonObject.put("address","3333");
//
//        //3.json对象转换成json对象的字符串
//        String content = jsonObject.toString();
//        System.out.println(content);
//
//        //二维码宽高
//        int width = 200;
//        int height = 200;
//
//        //创建Map集合
//        Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType,Object>();
//        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
//        //创建一个矩阵对象
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
//
//
//        //创建随机数避免重复
//        Random dom = new Random();
//        String haomiaoshus = String.valueOf(System.currentTimeMillis());
//
//        String fileName = "QRcode"+dom.nextInt(100)+haomiaoshus+".jpg";
//
//        //创建一个路径对象
//        Path path = FileSystems.getDefault().getPath(filepath,fileName);
//
//        //将矩阵对象生成一个图片
//        MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path);
//
//        System.out.println("成功生成二维码图片");
//    }
//
//    public static String createCode(Integer userId,String phone,FTPConfig ftpConfig) throws Exception{
//        //二维码图片的宽度
//        int width = 300;
//        //二维码图片的高度
//        int height = 300;
//        //二维码图片的格式
//        String format = "jpg";
//
////       String successPath = "https://eyatoo.cn/eyatoo/updateQRcode?userId="+userId+"&path="+filepath;
//        //二维码内容（支持中文），使用微信扫描后可直接跳转到百度
//        String content =  phone;
//        //定义二维码内容参数
//        Map hints = new HashMap();
//        //设置字符集编码格式
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        //设置容错等级，在这里我们使用M级别
//        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
//        //设置边框距
//        hints.put(EncodeHintType.MARGIN, 2);
//
//        //生成二维码
//        try {
//            //生成二维码的内容
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//            //创建随机数避免重复
//            Random dom = new Random();
//            String haomiaoshus = String.valueOf(System.currentTimeMillis());
//            String fileName = "QRcode"+dom.nextInt(100)+haomiaoshus+".jpg";
//            //生成的路径
//            String filepath = ftpConfig.getServerPath()+"\\User\\InviteQRcode"+fileName;
//
//            //指定生成图片的保存路径
//            Path file = new File(filepath).toPath();
//
//            //生成二维码
//            writeToPath(bitMatrix, format, file);
//            System.out.println("二维码生成成功！");
//            return  filepath;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}