package cn.dlpu.oa.listener;

//import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

//import org.apache.struts2.ServletActionContext;

import cn.dlpu.oa.utils.PubFun;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		
		/*若浏览器超过十分钟没有与服务器进行交互，则清空session。推荐使用方法一，因为这样的话在项目部署之后再修改此值时，
		 *只需要改数据库的值就可以，不用去修改配置文件，否则整个项目还需要重新部署。*/
//		方法一：（也可以写在过滤器中）
//		HttpSession session = (HttpSession) ActionContext.getContext().getSession();
		HttpSession session = ServletActionContext.getRequest().getSession();
		int sessionTimeOut = Integer.parseInt(PubFun.getConfigByName(PubFun.SESSION_TIMEOUT));
		session.setMaxInactiveInterval(sessionTimeOut);//单位为秒
		
//		方法二：（在web.xml文件中设置session寿命，如以下代码所示：）
/*		  <session-config>   
		     <session-timeout>10</session-timeout>（单位为分钟）
		  </session-config>*/
		/*HttpSession session = ServletActionContext.getRequest().getSession();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String time = sdf.format(now);
		int day = 1;
		if (Integer.parseInt(time)%2 == 1) {
			day = 1;
		} else if (Integer.parseInt(time)%2 == 0) {
			day = 0;
		}
		session.setAttribute("day", day);*/
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("session被创建。");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		
		System.out.println("===========================================");
		System.out.println("session被销毁。");
		
	}

}
