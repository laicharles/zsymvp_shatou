package com.wisewater.review.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 权限表
 * @author Xiong
 * @date 2017年5月10日 下午2:20:14
 */
@Entity
@Table(name="s_review_permissions")
@NamedQuery(name="SReviewPermissions.findAll", query="SELECT c FROM SReviewPermissions c")
public class SReviewPermissions extends com.wisewater.base.BasePojo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;
	
	private String reviewName;
	
	private int level;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
