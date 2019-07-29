package com.hcl.treading.schedular;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hcl.treading.entity.Stock;
import com.hcl.treading.entity.StockTransaction;
import com.hcl.treading.repository.StockRepository;
import com.hcl.treading.repository.TreadingRepository;

@Component
public class StockSchedular {
	
	@Autowired
	TreadingRepository treadingRepository;
	
	@Autowired
	StockRepository stockRepository;

	@Scheduled(fixedRate = 10000)
	public void fixedRateSch() { 
		
		HashMap<Long, Float> stockValue=new HashMap<Long,Float>();
		stockValue.put(Long.valueOf(1), Float.valueOf(100));
		stockValue.put(Long.valueOf(2), Float.valueOf(200));
		stockValue.put(Long.valueOf(3), Float.valueOf(50));
		List<Stock> stockList=stockRepository.findAll();
		
		Iterator itr=stockList.iterator();
		while(itr.hasNext()) {		
			Stock stock=(Stock)itr.next();
			StockTransaction stockTranjaction=new StockTransaction();
			stockTranjaction.setLastUpdated(LocalDateTime.now());
            stockTranjaction.setStockClosed(stockValue.get(stock.getStockId()));
            stockTranjaction.setStockHigh(stockValue.get(stock.getStockId())+5);
            stockTranjaction.setStockId(stock);
            stockTranjaction.setStockLow(stockValue.get(stock.getStockId())-20);
            stockTranjaction.setStockOpen(stockValue.get(stock.getStockId())-50);
            stockTranjaction.setStockVolume(80);
            treadingRepository.save(stockTranjaction);
            
		}
		
		
		
	}

	
}
