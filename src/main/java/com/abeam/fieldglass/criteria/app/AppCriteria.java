package com.abeam.fieldglass.criteria.app;

import com.abeam.system.pageModel.PageHelper;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		admin
 * Version:		1.0  
 * Create at:	2018年3月5日 上午11:07:37  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class AppCriteria extends PageHelper {
	private String terminalType;

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

}
