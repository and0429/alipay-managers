package com.collect.alipay.control;

import java.util.ArrayList;
import java.util.List;

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
import com.collect.alipay.service.DistributorService;
import com.collect.alipay.util.DistributorUtils;

/**
 * 分销商管理控制器
 * 
 * @author zhangkai
 *
 */
@RestController
@SessionAttributes(value = "loginer")
@RequestMapping("/distributor")
public class DistributorController {

	@Inject
	private DistributorService distributorService;

	@Inject
	private CustService custService;

	/**
	 * 获取Ztree数据
	 * 
	 * @return 数据集合
	 */
	@RequestMapping(value = "/getTree", method = RequestMethod.GET)
	public Object getAllDistributor() {
		return distributorService.getAll(null);
	}

	/**
	 * 获取登陆者的子树
	 * 
	 * @return 登录人员的子集
	 */
	@RequestMapping(value = "getZtree4Loginer", method = RequestMethod.GET)
	public Object getZtree4Longienr(ModelMap map) {

		List<Distributor> distributors = new ArrayList<Distributor>();

		Loginer loginer = (Loginer) map.get("loginer");

		if (loginer == null) {
			return distributors;
		}

		if (loginer.getRole() == 3) {
			return distributors;
		}

		String distributorId = loginer.getCustOrDistributorId();
		List<Distributor> allDistributors = distributorService.getAll(null);

		return DistributorUtils.getSelfAndChild(allDistributors, distributorId);

	}

	/**
	 * 获取所有的分销商
	 * 
	 * @param flag
	 *            标志
	 * @return 获去到的集合
	 */
	@RequestMapping(value = "/getSelect", method = RequestMethod.GET)
	public Object getAllDistributor(String getSelect) {
		return distributorService.getAll(null);
	}

	/**
	 * 获取所有的分销商
	 * 
	 * @param flag
	 *            标志
	 * @return 获去到的集合
	 */
	@RequestMapping(value = "/getSelect/{parentId}", method = RequestMethod.GET)
	public Object getDistributor(@PathVariable String parentId) {
		return distributorService.getByParentId(parentId);
	}

	/**
	 * 增加一个实体
	 * 
	 * @param distributor
	 *            待增加的实体
	 * @return 状态
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(Distributor distributor) {
		int result = distributorService.saveAndUpdateParentStatus(distributor);
		return new Status(result);
	}

	/**
	 * 根据Id删除实体
	 * 
	 * @param id
	 *            id
	 * @return 状态
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object delete(@PathVariable String id) {

		List<Cust> custs = custService.getByDistributorId(id);
		if (custs.size() > 0) {
			return new Status(0, "该分销商下有商户，不能删除！");
		}
		int rusult = distributorService.delete(id);
		return new Status(rusult);
	}

	/**
	 * 根据Id获取实体
	 * 
	 * @param id
	 *            id
	 * @return 查询到的实体
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable String id) {

		return distributorService.getById(id);
	}

	/**
	 * 更行实体
	 * 
	 * @param distributor
	 *            待更新
	 * @return 更新状态
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Distributor distributor) {
		int result = distributorService.update(distributor);
		return new Status(result);
	}

}
