package com.baidu.mywork.model;

public class IpDictBean {

	private final Long id;

	public IpDictBean(Long id) {
		this.id = id;
	}

	private String name;

	private String serverIp;

	private String clientIpRange;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getId() {
		return id;
	}

}
