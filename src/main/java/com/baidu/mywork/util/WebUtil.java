package com.baidu.mywork.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.EncodingUtil;

public class WebUtil {
	/**
	 * 直接取得当前服务端网络设置
	 * 
	 * @return
	 */
	public static final String getServerIP() {
		String serverName = "UnkownServerIP";
		try {
			InetAddress address = InetAddress.getLocalHost();
			serverName = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return serverName;
		}
		return serverName;
	}

	/**
	 * 根据请求Request取得服务器的主机名称
	 * 
	 * @param request
	 * @return
	 */
	public static final String getServerName(final HttpServletRequest request) {
		String serverName = request.getServerName();
		if ("127.0.0.1".equals(serverName) || "localhost".equals(serverName)) {
			try {
				InetAddress address = InetAddress.getLocalHost();
				serverName = address.getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return serverName;
			}
		}
		return serverName;
	}

	/**
	 * 取得WEB客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIP(final HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 根据请求Request构建URL
	 * 
	 * @param request
	 * @param servletPath
	 * @return
	 */
	public static final String getAbsoluteUrl(final HttpServletRequest request, String servletPath) {
		String serverName = getServerName(request);
		String path = request.getContextPath() + servletPath;
		StringBuffer urlBuff = new StringBuffer(request.getScheme());
		urlBuff.append("://");
		String port = request.getServerPort() == 80 ? "" : ":" + String.valueOf(request.getServerPort());
		urlBuff.append(serverName).append(port).append(path);
		return urlBuff.toString();
	}
	
	public static Map<String, String> executeRemoteHTTPByMapparameter(String remoteHttpUrl, String method,
            Map<String, String> parameter) throws IOException {
        Map<String, String> resultMap = new HashMap<String, String>();
        if (method != null) {
            HttpClient client = new HttpClient();
            NameValuePair[] nameValuePairs = null;
            if (parameter != null) {
                nameValuePairs = new NameValuePair[parameter.size()];
                int i = 0;
                for (Map.Entry<String, String> entry : parameter.entrySet()) {
                    NameValuePair simcard = new NameValuePair(entry.getKey(), entry.getValue());
                    nameValuePairs[i] = simcard;
                    i++;
                }
            }

            HttpMethod httpMethod = null;
            if (method.equalsIgnoreCase("get")) {
                if (nameValuePairs != null) {
                    remoteHttpUrl = remoteHttpUrl + "?" + EncodingUtil.formUrlEncode(nameValuePairs, "UTF-8");
                    ;
                }
                httpMethod = new GetMethod(remoteHttpUrl);
            }
            if (method.equalsIgnoreCase("post")) {
                httpMethod = new PostMethod(remoteHttpUrl);
                if (nameValuePairs != null) {
                    ((PostMethod) httpMethod).setRequestBody(nameValuePairs);
                }
            }
            if (httpMethod != null) {
                int responseCode = client.executeMethod(httpMethod);
                resultMap.put("responseCode", responseCode + "");
                String result = new String(httpMethod.getResponseBodyAsString().getBytes(), "UTF-8");
                resultMap.put("result", result);
                // 打印服务器返回的状态
                System.out.println(httpMethod.getStatusLine());

                // 释放连接
                httpMethod.releaseConnection();
            }
        }
        return resultMap;
    }
}
