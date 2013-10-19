package com.vieted.android.app.domain;

@Deprecated
public class BaseObject extends VietEdDomain{
	protected String name;
	protected String para2;
	protected float para3;
	
	public String getName() {
		return name;
	}
	public void setName(String para1) {
		this.name = para1;
	}
	public String getPara2() {
		return para2;
	}
	public void setPara2(String para2) {
		this.para2 = para2;
	}
	public float getPara3() {
		return para3;
	}
	public void setPara3(float para3) {
		this.para3 = para3;
	}
	
	

}
