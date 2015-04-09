package com.collect.alipay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Cust;
import com.collect.alipay.domain.Distributor;
import com.collect.alipay.service.CustService;
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.util.DistributorUtils;

/**
 * 用户业务实现类
 * 
 * @author zhangkai
 *
 */
@Named
public class CustServiceImpl extends BaseServiceImpl<Cust> implements CustService {

	@Inject
	private DistributorService distributorService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.CustService#getPagerWithMapCodition(com.collect
	 * .alipay.domain.Cust, java.lang.String)
	 */
	@Override
	public DataTableDto<Cust> getPagerWithMapCodition(Cust cust, String distributorId) {

		List<Distributor> all = distributorService.getAll(null);

		List<String> distributorIds = DistributorUtils.getAllNoChildDistributorById(all, distributorId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cust", cust);
		map.put("distributorIds", distributorIds);

		List<Cust> data = sqlSession.selectList(clazz.getName() + ".getPagerWithMapCodition", map);
		Integer count = sqlSession.selectOne(clazz.getName() + ".getPagerWithMapCoditionCount", map);

		return new DataTableDto<Cust>(cust.getDraw(), count, data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.CustService#getByDistributorId(java.lang.String
	 * )
	 */
	@Override
	public List<Cust> getByDistributorId(String distributorId) {
		return sqlSession.selectList(clazz.getName() + ".getByDistributorId", distributorId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.CustService#getByDistributors(java.util.List)
	 */
	@Override
	public List<Cust> getByDistributorIds(List<String> distributorIds) {
		return sqlSession.selectList(clazz.getName() + ".getByDistributorIds", distributorIds);
	}

}
