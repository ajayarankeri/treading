package com.hcl.treading.dto;

import lombok.Data;

@Data
public class StockPurchaseDto {
		
	private Long customerId;
	
	private Long stockId;
	
	private int quantity;
	

}
