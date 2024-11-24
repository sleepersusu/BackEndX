package com.example.bistro.reservations;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ReservationsService {
	
	@Autowired
	private ReservationsRepository ReservationsRepo;
	
	
	
	
	public Reservations createReservations(String customerName,
			String customerGender,String contactPhone,
			Date reservationDate,Timestamp startTime,
			Integer numberPeople,String notes,String reservationStatus) {
			Reservations reservations = new Reservations();
			reservations.setCustomerName(customerName);
			reservations.setCustomerGender(customerGender);
			reservations.setContactPhone(contactPhone);			
			reservations.setReservationDate(reservationDate);		
			reservations.setStartTime(startTime);
			reservations.setNumberPeople(numberPeople);
			reservations.setNotes(notes);
			if(reservationStatus==null) {
				
				reservations.setReservationStatus("已確認");
			}
			
			
		
		return ReservationsRepo.save(reservations);

	}

	
	public Reservations findReservationsById(Integer id) {
		Optional<Reservations> op = ReservationsRepo.findById(id);
		if(op.isPresent()) {
			
			return op.get();		
		}

		return null;
	}



	public void deleteReservations(Integer id) {
		ReservationsRepo.deleteById(id);
	}
	@Transactional
	public Reservations updateReservations(Integer id,String customerName,
			String customerGender,String contactPhone,
			Date reservationDate,Timestamp startTime,
			Integer numberPeople,String notes,String reservationStatus) {
		
		
		Optional<Reservations> op=ReservationsRepo.findById(id);
		
		if(op.isPresent()) {
			Reservations reservations=op.get();
			reservations.setCustomerName(customerName);
			reservations.setCustomerGender(customerGender);
			reservations.setContactPhone(contactPhone);			
			reservations.setReservationDate(reservationDate);		
			reservations.setStartTime(startTime);
			reservations.setNumberPeople(numberPeople);
			reservations.setNotes(notes);
			reservations.setReservationStatus(reservationStatus);
			 return ReservationsRepo.save(reservations);
 		}
	     return null;
	}

	public List<Reservations> findAllReservations() {
		return ReservationsRepo.findAll();
	}
	
	
	
	
	
}
