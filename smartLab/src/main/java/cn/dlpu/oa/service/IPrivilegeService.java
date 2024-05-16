package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Privilege;

public interface IPrivilegeService {

	public List<Privilege> findAllPri();

	public List<Privilege> findByIds(Integer[] privilegeIds);

	public List<Privilege> findTopPri();

}
