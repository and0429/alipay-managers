package com.collect.alipay.control.dto;

import java.util.List;

/**
 * 数据表格的数据载体
 * 
 * @author zhangkai
 *
 */
public class DataTableDto<T> {

	/**
	 * 总记录数
	 */
	private Integer recordsTotal;

	/**
	 * 请求的次数
	 */
	private Integer draw;

	/**
	 * 过滤后的记录数
	 */
	private Integer recordsFiltered;

	/**
	 * 数据
	 */
	private List<T> data;

	/**
	 * 构造器
	 * 
	 * @param recordsTotal
	 *            总记录数
	 * @param data
	 *            数据
	 */
	public DataTableDto(Integer draw, Integer recordsTotal, List<T> data) {
		super();
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
		this.draw = draw;
		this.data = data;
	}

	/**
	 * 构造器
	 */
	public DataTableDto() {
		super();
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

}
