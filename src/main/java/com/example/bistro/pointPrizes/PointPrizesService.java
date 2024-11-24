package com.example.bistro.pointPrizes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointPrizesService {

    @Autowired
    private PointPrizesRepository PPRepo;

    public List<PointPrizesBean> findAllPointPrizes() {
        return PPRepo.findAll();
    }

    public PointPrizesBean createPointPrizes(PointPrizesBean pointPrizes) {
        return PPRepo.save(pointPrizes);
    }

    public PointPrizesBean updatePointPrizes(PointPrizesBean pointPrizes) {
        return PPRepo.save(pointPrizes);
    }

    public void deletePointPrizes(PointPrizesBean pointPrizes) {
    	PPRepo.delete(pointPrizes);
    }
    
	public void deletePointPrizesById(Integer id) {
		PPRepo.deleteById(id);
	}
	
	public PointPrizesBean findById(int id) {
	    return PPRepo.findById(id).orElse(null);
	}
}
