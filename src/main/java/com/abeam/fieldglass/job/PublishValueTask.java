/**
 * 
 */
package com.abeam.fieldglass.job;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.abeam.fieldglass.constants.Constants;
import com.abeam.fieldglass.service.job.JobManageService;

/**
 * ßß
 * 
 * @Scheduled(fixedRate = 60 * 2 * 1000)
 */
@Lazy(false)
@Component
public class PublishValueTask {
	private static final Logger logger = LoggerFactory.getLogger(PublishValueTask.class);

	@Autowired
	private JobManageService jobManageService;

	public void complatePublishValue() {
		logger.info("补充发布日收盘价定时任务开始执行");
		long start = System.currentTimeMillis();
		boolean isSuccess = true;
		Date runDate = new Date();
		try {
			Date yestoday = getYestoday();
			// 查询预测日为昨日的股票的昨日收盘价
			System.out.println("开始执行job:" + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage(), e);
			isSuccess = false;
		}
		// 创建
		jobManageService.save(Constants.JOB_TYPE.JOB_TYPE_1001, runDate, start, System.currentTimeMillis(), isSuccess);
		logger.info("补充发布日收盘价定时任务执行结束");
	}

	private Date getYestoday() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime() - 1 * 24 * 3600 * 1000);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

}
