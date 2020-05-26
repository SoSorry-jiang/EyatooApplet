package com.eyatoo.pojo;

import java.util.Date;

public class DoctorCommunityCommentReply {
    private Integer id;
    private Integer communityId;
    private Integer userId;
    private Integer doctorId;
    private String userName;
    private String content;
    private Integer replyId;
    private Integer replyReplyId;
    private Date createTime;
    private Date LastUpdateTime;

    private Integer isPraise;

    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getReplyReplyId() {
        return replyReplyId;
    }

    public void setReplyReplyId(Integer replyReplyId) {
        this.replyReplyId = replyReplyId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return LastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        LastUpdateTime = lastUpdateTime;
    }

}
