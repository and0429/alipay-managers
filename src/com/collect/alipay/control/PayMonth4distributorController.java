package com.collect.alipay.control;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.PayMonth4Distributor;
import com.collect.alipay.service.PayMonth4DistributorService;

/**
 * 
 * @author zhangkai
 *
 */
@RestController
@SessionAttributes("loginer")
@RequestMapping("/paymonth4distributor")
public class PayMonth4distributorController {

	@Inject
	private PayMonth4DistributorService PayMonth4DistributorService;

	/**
	 * 
	 * @param p4d
	 * @param distributorId
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/paymonths", method = RequestMethod.POST)
	public Object paymonths(PayMonth4Distributor p4d, String distributorId, ModelMap map) {

		Loginer loginer = (Loginer) map.get("loginer");

		return PayMonth4DistributorService.getPager(p4d, loginer, distributorId);

	}
}
