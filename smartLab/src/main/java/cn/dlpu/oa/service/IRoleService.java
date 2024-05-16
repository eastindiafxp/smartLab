package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Role;
import cn.dlpu.oa.utils.HQLHelper;

public interface IRoleService {

	public List<Role> findRoleList();

	public void deleteById(int id) throws Exception;

	public void delete(Role model) throws Exception;

	public Role findRole(Role model);

	public Role findById(int id);

	public void updateRole(Role role2) throws Exception;

	public void saveNewRole(Role role) throws Exception;

	public List<Role> findByIds(Integer[] roleIdList);

	public void update(Role role) throws Exception;

	public Page getPage(HQLHelper hh, int pageNo, int isNew);
	
}
