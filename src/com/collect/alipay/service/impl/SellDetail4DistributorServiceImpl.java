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
import com.collect.alipay.domain.SellDetail4Distributor;
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.service.SellDetail4DistributorService;
import com.collect.alipay.util.DistributorUtils;

/**
 * 分销商的销售明细
 * 
 * @author zhangkai
 *
 */
@Named
public class SellDetail4DistributorServiceImpl extends BaseServiceImpl<SellDetail4Distributor> implements SellDetail4DistributorService {

	@Inject
	private DistributorService distributorService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.SellDetail4DistributorService#getPager(com
	 * .collect.alipay.domain.SellDetail4Distributor,
	 * com.collect.alipay.domain.Loginer, java.lang.String)
	 */
	@Override
	public DataTableDto<SellDetail4Distributor> getPager(SellDetail4Distributor s4d, Loginer loginer, String distributorId) {

		List<SellDetail4Distributor> data = new ArrayList<SellDetail4Distributor>();
		DataTableDto<SellDetail4Distributor> dto = new DataTableDto<SellDetail4Distributor>(s4d.getDraw(), 0, data);

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

		params.put("sellDetail4Distributor", s4d);
		params.put("distributorIds", distributorIds);

		data = sqlSession.selectList(SellDetail4Distributor.class.getName() + ".getpager", params);
		Integer count = sqlSession.selectOne(SellDetail4Distributor.class.getName() + ".getCountWithCondition", params);

		dto.setData(data);
		dto.setRecordsTotal(count);
		dto.setRecordsFiltered(count);

		return dto;
	}

}
