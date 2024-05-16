package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.ISysConfigDao;
import cn.dlpu.oa.domain.SysConfig;
import cn.dlpu.oa.service.ISysConfigService;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl implements ISysConfigService {
	
	private ISysConfigDao sysConfigDao;
	
	@Override
	public List<SysConfig> findPageSetList() {
		return sysConfigDao.findPageSetList();
	}

	public ISysConfigDao getSysConfigDao() {
		return sysConfigDao;
	}

	public void setSysConfigDao(ISysConfigDao sysConfigDao) {
		this.sysConfigDao = sysConfigDao;
	}

	@Override
	public SysConfig findByConfigName(String configName) {
		return sysConfigDao.findByConfigName(configName);
	}

	@Override
	public void updatePageSet(SysConfig sysConfig) throws Exception {
		this.sysConfigDao.updatePageSet(sysConfig);
	}

	@Override
	public List<SysConfig> findSessionSetList() {
		return sysConfigDao.findSessionSetList();
	}

}
