package cn.dlpu.oa.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 主页面各部分跳转
 * @author 樊晓璞 v1.0 2015-08-09
 *
 */

@Namespace("/main")
@ParentPackage("struts-default")
public class MainAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 跳转至主页面
	 * @return
	 */
	@Action(value = "mainPage", results = {
			@Result(name = SUCCESS,location = "/page/main/main.jsp",params = {})
	})
	public String mainPage() {
		return SUCCESS;
	}
	
	/**
	 * 跳转至头部页面
	 * @return
	 */
	@Action(value = "topPage", results = {
			@Result(name = SUCCESS,location = "/page/main/top.jsp",params = {})
	})
	public String topPage() {
		return SUCCESS;
	}
	
	/**
	 * 跳转至左边栏菜单页面
	 * @return
	 */
	@Action(value = "leftPage", results = {
			@Result(name = SUCCESS,location = "/page/main/menu.jsp",params = {})
	})
	public String leftPage() {
		return SUCCESS;
	}
	
	/**
	 * 跳转至右部主区域
	 * @return
	 */
	@Action(value = "rightPage", results = {
			@Result(name = SUCCESS,location = "/page/main/right.jsp",params = {})
	})
	public String rightPage() {
		return SUCCESS;
	}
	
	/**
	 * 跳转至底部页面
	 * @return
	 */
	@Action(value = "bottomPage", results = {
			@Result(name = SUCCESS,location = "/page/main/bottom.jsp",params = {})
	})
	public String bottomPage() {
		return SUCCESS;
	}
	
	/**
	 * 跳转至桌面
	 * @return
	 */
	@Action(value = "desktop", results = {
			@Result(name = SUCCESS,location = "/page/main/right.jsp",params = {})
	})
	public String desktop() {
		return SUCCESS;
	}
}
