package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IMedicineDao;
import cn.dlpu.oa.domain.Medicine;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.service.IMedicineService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements IMedicineService {
	
	private IMedicineDao medicineDao;
	
	@Override
	public List<Medicine> findMediList() {
		List<Medicine> mediList = medicineDao.findAll();
		return mediList;
	}

	
	/* set和get方法 */
	public IMedicineDao getMedicineDao() {
		return medicineDao;
	}

	public void setMedicineDao(IMedicineDao medicineDao) {
		this.medicineDao = medicineDao;
	}


	@Override
	public Medicine findById(int substrateId) {
		Medicine medi = medicineDao.findById(substrateId);
		return medi;
	}


	@Override
	public void update(Medicine medicine2) throws Exception {
		medicineDao.update(medicine2);
	}


	@Override
	public void save(Medicine medicine) throws Exception {
		medicineDao.save(medicine);
	}


	@Override
	public void deleteById(int medicineId) throws Exception {
		medicineDao.deleteById(medicineId);
	}


	@Override
	public List<Medicine> findByIds(Integer[] medicineIdList) {
		List<Medicine> ms = medicineDao.findByIds(medicineIdList);
		return ms;
	}


	@Override
	public List<Medicine> findMyMediList(int id) {
		List<Medicine> list = medicineDao.findMyMediList(id);
		return list;
	}


	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		Page page = medicineDao.getPage(hh, pageNo, isNew);
		return page;
	}

}
