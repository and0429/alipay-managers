package com.collect.alipay.service;

import java.util.List;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Loginer;

/**
 * 月账单业务类
 * 
 * @author zhangkai
 *
 */
public interface PayMonth4LoginerService extends BaseService<PayMonth4Loginer> {

	/**
	 * 获取指定商户的月账单
	 * 
	 * @param pm
	 *            封装分页和条件参数
	 * @param loginer
	 *            登录用户
	 * @return 表格数据传输对象
	 */
	DataTableDto<PayMonth4Loginer> getPager4Cust(PayMonth4Loginer pm, Loginer loginer);

	/**
	 * 统计每月的月账单数据
	 * 
	 * @return
	 */
	List<PayMonth4Loginer> getStatistics4Cust();

	/**
	 * 保存商户每月的总账单
	 */
	void saveStatistic4Cust();

}
