package com.baidu.livecast.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LIVECAST_IP_DICT")
public class IpDictDB extends IdTimeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8183196837461263009L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "NAME_DETAIL", nullable = true)
	private String nameDetail;

	@Column(name = "SERVER_IP", nullable = false)
	private String serverIp;

	@Column(name = "CLIENT_IP_RANGE", nullable = false)
	private String clientIpRange;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDetail() {
		return nameDetail;
	}

	public void setNameDetail(String nameDetail) {
		this.nameDetail = nameDetail;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getClientIpRange() {
		return clientIpRange;
	}

	public void setClientIpRange(String clientIpRange) {
		this.clientIpRange = clientIpRange;
	}

}
