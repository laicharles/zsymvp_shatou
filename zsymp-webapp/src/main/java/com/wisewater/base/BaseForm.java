package com.wisewater.base;

import java.io.Serializable;

public class BaseForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	/* 提示信息 */
	private String tips;
	
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
}
