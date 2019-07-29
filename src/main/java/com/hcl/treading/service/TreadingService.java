package com.hcl.treading.service;

import org.springframework.stereotype.Component;

import com.hcl.treading.dto.ConfirmPurchaseDto;
import com.hcl.treading.dto.ResponseDto;
import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.exception.NoOrderFoundException;
import com.hcl.treading.exception.ResourceNotFoundException;

@Component
public interface TreadingService {
	
	public ResponseDto purchaseStock(StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException;

	public ResponseDto confirmPurchaseStock(ConfirmPurchaseDto confirmPurchaseDto) throws ResourceNotFoundException;

	public ResponseDto orderHistory(Long customerId) throws ResourceNotFoundException, NoOrderFoundException;

}
