package com.vieted.android.app.domain;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/8/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Unit extends VietEdDomain {
    //TODO: need a name???
    protected String name;
    protected byte percentCompleted;
    protected byte scored;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getPercentCompleted() {
		return percentCompleted;
	}
	public void setPercentCompleted(byte percentCompleted) {
		this.percentCompleted = percentCompleted;
	}
	public byte getScored() {
		return scored;
	}
	public void setScored(byte scored) {
		this.scored = scored;
	}
    
    
    
}
