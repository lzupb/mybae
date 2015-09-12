<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp"%>
<%@ taglib uri="/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>度学堂 百度统一培训平台 直播</title>
<link media="all" href="${context}/css/common-min.css" type="text/css"
	rel="stylesheet">
<link media="all" href="${context}/css/index-min.css" type="text/css"
	rel="stylesheet">
<!--[if lt IE 9]>
    <script src="${serverip}/js/html5.js" type="text/javascript"></script>
    <![endif]-->
<style type="text/css">
#content {
	height: 540px;
	width: 720px;
	margin: 5px auto 0;
}

#footer {
	margin: 5px 0 0;
}
</style>
<script type="text/javascript" src="${context}/js/swfobject.js"></script>
<script type="text/javascript">
	var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
			: " http://");
	document
			.write(unescape("%3Cscript src='"
					+ _bdhmProtocol
					+ "hm.baidu.com/h.js%3Fb5bf61f2f2cd8fee972e3a7acb59d5c2' type='text/javascript'%3E%3C/script%3E"));

	function refresh() {
		window.location.reload();
	}

	// For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection.
	var swfVersionStr = "11.1.0";
	// To use express install, set to playerProductInstall.swf, otherwise the empty string.
	var xiSwfUrlStr = "${context}/playerProductInstall.swf";
	var flashvars = {
		"camera" : encodeURIComponent("${camera}"),
		"beginTime" : encodeURIComponent("${beginTime}"),
		"endTime" : encodeURIComponent("${endTime}")
	};
	var params = {};
	params.quality = "high";
	params.bgcolor = "#ffffff";
	//params.allowscriptaccess = "sameDomain";
	params.allowscriptaccess = "always";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = "onlyLivePlayer";
	attributes.name = "onlyLivePlayer";
	attributes.align = "middle";
	swfobject.embedSWF("${context}/onlyLivePlayer.swf", "flashContent",
			"100%", "100%", swfVersionStr, xiSwfUrlStr, flashvars, params,
			attributes);
	// JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.
	swfobject.createCSS("#flashContent", "display:block;text-align:center;");
</script>
</head>
<body class="g-bg">
	<!--#S header -->
	<header id="header" class=""> <section class="w"> <!--#S logo -->
	<h1 class="logo">
		<a href="http://learn.baidu.com/"><img alt=""
			src="${context}/images/logo.png"></a>
	</h1>
	<!--#E logo --> </section> </header>
	<!--#E header -->

	<!--#S content -->
	<section class="clearfix" id="content">
	<div id="flashContent"></div>
	<!--
        <object width="100%" height="100%" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000">
		  <param value="http://www.iqiyi.com/player/20140108005211/livePlayer.swf?coreUrl=http://www.iqiyi.com/player/20140108005255/library.swf&amp;local=1&amp;vid=c1_baidu2014_s10" name="movie">
		  <param value="high" name="quality">
		  <param value="opaque" name="wmode">
		  <param value="true" name="allowfullscreen">
		  <embed width="100%" height="100%" allowfullscreen="true" type="application/x-shockwave-flash" quality="high" src="http://www.iqiyi.com/player/20140108005211/livePlayer.swf?coreUrl=http://www.iqiyi.com/player/20140108005255/library.swf&amp;local=1&amp;vid=c1_baidu2014_s10">
		</object>
		--> </section>
	<!--#E content -->

	<!--#S footer -->
	<footer id="footer">
	<p>
		&copy; 2014 度学堂 荣誉出品 | 联系我们：<a href="mailto:du-learning@baidu.com">du-learning@baidu.com</a>
	</p>
	</footer>
	<!--#E footer -->
</body>
</html>
