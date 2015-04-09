package com.collect.alipay.control;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.domain.Cust;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.domain.SellDetail4Distributor;
import com.collect.alipay.service.SellDetail4DistributorService;

/**
 * 
 * @author zhangkai
 *
 */
@RestController
@RequestMapping(value = "/sellDetail4Distributor")
@SessionAttributes(value = "loginer")
public class SellDetail4DistributorController {

	@Inject
	private SellDetail4DistributorService sellDetail4DistributorService;

	/**
	 * 
	 * @param s4d
	 * @param distributorId
	 * @param custName
	 * @return
	 */
	@RequestMapping(value = "/getSellDetail4Distributors", method = RequestMethod.POST)
	public Object getSellDetail4Distributors(SellDetail4Distributor s4d, String distributorId, String custName, ModelMap modelMap) {
		Cust c = new Cust();
		c.setName(custName);
		s4d.setCust(c);

		Loginer loginer = (Loginer) modelMap.get("loginer");

		return sellDetail4DistributorService.getPager(s4d, loginer, distributorId);

	}

}
