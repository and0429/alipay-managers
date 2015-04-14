package com.collect.alipay.domain;

/**
 * 
 * @author zhangkai
 *
 */
public class PayMonth4Distributor extends BaseModel {

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
	private Distributor distributor;

	/**
	 * 总金额
	 */
	private float total;

	/**
	 * 提成
	 */
	private float deduct;

	/**
	 * 提成金额
	 */
	private float deductMoney;

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

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getDeduct() {
		return deduct;
	}

	public void setDeduct(float deduct) {
		this.deduct = deduct;
	}

	public float getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(float deductMoney) {
		this.deductMoney = deductMoney;
	}

}
