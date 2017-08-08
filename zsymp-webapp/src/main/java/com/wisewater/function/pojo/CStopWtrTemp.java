package com.wisewater.function.pojo;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.system.pojo.SDictionary;


/**
 * The persistent class for the c_stop_wtr_temp database table.
 * 
 */
@Entity
@Table(name="c_stop_wtr_temp")
@NamedQuery(name="CStopWtrTemp.findAll", query="SELECT c FROM CStopWtrTemp c")
public class CStopWtrTemp extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String area;

	private String stopDuration;

	private String first;

	private String headColor;

	private int isLogicDel;

	private String remarks;

	private String templateCode;

	private String templateID;

	private String templateName;

	private String tempRemark;

	private String textColor;

	private String token;

	private String url;

	private String why;
	
	private int isPrototype;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date sendDateTime;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sendStatus", referencedColumnName="logicID")
    private SDictionary sendStatus;
    
	public CStopWtrTemp() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStopDuration() {
        return stopDuration;
    }

    public void setStopDuration(String stopDuration) {
        this.stopDuration = stopDuration;
    }

    public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getHeadColor() {
		return this.headColor;
	}

	public void setHeadColor(String headColor) {
		this.headColor = headColor;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTemplateCode() {
		return this.templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateID() {
		return this.templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTempRemark() {
		return this.tempRemark;
	}

	public void setTempRemark(String tempRemark) {
		this.tempRemark = tempRemark;
	}

	public String getTextColor() {
		return this.textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWhy() {
		return this.why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

    public int getIsPrototype() {
        return isPrototype;
    }

    public void setIsPrototype(int isPrototype) {
        this.isPrototype = isPrototype;
    }
    
    public Date getSendDateTime() {
        return this.sendDateTime;
    }

    public void setSendDateTime(Date sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public SDictionary getSendStatus() {
        return this.sendStatus;
    }

    public void setSendStatus(SDictionary sendStatus) {
        this.sendStatus = sendStatus;
    }
}