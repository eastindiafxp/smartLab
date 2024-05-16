package cn.dlpu.oa.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * 药品管理Action
 * @author 樊晓璞 2016-10-01 v1.0
 *
 */
@Data
@Namespace("/medicine")
@ParentPackage("struts-default")
public class MedicineAction extends BaseAction<Medicine> {

	private static final long serialVersionUID = 1L;
	/** 用于页面回显的药品列表 */
	private List<Medicine> medicineList = new ArrayList<Medicine>();
	/** 用于接收页面信息的药品id */
	private int medicineId;
	/** 用于回传或接受页面信息的药品对象 */
	private Medicine medicine;
	/** 从页面接收的日期对象，因IE不兼容以Date接收，故单独拿出来以String接收 */
	private String medicineManuDate;
	/** 从页面接收的日期对象，因IE不兼容以Date接收，故单独拿出来以String接收 */
	private String medicineExpireDate;
	/** 跳转至信息提示页面时，用于显示提示信息的记录列表 */
	private List<String> recordPromptList = new ArrayList<String>();
	/** 跳转至信息提示页面时，用于显示提示信息的溶液列表 */
	private List<Solution> solutionList = new ArrayList<Solution>();
	/** 用于分页的page对象 */
	private Page page;
	/** 从药品查询页面接收的筛选条件：药品名称 */
	private String medicineName;
	/** 从药品查询页面接收的筛选条件：药品化学式 */
	private String medicineFormula;
	
	/**
	 * 查询药品信息列表
	 * @return
	 */
	@Action(value = "medicineManagePage",results = {
			@Result(name = SUCCESS,location = "/page/medicine/medicineList.jsp")
	})
	public String findMedicineList() {
		//查询药品信息列表
		User loginUser = PubFun.getLoginUser();
		HQLHelper hh = new HQLHelper(Medicine.class, 1);
		if (!PubFun.isAdmin(loginUser)) {//如果不是管理员，则只查询当前用户记录的药品信息
//			medicineList = medicineService.findMyMediList(loginUser.getId());
			hh.buildWhere("owner.id", PubFun.AND, loginUser.getId());
		}
		
		pageIncludeAll = medicineService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = medicineService.getPage(hh, pageNo, isNew);
		medicineList = page.getResultList();
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		medicineName = "";
		medicineFormula = "";
		return SUCCESS;
	}
	
	/**
	 * 根据筛选条件查询实验列表
	 * @return
	 */
	@Action(value = "queryMedicineList",results = {
			@Result(name = SUCCESS,location = "/page/medicine/medicineList.jsp")
	})
	public String queryMedicineList() {
		
		StringBuilder condition = new StringBuilder();
		condition.append(PubFun.hqlLikeEscape("name", medicineName));
		condition.append(PubFun.hqlLikeEscape("chemFormula", medicineFormula));
		HQLHelper hh = new HQLHelper(Medicine.class, 1);
		User loginUser = PubFun.getLoginUser();
		if (PubFun.isAdmin(loginUser)) {
			hh.setWhereStr("where 1 = 1 ");
		} else {
			hh.buildWhere("owner.id", PubFun.AND, loginUser.getId());
		}
		hh.setWhereStr(hh.getWhereStr() + condition.toString());
		
		pageIncludeAll = medicineService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = medicineService.getPage(hh, pageNo, isNew);
		medicineList = page.getResultList();
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		
		return SUCCESS;
	}
	
	/**
	 * 打开药品详细信息窗口
	 * @return
	 */
	@Action(value = "toMediDetail",results = {
			@Result(name = SUCCESS,location = "/page/medicine/mediDetailPage.jsp")
	})
	public String findMediDetail() {
		//用于页面回显的medicine对象
		medicine = medicineService.findById(medicineId);
		medicineId = 0;//清空药品id，以免在新建时引起混乱
		return SUCCESS;
	}

	/**
	 * 打开药品详细信息窗口
	 * @return
	 */
	@Action(value = "viewMedicineDetail2",results = {
			@Result(name = SUCCESS,location = "/page/medicine/mediDetailPage.jsp")
	})
	public String viewMedicineDetail2() {
		//用于页面回显的medicine对象
		medicine = medicineService.findById(medicineId);
		medicineId = 0;//清空药品id，以免在新建时引起混乱
		return SUCCESS;
	}

	
	/**
	 * 跳转到修改或新建药品信息页面
	 * @return
	 */
	@Action(value = "editMedicinePage",results = {
			@Result(name = SUCCESS,location = "/page/medicine/editMedicinePage.jsp")
	})
	public String toEditMedicinePage() {
		
		if (medicineId != 0) {//修改页面
			//用于页面回显的medicine对象
			medicine = medicineService.findById(medicineId);
			medicineId = 0;
		} else {//新建页面
			medicine = new Medicine();
			medicine.setId(0);//防止保存时出现空指针
		}
		return SUCCESS;
	}

	/**
	 * 保存新建/更改的药品信息
	 * @return
	 */
	@Action(value = "saveOrUpdateMedicine",results = {
			@Result(name = SUCCESS,location = "/page/medicine/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String saveOrUpdateMedicine() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (medicine.getId() != 0) {//修改
			try {
				Medicine medicine2 = medicineService.findById(medicine.getId());
				medicine2.setName(medicine.getName());
				medicine2.setChemFormula(medicine.getChemFormula());
				medicine2.setManufacturer(medicine.getManufacturer());
				medicine2.setPosition(medicine.getPosition());
				if (PubFun.isEmpty(medicineManuDate)) {
					medicine2.setManuDate(null);
				} else {
					medicine2.setManuDate(sdf.parse(medicineManuDate));
				}
				if (PubFun.isEmpty(medicineExpireDate)) {
					medicine2.setExpireDate(null);
				} else {
					medicine2.setExpireDate(sdf.parse(medicineExpireDate));
				}
				medicine2.setConcentration(medicine.getConcentration());
				medicine2.setReverse(medicine.getReverse());
				medicine2.setOwner(medicine.getOwner());
				medicineService.update(medicine2);
			} catch (ParseException e1) {
				e1.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e1.toString());
				exceptionMessage.setMessage("保存更改的药品信息出现异常！（日期格式转换出现错误！）");
				return ERROR;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存更改的药品信息出现异常！");
				return ERROR;
			}
		} else {//新建
			try {
				medicine.setOwner(PubFun.getLoginUser());
				if (PubFun.isEmpty(medicineManuDate)) {
					medicine.setManuDate(null);
				} else {
					medicine.setManuDate(sdf.parse(medicineManuDate));
				}
				if (PubFun.isEmpty(medicineExpireDate)) {
					medicine.setExpireDate(null);
				} else {
					medicine.setExpireDate(sdf.parse(medicineExpireDate));
				}
				medicineService.save(medicine);
				isNew = PubFun.IS_NEW;
			} catch (ParseException e1) {
				e1.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e1.toString());
				exceptionMessage.setMessage("保存新建的药品信息出现异常！（日期格式转换出现错误！）");
				return ERROR;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存新建的药品信息出现异常！");
				return ERROR;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据id删除药品信息
	 * @return
	 */

	@Action(value = "deleteMediById",results = {
//			@Result(name = SUCCESS,location = "/page/medicine/medicineList.jsp")
			@Result(name = SUCCESS,type = "chain", location = "medicineManagePage")
	})
	public String deleteMedicine() {
		/*删除药品信息时，若该药品与记录或者溶液有关联，则既不能删除与之相关联的记录和溶液信息，
		  也不能删除药品本身，因为若删除药品，相关联的溶液和记录信息相应的外键就变为空了，而
		  那些外键是不允许为空的。所以这种情况下删除药品时要不能删除，并且必须有详细提示信息。*/
		try {
			Medicine med = medicineService.findById(medicineId);
			if (!med.getRecords().isEmpty() || !med.getSolutions().isEmpty()) {
				/*当要删除的药品与记录有关联时，提示信息不但要显示与之关联的具体记录编号，也要显示
				   该记录所在实验以及该实验所在项目的编号。具体实施方法为：先得到该药品关联的记录列表，
				   然后遍历记录列表，在遍历的方法体内得到当前记录的记录编号、所属实验编号、所属项目编号，
				   然后将这三个编号组合成字符串后放进之前定义好的字符串列表对象recordPromptList中，然后
				   在操作失败提示页面循环显示recordPromptList，即可得到详细的提示信息*/
				if (!med.getRecords().isEmpty()) {
					//1.得到与该药品关联的记录列表
					List<Record> recordList = new ArrayList<Record>(med.getRecords());
					recordPromptList = new ArrayList<String>(recordList.size());//存放操作失败提示信息的字符串列表对象
					//2.遍历上一步得到的记录列表
					for (Record re : recordList) {
						//2.1得到当前遍历对象所属的实验
						Experiment ex = experimentService.findById(re.getExperiment().getId());
						//2.2得到当前对象所属实验的所属项目
						Project pr = projectService.findById(ex.getProject().getId());
						//2.3将得到的三个编号信息组合成字符串后放进recordPromptList中
						recordPromptList.add(pr.getProjNo() + ">>" + ex.getEptNo() + ">>" + re.getRecordNo());
					}
				} else {
					recordPromptList = null;
				}
				/*溶液配置表的层级关系比较简单，不用像记录表那么麻烦，得到与该药品关联的溶液列表直接回显即可*/
				if (!med.getSolutions().isEmpty()) {
					solutionList =new ArrayList<Solution>(med.getSolutions());
				} else {
					solutionList = null;
				}
				medicineId = 0;
				return "prompt";
			}
			medicineService.deleteById(medicineId);

		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除药品信息出现异常！");
			medicineId = 0;
			return ERROR;
		}
		medicineId = 0;
		return SUCCESS;
	}
	
	/* set和get方法 */
//	public List<Medicine> getMedicineList() {
//		return medicineList;
//	}
//
//	public void setMedicineList(List<Medicine> medicineList) {
//		this.medicineList = medicineList;
//	}
//
//	public int getMedicineId() {
//		return medicineId;
//	}
//
//	public void setMedicineId(int medicineId) {
//		this.medicineId = medicineId;
//	}
//
//	public Medicine getMedicine() {
//		return medicine;
//	}
//
//	public void setMedicine(Medicine medicine) {
//		this.medicine = medicine;
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
//	public String getMedicineName() {
//		return medicineName;
//	}
//
//	public void setMedicineName(String medicineName) {
//		this.medicineName = medicineName;
//	}
//
//	public String getMedicineFormula() {
//		return medicineFormula;
//	}
//
//	public void setMedicineFormula(String medicineFormula) {
//		this.medicineFormula = medicineFormula;
//	}
//
//	public String getMedicineManuDate() {
//		return medicineManuDate;
//	}
//
//	public void setMedicineManuDate(String medicineManuDate) {
//		this.medicineManuDate = medicineManuDate;
//	}
//
//	public String getMedicineExpireDate() {
//		return medicineExpireDate;
//	}
//
//	public void setMedicineExpireDate(String medicineExpireDate) {
//		this.medicineExpireDate = medicineExpireDate;
//	}

}
