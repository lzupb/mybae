package com.baidu.mywork.entity;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class MyNamingStrategy extends ImprovedNamingStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2652375716001578722L;

	@Override
	public String tableName(String tableName) {
		// TODO Auto-generated method stub
		return addUnderscores(tableName).toUpperCase();
	}
	
	

}
