package com.abeam.fieldglass.service.job.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.abeam.fieldglass.criteria.job.JobLogsCriteria;
import com.abeam.fieldglass.criteria.job.JobManageCriteria;
import com.abeam.fieldglass.dao.job.JobInfoDao;
import com.abeam.fieldglass.dao.job.JobManageDao;
import com.abeam.fieldglass.entity.SysJobInfo;
import com.abeam.fieldglass.entity.SysJobManage;
import com.abeam.fieldglass.job.QuartzJobFactory;
import com.abeam.fieldglass.service.job.JobManageService;
import com.abeam.system.pageModel.DataGrid;
import com.abeam.system.pageModel.PageHelper;
import com.abeam.system.util.DateUtils;

@Service
public class JobManageServiceImpl implements JobManageService {

	public final Logger log = LoggerFactory.getLogger(getClass());

	public final static int STOP = 0;

	public final static int START = 1;

	@Autowired
	private JobManageDao jobManageDao;

	@Autowired
	private JobInfoDao jobInfoDao;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public DataGrid getList(JobManageCriteria criteria, PageHelper ph) {
		return jobInfoDao.getList(criteria, ph);
	}

	@Override
	public DataGrid getList(JobLogsCriteria criteria, PageHelper ph) {
		return jobManageDao.getList(criteria, ph);
	}

	@Override
	public void changeStatus(String jobId, String status) throws SchedulerException {
		SysJobInfo job = jobInfoDao.getJobInfo(jobId);
		if (job == null) {
			return;
		}

		switch (Integer.parseInt(status)) {
		case START:
			addJob(job);
			job.setSjiStatusN(START);
			break;
		case STOP:
			deleteJob(job);
			job.setSjiStatusN(STOP);
			break;

		default:
			break;
		}
		jobInfoDao.update(job);

	}

	@Override
	public void save(String jobType, Date runDate, long start, long end, boolean isSuccess) {
		try {
			runDate = DateUtils.getStartDate(runDate);
			SysJobManage sjm = new SysJobManage();

			sjm.setSjmDateD(runDate);
			sjm.setSjmJobTypeV(jobType);
			sjm.setSjmStartTimeD(new Timestamp(start));

			sjm.setSjmEndTimeD(new Timestamp(end));
			sjm.setSjmSuccessN((short) (isSuccess ? 1 : 0));

			// 创建
			jobManageDao.create(sjm);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	@Override
	public SysJobInfo getJobInfo(String jobId) {
		return jobInfoDao.getJobInfo(jobId);
	}

	@Override
	public void edit(SysJobInfo jobInfo) {
		SysJobInfo jobInfoTmp = jobInfoDao.getJobInfo(jobInfo.getSjiIdN());
		jobInfoTmp.setSjiCronV(jobInfo.getSjiCronV());
		jobInfoDao.edit(jobInfoTmp);

	}

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addJob(SysJobInfo job) throws SchedulerException {
		if (job == null) {
			return;
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		log.debug(scheduler
				+ ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getSjiCodeV());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = QuartzJobFactory.class;

			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getSjiCodeV()).build();

			jobDetail.getJobDataMap().put("SysJobInfo", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getSjiCronV());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getSjiCodeV()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getSjiCronV());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
		log.info("job[{}] add>>>>>>>>>> ", job.getSjiCodeV());

	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(SysJobInfo job) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getSjiCodeV());
		scheduler.deleteJob(jobKey);
		log.info("job[{}] stop>>>>>>>>>> ", job.getSjiCodeV());
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(SysJobInfo job) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(job.getSjiCodeV());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getSjiCronV());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}

	@PostConstruct
	public void init() throws Exception {
		Session session = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));

		// 这里获取任务信息数据
		List<SysJobInfo> jobList = jobInfoDao.queryAll();

		for (SysJobInfo job : jobList) {
			if (START == job.getSjiStatusN()) {
				addJob(job);
			}
		}

		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.closeSession(sessionHolder.getSession());
	}

}
