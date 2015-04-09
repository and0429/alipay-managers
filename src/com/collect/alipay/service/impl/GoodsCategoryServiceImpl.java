package com.collect.alipay.service.impl;

import javax.inject.Named;

import com.collect.alipay.domain.GoodsCategory;
import com.collect.alipay.service.GoodsCategoryService;

/**
 * 商品类别的业务类
 * 
 * @author zhangkai
 *
 */
@Named
public class GoodsCategoryServiceImpl extends BaseServiceImpl<GoodsCategory> implements GoodsCategoryService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.GoodsCategoryService#getByName(java.lang.String
	 * )
	 */
	@Override
	public GoodsCategory getByNameAndCustId(GoodsCategory gc) {
		return sqlSession.selectOne(clazz.getName() + ".getByNameAndCustId", gc);
	}
}
