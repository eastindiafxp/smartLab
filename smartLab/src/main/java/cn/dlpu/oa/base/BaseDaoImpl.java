package cn.dlpu.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.HibernateSessionFactory;
import cn.dlpu.oa.utils.PubFun;

public class BaseDaoImpl<T> implements IBaseDao<T> {

	private Class<T> clazz;
	
	public BaseDaoImpl(String pub){}
	
	/**
	 * 在构造方法中获得实体类型
	 */
	
	public BaseDaoImpl(){
//		this.getClass();//获得继承此类的实体类型
		ParameterizedType superCalss = (ParameterizedType) this.getClass().getGenericSuperclass();//获得真正的父类
		Type[] types = superCalss.getActualTypeArguments();
		clazz = (Class<T>) types[0];
	}
	
	@Override
	public void save(T t) throws Exception {
		Session session = getSession();
		Transaction tc = session.getTransaction();
		tc.begin();
		try {
			session.save(t);
			tc.commit();
//			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tc.rollback();
		} finally {
//			session.clear();
			closeSession();
		}
	}

	@Override
	public void deleteById(int id) throws Exception {
		Session session = getSession();
		Transaction tc = session.getTransaction();
		tc.begin();
		try {
			session.delete(session.get(clazz, id));
			tc.commit();
//			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tc.rollback();
		} finally {
//			session.clear();
			closeSession();
		}
	}

	@Override
	public void update(T t) throws Exception {
		Session session = getSession();
		Transaction tc = session.getTransaction();
		tc.begin();
		try {
			session.update(t);
			tc.commit();
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tc.rollback();
		} finally {
//			session.flush();
//			session.clear();
			closeSession();
		}
	}

	
	@Override
	public T findById(int id) {
		return (T) getSession().get(clazz, id);
	}

	
	@Override
	public List<T> findByIds(Integer[] ids) {
		String hql = "FROM " + clazz.getSimpleName() + " WHERE ID IN (:idss)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idss", ids);
		return query.list();
	}

	
	@Override
	public List<T> findAll() {
		String hql = "FROM " + clazz.getSimpleName();//getSimpleName()：得到clazz的类名
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 获得session
	 * @return
	 */
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	/**
	 * 关闭session
	 */
	public void closeSession() {
		HibernateSessionFactory.closeSession();
	}

	/**
	 * 通用分页方法
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		//准备查询用的HQL语句及其参数
		String strPageSize;
		if (hh.getPageSizeStrategy() == PubFun.PAGESIZE_STRATEGY_1) {
			strPageSize = PubFun.getConfigByName(PubFun.STR_PAGE_SIZE1);
		} else if (hh.getPageSizeStrategy() == PubFun.PAGESIZE_STRATEGY_2) {
			strPageSize = PubFun.getConfigByName(PubFun.STR_PAGE_SIZE2);
		} else {
			strPageSize = PubFun.getConfigByName(PubFun.STR_PAGE_SIZE1);
		}
		int pageSize = Integer.parseInt(strPageSize);
		String countHql = hh.getCountHQL();
		String listHql = hh.getHQL();
		List<Object> args = hh.getArgs();
		
		/* 查询结果数 */
		Query query = this.getSession().createQuery(countHql);
		if (args != null && args.size() > 0) {
			int index = 0;
			for (Object o : args) {
				query.setParameter(index++, o);
			}
		}
		Long resultCount = (Long) query.uniqueResult();
		
		//当把最后一页的所有数据都删除的时候，当前页码数要减一，使其可以自动跳回到前一页
		int pageCount = (resultCount.intValue() + pageSize - 1)/pageSize;
		if (pageNo > pageCount) {
			pageNo = pageNo - 1;
		}
		//判断是否为新建，若是新建（新建用户，发表回复等），则新建完成之后要跳到最后一页
		if (isNew == PubFun.IS_NEW) {
			pageNo = pageCount;
		}
		/* 查询结果列表 */
		query = this.getSession().createQuery(listHql);
		if (args != null && args.size() > 0) {
			int index = 0;
			for (Object o : args) {
				query.setParameter(index++, o);
			}
		}
		
		//当导出Excel文件时，需要获得所有查询到的结果，而不仅仅是当前页面中的结果
		if (pageNo == PubFun.ALL_RESULT_LIST) {
			List allResultList = query.list();
			return new Page(allResultList);
		}
		
		query.setFirstResult(pageSize * (pageNo - 1));
		query.setMaxResults(pageSize);
		List resultList = query.list();
		
		return new Page(pageNo, pageSize, resultCount.intValue(), resultList);
	}
	
	/**
	 * 通过配置名称查询相应的配置值
	 * @param configName
	 * @return configValue
	 */
	@Override
	public String getConfigByName(String configName) {
		String hql = "SELECT id.configValue FROM SysConfig s WHERE s.id.configName = ?";
		Query query = getSession().createQuery(hql).setParameter(0, configName);
		String value = (String) query.uniqueResult();
		return value;
	}

}








