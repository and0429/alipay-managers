package com.collect.alipay.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Cust;
import com.collect.alipay.domain.Distributor;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.service.CustService;
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.service.LoginerService;
import com.collect.alipay.util.DistributorUtils;

/**
 * @author zhangkai
 *
 */
@Named
public class LoginerServiceImpl extends BaseServiceImpl<Loginer> implements LoginerService {

	@Inject
	private DistributorService disService;

	@Inject
	private CustService custService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.LoginerService#check(com.collect.alipay.domain
	 * .Loginer)
	 */
	@Override
	public Loginer check(Loginer loginer) {

		loginer.setPassword(DigestUtils.md5Hex(loginer.getPassword()));

		return sqlSession.selectOne(clazz.getName() + ".check", loginer);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.LoginerService#getByUsername(java.lang.String)
	 */
	@Override
	public Loginer getByUsername(String username) {
		return sqlSession.selectOne(clazz.getName() + ".getByUsername", username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.LoginerService#getZtreeData()
	 */
	@Override
	public List<Object> getZtreeData(Loginer loginer) {

		List<Object> result = new ArrayList<Object>();

		if (loginer.getRole() == 3) {
			Cust cust = custService.getById(loginer.getCustOrDistributorId());
			result.add(cust);
			return result;
		}

		List<Distributor> all = disService.getAll(null);

		List<Distributor> list = DistributorUtils.getSelfAndChild(all, loginer.getCustOrDistributorId());

		List<String> distributorIds = DistributorUtils.getAllNoChildDistributorById(list, loginer.getCustOrDistributorId());

		List<Cust> custs = custService.getByDistributorIds(distributorIds);

		result.addAll(list);
		result.addAll(custs);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.LoginerService#getLoginers(com.collect.alipay
	 * .domain.Loginer)
	 */
	@Override
	public DataTableDto<Loginer> getLoginers(Loginer loginer) {

		DataTableDto<Loginer> dataTables = this.getPager(loginer);

		List<Loginer> loginers = dataTables.getData();

		if (!loginers.isEmpty()) {
			for (Loginer loginer2 : loginers) {

				switch (loginer2.getRole()) {
				case 2:
					Distributor dis = disService.getById(loginer2.getCustOrDistributorId());
					if (dis != null) {
						loginer2.setCustOrDistributorName(dis.getName());
					}
					break;
				case 3:
					Cust cust = custService.getById(loginer2.getCustOrDistributorId());
					if (cust != null) {
						loginer2.setCustOrDistributorName(cust.getName());
					}
					break;
				}
			}
		}
		return dataTables;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.LoginerService#getByCustId(java.lang.String)
	 */
	@Override
	public List<Loginer> getByCustId(String custId) {
		return sqlSession.selectList(clazz.getName() + ".getByCustId", custId);
	}

}
