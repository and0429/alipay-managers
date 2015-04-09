package com.collect.alipay.domain;

/**
 * 分销商
 * 
 * @author zhangkai
 *
 */
public class Distributor extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 名字
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
	 * 父级分销商
	 */
	private Distributor pPistributor;

	/**
	 * 父级分销商Id 用于zTree
	 */
	private String pId;
	/**
	 * 是否有子分销商<br/>
	 * 1:有<br/>
	 * 0:没有
	 * 
	 */
	private Integer hasChild;

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * 是否默认为打开
	 */
	private boolean open = true;

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Distributor getpPistributor() {
		return pPistributor;
	}

	public void setpPistributor(Distributor pPistributor) {
		this.pPistributor = pPistributor;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

}
