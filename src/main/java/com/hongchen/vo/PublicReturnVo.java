package com.hongchen.vo;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;

/**
 * 公共返回的类
 * 
 * @author dell
 * @param <T>
 *
 */
public class PublicReturnVo<T> implements Serializable {
	private Pages page;
	private T list;
	public PublicReturnVo() {
		super();
	}
	public PublicReturnVo(Pages page, T list) {
		super();
		this.page = page;
		this.list = list;
	}

	public Pages getPage() {
		return page;
	}

	public void setPage(Pages page) {
		this.page = page;
	}

	public T getList() {
		return list;
	}

	public void setList(T list) {
		this.list = list;
	}


	public static PublicReturnVo conversionPublicReturn(Page page){
		PublicReturnVo vo = new PublicReturnVo();

		if(page!= null && page.getRecords() != null){
			Pages pages = new Pages();
			vo.setList(page.getRecords());
			pages.setCurrent(page.getCurrent());
			pages.setSize(page.getSize());
			pages.setTotal(page.getTotal());
			pages.setPages(page.getPages());
			vo.setPage(pages);
		}
		return vo;
	}

}
