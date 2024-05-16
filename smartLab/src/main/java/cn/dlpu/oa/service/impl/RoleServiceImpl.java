package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IRoleDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Role;
import cn.dlpu.oa.service.IRoleService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

	private IRoleDao roleDao;
	
	@Override
	public List<Role> findRoleList() {
		return roleDao.findAll();
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void deleteById(int id) throws Exception {
		roleDao.deleteById(id);
	}

	@Override
	public void delete(Role model) throws Exception {
		roleDao.deleteById(model.getId());
	}

	@Override
	public Role findRole(Role model) {
		return roleDao.findById(model.getId());
	}

	@Override
	public Role findById(int id) {
		return roleDao.findById(id);
	}

	@Override
	public void updateRole(Role role2) throws Exception {
		roleDao.update(role2);
	}

	@Override
	public void saveNewRole(Role role) throws Exception {
		roleDao.save(role);
	}

	@Override
	public List<Role> findByIds(Integer[] roleIdList) {
		return roleDao.findByIds(roleIdList);
	}

	@Override
	public void update(Role role) throws Exception {
		roleDao.update(role);
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		return roleDao.getPage(hh, pageNo, isNew);
	}

}
