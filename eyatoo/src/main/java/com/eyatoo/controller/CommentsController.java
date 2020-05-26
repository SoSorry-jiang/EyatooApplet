package com.eyatoo.controller;
import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.User;
import com.eyatoo.pojo.UserComment;
import com.eyatoo.service.AccountsService;
import com.eyatoo.service.UserCommentService;
import com.eyatoo.service.UserService;
import com.eyatoo.utils.Random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentsController {

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private UserCommentService userCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountsService accountsService;

    //点击单一商品进去跳进去时，查看该商品的评论
    @RequestMapping("/findOneComments")
    public  Map<String,Object>  findOneComments(Integer articles_id){
        List<UserComment> ucommentsList = userCommentService.getAllCommentsByAaticlesId(articles_id);
        Map<String,Object> parameterList = new HashMap<>();
        if (ucommentsList != null) {

            parameterList.put("ucommentsList", ucommentsList);
            parameterList.put("msg","success");
        }else{
            System.out.println("该商品暂无评论！");
            parameterList.put("msg","false");
        }
      return parameterList;
    }

    //用户评论商品时添加评论
    @RequestMapping("/addComment")
    public Integer userCommented(UserComment user_comment,Integer status,String accId) throws IOException {
        //上传图片
        User user = userService.findById(user_comment.getCommentUserId());
        user_comment.setUserAvator(user.getTxPhoto());
        user_comment.setUserName(user.getName());
        if(user_comment.getPictureA().equals("undefined"))
            user_comment.setPictureA(null);
        if(user_comment.getPictureB().equals("undefined"))
            user_comment.setPictureB(null);
        if(user_comment.getPictureC().equals("undefined"))
            user_comment.setPictureC(null);
        if(user_comment.getPictureD().equals("undefined"))
            user_comment.setPictureD(null);
        if(user_comment.getPictureE().equals("undefined"))
            user_comment.setPictureE(null);

        Integer isOk =  userCommentService.addUserComments(user_comment);
        if(isOk != 0){
            if(status == 4){
               accountsService.updateAccountsStatus(accId,5);
            }
            else if(status == 5){
                accountsService.updateAccountsStatus(accId,6);
            }
            System.out.println("评论成功！");
            //评论成功时执行的操作，还没完成
        }else{
            System.out.println("出现未知错误！");
        }
        return  isOk;
    }

    //删除评论
    @RequestMapping("/delComment")
    public Integer delComment(Integer[] id){
        Integer num = 0;
        for (Integer ids:id) {
         num  = userCommentService.deleteComments(ids);
        }
        return num;
    }
}
