package com.example.bistro.orderDetails;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Seats")
public class Seats {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID;

private String seatType;



@OneToMany(mappedBy = "seats", cascade = CascadeType.ALL)
private List <Orders> orders;



public Integer getID() {
	return ID;
}

public void setID(Integer iD) {
	ID = iD;
}

public String getSeatType() {
	return seatType;
}

public void setSeatType(String seatType) {
	this.seatType = seatType;
}


}
