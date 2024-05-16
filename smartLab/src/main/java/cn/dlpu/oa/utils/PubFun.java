package cn.dlpu.oa.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Department;
import cn.dlpu.oa.domain.User;

/**
 * 常量与公共方法
 * @author 樊晓璞 v1.0 2015-08-01
 *
 */
public class PubFun {
	
	/** 部门树形列表的显示前缀 */
	public static final String PRIFIX = ">";
	/** 部门树形列表 */
	public static List<Department> treeList1 = new ArrayList<Department>();
	/** 初始化密码 */
	public static final String INIT_PWD = "1234";//81dc9bdb52d04dc20036dbd8313ed055
	/** 登陆用户在session中的存储key值 */
	public static final String LOGIN_USER = "loginUser";
	/** 所有权限 */
	public static final String ALL_PRI_LIST = "allPriList";
	/** 分页时的默认页码 */
	public static final int DEFAULT_PAGE_NO = 1;
	/** 分页时每页默认显示的默认结果数(10) */
	public static final String DEFAULT_PAGE_SIZE = "10";
	/**  session的默认寿命(600秒) */
	public static final String DEFAULT_SESSION_TIMEOUT = "600";
	/** 每页显示的结果数在SysConfig表中的标识名称1 */
	public static final String STR_PAGE_SIZE1 = "PageSize1";
	/** 每页显示的结果数在SysConfig表中的标识名称2 */
	public static final String STR_PAGE_SIZE2 = "PageSize2";
	/** 是否是在进行新建的操作标识:1-在新建 */
	public static final int IS_NEW = 1;
	/** 是否是在进行新建的操作标识:0-未在新建 */
	public static final int NOT_NEW = 0;
	/** session寿命在SysConfig表中的标识名称 */
	public static final String SESSION_TIMEOUT = "SessionTimeOut";
	/** 当未继承BaseDao的类要调用其中的方法时需要此参数 */
	public static final String PUBLIC = "pub";//这里的值可以是除关键字之外的任意字符串
	/** 不同的模块有时每页需要显示的记录数不同，需要不同的分页策略，默认采用策略1 */
	public static final int PAGESIZE_STRATEGY_1 = 1;
	/** 不同的模块有时每页需要显示的记录数不同，需要不同的分页策略，默认采用策略1，此条标识策略2 */
	public static final int PAGESIZE_STRATEGY_2 = 2;
	/** 组建查询语句时使用的语句中的关键字 */
	public static final String OR = "or";
	/** 组建查询语句时使用的语句中的关键字 */
	public static final String AND = "and";
	/** buildCondition()方法中的常量 */
	public static final String STRING_EMTPY = "";
	/** buildCondition()方法中的常量 */
	public static final String STRING_TEXT01 = " AND ( ";
	/** buildCondition()方法中的常量 */
	public static final String STRING_TEXT02 = " = '";
	/** buildCondition()方法中的常量 */
	public static final String STRING_TEXT03 = "') ";
	/** 将结果列表导出为Excel文件时，获得page对象的标识，该pege对象包含所有查询到的结果，并不仅仅是当前页面的结果 */
	public static final int ALL_RESULT_LIST = -999;
	
	/**
	 * 递归法获得部门列表的树形结构（即有层次的列表）
	 * @param departmentList：最顶级的部门列表
	 * @param removeId：修改部门信息时上级部门名称列表中不能显示的部门的id（不能显示本部门及其子部门）
	 * @return
	 */
	public static List<Department> buildTreeList(Collection<Department> departmentList, int removeId) {
		
		treeList1.clear();//每次获得部门树形列表前清空列表，防止列表项重复累加
		List<Department> treeList2 = new ArrayList<Department>();
		treeList2 = getTreeList(departmentList, PRIFIX, removeId);
		return treeList2;
	}
	
	/**
	 * 递归法获得部门列表的树形结构（即有层次的列表）
	 * @param departmentList：最顶级的部门列表
	 * @param prifix：修改或新建部门信息时上级部门名称列表前添加的特殊符号
	 * @param removeId：修改部门信息时上级部门名称列表中不能显示的部门的id（不能显示本部门及其子部门）
	 * @return
	 */
	public static List<Department> getTreeList(Collection<Department> departmentList, String prifix, int removeId) {
		
		for (Department dept1 : departmentList) {
			//如果循环到的部门是不需要显示的部门，则跳出本次循环
			if (0 != removeId && dept1.getId() == removeId) {
				continue;
			}
			//新建一个对象，以免设置属性时也一起修改了数据库，因为此时session还未关闭
			Department dept2 = new Department();
			dept2.setId(dept1.getId());
			dept2.setName(prifix + dept1.getName());
			
			treeList1.add(dept2);
			getTreeList(dept1.getChildren(), "　" + prifix, removeId);
		}
		return treeList1;
	}
	
	/**
	 * 判断登录用户是不是管理员用户
	 * @param user
	 * @return
	 */
	public static Boolean isAdmin(User user) {
		if ("admin".equals(user.getLoginName())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获得用户的IP地址
	 * @return
	 */
	public static String getIP() {
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		return ip;
	}
	
	/**
	 * 获得登录用户
	 * @return
	 */
	public static User getLoginUser() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(LOGIN_USER);
		return user;
	}
	
	/**
	 * 通过配置名称查询相应的配置值
	 * @param configName
	 * @return value
	 */
	@SuppressWarnings("rawtypes")
	public static String getConfigByName(String configName) {
		IBaseDao dao = new BaseDaoImpl(PUBLIC);
		String value = dao.getConfigByName(configName);
		return value;
	}
	
	/**
     * 功能：当查询条件中含有"_"或"%"时，使用"/"字符将其转义，然后生成HQL语句
     * 强调：只适用于模糊查询组织HQL
     * @param colName: 字段名字
     * @param colValue: 字段值
     */
    public static String hqlLikeEscape(String colName, String colValue) {
		StringBuffer retStr = new StringBuffer(50);
		
		// 当查询条件值中含有特殊字符："_"或"%"
		if(null != colValue && (colValue.indexOf("_") != -1 || colValue.indexOf("%") != -1)){
			String str = StringUtils.replace(colValue, "_", "/_");
			str = StringUtils.replace(str, "%", "/%");
			
			retStr.append(" AND ").append(colName).append(" LIKE '%").append(str).append("%' escape '/'");
		} else {// 查询条件值中不含有特殊字符
			retStr.append(" AND ").append(colName).append(" LIKE '%").append(colValue).append("%'");
		}
		return retStr.toString();
	}
    
    /**
	 * 1、如果name为null，或trim(name)的长度为0，返回empty。<br>
	 * 2、如果value为null，或value的长度为0，返回empty。 <br>
	 * 3、不处理value中包含“*、%、：”的转义。
	 */
	public static String buildCondition(String name, String value) {

		// 条件处理
		if (name == null || name.trim().length() == 0 || value == null || value.length() == 0) {
			return STRING_EMTPY;
		}

		// 构造条件
		StringBuilder sb = new StringBuilder(64);

		sb.append(STRING_TEXT01);
		sb.append(name);
		sb.append(STRING_TEXT02);
		sb.append(value);
		sb.append(STRING_TEXT03);

		// 返回信息
		return sb.toString();
	}

	/**
	 * 判断字符串是否为空
	 * @param summaryStartDate
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str != "" && str.length() != 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 导出实验数据为Excel文件时，自动生成文件名的方法
	 * @param documentName
	 * @return
	 */
	public static String generateFileName(String documentName) {
		String fileName;
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(now);
		fileName = documentName + time + ".xls";
		return fileName;
	}
	
}

