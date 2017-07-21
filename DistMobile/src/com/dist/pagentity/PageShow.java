package com.dist.pagentity;
public class PageShow extends BasePageShow implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2436489653398584975L;	
	
	public final static int ALL=-1;	
	public final static int PENDING=0;	//待审核的
	public final static int NORMAL=1;	
	public final static int LOSE=2;	
	public final static int FORBIDDEN=3;	
//	记录设备状态的属性
	private long pending;//0
	private long normal;//1
	private long lose;//2
	private long forbidden;//3
	

	




	public long getPending() {
		return pending;
	}

	public void setPending(long pending) {
		this.pending = pending;
	}

	public long getNormal() {
		return normal;
	}

	public void setNormal(long normal) {
		this.normal = normal;
	}

	public long getLose() {
		return lose;
	}

	public void setLose(long lose) {
		this.lose = lose;
	}

	public long getForbidden() {
		return forbidden;
	}

	public void setForbidden(long forbidden) {
		this.forbidden = forbidden;
	}

	
}
