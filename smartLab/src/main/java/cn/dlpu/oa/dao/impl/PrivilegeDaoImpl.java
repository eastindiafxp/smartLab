package cn.dlpu.oa.dao.impl;

import java.util.List;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IPrivilegeDao;
import cn.dlpu.oa.domain.Privilege;


public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements IPrivilegeDao {

	@Override
	public List<Privilege> findTopPri() {
		String hql = "from Privilege p where p.parent is null";
		return this.getSession().createQuery(hql).list();
	}

}
