package com.collect.alipay.service.impl;

import static org.apache.log4j.Logger.getLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Cust;
import com.collect.alipay.domain.Distributor;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Cust;
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.service.PayMonth4CustService;
import com.collect.alipay.util.DistributorUtils;
import com.collect.alipay.util.UUIDUtil;

/**
 * 商户支付月账单的业务实现类
 * 
 * @author zhangkai
 *
 */
@Named("payMonth4CustService")
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.PayMonth4CustService#getStatistics4Cust()
	 */
	public List<PayMonth4Cust> getStatistics4Cust() {
		return sqlSession.selectList(clazz.getName() + ".getStatistics4Cust");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.PayMonth4CustService#saveStatistic4Cust()
	 */
	@Override
	public void saveStatistic4Cust() {

		List<PayMonth4Cust> payMonth4Custs = this.getStatistics4Cust();

		for (int i = 0; i < payMonth4Custs.size(); i++) {

			PayMonth4Cust pm = payMonth4Custs.get(i);
			pm.setId(UUIDUtil.randomUUID());

			this.save(pm);
		}

		getLogger(this.clazz).info("======================= Statistic the pay of month for cust================");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.PayMonth4CustService#getPayMonth4Loginer(com
	 * .collect.alipay.domain.PayMonth4Cust, com.collect.alipay.domain.Loginer)
	 */
	@Override
	public Object getPayMonth4Loginer(PayMonth4Cust payMonth4Cust, Loginer loginer) {

		List<PayMonth4Cust> data = new ArrayList<PayMonth4Cust>();
		DataTableDto<PayMonth4Cust> dto = new DataTableDto<PayMonth4Cust>(payMonth4Cust.getDraw(), 0, data);

		if (loginer == null) {
			return dto;
		}

		if (loginer.getRole() != 3) {
			return dto;
		}

		Cust cust = new Cust();
		cust.setId(loginer.getCustOrDistributorId());

		payMonth4Cust.setCust(cust);

		data = sqlSession.selectList(PayMonth4Cust.class.getName() + ".getPayMonth4Loginer", payMonth4Cust);
		Integer count = sqlSession.selectOne(PayMonth4Cust.class.getName() + ".getCountPayMonth4Loginer", payMonth4Cust);

		dto.setData(data);
		dto.setRecordsTotal(count);
		dto.setRecordsFiltered(count);

		return dto;

	}

}
