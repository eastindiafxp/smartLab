package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Privilege;

public interface IPrivilegeDao extends IBaseDao<Privilege> {

	public List<Privilege> findTopPri();

}
