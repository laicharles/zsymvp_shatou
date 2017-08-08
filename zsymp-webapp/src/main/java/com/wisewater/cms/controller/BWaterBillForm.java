package com.wisewater.cms.controller;

import com.wisewater.base.BaseForm;

public class BWaterBillForm extends BaseForm {
  
    private static final long serialVersionUID = 1L;
    
    private String chargeDate = null;
    
    private String account = null;
    
    private String chargeContent = null;
    
    private String collectFee = null;
    
    private String totaleFee = null;
    
    private String accountNo = null;
    
    //是否已经缴纳
  	private String ispay;
  	
  	//水费
  	private String water_charge;
  	
	//其他费用
	private String fee_charge;
	
    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getChargeContent() {
        return chargeContent;
    }

    public void setChargeContent(String chargeContent) {
        this.chargeContent = chargeContent;
    }

    public String getCollectFee() {
        return collectFee;
    }

    public void setCollectFee(String collectFee) {
        this.collectFee = collectFee;
    }

    public String getTotaleFee() {
        return totaleFee;
    }

    public void setTotaleFee(String totaleFee) {
        this.totaleFee = totaleFee;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

	public String getIspay() {
		return ispay;
	}

	public void setIspay(String ispay) {
		this.ispay = ispay;
	}

	public String getWater_charge() {
		return water_charge;
	}

	public void setWater_charge(String water_charge) {
		this.water_charge = water_charge;
	}

	public String getFee_charge() {
		return fee_charge;
	}

	public void setFee_charge(String fee_charge) {
		this.fee_charge = fee_charge;
	}

    
    
}