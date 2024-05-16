package cn.dlpu.oa.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 辅助生成HQL语句的工具类
 * @author 樊晓璞 v1.0 2015-08-16
 *
 */
public class HQLHelper {
	/** FROM子句 */
	private String fromStr = "";
	/** WHERE子句 */
	private String whereStr = "";
	/** GROUP BY子句 */
	private String groupByStr = "";
	/** ORDER BY子句 */
	private String orderByStr = "";
	/** 封装HQL中对应的参数信息 */
	private List<Object> args = new ArrayList<Object>();
	/** 不同的模块有时每页需要显示的记录数不同，需要不同的分页策略，默认采用策略1 */
	private int pageSizeStrategy = PubFun.PAGESIZE_STRATEGY_1;
	
	public HQLHelper() {}
	
	/**
	 * 通过构造方法拼接from子句
	 * @param cla
	 */
	@SuppressWarnings("rawtypes")
	public HQLHelper(Class cla) {
		this.fromStr = " FROM " + cla.getSimpleName() + " t";
	}
	
	/**
	 * 通过构造方法拼接from语句，并选择不同的分页策略（区别在于每页显示的结果数）
	 * @param cla
	 * @param pageStrategy
	 */
	@SuppressWarnings("rawtypes")
	public HQLHelper(Class cla, int pageStrategy) {
		
		this.fromStr = " FROM " + cla.getSimpleName() + " t ";
		
		if (pageStrategy == PubFun.PAGESIZE_STRATEGY_1) {
			this.pageSizeStrategy = PubFun.PAGESIZE_STRATEGY_1;
		} else if (pageStrategy == PubFun.PAGESIZE_STRATEGY_2) {
			this.pageSizeStrategy = PubFun.PAGESIZE_STRATEGY_2;
		} else {
			//pageStrategy不是以上情况时时，默认采用“1”
			this.pageSizeStrategy = PubFun.PAGESIZE_STRATEGY_1;
		}
		
	}
	
	
	/**
	 * 拼接whereNot子句
	 * @param condition
	 * @param args
	 */
	public void buildWhereNot(String condition, String andOr, Object...args) {
		if (this.whereStr.length() == 0) {
			this.whereStr = " WHERE " + condition + " != ?";
		} else {
			this.whereStr += " " + andOr + " " + condition + " != ?";
		}
		
		if (args != null && args.length > 0) {
			for (Object o : args) {
				this.args.add(o);
			}
		}
		
	}
	
	/**
	 * 拼接where子句
	 * @param condition
	 * @param args
	 */
	public void buildWhere(String condition, String andOr, Object...args) {
		if (this.whereStr.length() == 0) {
			this.whereStr = " WHERE " + condition + " = ?";
		} else {
			this.whereStr += " " + andOr + " " + condition + " = ?";
		}
		
		if (args != null && args.length > 0) {
			for (Object o : args) {
				this.args.add(o);
			}
		}
		
	}
	
	/**
	 * 拼接groupBy子句
	 * @param groupBy
	 */
	public void buildGroupBy(String groupBy) {
			this.groupByStr = " GROUP BY " + groupBy;
	}
	
	/**
	 * 拼接order by 子句
	 * @param orderBy
	 * @param asc
	 */
	public void buildOrderBy(String orderBy, boolean asc) {
		if (this.orderByStr.length() == 0) {
			this.orderByStr = " ORDER BY " + orderBy + (asc?" ASC" : " DESC");
		} else {
			this.orderByStr += ", " + orderBy + (asc?" ASC" : " DESC");
		}
		
	}
	
	/**
	 * 构建查找list集合的HQL
	 * @return
	 */
	public String getHQL() {
		return this.fromStr + this.whereStr +this.groupByStr + this.orderByStr;
	}
	
	/**
	 * 获得查找结果数的HQL
	 * @return
	 */
	public String getCountHQL() {
		return "SELECT COUNT(*)" + this.fromStr + this.whereStr;
	}
	
	/* get and set method */
	public String getFromStr() {
		return fromStr;
	}
	
	public void setFromStr(String fromStr) {
		this.fromStr = fromStr;
	}
	
	public String getWhereStr() {
		return whereStr;
	}
	
	public void setWhereStr(String whereStr) {
		this.whereStr = whereStr;
	}
	
	public String getOrderByStr() {
		return orderByStr;
	}
	
	public void setOrderByStr(String orderByStr) {
		this.orderByStr = orderByStr;
	}

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}

	public String getGroupByStr() {
		return groupByStr;
	}

	public void setGroupByStr(String groupByStr) {
		this.groupByStr = groupByStr;
	}

	public int getPageSizeStrategy() {
		return pageSizeStrategy;
	}

	public void setPageSizeStrategy(int pageSizeStrategy) {
		this.pageSizeStrategy = pageSizeStrategy;
	}

}
