package cn.dlpu.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.service.IDepartmentService;
import cn.dlpu.oa.service.IExperimentService;
import cn.dlpu.oa.service.IForumManageService;
import cn.dlpu.oa.service.IForumService;
import cn.dlpu.oa.service.IMedicineService;
import cn.dlpu.oa.service.IPrivilegeService;
//import cn.dlpu.oa.service.IProcessDefinitionService;
import cn.dlpu.oa.service.IProjectService;
import cn.dlpu.oa.service.IRecordService;
import cn.dlpu.oa.service.IReplyService;
import cn.dlpu.oa.service.IRoleService;
import cn.dlpu.oa.service.ISolutionService;
import cn.dlpu.oa.service.ISpecimenService;
import cn.dlpu.oa.service.ISummaryService;
import cn.dlpu.oa.service.ISysConfigService;
import cn.dlpu.oa.service.ITemplateService;
import cn.dlpu.oa.service.ITestService;
import cn.dlpu.oa.service.ITopicService;
import cn.dlpu.oa.service.IUserService;
import cn.dlpu.oa.utils.ExceptionMessage;
import cn.dlpu.oa.utils.PubFun;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;
	
	protected HttpSession session;
	
	protected ExceptionMessage exceptionMessage = new ExceptionMessage();
	
	protected T model;
	
	protected IRoleService roleService;
	
	protected IDepartmentService departmentService;

	protected IUserService userService;
	
	protected IPrivilegeService privilegeService;
	
	protected IForumManageService forumManageService;
	
	protected IForumService forumService;
	
	protected ITopicService topicService;
	
	protected IReplyService replyService;
	
	protected ITemplateService templateService;
	
	protected IProjectService projectService;
	
	protected IExperimentService  experimentService;
	
	protected IRecordService recordService;
	
	protected IMedicineService medicineService;
	
	protected ISpecimenService specimenService;
	
	protected ISolutionService solutionService;
	
	protected ISummaryService summaryService;
	
	protected ITestService testService;
	
	/* 分页信息 */
	protected int pageNo = PubFun.DEFAULT_PAGE_NO;
	
	protected int isNew = PubFun.NOT_NEW;
	
//	protected IProcessDefinitionService proDefService;
	
	protected ISysConfigService sysConfigService;
	@SuppressWarnings("rawtypes")
	/** 导出为Excel文件时需要导出的所有信息列表 */
	protected List allQueryList;
	/** 包含所有查询结果的页面，导出Excel时使用 */
	protected Page pageIncludeAll;
	
	
	
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();//获得父类Action类型
		Type[] types = type.getActualTypeArguments();//获得泛型内的具体类型
		Class<T> clazz = (Class<T>) types[0];
		try {
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得值栈
	 */
	protected ValueStack getValueStack(){
		return ActionContext.getContext().getValueStack();
	}

	/**
	 * 模型驱动必须实现的方法，也是ModelDriven接口中唯一的方法 
	 */
	@Override
	public T getModel() {
		return model;
	}
	
	//出现“no result defined for action...”错误时以下三个方法重写supportAction中相应的方法，然后在其中打断点，排查错误。
	public void addActionError(String anErrorMessage){
		   String s=anErrorMessage;
		   System.out.println(s);
	}
	
	public void addActionMessage(String aMessage){
		   String s=aMessage;
		   System.out.println(s);
	}
	
	public void addFieldError(String fieldName, String errorMessage){
		   String s=errorMessage;
		   String f=fieldName;
		   System.out.println(s);
		   System.out.println(f);
	}

	/* set and get method */
	public void setModel(T model) {
		this.model = model;
	}

	public IPrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	
	public void setPrivilegeService(IPrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	
	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(ExceptionMessage exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IForumManageService getForumManageService() {
		return forumManageService;
	}

	public void setForumManageService(IForumManageService forumManageService) {
		this.forumManageService = forumManageService;
	}

	public IForumService getForumService() {
		return forumService;
	}

	public void setForumService(IForumService forumService) {
		this.forumService = forumService;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public IReplyService getReplyService() {
		return replyService;
	}

	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public ITemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplateService templateService) {
		this.templateService = templateService;
	}

//	public IProcessDefinitionService getProDefService() {
//		return proDefService;
//	}

//	public void setProDefService(IProcessDefinitionService proDefService) {
//		this.proDefService = proDefService;
//	}

	public ISysConfigService getSysConfigService() {
		return sysConfigService;
	}

	public void setSysConfigService(ISysConfigService sysConfigService) {
		this.sysConfigService = sysConfigService;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public IExperimentService getExperimentService() {
		return experimentService;
	}

	public void setExperimentService(IExperimentService experimentService) {
		this.experimentService = experimentService;
	}

	public IRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(IRecordService recordService) {
		this.recordService = recordService;
	}

	public IMedicineService getMedicineService() {
		return medicineService;
	}

	public void setMedicineService(IMedicineService medicineService) {
		this.medicineService = medicineService;
	}

	public ISpecimenService getSpecimenService() {
		return specimenService;
	}

	public void setSpecimenService(ISpecimenService specimenService) {
		this.specimenService = specimenService;
	}

	public ISolutionService getSolutionService() {
		return solutionService;
	}

	public void setSolutionService(ISolutionService solutionService) {
		this.solutionService = solutionService;
	}

	public ISummaryService getSummaryService() {
		return summaryService;
	}

	public void setSummaryService(ISummaryService summaryService) {
		this.summaryService = summaryService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@SuppressWarnings("rawtypes")
	public List getAllQueryList() {
		return allQueryList;
	}
	
	@SuppressWarnings("rawtypes") 
	public void setAllQueryList(List allQueryList) {
		this.allQueryList = allQueryList;
	}

	public Page getPageIncludeAll() {
		return pageIncludeAll;
	}

	public void setPageIncludeAll(Page pageIncludeAll) {
		this.pageIncludeAll = pageIncludeAll;
	}

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}

}
