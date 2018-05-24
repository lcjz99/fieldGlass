package com.abeam.fieldglass.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.abeam.system.pageModel.PageHelper;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		拍财富
 * Author:		admin
 * Version:		1.0  
 * Create at:	2018年3月5日 下午6:21:02  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class BannerDto extends PageHelper implements Serializable {
	private String title;
	private String sortNum;
	private String types;
	private String startDate;
	private String endDate;
	private String link;
	private String pic;
	private MultipartFile appPic;
	private String creater;
	private String picSize;
	private String id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public MultipartFile getAppPic() {
		return appPic;
	}

	public void setAppPic(MultipartFile appPic) {
		this.appPic = appPic;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getPicSize() {
		return picSize;
	}

	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
