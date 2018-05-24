package com.abeam.fieldglass.dao.job.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abeam.fieldglass.criteria.job.JobManageCriteria;
import com.abeam.fieldglass.dao.job.JobInfoDao;
import com.abeam.fieldglass.entity.SysJobInfo;
import com.abeam.system.dao.impl.BaseDaoImpl;
import com.abeam.system.pageModel.DataGrid;
import com.abeam.system.pageModel.PageHelper;
import com.abeam.system.util.StringUtils;

@Repository
public class JobInfoDaoImpl extends BaseDaoImpl<SysJobInfo> implements JobInfoDao {

	@Override
	public DataGrid getList(JobManageCriteria criteria, PageHelper ph) {
		DataGrid dg = new DataGrid();

		StringBuffer hql = new StringBuffer(" from SysJobInfo t where isactive=1");

		Map<String, Object> params = new HashMap<String, Object>();
		addCriteria(hql, params, criteria);

		List<SysJobInfo> ul = this.find(hql.toString(), params, ph.getPage(), ph.getRows());
		dg.setRows(ul);

		Long total = this.count(" select count(*) " + hql.toString(), params);
		Map<String, Object> summary = dg.getSummary();
		summary.put("totalCount", total);
		dg.setTotal(total);

		return dg;
	}

	private void addCriteria(StringBuffer hql, Map<String, Object> params, JobManageCriteria criteria) {

		if (StringUtils.isNotBlank(criteria.getJobCode())) {
			hql.append(" and t.sjiCodeV = :jobCode");
			params.put("jobCode", criteria.getJobCode());
		}

	}

	@Override
	public SysJobInfo getJobInfo(String jobId) {
		return this.get(SysJobInfo.class, Long.parseLong(jobId));
	}

	@Override
	public List<SysJobInfo> queryAll() {
		return this.find("from SysJobInfo where sjiStatusN='1'");
	}

	@Override
	public void edit(SysJobInfo jobInfo) {
		this.update(jobInfo);

	}

	@Override
	public SysJobInfo getJobInfo(Long sjiIdN) {
		return this.get(SysJobInfo.class, sjiIdN);
	}

}
