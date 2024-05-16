package cn.dlpu.oa.domain;

import lombok.*;

import java.util.List;

/**
 * 分页信息类
 *
 * @author 樊晓璞 v1.0 2015-08-16
 */
//@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Page {

	/**
	 * 当前页码
	 */
	private int pageNo;
	/**
	 * 每页显示的结果数
	 */
	private int pageSize;
	/**
	 * 总结果数
	 */
	private int resultCount;
	/**
	 * 结果集
	 */
	private List resultList;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 要显示的页面列表的开始页码
	 */
	private int beginPageNo;
	/**
	 * 要显示的页面列表的结束页码
	 */
	private int endPageNo;
	/**
	 * 导出Excel文件时需要导出所有查询到的结果列表，而不仅仅是当前页，所以这里需要一个包含所有结果的list
	 */
	private List allResultList;

//	public Page() {}

	public Page(List allResultList) {
		super();
		this.allResultList = allResultList;
	}

	/**
	 * 构造器，其余三个属性未写进是因为他们是由其他四个计算得来
	 */
	public Page(int pageNo, int pageSize, int resultCount, List resultList) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.resultCount = resultCount;
		this.resultList = resultList;

		this.pageCount = (this.resultCount + this.pageSize - 1) / this.pageSize;//计算总页数
		if (this.pageCount <= 10) {
			this.beginPageNo = 1;
			this.endPageNo = this.pageCount;
		} else {
			this.beginPageNo = this.pageNo - 4;
			this.endPageNo = this.pageNo + 5;
			if (this.beginPageNo < 1) {
				this.beginPageNo = 1;
				this.endPageNo = 10;
			}
			if (this.endPageNo > this.pageCount) {
				this.endPageNo = this.pageCount;
				this.beginPageNo = this.endPageNo - 9;
			}

		}

	}

	/* get and set method */
//	public int getPageNo() {
//		return pageNo;
//	}
//
//	public void setPageNo(int pageNo) {
//		this.pageNo = pageNo;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public int getResultCount() {
//		return resultCount;
//	}
//
//	public void setResultCount(int resultCount) {
//		this.resultCount = resultCount;
//	}
//
//	public List getResultList() {
//		return resultList;
//	}
//
//	public void setResultList(List resultList) {
//		this.resultList = resultList;
//	}
//
//	public int getPageCount() {
//		return pageCount;
//	}
//
//	public void setPageCount(int pageCount) {
//		this.pageCount = pageCount;
//	}
//
//	public int getBeginPageNo() {
//		return beginPageNo;
//	}
//
//	public void setBeginPageNo(int beginPageNo) {
//		this.beginPageNo = beginPageNo;
//	}
//
//	public int getEndPageNo() {
//		return endPageNo;
//	}
//
//	public void setEndPageNo(int endPageNo) {
//		this.endPageNo = endPageNo;
//	}
//
//	public List getAllResultList() {
//		return allResultList;
//	}
//
//	public void setAllResultList(List allResultList) {
//		this.allResultList = allResultList;
//	}

}
