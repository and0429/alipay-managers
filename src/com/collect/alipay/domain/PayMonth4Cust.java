package com.collect.alipay.domain;

/**
 * 月账单for商户
 * 
 * @author zhangkai
 *
 */
public class PayMonth4Cust extends BaseModel {

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
	 * 商户
	 */
	private Cust cust;

	/**
	 * 总金额
	 */
	private float total;

	/**
	 * 提成
	 */
	private float deduct;

	public float getDeduct() {
		return deduct;
	}

	public void setDeduct(float deduct) {
		this.deduct = deduct;
	}

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

	public Cust getCust() {
		return cust;
	}

	public void setCust(Cust cust) {
		this.cust = cust;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
