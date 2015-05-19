package com.collect.alipay.service.impl;

import static org.apache.log4j.Logger.getLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Distributor;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Distributor;
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.service.PayMonth4DistributorService;
import com.collect.alipay.util.DistributorUtils;
import com.collect.alipay.util.UUIDUtil;

/**
 * 
 * @author zhangkai
 *
 */
@Named("payMonth4DistributorService")
public class PayMonth4DistributorServiceImpl extends BaseServiceImpl<PayMonth4Distributor> implements PayMonth4DistributorService {

	@Inject
	private DistributorService distributorService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.PayMonth4DistributorService#saveStatistic4Cust
	 * ()
	 */
	@Override
	public void saveStatistic4Distributor() {

		List<Distributor> distributors = distributorService.getAll(null);

		for (Distributor distributor : distributors) {

			String distributorId = distributor.getId();

			List<String> lastDistributorIds = DistributorUtils.getChildDistributorById(distributors, distributorId);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("list", lastDistributorIds);
			params.put("distributorId", distributorId);

			PayMonth4Distributor PayMonth4Distributor = this.getPayMonth4DistributorByLastDistributorIds(lastDistributorIds);

			PayMonth4Distributor.setId(UUIDUtil.randomUUID());
			PayMonth4Distributor.setDistributor(distributor);

			this.save(PayMonth4Distributor);

			getLogger(this.clazz).info("======================= Statistic the pay of month for distributor================");

		}

	}

	/**
	 * 根据分销商的最后一级子分销商的集合计算此父级绯绡上的总账
	 * 
	 * @param lastDistributorIds
	 * @return
	 */
	private PayMonth4Distributor getPayMonth4DistributorByLastDistributorIds(List<String> lastDistributorIds) {
		return sqlSession.selectOne(clazz.getName() + ".getPayMonth4DistributorByLastDistributorIds", lastDistributorIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.PayMonth4DistributorService#getPager(com.collect
	 * .alipay.domain.PayMonth4Distributor, com.collect.alipay.domain.Loginer,
	 * java.lang.String)
	 */
	@Override
	public DataTableDto<PayMonth4Distributor> getPager(PayMonth4Distributor p4d, Loginer loginer, String distributorId) {

		List<PayMonth4Distributor> data = new ArrayList<PayMonth4Distributor>();
		DataTableDto<PayMonth4Distributor> dto = new DataTableDto<PayMonth4Distributor>(p4d.getDraw(), 0, data);

		if (loginer == null) {
			return dto;
		}

		if (loginer.getRole() == 3) {
			return dto;
		}

		if (distributorId == null)
			distributorId = loginer.getCustOrDistributorId();

		Distributor distributor = new Distributor();
		distributor.setId(distributorId);
		p4d.setDistributor(distributor);

		data = sqlSession.selectList(PayMonth4Distributor.class.getName() + ".getpager", p4d);
		Integer count = sqlSession.selectOne(PayMonth4Distributor.class.getName() + ".getCountWithCondition", p4d);

		dto.setData(data);
		dto.setRecordsTotal(count);
		dto.setRecordsFiltered(count);

		return dto;
	}

}
