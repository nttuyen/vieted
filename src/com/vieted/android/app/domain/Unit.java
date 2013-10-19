package com.vieted.android.app.domain;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/8/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class Unit extends VietEdDomain {
    //TODO: need a name???
    protected String name;
    protected int percentCompleted;
    protected float scored;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPercentCompleted() {
		return percentCompleted;
	}
	public void setPercentCompleted(int percentCompleted) {
		this.percentCompleted = percentCompleted;
	}
	public float getScored() {
		return scored;
	}
	public void setScored(float scored) {
		this.scored = scored;
	}
    
    
    
}
