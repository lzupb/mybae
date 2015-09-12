<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp"%>
<%@ taglib uri="/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>taogege.duapp.com oauth2 demo</title>
<link media="all" href="${ctx}/static/css/oauthdemo.css" type="text/css"
	rel="stylesheet">

<!--[if lt IE 9]>
    <script src="${serverip}/js/html5.js" type="text/javascript"></script>
    <![endif]-->
</head>
<body>
	<div id="c_base" class="c_base">
		<div id="c_content" class="c_main">


			<div class="">
				<div class="c_inmiddle_area" id="maincontent" spellcheck="false">


					<!-- App section -->
					<div id="idDiv_AppSection" class="modulerow">
						<!-- Title/app image/app domain section -->
						<img class="consentWebTitleIcon"
							src="${ctx}/static/images/oauthclient.gif"
							alt="oschina (20101109005929)" id="idImg_AppLogo" /> <span
							id="idSpan_AppHeaderText" class="consentTitleText">
							是否允许此应用访问你的信息？ </span>
						<div id="idDiv_AppDomainText" class="BreakWord">
							<span id="idSpan_AppDomainText" class="SecondaryText">
								taogege.duapp.com </span>
						</div>
						<div id="idDiv_AppDescText" class="modulerow BreakWord">
							<span id="idSpan_AppDescText"> taogege.duapp.com 
								需要得到你的许可才能执行以下操作: </span>
						</div>
					</div>

					<!-- Offer(s) section -->
					<div id="idDiv_OfferSection">

						<div id="idDiv_Offer_0" class="consentScope">
							<div class="consentWebScopeImage" id="idDiv_OfferImg_0">
								<img src="${ctx}/static/images/wl-emails.png" alt=""
									id="idImg_Offer_0" />
							</div>
							<div class="consentWebScopeText" id="idDiv_OfferText_0">
								<span class="bold" id="idSpan_OfferTitle_0"> 访问你的电子邮件地址 </span>
								<div id="idDiv_OfferDesc_0" class="BreakWord">
									<span id="idSpan_OfferDesc_0"> taogege.duapp.com 
										将能够查看你的个人资料中的电子邮件地址。 </span>
								</div>
							</div>
						</div>

					</div>

					<!-- Link to manage consent page text -->
					<div id="idDiv_Settings" class="modulerow">
						你可以随时在帐户设置中更改这些<a id="manageconsentlink" href="#" target="_blank">应用程序权限</a>。
					</div>

					<!-- Values to be posted back -->
					<form action="${ctx}/o/oauth2/auth" method="post">
					<label>email:</label><input type="text"/><br>
					<label>password:</label><input type="text"/>
					
					<input type="hidden" name="response_type" value="${response_type}" id="response_type" />
					<input type="hidden" name="client_id" value="${client_id}" id="client_id" />
					<input type="hidden" name="client_secret" value="" id="client_secret" />
					<input type="hidden" name="redirect_uri" value="${redirect_uri}" id="redirect_uri" />
					<input type="hidden" name="state" value="${state}" id="state" />
					<input type="hidden" name="scope" value="${scope}" id="scope" />					
					<div class="Buttons">
						<!-- Note: newline/space between input button types is adding unnecessary pixels, so we need to make sure there is no space/newline between them -->
						<input type="submit" class="default" name="ucaccept" value="是"
							id="idBtn_Accept" />
						<input type="submit"
							name="ucdeny" value="否" id="idBtn_Deny" />
					</div>
					</form>

					<!-- App privacy link (if any) -->
					<div id="idDiv_AppPrivacyandToS" class="appfooter">
						<div class="modulerow links">
							testclient <a id="appprivacy"
								href="http://www.oschina.net/home/about" target="_blank">隐私和
								Cookie</a><a id="appterms" href="http://www.oschina.net/home/about"
								target="_blank">条款</a>
						</div>
					</div>

				</div>
				<div class="c_inmiddle_area" id="dynamiccontent"
					style="display: none" aria-hidden="true" spellcheck="false">

				</div>
				<div class="c_inmiddle_area" id="dynamiccontent1"
					style="display: none" aria-hidden="true" spellcheck="false">

				</div>
			</div>
			<div class="ClearFloat"></div>

			<div id="DiagnosticData" style="display: none">BeginReq-AppInit-UpdateConsent(True)</div>
		</div>
	</div>
</body>
</html>
