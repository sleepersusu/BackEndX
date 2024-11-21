package com.example.bistro.orderDetails;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Members")
public class Members {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	private String memberAccount;
	private String memberPassword;
	private String memberName;
	private Integer memberAge;
	private Integer memberSex;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端輸入輸出時的格式對應，若須強制轉換格式，el 須使用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberBirthday;
	private Integer memberFavor;
	private String memberAddress;
	private String memberPhone;
	private String memberEmail;
	private byte[] memberImg;
	private Integer memberPoint;
	private String memberStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端輸入輸出時的格式對應，若須強制轉換格式，el 須使用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	
	
	
	
	
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	private List <Orders> orders;
	
	
	

	@PrePersist // 當物件要轉換成 Persistent 狀態以前，執行這個方法
	public void onCreate() {
		if (createdAt == null||memberBirthday == null) {
			createdAt = new Date();
			memberBirthday = new Date();
		}
	}


	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(Integer memberAge) {
		this.memberAge = memberAge;
	}

	public Integer getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(Integer memberSex) {
		this.memberSex = memberSex;
	}

	public Date getMemberBirthday() {
		return memberBirthday;
	}

	public void setMemberBirthday(Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}

	public Integer getMemberFavor() {
		return memberFavor;
	}

	public void setMemberFavor(Integer memberFavor) {
		this.memberFavor = memberFavor;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public byte[] getMemberImg() {
		return memberImg;
	}

	public void setMemberImg(byte[] memberImg) {
		this.memberImg = memberImg;
	}

	public Integer getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(Integer memberPoint) {
		this.memberPoint = memberPoint;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
