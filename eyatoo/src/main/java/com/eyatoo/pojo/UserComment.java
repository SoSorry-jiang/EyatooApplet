package com.eyatoo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//用户评论表
public class UserComment {
    private  Integer id;
    private  Integer commentUserId;
    private  Integer commentArticlesId;
    private Integer commentDoctorId;
    private String userAvator;
    private String userName;
    private  Double score;
    private  String comments;
    private Date createTime;
    private Date lastUpdateTime;
    private String pictureA;
    private String pictureB;
    private String pictureC;
    private String pictureD;
    private String pictureE;

    public Integer getCommentDoctorId() {
        return commentDoctorId;
    }

    public void setCommentDoctorId(Integer commentDoctorId) {
        this.commentDoctorId = commentDoctorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(String userAvator) {
        this.userAvator = userAvator;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCommentUserId() {
        return commentUserId;
    }
    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }
    public Integer getCommentArticlesId() {
        return commentArticlesId;
    }
    public void setCommentArticlesId(Integer commentArticlesId) {
        this.commentArticlesId = commentArticlesId;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getPictureA() {
        return pictureA;
    }

    public void setPictureA(String pictureA) {
        this.pictureA = pictureA;
    }

    public String getPictureB() {
        return pictureB;
    }

    public void setPictureB(String pictureB) {
        this.pictureB = pictureB;
    }

    public String getPictureC() {
        return pictureC;
    }

    public void setPictureC(String pictureC) {
        this.pictureC = pictureC;
    }

    public String getPictureD() {
        return pictureD;
    }

    public void setPictureD(String pictureD) {
        this.pictureD = pictureD;
    }

    public String getPictureE() {
        return pictureE;
    }

    public void setPictureE(String pictureE) {
        this.pictureE = pictureE;
    }

    public Double getScore() {
        return score;
    }
}
