package com.collect.alipay.service;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Distributor;

/**
 * 分销商月总账单
 * 
 * @author zhangkai
 *
 */
public interface PayMonth4DistributorService extends BaseService<PayMonth4Distributor> {

	/**
	 * 保存分销商的月总帐单
	 */
	void saveStatistic4Distributor();

	
	DataTableDto<PayMonth4Distributor> getPager(PayMonth4Distributor p4d, Loginer loginer, String distributorId);
}
