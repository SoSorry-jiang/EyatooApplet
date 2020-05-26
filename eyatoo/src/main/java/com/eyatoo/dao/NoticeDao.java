package com.eyatoo.dao;

import com.eyatoo.pojo.Notice;

import java.util.List;

public interface NoticeDao {
    //添加一条通知
    Integer addOneNotice(Notice notice);
    //查找所有通知
    List<Notice> getAllNotic();
}
