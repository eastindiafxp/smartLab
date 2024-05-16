package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Medicine;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.utils.HQLHelper;

public interface IMedicineService {

	public List<Medicine> findMediList();

	public Medicine findById(int substrateId);

	public void update(Medicine medicine2) throws Exception;

	public void save(Medicine medicine) throws Exception;

	public void deleteById(int medicineId) throws Exception;

	public List<Medicine> findByIds(Integer[] medicineIdList);

	public List<Medicine> findMyMediList(int id);

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

}
