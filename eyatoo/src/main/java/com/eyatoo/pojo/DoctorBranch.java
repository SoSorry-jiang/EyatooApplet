package com.eyatoo.pojo;

import java.util.Date;

public class DoctorBranch {
    private Integer id;
    private Integer doctorId;
    private Integer workPlace;
    private Integer branch_id;
    private Date create_time;


    public Integer getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Integer workPlace) {
        this.workPlace = workPlace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
