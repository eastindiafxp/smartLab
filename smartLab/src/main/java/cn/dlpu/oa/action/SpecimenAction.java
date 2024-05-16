package cn.dlpu.oa.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Experiment;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Project;
import cn.dlpu.oa.domain.Record;
import cn.dlpu.oa.domain.Specimen;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import lombok.Data;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 样品管理Action
 * @author 樊晓璞 2016-10-03 v1.0
 *
 */
@Data
@Namespace("/specimen")
//@ParentPackage("struts-default")
@ParentPackage("json-default")
public class SpecimenAction extends BaseAction<Specimen> {

	private static final long serialVersionUID = 1L;
	/** 用于页面回显的样品列表对象 */
	private List<Specimen> specimenList = new ArrayList<Specimen>();
	/** 从页面接收的样品id */
	private int specimenId;
	/** 用于页面回显的样品对象 */
	private Specimen specimen;
	/** 从页面接收的送检日期 */
	private String specSubmitDate;
	/** 从页面接收的检测日期 */
	private String specDetectDate;
	/** 从页面接收的用于验证标签唯一性的标签对象 */
	private String label2;
	/** 用于在验证标签唯一性后进行标识的布尔对象 */
	private boolean ok = false;
	/** 用于分页的page对象 */
	private Page page;
	/** 从药品查询页面接收的筛选条件：样品标签 */
	private String specimenLabel;
	/** 从药品查询页面接收的筛选条件：送检标签 */
	private String specimenDetectLabel;
	/** 从药品查询页面接收的筛选条件：检测日期(起始)*/
	private String specimenDetectDate1;
	/** 从药品查询页面接收的筛选条件：检测日期(截止) */
	private String specimenDetectDate2;
	/** 从药品查询页面接收的筛选条件：检测手段 */
	private String specimenDetectMean;
	

	/**
	 * 查询样品列表
	 * @return
	 */
	@Action(value = "specimenManagePage", results = {
			@Result(name = SUCCESS,location = "/page/specimen/specimenList.jsp",params = {})
	})
	public String findSpecimenList() {
		//用于页面回显的样品列表对象
		User loginUser = PubFun.getLoginUser();
		HQLHelper hh = new HQLHelper(Specimen.class, 1);
		
		if (!PubFun.isAdmin(loginUser)) {
//			specimenList = specimenService.findMySpecimenList(loginUser.getId());
			hh.buildWhere("owner.id", PubFun.AND, loginUser.getId());
		}
		
		pageIncludeAll = specimenService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = specimenService.getPage(hh, pageNo, isNew);
		specimenList = page.getResultList();
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		
		/* 在页面显示某个样品所属的记录时，不仅要显示记录编号，还要显示该记录相应的实验编号与项目编号。
		    在循环语句中得到这些编号信息后，将其组合成一个字符串存储到标签表的reverse1字段中，然后在页
		    面显示该字段的值即可。 */
		for (Specimen s : specimenList) {
			/*在判断赋值之前必须先清空reverse1的值，否则保存任意一个样品信息时，其他样品的reverse1字段都
			   会一起更新为各自所属的记录信息，并给页面显示带来混乱。可能是因为多个页面所用的样品对象名称
			   均为specimen，而session却并未关闭，相互之间影响而存入数据库所致。*/
			s.setReverse1(null);
			if (s.getRecord() != null) {
				Record re = recordService.findById(s.getRecord().getId());
				Experiment ex = experimentService.findById(re.getExperiment().getId());
				Project pr = projectService.findById(ex.getProject().getId());
				s.setReverse1(pr.getProjNo() + ">>" + ex.getEptNo() + ">>"  + re.getRecordNo());
			}
		}
		specimenLabel = "";
		specimenDetectLabel = "";
		specimenDetectDate1 = "";
		specimenDetectDate2 = "";
		specimenDetectMean = "";
		return SUCCESS;
	}
	
	/**
	 * 根据筛选条件查询样品列表
	 * @return
	 */
	@Action(value = "querySpecimenList", results = {
			@Result(name = SUCCESS,location = "/page/specimen/specimenList.jsp",params = {})
	})
	public String querySpecimenList() {
		
		String condition = generateHqlCondition();
		
		HQLHelper hh = new HQLHelper(Specimen.class, 1);
		User loginUser = PubFun.getLoginUser();
		if (PubFun.isAdmin(loginUser)) {
			hh.setWhereStr("where 1 = 1 ");
		} else {
			hh.buildWhere("owner.id", PubFun.AND, loginUser.getId());
		}
		hh.setWhereStr(hh.getWhereStr() + condition.toString());
		
		pageIncludeAll = specimenService.getPage(hh, PubFun.ALL_RESULT_LIST, isNew);//包含查询到的所有结果
		allQueryList = pageIncludeAll.getAllResultList();//用于导出Excel文件的列表信息
		
		page = specimenService.getPage(hh, pageNo, isNew);
		specimenList = page.getResultList();//用于页面显示的样品列表
		for (Specimen s : specimenList) {
			s.setReverse1(null);
			if (s.getRecord() != null) {
				Record re = recordService.findById(s.getRecord().getId());
				Experiment ex = experimentService.findById(re.getExperiment().getId());
				Project pr = projectService.findById(ex.getProject().getId());
				s.setReverse1(pr.getProjNo() + ">>" + ex.getEptNo() + ">>"  + re.getRecordNo());
			}
		}
		
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
		condition.append(PubFun.hqlLikeEscape("label", specimenLabel));
//		condition.append(PubFun.hqlLikeEscape("detectLabel", specimenDetectLabel));
//		condition.append(PubFun.hqlLikeEscape("detectMean", specimenDetectMean));
//		if (!PubFun.isEmpty(specimenDetectDate1) && !PubFun.isEmpty(specimenDetectDate2)) {
//			condition.append(" AND detectDate BETWEEN '" + specimenDetectDate1 + "' AND '" + specimenDetectDate2 +"'");
//		} else if (!PubFun.isEmpty(specimenDetectDate1) && PubFun.isEmpty(specimenDetectDate2)) {
//			condition.append("AND detectDate >= '" + specimenDetectDate1 + "'");
//		} else if (PubFun.isEmpty(specimenDetectDate1) && !PubFun.isEmpty(specimenDetectDate2)) {
//			condition.append("AND detectDate <= '" + specimenDetectDate2 + "'");
//		}
		return condition.toString();
	}
	
	/**
	 * 跳转到样品详细信息页面
	 * @return
	 */
	@Action(value = "viewSpecimenDetail",results = {
			@Result(name = SUCCESS,location = "/page/specimen/specimenDetailPage.jsp")
	})
	public String findSpecDetail() {
		//用于页面回显的样品对象
		specimen = specimenService.findById(specimenId);
		/*在判断赋值之前必须先清空reverse1的值，否则保存任意一个样品信息时，其他样品的reverse1字段都
		   会一起更新为各自所属的记录信息，并给页面显示带来混乱。可能是因为多个页面所用的样品对象名称
		   均为specimen，而session却并未关闭，相互之间影响而存入数据库所致。*/
		specimen.setReverse1(null);
		//将样品所属的记录编号及其相应的实验编号、项目编号存入reverse1字段中,用于页面显示
		if (specimen.getRecord() != null) {
			Record re = recordService.findById(specimen.getRecord().getId());
			Experiment ex = experimentService.findById(re.getExperiment().getId());
			Project pr = projectService.findById(ex.getProject().getId());
			specimen.setReverse1(pr.getProjNo() + ">>" + ex.getEptNo() + ">>"  + re.getRecordNo());
		}
		specimenId = 0;
		return SUCCESS;
	}
	
	/**
	 * 跳转到样品信息新建/修改页面
	 * @return
	 */
	@Action(value = "editSpecimenPage",results = {
			@Result(name = SUCCESS,location = "/page/specimen/editSpecimenPage.jsp")
	})
	public String toEditSpecimenPage() {
		
		if (specimenId == 0) {//新建页面
			specimen = new Specimen();
			specimen.setId(0);//将样品id设为0，以免保存时出现空指针
		} else {//修改页面
			specimen = specimenService.findById(specimenId);
			specimenId = 0;//将specimenId设为0，以免新建时出现混乱
		}
		return SUCCESS;
	}
	
	/**
	 * 验证样品标签唯一性
	 * @return
	 */
	@Action(value = "validLabel",results = {
			@Result(name = "ajaxResult",type = "json",params = {"data", "data"})
	})
	public String validLabel() {
		Specimen spec = specimenService.findByLabel(label2);
		if (spec == null) {
			ok = true;
		} else {
			ok = false;
		}
		model.setReverse(ok + "");
		return "ajaxResult";
	}

	/**
	 * 保存样品信息
	 * @return
	 */
	@Action(value = "saveOrUpdateSpecimen",results = {
			@Result(name = SUCCESS,location = "/page/specimen/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String saveOrUpdateSpecimen() {
		/*在判断赋值之前必须先清空reverse1的值，否则保存任意一个样品信息时，其他样品的reverse1字段都
		   会一起更新为各自所属的记录信息，并给页面显示带来混乱。可能是因为多个页面所用的样品对象名称
		   均为specimen，而session却并未关闭，相互之间影响而存入数据库所致。*/
		specimen.setReverse1(null);//reverse1字段仅为页面显示时方便，不存入数据库，以免引起显示混乱
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (specimen.getId() == 0) {//新建
			try {
				specimen.setOwner(PubFun.getLoginUser());
//				if (PubFun.isEmpty(specSubmitDate)) {
//					specimen.setSubmitDate(null);
//				} else {
//					specimen.setSubmitDate(sdf.parse(specSubmitDate));
//				}
//				if (PubFun.isEmpty(specDetectDate)) {
//					specimen.setDetectDate(null);
//				} else {
//					specimen.setDetectDate(sdf.parse(specDetectDate));
//				}
				specimenService.save(specimen);
				isNew = PubFun.IS_NEW;
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//				exceptionMessage.setClassName(this.getClass().getName());
//				exceptionMessage.setError(e1.toString());
//				exceptionMessage.setMessage("保存新建的样品信息出现异常！(日期格式转换异常！)");
//				return ERROR;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存新建的样品信息出现异常！");
				return ERROR;
			}
		} else {//修改
			try {
				Specimen specimen2 = specimenService.findById(specimen.getId());
				specimen2.setLabel(specimen.getLabel());
//				specimen2.setDetectResult(specimen.getDetectResult());
				specimen2.setReverse(specimen.getReverse());
				specimen2.setOwner(specimen.getOwner());
//				specimen2.setDetectLabel(specimen.getDetectLabel());
//				specimen2.setDetectMean(specimen.getDetectMean());
//				if (PubFun.isEmpty(specSubmitDate)) {
//					specimen2.setSubmitDate(null);
//				} else {
//					specimen2.setSubmitDate(sdf.parse(specSubmitDate));
//				}
//				if (PubFun.isEmpty(specDetectDate)) {
//					specimen2.setDetectDate(null);
//				} else {
//					specimen2.setDetectDate(sdf.parse(specDetectDate));
//				}
				specimenService.update(specimen2);
			} catch (ParseException e1) {
				e1.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e1.toString());
				exceptionMessage.setMessage("保存修改的样品信息出现异常！(日期格式转换异常！)");
				return ERROR;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存修改的样品信息出现异常！");
				return ERROR;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据id删除样品信息
	 * @return
	 */
	@Action(value = "deleteSpecById",results = {
//			@Result(name = SUCCESS,location = "/page/solution/editSuccess.jsp"),
			@Result(name = SUCCESS,type = "chain", location = "specimenManagePage"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String deleteSpecById() {
		specimen = specimenService.findById(specimenId);
		/*在判断赋值之前必须先清空reverse1的值，否则保存任意一个样品信息时，其他样品的reverse1字段都
		   会一起更新为各自所属的记录信息，并给页面显示带来混乱。原因暂时不明。*/
		specimen.setReverse1(null);
		if (specimen.getRecord() != null) {
			Experiment ex = experimentService.findById(specimen.getRecord().getExperiment().getId());
			Project pr = projectService.findById(ex.getProject().getId());
			specimen.setReverse1(pr.getProjNo() + ">>" + ex.getEptNo() + ">>" + specimen.getRecord().getRecordNo());
			return "prompt";
		}
		try {
			specimenService.deleteById(specimenId);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除样品信息出现异常！");
			specimenId = 0;
			return ERROR;
		}
		specimenId = 0;
		return SUCCESS;
	}
	
	/* set和get方法 */
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
//	public Specimen getSpecimen() {
//		return specimen;
//	}
//
//	public void setSpecimen(Specimen specimen) {
//		this.specimen = specimen;
//	}
//
//	public String getLabel2() {
//		return label2;
//	}
//
//	public void setLabel2(String label2) {
//		this.label2 = label2;
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
//	public String getSpecimenLabel() {
//		return specimenLabel;
//	}
//
//	public void setSpecimenLabel(String specimenLabel) {
//		this.specimenLabel = specimenLabel;
//	}
//
//	public String getSpecimenDetectLabel() {
//		return specimenDetectLabel;
//	}
//
//	public void setSpecimenDetectLabel(String specimenDetectLabel) {
//		this.specimenDetectLabel = specimenDetectLabel;
//	}
//
//	public String getSpecSubmitDate() {
//		return specSubmitDate;
//	}
//
//	public void setSpecSubmitDate(String specSubmitDate) {
//		this.specSubmitDate = specSubmitDate;
//	}
//
//	public String getSpecDetectDate() {
//		return specDetectDate;
//	}
//
//	public void setSpecDetectDate(String specDetectDate) {
//		this.specDetectDate = specDetectDate;
//	}
//
//	public String getSpecimenDetectDate1() {
//		return specimenDetectDate1;
//	}
//
//	public void setSpecimenDetectDate1(String specimenDetectDate1) {
//		this.specimenDetectDate1 = specimenDetectDate1;
//	}
//
//	public String getSpecimenDetectDate2() {
//		return specimenDetectDate2;
//	}
//
//	public void setSpecimenDetectDate2(String specimenDetectDate2) {
//		this.specimenDetectDate2 = specimenDetectDate2;
//	}
//
//	public String getSpecimenDetectMean() {
//		return specimenDetectMean;
//	}
//
//	public void setSpecimenDetectMean(String specimenDetectMean) {
//		this.specimenDetectMean = specimenDetectMean;
//	}
//
//	public Page getPageIncludeAll() {
//		return pageIncludeAll;
//	}
//
//	public void setPageIncludeAll(Page pageIncludeAll) {
//		this.pageIncludeAll = pageIncludeAll;
//	}

	
}
