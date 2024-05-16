package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Specimen;
import cn.dlpu.oa.utils.HQLHelper;

public interface ISpecimenService {

	public List<Specimen> findSpecimenList();

	public Specimen findById(int specimenId);

	public List<Specimen> findAvaiSpecimenList();

	public Specimen findByLabel(String label2);

	public void save(Specimen specimen) throws Exception;

	public void update(Specimen specimen2) throws Exception;

	public void deleteById(int specimenId) throws Exception;

	public List<Specimen> findMySpecimenList(int id);

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

	public List<Specimen> findByIds(Integer[] specimenIdList);

}
