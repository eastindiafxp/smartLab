package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Medicine;

public interface IMedicineDao extends IBaseDao<Medicine> {

	public List<Medicine> findMyMediList(int id);

}
