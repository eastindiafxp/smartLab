package cn.dlpu.oa.base;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.utils.HQLHelper;

/**
 * 通用Dao
 * @author eastindia
 *
 */

public interface IBaseDao<T> {
	
	/*
	 * 添加通用方法
	 */
	public void save(T t) throws Exception;
	
	public void deleteById(int id) throws Exception;
	
	public void update(T t) throws Exception;
	
	public T findById(int id);
	
	public List<T> findByIds(Integer[] ids);
	
	public List<T> findAll();
	//公共分页方法
	public Page getPage(HQLHelper hh, int pageNo, int isNew);
	//通过配置名称查询相应的配置值
	public String getConfigByName(String configName);
	
}
