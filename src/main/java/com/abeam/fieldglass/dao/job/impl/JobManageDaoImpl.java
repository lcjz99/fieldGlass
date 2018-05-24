package com.abeam.fieldglass.dao.job.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abeam.fieldglass.criteria.job.JobLogsCriteria;
import com.abeam.fieldglass.dao.job.JobManageDao;
import com.abeam.fieldglass.entity.SysJobManage;
import com.abeam.system.dao.impl.BaseDaoImpl;
import com.abeam.system.pageModel.DataGrid;
import com.abeam.system.pageModel.PageHelper;
import com.abeam.system.util.StringUtils;

@Repository
public class JobManageDaoImpl extends BaseDaoImpl<SysJobManage> implements JobManageDao {

	@Override
	public DataGrid getList(JobLogsCriteria criteria, PageHelper ph) {
		DataGrid dg = new DataGrid();

		StringBuffer hql = new StringBuffer(" from SysJobManage t where isactive=1");

		Map<String, Object> params = new HashMap<String, Object>();
		addCriteria(hql, params, criteria);

		List<SysJobManage> ul = this.find(hql.toString() + " order by inserttime desc", params, ph.getPage(),
				ph.getRows());
		dg.setRows(ul);

		Long total = this.count(" select count(*) " + hql.toString(), params);
		Map<String, Object> summary = dg.getSummary();
		summary.put("totalCount", total);
		dg.setTotal(total);

		return dg;
	}

	private void addCriteria(StringBuffer hql, Map<String, Object> params, JobLogsCriteria criteria) {

		if (StringUtils.isNotBlank(criteria.getJobType())) {
			hql.append(" and t.sjmJobTypeV = :jobType");
			params.put("jobType", criteria.getJobType());
		}

	}

	@Override
	public void create(SysJobManage sjm) {
		this.save(sjm);

	}
}
