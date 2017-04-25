package com.hongchen.vo;

/**
 * 分页工具类
 * 
 * @author lzz
 *
 */
public class Pages {

	public Pages() {

	}

	/**
	 * 
	 * @param _current 当前页数
	 * @param _total 一共多页
	 * @param _size 一页有多数据
	 */
	public Pages(int _current, int _total, int _size, int _pages) {
		this.current = _current;
		this.total = _total;
		this.size = _size;
		this.pages = _pages;
	}

	/** 当前页数 */
	private int current;
	/** 一共多小数据*/
	private int total;
	/** 一页有多数据 */
	private int size;

	private int pages;//分页数

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
