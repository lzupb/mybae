package com.baidu.mywork.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "LIVECAST_ROOM")
public class LiveRoomDB extends IdTimeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7470191400973002230L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "ROOM_DETAIL", nullable = true)
	private String roomDetail;

	@Column(name = "ROOM_PASSWORD", nullable = true)
	private String roomPassword;

	@Column(name = "MEDIA_KEY", nullable = true)
	private String mediaKey;

	@Column(name = "RTMP_URL", nullable = true)
	private String rtmpUrl;

	@Column(name = "START_TIME", nullable = true)
	private Date startTime;

	@Column(name = "END_TIME", nullable = true)
	private Date endTime;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomDetail() {
		return roomDetail;
	}

	public void setRoomDetail(String roomDetail) {
		this.roomDetail = roomDetail;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}

	public String getMediaKey() {
		return mediaKey;
	}

	public void setMediaKey(String mediaKey) {
		this.mediaKey = mediaKey;
	}

	public String getRtmpUrl() {
		return rtmpUrl;
	}

	public void setRtmpUrl(String rtmpUrl) {
		this.rtmpUrl = rtmpUrl;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
