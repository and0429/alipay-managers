package com.collect.alipay.domain;

/**
 * goods category
 * 
 * @author zhangkai
 *
 */
public class GoodsCategory extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 类别名称
	 */
	private String name;

	/**
	 * 商户Id
	 */
	private String custId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

}
