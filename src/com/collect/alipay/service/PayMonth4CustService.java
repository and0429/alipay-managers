package com.collect.alipay.service;

import java.util.List;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Cust;

/**
 * 商户支付月账单的业务接口
 * 
 * @author zhangkai
 *
 */
public interface PayMonth4CustService extends BaseService<PayMonth4Cust> {

	/**
	 * 获取登录用户的月支付总汇
	 *
	 * @param payMonth4Cust
	 *            封装参数
	 * @param loginer
	 *            登录者
	 * @return 集合
	 */
	DataTableDto<PayMonth4Cust> getPager(PayMonth4Cust payMonth4Cust, Loginer loginer, String distributorId);

	/**
	 * 保存月总记录
	 */
	void saveStatistic4Cust();

	/**
	 * 获取月总记录
	 * 
	 * @return
	 */
	List<PayMonth4Cust> getStatistics4Cust();

	/**
	 * 获取商户的月账单（登陆者）
	 * 
	 * @param payMonth4Cust
	 * @param loginer
	 * @return
	 */
	Object getPayMonth4Loginer(PayMonth4Cust payMonth4Cust, Loginer loginer);

}
