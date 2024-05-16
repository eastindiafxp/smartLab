package cn.dlpu.oa.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.ISysConfigDao;
import cn.dlpu.oa.domain.SysConfig;

public class SysConfigDaoImpl extends BaseDaoImpl<SysConfig> implements ISysConfigDao {

	
	@Override
	public List<SysConfig> findPageSetList() {
		String hql = "from SysConfig s where s.id.configName like 'Page%'";
		return this.getSession().createQuery(hql).list();
	}

	
	@Override
	public List<SysConfig> findSessionSetList() {
		String hql = "from SysConfig s where s.id.configName like 'Session%'";
		return this.getSession().createQuery(hql).list();
	}
	
	
	@Override
	public SysConfig findByConfigName(String configName) {
		String hql = "from SysConfig s where s.id.configName = ?";
		List<SysConfig> list = this.getSession().createQuery(hql).setParameter(0, configName).list();
		return list.get(list.size() - 1);
	}
	
	/**
	 * 若此方法使用hibernate，则调用session的update方法的时候，session会根据id选定要更新的对象，
	 * 但是sysconfig用的是联合主键，并且此处要更改的值就是联合主键中的一个，因此此处需要使用JDBC来完成。
	 * 还有一种思路就是将原先的id值也传过来，这时可以用hibernate进行更新
	 */
	@Override
	public void updatePageSet(SysConfig sysConfig) throws Exception {
		
		Session session = this.getSession();
		Transaction tc = session.getTransaction();
		tc.begin();
		
		String sql = "UPDATE SYSCONFIG s SET s.ConfigValue = ? where s.ConfigName = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dlpu_oa";
			String dbUser = "dlpuoa";
			String dbPwd = "952008fxpdl";
			conn = DriverManager.getConnection(url, dbUser, dbPwd);
			
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setObject(1, sysConfig.getId().getConfigValue());
			ps.setObject(2, sysConfig.getId().getConfigName());
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tc.rollback();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			closeSession();
		}
	}


}
