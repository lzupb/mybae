package com.baidu.mywork.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baidu.mywork.model.LiveRoomBean;
import com.baidu.mywork.service.LiveRoomService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/liveList")
public class LiveListRest {

	@Autowired
	private LiveRoomService LiveRoomService;

	@RequestMapping(method = RequestMethod.GET)
	public void index(HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		List<LiveRoomBean> list = LiveRoomService.findTodayLiveRoom();
		if (list != null) {
			Gson gson = new Gson();
			// String str = gson.toJson(list);
			String str = "{\"page\":\"1\",\"total\":\"1\",\"rows\":[{\"id\":1,\"cell\":{\"roomID\":2,\"name\":\"test1\",\"mediaKey\":\"testmediaKey\",\"startTime\":\"123\",\"endTime\":\"456\"}}]}";
			try {
				response.getWriter().write(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("{'stop':'ok'}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
