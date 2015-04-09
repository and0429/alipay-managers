package com.collect.alipay.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.Pay;
import com.collect.alipay.service.PayService;
import com.collect.alipay.util.UUIDUtil;
import com.collect.alipay.wsclient.AlipayPayService;
import com.collect.alipay.wsclient.PrecreateRequest;
import com.collect.alipay.wsclient.PrecreateResponse;

/**
 * 支付的业务类
 * 
 * @author zhangkai
 *
 */
@Named
public class PayServiceImpl extends BaseServiceImpl<Pay> implements PayService {

	@Inject
	private AlipayPayService alipayService;
	
	@Inject
	private String notifyUrl;;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.PayService#pay(java.util.List,
	 * java.lang.String, com.collect.alipay.domain.Loginer)
	 */
	@Override
	public PrecreateResponse pay(String total, Loginer loginer) {

		PrecreateRequest pr = new PrecreateRequest();

		pr.setProductCode("QR_CODE_OFFLINE");
		pr.setRemark("102");
		pr.setSubject("102");
		pr.setTotal(total);
		pr.setTradeNo("101" + UUIDUtil.randomUUID());
		pr.setUser(loginer == null ? null : loginer.getUsername());
		pr.setNotifyUrl(notifyUrl);

		return alipayService.alipayPrecreate(pr);
	}

}
