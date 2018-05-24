package com.abeam.fieldglass.criteria.pk;

import com.abeam.system.pageModel.PageHelper;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		admin
 * Version:		1.0  
 * Create at:	2018年1月16日 下午1:36:52  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class PkCriteria extends PageHelper {
	private String userId;
	private String forecastId;
	private String stockCode;
	private String stockName;
	private String startDate;
	private String endDate;
	private String status;
	private String friendId;
	private String friendFocaId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getForecastId() {
		return forecastId;
	}

	public void setForecastId(String forecastId) {
		this.forecastId = forecastId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getFriendFocaId() {
		return friendFocaId;
	}

	public void setFriendFocaId(String friendFocaId) {
		this.friendFocaId = friendFocaId;
	}

}
