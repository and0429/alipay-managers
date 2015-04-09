package com.collect.alipay.control;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.Pay;
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
	public Object prePay(String total, ModelMap model) {
		Loginer loginer = (Loginer) model.get("loginer");
		return payService.pay(total, loginer);
	}

	/**
	 * 支付后接受通知
	 */
	@RequestMapping(value = "/alipayNotify")
	public String alipayNotify(Map<String, Object> params) {

		Set<Entry<String, Object>> set = params.entrySet();

		for (Entry<String, Object> entry : set) {

			System.out.println(entry.getKey() + "=================" + entry.getValue());
		}
		
		return "success";
	}

}
