//package com.eyatoo.controller;
//
//import com.eyatoo.webChat.WebSocket;
//import com.eyatoo.wxutil.FileUtil;
//import com.eyatoo.wxutil.KeyUtil;
//import org.apache.commons.codec.binary.Base64;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.ClassUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//@Controller("/eyatoo")
//public class WebSocketController{
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @RequestMapping("/websocket/{name}")
//    public String webSocket(@PathVariable String name, Model model){
//        try{
//            logger.info("跳转到websocket的页面上");
//            model.addAttribute("username",name);
//            return "webChat";
//        }
//        catch (Exception e){
//            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
//            model.addAttribute("msg",e.getMessage());
//            return "error";
//        }
//    }
////    @RequestMapping("/websocket/upload")
////    @ResponseBody
////    public void upload(@RequestParam("file") MultipartFile file){
////        try {
////            byte[] buffer = file.getBytes();
////            String base64="data:image/jpeg;base64,"+Base64.encodeBase64String(buffer);
////            WebSocket webSocket=new WebSocket();
////            webSocket.onMessage(base64,null);
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////        System.out.println("asdf");
////    }
//
//    //base64图片上传（有问题，fastjson好像不能处理这么长的字符串）
////    @PostMapping("/websocket/questionType/importExcel")
////    @ResponseBody
////    public void uploadExportWord(MultipartFile files) {
////        try {
////            byte[] buffer = files.getBytes();
////            String base64="data:image/jpeg;base64,"+Base64.encodeBase64String(buffer);
////            WebSocket webSocket=new WebSocket();
////            String a="{\n" +
////                    " \"message\":"+base64+",\n" +
////                    " \"username\":\"1\",\n" +
////                    " \"to\":\"1\"\n" +
////                    "}";
////            webSocket.onMessage(a,null);
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////    }
//
//    @PostMapping("/websocket/questionType/importExcel")
//    @ResponseBody
//    public String uploadExportWord(MultipartFile files,@RequestParam("username")String username,@RequestParam("selectText")String selectText) {
//        String fileName = files.getOriginalFilename();
//        fileName=KeyUtil.genUniqueKey()+fileName;
//        /*System.out.println("fileName-->" + fileName);
//        System.out.println("getContentType-->" + contentType);*/
//        String filePath=ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/imgupload/";
//        System.out.println(filePath);
//        //文件上传
//        try {
//            FileUtil.uploadFile(files.getBytes(), filePath, fileName);
//            WebSocket webSocket=new WebSocket();
//            String a="{\"message\":\"/imgupload/"+fileName+"\",\"username\":\""+username+"\",\"to\":\""+selectText+"\",\"type\":1}";
//            webSocket.onMessage(a,null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        String base64="";
//        try {
//            base64="data:image/jpeg;base64,"+Base64.encodeBase64String(files.getBytes());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return base64;
//    }
//
//    @RequestMapping("/websocket/questionType/importAudio")
//    @ResponseBody
//    public void importAudio(@RequestParam("dataurl") String dataurl,@RequestParam("username")String username,@RequestParam("selectText")String selectText) {
//        System.out.println("进图录音方法");
//        dataurl = dataurl.replaceAll("data:audio/mp3;base64,","");
//        byte[] audioByte=Base64.decodeBase64(dataurl);
//        String fileName=KeyUtil.genUniqueKey()+".mp3";
//        String filePath=ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/audioupload/";
//        //文件上传
//        try {
//            FileUtil.uploadFile(audioByte, filePath, fileName);
//            WebSocket webSocket=new WebSocket();
//            String a="{\"message\":\"/audioupload/"+fileName+"\",\"username\":\""+username+"\",\"to\":\""+selectText+"\",\"type\":2}";
//            webSocket.onMessage(a,null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//}
