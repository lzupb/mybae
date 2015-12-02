package com.baidu.mywork.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.mywork.entity.LiveRoomDB;
import com.baidu.mywork.model.LiveRoomBean;
import com.baidu.mywork.repository.ILiveRoomDAO;

@Service
@Transactional
public class LiveRoomService {

	private static final Logger logger = LoggerFactory
			.getLogger(LiveRoomService.class);
	
	@Autowired
	private ILiveRoomDAO liveRoomDAO;
	
	public void saveLiveRoom(LiveRoomBean bean) {
		if (bean != null) {
			LiveRoomDB entity = null;
			if (bean.getId() != null && bean.getId() > 0) {
				entity = liveRoomDAO.findOne(bean.getId());
				entity.setUpdateTime(new Date());
			}else {
				entity = new LiveRoomDB();
				entity.setName(bean.getName());
				entity.setRoomDetail(bean.getRoomDetail());
				entity.setRoomPassword(bean.getRoomPassword());
				entity.setStartTime(bean.getStartTime());
				entity.setEndTime(bean.getEndTime());
				entity.setMediaKey("test-media-key");
				entity.setCreateTime(new Date());
				entity.setUpdateTime(new Date());
			}
			liveRoomDAO.save(entity);
		}
		
	}

	public List<LiveRoomBean> findTodayLiveRoom() {		
		Calendar startTime = Calendar.getInstance();
		startTime.add(Calendar.DATE, -1);
		startTime.set(Calendar.HOUR_OF_DAY, 23);
		startTime.set(Calendar.MINUTE, 59);
		startTime.set(Calendar.SECOND, 59);
		Calendar endTime = Calendar.getInstance();
		endTime.add(Calendar.DATE, 1);
		endTime.set(Calendar.HOUR_OF_DAY, 0);
		endTime.set(Calendar.MINUTE, 0);
		endTime.set(Calendar.SECOND, 0);
		logger.info("findTodayLiveRoom params:{}/{}",startTime,endTime);
		List<LiveRoomDB> list = liveRoomDAO.searchByStartEndTime(startTime.getTime(), 
				endTime.getTime());
		if (list != null) {
			List<LiveRoomBean> beanlist = new ArrayList<LiveRoomBean>();
			for (LiveRoomDB db:list) {
				LiveRoomBean bean = new LiveRoomBean(db.getId());
				bean.setName(db.getName());
				bean.setRoomDetail(db.getRoomDetail());
				bean.setRoomPassword(db.getRoomPassword());
				bean.setMediaKey(db.getMediaKey());
				bean.setStartTime(db.getStartTime());
				bean.setEndTime(db.getEndTime());
				beanlist.add(bean);
			}
			return beanlist;
		}
		return null;
	}

}
