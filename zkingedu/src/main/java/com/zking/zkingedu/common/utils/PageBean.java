package com.zking.zkingedu.common.utils;

/**
 * 分页工具类
 *
 * @author DML
 * @date 2019年1月8日
 * 可以实现分页查询
 */
public class PageBean <T>{

	private Integer pageIndex;
	private Integer pageSize;
	private T t;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public PageBean(Integer pageIndex, Integer pageSize, T t) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.t = t;
	}

	public PageBean(Integer pageIndex, Integer pageSize) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public PageBean(){}
}
