package com.collect.alipay.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.control.dto.Status;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.Pay;
import com.collect.alipay.service.PayService;
import com.collect.alipay.util.UUIDUtil;
import com.collect.alipay.wsclient.AlipayPayService;
import com.collect.alipay.wsclient.PrecreateRequest;

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
	private String notifyUrl;

	@Inject
	private String tradeNoPrefix;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.collect.alipay.service.PayService#pay(java.util.List,
	 * java.lang.String, com.collect.alipay.domain.Loginer)
	 */
	@Override
	public Object pay(Pay pay, Loginer loginer) {

		String tradeNo = tradeNoPrefix + UUIDUtil.randomUUID();

		pay.setId(tradeNo);
		pay.setLoginer(loginer.getUsername());
		pay.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		pay.setCustId(loginer.getCustOrDistributorId());

		int result = this.save(pay);

		switch (pay.getPayWay()) {
		case 0:
			PrecreateRequest pr = new PrecreateRequest();

			pr.setProductCode("QR_CODE_OFFLINE");
			pr.setRemark("102");
			pr.setSubject("102");
			pr.setTotal(pay.getAmount() + "");
			pr.setTradeNo(tradeNo);
			pr.setUser(loginer == null ? "" : loginer.getUsername());
			pr.setNotifyUrl(notifyUrl);

			return alipayService.alipayPrecreate(pr);

		case 1:
			return new Status(result);
		}

		return new Status();

	}

}
