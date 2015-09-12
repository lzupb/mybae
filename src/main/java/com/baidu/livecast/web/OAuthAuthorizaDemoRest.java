package com.baidu.livecast.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/o/oauth2/authDemo")
public class OAuthAuthorizaDemoRest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(OAuthAuthorizaDemoRest.class);	

	@RequestMapping(method = RequestMethod.GET)
	public String auth(HttpServletRequest request, Model model) throws OAuthSystemException, IOException {
		try {
			//dynamically recognize an OAuth profile based on request characteristic (params,
			// method, content type etc.), perform validation
			logger.info("protocol:{}/scheme:{}",request.getProtocol(),request.getScheme());
			String redirect_uri = request.getParameter(OAuth.OAUTH_REDIRECT_URI);
			model.addAttribute("redirect_uri", redirect_uri == null ? "http://qe.com" :redirect_uri);
			String client_id = request.getParameter(OAuth.OAUTH_CLIENT_ID);
			model.addAttribute("client_id", client_id == null ? "123456" :client_id);
			String response_type = request.getParameter(OAuth.OAUTH_RESPONSE_TYPE);
			model.addAttribute("response_type", response_type == null ? "code" :response_type);
			String scope = request.getParameter(OAuth.OAUTH_SCOPE);
			model.addAttribute("scope", scope);
			model.addAttribute("state", "123efdewqrew");
//			String require_scheme = request.getScheme();
//			if (require_scheme.trim().equals("https")) {
//				
//				returnview = "auth-demo";
//			}else {
//				
//			}
			
			 } catch(Exception ex) {	
						 
		}
		return "auth-demo"; 
	 }

	private void validateRedirectionURI(OAuthAuthzRequest oauthRequest) {
		
	}
}
