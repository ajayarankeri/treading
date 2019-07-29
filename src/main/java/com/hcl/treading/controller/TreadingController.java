package com.hcl.treading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.exception.ResourceNotFoundException;
import com.hcl.treading.service.TreadingService;

@RestController
@RequestMapping("")
public class TreadingController {
	
	@Autowired
	TreadingService treadingService;
	
	@PostMapping("/purchase")
	public ResponseEntity<Object> purchaseStock(@RequestBody StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException {
				
		return new ResponseEntity<>(treadingService.purchaseStock(stockPurchaseDto),HttpStatus.OK);
		
	}
	
	@PostMapping("/confirm")
	public ResponseEntity<Object> confirmPurchaseStock(@RequestBody StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException {
				
		return new ResponseEntity<>(treadingService.purchaseStock(stockPurchaseDto),HttpStatus.OK);
		
	}
	

}
