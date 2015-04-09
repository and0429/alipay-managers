package com.collect.alipay.control;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Loginer;
import com.collect.alipay.service.PayMonth4LoginerService;

/**
 * 月账单控制器
 * 
 * @author zhangkai
 *
 */
@RestController
@SessionAttributes(value = "loginer")
@RequestMapping(value = "/payMonth4Loginer")
public class PayMonth4LoginerController {

	@Inject
	private PayMonth4LoginerService payMonth4LoginerService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/payMonths", method = RequestMethod.POST)
	public Object payMonths(PayMonth4Loginer pm, ModelMap modelMap) {
		Loginer loginer = (Loginer) modelMap.get("loginer");
		return payMonth4LoginerService.getPager4Cust(pm, loginer);
	}
}
