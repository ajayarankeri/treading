package com.hcl.treading.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void purchaseStock(@RequestBody StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException {
		
		treadingService.purchaseStock(stockPurchaseDto);
		
	}

}
