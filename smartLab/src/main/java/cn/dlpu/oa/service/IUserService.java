package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.HQLHelper;

public interface IUserService {

	public List<User> findUserList();

	public void deleteById(int id) throws Exception;

	public void register(User user) throws Exception;

	public User findById(int id);

	public void update(User user) throws Exception;

	public User findByLName(String lName);

	public User login(String loginName, String password) throws Exception;

	public Page getPage(HQLHelper hh, int pageNo, int isNewUser);

	public List<User> findByIds(Integer[] operaterIdList);

	public List<User> findSupervisorList();

	public List<User> findStudentsBySuId(int id);

}
