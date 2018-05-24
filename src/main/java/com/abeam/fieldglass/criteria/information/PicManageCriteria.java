package com.abeam.fieldglass.criteria.information;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.abeam.system.pageModel.PageHelper;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		hujiahui
 * Version:		1.0  
 * Create at:	2018年3月5日 下午1:47:32  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
public class PicManageCriteria extends PageHelper implements Serializable {

	private static final long serialVersionUID = 1242932407757368337L;

	private String picName;

	private MultipartFile picFile;

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public MultipartFile getPicFile() {
		return picFile;
	}

	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
	}

}
