package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IUserDao;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.MD5Utils;

public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findByLName(String lName) {
		Session session = getSession();
		String hql = "select u from User u where u.loginName = ?";
		User user = (User) session.createQuery(hql).setParameter(0, lName).uniqueResult();
		closeSession();
		return user;
	}

	@Override
	public User login(String loginName, String password) throws Exception {
		Session session = getSession();
		String pwd = MD5Utils.md5(password);
		String hql  = "select u from User u where u.loginName = ? and u.password = ?";
		User user = (User) session.createQuery(hql).setParameter(0, loginName).setParameter(1, pwd).uniqueResult();
		closeSession();
		return user;
	}

	
	@Override
	public List<User> findSupervisorList() {
		String hql = "select s from User s where s.supervisor.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, 1);
		return query.list();
	}

	
	@Override
	public List<User> findUserListExcept1() {
		String hql = "select u from User u where u.id != ?";
		Query query = getSession().createQuery(hql).setParameter(0, 31);
		return query.list();
	}

	@Override
	public List<User> findUserListExceptAdmin() {
		String hql = "select u from User u where u.loginName != ?";
		Query query = getSession().createQuery(hql).setParameter(0, "admin");
		return query.list();
	}


	
	@Override
	public List<User> findStudentsBySuId(int id) {
		String hql = "select s from User s where s.supervisor.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, id);
		return query.list();
	}

}
