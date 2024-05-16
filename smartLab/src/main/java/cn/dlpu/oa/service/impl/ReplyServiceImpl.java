package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IReplyDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Reply;
import cn.dlpu.oa.domain.Topic;
import cn.dlpu.oa.service.IReplyService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements IReplyService {

	private IReplyDao replyDao;
	
	@Override
	public void saveReply(Reply reply) throws Exception {
		replyDao.save(reply);
	}

	public IReplyDao getReplyDao() {
		return replyDao;
	}

	public void setReplyDao(IReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public List<Reply> findReplyList(Topic topic) {
		return replyDao.findReplyList(topic);
	}

	/*@Override
	public Page getPage(int pageNo, Topic topic) {
		return replyDao.getPage(pageNo, topic);
	}*/

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		return replyDao.getPage(hh, pageNo, isNew);
	}

}
