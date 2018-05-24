package com.abeam.fieldglass.criteria.plate;

import com.abeam.system.pageModel.PageHelper;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		hujiahui
 * Version:		1.0  
 * Create at:	2018年1月18日 下午3:44:15  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
public class StockManageCriteria extends PageHelper {
	private String mpsId;
	private String mpsPlateId;
	private String mpsPlateName;
	private String mpsStockCode;
	private String mpsStockName;
	private String mpsStockSerial;
	private String mpsStatus;
	private String types;

	public String getMpsId() {
		return mpsId;
	}

	public void setMpsId(String mpsId) {
		this.mpsId = mpsId;
	}

	public String getMpsPlateId() {
		return mpsPlateId;
	}

	public void setMpsPlateId(String mpsPlateId) {
		this.mpsPlateId = mpsPlateId;
	}

	public String getMpsStockSerial() {
		return mpsStockSerial;
	}

	public void setMpsStockSerial(String mpsStockSerial) {
		this.mpsStockSerial = mpsStockSerial;
	}

	public String getMpsPlateName() {
		return mpsPlateName;
	}

	public void setMpsPlateName(String mpsPlateName) {
		this.mpsPlateName = mpsPlateName;
	}

	public String getMpsStockCode() {
		return mpsStockCode;
	}

	public void setMpsStockCode(String mpsStockCode) {
		this.mpsStockCode = mpsStockCode;
	}

	public String getMpsStockName() {
		return mpsStockName;
	}

	public void setMpsStockName(String mpsStockName) {
		this.mpsStockName = mpsStockName;
	}

	public String getMpsStatus() {
		return mpsStatus;
	}

	public void setMpsStatus(String mpsStatus) {
		this.mpsStatus = mpsStatus;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
