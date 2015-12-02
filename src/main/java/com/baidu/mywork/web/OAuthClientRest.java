package com.baidu.mywork.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/client")
public class OAuthClientRest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(OAuthClientRest.class);
	

	@RequestMapping(method = RequestMethod.GET)
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException, IOException, OAuthProblemException {
		OAuthClient client = new OAuthClient(new URLConnectionClient());
		TokenRequestBuilder tokenRequestBuilder = OAuthClientRequest.tokenLocation(TestOAuthProvider.TESTOAUTH.getTokenEndpoint());
		tokenRequestBuilder.setClientId("123456")
						    .setClientSecret("retedsfewt")
						    .setCode(request.getParameter(OAuth.OAUTH_CODE))
						    .setGrantType(GrantType.AUTHORIZATION_CODE)
						    .setRedirectURI("http://123.com");
		
		OAuthJSONAccessTokenResponse jsontoken =  client.accessToken(tokenRequestBuilder.buildBodyMessage());
		logger.info("accessToken:{}",jsontoken.getAccessToken());
		response.getWriter().write(jsontoken.getBody());
	 }

	private void validateRedirectionURI(OAuthAuthzRequest oauthRequest) {
		// TODO Auto-generated method stub
		
	}
	
	private enum TestOAuthProvider {


		FACEBOOK(
				"facebook", 
				"https://graph.facebook.com/oauth/authorize", 
				"https://graph.facebook.com/oauth/access_token"),
		
		FOURSQUARE(
				"foursquare", 
				"https://foursquare.com/oauth2/authenticate", 
				"https://foursquare.com/oauth2/access_token"),
		
		GITHUB(
				"GitHub", 
				"https://github.com/login/oauth/authorize", 
				"https://github.com/login/oauth/access_token"),
		
		GOOGLE(
				"Google", 
				"https://accounts.google.com/o/oauth2/auth", 
				"https://accounts.google.com/o/oauth2/token"),
		
		INSTAGRAM(
				"Instagram", 
				"https://api.instagram.com/oauth/authorize", 
				"https://api.instagram.com/oauth/access_token"),
		
		LINKEDIN(
				"LinkedIn", 
				"https://www.linkedin.com/uas/oauth2/authorization", 
				"https://www.linkedin.com/uas/oauth2/accessToken"),
		
		MICROSOFT(
				"Microsoft", 
				"https://login.live.com/oauth20_authorize.srf", 
				"https://login.live.com/oauth20_token.srf"),
		
		PAYPAL(
				"PayPal", 
				"https://identity.x.com/xidentity/resources/authorize", 
				"https://identity.x.com/xidentity/oauthtokenservice"),
		
		REDDIT(
				"reddit", 
				"https://ssl.reddit.com/api/v1/authorize", 
				"https://ssl.reddit.com/api/v1/access_token"),
		
		SALESFORCE(
				"salesforce", 
				"https://login.salesforce.com/services/oauth2/authorize", 
				"https://login.salesforce.com/services/oauth2/token"),
		
		YAMMER(
				"Yammer", 
				"https://www.yammer.com/dialog/oauth", 
				"https://www.yammer.com/oauth2/access_token.json"),
				
		TESTOAUTH(
				"TestOAuth", 
				"http://10.48.223.115:8080/all.live/o/oauth2/authDemo", 
				"http://10.48.223.115:8080/all.live/o/oauth2/token");
		
		private String providerName;
		
		private String authzEndpoint; 
		
		private String tokenEndpoint;
		
		/**
		 * Get the provider name
		 * 
		 * @return Returns the provider name
		 */
		public String getProviderName() {
			return providerName;
		}
		
		/**
		 * Get the authorization endpoint
		 * 
		 * @return Returns the authorization endpoint
		 */
		public String getAuthzEndpoint() {
			return authzEndpoint;
		}
		
		/**
		 * Get the access token endpoint
		 * 
		 * @return Returns the access token endpoint
		 */
		public String getTokenEndpoint() {
			return tokenEndpoint;
		}
		
		/**
		 * Full private constructor
		 * 
		 * @param providerName The provider name
		 * @param authzEndpoint The authorization endpoint
		 * @param tokenEndpoint The token endpoint
		 */
		private TestOAuthProvider(
				final String providerName, 
				final String authzEndpoint, 
				final String tokenEndpoint) {
			
			this.providerName = providerName;
			this.authzEndpoint = authzEndpoint;
			this.tokenEndpoint = tokenEndpoint;
		}
		

	}
}
