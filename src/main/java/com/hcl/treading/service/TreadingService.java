package com.hcl.treading.service;

import org.springframework.stereotype.Component;

import com.hcl.treading.dto.ResponseDto;
import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.exception.ResourceNotFoundException;

@Component
public interface TreadingService {
	
	ResponseDto purchaseStock(StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException;

}
