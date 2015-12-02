package com.baidu.mywork.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baidu.mywork.model.LiveRoomBean;
import com.baidu.mywork.service.IpCalculateService;
import com.baidu.mywork.service.LiveRoomService;
import com.baidu.mywork.util.ThreadSessionHelper;

@Controller
@RequestMapping(value = "/index")
public class IndexRest {
	
	@Autowired
	private IpCalculateService ipCalculateService;
	
	@Autowired
	private LiveRoomService LiveRoomService;

	 @RequestMapping(method = RequestMethod.GET)
	 public String index(Model model,HttpServletResponse response) {
		 Cookie cookie = new Cookie("oauth", "test-oauth");
		 response.addCookie(cookie);		 
		 String returnView = "";
		 String clientIP = ThreadSessionHelper.getClientIP();
		 String serverip = ipCalculateService.calculateServerIpByClientIp(clientIP);
		 List<LiveRoomBean> list = LiveRoomService.findTodayLiveRoom();
		 if (list != null && list.size() ==1) {
			 LiveRoomBean bean = list.get(0);
			 String camera = "http://"+serverip+":9021/playlist.m3u8?src_media_id="
						+ bean.getMediaKey() + "&output_format=1231&live_video=1&src=xxx";
			 model.addAttribute("camera", camera);
			 String context = "http://" + serverip + ":8880/bdlive";
			 model.addAttribute("context",context);
			 DateFormat datFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 model.addAttribute("beginTime",datFormat.format(bean.getStartTime()));
			 model.addAttribute("endTime",datFormat.format(bean.getEndTime()));
			 returnView = "bdlive";
		 }else {
			 returnView = "bdlive-list";
		 }
		 
		 return returnView;
	 }
}
