package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.SysConfig;

public interface ISysConfigService {

	public List<SysConfig> findPageSetList();

	public SysConfig findByConfigName(String configName);

	public void updatePageSet(SysConfig sysConfig) throws Exception;

	public List<SysConfig> findSessionSetList();

}
