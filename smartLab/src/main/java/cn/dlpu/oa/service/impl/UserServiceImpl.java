package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IUserDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.service.IUserService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> findUserList() {
//		List<User> userList = userDao.findUserListExcept1();
		List<User> userList = userDao.findUserListExceptAdmin();
		return userList;
	}

	@Override
	public void deleteById(int id) throws Exception {
		userDao.deleteById(id);
	}

	@Override
	public void register(User user) throws Exception {
		userDao.save(user);
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public void update(User user) throws Exception {
		userDao.update(user);
	}

	@Override
	public User findByLName(String lName) {
		return userDao.findByLName(lName);
	}

	@Override
	public User login(String loginName, String password) throws Exception {
		return userDao.login(loginName, password);
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNewUser) {
		return userDao.getPage(hh, pageNo, isNewUser);
	}

	@Override
	public List<User> findByIds(Integer[] operaterIdList) {
		List<User> users = userDao.findByIds(operaterIdList);
		return users;
	}

	@Override
	public List<User> findSupervisorList() {
		List<User> users = userDao.findSupervisorList();
		return users;
	}

	@Override
	public List<User> findStudentsBySuId(int id) {
		List<User> stus = userDao.findStudentsBySuId(id);
		return stus;
	}

}
