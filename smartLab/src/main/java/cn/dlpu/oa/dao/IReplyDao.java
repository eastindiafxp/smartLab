package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Reply;
import cn.dlpu.oa.domain.Topic;

public interface IReplyDao extends IBaseDao<Reply> {

	public List<Reply> findReplyList(Topic topic);

//	public Page getPage(int pageNo, Topic topic);

}
