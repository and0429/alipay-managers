package com.collect.alipay.domain;

/**
 * 支付详情
 * 
 * @author zhangkai
 *
 */
public class PayDetail {

	/**
	 * 买家支付 宝用户号,以 2088 开头的纯 16 位数字
	 */
	private String buyer_id;

	/**
	 * 该交易在支付宝系统中的交 易流水号。 最短 16 位，最长 64 位
	 */
	private String trade_no;

	/**
	 * 通知的发送时间。 格式为 yyyy-MM-dd HH:mm:ss。
	 */
	private String notify_time;

	/**
	 * 它在支付宝的交易明细中排 在第一列，对于财务对账尤为 重要。是请求时对应的参数， 原样通知回来。
	 */
	private String subject;

	/**
	 * 对应商户网站的订单系统中 的唯一订单号，非支付宝交易 号。 需保证在商户网站中的唯一 性。是请求时对应的参数，原 样返回。
	 */
	private String out_trade_no;

	/**
	 * 交易状态 WAIT_BUYER_PAY 交易创建，等待买家付款。 TRADE_CLOSED z 在指定时间段内未支付时关闭的交易； z
	 * 在交易完成全额退款成功时关闭的交易。 TRADE_SUCCESS 交易成功，且可对该交易做操作，如：多级分润、退款等。 TRADE_PENDING
	 * 等待卖家收款（买家付款后，如果卖家账号被冻结）。 TRADE_FINISHED 交易成功且结束，即不可再做任何操作
	 */
	private String trade_status;

	/**
	 * 付款时间 yyyy-mm-dd HH:mm:ss
	 */
	private String gmt_payment;

	/**
	 * 买家支付 宝账号
	 */
	private String buyer_email;

	/**
	 * 交易金额
	 */
	private String total_fee;

	/**
	 * 卖家支付宝账号对应的支付 宝唯一用户号。 以 2088 开头的纯 16 位数字。
	 */
	private String seller_id;

	/**
	 * 
	 */
	private String notify_id;

	/**
	 * 卖家支付宝账号，可以是 email 和手机号码。
	 */
	private String seller_email;

	/**
	 * 登录者
	 */
	private String longiner;

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		this.longiner = out_trade_no.replaceAll("[0-9]", "");
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getLonginer() {
		return longiner;
	}

	public void setLonginer(String longiner) {
		this.longiner = longiner;
	}

}
