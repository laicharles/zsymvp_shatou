package com.wisewater.util.tools;

import java.io.Serializable;

/**
 * 
 * @author gawe.chen 描述：json返回结果类
 */
public class ControllerJsonMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean result = false;
	private String warnMsg;
	private String tips;
	// 云识别水表值
	private String cloudValue;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getWarnMsg() {
		return warnMsg;
	}

	public void setWarnMsg(String warnMsg) {
		this.warnMsg = warnMsg;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getCloudValue() {
		return cloudValue;
	}

	public void setCloudValue(String cloudValue) {
		this.cloudValue = cloudValue;
	}

}
