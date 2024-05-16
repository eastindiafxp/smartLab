package cn.dlpu.oa.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.StageSummary;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import lombok.Data;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 阶段总结Action
 * @author 樊晓璞 2016-10-15 v1.0
 *
 */

@Data
@Namespace("/summary")
@ParentPackage("struts-default")
public class SummaryAction extends BaseAction<StageSummary> {

	private static final long serialVersionUID = 1L;
	/** 用于页面回显的总结列表对象 */
	List<StageSummary> summaryList1 = new ArrayList<StageSummary>();
	List<StageSummary> summaryList2 = new ArrayList<StageSummary>();
	/** 分页时的页面对象 */
	private Page page;
	/** 从页面接收的总结id信息 */
	private int summaryId;
	/** 要回显至详情窗口的总结对象 */
	private StageSummary summary;
	/** 因IE浏览器不支持以Date接收页面的日期，所以单独将其拿出来以String接收 */
	private String sumDate;
	/** 要回显至页面下拉列表框的列表项的作者列表 */
	private List<User> summaryAuthorList = new ArrayList<User>();
	/** 从页面接收的查询条件：查询起始日期 */
	private String summaryStartDate;
	/** 从页面接收的查询条件：查询截止日期 */
	private String summaryEndDate;
	/** 从页面接收的查询条件：作者id */
	private int summaryAuthorId;
	/** 从页面接收的查询条件：标题 */
	private String summaryTitle;

	/**
	 * 查找总结列表信息
	 * @return
	 */
	@Action(value = "findSummaryList", results = {
			@Result(name = SUCCESS,location = "/page/summary/summaryList.jsp",params = {})
	})
	public String findSummaryList() {
		
		User loginUser = PubFun.getLoginUser();
		if (PubFun.isAdmin(loginUser)) {
			//用于回显作者下拉列表框的列表项的列表对象
			summaryAuthorList = userService.findUserList();
		} else {
			if (loginUser.getSupervisor().getId() == 1) {//登录用户是导师
				summaryAuthorList = userService.findStudentsBySuId(loginUser.getId());
				summaryAuthorList.add(loginUser);
			} else {//登录用户是学生
				//用于回显作者下拉列表框的列表项的列表对象
				summaryAuthorList = userService.findStudentsBySuId(loginUser.getSupervisor().getId());
				summaryAuthorList.add(userService.findById(loginUser.getSupervisor().getId()));
			}
		}
		
		HQLHelper hh = new HQLHelper(StageSummary.class, 1);
		if (!PubFun.isAdmin(loginUser)) {//登录用户不是管理员时
			if (loginUser.getSupervisor().getId() == 1) {//登录用户是导师
				hh.buildWhere("author.id", PubFun.OR, loginUser.getId());
				hh.buildWhere("author.supervisor.id", PubFun.OR, loginUser.getId());
			} else {//登录用户是学生
				hh.buildWhere("author.id", PubFun.OR, loginUser.getSupervisor().getId());
				hh.buildWhere("author.supervisor.id", PubFun.OR, loginUser.getSupervisor().getId());
			}
		}
		
		pageIncludeAll = summaryService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = summaryService.getPage(hh, pageNo, isNew);
		summaryList1 = page.getResultList();
		
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		summaryId = 0;
		//清空查询条件信息
		summaryStartDate = null;
		summaryEndDate = null;
		summaryAuthorId = 0;
		summaryTitle = "";
		
		return SUCCESS;
	}
	
	/**
	 * 根据筛选条件查询阶段总结列表
	 * @return
	 */
	@Action(value = "querySummaryList", results = {
			@Result(name = SUCCESS,location = "/page/summary/summaryList.jsp",params = {})
	})
	public String querySummaryList() {
		
		User loginUser = PubFun.getLoginUser();
		if (PubFun.isAdmin(loginUser)) {
			//用于回显作者下拉列表框的列表项的列表对象
			summaryAuthorList = userService.findUserList();
		} else {
			if (loginUser.getSupervisor().getId() == 1) {//登录用户是导师
				summaryAuthorList = userService.findStudentsBySuId(loginUser.getId());
				summaryAuthorList.add(loginUser);
			} else {//登录用户是学生
				//用于回显作者下拉列表框的列表项的列表对象
				summaryAuthorList = userService.findStudentsBySuId(loginUser.getSupervisor().getId());
				summaryAuthorList.add(userService.findById(loginUser.getSupervisor().getId()));
			}
		}
		
		String condition = generateHqlCondition(loginUser);
		HQLHelper hh = new HQLHelper(StageSummary.class, 1);
		hh.setWhereStr(condition.toString());
		
		pageIncludeAll = summaryService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = summaryService.getPage(hh, pageNo, isNew);
		summaryList1 = page.getResultList();
		
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		summaryId = 0;
		
		return SUCCESS;
	}

	/**
	 * 构建查询语句
	 * @param loginUser
	 * @return
	 */
	public String generateHqlCondition(User loginUser) {
		StringBuffer condition = new StringBuffer(1024);
		condition.append("WHERE 1 = 1 ");
		if (!PubFun.isEmpty(summaryStartDate) && !PubFun.isEmpty(summaryEndDate)) {
			condition.append(" AND summaryDate >= '" + summaryStartDate + "' AND summaryDate <= '" + summaryEndDate + "'");
		} else if (!PubFun.isEmpty(summaryStartDate) && PubFun.isEmpty(summaryEndDate)) {
			condition.append(" AND summaryDate >= '" + summaryStartDate + "'");
		} else if (PubFun.isEmpty(summaryStartDate) && !PubFun.isEmpty(summaryEndDate)) {
			condition.append(" AND summaryDate <= '" + summaryEndDate + "'");
		}
		if (!PubFun.isEmpty(summaryTitle)) {
			condition.append(PubFun.hqlLikeEscape("title", summaryTitle));
		}
		if (summaryAuthorId != 0) {
			condition.append(PubFun.buildCondition("author.id", summaryAuthorId + ""));
		} else {
			if (!PubFun.isAdmin(loginUser)) {//登录用户不是管理员时
				if (loginUser.getSupervisor().getId() == 1) {//登录用户是导师
					condition.append(" AND (author.id = " + loginUser.getId());
					condition.append(" OR author.supervisor.id = " + loginUser.getId() + ")");
				} else {//登录用户是学生
					condition.append(" AND (author.id = " + loginUser.getSupervisor().getId());
					condition.append(" OR author.supervisor.id = " + loginUser.getSupervisor().getId() + ")");
				}
			}
		}
		return condition.toString();
	}
	
	/**
	 * 跳转到总结信息详情页面
	 * @return
	 */
	@Action(value = "viewSummaryDetail", results = {
			@Result(name = SUCCESS,location = "/page/summary/summaryDetailPage.jsp",params = {})
	})
	public String viewSummaryDetail() {
		//用于页面回显的总结对象
		summary = summaryService.findById(summaryId);
		summaryId = 0;//清空id，以免新建时出现混乱
		return SUCCESS;
	}
	
	/**
	 * 跳转到新建/修改总结信息页面
	 * @return
	 */
	@Action(value = "editSummaryPage", results = {
			@Result(name = SUCCESS,location = "/page/summary/editSummaryPage.jsp",params = {})
	})
	public String toEditSummaryPage() {
		
		if (summaryId != 0) {//修改页面
			//用于页面回显的总结对象
			summary = summaryService.findById(summaryId);
			summaryId = 0;
		} else {//新建页面
			summary = new StageSummary();
			summary.setId(0);//防止保存时出现空指针
		}
		return SUCCESS;
	}
	
	/**
	 * 保存新建或修改的阶段总结信息
	 * @return
	 */
	@Action(value = "saveOrUpdateSummary", results = {
			@Result(name = SUCCESS,location = "/page/summary/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})

	public String saveOrUpdateSummary() {
		
		User loginUser = PubFun.getLoginUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("============================");
//		System.out.println(summaryList1);
//		summaryList1 = summaryList2;
		if (summary.getId() != 0) {//修改
			try {
				StageSummary summary2 = summaryService.findById(summary.getId());
				if (!PubFun.isAdmin(loginUser)) {
					summary2.setAuthor(loginUser);
				}
				summary2.setTitle(summary.getTitle());
				summary2.setSummaryDate(sdf.parse(sumDate));
				summary2.setSummary1(summary.getSummary1());
				summary2.setSummary2(summary.getSummary2());
				summary2.setSummary3(summary.getSummary3());
				summary2.setReverse(summary.getReverse());
				summary2.setLastUpdateDate(new Date());
				summaryService.update(summary2);
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改阶段总结信息时出现异常！");
				return ERROR;
			}
		} else {//新建
			try {
				summary.setAuthor(loginUser);
				summary.setSummaryDate(sdf.parse(sumDate));
				summary.setLastUpdateDate(new Date());
				summaryService.save(summary);
				isNew = PubFun.IS_NEW;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("新建阶段总结信息时出现异常！");
				return ERROR;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据id删除阶段总结信息
	 * @return
	 */
	@Action(value = "deleteSummaryById", results = {
//			@Result(name = SUCCESS,location = "/page/summary/summaryList.jsp",params = {})
			@Result(name = SUCCESS,type = "chain", location = "findSummaryList")
	})
	public String deleteSummaryById() {
		
		try {
			summaryService.deleteById(summaryId);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除阶段总结信息时出现异常！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/* set和get方法 */
//	public List<StageSummary> getSummaryList1() {
//		return summaryList1;
//	}
//
//	public void setSummaryList1(List<StageSummary> summaryList1) {
//		this.summaryList1 = summaryList1;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public Page getPage() {
//		return page;
//	}
//
//	public void setPage(Page page) {
//		this.page = page;
//	}
//
//	public int getSummaryId() {
//		return summaryId;
//	}
//
//	public void setSummaryId(int summaryId) {
//		this.summaryId = summaryId;
//	}
//
//	public int getSummaryAuthorId() {
//		return summaryAuthorId;
//	}
//
//	public void setSummaryAuthorId(int summaryAuthorId) {
//		this.summaryAuthorId = summaryAuthorId;
//	}
//
//	public List<User> getSummaryAuthorList() {
//		return summaryAuthorList;
//	}
//
//	public void setSummaryAuthorList(List<User> summaryAuthorList) {
//		this.summaryAuthorList = summaryAuthorList;
//	}
//
//	public String getSummaryStartDate() {
//		return summaryStartDate;
//	}
//
//	public void setSummaryStartDate(String summaryStartDate) {
//		this.summaryStartDate = summaryStartDate;
//	}
//
//	public String getSummaryEndDate() {
//		return summaryEndDate;
//	}
//
//	public void setSummaryEndDate(String summaryEndDate) {
//		this.summaryEndDate = summaryEndDate;
//	}
//
//	public StageSummary getSummary() {
//		return summary;
//	}
//
//	public void setSummary(StageSummary summary) {
//		this.summary = summary;
//	}
//
//	public String getSumDate() {
//		return sumDate;
//	}
//
//	public void setSumDate(String sumDate) {
//		this.sumDate = sumDate;
//	}
//
//	public String getSummaryTitle() {
//		return summaryTitle;
//	}
//
//	public void setSummaryTitle(String summaryTitle) {
//		this.summaryTitle = summaryTitle;
//	}

}
