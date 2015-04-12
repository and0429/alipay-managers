package com.collect.alipay.control;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.Pay;
import com.collect.alipay.domain.PayDetail;
import com.collect.alipay.service.PayService;

/**
 * 支付业务的控制器
 * 
 * @author zhangkai
 *
 */
@RestController
@RequestMapping(value = "/pay")
@SessionAttributes("loginer")
public class PayController {

	@Inject
	private PayService payService;

	/**
	 * 登录用户的支付详情
	 * 
	 * @param pay
	 *            封装参数
	 * @param modelMap
	 *            map
	 * @return 详情的集合
	 */
	@RequestMapping(value = "/sellDetail", method = RequestMethod.POST)
	public Object sellDetail(Pay pay, ModelMap modelMap) {
		Loginer loginer = (Loginer) modelMap.get("loginer");
		if (loginer != null) {
			pay.setLoginer(loginer.getUsername());
		}
		return payService.getPager(pay);
	}

	/**
	 * 预支付
	 * 
	 * @param pays
	 * @param total
	 * @return
	 */
	@RequestMapping(value = "/prepay", method = RequestMethod.POST)
	public Object prePay(Pay pay, ModelMap model) {
		Loginer loginer = (Loginer) model.get("loginer");
		return payService.pay(pay, loginer);
	}

	/**
	 * 支付后接受通知
	 */
	@RequestMapping(value = "/alipayNotify", method = RequestMethod.POST)
	public String alipayNotify(PayDetail payDetail) {

		Pay pay = new Pay();
		pay.setAmount(Float.parseFloat(payDetail.getTotal_fee()));
		pay.setCategory(payDetail.getSubject());
		pay.setId(payDetail.getOut_trade_no());
		pay.setPayDate(payDetail.getGmt_payment());
		pay.setBuyer(payDetail.getBuyer_email());
		pay.setTradeNo(payDetail.getTrade_no());
		pay.setStatus(payDetail.getTrade_status());

		payService.update(pay);

		return "success";
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getStatus/{id}", method = RequestMethod.GET)
	public Object getStatus(@PathVariable String id) {
		return payService.getById(id);
	}

	/**
	 * 退款
	 * 
	 * @return
	 */
	@RequestMapping(value = "/refund/{tradeNo}/{refundTotal}", method = RequestMethod.GET)
	public Object refund(Pay pay) {
		return payService.refund(pay);
	}
}
