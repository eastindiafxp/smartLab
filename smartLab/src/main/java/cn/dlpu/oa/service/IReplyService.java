package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Reply;
import cn.dlpu.oa.domain.Topic;
import cn.dlpu.oa.utils.HQLHelper;

public interface IReplyService {

	public void saveReply(Reply reply) throws Exception;

	public List<Reply> findReplyList(Topic topic);

//	public Page getPage(int pageNo, Topic topic);

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

}
