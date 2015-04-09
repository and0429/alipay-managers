package com.collect.alipay.service;

import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.Pay;
import com.collect.alipay.wsclient.PrecreateResponse;

/**
 * 支付的业务接口
 * 
 * @author zhangkai
 *
 */
public interface PayService extends BaseService<Pay> {

	/**
	 * 预支付
	 * 
	 * @param pays
	 *            详情集合
	 * @param total
	 *            总价钱
	 * @param loginer
	 *            登录用户
	 * @return 支付结果
	 */
	PrecreateResponse pay(String total, Loginer loginer);

}
