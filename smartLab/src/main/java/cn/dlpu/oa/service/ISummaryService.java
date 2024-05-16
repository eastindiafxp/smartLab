package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.StageSummary;
import cn.dlpu.oa.utils.HQLHelper;

public interface ISummaryService {

	public List<StageSummary> findSummaryList();

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

	public StageSummary findById(int summaryId);

	public void update(StageSummary summary) throws Exception;

	public void save(StageSummary summary) throws Exception;

	public List<StageSummary> findByIds(Integer[] summaryIdList);

	public void deleteById(int summaryId) throws Exception;

}
