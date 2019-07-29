package com.hcl.treading.service;

import org.springframework.stereotype.Service;

import com.hcl.treading.dto.StockPurchaseDto;

@Service
public interface TreadingService {

	void purchaseStock(StockPurchaseDto stockPurchaseDto);

}
