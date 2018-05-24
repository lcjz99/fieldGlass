package com.abeam.fieldglass.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * SysJobManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "sys_job_manage")
public class SysJobManage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1731985058843690270L;
	private Integer sjmIdN;
	private Date sjmDateD;
	private String sjmJobTypeV;
	private String sjmJobNameV;
	private Short sjmSuccessN;
	private Timestamp sjmStartTimeD;
	private Timestamp sjmEndTimeD;
	private Timestamp inserttime;
	private Timestamp updatetime;
	private Short isactive;

	// Constructors

	/** default constructor */
	public SysJobManage() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SJM_ID_N", unique = true, nullable = false)
	public Integer getSjmIdN() {
		return this.sjmIdN;
	}

	public void setSjmIdN(Integer sjmIdN) {
		this.sjmIdN = sjmIdN;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SJM_DATE_D", nullable = false, length = 10)
	public Date getSjmDateD() {
		return this.sjmDateD;
	}

	public void setSjmDateD(Date sjmDateD) {
		this.sjmDateD = sjmDateD;
	}

	@Column(name = "SJM_JOB_TYPE_V", nullable = false, length = 20)
	public String getSjmJobTypeV() {
		return this.sjmJobTypeV;
	}

	public void setSjmJobTypeV(String sjmJobTypeV) {
		this.sjmJobTypeV = sjmJobTypeV;
	}

	@Transient
	public String getSjmJobNameV() {
		return sjmJobNameV;
	}

	public void setSjmJobNameV(String sjmJobNameV) {
		this.sjmJobNameV = sjmJobNameV;
	}

	@Column(name = "SJM_SUCCESS_N", nullable = false)
	public Short getSjmSuccessN() {
		return this.sjmSuccessN;
	}

	public void setSjmSuccessN(Short sjmSuccessN) {
		this.sjmSuccessN = sjmSuccessN;
	}

	@Column(name = "SJM_START_TIME_D", nullable = false, length = 19)
	public Timestamp getSjmStartTimeD() {
		return this.sjmStartTimeD;
	}

	public void setSjmStartTimeD(Timestamp sjmStartTimeD) {
		this.sjmStartTimeD = sjmStartTimeD;
	}

	@Column(name = "SJM_END_TIME_D", nullable = false, length = 19)
	public Timestamp getSjmEndTimeD() {
		return this.sjmEndTimeD;
	}

	public void setSjmEndTimeD(Timestamp sjmEndTimeD) {
		this.sjmEndTimeD = sjmEndTimeD;
	}

	@Column(name = "inserttime", length = 19)
	public Timestamp getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Timestamp inserttime) {
		this.inserttime = inserttime;
	}

	@Column(name = "updatetime", length = 19)
	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "isactive")
	public Short getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Short isactive) {
		this.isactive = isactive;
	}

}