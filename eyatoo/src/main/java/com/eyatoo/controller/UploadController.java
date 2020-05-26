package com.eyatoo.controller;

import com.eyatoo.config.FTPConfig;
import com.eyatoo.utils.Random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    private  FTPConfig ftpConfig;



   @RequestMapping("fileUpload")
   @ResponseBody
    private String  fileUpload(MultipartFile path,String FolderName) throws IOException {
       String b = null;
           if (!path.isEmpty()) {
           //使用随机数将上传的文件名称修改
           String FileName = RandomUtil.number() + ".jpg";
           //上传的保存路径
           String filePath = ftpConfig.getServerPath() + FolderName;
           //封装上传文件位置的全路径
           File targetFile = new File(filePath, FileName);
           //把本地文件上传到封装上传文件位置的全路径
               path.transferTo(targetFile);
           b = ftpConfig.getImageBaseUrl() + FolderName +"/"+ FileName;

       }
       return b;
   }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(String filePath,String FolderName) throws IOException {
        //取得当前主机存放项目的绝对路径
        String workPath = ftpConfig.getServerPath();
        //得到文件名
        String fileUrl = filePath.toString();
        int beginIndex = fileUrl.lastIndexOf("/");
        int endIndex = fileUrl.length();
        String substring = fileUrl.substring(beginIndex + 1, endIndex);
        System.out.println(substring);
        //获得文件存放的绝对路径
        String fullFilePath = workPath
                + FolderName+"\\"
                + substring;
        System.out.println(fullFilePath);
        //删除文件
        File deleteFile = new File(fullFilePath);
        if (deleteFile.exists() && deleteFile.isFile()) {
            deleteFile.delete();
            return "成功";
        }
        return "失败";
   }

}
