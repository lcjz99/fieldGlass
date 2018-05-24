package com.abeam.fieldglass.service.job;

import java.util.Date;

import org.quartz.SchedulerException;

import com.abeam.fieldglass.criteria.job.JobLogsCriteria;
import com.abeam.fieldglass.criteria.job.JobManageCriteria;
import com.abeam.fieldglass.entity.SysJobInfo;
import com.abeam.system.pageModel.DataGrid;
import com.abeam.system.pageModel.PageHelper;

public interface JobManageService {
	/**
	 * job 信息分页查询
	 * 
	 * @param criteria
	 * @param ph
	 * @return
	 */
	DataGrid getList(JobManageCriteria criteria, PageHelper ph);

	void changeStatus(String jobId, String status) throws SchedulerException;

	void save(String jobType, Date runDate, long start, long end, boolean isSuccess);

	SysJobInfo getJobInfo(String jobId);

	DataGrid getList(JobLogsCriteria criteria, PageHelper ph);

	void edit(SysJobInfo jobInfo);

}
