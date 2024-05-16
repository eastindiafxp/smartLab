package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findByLName(String lName);

	public User login(String loginName, String password) throws Exception;

	public List<User> findSupervisorList();

	public List<User> findUserListExcept1();

	public List<User> findUserListExceptAdmin();

	public List<User> findStudentsBySuId(int id);

}
