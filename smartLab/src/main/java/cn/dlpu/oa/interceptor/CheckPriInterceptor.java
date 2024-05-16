package cn.dlpu.oa.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.dlpu.oa.domain.Privilege;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.PubFun;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.convention.annotation.InterceptorRef;

/**
 * 进行权限检查的拦截器
 * @author 樊晓璞 v1.0 2015-08-09
 *
 */
@InterceptorRef("checkPrivilege")
public class CheckPriInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	
	private List<Privilege> priList = new ArrayList<Privilege>();
	
	private List<String> urlList = new ArrayList<String>();
	
	
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		/*若浏览器超过十分钟没有与服务器进行交互，则清空session。推荐使用方法一，因为这样的话在项目部署之后再修改此值时，
		 *只需要改数据库的值就可以，不用去修改配置文件，否则整个项目还需要重新部署。*/
//		方法一：(也可以写在监听器中)
//		int sessionTimeOut = Integer.parseInt(PubFun.getConfigByName(PubFun.SESSION_TIMEOUT));
//		session.setMaxInactiveInterval(sessionTimeOut);//单位为秒
		
//		方法二：（在web.xml文件中设置session寿命,如以下代码所示：）
/*		  <session-config>
		     <session-timeout>10</session-timeout>（单位为分钟）
		  </session-config>*/
		String namespace = ai.getProxy().getNamespace();
		String actionName = ai.getProxy().getActionName();
		String url = namespace + "/" + actionName;
		
		//从session中获取登录用户
		User user = (User) session.getAttribute(PubFun.LOGIN_USER);
		//一、用户没有登录时
		if (null == user) {
			//如果用户访问的是登录功能,则放行，否则跳转至登录页面
			if ("/user/loginPage".equals(url) || "/user/login".equals(url)) {
				ai.invoke();
			} else {
				return "loginPage";
			}
		//二、用户已经登录
		} else {
			priList = null;
			priList = (List<Privilege>) ServletActionContext.getServletContext().getAttribute(PubFun.ALL_PRI_LIST);
			for (int i = 0; i < priList.size(); i++) {
				urlList.add(priList.get(i).getUrl());
			}
			if (urlList.contains(url)) {
				//如果用户拥有访问权限，则放行，否则跳转至无权限提示页面
				boolean flag = user.hasThisPriByUrl(url);
				if (flag) {
					ai.invoke();
				} else {
					urlList = new ArrayList<String>();
					return "noPriPage";
				}
			}
			urlList = new ArrayList<String>();
		}
		
		return ai.invoke();
	}

	/* setter and getter method */
	public List<Privilege> getPriList() {
		return priList;
	}

	public void setPriList(List<Privilege> priList) {
		this.priList = priList;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
	
}
