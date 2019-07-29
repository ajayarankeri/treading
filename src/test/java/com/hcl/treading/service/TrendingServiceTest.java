package com.hcl.treading.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.entity.Customer;
import com.hcl.treading.entity.DmateAccount;
import com.hcl.treading.entity.Stock;
import com.hcl.treading.entity.StockPurchase;
import com.hcl.treading.entity.StockTransaction;
import com.hcl.treading.exception.ResourceNotFoundException;
import com.hcl.treading.repository.CustomerRepository;
import com.hcl.treading.repository.DmateAccountRepository;
import com.hcl.treading.repository.StockPurchaseRepository;
import com.hcl.treading.repository.StockRepository;
import com.hcl.treading.repository.StockTransactionRepository;
import com.hcl.treading.repository.TradingRepository;
import com.hcl.treading.serviceimpl.TreadingServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class TrendingServiceTest {
	
	@InjectMocks
	TreadingServiceImpl treadingService;
	
	@Mock
	CustomerRepository customerRepository;
	@Mock
	StockRepository stockRepository;
	
	@Mock
	StockPurchaseRepository stockPurchaseRepository;
	
	@Mock
	StockTransactionRepository stockTransactionRepository;
	@Mock
	TradingRepository tradingRepository;
	
	@Mock
	DmateAccountRepository dmateAccountRepository;
	
	
	Stock stock;
	Customer customer;
	StockPurchase stockPurchase;
	StockTransaction stockTransaction;
	DmateAccount dmateAccount,dmateAccount1;
	List<StockPurchase> stockPurchaseList;
	StockPurchaseDto stockPurchaseDto;
	
	
	@Before
	public void setMockData() {
		
		stockPurchaseDto=new StockPurchaseDto();
		stockPurchaseDto.setCustomerId(1l);
		stockPurchaseDto.setQuantity(2);
		stockPurchaseDto.setStockId(1l);
		
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
		
		dmateAccount1=new DmateAccount();
		dmateAccount1.setAccountNumber(Long.valueOf(2));
		dmateAccount1.setAccountType("dmate");
		dmateAccount1.setBalance(Double.valueOf(0));
		dmateAccount1.setCustomerId(customer);
		dmateAccount1.setId(Long.valueOf(1));
		
		

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
	
	
	@Test
	public void  purchaseStockTest() throws ResourceNotFoundException {
		Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));
		Mockito.when(stockRepository.findById(1l)).thenReturn(Optional.of(stock));
		Mockito.when(stockTransactionRepository.findById(1l)).thenReturn(Optional.of(stockTransaction));
		Mockito.when(dmateAccountRepository.findById(1l)).thenReturn(Optional.of(dmateAccount));
		assertNotNull(treadingService.purchaseStock(stockPurchaseDto));
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void  purchaseStockFailTest() throws ResourceNotFoundException {
		Mockito.when(customerRepository.findById(2l)).thenReturn(Optional.of(customer));
		Mockito.when(stockRepository.findById(2l)).thenReturn(Optional.of(stock));
		Mockito.when(stockTransactionRepository.findById(2l)).thenReturn(Optional.of(stockTransaction));
		Mockito.when(dmateAccountRepository.findById(2l)).thenReturn(Optional.of(dmateAccount));
		assertNotNull(treadingService.purchaseStock(stockPurchaseDto));
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void  purchaseStockBalnceFailTest() throws ResourceNotFoundException {
		Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));
		Mockito.when(stockRepository.findById(1l)).thenReturn(Optional.of(stock));
		Mockito.when(stockTransactionRepository.findById(1l)).thenReturn(Optional.of(stockTransaction));
		Mockito.when(dmateAccountRepository.findById(2l)).thenReturn(Optional.of(dmateAccount1));
		assertNotNull(treadingService.purchaseStock(stockPurchaseDto));
	}
	

}
