/**
 * 
 */
package com.collect.alipay.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSession;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.domain.BaseModel;
import com.collect.alipay.service.BaseService;

/**
 * 业务类的基础接口实现类
 * 
 * @author zhangkai
 *
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Inject
	@Named("sqlSession")
	protected SqlSession sqlSession;

	protected Class<T> clazz;

	/**
	 * 构造方法
	 */
	@SuppressWarnings("unchecked")
	protected BaseServiceImpl() {

		if (clazz == null) {

			ParameterizedType pf = (ParameterizedType) this.getClass().getGenericSuperclass();
			Type[] types = pf.getActualTypeArguments();

			clazz = (Class<T>) types[0];
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public Integer save(T t) {
		return sqlSession.insert(clazz.getName() + ".save", t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Integer delete(Serializable id) {
		return sqlSession.delete(clazz.getName() + ".delete", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.BaseService#update(java.lang.Object)
	 */
	@Override
	public Integer update(T t) {
		return sqlSession.delete(clazz.getName() + ".update", t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.BaseService#getById(java.io.Serializable)
	 */
	@Override
	public T getById(Serializable id) {
		return sqlSession.selectOne(clazz.getName() + ".getById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	@Override
	public Integer getCount(T condition) {

		if (condition == null) {
			return sqlSession.selectOne(clazz.getName() + ".getCount");
		} else {
			return sqlSession.selectOne(clazz.getName() + ".getCountWithCondition", condition);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.BaseService#getAll(java.lang.Object)
	 */
	@Override
	public List<T> getAll(T condition) {
		if (condition == null) {
			return sqlSession.selectList(clazz.getName() + ".getAll");
		} else {
			return sqlSession.selectList(clazz.getName() + ".getpager", condition);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.BaseService#getPager(java.lang.Object)
	 */
	@Override
	public DataTableDto<T> getPager(T condition) {

		List<T> list = this.getAll(condition);

		int total = this.getCount(condition);
		int draw = 0;
		if (condition instanceof BaseModel) {
			draw = ((BaseModel) condition).getDraw();
		}

		return new DataTableDto<T>(draw, total, list);
	}

}
