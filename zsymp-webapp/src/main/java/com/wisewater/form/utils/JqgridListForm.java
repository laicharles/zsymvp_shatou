package com.wisewater.form.utils;

import java.io.Serializable;
import java.util.List;

import com.wisewater.base.BaseForm;
//列表式的信息
public class JqgridListForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* 当前页数 */
	private int page;
	
	/* 总页数 */
	private int total;
	/* 总计录数  */
	private long records;
	
	private List<? extends BaseForm> formList;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public long getRecords() {
		return records;
	}
	public void setRecords(long records) {
		this.records = records;
	}
	public List<? extends BaseForm> getFormList() {
		return formList;
	}
	public void setFormList(List<? extends BaseForm> formList) {
		this.formList = formList;
	}

	
	

}
