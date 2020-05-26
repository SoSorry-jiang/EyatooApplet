package com.eyatoo.pojo;

import java.util.List;

public class Page {
	//每页显示的行数
	private Integer size = 4;
	//总页数
    private Integer totalPage;
	//起始页数
    private Integer start;
	//总数据
    private Integer totalCount;
	//每页显示的数据
    private List<?> allMsg;
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		if(totalCount != null){
		this.totalCount = totalCount;
		this.totalPage = this.totalCount % this.size==0 ? this.totalCount / this.size:this.totalCount/this.size +1;
		}
	}
	public List<?> getAllMsg() {
		return allMsg;
	}
	public void setAllMsg(List<?> allMsg) {
		this.allMsg = allMsg;
	}
}
