package com.hcl.treading.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockSchedular {

	@Scheduled(fixedRate = 10000)
	public void fixedRateSch() { 
		
	}

	
}
