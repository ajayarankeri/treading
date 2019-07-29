package com.hcl.treading.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.entity.Customer;
import com.hcl.treading.entity.Stock;
import com.hcl.treading.exception.ResourceNotFoundException;
import com.hcl.treading.repository.CustomerRepository;
import com.hcl.treading.repository.StockRepository;
import com.hcl.treading.repository.StockTransactionRepository;
import com.hcl.treading.repository.TradingRepository;
import com.hcl.treading.service.TreadingService;

@Service
public class TreadingServiceImpl implements TreadingService{
	
	@Autowired
	TradingRepository tradingRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	StockTransactionRepository stockTransactionRepository;

	@Autowired
	StockRepository stockRepository;
	
	@Override
	public void purchaseStock(StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException {
		
		Customer customer=customerRepository.findById(stockPurchaseDto.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Customer Not Found"));
		Stock availableStock=stockRepository.findById(stockPurchaseDto.getStockId()).orElseThrow(()->new ResourceNotFoundException("Stock Not Available"));
		
	}

}
