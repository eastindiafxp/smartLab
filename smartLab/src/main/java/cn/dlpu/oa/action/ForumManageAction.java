package cn.dlpu.oa.action;

import java.util.ArrayList;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import org.apache.struts2.convention.annotation.Action;

/**
 * 论坛版块管理
 * @author 樊晓璞 v1.0 2015-08-12
 *
 */

public class ForumManageAction extends BaseAction<Forum> {

	private static final long serialVersionUID = 1L;
	
	private List<Forum> forumList = new ArrayList<Forum>();
	
	private Forum forum = null;
	
	private Page page;
	
	/**
	 * 查询论坛管理版块列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findForumManageList() {
//		forumList = forumManageService.findAllForum();
		HQLHelper hh = new HQLHelper(Forum.class);
		hh.buildOrderBy("t.showColNo", true);
		page = forumManageService.getPage(hh, pageNo, isNew);
		forumList  = page.getResultList();
		isNew = PubFun.NOT_NEW;
		pageNo = PubFun.DEFAULT_PAGE_NO;
		return SUCCESS;
	}
	
	/**
	 * 根据id删除论坛版块
	 * @return
	 */
	public String deleteById() {
		try {
			forumManageService.deleteById(model.getId());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除论坛版块出现异常！");
			return ERROR;
		}
	}
	
	/**
	 * 跳转至新建论坛版块页面
	 * @return
	 */
	public String createNewForumPage() {
		forum = null;
		return SUCCESS;
	}
	
	/**
	 * 跳转至修改论坛模块信息页面
	 * @return
	 */
	public String editForumPage() {
		forum = forumManageService.findById(model.getId());
		return SUCCESS;
	}
	
	/**
	 * 保存新建或修改的论坛模块
	 * @return
	 */
	public String savaForum() {
		if (forum.getId() == 0) {
			//新建
			try {
				if ("".equals(forum.getName())) {
					forum.setName(null);
				}
				forumManageService.saveForum(forum);
				isNew = PubFun.IS_NEW;
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("保存论坛模块时出现异常！");
				return ERROR;
			}
		} else {
			//修改
			Forum forum2 = forumManageService.findById(forum.getId());
			forum2.setName(forum.getName());
			forum2.setDescription(forum.getDescription());
			try {
				forumManageService.updateForum(forum2);
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改论坛模块时出现异常！");
				return ERROR;
			}
		}
	}
	
	/**
	 * 点击上移按钮，使本模块与上一个模块互换位置
	 * @return
	 */
	public String moveUp() {
		try {
			forumManageService.moveUp(model.getId());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("上移论坛模块出现异常！");
			return ERROR;
		}
	}

	/**
	 * 点击下移按钮，使本模块与下一个模块互换位置
	 * @return
	 */
	public String moveDown() {
		try {
			forumManageService.moveDown(model.getId());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("下移论坛模块出现异常！");
			return ERROR;
		}
	}
	
	/* get and set method */
	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public List<Forum> getForumList() {
		return forumList;
	}

	public void setForumList(List<Forum> forumList) {
		this.forumList = forumList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
