package com.wisewater.util.tools;

import java.io.Serializable;

public class JqgridTreeForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String level;
	private String parent;
	private boolean isLeaf;
	private boolean expanded;
	private boolean loaded;
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public boolean isIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	
	
}
