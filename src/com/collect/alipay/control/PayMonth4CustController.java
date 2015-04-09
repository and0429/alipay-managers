package com.collect.alipay.control;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.domain.Cust;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Cust;
import com.collect.alipay.service.PayMonth4CustService;

/**
 * 商户支付月账单的控制器
 * 
 * @author zhangkai
 *
 */
@RestController
@RequestMapping(value = "/paymonth4cust")
@SessionAttributes(value = "loginer")
public class PayMonth4CustController {

	@Inject
	private PayMonth4CustService payMonth4CustService;

	@RequestMapping(value = "/paymonth4custs", method = RequestMethod.POST)
	public Object paymonth4custs(PayMonth4Cust payMonth4Cust, String distributorId, String custName, ModelMap modelMap) {
		Cust c = new Cust();
		c.setName(custName);
		payMonth4Cust.setCust(c);

		Loginer loginer = (Loginer) modelMap.get("loginer");

		return payMonth4CustService.getPager(payMonth4Cust, loginer, distributorId);
	}

}
