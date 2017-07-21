package com.dist.service;

import com.dist.entity.SSystemuser;
import com.dist.entity.SUsers;

public interface SystemuserServiceI extends BasicServiceI<SSystemuser>{
	public SSystemuser login(SSystemuser suser);
}
