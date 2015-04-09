package com.collect.alipay.service;

import java.util.List;

import com.collect.alipay.domain.Distributor;

/**
 * 分销商业务接口
 * 
 * @author zhangkai
 *
 */
public interface DistributorService extends BaseService<Distributor> {

	/**
	 * 保存实体和更新父级的haschild
	 * 
	 * @param distributor
	 *            传递参数的实体
	 * @return 保存后影响的行数
	 */
	int saveAndUpdateParentStatus(Distributor distributor);

	/**
	 * 根据父级Id查询松油的分销商
	 * 
	 * @param parentId
	 * @return
	 */
	List<Distributor> getByParentId(String parentId);

}
