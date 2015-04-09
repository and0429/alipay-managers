package com.collect.alipay.domain;

/**
 * 商户模型
 * 
 * @author zhangkai
 * 
 */
public class Cust extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 地址
	 */
	private String addr;
	/**
	 * 负责人
	 */
	private String manager;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 所属分销商
	 */
	private Distributor distributor;

	/**
	 * 用于zTree加载
	 */
	private String pId;

	public String getAddr() {
		return addr;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public String getId() {
		return id;
	}

	public String getManager() {
		return manager;
	}

	public String getName() {
		return name;
	}

	public String getpId() {
		return pId;
	}

	public String getTel() {
		return tel;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
