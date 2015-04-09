package com.collect.alipay.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Distributor;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Cust;
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.service.PayMonth4CustService;
import com.collect.alipay.util.DistributorUtils;

/**
 * 商户支付月账单的业务实现类
 * 
 * @author zhangkai
 *
 */
@Named
public class PayMonth4CustServiceImpl extends BaseServiceImpl<PayMonth4Cust> implements PayMonth4CustService {

	@Inject
	private DistributorService distributorService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.PayMonth4CustService#getPager(com.collect.
	 * alipay.domain.PayMonth4Cust, com.collect.alipay.domain.Loginer)
	 */
	@Override
	public DataTableDto<PayMonth4Cust> getPager(PayMonth4Cust payMonth4Cust, Loginer loginer, String distributorId) {

		List<PayMonth4Cust> data = new ArrayList<PayMonth4Cust>();
		DataTableDto<PayMonth4Cust> dto = new DataTableDto<PayMonth4Cust>(payMonth4Cust.getDraw(), 0, data);

		if (loginer == null) {
			return dto;
		}

		if (loginer.getRole() == 3) {
			return dto;
		}

		if (distributorId == null)
			distributorId = loginer.getCustOrDistributorId();

		List<Distributor> allDistributors = distributorService.getAll(null);
		List<String> distributorIds = DistributorUtils.getAllNoChildDistributorById(allDistributors, distributorId);

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("payMonth4Cust", payMonth4Cust);
		params.put("distributorIds", distributorIds);

		data = sqlSession.selectList(PayMonth4Cust.class.getName() + ".getpager", params);
		Integer count = sqlSession.selectOne(PayMonth4Cust.class.getName() + ".getCountWithCondition", params);

		dto.setData(data);
		dto.setRecordsTotal(count);
		dto.setRecordsFiltered(count);

		return dto;
	}

}
