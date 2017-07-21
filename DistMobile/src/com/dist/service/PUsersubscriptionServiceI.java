package com.dist.service;

import com.dist.entity.PUsersubscription;
public interface PUsersubscriptionServiceI extends BasicServiceI<PUsersubscription>{
	public PUsersubscription deleteByParams(String userid,String sourceid);
}
