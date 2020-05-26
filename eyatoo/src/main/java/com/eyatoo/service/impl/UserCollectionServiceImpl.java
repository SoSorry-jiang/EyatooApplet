package com.eyatoo.service.impl;

import com.eyatoo.dao.UserCollectionDao;
import com.eyatoo.pojo.UserCollection;
import com.eyatoo.service.UserCollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Resource
    private UserCollectionDao userCollectionDao;

    @Override
    public Integer getCountCollectedById(Integer id) {
        Integer num = 0;
        try {
            num = userCollectionDao.getCountCollectedById(id);
            return num;
        }catch (Exception e){
            e.printStackTrace();
            return num;
        }

    }

    @Override
    public Integer addCollection(UserCollection user_collection) {
        Integer isOK = 0;
        try {
         isOK = userCollectionDao.addCollection(user_collection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<Integer> findUserCollectedIdById(Integer id) {
        List<Integer> cidList = null;
        try {
            cidList = userCollectionDao.findUserCollectedIdById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cidList;
    }

    @Override
    public Integer delCollection(UserCollection user_collection) {
        Integer num = 0;
        try {
            num = userCollectionDao.delCollection(user_collection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer findUserIsCollectedPro(Integer collected_user_id, Integer collected_articles_id) {
        Integer isOk = 0;
        try {
            isOk = userCollectionDao.findUserIsCollectedPro(collected_user_id,collected_articles_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
