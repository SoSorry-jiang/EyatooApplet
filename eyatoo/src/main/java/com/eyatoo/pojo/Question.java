package com.eyatoo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "question_tagid")
    private Integer questionTagId;
    @Column(name = "question_content")
    private String questionContent;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_avator")
    private String userAvator;
    @Column(name = "praise_points")
    private BigInteger praisePoints;
    @Column(name="browse_count")
    private BigInteger browseCount;

    public BigInteger getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(BigInteger browseCount) {
        this.browseCount = browseCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(String userAvator) {
        this.userAvator = userAvator;
    }

    public BigInteger getPraisePoints() {
        return praisePoints;
    }

    public void setPraisePoints(BigInteger praisePoints) {
        this.praisePoints = praisePoints;
    }

    public Integer getQuestionTagId() {
        return questionTagId;
    }

    public void setQuestionTagId(Integer questionTagId) {
        this.questionTagId = questionTagId;
    }
}
