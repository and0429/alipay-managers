package com.collect.alipay.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import com.collect.alipay.control.dto.Status;
import com.collect.alipay.domain.Cust;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.Pay;
import com.collect.alipay.service.CustService;
import com.collect.alipay.service.PayService;
import com.collect.alipay.wsclient.AlipayPayService;
import com.collect.alipay.wsclient.CreateandpayRequest;
import com.collect.alipay.wsclient.PrecreateRequest;
import com.collect.alipay.wsclient.RefundRequest;
import com.collect.alipay.wsclient.RefundResponse;

/**
 * 支付的业务类
 * 
 * @author zhangkai
 *
 */
@Named
public class PayServiceImpl extends BaseServiceImpl<Pay> implements PayService {

	public static final String REFUND_ERROR = "退款错误";

	@Inject
	private AlipayPayService alipayService;

	@Inject
	private CustService custService;

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

		String tradeNo = tradeNoPrefix + System.currentTimeMillis() + "";

		Cust cust = custService.getById(loginer.getCustOrDistributorId());

		pay.setId(tradeNo);
		pay.setLoginer(loginer.getUsername());
		pay.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		pay.setCustId(loginer.getCustOrDistributorId());
		pay.setStatus("WAIT_BUYER_PAY");

		this.save(pay);

		switch (pay.getPayWay()) {
		case 0:
			PrecreateRequest pr = new PrecreateRequest();

			pr.setProductCode("QR_CODE_OFFLINE");
			pr.setRemark("102");
			pr.setSubject("102");
			pr.setTotal(pay.getAmount().floatValue() + "");
			pr.setTradeNo(tradeNo);
			pr.setUser(loginer == null ? "" : loginer.getUsername());
			pr.setNotifyUrl(notifyUrl);
			pr.setPartner(cust.getAlipayusername());
			pr.setKey(cust.getAlipaypassword());

			return alipayService.alipayPrecreate(pr);

		case 1:
			Pay pay2 = this.getById(tradeNo);
			pay2.setStatus("TRADE_SUCCESS");
			int result = this.update(pay2);
			return new Status(result);

		case 2:
			CreateandpayRequest cr = new CreateandpayRequest();

			cr.setDynamicId(pay.getCode());
			cr.setDynamicIdType("bar_code");
			cr.setProductCode("BARCODE_PAY_OFFLINE");
			cr.setRemark("102");
			cr.setSubject("102");
			cr.setTotal(pay.getAmount().floatValue() + "");
			cr.setTradeNo(tradeNo);
			cr.setUser(loginer == null ? "" : loginer.getUsername());
			cr.setNotifyUrl(notifyUrl);
			cr.setPartner(cust.getAlipayusername());
			cr.setKey(cust.getAlipaypassword());

			return alipayService.alipayCreateandPay(cr);

		}

		return new Status();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.collect.alipay.service.PayService#refund(com.collect.alipay.domain
	 * .Pay)
	 */
	@Override
	public Object refund(Pay pay) {

		String tradeNo = pay.getTradeNo();

		Pay payFromdb = this.getById(tradeNo);

		Cust cust = custService.getById(payFromdb.getCustId());

		if (cust == null) {
			return new Status(0, REFUND_ERROR);
		}

		RefundRequest rfreq = new RefundRequest();
		rfreq.setAmount(pay.getRefundTotal().floatValue() + "");
		rfreq.setTradeNo(tradeNo);
		rfreq.setPartner(cust.getAlipayusername());
		rfreq.setKey(cust.getAlipaypassword());

		RefundResponse rfresp = alipayService.alipayRefund(rfreq);

		if (rfresp == null) {
			return new Status(0, REFUND_ERROR);
		}

		if ("F".equals(rfresp.getSuccess())) {
			return new Status(0, REFUND_ERROR);
		}

		Pay payFromDb = this.getById(pay.getTradeNo());

		payFromDb.setAmount(payFromDb.getAmount() - pay.getRefundTotal());
		payFromDb.setRefundTotal(pay.getRefundTotal());
		payFromDb.setRefundTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		payFromDb.setStatus("REFUND_SUCCESS");

		int result = this.update(payFromDb);

		return new Status(result);
	}
}
