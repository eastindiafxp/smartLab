package cn.dlpu.oa.action;

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
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import lombok.Data;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 溶液配制管理Action
 * @author 樊晓璞 2016-10-02 v1.0
 *
 */
@Data
@Namespace("/solution")
@ParentPackage("struts-default")
public class SolutionAction extends BaseAction<Solution> {

	private static final long serialVersionUID = 1L;
	/** 用于溶液列表页面回显的溶液列表对象 */
	private List<Solution> solutionList = new ArrayList<Solution>();
	/** 用于页面列表框的溶液所用药品列表对象 */
	private List<Medicine> medicineList = new ArrayList<Medicine>();
	/** 用于页面列表框的溶液所用药品默认选中项 */
	private Integer[] medicineIdList;
	/** 从列表页面接收的溶液id */
	private int solutionId;
	/** 用于页面回显的溶液对象 */
	private Solution solution;
	/** 跳转至信息提示页面时，用于显示提示信息的记录列表 */
	private List<String> recordPromptList = new ArrayList<String>();
	/** 用于分页的page对象 */
	private Page page;
	/** 从药品查询页面接收的筛选条件：溶液名称 */
	private String solutionName;
	/** 从药品查询页面接收的筛选条件：溶剂 */
	private String solutionSolvent;
	
	/**
	 * 查询溶液列表
	 * @return
	 */
	@Action(value = "solutionManagePage",results = {
			@Result(name = SUCCESS,location = "/page/solution/solutionList.jsp")
	})
	public String findSolutionList() {
		//用于页面回显的溶液列表
		User loginUser = PubFun.getLoginUser();
		HQLHelper hh = new HQLHelper(Solution.class, 1);
		if (!PubFun.isAdmin(loginUser)) {//如果不是管理员用户，则只显示自己旗下的溶液信息
//			solutionList = solutionService.findMySolutionList(loginUser.getId());
			hh.buildWhere("owner.id", PubFun.AND, loginUser.getId());
		}
		
		pageIncludeAll = solutionService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = solutionService.getPage(hh, pageNo, isNew);
		solutionList = page.getResultList();
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		solutionName = "";
		solutionSolvent = "";
		return SUCCESS;
	}
	
	/**
	 * 根据条件筛选溶液列表
	 * @return
	 */
	@Action(value = "querySolutionList",results = {
			@Result(name = SUCCESS,location = "/page/solution/solutionList.jsp")
	})
	public String querySolutionList() {
		
		StringBuilder condition = new StringBuilder();
		condition.append(PubFun.hqlLikeEscape("name", solutionName));
		condition.append(PubFun.hqlLikeEscape("solvent", solutionSolvent));
		HQLHelper hh = new HQLHelper(Solution.class, 1);
		User loginUser = PubFun.getLoginUser();
		if (PubFun.isAdmin(loginUser)) {
			hh.setWhereStr("where 1 = 1 ");
		} else {
			hh.buildWhere("owner.id", PubFun.AND, loginUser.getId());
		}
		hh.setWhereStr(hh.getWhereStr() + condition.toString());
		
		pageIncludeAll = solutionService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = solutionService.getPage(hh, pageNo, isNew);
		solutionList = page.getResultList();
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		return SUCCESS;
	}
	
	/**
	 * 跳转到溶液信息详细页面
	 * @return
	 */
	@Action(value = "toSoluDetail",results = {
			@Result(name = SUCCESS,location = "/page/solution/solutionDetailPage.jsp")
	})
	public String findSoluDetail() {
		//用于页面回显的溶液对象
		solution = solutionService.findById(solutionId);
		solutionId = 0;
		return SUCCESS;
	}
	
	/**
	 * 跳转到溶液信息新建/修改页面
	 * @return
	 */
	@Action(value = "editSolutionPage",results = {
			@Result(name = SUCCESS,location = "/page/solution/editSolutionPage.jsp")
	})
	public String toEditSolutionPage() {
		//溶质多选框的列表项
		User loginUser = PubFun.getLoginUser();
		if (PubFun.isAdmin(loginUser)) {
			medicineList = medicineService.findMediList();
		} else {
			medicineList = medicineService.findMyMediList(loginUser.getId());
		}
		
		if (solutionId == 0) {//新建页面
			solution = new Solution();
			medicineIdList = null;
			solution.setId(0);
		} else {//修改页面
			solution = solutionService.findById(solutionId);
			List<Medicine> medicines = new ArrayList<Medicine>(solution.getMedicines()); 
			medicineIdList = new Integer[medicines.size()];
			for (int i = 0; i <medicines.size(); i++) {
				medicineIdList[i] = medicines.get(i).getId();
			}
			solutionId = 0;
		}
		return SUCCESS;
	}
	
	/**
	 * 保存新建或更改的溶液信息
	 * @return
	 */
	@Action(value = "saveOrUpdateSolution",results = {
			@Result(name = SUCCESS,location = "/page/solution/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String saveOrUpdateSolution() {
		
		if (medicineIdList.length != 0 && medicineIdList != null) {
			List<Medicine> medicines = medicineService.findByIds(medicineIdList);
			solution.setMedicines(new HashSet<Medicine>(medicines));
		}
		
		if (solution.getId() == 0) {//新建
			try {
				solution.setOwner(PubFun.getLoginUser());
				solutionService.save(solution);
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存新建的溶液信息出现异常！");
				return ERROR;
			}
		} else {//修改
			Solution solution2 = solutionService.findById(solution.getId());
			solution2.setName(solution.getName() );
			solution2.setSolvent(solution.getSolvent());
			solution2.setDosage(solution.getDosage());
			solution2.setMedicines(solution.getMedicines());
			solution2.setReverse(solution.getReverse());
			solution2.setOwner(solution.getOwner());
			try {
				solutionService.update(solution2);
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存更改的溶液信息出现异常！");
				return ERROR;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据id删除溶液信息
	 * @return
	 */
	@Action(value = "deleteSoluById",results = {
//			@Result(name = SUCCESS,location = "/page/solution/editSuccess.jsp"),
			@Result(name = SUCCESS,type = "chain", location = "solutionManagePage"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String deleteSoluById() {
		
		Solution solu = solutionService.findById(solutionId);
		//如果有记录与此溶液相关联，则不能删除，并且需要有提示信息
		if (!solu.getRecords().isEmpty()) {
			recordPromptList = new ArrayList<String>(solu.getRecords().size());
			for (Record re : solu.getRecords()) {
				Experiment ex = experimentService.findById(re.getExperiment().getId());
				Project pr = projectService.findById(ex.getProject().getId());
				recordPromptList.add(pr.getProjNo() + ">>" + ex.getEptNo() + ">>" + re.getRecordNo());
			}
			solutionId = 0;
			return "prompt";
		}
		//如果次溶液与溶质（medicines）相关联，则需要先解除关联关系，再删除
		if (!solu.getMedicines().isEmpty()) {
			for (Medicine me : solu.getMedicines()) {
				me.getSolutions().remove(solu);
			}
			solu.setMedicines(null);
		}
		try {
			solutionService.deleteById(solutionId);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除溶液信息出现异常！");
			solutionId = 0;
			return ERROR;
		}
		solutionId = 0;
		return SUCCESS;
	}
	
	/* set和get方法 */
//	public List<Solution> getSolutionList() {
//		return solutionList;
//	}
//
//	public void setSolutionList(List<Solution> solutionList) {
//		this.solutionList = solutionList;
//	}
//
//	public List<Medicine> getMedicineList() {
//		return medicineList;
//	}
//
//	public void setMedicineList(List<Medicine> medicineList) {
//		this.medicineList = medicineList;
//	}
//
//	public int getSolutionId() {
//		return solutionId;
//	}
//
//	public void setSolutionId(int solutionId) {
//		this.solutionId = solutionId;
//	}
//
//	public Solution getSolution() {
//		return solution;
//	}
//
//	public void setSolution(Solution solution) {
//		this.solution = solution;
//	}
//
//	public Integer[] getMedicineIdList() {
//		return medicineIdList;
//	}
//
//	public void setMedicineIdList(Integer[] medicineIdList) {
//		this.medicineIdList = medicineIdList;
//	}
//
//	public List<String> getRecordPromptList() {
//		return recordPromptList;
//	}
//
//	public void setRecordPromptList(List<String> recordPromptList) {
//		this.recordPromptList = recordPromptList;
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
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public String getSolutionName() {
//		return solutionName;
//	}
//
//	public void setSolutionName(String solutionName) {
//		this.solutionName = solutionName;
//	}
//
//	public String getSolutionSolvent() {
//		return solutionSolvent;
//	}
//
//	public void setSolutionSolvent(String solutionSolvent) {
//		this.solutionSolvent = solutionSolvent;
//	}

}
