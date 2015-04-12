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
	 * 支付宝交易号
	 */
	private String tradeNo;

	/**
	 * 支付者账号
	 */
	private String buyer;

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

	/**
	 * 支付状态
	 */
	private String status;

	/**
	 * 商户Id
	 */
	private String custId;

	/**
	 * 退款金额
	 */
	private float refundTotal;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public float getRefundTotal() {
		return refundTotal;
	}

	public void setRefundTotal(float refundTotal) {
		this.refundTotal = refundTotal;
	}

	public float getAmount() {
		return amount;
	}

	public String getBuyer() {
		return buyer;
	}

	public String getCategory() {
		return category;
	}

	public String getId() {
		return id;
	}

	public String getLoginer() {
		return loginer;
	}

	public String getPayDate() {
		return payDate;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLoginer(String loginer) {
		this.loginer = loginer;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

}
