package com.example.bistro.campaign;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity 
@Table(name = "Campaign")
public class Campaign {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "campaignTitle")
	private String campaignTitle;
	
	@Column(name = "campaignDescription")
	private String campaignDescription;
	
	@Column(name = "campaignType")
	private String campaignType;
	
	@Column(name = "minOrderAmount")
	private Integer minOrderAmount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startDate")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endDate")
	private Date endDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;
	
	@Column(name = "note")
	private String note;
	
	@PrePersist 
	public void onCreate() {
		if(createdAt == null) {
			createdAt = new Date();
		}		
	}

	public Campaign() {
	}

	public Integer getMinOrderAmount() {
		return minOrderAmount;
	}

	public void setMinOrderAmount(Integer minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCampaignTitle() {
		return campaignTitle;
	}

	public void setCampaignTitle(String campaignTitle) {
		this.campaignTitle = campaignTitle;
	}

	public String getCampaignDescription() {
		return campaignDescription;
	}

	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
	

}
