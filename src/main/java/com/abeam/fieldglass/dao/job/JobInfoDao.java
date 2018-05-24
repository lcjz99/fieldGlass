package com.abeam.fieldglass.dao.job;

import java.util.List;

import com.abeam.fieldglass.criteria.job.JobManageCriteria;
import com.abeam.fieldglass.entity.SysJobInfo;
import com.abeam.system.dao.BaseDaoI;
import com.abeam.system.pageModel.DataGrid;
import com.abeam.system.pageModel.PageHelper;

public interface JobInfoDao extends BaseDaoI<SysJobInfo> {

	DataGrid getList(JobManageCriteria criteria, PageHelper ph);

	SysJobInfo getJobInfo(String jobId);

	List<SysJobInfo> queryAll();

	void edit(SysJobInfo jobInfo);

	SysJobInfo getJobInfo(Long sjiIdN);

}
