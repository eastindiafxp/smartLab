package cn.dlpu.oa.action;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Experiment;
import cn.dlpu.oa.domain.Medicine;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Project;
import cn.dlpu.oa.domain.Record;
import cn.dlpu.oa.domain.Solution;
import cn.dlpu.oa.domain.Specimen;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.HibernateSessionFactory;
import cn.dlpu.oa.utils.PubFun;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实验项目管理
 * @author 樊晓璞 v1.0 2016-09-24
 *
 */

@Namespace("/experiment")
//@ParentPackage("struts-default")
@ParentPackage("json-default")
@Setter
@Getter
public class EptProjectAction extends BaseAction<Project> {

	private static final long serialVersionUID = 1L;
	/** 要传给页面的项目列表 */
	private List<Project> projectList =new ArrayList<Project>();
	/** 页面传递过来的项目id值 */
	private int projectId;
	/** 要传递至页面的项目 */
	private Project project;
	/** 要传递至项目详细信息页面的项目对象，为了不同页面之间不起冲突，所有单另再写一个2 */
	private Project project2;
	/** 要传递至项目修改页面的项目对象 */
	private Project project3;
	/** 从页面接收的项目开始日期，因IE浏览器不支持用Date接收，所以单独拿出来以String接收 */
	private String project3StartDate;
	/** 从页面接收的项目结束日期，因IE浏览器不支持用Date接收，所以单独拿出来以String接收 */
	private String project3EndDate;
	/** 要传递至项目修改页面的项目对象 */
	private Project project4;
	/** 查询记录列表时页面面包屑导航部分需要此对象进行回显，为避免混乱，单独使用project5 */
	private Project project5;
	/** 要传递至项目修改页面的用户列表，用于回显下拉列表框用户列表 */
	private List<User> claimerList = new ArrayList<User>();
	/** 验证项目编号是否可用的标识 */
	private boolean ok = false;
	/**  用于接收从页面传递过来的项目编号 */
	private String projectNo;
	/** 要传递至页面的项目申请人id */
	private int claimerId;
	/** 要传递至页面的实验列表 */
	private List<Experiment> experimentList = new ArrayList<Experiment>();
	/** 从页面接收的实验id */
	private int experimentId;
	/** 要传递至页面的实验对象 */
	private Experiment experiment;
	/** 从页面接收的实验开始日期，因IE浏览器不支持用Date接收，所以单独拿出来 */
	private String experimentStartDate;
	/** 从页面接收的实验结束日期，因IE浏览器不支持用Date接收，所以单独拿出来 */
	private String experimentEndDate;
	/** 要传递至页面用于回显的实验操作者id */
	private int mainOperateId;
	/** 要传递至页面用于下拉列表框选项显示的实验操作者列表 */
	private List<User> operateList = new ArrayList<User>();
	/** 从页面接收的实验编号 */
	private String experimentNo;
	/** 用于分页的page对象 */
	private Page page;
	/** 用于页面回显或接受页面信息的记录对象 */
	private Record record;
	/** 从页面接收的记录开始日期，因IE浏览器不支持以Date接收日期，所以单独拿出来，以String接收 */
	private String recordStartDate;
	/** 从页面接收的记录结束日期，因IE浏览器不支持以Date接收日期，所以单独拿出来，以String接收 */
	private String recordEndDate;
	/** 用于页面回显的记录列表 */
	private List<Record> recordList = new ArrayList<Record>();
	/** 用于接收页面回传的记录id */
	private int recordId;
	/** 从页面接收或者用于页面回显的被镀材料id */
	private int substrateId;
	/** 页面回显时下拉列表框里的全部列表 */
	private List<Medicine> substrateList = new ArrayList<Medicine>();
	/** 页面回显时用于显示样品下拉框列表 */
	private List<Specimen> specimenList = new ArrayList<Specimen>();
	/** 用于页面回显的样品id */
	private int specimenId;
	/** 页面回显时溶液多选框的所有选项 */
	private List<Solution> solutionList = new ArrayList<Solution>();
	/** 溶液多选框的默认选中项 */
	private Integer[] solutionIdList;
	/** 页面回显时的操作者列表 */
	private List<User> operaterList = new ArrayList<User>();
	/** 操作者多选框的默认选中项 */
	private Integer[] operaterIdList;
	/** 验证记录编号唯一性时从页面接收的编号值 */
	private String recordNo;
	/** 从记录查询页面接收的筛选条件：记录编号 */
	private String recordNo2;
	/** 从记录查询页面接收的筛选条件：记录名称 */
	private String recordName2;

	/**
	 * 查询项目列表
	 * @return
	 */
	@Action(value = "projAndExpeManagePage",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/projectList.jsp")
	})
	public String findProjectList() {
//		查找project列表以在页面显示
		HQLHelper hh = new HQLHelper(Project.class, 2);
		User loginUser = PubFun.getLoginUser();
		if (!PubFun.isAdmin(loginUser)) {
			if (loginUser.getSupervisor().getId() == 1) {
				hh.buildWhere("claimer.id", PubFun.AND, loginUser.getId());
			} else {
				hh.buildWhere("claimer.id", PubFun.AND, loginUser.getSupervisor().getId());
			}
		}
		HibernateSessionFactory.getSession().clear();
		page = projectService.getPage(hh, pageNo, isNew);
		pageNo = PubFun.DEFAULT_PAGE_NO;
		projectList = page.getResultList();
//		HibernateSessionFactory.getSession().refresh(projectList);
		isNew = PubFun.NOT_NEW;
		projectId = 0;//清空id信息，以免在新建项目时混乱
		return SUCCESS;
	}

	/**
	 * 根据id查询项目的详细信息
	 * @return
	 */
	@Action(value = "toProjDetail",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/projDetailPage.jsp")
	})
	public String findProjDetail() {

		project2 = projectService.findById(projectId);
//		HibernateSessionFactory.getSession().refresh(project2);
		projectId = 0;//清空id信息，以免在新建项目时混乱
		return SUCCESS;
	}

	/**
	 * 根据id删除项目信息
	 * @return
	 */
	@Action(value = "deleteProjectById",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/projectList.jsp")
	})
	public String deleteProjectById() {
		try {
			projectService.deleteById(projectId);
			projectId = 0;//清空id信息，以免在新建项目时混乱

			User loginUser = PubFun.getLoginUser();//当前登录用户
			if (!PubFun.isAdmin(loginUser)) {//如果不是管理员用户，则有选择地显示项目
				if (loginUser.getSupervisor().getId() == 1) {//当前登录用户是导师
					//用于回显页面项目列表
					projectList = projectService.findMyProject(loginUser.getId());
				} else {//当前登录用户是学生
					//用于回显页面项目列表
					projectList = projectService.findMyProject(loginUser.getSupervisor().getId());
				}
			} else {//如果是管理员，则全部显示
				projectList = projectService.findProjectList();
			}

		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除项目信息出现异常！");
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 跳转至修改项目信息页面
	 * @return
	 */
	@Action(value = "toEditProjectPage",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editProjectPage.jsp")
	})
	public String toEditProjectPage() {
		claimerList = userService.findSupervisorList();
//		if (projectId == 0) {//新建
//			User loginUser = PubFun.getLoginUser();
//			project3 = new Project();
//			project3.setId(0);//将要传递至页面的id的值设为0，以免保存时出现空指针
//			claimerId = loginUser.getId();
//		} else {//修改
			project3 = projectService.findById(projectId);
//		HibernateSessionFactory.getSession().refresh(project3);
			claimerId = project3.getClaimer().getId();//获得要修改的项目的申请者id，用于页面回显

			projectId = 0;//清空id信息，以免在新建项目时混乱
//		}
		return SUCCESS;
	}


	/**
	 * 跳转至新建项目信息页面
	 * @return
	 */
	@Action(value = "toCreateNewProjPage",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editProjectPage.jsp")
	})
	public String toCreateNewProjPage() {
		claimerList = userService.findSupervisorList();
//		if (projectId == 0) {//新建
			User loginUser = PubFun.getLoginUser();
			project3 = new Project();
			project3.setId(0);//将要传递至页面的id的值设为0，以免保存时出现空指针
			claimerId = loginUser.getId();
//		} else {//修改
//			project3 = projectService.findById(projectId);
//			claimerId = project3.getClaimer().getId();//获得要修改的项目的申请者id，用于页面回显
//			projectId = 0;//清空id信息，以免在新建项目时混乱
//		}
		return SUCCESS;
	}


	/**
	 * 验证项目编号是否可用
	 * @return
	 */
	@Action(value = "validProjectNo",results = {
			@Result(name = "ajaxResult",type = "json",params = {"data", "data"})
	})
	public String validProjectNo() {

		Project proj = projectService.findByProjNo(projectNo);
		if (null == proj || proj.equals(null))  {
			ok = true;
		} else {
			ok = false;
		}
		model.setReverse(ok + "");
		return "ajaxResult";
	}

	/**
	 * 保存新建或更改的项目信息
	 * @return
	 */
	@Action(value = "saveOrUpdateProj",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String saveOrUpdateProj() {
		if (project3.getId() == 0) {//新建
			try {
				project3.setClaimer(userService.findById(claimerId));
				project3.setStartDate(Date.valueOf(project3StartDate));
				if (PubFun.isEmpty(project3EndDate)) {
					project3.setEndDate(null);
				} else {
					project3.setEndDate(Date.valueOf(project3EndDate));
				}
				projectService.save(project3);
				isNew = PubFun.IS_NEW;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("新建项目信息出现异常！");
				return ERROR;
			}
		} else{//修改
			Project proj = projectService.findById(project3.getId());
			proj.setProjNo(project3.getProjNo());
			proj.setName(project3.getName());
			proj.setClaimer(userService.findById(claimerId));
			proj.setStartDate(Date.valueOf(project3StartDate));
			if (PubFun.isEmpty(project3EndDate)) {
				proj.setEndDate(null);
			} else {
				proj.setEndDate(Date.valueOf(project3EndDate));
			}
			proj.setReverse(project3.getReverse());
			try {
				projectService.update(proj);
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改项目信息出现异常！");
				return ERROR;
			}
		}
		return SUCCESS;
	}

	/**
	 * 根据项目编号查询实验列表
	 * @return
	 */
	@Action(value = "findExperimentList",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/experimentList.jsp")
	})
	public String findExperimentList() {
		project4 = projectService.findById(projectId);
		experimentList = experimentService.findByProjId(projectId);
		/* 在这里不能清空所属项目的id（projectId），因为在新建一个实验时，需要默认选中该实验所属的项目为当前项目。 */
//		projectId = 0;
		return SUCCESS;
	}

	/**
	 * 根据id查看实验的详细信息
	 * @return
	 */
	@Action(value = "toEptDetail",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/eptDetailPage.jsp"),
	})
	public String toEptDetail() {
		experiment = experimentService.findById(experimentId);
		experimentId = 0;//清空实验id，以免在新建实验时引起混乱
		return SUCCESS;
	}

	/**
	 * 跳转到新建实验信息页面
	 * @return
	 */
	@Action(value = "toCreateNewEptPage",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editExperimentPage.jsp"),
	})
	public String toCreateNewEptPage() {

		User loginUser = PubFun.getLoginUser();//当前登录用户
		if (!PubFun.isAdmin(loginUser)) {//如果不是管理员用户，则有选择地显示用户
			if (loginUser.getSupervisor().getId() == 1) {//当前登录用户是导师
				//用于回显页面项目下拉列表框的项目列表
				projectList = projectService.findMyProject(loginUser.getId());
				//用于回显页面操作者下拉列表框的用户列表
				operateList = userService.findStudentsBySuId(loginUser.getId());
				operateList.add(loginUser);
			} else {//当前登录用户是学生
				//用于回显页面项目下拉列表框的项目列表
				projectList = projectService.findMyProject(loginUser.getSupervisor().getId());
				//用于回显页面操作者下拉列表框的用户列表
				operateList = userService.findStudentsBySuId(loginUser.getSupervisor().getId());
				operateList.add(loginUser.getSupervisor());
			}
		} else {//如果是管理员，则用户全部显示
			operateList = userService.findUserList();
			projectList = projectService.findProjectList();
		}

//		if (experimentId == 0) {//新建
			experiment = new Experiment();
			experiment.setId(0);//将id值设为0，以免保存时出现空指针
//			projectId = 0;//清空所属项目的id值
			mainOperateId = 0;//清空实验操作者的id值
//		} else {//修改
//			//用于页面回显的experiment对象
//			experiment = experimentService.findById(experimentId);
//			//用于回显页面项目下拉列表框的默认值
//			projectId = experiment.getProject().getId();
//			//用于回显页面实验操作者下拉列表框的默认值
//			mainOperateId = experiment.getMainOperate().getId();
//			//清空id信息，以免在新建实验时出现混乱
//			experimentId = 0;
//		}
		return SUCCESS;
	}

	/**
	 * 跳转到修改实验信息页面
	 * @return
	 */
	@Action(value = "editExperimentPage",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editExperimentPage.jsp"),
	})
	public String toEditExperimentPage() {

		User loginUser = PubFun.getLoginUser();//当前登录用户
		if (!PubFun.isAdmin(loginUser)) {//如果不是管理员用户，则有选择地显示用户
			if (loginUser.getSupervisor().getId() == 1) {//当前登录用户是导师
				//用于回显页面项目下拉列表框的项目列表
				projectList = projectService.findMyProject(loginUser.getId());
				//用于回显页面操作者下拉列表框的用户列表
				operateList = userService.findStudentsBySuId(loginUser.getId());
				operateList.add(loginUser);
			} else {//当前登录用户是学生
				//用于回显页面项目下拉列表框的项目列表
				projectList = projectService.findMyProject(loginUser.getSupervisor().getId());
				//用于回显页面操作者下拉列表框的用户列表
				operateList = userService.findStudentsBySuId(loginUser.getSupervisor().getId());
				operateList.add(loginUser.getSupervisor());
			}
		} else {//如果是管理员，则用户全部显示
			operateList = userService.findUserList();
			projectList = projectService.findProjectList();
		}

//		if (experimentId == 0) {//新建
//			experiment = new Experiment();
//			experiment.setId(0);//将id值设为0，以免保存时出现空指针
////			projectId = 0;//清空所属项目的id值
//			mainOperateId = 0;//清空实验操作者的id值
//		} else {//修改
			//用于页面回显的experiment对象
			experiment = experimentService.findById(experimentId);
			//用于回显页面项目下拉列表框的默认值
			projectId = experiment.getProject().getId();
			//用于回显页面实验操作者下拉列表框的默认值
			mainOperateId = experiment.getMainOperate().getId();
			//清空id信息，以免在新建实验时出现混乱
			experimentId = 0;
//		}
		return SUCCESS;
	}

	/**
	 * 验证实验编号是否可用
	 * @return
	 */
	@Action(value = "validExperimentNo",results = {
			@Result(name = "ajaxResult",type = "json",params = {"data", "data"})
	})
	public String validExperimentNo() {
		Experiment ept = experimentService.findByEptNoProjId(experimentNo, projectId);
		if (ept == null || ept.equals(null)) {
			ok = true;
		} else {
			ok = false;
		}
		model.setReverse(ok + "");
		return "ajaxResult";
	}


	/**
	 * 保存新建或修改的实验信息
	 * @return
	 */
	@Action(value = "saveOrUpdateEpt",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editEptSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String saveOrUpdateEpt() {
		if (experiment.getId() ==  0) {//新建
			experiment.setProject(projectService.findById(projectId));
			experiment.setMainOperate(userService.findById(mainOperateId));
			experiment.setStartDate(Date.valueOf(experimentStartDate));
			if (PubFun.isEmpty(experimentEndDate)) {
				experiment.setEndDate(null);
			} else {
				experiment.setEndDate(Date.valueOf(experimentEndDate));
			}
			try {
				experimentService.save(experiment);
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("新建实验信息出现异常！");
				return ERROR;
			}
		} else {//修改
			Experiment ept = experimentService.findById(experiment.getId());
			ept.setEptNo(experiment.getEptNo());
			ept.setName(experiment.getName());
			ept.setProject(projectService.findById(projectId));
			ept.setMainOperate(userService.findById(mainOperateId));
			ept.setStartDate(Date.valueOf(experimentStartDate));
			if (PubFun.isEmpty(experimentEndDate)) {
				ept.setEndDate(null);
			} else {
				ept.setEndDate(Date.valueOf(experimentEndDate));
			}
			ept.setDescription(experiment.getDescription());
			ept.setReverse(experiment.getReverse());
			try {
				experimentService.update(ept);
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改实验信息出现异常！");
				return ERROR;
			}
		}
		return SUCCESS;
	}

	/**
	 * 根据id删除实验信息
	 * @return
	 */
	@Action(value = "deleteEptById",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/experimentList.jsp"),
			@Result(name = ERROR,location = "/page/eptProject/experimentList.jsp")
	})
	public String deleteEptById() {
		try {
			//级联删除时需要先通过以下方法删除两个对象之间的关联，然后再执行删除操作
			Experiment experiment = experimentService.findById(experimentId);
			if (null != experiment) {
				projectId = experiment.getProject().getId();
				experiment.getProject().getExperiments().remove(experiment);
				experiment.setProject(null);
			}
			experimentService.deleteById(experimentId);
			experimentList = experimentService.findByProjId(projectId);

			experimentId = 0;//清空实验id，以免在新建实验时引起混乱
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除实验信息出现异常！");
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 查询记录列表
	 * @return
	 */
	@Action(value = "findRecordList",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/recordList.jsp"),
	})
	public String findRecordList () {
//		System.out.println(projectId);
		/* 记录列表页面面包屑导航栏需要的实验对象 */
		experiment = experimentService.findById(experimentId);
		/* 记录列表页面面包屑导航栏需要的项目对象 */
		project5 = experiment.getProject();

		HQLHelper hh = new HQLHelper(Record.class, 1);
		hh.buildWhere("experiment.id", PubFun.AND, experimentId);

		pageIncludeAll = specimenService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息

		page = recordService.getPage(hh, pageNo, isNew);
		pageNo = PubFun.DEFAULT_PAGE_NO;
		/* 记录列表 */
		recordList = page.getResultList();
//		recordList = recordService.findRecordList(experimentId);
		isNew = PubFun.NOT_NEW;
		recordNo2 = "";
		recordName2 = "";
		return SUCCESS;
	}

	/**
	 * 根据筛选条件查询记录列表
	 * @return
	 */
	@Action(value = "queryRecordList",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/recordList.jsp"),
	})
	public String queryRecordList() {
		/* 拼凑查询语句 */
		StringBuilder condition = new StringBuilder();
		condition.append(PubFun.hqlLikeEscape("recordNo", recordNo2));
		condition.append(PubFun.hqlLikeEscape("name", recordName2));
		HQLHelper hh = new HQLHelper(Record.class, 1);
		hh.buildWhere("experiment.id", PubFun.AND, experimentId);
		hh.setWhereStr(hh.getWhereStr() + condition.toString());

		pageIncludeAll = specimenService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息

		/* 根据拼凑的查询条件查询记录列表页面 */
		page = recordService.getPage(hh, pageNo, isNew);
		recordList = page.getResultList();

		/* 记录列表页面面包屑导航栏需要的实验对象 */
		experiment = experimentService.findById(experimentId);
		/* 记录列表页面面包屑导航栏需要的项目对象 */
		project5 = experiment.getProject();

		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;

		return SUCCESS;
	}

	/**
	 * 跳转到记录详细信息页面
	 * @return
	 */
	@Action(value = "toRecordDetail",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/recordDetailPage.jsp"),
	})
	public String findRecordDetail() {

		record = recordService.findById(recordId);
		recordId = 0;//清空id信息，以免在新建时出现混乱

		return SUCCESS;
	}

	/**
	 * 跳转到新建记录信息页面
	 * @return
	 */
	@Action(value = "toCreateNewRecordPage",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editRecordPage.jsp"),
	})
	public String toCreateNewRecordPage() {
		//当前登录用户
		User loginUser = PubFun.getLoginUser();
		experiment = experimentService.findById(experimentId);
		//实验下拉列表框的列表项
		experimentList = experimentService.findByProjId(experiment.getProject().getId());
		if (PubFun.isAdmin(loginUser)) {
			//被镀材料下拉列表框的列表项
			substrateList = medicineService.findMediList();
			//溶液下拉列表框的列表项
			solutionList = solutionService.findSolutionList();
			//样品标签下拉列表框的列表项
			specimenList = specimenService.findSpecimenList();
		} else {
			substrateList = medicineService.findMyMediList(loginUser.getId());
			solutionList = solutionService.findMySolutionList(loginUser.getId());
			specimenList = specimenService.findMySpecimenList(loginUser.getId());
		}
		//操作者下拉列表框的列表项operaterList
		if (!PubFun.isAdmin(loginUser)) {//如果不是管理员用户，则有选择地显示用户
			if (loginUser.getSupervisor().getId() == 1) {//当前登录用户是导师
				operaterList = userService.findStudentsBySuId(loginUser.getId());
				operaterList.add(loginUser);
			} else {//当前登录用户是学生
				operaterList = userService.findStudentsBySuId(loginUser.getSupervisor().getId());
				operaterList.add(loginUser.getSupervisor());
			}
		} else {//如果是管理员，则显示全部用户
			operaterList = userService.findUserList();
		}

//		if (recordId == 0) {//新建页面
			record = new Record();
			record.setId(0);//将id设为0，以免保存时出现空指针
			substrateId = 0;
			specimenId = 0;
			solutionIdList = null;
			operaterIdList = null;
			isNew = PubFun.IS_NEW;
//		} else {//修改页面
//			record = recordService.findById(recordId);//用于页面回显的record对象
//			experimentId = record.getExperiment().getId();//默认选中的实验id
//			substrateId = record.getSubstrate().getId();//默认选中的被镀材料id
//			if (null == record.getSpecimens() || record.getSpecimens().size() == 0) {
//				specimenId = 0;
//			} else {
//				List<Specimen> specimens = new ArrayList<Specimen>(record.getSpecimens());
//				specimenId = specimens.get(0).getId();//默认选中的样品id
//			}
//			//溶液列表中默认选中的选项
//			List<Solution> solutions = new ArrayList<Solution>(record.getSolutions());
//			solutionIdList = new Integer[solutions.size()];
//			for (int i = 0; i < solutions.size(); i++) {
//				solutionIdList[i] = solutions.get(i).getId();
//			}
//			//操作者列表中默认选中的选项
//			List<User> operaters = new ArrayList<User>(record.getOperaters());
//			operaterIdList = new Integer[operaters.size()];
//			for (int j = 0; j < operaters.size(); j++) {
//				operaterIdList[j] = operaters.get(j).getId();
//			}
//			recordId = 0;//清空record的id信息，以免在新建时出现混乱
//		}
		return SUCCESS;
	}

	/**
	 * 跳转到修改记录信息页面
	 * @return
	 */
	@Action(value = "editRecordPage",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editRecordPage.jsp"),
	})
	public String editRecordPage() {
		//当前登录用户
		User loginUser = PubFun.getLoginUser();
		//实验下拉列表框的列表项
		experimentList = experimentService.findByProjId(experiment.getProject().getId());
		if (PubFun.isAdmin(loginUser)) {
			//被镀材料下拉列表框的列表项
			substrateList = medicineService.findMediList();
			//溶液下拉列表框的列表项
			solutionList = solutionService.findSolutionList();
			//样品标签下拉列表框的列表项
			specimenList = specimenService.findSpecimenList();
		} else {
			substrateList = medicineService.findMyMediList(loginUser.getId());
			solutionList = solutionService.findMySolutionList(loginUser.getId());
			specimenList = specimenService.findMySpecimenList(loginUser.getId());
		}
		//操作者下拉列表框的列表项operaterList
		if (!PubFun.isAdmin(loginUser)) {//如果不是管理员用户，则有选择地显示用户
			if (loginUser.getSupervisor().getId() == 1) {//当前登录用户是导师
				operaterList = userService.findStudentsBySuId(loginUser.getId());
				operaterList.add(loginUser);
			} else {//当前登录用户是学生
				operaterList = userService.findStudentsBySuId(loginUser.getSupervisor().getId());
				operaterList.add(loginUser.getSupervisor());
			}
		} else {//如果是管理员，则显示全部用户
			operaterList = userService.findUserList();
		}

//		if (recordId == 0) {//新建页面
//			record = new Record();
//			record.setId(0);//将id设为0，以免保存时出现空指针
//			substrateId = 0;
//			specimenId = 0;
//			solutionIdList = null;
//			operaterIdList = null;
//			isNew = PubFun.IS_NEW;
//		} else {//修改页面
			record = recordService.findById(recordId);//用于页面回显的record对象
			experimentId = record.getExperiment().getId();//默认选中的实验id
			substrateId = record.getSubstrate().getId();//默认选中的被镀材料id
			if (null == record.getSpecimens() || record.getSpecimens().size() == 0) {
				specimenId = 0;
			} else {
				List<Specimen> specimens = new ArrayList<Specimen>(record.getSpecimens());
				specimenId = specimens.get(0).getId();//默认选中的样品id
			}
			//溶液列表中默认选中的选项
			List<Solution> solutions = new ArrayList<Solution>(record.getSolutions());
			solutionIdList = new Integer[solutions.size()];
			for (int i = 0; i < solutions.size(); i++) {
				solutionIdList[i] = solutions.get(i).getId();
			}
			//操作者列表中默认选中的选项
			List<User> operaters = new ArrayList<User>(record.getOperaters());
			operaterIdList = new Integer[operaters.size()];
			for (int j = 0; j < operaters.size(); j++) {
				operaterIdList[j] = operaters.get(j).getId();
			}
			recordId = 0;//清空record的id信息，以免在新建时出现混乱
//		}
		return SUCCESS;
	}

	/**
	 * 验证记录修改或新建页面被选中的样品是否已与其他记录做关联
	 * @return
	 */
	@Action(value = "validSpecimenId",results = {
			@Result(name = "ajaxResult",type = "json",params = {"data", "data"})
	})
	public String validSpecimenId() {

		Record re = specimenService.findById(specimenId).getRecord();
		if (re == null) {
			ok = true;
		} else {
			ok = false;
		}
		model.setReverse(ok + "");
		return "ajaxResult";
	}

	/**
	 * 保存新建或更改的记录信息
	 * @return
	 */
	@Action(value = "saveOrUpdateRecord",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/editSuccess.jsp"),
	})
	public String saveOrUpdateRecord() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (record.getId() == 0) {//新建
			try {
				//给记录所在实验属性赋值
				record.setExperiment(experimentService.findById(experimentId));
				//为记录的被镀材料属性赋值
				record.setSubstrate(medicineService.findById(substrateId));
				//为样品属性赋值
				List<Specimen> specimens = new ArrayList<Specimen>();
				specimens.add(specimenService.findById(specimenId));
				record.setSpecimens(new HashSet<Specimen>(specimens));
				//为该记录所需的溶液赋值，在页面已进行过非空校验，故在这里不再校验
				List<Solution> solutions = solutionService.findByIds(solutionIdList);
				record.setSolutions(new HashSet<Solution>(solutions));
				//为该记录的操作者赋值，在页面已进行过非空校验，故在这里不再校验
				List<User> operaters = userService.findByIds(operaterIdList);
				record.setOperaters(new HashSet<User>(operaters));
				record.setStartDate(sdf.parse(recordStartDate));
				if (PubFun.isEmpty(recordEndDate)) {
					record.setEndDate(null);
				} else {
					record.setEndDate(sdf.parse(recordEndDate));
				}
				recordService.save(record);
			} catch (ParseException e1) {
				e1.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e1.toString());
				exceptionMessage.setMessage("保存新建的记录信息出现异常！（日期格式转换有异常）");
				return ERROR;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存新建的记录信息出现异常！");
				return ERROR;
			}
		} else {//修改
			try {
				Record record2 = recordService.findById(record.getId());
				//为记录对象record2赋予新值
				record2.setRecordNo(record.getRecordNo());
				record2.setName(record.getName());
				record2.setExperiment(experimentService.findById(experimentId));
				record2.setSubstrate(medicineService.findById(substrateId));
				record2.setSubstrateMass(record.getSubstrateMass());
				record2.setCladLayer(record.getCladLayer());
				record2.setPh(record.getPh());
				record2.setReaTime(record.getReaTime());
				List<Specimen> specimens = new ArrayList<Specimen>();
				specimens.add(specimenService.findById(specimenId));
				record2.setSpecimens(new HashSet<Specimen>(specimens));
				record2.setStartDate(sdf.parse(recordStartDate));
				if (PubFun.isEmpty(recordEndDate)) {
					record2.setEndDate(null);
				} else {
					record2.setEndDate(sdf.parse(recordEndDate));
				}
				//为溶液列表赋值，页面已进行过非空校验
				List<Solution> solutions = solutionService.findByIds(solutionIdList);
				record2.setSolutions(new HashSet<Solution>(solutions));
				//为实验操作者列表赋值，页面已进行过非空校验
				List<User> operaters = userService.findByIds(operaterIdList);
				record2.setOperaters(new HashSet<User>(operaters));
				record2.setReverse(record.getReverse());
				recordService.update(record2);
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改记录信息出现异常！");
				return ERROR;
			}
		}
		return SUCCESS;
	}

	/**
	 * 记录编号的唯一性验证
	 * @return
	 */
	@Action(value = "validRecordNo",results = {
			@Result(name = "ajaxResult",type = "json",params = {"data", "data"})
	})
	public String validRecordNo() {
		Record record2 = recordService.findByRecNoEptId(recordNo, experimentId);
		if (record2 == null || record2.equals(null)) {
			ok = true;
		} else {
			ok = false;
		}
		model.setReverse(ok + "");
		return "ajaxResult";
	}

	/**
	 * 根据id删除记录信息
	 * @return
	 */
	@Action(value = "deleteRecordById",results = {
			@Result(name = SUCCESS,location = "/page/eptProject/recordList.jsp"),
	})
	public String deleteRecordById() {

		try {
			//删除记录之前先删除其与实验的级联关系
			Record record = recordService.findById(recordId);
			record.getExperiment().getRecords().remove(record);
			record.setExperiment(null);

			//删除之前先删除其与样品的级联关系
			if (record.getSpecimens() != null && record.getSpecimens().size() != 0) {
				for (Specimen s : record.getSpecimens()) {
					s.setRecord(null);
				}
			}
			record.setSpecimens(null);

			recordService.deleteById(recordId);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除记录信息出现异常！");
		}
		recordId = 0;
		return SUCCESS;
	}
	
	/*出现“no result defined for action...”错误时以下三个方法重写supportAction中相应的方法，然后在其中打断点，排查错误。
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
	}*/

	/* set和get方法 */
//	public List<Project> getProjectList() {
//		return projectList;
//	}
//
//	public void setProjectList(List<Project> projectList) {
//		this.projectList = projectList;
//	}
//
//	public int getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(int projectId) {
//		this.projectId = projectId;
//	}
//
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
//
//	public Project getProject2() {
//		return project2;
//	}
//
//	public void setProject2(Project project2) {
//		this.project2 = project2;
//	}
//
//	public Project getProject3() {
//		return project3;
//	}
//
//	public void setProject3(Project project3) {
//		this.project3 = project3;
//	}
//
//	public List<User> getClaimerList() {
//		return claimerList;
//	}
//
//	public void setClaimerList(List<User> claimerList) {
//		this.claimerList = claimerList;
//	}
//
//	public boolean isOk() {
//		return ok;
//	}
//
//	public void setOk(boolean ok) {
//		this.ok = ok;
//	}
//
//	public int getClaimerId() {
//		return claimerId;
//	}
//
//	public void setClaimerId(int claimerId) {
//		this.claimerId = claimerId;
//	}
//
//	public String getProjectNo() {
//		return projectNo;
//	}
//
//	public void setProjectNo(String projectNo) {
//		this.projectNo = projectNo;
//	}
//
//	public List<Experiment> getExperimentList() {
//		return experimentList;
//	}
//
//	public void setExperimentList(List<Experiment> experimentList) {
//		this.experimentList = experimentList;
//	}
//
//	public Project getProject4() {
//		return project4;
//	}
//
//	public void setProject4(Project project4) {
//		this.project4 = project4;
//	}
//
//	public int getExperimentId() {
//		return experimentId;
//	}
//
//	public void setExperimentId(int experimentId) {
//		this.experimentId = experimentId;
//	}
//
//	public Experiment getExperiment() {
//		return experiment;
//	}
//
//	public void setExperiment(Experiment experiment) {
//		this.experiment = experiment;
//	}
//
//	public int getMainOperateId() {
//		return mainOperateId;
//	}
//
//	public void setMainOperateId(int mainOperateId) {
//		this.mainOperateId = mainOperateId;
//	}
//
//	public List<User> getOperateList() {
//		return operateList;
//	}
//
//	public void setOperateList(List<User> operateList) {
//		this.operateList = operateList;
//	}
//
//	public String getExperimentNo() {
//		return experimentNo;
//	}
//
//	public void setExperimentNo(String experimentNo) {
//		this.experimentNo = experimentNo;
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
//	public Record getRecord() {
//		return record;
//	}
//
//	public void setRecord(Record record) {
//		this.record = record;
//	}
//
//	public List<Record> getRecordList() {
//		return recordList;
//	}
//
//	public void setRecordList(List<Record> recordList) {
//		this.recordList = recordList;
//	}
//
//	public Project getProject5() {
//		return project5;
//	}
//
//	public void setProject5(Project project5) {
//		this.project5 = project5;
//	}
//
//	public int getRecordId() {
//		return recordId;
//	}
//
//	public void setRecordId(int recordId) {
//		this.recordId = recordId;
//	}
//
//	public int getSubstrateId() {
//		return substrateId;
//	}
//
//	public void setSubstrateId(int substrateId) {
//		this.substrateId = substrateId;
//	}
//
//	public List<Medicine> getSubstrateList() {
//		return substrateList;
//	}
//
//	public void setSubstrateList(List<Medicine> substrateList) {
//		this.substrateList = substrateList;
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
//	public List<Solution> getSolutionList() {
//		return solutionList;
//	}
//
//	public void setSolutionList(List<Solution> solutionList) {
//		this.solutionList = solutionList;
//	}
//
//	public Integer[] getSolutionIdList() {
//		return solutionIdList;
//	}
//
//	public void setSolutionIdList(Integer[] solutionIdList) {
//		this.solutionIdList = solutionIdList;
//	}
//
//	public List<User> getOperaterList() {
//		return operaterList;
//	}
//
//	public void setOperaterList(List<User> operaterList) {
//		this.operaterList = operaterList;
//	}
//
//	public Integer[] getOperaterIdList() {
//		return operaterIdList;
//	}
//
//	public void setOperaterIdList(Integer[] operaterIdList) {
//		this.operaterIdList = operaterIdList;
//	}
//
//	public String getRecordNo() {
//		return recordNo;
//	}
//
//	public void setRecordNo(String recordNo) {
//		this.recordNo = recordNo;
//	}
//
//	public String getRecordNo2() {
//		return recordNo2;
//	}
//
//	public void setRecordNo2(String recordNo2) {
//		this.recordNo2 = recordNo2;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public String getRecordName2() {
//		return recordName2;
//	}
//
//	public void setRecordName2(String recordName2) {
//		this.recordName2 = recordName2;
//	}
//
//	public String getExperimentStartDate() {
//		return experimentStartDate;
//	}
//
//	public void setExperimentStartDate(String experimentStartDate) {
//		this.experimentStartDate = experimentStartDate;
//	}
//
//	public String getExperimentEndDate() {
//		return experimentEndDate;
//	}
//
//	public void setExperimentEndDate(String experimentEndDate) {
//		this.experimentEndDate = experimentEndDate;
//	}
//
//	public String getProject3StartDate() {
//		return project3StartDate;
//	}
//
//	public void setProject3StartDate(String project3StartDate) {
//		this.project3StartDate = project3StartDate;
//	}
//
//	public String getProject3EndDate() {
//		return project3EndDate;
//	}
//
//	public void setProject3EndDate(String project3EndDate) {
//		this.project3EndDate = project3EndDate;
//	}
//
//	public String getRecordStartDate() {
//		return recordStartDate;
//	}
//
//	public void setRecordStartDate(String recordStartDate) {
//		this.recordStartDate = recordStartDate;
//	}
//
//	public String getRecordEndDate() {
//		return recordEndDate;
//	}
//
//	public void setRecordEndDate(String recordEndDate) {
//		this.recordEndDate = recordEndDate;
//	}

}
