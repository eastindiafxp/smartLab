package cn.dlpu.oa.service.impl;//package cn.dlpu.oa.service.impl;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.zip.ZipInputStream;
//
//import org.jbpm.api.NewDeployment;
//import org.jbpm.api.ProcessDefinition;
//import org.jbpm.api.ProcessDefinitionQuery;
//import org.jbpm.api.ProcessEngine;
//
//import cn.dlpu.oa.service.IProcessDefinitionService;
//
//public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {
//	
//	private ProcessEngine processEngine;
//
//	public ProcessEngine getProcessEngine() {
//		return processEngine;
//	}
//
//	public void setProcessEngine(ProcessEngine processEngine) {
//		this.processEngine = processEngine;
//	}
//
//	@Override
//	public List<ProcessDefinition> findLastProList() {
//		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
//		query.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION);
//		List<ProcessDefinition> list = query.list();
//		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
//		
//		for (ProcessDefinition p : list) {
//			map.put(p.getKey(), p);
//		}
//		
//		return new ArrayList<ProcessDefinition>(map.values());
//	}
//
//	@Override
//	public void deploy(File resource) {
//		NewDeployment deploy = processEngine.getRepositoryService().createDeployment();
//		ZipInputStream zipInputstream = null;
//		try {
//			zipInputstream = new ZipInputStream(new FileInputStream(resource));
//			deploy.addResourcesFromZipInputStream(zipInputstream);
//			deploy.deploy();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			if (zipInputstream != null) {
//				try {
//					zipInputstream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//	}
//
//	@Override
//	public void deleteByKey(String key) {
//		//根据key值查询流程定义
//		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
//		query.processDefinitionKey(key);//添加查询条件
//		List<ProcessDefinition> list = query.list();
//		//循环删除
//		for (ProcessDefinition p : list) {
//			processEngine.getRepositoryService().deleteDeploymentCascade(p.getDeploymentId());
//		}
//	}
//
//	@Override
//	public InputStream getImageInputStream(String id) {
//		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
//		query.processDefinitionId(id);
//		ProcessDefinition p = query.uniqueResult();
//		String name = p.getImageResourceName();
//		InputStream inputStream = processEngine.getRepositoryService().getResourceAsStream(p.getDeploymentId(), name);
//		return inputStream;
//	}
//	
//}
