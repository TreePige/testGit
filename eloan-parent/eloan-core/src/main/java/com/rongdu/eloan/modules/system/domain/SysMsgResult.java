package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 信息发送结果实体
 * 
 * @author wujing
 * @version 1.0
 * @since 2014-04-21
 */
public class SysMsgResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Long id;
	/**
	 * 信息类型:1邮件，2短信
	 */
	private Byte type;
	/**
	 * 信息编码
	 */
	private String nid;
	/**
	 * 发送人
	 */
	private Long sendUser;
	/**
	 * 接收人
	 */
	private Long receiveUser;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 发送结果
	 */
	private String sendResult;
	/**
	 * 发送状态:0待发送,1成功，-1失败
	 */
	private Byte status;

	/**
	 * 接受地址
	 */
	private String receiveAddr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取信息类型:1邮件，2短信
	 * 
	 * @return 信息类型:1邮件，2短信
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 设置信息类型:1邮件，2短信
	 * 
	 * @param type 要设置的信息类型:1邮件，2短信
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	public Long getSendUser() {
		return sendUser;
	}

	public void setSendUser(Long sendUser) {
		this.sendUser = sendUser;
	}

	public Long getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(Long receiveUser) {
		this.receiveUser = receiveUser;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title 要设置的标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content 要设置的内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取发送时间
	 * 
	 * @return 发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 设置发送时间
	 * 
	 * @param sendTime 要设置的发送时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 获取发送结果
	 * 
	 * @return 发送结果
	 */
	public String getSendResult() {
		return sendResult;
	}

	/**
	 * 设置发送结果
	 * 
	 * @param sendResult 要设置的发送结果
	 */
	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}

	/**
	 * 获取发送状态:0失败,1成功
	 * 
	 * @return 发送状态:0失败,1成功
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 设置发送状态:0失败,1成功
	 * 
	 * @param status 要设置的发送状态:0失败,1成功
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * @return the nid
	 */
	public String getNid() {
		return nid;
	}

	/**
	 * @param nid the nid to set
	 */
	public void setNid(String nid) {
		this.nid = nid;
	}

	/**
	 * @return the receiveAddr
	 */
	public String getReceiveAddr() {
		return receiveAddr;
	}

	/**
	 * @param receiveAddr the receiveAddr to set
	 */
	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}
}
