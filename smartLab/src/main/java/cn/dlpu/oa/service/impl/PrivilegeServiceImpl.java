package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IPrivilegeDao;
import cn.dlpu.oa.domain.Privilege;
import cn.dlpu.oa.service.IPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

	private IPrivilegeDao privilegeDao;

	public IPrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}

	public void setPrivilegeDao(IPrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	@Override
	public List<Privilege> findAllPri() {
		return privilegeDao.findAll();
	}

	@Override
	public List<Privilege> findByIds(Integer[] privilegeIds) {
		return privilegeDao.findByIds(privilegeIds);
	}

	@Override
	public List<Privilege> findTopPri() {
		return privilegeDao.findTopPri();
	}

}
