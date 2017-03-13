package com.rongdu.eloan.common.utils.upload;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**request代理类 */
public class HttpServletRequestProxy extends HttpServletRequestWrapper {  
    private int contentLength=-1;
    LimitedServletInputStreamCustom lis= null;
    		
    public HttpServletRequestProxy(HttpServletRequest request) {
		super(request);
		contentLength = request.getContentLength();
    }
    
    public HttpServletRequestProxy(HttpServletRequest request, String progressId) {
    	super(request);
		contentLength = request.getContentLength();
		ProgressUtil.progressId = progressId;
		
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {  
        ServletInputStream in = super.getInputStream();        
        lis= new LimitedServletInputStreamCustom(in,contentLength,ProgressUtil.progressId);
        return lis;  
    }
    
}