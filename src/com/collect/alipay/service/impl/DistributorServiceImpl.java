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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.DistributorService#deleteAndUpdatePdistributor
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public int deleteAndUpdatePdistributor(String id, String pId) {

		int result = this.delete(id);

		List<Distributor> list = this.getByParentId(pId);
		/*
		 * 如果删除分销商后，父级没有子分销商了，修改Haschild字段
		 */
		if (list == null || list.isEmpty()) {
			Distributor dis = this.getById(pId);
			dis.setHasChild(0);
			this.update(dis);
		}

		// 删除此分销商的用户
		this.deleteLoginerBydistributorId(id);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.DistributorService#deleteLoginerBydistributorId
	 * (java.lang.String)
	 */
	@Override
	public int deleteLoginerBydistributorId(String id) {
		return sqlSession.delete(clazz.getName() + ".deleteLoginerBydistributorId", id);
	}

}
