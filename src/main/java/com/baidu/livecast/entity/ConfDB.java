package com.baidu.livecast.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LIVECAST_CONF")
public class ConfDB extends IdTimeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5342255224243103322L;

	@Column(name = "NAME", nullable = false)
	private String key;

	@Column(name = "VALUE", nullable = true)
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
