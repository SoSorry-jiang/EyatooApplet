package com.eyatoo.controller;


import com.eyatoo.pojo.Notice;
import com.eyatoo.service.NoticeService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("addOneNotice")
    private Integer addOneNotice(Notice notice){
        return  noticeService.addOneNotice(notice);
    }

    @RequestMapping("getAllNotices")
    private List<Notice> getAllNotices(){
        return noticeService.getAllNotic();
    }
}
