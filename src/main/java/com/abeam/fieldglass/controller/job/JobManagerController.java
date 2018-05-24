package com.abeam.fieldglass.controller.job;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abeam.fieldglass.constants.Constants;
import com.abeam.fieldglass.criteria.job.JobLogsCriteria;
import com.abeam.fieldglass.criteria.job.JobManageCriteria;
import com.abeam.fieldglass.entity.SysJobInfo;
import com.abeam.fieldglass.entity.SysJobManage;
import com.abeam.fieldglass.service.common.SpringContextHolder;
import com.abeam.fieldglass.service.job.JobManageService;
import com.abeam.system.controller.BaseController;
import com.abeam.system.pageModel.DataGrid;
import com.abeam.system.pageModel.Json;
import com.abeam.system.pageModel.PageHelper;

@Controller
@RequestMapping("/jobMgr")
public class JobManagerController extends BaseController {
	private static final Logger logger = Logger.getLogger(JobManagerController.class);

	@Autowired
	JobManageService jobManageService;

	/**
	 * 跳转到界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	private String main() {
		return redirectJsp("job", "JobManage");
	}

	/**
	 * 获取列表数据
	 * 
	 * @param criteria
	 * @param ph
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	private DataGrid dataGrid(JobManageCriteria criteria, PageHelper ph) {
		if (null == criteria) {
			criteria = new JobManageCriteria();
		}
		DataGrid data = new DataGrid();
		try {
			data = jobManageService.getList(criteria, ph);
			@SuppressWarnings("unchecked")
			List<SysJobInfo> jobInfos = data.getRows();
			for (SysJobInfo sysJobInfo : jobInfos) {
				sysJobInfo.setSjiNameV(Constants.getConstantName("JOB_TYPE", sysJobInfo.getSjiCodeV()));
			}
		} catch (Exception e) {
			logger.error("Job管理列表查询失败：" + e.getMessage(), e);
		}
		return data;
	}

	/**
	 * 修改job状态
	 * 
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public Json change(@RequestParam String jobId, String status) {
		Json j = new Json();
		try {
			jobManageService.changeStatus(jobId, status);
			j.setSuccess(true);
			j.setMsg("Job状态更新成功！");
		} catch (Exception e) {
			logger.error("Job状态更新失败：" + e.getMessage(), e);
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 立即执行
	 * 
	 * @return
	 */
	@RequestMapping("/exec")
	@ResponseBody
	public Json exec(@RequestParam String jobId) {
		Json j = new Json();
		try {
			SysJobInfo job = jobManageService.getJobInfo(jobId);

			Object obj = SpringContextHolder.getBean(job.getSjiSpringIdV());
			Method method = obj.getClass().getMethod(job.getSjiMethodNameV(), null);
			method.invoke(obj, null);

			j.setSuccess(true);
			j.setMsg("Job执行成功！");
		} catch (Exception e) {
			logger.error("Job执行失败：" + e.getMessage(), e);
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到日志查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/logsPage")
	public String logsPage(@RequestParam String jobType, HttpServletRequest request) {
		request.setAttribute("jobType", jobType);
		return redirectJsp("job", "JobLogs");
	}

	/**
	 * 获取日志列表数据
	 * 
	 * @param criteria
	 * @param ph
	 * @return
	 */
	@RequestMapping("/listLogs")
	@ResponseBody
	private DataGrid dataGrid1(JobLogsCriteria criteria, PageHelper ph) {
		if (null == criteria) {
			criteria = new JobLogsCriteria();
		}
		DataGrid data = new DataGrid();
		try {
			data = jobManageService.getList(criteria, ph);
			@SuppressWarnings("unchecked")
			List<SysJobManage> jobLogs = data.getRows();
			for (SysJobManage jobLog : jobLogs) {
				jobLog.setSjmJobNameV(Constants.getConstantName("JOB_TYPE", jobLog.getSjmJobTypeV()));
			}
		} catch (Exception e) {
			logger.error("Job日志列表查询失败：" + e.getMessage(), e);
		}
		return data;
	}

	/**
	 * 跳转到修改Job信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String updatePage(@RequestParam String jobId, HttpServletRequest request) {
		SysJobInfo jobInfo = jobManageService.getJobInfo(jobId);
		jobInfo.setSjiNameV(Constants.getConstantName("JOB_TYPE", jobInfo.getSjiCodeV()));
		request.setAttribute("jobInfo", jobInfo);
		return redirectJsp("job", "JobEdit");
	}

	/**
	 * 修改Job
	 * 
	 * @param jobInfo
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(SysJobInfo jobInfo) {
		Json j = new Json();
		try {
			jobManageService.edit(jobInfo);
			j.setSuccess(true);
			j.setMsg("Job编辑成功！");
		} catch (Exception e) {
			logger.error("Job编辑失败：" + e.getMessage(), e);
		}
		return j;
	}
}
