package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Specimen;

public interface ISpecimenDao extends IBaseDao<Specimen> {

	public List<Specimen> findAvaiSpecimenList();

	public Specimen findByLabel(String label2);

	public List<Specimen> findMySpecimenList(int id);

}
