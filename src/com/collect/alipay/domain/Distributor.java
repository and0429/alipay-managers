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

	/**
	 * 分成率
	 */
	private Float deduct;

	/**
	 * 是否默认为打开
	 */
	private boolean open = true;

	public String getAddr() {
		return addr;
	}

	public Float getDeduct() {
		return deduct;
	}

	public Integer getHasChild() {
		return hasChild;
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

	public boolean getOpen() {
		return open;
	}

	public String getpId() {
		return pId;
	}

	public Distributor getpPistributor() {
		return pPistributor;
	}

	public String getTel() {
		return tel;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setDeduct(Float deduct) {
		this.deduct = deduct;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
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

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setpPistributor(Distributor pPistributor) {
		this.pPistributor = pPistributor;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
