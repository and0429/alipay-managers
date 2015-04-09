package com.collect.alipay.domain;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.validator.constraints.NotEmpty;

/****
 * 登陆者模型
 * 
 * @author zhangkai
 *
 */
public class Loginer extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public interface WithoutPasswordView {
	};

	public interface WithPasswordView extends WithoutPasswordView{
	};

	/**
	 * 主键
	 */
	private String id;

	/***
	 * 用户名
	 */
	@NotEmpty(message = "用户名不能为空")
	private String username;

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不能为空")
	private String password;

	/**
	 * 角色
	 */
	private Integer role;

	/**
	 * 分销商或者商户的Id<br/>
	 * 1、代理商<br/>
	 * 2、分销商<br/>
	 * 3、商户
	 */
	private String custOrDistributorId;

	/**
	 * 所属的名称
	 */
	private String custOrDistributorName;

	/**
	 * 登录信息
	 */
	private String loginMessage;

	public String getCustOrDistributorId() {
		return custOrDistributorId;
	}

	public String getCustOrDistributorName() {
		return custOrDistributorName;
	}

	public String getId() {
		return id;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	@JsonView(WithPasswordView.class)
	public String getPassword() {
		return password;
	}

	public Integer getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public void setCustOrDistributorId(String custOrDistributorId) {
		this.custOrDistributorId = custOrDistributorId;
	}

	public void setCustOrDistributorName(String custOrDistributorName) {
		this.custOrDistributorName = custOrDistributorName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
