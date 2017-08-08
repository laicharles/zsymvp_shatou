package com.wisewater.util.tools;

import java.io.Serializable;

public class ZtreeForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pId;
	private String name;
	private boolean nocheck;   //radio是否选中的属性，与checked是不能同时使用
	private boolean open;
	
	private boolean checked;   //checkbox是否选中时使用的属性
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	
	
	
}
