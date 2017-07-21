package com.dist.pagentity;
public class AppsPageShow extends BasePageShow implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1316383722467906080L;
//	用于记录不同的应用程序类型
	public final static int ALL=0;	
	public final static int ANDROIDALL=1;	//待审核的
	public final static int ANDROIDPHONE=2;	
	public final static int ANDROIDPAD=3;	
	public final static int IOSALL=4;	
	public final static int IOSPHONE=5;	
	public final static int IOSPAD=6;	
		
//	用于记录不同类型应用程序的数量
	private long all;            //0  所有应用程序
	private long androidall;            //1  所有android应用程序
	private long androidphone;            //2  所有android手机应用程序
	private long androidpad;            //3  所有android平板应用程序
	private long iosall;            //4  所有ios应用程序
	private long iosphone;            //5 所有ios手机应用程序
	private long iospad;            //6  所有ios平板应用程序
	public long getAll() {
		return all;
	}
	public void setAll(long all) {
		this.all = all;
	}
	public long getAndroidall() {
		return androidall;
	}
	public void setAndroidall(long androidall) {
		this.androidall = androidall;
	}
	public long getAndroidphone() {
		return androidphone;
	}
	public void setAndroidphone(long androidphone) {
		this.androidphone = androidphone;
	}
	public long getAndroidpad() {
		return androidpad;
	}
	public void setAndroidpad(long androidpad) {
		this.androidpad = androidpad;
	}
	public long getIosall() {
		return iosall;
	}
	public void setIosall(long iosall) {
		this.iosall = iosall;
	}
	public long getIosphone() {
		return iosphone;
	}
	public void setIosphone(long iosphone) {
		this.iosphone = iosphone;
	}
	public long getIospad() {
		return iospad;
	}
	public void setIospad(long iospad) {
		this.iospad = iospad;
	}

	
}
