/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.common.utils.sms;

import java.util.Map;

import org.dom4j.DocumentException;

/**
 *	短信接口
 *  @author wgc,wgc@erongdu.com
 *  @CreatTime 2015年7月3日 下午2:46:25
 *  @since version 1.0.0
 */
public interface SmsPortal {

	/**
	 * 短信发送接口
	 * @description
	 * @param phone  接收短信的手机
	 * @param content 短信内容【业务内容+签名】
	 * @return 提示信息
	 * @author wgc 
	 * @return String
	 * @since  1.0.0
	 */
	public String send(String phone, String content);
	
	/**
	 * 获取短信用户信息   短信余额
	 * @description
	 * @return
	 * @throws DocumentException
	 * @author wgc
	 * @return Map<String,Integer>
	 * @since  1.0.0
	 */
	public Map<String, Integer> getUseInfo() throws DocumentException;
}
