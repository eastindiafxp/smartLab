package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.SysConfig;

public interface ISysConfigDao extends IBaseDao<SysConfig> {

	public List<SysConfig> findPageSetList();

	public SysConfig findByConfigName(String configName);

	public void updatePageSet(SysConfig sysConfig) throws Exception;

	public List<SysConfig> findSessionSetList();

}
