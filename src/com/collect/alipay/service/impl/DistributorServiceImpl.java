/**
 * 
 */
package com.collect.alipay.service.impl;

import java.util.List;

import javax.inject.Named;

import com.collect.alipay.domain.Distributor;
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.util.UUIDUtil;

/**
 * @author zhangkai
 *
 */
@Named
public class DistributorServiceImpl extends BaseServiceImpl<Distributor> implements DistributorService {

	/*
	 * s(non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.DistributorService#saveAndUpdateParentStatus
	 * (com.collect.alipay.domain.Distributor)
	 */
	@Override
	public int saveAndUpdateParentStatus(Distributor distributor) {

		distributor.setId(UUIDUtil.randomUUID());
		int result = this.save(distributor);

		Distributor dis = new Distributor();
		dis.setId(distributor.getpId());
		dis.setHasChild(1);

		this.update(dis);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.DistributorService#getByParentId(java.lang
	 * .String)
	 */
	@Override
	public List<Distributor> getByParentId(String parentId) {
		return sqlSession.selectList(clazz.getName() + ".getByParentId", parentId);
	}

}
