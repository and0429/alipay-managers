package com.collect.alipay.control;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.control.dto.Status;
import com.collect.alipay.domain.Cust;
import com.collect.alipay.domain.Distributor;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.service.CustService;
import com.collect.alipay.util.UUIDUtil;

/**
 * 用户的控制器类
 * 
 * @author zhangkai
 *
 */
@RestController
@RequestMapping(value = "/cust")
@SessionAttributes(value = "loginer")
public class CustController {

	@Inject
	private CustService custService;

	/**
	 * add a entity
	 * 
	 * @param cust
	 *            实体
	 * @param distributorId
	 *            父级分销商Id
	 * @return 状态
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(Cust cust, String distributorId) {
		if (!"-1".equals(distributorId)) {
			Distributor d = new Distributor();
			d.setId(distributorId);
			cust.setDistributor(d);
		}

		cust.setId(UUIDUtil.randomUUID());

		return new Status(custService.save(cust));
	}

	/**
	 * delete by id
	 * 
	 * @param id
	 *            id
	 * @return status;
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object delete(@PathVariable String id) {
		int result = custService.delete(id);
		return new Status(result);
	}

	/**
	 * 用户列表
	 * 
	 * @return 组装好的表格数据
	 */
	@RequestMapping(value = "/custs", method = RequestMethod.POST)
	public Object users(Cust cust, String distributorId, ModelMap modelMap) {

		Loginer loginer = (Loginer) modelMap.get("loginer");

		if (distributorId == null) {
			distributorId = loginer.getCustOrDistributorId();
		}

		return custService.getPagerWithMapCodition(cust, distributorId);
	}

	/**
	 * 根据Id获取
	 * 
	 * @param id
	 *            id
	 * @return 查到的数据
	 */
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable String id) {
		return custService.getById(id);
	}

	/**
	 * 跟新
	 * 
	 * @param cust
	 * @param distributorId
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Cust cust, String distributorId) {
		if (!"-1".equals(distributorId)) {
			Distributor d = new Distributor();
			d.setId(distributorId);
			cust.setDistributor(d);
		}

		return new Status(custService.update(cust));
	}

	/**
	 * 记载select
	 * 
	 * @param distributorId
	 *            分销商id
	 * @return
	 */
	@RequestMapping(value = "/getSelect/{distributorId}")
	public Object getSelect(@PathVariable String distributorId) {
		return custService.getByDistributorId(distributorId);
	}

}
