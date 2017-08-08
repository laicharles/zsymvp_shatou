package com.wisewater.weixin.pojo;

public class DetailedBindUserForm {
	
	private String hno;   //户号
	private String name;  //户名
	private String addr;  //地址
	private String wide;  //口径
	private String manu;  //表型
	private String wm;    //外码
	private String pdate; //安装日期
	private String usort; //用水性质
	
	public DetailedBindUserForm(){}
	public DetailedBindUserForm(String hno, String name, String addr,
			String wide, String manu, String wm, String pdate, String usort) {
		super();
		this.hno = hno;
		this.name = name;
		this.addr = addr;
		this.wide = wide;
		this.manu = manu;
		this.wm = wm;
		this.pdate = pdate;
		this.usort = usort;
	}
	
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getWide() {
		return wide;
	}
	public void setWide(String wide) {
		this.wide = wide;
	}
	public String getManu() {
		return manu;
	}
	public void setManu(String manu) {
		this.manu = manu;
	}
	public String getWm() {
		return wm;
	}
	public void setWm(String wm) {
		this.wm = wm;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public String getUsort() {
		return usort;
	}
	public void setUsort(String usort) {
		this.usort = usort;
	}
	
}
