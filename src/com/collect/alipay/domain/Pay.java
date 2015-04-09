package com.collect.alipay.domain;

/**
 * 支付流水实体类
 * 
 * @author zhangkai
 *
 */
/**
 * @author zhangkai
 *
 */
public class Pay extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 商品类型
	 */
	private String category;

	/**
	 * 价格
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

	public String getLoginer() {
		return loginer;
	}

	public void setLoginer(String loginer) {
		this.loginer = loginer;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

}
