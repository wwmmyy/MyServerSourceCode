package com.dist.service;

import com.dist.entity.PUsersubscription;
import com.dist.entity.SApplicationpurview;
import com.dist.entity.SDeviceactivity;
import com.dist.entity.SUsers;

public interface SApplicationpurviewServiceI extends BasicServiceI<SApplicationpurview>{
	public String  saveOrUpade(SApplicationpurview user);
	public SApplicationpurview deleteByParams(String userid,String appid);

}
