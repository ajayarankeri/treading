package com.hcl.treading.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.treading.entity.Customer;
import com.hcl.treading.entity.DmateAccount;
import com.hcl.treading.entity.Stock;
import com.hcl.treading.entity.StockPurchase;
import com.hcl.treading.entity.StockTransaction;
import com.hcl.treading.repository.CustomerRepository;
import com.hcl.treading.repository.StockRepository;
import com.hcl.treading.repository.StockTransactionRepository;
import com.hcl.treading.repository.TradingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class TrendingServiceTest {
	
	@InjectMocks
	TreadingService treadingService;
	
	@Mock
	CustomerRepository customerRepository;
	@Mock
	StockRepository stockRepository;
	@Mock
	StockTransactionRepository stockTransactionRepository;
	@Mock
	TradingRepository tradingRepository;
	
	Stock stock;
	Customer customer;
	StockPurchase stockPurchase;
	StockTransaction stockTransaction;
	DmateAccount dmateAccount;
	List<StockPurchase> stockPurchaseList;
	
	@Before
	public void setMockData() {
		
		stock=new Stock();
		stock.setStockId(Long.valueOf(1));
		stock.setStockName("SBI");
		stock.setStockType("Equity");
		
		customer =new Customer();
		customer.setAddress("Pune");
		customer.setBirthDate(LocalDate.parse("2019-08-09"));
		customer.setCustomerId(Long.valueOf(1));
		customer.setEmail("vinayakdesaimca@gmail.com");
		customer.setFullName("vinayak");
		customer.setGender("M");
		customer.setMobileNo("9158022153");
		
		
		dmateAccount=new DmateAccount();
		dmateAccount.setAccountNumber(Long.valueOf(1));
		dmateAccount.setAccountType("dmate");
		dmateAccount.setBalance(Double.valueOf(23));
		dmateAccount.setCustomerId(customer);
		dmateAccount.setId(Long.valueOf(1));
		

		stockPurchase=new StockPurchase();
		stockPurchase.setCustomerId(customer);
		stockPurchase.setFees(Double.valueOf(200));
		stockPurchase.setPurchaseAmount(Double.valueOf(200));
		stockPurchase.setPurchaseDate(LocalDateTime.now());
		stockPurchase.setPurchaseId(Long.valueOf(1));
		stockPurchase.setPurchaseStatus(0);
		stockPurchase.setTotalFee(Double.valueOf(200));
		stockPurchase.setUnit(3);
		stockPurchase.setStockId(stock);
		stockPurchase.setFees(Double.valueOf(200));
		
		stockTransaction=new StockTransaction();
		stockTransaction.setLastUpdated(LocalDateTime.now());
		stockTransaction.setPerPrice(Double.valueOf(3));
		stockTransaction.setStockId(stock);
		stockTransaction.setStockOpen(Float.valueOf(1));
		stockTransaction.setStockTransactionId(Long.valueOf(1));
		
		stockPurchaseList=new ArrayList<StockPurchase>();
		stockPurchaseList.add(stockPurchase);
		
	}
	
	

}
