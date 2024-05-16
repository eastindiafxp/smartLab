package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.ISpecimenDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Specimen;
import cn.dlpu.oa.service.ISpecimenService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class SpecimenServiceImpl implements ISpecimenService {

	private ISpecimenDao specimenDao;
	
	@Override
	public List<Specimen> findSpecimenList() {
		List<Specimen> specList = specimenDao.findAll();
		return specList;
	}
	

	public ISpecimenDao getSpecimenDao() {
		return specimenDao;
	}

	public void setSpecimenDao(ISpecimenDao specimenDao) {
		this.specimenDao = specimenDao;
	}


	@Override
	public Specimen findById(int specimenId) {
		Specimen spec = specimenDao.findById(specimenId);
		return spec;
	}


	@Override
	public List<Specimen> findAvaiSpecimenList() {
		List<Specimen> list = specimenDao.findAvaiSpecimenList();
		return list;
	}


	@Override
	public Specimen findByLabel(String label2) {
		Specimen spec = specimenDao.findByLabel(label2);
		return spec;
	}


	@Override
	public void save(Specimen specimen) throws Exception {
		specimenDao.save(specimen);
	}


	@Override
	public void update(Specimen specimen2) throws Exception {
		specimenDao.update(specimen2);
	}


	@Override
	public void deleteById(int specimenId) throws Exception {
		specimenDao.deleteById(specimenId);
	}


	@Override
	public List<Specimen> findMySpecimenList(int id) {
		List<Specimen> list = specimenDao.findMySpecimenList(id);
		return list;
	}


	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		Page page = specimenDao.getPage(hh, pageNo, isNew);
		return page;
	}


	@Override
	public List<Specimen> findByIds(Integer[] specimenIdList) {
		List<Specimen> list = specimenDao.findByIds(specimenIdList);
		return list;
	}


}
