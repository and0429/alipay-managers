package com.collect.alipay.service;

import com.collect.alipay.domain.GoodsCategory;

/**
 * 商品类别管理的业务类
 * 
 * @author zhangkai
 *
 */
public interface GoodsCategoryService extends BaseService<GoodsCategory> {

	/**
	 * 根据名称获取实体
	 * 
	 * @param name 名称
	 * @return null or entity
	 */
	GoodsCategory getByNameAndCustId(GoodsCategory gc);

}
