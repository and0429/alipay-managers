package com.collect.alipay.domain;

/**
 * 分销商的销售详情
 * 
 * @author zhangkai
 *
 */
public class SellDetail4Distributor extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 消费类型
	 */
	private String category;

	/**
	 * 金额
	 */
	private float amount;
	/**
	 * 0:支付宝<br/>
	 * 1:现金
	 */
	private Integer payWay;
	/**
	 * 支付日期
	 */
	private String payDate;

	/**
	 * 操作员
	 */
	private String loginer;

	/**
	 * 所属商户
	 */
	private Cust cust;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getLoginer() {
		return loginer;
	}

	public void setLoginer(String loginer) {
		this.loginer = loginer;
	}

	public Cust getCust() {
		return cust;
	}

	public void setCust(Cust cust) {
		this.cust = cust;
	}
	
}
