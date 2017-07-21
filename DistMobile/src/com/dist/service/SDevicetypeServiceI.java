package com.dist.service;

import java.util.List;

import com.dist.entity.SDevicetype;

public interface SDevicetypeServiceI extends BasicServiceI<SDevicetype>{

	public List<SDevicetype> findByPropertys(String system, String hardware);
	
}
