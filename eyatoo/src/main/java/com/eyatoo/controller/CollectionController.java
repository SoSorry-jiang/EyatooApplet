package com.eyatoo.controller;

import com.eyatoo.pojo.UserCollection;
import com.eyatoo.service.ProjectMedicalAdvertisementService;
import com.eyatoo.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CollectionController{
    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

    //加载每个商品的收藏总数-----这个方法应该放在首页里面
    @RequestMapping("/loginCollection")
    public Map<String,Integer> loginCollection(){
        List<Integer> CommoditList = projectMedicalAdvertisementService.getAllProjectId();
        Map<String,Integer> ComoditCountList = new HashMap<>();
        for (Integer commodid:CommoditList){
            Integer oneCount = userCollectionService.getCountCollectedById(commodid);
            ComoditCountList.put("Id",commodid);
            ComoditCountList.put("Count",oneCount);
        }
        return ComoditCountList;
    }

    //查询单个商品的收藏总数
    @RequestMapping("/getOneCollectionCount")
    public Integer getOneCollectionCount(Integer id){
        Integer num = userCollectionService.getCountCollectedById(id);
        System.out.println(num);
        return num;
    }

    //查询用户是否已经收藏该商品
    @RequestMapping("/loginUserCollected")
    public Integer loginUserCollected(Integer collectedUserId,
                                      Integer collectedArticlesId){
       return userCollectionService.findUserIsCollectedPro(collectedUserId, collectedArticlesId);
    }


    //用户收藏商品触发
    @RequestMapping("/userIsCollection")
    public Integer userIsCollection(UserCollection user_collection){
            Integer isOK = userCollectionService.addCollection(user_collection);
        return isOK;
    }

    //用户取消收藏商品时触发
    @RequestMapping("/userDisCollection")
    public Integer userDisCollection(UserCollection user_collection){
        Integer isOK = userCollectionService.delCollection(user_collection);
        return isOK;
    }
}
