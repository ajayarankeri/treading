package com.hcl.treading.service;

import org.springframework.stereotype.Component;

import com.hcl.treading.dto.StockPurchaseDto;

@Component
public interface TreadingService {

	void purchaseStock(StockPurchaseDto stockPurchaseDto);

}
