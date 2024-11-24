package com.example.bistro.pointPrizes;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "PointPrizes")
public class PointPrizesBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	private String pointPrizesName;

	private int pointPrizesPoints;

	private String pointPrizesDescription;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date pointPrizesExpiration;
	@Transient
	private String rewardsStatus;

	private int pointPrizesCount;	
    @Lob
	private byte[] pointPrizesImg;

      
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPointPrizesName() {
		return pointPrizesName;
	}

	public void setPointPrizesName(String pointPrizesName) {
		this.pointPrizesName = pointPrizesName;
	}

	public int getPointPrizesPoints() {
		return pointPrizesPoints;
	}

	public void setPointPrizesPoints(int pointPrizesPoints) {
		this.pointPrizesPoints = pointPrizesPoints;
	}

	public String getPointPrizesDescription() {
		return pointPrizesDescription;
	}

	public void setPointPrizesDescription(String pointPrizesDescription) {
		this.pointPrizesDescription = pointPrizesDescription;
	}

	public Date getPointPrizesExpiration() {
		return pointPrizesExpiration;
	}

	public void setPointPrizesExpiration(Date pointPrizesExpiration) {
		this.pointPrizesExpiration = pointPrizesExpiration;
	}

	public int getPointPrizesCount() {
		return pointPrizesCount;
	}

	public void setPointPrizesCount(int pointPrizesCount) {
		this.pointPrizesCount = pointPrizesCount;
	}

	public byte[] getPointPrizesImg() {
		return pointPrizesImg;
	}

	public void setPointPrizesImg(byte[] pointPrizesImg) {
		this.pointPrizesImg = pointPrizesImg;
	}

	public String getRewardsStatus() {
		// 判斷有效日期是否已過期
		Date currentDate = new Date();
		if (pointPrizesExpiration != null && pointPrizesExpiration.before(currentDate)) {
			return "已過期";
		} else {
			return "上架中";
		}
	}

	@Override
	public String toString() {
		return "PointPrizesBean [id=" + id + ", pointPrizesName=" + pointPrizesName
				+ ", pointPrizesPoints=" + pointPrizesPoints + ", pointPrizesDescription=" + pointPrizesDescription
				+ ", pointPrizesExpiration=" + pointPrizesExpiration + ", rewardsStatus=" + rewardsStatus
				+ ", pointPrizesCount=" + pointPrizesCount + ", pointPrizesImg=" + Arrays.toString(pointPrizesImg)
				+ "]";
	}
	
    public String getBase64Image() {
        if (pointPrizesImg != null && pointPrizesImg.length > 0) {
            return Base64.getEncoder().encodeToString(pointPrizesImg);
        }
        return null;
    }

}
