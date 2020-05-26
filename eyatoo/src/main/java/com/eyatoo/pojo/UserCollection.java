package com.eyatoo.pojo;

import java.util.Date;


//用户收藏表
public class UserCollection {
    private Integer id;
    private Integer collectedUserId;
    private Integer collectedArticlesId;
    private Date createTime;
    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollectedUserId() {
        return collectedUserId;
    }

    public void setCollectedUserId(Integer collectedUserId) {
        this.collectedUserId = collectedUserId;
    }

    public Integer getCollectedArticlesId() {
        return collectedArticlesId;
    }

    public void setCollectedArticlesId(Integer collectedArticlesId) {
        this.collectedArticlesId = collectedArticlesId;
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
}
