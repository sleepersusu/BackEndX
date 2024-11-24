package com.example.bistro.PointsRecords;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.bistro.members.Members;
import com.example.bistro.pointPrizes.PointPrizesBean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PointsRecords")
public class PointsRecordsBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Members members;
    
    @ManyToOne
    @JoinColumn(name = "pointPrizesId")
    private PointPrizesBean PointPrizes;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date recordsDate;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	public PointPrizesBean getPointPrizes() {
		return PointPrizes;
	}

	public void setPointPrizes(PointPrizesBean pointPrizes) {
		PointPrizes = pointPrizes;
	}

	public Date getRecordsDate() {
		return recordsDate;
	}

	public void setRecordsDate(Date recordsDate) {
		this.recordsDate = recordsDate;
	}


    
}
