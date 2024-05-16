package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.ITemplateDao;
import cn.dlpu.oa.domain.Template;
import cn.dlpu.oa.service.ITemplateService;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements ITemplateService {
	
	private ITemplateDao templateDao;

	public ITemplateDao getTemplateDao() {
		return templateDao;
	}

	public void setTemplateDao(ITemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	@Override
	public List<Template> findTemplateList() {
		return templateDao.findAll();
	}
	
}
