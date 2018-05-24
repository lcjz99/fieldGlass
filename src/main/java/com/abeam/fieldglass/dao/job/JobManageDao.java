package com.abeam.fieldglass.dao.job;

import com.abeam.fieldglass.criteria.job.JobLogsCriteria;
import com.abeam.fieldglass.entity.SysJobManage;
import com.abeam.system.dao.BaseDaoI;
import com.abeam.system.pageModel.DataGrid;
import com.abeam.system.pageModel.PageHelper;

public interface JobManageDao extends BaseDaoI<SysJobManage> {

	void create(SysJobManage sjm);

	DataGrid getList(JobLogsCriteria criteria, PageHelper ph);

}
