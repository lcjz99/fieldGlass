package com.abeam.fieldglass.criteria.prize;

import com.abeam.system.pageModel.PageHelper;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		hujiahui
 * Version:		1.0  
 * Create at:	2018年1月18日 下午3:44:03  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
public class PrizeLogCriteria extends PageHelper {
	private String mplId;
	private String mplStockCode;
	private String mplStockName;
	private String expectDateBegin;
	private String expectDateEnd;
	private String prizeDateBegin;
	private String prizeDateEnd;
	private String mplStatus;
	
	private String mplExpectDate;
	private String mplSettlementDate;
	private String mplPrizeValue;
	private String mplMarketValue;
	
	public String getMplMarketValue() {
		return mplMarketValue;
	}
	public void setMplMarketValue(String mplMarketValue) {
		this.mplMarketValue = mplMarketValue;
	}
	public String getMplPrizeValue() {
		return mplPrizeValue;
	}
	public void setMplPrizeValue(String mplPrizeValue) {
		this.mplPrizeValue = mplPrizeValue;
	}
	public String getMplExpectDate() {
		return mplExpectDate;
	}
	public void setMplExpectDate(String mplExpectDate) {
		this.mplExpectDate = mplExpectDate;
	}
	public String getMplSettlementDate() {
		return mplSettlementDate;
	}
	public void setMplSettlementDate(String mplSettlementDate) {
		this.mplSettlementDate = mplSettlementDate;
	}
	public String getMplId() {
		return mplId;
	}
	public void setMplId(String mplId) {
		this.mplId = mplId;
	}
	public String getMplStockCode() {
		return mplStockCode;
	}
	public void setMplStockCode(String mplStockCode) {
		this.mplStockCode = mplStockCode;
	}
	public String getMplStockName() {
		return mplStockName;
	}
	public void setMplStockName(String mplStockName) {
		this.mplStockName = mplStockName;
	}
	public String getExpectDateBegin() {
		return expectDateBegin;
	}
	public void setExpectDateBegin(String expectDateBegin) {
		this.expectDateBegin = expectDateBegin;
	}
	public String getExpectDateEnd() {
		return expectDateEnd;
	}
	public void setExpectDateEnd(String expectDateEnd) {
		this.expectDateEnd = expectDateEnd;
	}
	public String getPrizeDateBegin() {
		return prizeDateBegin;
	}
	public void setPrizeDateBegin(String prizeDateBegin) {
		this.prizeDateBegin = prizeDateBegin;
	}
	public String getPrizeDateEnd() {
		return prizeDateEnd;
	}
	public void setPrizeDateEnd(String prizeDateEnd) {
		this.prizeDateEnd = prizeDateEnd;
	}
	public String getMplStatus() {
		return mplStatus;
	}
	public void setMplStatus(String mplStatus) {
		this.mplStatus = mplStatus;
	}
}
