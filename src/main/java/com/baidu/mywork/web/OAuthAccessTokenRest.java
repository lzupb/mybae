package com.baidu.mywork.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/o/oauth2/token")
public class OAuthAccessTokenRest {

	private final OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(
			new MD5Generator());

	@RequestMapping(method = RequestMethod.POST)
	public void token(HttpServletRequest request, HttpServletResponse response)
			throws OAuthSystemException, IOException {
		OAuthTokenRequest oauthRequest = null;

		try {
			oauthRequest = new OAuthTokenRequest(request);

			validateClient(oauthRequest);

			String authzCode = oauthRequest.getCode();

			// some code

			String accessToken = oauthIssuerImpl.accessToken();
			String refreshToken = oauthIssuerImpl.refreshToken();

			// some code

			OAuthResponse r = OAuthASResponse
					.tokenResponse(HttpServletResponse.SC_OK)
					.setAccessToken(accessToken).setExpiresIn("3600")
					.setRefreshToken(refreshToken).buildJSONMessage();

			response.setStatus(r.getResponseStatus());
			PrintWriter pw = response.getWriter();
			pw.print(r.getBody());
			pw.flush();
			pw.close();

			// if something goes wrong
		} catch (OAuthProblemException ex) {

			OAuthResponse r = OAuthResponse.errorResponse(401).error(ex)
					.buildJSONMessage();

			response.setStatus(r.getResponseStatus());

			PrintWriter pw = response.getWriter();
			pw.print(r.getBody());
			pw.flush();
			pw.close();

			response.sendError(401);
		}
	}

	private void validateClient(OAuthTokenRequest oauthRequest) {
		// TODO Auto-generated method stub

	}

}
