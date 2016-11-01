package com.baidu.mywork.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baidu.mywork.service.IpCalculateService;
import com.baidu.mywork.service.LiveRoomService;

@Controller
@RequestMapping(value = "/worker")
public class StartWorkRest {

	@Autowired
	private IpCalculateService ipCalculateService;

	@Autowired
	private LiveRoomService LiveRoomService;

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public void start(HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		try {
			response.getWriter().write("{'stop':'ok'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "stop", method = RequestMethod.POST)
	public void stop(HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		try {
			response.getWriter().write("{'title':'ok'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
