package com.eyatoo.pojo;

import java.math.BigInteger;
import java.util.Date;

public class DoctorAnswer {
    private Integer id;
    private Integer doctorId;
    private Integer questionId;
    private String doctorAvator;
    private String doctorName;
    private String pictureA;
    private String pictureB;
    private String pictureC;
    private String pictureD;
    private String pictureE;
    private String contentA;
    private String contentB;
    private String contentC;
    private String contentD;
    private BigInteger praiseCount;
    private Date createTime;
    private Date lastUpdateTime;

    private Integer countCommunityComment;
    private Integer isConcerned;
    private Integer isPraise;

    public Integer getIsConcerned() {
        return isConcerned;
    }

    public void setIsConcerned(Integer isConcerned) {
        this.isConcerned = isConcerned;
    }

    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public Integer getCountCommunityComment() {
        return countCommunityComment;
    }

    public void setCountCommunityComment(Integer countCommunityComment) {
        this.countCommunityComment = countCommunityComment;
    }

    public BigInteger getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(BigInteger praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorAvator() {
        return doctorAvator;
    }

    public void setDoctorAvator(String doctorAvator) {
        this.doctorAvator = doctorAvator;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getContentA() {
        return contentA;
    }

    public void setContentA(String contentA) {
        this.contentA = contentA;
    }

    public String getContentB() {
        return contentB;
    }

    public void setContentB(String contentB) {
        this.contentB = contentB;
    }

    public String getContentC() {
        return contentC;
    }

    public void setContentC(String contentC) {
        this.contentC = contentC;
    }

    public String getContentD() {
        return contentD;
    }

    public void setContentD(String contentD) {
        this.contentD = contentD;
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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}
