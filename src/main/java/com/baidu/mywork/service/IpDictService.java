package com.baidu.mywork.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.mywork.entity.IpDictDB;
import com.baidu.mywork.model.IpDictBean;
import com.baidu.mywork.repository.IIpDictDAO;

@Service
@Transactional
public class IpDictService {
	
	@Autowired
	private IIpDictDAO ipDictDAO;
	
	public void saveDict(IpDictBean bean) {
		if (bean != null) {
			IpDictDB entity = null;
			if (bean.getId() != null && bean.getId() > 0) {
				entity = ipDictDAO.findOne(bean.getId());
				entity.setUpdateTime(new Date());
			}else {
				entity = new IpDictDB();
				entity.setCreateTime(new Date());
				entity.setUpdateTime(new Date());
			}
			entity.setName(bean.getName());
			entity.setServerIp(bean.getServerIp());
			entity.setClientIpRange(bean.getClientIpRange());
			ipDictDAO.save(entity);
		}
	}
	
	public List<IpDictBean> loadAllIpDict() {
		Iterable<IpDictDB> it =ipDictDAO.findAll();
		if (it != null) {
			List<IpDictBean> list = new ArrayList<IpDictBean>();
			Iterator<IpDictDB> ite = it.iterator();
			while (ite.hasNext()) {
				IpDictDB dbBean = ite.next();
				IpDictBean bean = new IpDictBean(dbBean.getId());
				bean.setClientIpRange(dbBean.getClientIpRange());
				bean.setServerIp(dbBean.getServerIp());
				list.add(bean);
			}
			return list;
		}
		return null;
	}

}
