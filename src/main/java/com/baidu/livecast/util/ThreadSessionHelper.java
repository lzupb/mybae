package com.baidu.livecast.util;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;



/**
 * session辅助类<br>

 * 
 */
public class ThreadSessionHelper {

	public static void clearThreadSession() {
		ThreadSession.clear();
	}

	public static Map getAttributes() {
		return ThreadSession.getAttributes();
	}

	public static void setAttribute(String key, Object value) {
		ThreadSession.setAttribute(key, value);
	}

	public static Object getAttribute(String key) {
		return getAttributes().get(key);
	}	

	static void setRequestURI(String uri) {
		ThreadSession.setAttribute(SysCommonContants.REQUEST_URI_KEY, uri);
	}

	public static String getRequestURI() {
		return (String) ThreadSession.getAttribute(SysCommonContants.REQUEST_URI_KEY);
	}
	
	public static String getCurrentLoginUserName(){
		if (getSession() == null) return  null;
		return (String)getSession().getAttribute(SysCommonContants.CURRENT_USER_NAME);
	}

	static void setClientIP(String clientIP) {
		ThreadSession.setAttribute(SysCommonContants.CLIENT_IP_KEY, clientIP);
	}

	public static String getClientIP() {
		return (String) ThreadSession.getAttribute(SysCommonContants.CLIENT_IP_KEY);
	}
	
	static void setSession(HttpSession httpSession) {
        ThreadSession.setAttribute(SysCommonContants.HTTP_SESSION, httpSession);
    }

    public static HttpSession getSession() {
        return (HttpSession) ThreadSession.getAttribute(SysCommonContants.HTTP_SESSION);
    }
	
}

class ThreadSession implements Serializable {

    
    /**
     * 
     */
    private static final long serialVersionUID = 3266905893684756575L;
    private static ThreadLocal<Map> threadSessionHolder = new ThreadLocal<Map>();

    public static void clear() {
        threadSessionHolder.set(null);
    }

    public static Map getAttributes() {
        if (threadSessionHolder.get() == null) {
            threadSessionHolder.set(new ConcurrentHashMap());
        }

        return (Map) threadSessionHolder.get();
    }

    public static void setAttribute(String key, Object value) {
        Assert.notNull(key, "Only non-null key are permitted");
        Assert.notNull(value, "Only non-null value are permitted");
        getAttributes().put(key, value);
    }

    public static Object getAttribute(String key) {
        Assert.notNull(key, "Only non-null key are permitted");
        return getAttributes().get(key);
    }
}
