package com.collect.alipay.domain;


/**
 * 用户支付月总
 * 
 * @author zhangkai
 *
 */
public class PayMonth4Loginer extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 月份
	 */
	private String month;

	/**
	 * 用户
	 */
	private Loginer loginer;

	/**
	 * 月总金额
	 */
	private float total;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Loginer getLoginer() {
		return loginer;
	}

	public void setLoginer(Loginer loginer) {
		this.loginer = loginer;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
