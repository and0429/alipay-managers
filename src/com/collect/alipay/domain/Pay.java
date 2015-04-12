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
	private Float amount;

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
	private Float refundTotal;

	/**
	 * 退款时间
	 */
	private String refundTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
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

	public Float getRefundTotal() {
		return refundTotal;
	}

	public void setRefundTotal(Float refundTotal) {
		this.refundTotal = refundTotal;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	
	

}
