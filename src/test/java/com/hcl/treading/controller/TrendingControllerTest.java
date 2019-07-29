package com.hcl.treading.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.entity.Customer;
import com.hcl.treading.entity.DmateAccount;
import com.hcl.treading.entity.Stock;
import com.hcl.treading.entity.StockPurchase;
import com.hcl.treading.entity.StockTransaction;
import com.hcl.treading.exception.NoOrderFoundException;
import com.hcl.treading.exception.ResourceNotFoundException;
import com.hcl.treading.serviceimpl.TreadingServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class TrendingControllerTest {

	@InjectMocks
	TreadingController treadingController;
	
	@Mock
	TreadingServiceImpl treadingServiceImpl;
	
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
	public void confirmPurchaseStockControllerTest() throws ResourceNotFoundException {
		//assertNotNull(treadingController.confirmPurchaseStock(stockPurchaseDto));
	}
	
	@Test
	public void purchaseStockControllerTest() throws ResourceNotFoundException {
		assertNotNull(treadingController.purchaseStock(stockPurchaseDto));
	}
	
	@Test
	public void orderHistoryTest() throws ResourceNotFoundException, NoOrderFoundException {
		assertNotNull(treadingController.orderHistory(1l));
	}
}
