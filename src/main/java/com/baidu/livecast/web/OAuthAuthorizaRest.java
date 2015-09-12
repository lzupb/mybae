package com.baidu.livecast.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/o/oauth2/auth")
public class OAuthAuthorizaRest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(OAuthAuthorizaRest.class);
	
	private final OAuthIssuer issuer = new OAuthIssuerImpl(new MD5Generator());

	@RequestMapping(method = RequestMethod.POST)
	public void auth(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException, IOException {
		try {
			//dynamically recognize an OAuth profile based on request characteristic (params,
			// method, content type etc.), perform validation
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
			logger.info("protocol:{}/scheme:{}",request.getProtocol(),request.getScheme());
			validateRedirectionURI(oauthRequest);
			 
			//build OAuth response
			OAuthResponse resp = OAuthASResponse
			.authorizationResponse(request,HttpServletResponse.SC_FOUND)
			.setCode(issuer.authorizationCode())
			.location(oauthRequest.getRedirectURI())
			.buildQueryMessage();
			 
			response.sendRedirect(resp.getLocationUri());
			 
			//if someithing goes wrong
			 } catch(OAuthProblemException ex) {
				final OAuthResponse resp = OAuthASResponse
				.errorResponse(HttpServletResponse.SC_FOUND)
				.error(ex)
				.location("")
				.buildQueryMessage();
						 
			response.sendRedirect(resp.getLocationUri());
		}
	 }

	private void validateRedirectionURI(OAuthAuthzRequest oauthRequest) {
		// TODO Auto-generated method stub
		
	}
}
