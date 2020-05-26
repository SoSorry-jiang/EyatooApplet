package com.eyatoo.utils;

import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//处理文件的通用类
public class FileUtil {

    //删除文件
    public static int delFile (String path) {
        int num = 0;
        File file=new File(path);
        if(file.exists() && file.isFile()){
            if (file.delete()){
                return num = 1;
            }else {
                return num = 0;
            }
        }
        return num;
    }
}
