package com.collect.alipay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Loginer;
import com.collect.alipay.service.LoginerService;
import com.collect.alipay.service.PayMonth4LoginerService;

/**
 * 月总账单的业务类
 * 
 * @author zhangkai
 *
 */
@Named(value = "payMonth4LoginerService")
public class PayMonth4LoginerServiceImpl extends BaseServiceImpl<PayMonth4Loginer> implements PayMonth4LoginerService {

	@Inject
	private LoginerService loginerService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.PayMonth4LoginerService#getPager4Cust(com.
	 * collect.alipay.domain.PayMonth4Loginer,
	 * com.collect.alipay.domain.Loginer)
	 */
	@Override
	public DataTableDto<PayMonth4Loginer> getPager4Cust(PayMonth4Loginer pm, Loginer loginer) {

		String custId = loginer.getCustOrDistributorId();

		// get all loginer of loginer cust.
		List<Loginer> loginers = loginerService.getByCustId(custId);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pm", pm);
		params.put("loginers", loginers);

		List<PayMonth4Loginer> data = sqlSession.selectList(clazz.getName() + ".getPager4Cust", params);
		Integer count = sqlSession.selectOne(clazz.getName() + ".getCount4Cust", params);

		return new DataTableDto<PayMonth4Loginer>(loginer.getDraw(), count, data);

	}
}
