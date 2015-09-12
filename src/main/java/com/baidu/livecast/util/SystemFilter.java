package com.baidu.livecast.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SystemFilter implements Filter {

	
	public final void doFilter(final ServletRequest servletRequest,final ServletResponse servletResponse, final FilterChain filterChain)
		throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		try {
    		final HttpSession session = request.getSession(false);		
    		if (session != null) {
    			ThreadSessionHelper.setSession(session); 
    		}
    		ThreadSessionHelper.setClientIP(WebUtil.getClientIP(request));
    		ThreadSessionHelper.setRequestURI(request.getRequestURI());    		
			filterChain.doFilter(request, response);
		}finally{
			ThreadSessionHelper.clearThreadSession();
		}	
		
	}	

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }

}
