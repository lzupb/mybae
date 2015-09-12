package com.baidu.livecast.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.livecast.model.IpDictBean;

@Service
@Transactional
public class IpCalculateService implements InitializingBean{

	private static final Logger logger = LoggerFactory
			.getLogger(IpCalculateService.class);

	@Autowired
	private IpDictService ipDictService;

	private static Map<String, String> testMap = new HashMap<String, String>();
	private static Map<String, String> ipClientMap = new HashMap<String, String>();
	private static Map<String, ServerInfo> ipServerMap = new HashMap<String, ServerInfo>();

	public String calculateServerIpByClientIp(String clientIp) {
		checkIPData();
		logger.info("calculateServerIpByClientIp clientIp:{}", clientIp);
		if (testMap.containsKey(clientIp)) {
			String value = testMap.get(clientIp);
			return findServerIp(value, clientIp);
		}

		String binaryString = getBinaryString(clientIp);
		for (Map.Entry<String, String> entry : ipClientMap.entrySet()) {
			if (binaryString.startsWith(entry.getKey())) {
				String value = entry.getValue();
				return findServerIp(value, clientIp);
			}
		}
		logger.info("cannot find matched ip:{},return beijing-dasha-youxian",
				clientIp);
		String key = "beijing-dasha-youxian";
		return findServerIp(key, clientIp);

	}

	public synchronized void refreshIPRangeMap() {
		testMap.clear();
		ipClientMap.clear();
		ipServerMap.clear();
		List<IpDictBean> list = ipDictService.loadAllIpDict();
		if (list != null && list.size() > 0) {
			for (IpDictBean bean : list) {
				testMap.put(bean.getServerIp(), bean.getName());
				{
					String[] clientips = bean.getClientIpRange().split(",");
					for (String text : clientips) {
						String prefix = getPrefix(text);
						ipClientMap.put(prefix, bean.getName());
					}
				}

				{
					String[] serverips = bean.getServerIp().split(",");
					ServerInfo serverInfo = null;
					if (ipServerMap.containsKey(bean.getName())) {
						serverInfo = ipServerMap.get(bean.getName());
					} else {
						serverInfo = new ServerInfo();
						ipServerMap.put(bean.getName(), serverInfo);
					}
					for (String text : serverips) {
						serverInfo.addIp(text);
					}

				}
			}
		}
	}
	
	private void checkIPData() {
		boolean flag = (testMap != null && !testMap.isEmpty())
				&& (ipClientMap != null && !ipClientMap.isEmpty())
				&& (ipServerMap != null && !ipServerMap.isEmpty());
		if (flag == false) {
			throw new RuntimeException("IPData error!!!");
		}
	}

	private String getPrefix(String text) {
		String[] array = text.split("/");
		String ip = array[0];
		String postfix = array[1];
		String binaryString = getBinaryString(ip);
		String prefix = binaryString.substring(0, Integer.parseInt(postfix));
		return prefix;
	}

	private String getBinaryString(String ip) {
		String[] parts = ip.split("\\.");
		String part0 = parts[0];
		String part1 = parts[1];
		String part2 = parts[2];
		String part3 = parts[3];
		String text0 = Integer.toBinaryString(Integer.parseInt(part0));
		text0 = fit(text0);
		String text1 = Integer.toBinaryString(Integer.parseInt(part1));
		text1 = fit(text1);
		String text2 = Integer.toBinaryString(Integer.parseInt(part2));
		text2 = fit(text2);
		String text3 = Integer.toBinaryString(Integer.parseInt(part3));
		text3 = fit(text3);
		String result = text0 + text1 + text2 + text3;
		return result;
	}

	private String fit(String text) {
		for (int i = text.length(); i < Integer.toBinaryString(255).length(); i++) {
			text = "0" + text;
		}
		return text;
	}

	private String findServerIp(String name, String ip) {
		String result = ipServerMap.get(name).getIp();
		logger.info("getMatchedIp:{}-->{}", ip, result);
		return result;
	}

	static class ServerInfo {
		private AtomicInteger index = new AtomicInteger(0);
		private int size;
		private List<String> ips = new ArrayList<String>();

		public String getIp() {
			int i = 0;
			synchronized (index) {
				i = index.addAndGet(1);
				if (i >= size) {
					i = 0;
					index.set(i);
				}
			}
			return ips.get(i);
		}

		public void addIp(String ip) {
			this.ips.add(ip);
			this.size = ips.size();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		refreshIPRangeMap();		
	}

}
