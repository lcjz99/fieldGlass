package com.abeam.fieldglass.criteria.forecast;

import com.abeam.system.pageModel.PageHelper;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		admin
 * Version:		1.0  
 * Create at:	2018年1月17日 上午10:37:25  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class ForecastCriteria extends PageHelper {
	private String userId;
	private String userName;
	private String stockCode;
	private String stockName;
	private String focaId;
	private String period;
	private String focastType;
	private String mflBalanceType;
	private String focaStartDate;
	private String focaEndDate;
	private String startDate;
	private String endDate;
	private String preSDate;
	private String preEDate;
	private Boolean isComplated;

	public String getMflBalanceType() {
		return mflBalanceType;
	}

	public void setMflBalanceType(String mflBalanceType) {
		this.mflBalanceType = mflBalanceType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getFocaId() {
		return focaId;
	}

	public void setFocaId(String focaId) {
		this.focaId = focaId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFocastType() {
		return focastType;
	}

	public void setFocastType(String focastType) {
		this.focastType = focastType;
	}

	public String getFocaStartDate() {
		return focaStartDate;
	}

	public void setFocaStartDate(String focaStartDate) {
		this.focaStartDate = focaStartDate;
	}

	public String getFocaEndDate() {
		return focaEndDate;
	}

	public void setFocaEndDate(String focaEndDate) {
		this.focaEndDate = focaEndDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPreSDate() {
		return preSDate;
	}

	public void setPreSDate(String preSDate) {
		this.preSDate = preSDate;
	}

	public String getPreEDate() {
		return preEDate;
	}

	public void setPreEDate(String preEDate) {
		this.preEDate = preEDate;
	}

	public Boolean isComplated() {
		return isComplated;
	}

	public void setComplated(Boolean isComplated) {
		this.isComplated = isComplated;
	}
}
