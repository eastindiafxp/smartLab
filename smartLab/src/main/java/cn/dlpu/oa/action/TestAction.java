package cn.dlpu.oa.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Specimen;
import cn.dlpu.oa.domain.Test;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import lombok.Data;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 样品检测管理
 * @author 樊晓璞 2016-12-05 v1.0
 *
 */
@Data
@Namespace("/test")
@ParentPackage("struts-default")
public class TestAction extends BaseAction<Test> {

	private static final long serialVersionUID = 1L;
	
	/** 用于分页的page对象 */
	private Page page;
	/** 用于页面回显的检测列表对象 */
	private List<Test> testList = new ArrayList<Test>();
	/** 从页面接收的检测id */
	private int testId;
	/** 用于页面回显的test对象 */
	private Test test;
	/** 页面的样品下拉列表框的列表 */
	private List<Specimen> specimenList = new ArrayList<Specimen>();
	/** 样品下拉列表框的默认选中项 */
	private int specimenId;
	/** 从页面接收的送检日期 */
	private String testSubmitDate;
	/** 从页面接收的检测日期 */
	private String testTestDate;
	/** 从页面接收的查询条件 */
	private String testLabel;
	/** 从页面接收的查询条件 */
	private String testSpecLabel;
	/** 从页面接收的查询条件 */
	private String testDate1;
	/** 从页面接收的查询条件 */
	private String testDate2;
	/** 从页面接收的查询条件 */
	private String testTestMethod;
	
	
	/**
	 * 查询检测列表
	 * @return
	 */
	@Action(value = "testManagePage", results = {
			@Result(name = SUCCESS,location = "/page/test/testList.jsp",params = {})
	})
	public String findTestList() {
		//用于页面回显的样品列表对象
		User loginUser = PubFun.getLoginUser();
		HQLHelper hh = new HQLHelper(Test.class, 1);
		
		if (!PubFun.isAdmin(loginUser)) {//登录用户不是管理员时
			if (loginUser.getSupervisor().getId() == 1) {//登录用户是导师
				hh.buildWhere("owner.id", PubFun.OR, loginUser.getId());
				hh.buildWhere("owner.supervisor.id", PubFun.OR, loginUser.getId());
			} else {//登录用户是学生
				hh.buildWhere("owner.id", PubFun.OR, loginUser.getSupervisor().getId());
				hh.buildWhere("owner.supervisor.id", PubFun.OR, loginUser.getSupervisor().getId());
			}
		}
		
		pageIncludeAll = testService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果,不仅仅是当前页
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = testService.getPage(hh, pageNo, isNew);
		testList = page.getResultList();
		
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		
		testLabel = "";
		testSpecLabel = "";
		testDate1 = "";
		testDate2 = "";
		testTestMethod = "";
		
		return SUCCESS;
	}
	
	/**
	 * 根据筛选条件查询检测列表
	 * @return
	 */
	@Action(value = "queryTestList", results = {
			@Result(name = SUCCESS,location = "/page/test/testList.jsp",params = {})
	})
	public String queryTestList() {
		
		String condition = generateHqlCondition();
		
		HQLHelper hh = new HQLHelper(Test.class, 1);
		User loginUser = PubFun.getLoginUser();
		if (PubFun.isAdmin(loginUser)) {
			hh.setWhereStr("where 1 = 1 ");
		} else {
			if (loginUser.getSupervisor().getId() == 1) {//登录用户是导师
				hh.buildWhere("owner.id", PubFun.OR, loginUser.getId());
				hh.buildWhere("owner.supervisor.id", PubFun.OR, loginUser.getId());
			} else {//登录用户是学生
				hh.buildWhere("owner.id", PubFun.OR, loginUser.getSupervisor().getId());
				hh.buildWhere("owner.supervisor.id", PubFun.OR, loginUser.getSupervisor().getId());
			}
		}
		hh.setWhereStr(hh.getWhereStr() + condition.toString());
		
		pageIncludeAll = specimenService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = testService.getPage(hh, pageNo, isNew);
		testList = page.getResultList();//用于页面显示的检测列表
		
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		
		return SUCCESS;
	}
	
	/**
	 * 构造hql查询语句
	 * @return
	 */
	public String generateHqlCondition() {
		StringBuilder condition = new StringBuilder();
		condition.append(PubFun.hqlLikeEscape("label", testLabel));
		condition.append(PubFun.hqlLikeEscape("t.specimen.label", testSpecLabel));
		condition.append(PubFun.hqlLikeEscape("testMethod", testTestMethod));
		if (!PubFun.isEmpty(testDate1) && !PubFun.isEmpty(testDate2)) {
			condition.append(" AND testDate BETWEEN '" + testDate1 + "' AND '" + testDate2 +"'");
		} else if (!PubFun.isEmpty(testDate1) && PubFun.isEmpty(testDate2)) {
			condition.append("AND testDate >= '" + testDate1 + "'");
		} else if (PubFun.isEmpty(testDate1) && !PubFun.isEmpty(testDate2)) {
			condition.append("AND testDate <= '" + testDate2 + "'");
		}
		return condition.toString();
	}

	/**
	 * 跳转到检测信息新建/修改页面
	 * @return
	 */
	@Action(value = "editTestPage",results = {
			@Result(name = SUCCESS,location = "/page/test/editTestPage.jsp")
	})
	public String toEditTestPage() {
		//页面样品下拉列表框的列表
		specimenList = specimenService.findMySpecimenList(PubFun.getLoginUser().getId());
		
		if (testId == 0) {//新建页面
			test = new Test();
			test.setId(0);//将样品id设为0，以免保存时出现空指针
			specimenId = 0;
		} else {//修改页面
			test = testService.findById(testId);
			specimenId = test.getSpecimen().getId();//样品下拉列表框的默认选中项
			testId = 0;//将testId设为0，以免新建时出现混乱
		}
		
		return SUCCESS;
	}
	
	/**
	 * 保存检测信息
	 * @return
	 */
	public String saveOrUpdateTest() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (test.getId() == 0) {//新建
			try {
				Specimen specimen = specimenService.findById(specimenId);
				test.setSpecimen(specimen);
				test.setOwner(PubFun.getLoginUser());
				if (PubFun.isEmpty(testSubmitDate)) {
					test.setSubmitDate(null);
				} else {
					test.setSubmitDate(sdf.parse(testSubmitDate));
				}
				if (PubFun.isEmpty(testTestDate)) {
					test.setTestDate(null);
				} else {
					test.setTestDate(sdf.parse(testTestDate));
				}
				testService.save(test);
				isNew = PubFun.IS_NEW;
			} catch (ParseException e1) {
				e1.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e1.toString());
				exceptionMessage.setMessage("保存新建的检测信息出现异常！(日期格式转换异常！)");
				return ERROR;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存新建的检测信息出现异常！");
				return ERROR;
			}
		} else {//修改
			try {
				Test test2 = testService.findById(test.getId());
				test2.setLabel(test.getLabel());
				test2.setSpecimen(specimenService.findById(specimenId));
				test2.setTestMethod(test.getTestMethod());
				test2.setReverse(test.getReverse());
				test2.setTestResult(test.getTestResult());
				test2.setOwner(test.getOwner());
				if (PubFun.isEmpty(testSubmitDate)) {
					test2.setSubmitDate(null);
				} else {
					test2.setSubmitDate(sdf.parse(testSubmitDate));
				}
				if (PubFun.isEmpty(testTestDate)) {
					test2.setTestDate(null);
				} else {
					test2.setTestDate(sdf.parse(testTestDate));
				}
				testService.update(test2);
			} catch (ParseException e1) {
				e1.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e1.toString());
				exceptionMessage.setMessage("保存修改的样品检测信息出现异常！(日期格式转换异常！)");
				return ERROR;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存修改的样品检测信息出现异常！");
				return ERROR;
			}
		}
		testId = 0;
		return SUCCESS;
	}
	
	/**
	 * 跳转到样品详细信息页面
	 * @return
	 */
	@Action(value = "toTestDetail",results = {
			@Result(name = SUCCESS,location = "/page/test/testDetailPage.jsp")
	})
	public String findTestDetail() {
		//用于页面回显的样品对象
		test = testService.findById(testId);
		testId = 0;
		return SUCCESS;
	}
	
	/**
	 * 根据id删除检测信息
	 * @return
	 */
	public String deleteTestById() {
		test = testService.findById(testId);
		Specimen sp = test.getSpecimen();
		if (!sp.equals(null) && sp != null) {
			sp.getTests().remove(test);
			test.setSpecimen(null);
		}
		try {
			testService.deleteById(testId);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除样品信息出现异常！");
			specimenId = 0;
			return ERROR;
		}
		testId = 0;
		specimenId = 0;
		return SUCCESS;
	}
	
//	public Page getPage() {
//		return page;
//	}
//
//	public void setPage(Page page) {
//		this.page = page;
//	}
//
//	public List<Test> getTestList() {
//		return testList;
//	}
//
//	public void setTestList(List<Test> testList) {
//		this.testList = testList;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public int getTestId() {
//		return testId;
//	}
//
//	public void setTestId(int testId) {
//		this.testId = testId;
//	}
//
//	public Test getTest() {
//		return test;
//	}
//
//	public void setTest(Test test) {
//		this.test = test;
//	}
//
//	public List<Specimen> getSpecimenList() {
//		return specimenList;
//	}
//
//	public void setSpecimenList(List<Specimen> specimenList) {
//		this.specimenList = specimenList;
//	}
//
//	public int getSpecimenId() {
//		return specimenId;
//	}
//
//	public void setSpecimenId(int specimenId) {
//		this.specimenId = specimenId;
//	}
//
//	public String getTestSubmitDate() {
//		return testSubmitDate;
//	}
//
//	public void setTestSubmitDate(String testSubmitDate) {
//		this.testSubmitDate = testSubmitDate;
//	}
//
//	public String getTestTestDate() {
//		return testTestDate;
//	}
//
//	public void setTestTestDate(String testTestDate) {
//		this.testTestDate = testTestDate;
//	}
//
//	public String getTestLabel() {
//		return testLabel;
//	}
//
//	public void setTestLabel(String testLabel) {
//		this.testLabel = testLabel;
//	}
//
//	public String getTestSpecLabel() {
//		return testSpecLabel;
//	}
//
//	public void setTestSpecLabel(String testSpecLabel) {
//		this.testSpecLabel = testSpecLabel;
//	}
//
//	public String getTestDate1() {
//		return testDate1;
//	}
//
//	public void setTestDate1(String testDate1) {
//		this.testDate1 = testDate1;
//	}
//
//	public String getTestDate2() {
//		return testDate2;
//	}
//
//	public void setTestDate2(String testDate2) {
//		this.testDate2 = testDate2;
//	}
//
//	public String getTestTestMethod() {
//		return testTestMethod;
//	}
//
//	public void setTestTestMethod(String testTestMethod) {
//		this.testTestMethod = testTestMethod;
//	}

}
