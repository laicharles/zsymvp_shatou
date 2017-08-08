package com.wisewater.wechatpublic.util;

import java.util.List;


public class PageUtil {
	private int cpage=1;//当前页
	private int showNum=10;//每页显示行数
	private int allNum=0;//总数据条数
	private int allPages=0;//总页数
	private List<?> list;//存放每页要显示的数据
	public PageUtil(int cpage, int showNum, int allNum, int allPages, List<?> list) {
		super();
		this.cpage = cpage;
		this.showNum = showNum;
		this.allNum = allNum;
		this.allPages = allPages;
		this.list = list;
	}
	public PageUtil() {
		
	}
	public int getCpage() {
		return cpage;
	}
	public void setCpage(int cpage) {
		this.cpage = cpage;
	}
	public int getShowNum() {
		return showNum;
	}
	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}
	public int getAllNum() {
		return allNum;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
		if(this.allNum%this.showNum==0){
			this.allPages=this.allNum/this.showNum;
		}else
			this.allPages=this.allNum/this.showNum+1;
	}
	public int getAllPages() {
		return allPages;
	}
	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	
}
