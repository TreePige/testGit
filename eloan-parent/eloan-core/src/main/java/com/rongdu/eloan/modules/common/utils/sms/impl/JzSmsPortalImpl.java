package com.rongdu.eloan.modules.common.utils.sms.impl;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jianzhou.sdk.BusinessService;
import com.rongdu.eloan.modules.common.utils.sms.SmsPortal;

/**
 * 建周短信接口实现
 *	@Description
 *  @author wgc,wgc@erongdu.com
 *  @CreatTime 2015年7月3日 下午3:02:42
 *  @since version 1.0.0
 */
public class JzSmsPortalImpl implements SmsPortal {
	private static final Logger logger = LoggerFactory.getLogger(JzSmsPortalImpl.class);
	//短信服务
	private static final String smsUrl   = "http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService";
	//在建周开的用户名
	private static final String username = "sdk_henhaodai";
	//密码
	private static final String password = "qazwsx";	
	//签名
	private static final String flag = "【汇沁科技】";

	@Override
	public String send(String phone, String content) {
		BusinessService bs = new BusinessService();
		bs.setWebService(smsUrl);
		String sent_content = content + flag; //内容+签名  签名是建周内定的。
		logger.info("【建周】发送短信内容 ====== " + sent_content);
		int resultMsg = bs.sendBatchMessage(username, password, phone, sent_content);
		logger.debug("短信发送结果：" + resultMsg);
		String result = "";
		if (resultMsg == -1) {
			result = "余额不足, " + resultMsg;
		} else if (resultMsg == -2) {
			result = "账号或密码错误, " + resultMsg;
		} else if (resultMsg == -3) {
			result = "连接服务商失败, " + resultMsg;
		} else if (resultMsg == -4) {
			result = "超时, " + resultMsg;
		} else if (resultMsg == -5) {
			result = "其他错误，一般为网络问题，IP受限等, " + resultMsg;
		} else if (resultMsg == -6) {
			result = "短信内容为空, " + resultMsg;
		} else if (resultMsg == -7) {
			result = "目标号码为空, " + resultMsg;
		} else if (resultMsg == -8) {
			result = "用户通道设置不对，需要设置三个通道, " + resultMsg;
		} else if (resultMsg == -9) {
			result = "捕获未知异常, " + resultMsg;
		} else if (resultMsg == -10) {
			result = "超过最大定时时间限制, " + resultMsg;
		} else if (resultMsg == -11) {
			result = "目标号码在黑名单里, " + resultMsg;
		} else if (resultMsg == -12) {
			result = "消息内容包含禁用词语, " + resultMsg;
		} else if (resultMsg == -13) {
			result = "没有权限使用该网关, " + resultMsg;
		} else if (resultMsg == -14) {
			result = "找不到对应的Channel ID, " + resultMsg;
		} else if (resultMsg == -17) {
			result = "没有提交权限，客户端帐号无法使用接口提交, " + resultMsg;
		} else if (resultMsg == -20) {
			result = "超速提交(一般为每秒一次提交), " + resultMsg;
		} else if (resultMsg > 0) {
			result = "ok";
		}
		return result;
	}
	
	public static void main(String[] args) throws DocumentException {
		String url = "http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService";
		BusinessService bs = new BusinessService();
		/*bs.setWebService(url);
		String sent_content = "测试是否收到短信【汇沁科技】";
		int resultMsg = bs.sendBatchMessage(username, password, "接收短信的手机", sent_content);
		System.out.println("短信发送结果：" + resultMsg);*/
		
		JzSmsPortalImpl j = new JzSmsPortalImpl();
		Map<String,Integer> resultMap = j.getUseInfo();
		System.out.println("==="+resultMap.get("remainFee"));
	}

	@Override
	public Map<String, Integer> getUseInfo() throws DocumentException {
		BusinessService bs = new BusinessService();
		bs.setWebService(smsUrl);
		String resultXML = bs.getUserInfo(username, password);
		Document dom=DocumentHelper.parseText(resultXML);
		Element root=dom.getRootElement();
		Integer remainFee = Integer.parseInt(root.element("remainFee").getText());//短信余额
		logger.info("建周短信通道短信余额结果：querySmsCount() == " + getCountResult(remainFee));
		HashMap<String, Integer> queryMap = new HashMap<String, Integer>();
		queryMap.put("remainFee", remainFee);
		return queryMap;
	}
	
	public String getCountResult(int result) {
		String resultString = "";
		if (result > 0) {
			resultString = result + "==========短信余额";
		} else if (result == -1) {
			resultString = result + "==余额不足";
		} else if (result == -2) {
			resultString = result + "==账号或密码错误";
		} else if (result == -3) {
			resultString = result + "==连接服务商失败";
		}
		return resultString;
	}
}
