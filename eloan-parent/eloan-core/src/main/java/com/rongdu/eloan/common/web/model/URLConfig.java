package com.rongdu.eloan.common.web.model;

import com.rongdu.eloan.common.utils.StringUtil;


/**
 * URL配置
 * @author zhangyz
 */

public class URLConfig {
	
	/**
	 * 协议类型，默认http
	 */
	private String protocol = "http";

	/**
	 * 主机地址
	 */
	private String host;

	/**
	 * 端口
	 */
	private Integer port = 80;

	private String url = null;

	private String contextPath="";
		
	
	public void init() {
		if (url == null)
			createUrl();
	}
	
	
	private void createUrl() {
		StringBuffer sb = new StringBuffer();
		if (StringUtil.isNotBlank(protocol)) {
			sb.append(protocol).append("://");
		}
		if (StringUtil.isNotBlank(host)) {
			sb.append(host);
		}
		if (port != null) {
			if ((protocol.equals("http") && port == 80)
					|| (protocol.equals("https") && port == 443)) {

			} else {
				sb.append(":").append(port);
			}
		}
		
		if (contextPath == null || contextPath.equals("")) {
			
		}
		else {
			if (contextPath.charAt(0) == '/') {
				sb.append(contextPath);
			}
			else {
				sb.append("/" + contextPath);
			}
		}
				
		while(sb.charAt(sb.length() - 1) == '/') {
			{
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		url = sb.toString();		
	}
	
	
	/**
	 * 返回配置好的url，${protocol}://${host}:{port}/{contentPath} 不以"/"结尾
	 * 
	 * @return
	 */
	public String toString() {
		if (url == null) {
			createUrl();
		}
		return url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host.toLowerCase();
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol.toLowerCase();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
}