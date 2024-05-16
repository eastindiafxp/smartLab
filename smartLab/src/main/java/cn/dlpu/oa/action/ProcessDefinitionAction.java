package cn.dlpu.oa.action;//package cn.dlpu.oa.action;
//
//import java.io.File;
//import java.io.InputStream;
//import java.util.List;
//
//import org.jbpm.api.ProcessDefinition;
//
//import cn.dlpu.oa.service.IProcessDefinitionService;
//
////import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//
///**
// * 流程定义管理
// * @author 樊晓璞 v1.0 2015-08-28
// *
// */
//public class ProcessDefinitionAction extends ActionSupport {
//
//	private static final long serialVersionUID = 1L;
//	
//	private IProcessDefinitionService proDefService;
//	
//	private List<ProcessDefinition> lastProList;
//	/** 用于文件上传 */
//	private File resource;
//	
//	private String key;
//	/** 用于文件下载的输入流 */
//	private InputStream inputStream;
//	
//	private String id;
//	
//	/**
//	 * 查询流程列表
//	 * @return
//	 */
//	public String findPrcDefList() {
//		lastProList = proDefService.findLastProList();
////		ActionContext.getContext().getValueStack().set("lastProList", getLastProList());
//		return SUCCESS;
//	}
//	
//	/**
//	 * 根据key值删除已有流程
//	 * @return
//	 */
//	public String deleteByKey() {
//		proDefService.deleteByKey(key);
//		return SUCCESS;
//	}
//	
//	/**
//	 * 跳转到流程定义部署页面
//	 * @return
//	 */
//	public String prcDeployPage() {
//		return SUCCESS;
//	}
//	
//	/**
//	 * 部署流程定义
//	 * @return
//	 */
//	public String deployPro() {
//		//使用上传的文件部署流程定义
//		proDefService.deploy(resource);
//		return SUCCESS;
//	}
//	
//	/**
//	 * 查看流程图
//	 * @return
//	 */
//	public String showProImage() {
//		inputStream = proDefService.getImageInputStream(id);
//		return SUCCESS;
//	}
//	
//	
//	/* set and get method */
//	public IProcessDefinitionService getProDefService() {
//		return proDefService;
//	}
//
//	public void setProDefService(IProcessDefinitionService proDefService) {
//		this.proDefService = proDefService;
//	}
//
//	public List<ProcessDefinition> getLastProList() {
//		return lastProList;
//	}
//
//	public void setLastProList(List<ProcessDefinition> lastProList) {
//		this.lastProList = lastProList;
//	}
//
//	public File getResource() {
//		return resource;
//	}
//
//	public void setResource(File resource) {
//		this.resource = resource;
//	}
//
//	public String getKey() {
//		return key;
//	}
//
//	public void setKey(String key) {
//		this.key = key;
//	}
//
//	public InputStream getInputStream() {
//		return inputStream;
//	}
//
//	public void setInputStream(InputStream inputStream) {
//		this.inputStream = inputStream;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//	
//}
