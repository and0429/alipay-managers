package com.collect.alipay.service;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.SellDetail4Distributor;

public interface SellDetail4DistributorService {

	/**
	 * 
	 * @param s4d
	 * @param loginer
	 * @param distributorId
	 * @return
	 */
	DataTableDto<SellDetail4Distributor> getPager(SellDetail4Distributor s4d, Loginer loginer, String distributorId);

}
