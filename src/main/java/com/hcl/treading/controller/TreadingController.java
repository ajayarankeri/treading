package com.hcl.treading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.treading.dto.ConfirmPurchaseDto;
import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.exception.NoOrderFoundException;
import com.hcl.treading.exception.NoStockAvailableException;
import com.hcl.treading.exception.ResourceNotFoundException;
import com.hcl.treading.service.TreadingService;

@RestController
@RequestMapping("")
public class TreadingController {
	
	@Autowired
	TreadingService treadingService;
	
	@GetMapping("/stocklist")
	public ResponseEntity<Object> getStockList()throws NoStockAvailableException{
		return new ResponseEntity<>(treadingService.getStockList(),HttpStatus.OK);
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<Object> purchaseStock(@RequestBody StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException {
				
		return new ResponseEntity<>(treadingService.purchaseStock(stockPurchaseDto),HttpStatus.OK);
		
	}
	
	@PostMapping("/confirm")
	public ResponseEntity<Object> confirmPurchaseStock(@RequestBody ConfirmPurchaseDto confirmPurchaseDto) throws ResourceNotFoundException {
				
		return new ResponseEntity<>(treadingService.confirmPurchaseStock(confirmPurchaseDto),HttpStatus.OK);
		
	}
	
	@PostMapping("/history")
	public ResponseEntity<Object> orderHistory(@RequestParam Long customerId ) throws ResourceNotFoundException, NoOrderFoundException {
		return new ResponseEntity<>(treadingService.orderHistory(customerId),HttpStatus.OK);
		
	}
	

}
