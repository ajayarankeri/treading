package com.hcl.treading.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.repository.TreadingRepository;
import com.hcl.treading.service.TreadingService;

public class TreadingServiceImpl implements TreadingService{
	
	@Autowired
	TreadingRepository treadingRepository;

	@Override
	public void purchaseStock(StockPurchaseDto stockPurchaseDto) {
		
	}

}
