package com.eyatoo.service.impl;

import com.eyatoo.dao.NoticeDao;
import com.eyatoo.pojo.Notice;
import com.eyatoo.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDao noticeDao;

    @Override
    public Integer addOneNotice(Notice notice) {
        Integer isOk = 0;
        try {
            isOk = noticeDao.addOneNotice(notice);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public List<Notice> getAllNotic() {
        List<Notice> notices = new ArrayList<>();
        try {
           notices = noticeDao.getAllNotic();
        }catch (Exception e){
            e.printStackTrace();
        }
        return notices;
    }
}
