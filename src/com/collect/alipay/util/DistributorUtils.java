package com.collect.alipay.util;

import java.util.ArrayList;
import java.util.List;

import com.collect.alipay.domain.Distributor;

/**
 * 用于处理分销商和子分销商的关系
 * 
 * @author zhangkai
 * 
 */
public class DistributorUtils {

	/**
	 * 从集合中找到指定id的分销商的所有最后一层子分销商的Id的集合
	 * 
	 * @param all
	 *            所有的分销商
	 * @return 最后一级子分销商的Id集合
	 */
	public static List<String> getAllNoChildDistributorById(List<Distributor> all, String id) {
		List<String> list = new ArrayList<String>();

		getAllNoChildDistributorById(all, list, id);

		return list;
	}

	/**
	 * 根据Id
	 * 
	 * @param disList
	 * @param distribuotrIdList
	 * @param id
	 */
	private static void getAllNoChildDistributorById(List<Distributor> disList, List<String> distribuotrIdList, String id) {

		for (int i = 0; i < disList.size(); i++) {
			Distributor distributor = disList.get(i);
			if (!id.equals(distributor.getId())) {
				continue;
			}
			// no child distributor
			if (distributor.getHasChild() == 0) {
				distribuotrIdList.add(id);
			} else {
				for (int j = 0; j < disList.size(); j++) {
					Distributor distributor2 = disList.get(j);
					if (distributor.getId().equals(distributor2.getpId())) {
						getAllNoChildDistributorById(disList, distribuotrIdList, distributor2.getId());
					}
				}
			}
		}
	}

	/**
	 * 返回一个指定Id的分销商的所有的子集合
	 * 
	 * @param all
	 *            所有的分销商
	 * @param pId
	 *            指定的父级分销商
	 * @return 所有的子分销商和自己
	 */
	public static List<Distributor> getSelfAndChild(List<Distributor> all, String pId) {

		List<Distributor> list = new ArrayList<Distributor>();
		getSelfAndChild(all, list, pId);

		return list;
	}

	/**
	 * 返回一个指定Id的分销商的所有的子集合
	 * 
	 * @param all
	 * @param list
	 * @param pId
	 */
	private static void getSelfAndChild(List<Distributor> all, List<Distributor> list, String pId) {
		for (int i = 0; i < all.size(); i++) {
			Distributor distributor = all.get(i);
			if (distributor.getId().equals(pId)) {
				list.add(distributor);
			} else {
				if (pId.equals(distributor.getpId())) {
					getSelfAndChild(all, list, distributor.getId());
				}
			}
		}
	}
}
