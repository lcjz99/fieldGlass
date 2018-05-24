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
public class InforManageCriteria extends PageHelper implements Serializable{
	private String aiId;
	private String aiTitle;
	private MultipartFile aiTitlePic;
	private String aiSource;
	private String aiStockRelated;
	private String aiStockRelatedCode;
	private String aiPlateRelatedId;
	private String aiPlateRelatedName;
	private String aiArticle;
	private String aiStatus;
	private String aiCreator;
	
	private String picSize;
	
	public String getPicSize() {
		return picSize;
	}
	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}
	public MultipartFile getAiTitlePic() {
		return aiTitlePic;
	}
	public void setAiTitlePic(MultipartFile aiTitlePic) {
		this.aiTitlePic = aiTitlePic;
	}
	public String getAiSource() {
		return aiSource;
	}
	public void setAiSource(String aiSource) {
		this.aiSource = aiSource;
	}
	public String getAiStockRelated() {
		return aiStockRelated;
	}
	public void setAiStockRelated(String aiStockRelated) {
		this.aiStockRelated = aiStockRelated;
	}
	public String getAiStockRelatedCode() {
		return aiStockRelatedCode;
	}
	public void setAiStockRelatedCode(String aiStockRelatedCode) {
		this.aiStockRelatedCode = aiStockRelatedCode;
	}
	public String getAiPlateRelatedId() {
		return aiPlateRelatedId;
	}
	public void setAiPlateRelatedId(String aiPlateRelatedId) {
		this.aiPlateRelatedId = aiPlateRelatedId;
	}
	public String getAiPlateRelatedName() {
		return aiPlateRelatedName;
	}
	public void setAiPlateRelatedName(String aiPlateRelatedName) {
		this.aiPlateRelatedName = aiPlateRelatedName;
	}
	public String getAiArticle() {
		return aiArticle;
	}
	public void setAiArticle(String aiArticle) {
		this.aiArticle = aiArticle;
	}
	public String getAiStatus() {
		return aiStatus;
	}
	public void setAiStatus(String aiStatus) {
		this.aiStatus = aiStatus;
	}
	public String getAiCreator() {
		return aiCreator;
	}
	public void setAiCreator(String aiCreator) {
		this.aiCreator = aiCreator;
	}
	public String getAiId() {
		return aiId;
	}
	public void setAiId(String aiId) {
		this.aiId = aiId;
	}
	public String getAiTitle() {
		return aiTitle;
	}
	public void setAiTitle(String aiTitle) {
		this.aiTitle = aiTitle;
	}

}
