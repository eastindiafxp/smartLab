package cn.dlpu.oa.action;

import java.util.ArrayList;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Topic;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import org.apache.struts2.convention.annotation.Action;

/**
 * 论坛
 * @author 樊晓璞 v1.0 2015-08-13
 *
 */

public class ForumAction extends BaseAction<Forum> {

	private static final long serialVersionUID = 1L;
	
	/** 传递给页面的模块列表 */
	private List<Forum> forumList = new ArrayList<Forum>();
	/** 传递给页面的主题列表 */
	private List<Topic> topicList = new ArrayList<Topic>();
	/** 根据页面回传的id查询的模块，并用于页面回显 */
	private Forum forum;
	/**帖子类型参数： 0-全部主题,1-全部精华贴 */
	private int viewType = 0;
	/** 排序条件
	 * 0-默认排序（按最后更新时间排序，但所有置顶帖都在前面）
	 * 1-按最后更新时间排序
	 * 2-按主题发表时间排序
	 * 3-按回复数量排序 */
	private int orderBy = 0;
	/** true-升序,false-降序 */
	private boolean asc = true;
	/**根据分页信息查询出的页面信息*/
	private Page page;
	
	/**
	 * 查询版块列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findForumList() {
		forum = null;
//		forumList = forumService.findForumList();
		HQLHelper hh = new HQLHelper(Forum.class);
		hh.buildOrderBy("t.showColNo", true);
		page = forumService.getPage(hh, pageNo, isNew);
		forumList = page.getResultList();
		pageNo = PubFun.DEFAULT_PAGE_NO;
		this.initCondition();
		return SUCCESS;
	}
	
	/**
	 * 根据模块id查询相应的主题列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findTopicList() {
		//用于页面回显的forum
		forum =  forumService.findById(model.getId());//用属性驱动获取id不成功
		
		/*根据筛选条件组织分页查询语句*/
		HQLHelper hh = new HQLHelper(Topic.class);
		hh.buildWhere("forumId", PubFun.AND, forum.getId());
		//根据帖子类型筛选帖子
		if (viewType == 1) {
			//查询精华帖
			hh.buildWhere("topicType", "01");
		}
		//排序
		if (orderBy == 0) {
			hh.buildOrderBy("(case t.topicType when '04' then 1 else 5 end)", true);
			hh.buildOrderBy("t.postTime", false);
		} else if (orderBy == 1) {
			hh.buildOrderBy("t.updateTime", asc);
		} else if (orderBy == 2) {
			hh.buildOrderBy("t.postTime", asc);
		} else {
			hh.buildOrderBy("t.replyCount", asc);
		}
//		topicList = topicService.findTopicByForumId(forum.getId());
		page = topicService.getPage(hh, pageNo, isNew);
		pageNo = PubFun.DEFAULT_PAGE_NO;
		isNew = PubFun.NOT_NEW;
		topicList = page.getResultList();
		
		return SUCCESS;
	}
	
	/**
	 * 初始化查询（主题列表筛选条件的）参数
	 * 说明：查询主题列表时通过下拉列表框设定了三个参数，但是当转到其他模块查询相应的主题列表时，这三个参数需要初始化
	 */
	public void initCondition() {
		this.viewType = 0;
		this.orderBy = 0;
		this.asc = true;
	}
	
	/* set and get method */
	public List<Forum> getForumList() {
		return forumList;
	}

	public void setForumList(List<Forum> forumList) {
		this.forumList = forumList;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
