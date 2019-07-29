package com.hcl.treading.schedular;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.treading.entity.Stock;
import com.hcl.treading.entity.StockTransaction;
import com.hcl.treading.exception.ResourceNotFoundException;
import com.hcl.treading.repository.StockRepository;
import com.hcl.treading.repository.TradingRepository;

@Component
public class StockSchedular {
	
	@Autowired
	TradingRepository treadingRepository;
	
	@Autowired
	StockRepository stockRepository;

	//@Scheduled(fixedRate = 10000)
	public void fixedRateSch() throws ResourceNotFoundException { 
		
		HashMap<Long, Float> stockValue=new HashMap<Long,Float>();
		stockValue.put(Long.valueOf(1), Float.valueOf(100));
		stockValue.put(Long.valueOf(2), Float.valueOf(200));
		stockValue.put(Long.valueOf(3), Float.valueOf(50));
		List<Stock> stockList=stockRepository.findAll();
		
		Iterator<Stock> itr=stockList.iterator();
		while(itr.hasNext()) {		
			Stock stock=itr.next();
			StockTransaction stockTranjaction=treadingRepository.findById(stock.getStockId()).orElseThrow(()-> new ResourceNotFoundException("not found"));
			stockTranjaction.setLastUpdated(LocalDateTime.now());
			stockTranjaction.setStockId(stock);
			stockTranjaction.setPerPrice(Double.valueOf(stockTranjaction.getPerPrice()+1));
			stockTranjaction.setStockOpen(Float.valueOf(10));
            treadingRepository.save(stockTranjaction);
            
		}
		
		
		
	}

	
}
