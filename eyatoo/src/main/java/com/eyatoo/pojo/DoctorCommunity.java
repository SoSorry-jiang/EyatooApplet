package com.eyatoo.pojo;

import java.math.BigInteger;
import java.util.Date;

public class DoctorCommunity {
    private  Integer id;
    private  Integer doctorId;
    private String doctorName;
    private String doctorAvator;
    private String title;
    private String pictureA;
    private String pictureB;
    private String pictureC;
    private String pictureD;
    private String pictureE;
    private String contentA;
    private String contentB;
    private String contentC;
    private String contentD;
    private BigInteger praisePoints;
    private String projectTagId;
    private BigInteger browseVolume;
    private Date createTime;
    private Date lastUpdateTime;

    private Integer commentCount;

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public BigInteger getBrowseVolume() {
        return browseVolume;
    }

    public void setBrowseVolume(BigInteger browseVolume) {
        this.browseVolume = browseVolume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAvator() {
        return doctorAvator;
    }

    public void setDoctorAvator(String doctorAvator) {
        this.doctorAvator = doctorAvator;
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


    public BigInteger getPraisePoints() {
        return praisePoints;
    }

    public void setPraisePoints(BigInteger praisePoints) {
        this.praisePoints = praisePoints;
    }

    public String getProjectTagId() {
        return projectTagId;
    }

    public void setProjectTagId(String projectTagId) {
        this.projectTagId = projectTagId;
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
}
