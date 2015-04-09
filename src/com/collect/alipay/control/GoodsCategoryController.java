package com.collect.alipay.control;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collect.alipay.control.dto.DataTableDto;
import com.collect.alipay.control.dto.Status;
import com.collect.alipay.domain.GoodsCategory;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.service.GoodsCategoryService;
import com.collect.alipay.util.UUIDUtil;

/**
 * 商品类别的控制器
 * 
 * @author zhangkai
 *
 */
@RestController
@SessionAttributes("loginer")
@RequestMapping(value = "/goodsCategory")
public class GoodsCategoryController {

	@Inject
	private GoodsCategoryService goodsCategoryService;

	/**
	 * 列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/goodsCategorys", method = RequestMethod.GET)
	public Object goodsCategorys(GoodsCategory gc, Model model) {
		Loginer loginer = (Loginer) model.asMap().get("loginer");

		if (loginer == null) {
			return new DataTableDto<GoodsCategory>(gc.getDraw(), 0, new ArrayList<GoodsCategory>());
		}

		gc.setCustId(loginer.getCustOrDistributorId());

		return goodsCategoryService.getPager(gc);
	}

	/**
	 * 获取所有
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Object getAll(GoodsCategory gc, Model model) {
		Loginer loginer = (Loginer) model.asMap().get("loginer");
		if (loginer == null) {
			return new Status();
		}

		gc.setCustId(loginer.getCustOrDistributorId());

		return goodsCategoryService.getAll(gc);

	}

	/**
	 * 新增
	 * 
	 * @param gc
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(GoodsCategory gc, Model model) {

		Loginer loginer = (Loginer) model.asMap().get("loginer");

		if (loginer == null) {
			return new Status(0, "请先登录");
		}

		gc.setCustId(loginer.getCustOrDistributorId());

		GoodsCategory gcg = goodsCategoryService.getByNameAndCustId(gc);
		if (gcg != null) {
			return new Status(0, "类别名称已经存在");
		}

		gc.setId(UUIDUtil.randomUUID());

		int result = goodsCategoryService.save(gc);

		return new Status(result);
	}

	/**
	 * delete
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object delete(@PathVariable String id) {
		int result = goodsCategoryService.delete(id);
		return new Status(result);
	}

}
