package com.collect.alipay.control.dto;

/**
 * 状态传输
 * 
 * @author zhangkai
 *
 */
public class Status {

	/**
	 * 状态代码
	 */
	private int code;

	/**
	 * 状态信息
	 */
	private String msg = "error";

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 构造器
	 * 
	 * @param code
	 *            代码
	 * @param msg
	 *            消息
	 */
	public Status(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 构造器
	 */
	public Status() {
		super();
	}

	/**
	 * 构造器
	 * 
	 * @param code
	 *            代码
	 */
	public Status(int code) {
		super();
		this.code = code;
	}

}
