package com.abeam.fieldglass.criteria.user;

import com.abeam.system.pageModel.PageHelper;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		admin
 * Version:		1.0  
 * Create at:	2018年1月15日 上午10:09:30  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class PlateCriteria extends PageHelper {
	private String mpPlateId;
	private String mpPlateName;
	private String mpSerialNo;
	private String mpStatus;
	private String flag;
	private String types;

	public String getMpPlateId() {
		return mpPlateId;
	}

	public void setMpPlateId(String mpPlateId) {
		this.mpPlateId = mpPlateId;
	}

	public String getMpPlateName() {
		return mpPlateName;
	}

	public void setMpPlateName(String mpPlateName) {
		this.mpPlateName = mpPlateName;
	}

	public String getMpSerialNo() {
		return mpSerialNo;
	}

	public void setMpSerialNo(String mpSerialNo) {
		this.mpSerialNo = mpSerialNo;
	}

	public String getMpStatus() {
		return mpStatus;
	}

	public void setMpStatus(String mpStatus) {
		this.mpStatus = mpStatus;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
