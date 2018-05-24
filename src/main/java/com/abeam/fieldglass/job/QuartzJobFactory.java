package com.abeam.fieldglass.job;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abeam.fieldglass.entity.SysJobInfo;
import com.abeam.fieldglass.service.common.SpringContextHolder;

/**
 * 
 */
// @DisallowConcurrentExecution
public class QuartzJobFactory implements Job {
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SysJobInfo sysJobInfo = (SysJobInfo) context.getMergedJobDataMap().get("SysJobInfo");
		Object object = null;
		@SuppressWarnings("rawtypes")
		Class clazz = null;
		if (StringUtils.isBlank(sysJobInfo.getSjiSpringIdV())) {
			throw new RuntimeException("");
		}

		object = SpringContextHolder.getBean(sysJobInfo.getSjiSpringIdV());
		if (object == null) {
			log.error("任务名称 = [" + sysJobInfo.getSjiCodeV() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(sysJobInfo.getSjiMethodNameV());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + sysJobInfo.getSjiMethodNameV() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			log.error(e.getMessage(), e);
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}