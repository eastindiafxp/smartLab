package cn.dlpu.oa.action;//package cn.dlpu.oa.action;
//
//import java.io.File;
//import java.util.List;
//
//import org.apache.struts2.ServletActionContext;
//import org.jbpm.api.ProcessDefinition;
//
//import cn.dlpu.oa.base.BaseAction;
//import cn.dlpu.oa.domain.Template;
//
///**
// * 
// * @author 樊晓璞 v1.0 2015-08-28
// *
// */
//public class TemplateAction extends BaseAction<Template> {
//
//	private static final long serialVersionUID = 1L;
//	
//	private List<Template> templateList;
//	
//	private List<ProcessDefinition> proDefList;
//	
//	private Template template;
//	/** 用于文件上传 */
//	private File resource;
//	
//	/**
//	 * 查询模板列表
//	 * @return
//	 */
//	public String findTemplateList() {
//		templateList = templateService.findTemplateList();
//		return SUCCESS;
//	}
//	
//	/**
//	 * 删除模板
//	 * @return
//	 */
//	public String deleteById() {
//		
//		return SUCCESS;
//	}
//	
//	/**
//	 * 跳转到修改模板页面
//	 * @return
//	 */
//	public String editTemplatePage() {
//		//准备数据-流程定义
//		proDefList = proDefService.findLastProList();
//		return SUCCESS;
//	}
//	
//	/**
//	 * 保存新建或修改的模板
//	 * @return
//	 */
//	public String saveTemplate() {
//		//将上传的文件保存至uploadFiles中
//		String realPath = ServletActionContext.getServletContext().getRealPath("/uploadFiles");//获得uploadFiles的真实路径
//		File destination = new File(realPath + File.separator + resource.getName());
//		resource.renameTo(destination);
//		return  SUCCESS;
//	}
//	
//	/**
//	 * 下载模板文档
//	 * @return
//	 */
//	public String download() {
//		
//		return SUCCESS;
//	}
//
//	/* set and get method */
//	public List<Template> getTemplateList() {
//		return templateList;
//	}
//
//	public void setTemplateList(List<Template> templateList) {
//		this.templateList = templateList;
//	}
//
//	public List<ProcessDefinition> getProDefList() {
//		return proDefList;
//	}
//
//	public void setProDefList(List<ProcessDefinition> proDefList) {
//		this.proDefList = proDefList;
//	}
//
//	public Template getTemplate() {
//		return template;
//	}
//
//	public void setTemplate(Template template) {
//		this.template = template;
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
//}
