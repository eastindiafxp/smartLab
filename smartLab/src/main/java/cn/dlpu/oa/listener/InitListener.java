package cn.dlpu.oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.dlpu.oa.domain.Privilege;
import cn.dlpu.oa.service.IPrivilegeService;

/**
 * 项目启动时加载权限数据的监听器
 * @author 樊晓璞 v1.0 2015-08-08
 *
 */
public class InitListener implements ServletContextListener {

	/**
	 * 初始化方法，项目启动时加载
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		//获取spring容器
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		//从spring容器中获取privilegeService
		IPrivilegeService privilegeService = (IPrivilegeService) ac.getBean("privilegeService");
		//查询权限数据
		List<Privilege> topPriList = privilegeService.findTopPri();
		//将权限数据放入application
		sce.getServletContext().setAttribute("topPriList", topPriList);
		List<Privilege> allPriList = privilegeService.findAllPri();
		sce.getServletContext().setAttribute("allPriList", allPriList);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

}
