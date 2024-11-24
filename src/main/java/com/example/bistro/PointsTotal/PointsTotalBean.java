package com.example.bistro.PointsTotal;

import com.example.bistro.members.Members;
import com.example.bistro.pointPrizes.PointPrizesBean;
import com.example.bistro.pointsGet.PointsGetBean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PointsTotal")
public class PointsTotalBean {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Members members;
    
    @ManyToOne
    @JoinColumn(name = "pointPrizesId")
    private PointPrizesBean PointPrizes;
    
    @ManyToOne
    @JoinColumn(name = "pointGetId")
    private PointsGetBean PointGet;
    
    private Integer PointsTotal;

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

	public PointsGetBean getPointGet() {
		return PointGet;
	}

	public void setPointGet(PointsGetBean pointGet) {
		PointGet = pointGet;
	}

	public Integer getPointsTotal() {
		return PointsTotal;
	}

	public void setPointsTotal(Integer pointsTotal) {
		PointsTotal = pointsTotal;
	}
    
    
}
