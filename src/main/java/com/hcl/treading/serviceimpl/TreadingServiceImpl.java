package com.hcl.treading.serviceimpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.treading.dto.ConfirmPurchaseDto;
import com.hcl.treading.dto.ResponseDto;
import com.hcl.treading.dto.StockPurchaseDto;
import com.hcl.treading.entity.Customer;
import com.hcl.treading.entity.DmateAccount;
import com.hcl.treading.entity.Stock;
import com.hcl.treading.entity.StockPurchase;
import com.hcl.treading.entity.StockTransaction;
import com.hcl.treading.exception.NoOrderFoundException;
import com.hcl.treading.exception.ResourceNotFoundException;
import com.hcl.treading.repository.CustomerRepository;
import com.hcl.treading.repository.DmateAccountRepository;
import com.hcl.treading.repository.StockPurchaseRepository;
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
	
	@Autowired
	DmateAccountRepository dmateAccountRepository;
	
	@Autowired
	StockPurchaseRepository stockPurchaseRepository;
	
	@Override
	public ResponseDto purchaseStock(StockPurchaseDto stockPurchaseDto) throws ResourceNotFoundException {
		
		Customer customer=customerRepository.findById(stockPurchaseDto.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Customer Not Found"));
		Stock availableStock=stockRepository.findById(stockPurchaseDto.getStockId()).orElseThrow(()->new ResourceNotFoundException("Stock Not Available"));		
		StockTransaction stockTransactionDtails=stockTransactionRepository.findById(stockPurchaseDto.getStockId()).orElseThrow(()->new ResourceNotFoundException("Stock Values are not defined"));
		DmateAccount accountDetails=dmateAccountRepository.findById(stockPurchaseDto.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Your Dmate Account Not Found"));
		
		StockPurchase stockPurchaseDetails=null;
		
		if(customer!=null && availableStock!=null && stockTransactionDtails!=null && accountDetails!=null) {
			if(accountDetails.getBalance()>(stockTransactionDtails.getPerPrice()*stockPurchaseDto.getQuantity())) {
				 stockPurchaseDetails=new StockPurchase();
					stockPurchaseDetails.setPurchaseDate(LocalDateTime.now());
					stockPurchaseDetails.setPurchaseAmount(stockTransactionDtails.getPerPrice()*stockPurchaseDto.getQuantity());
					stockPurchaseDetails.setPurchaseStatus(0);
					stockPurchaseDetails.setCustomerId(customer);
					stockPurchaseDetails.setStockId(availableStock);
					stockPurchaseDetails.setFees(0.20);
					stockPurchaseDetails.setTotalFee(0.20*stockPurchaseDto.getQuantity());
					stockPurchaseDetails.setUnit(stockPurchaseDto.getQuantity());
					
				    stockPurchaseRepository.save(stockPurchaseDetails);
				  		    
			}else {
				throw new ResourceNotFoundException("Your Dmate account does not have sufficient balance");
			}
		}
		
		if(stockPurchaseDetails!=null)
			return new ResponseDto("sucess", 200, stockPurchaseDetails);
		else
			return new ResponseDto("sucess", 300, "Please verify your entered details");
	}
	
	@Override
	public ResponseDto orderHistory(Long customerId) throws ResourceNotFoundException, NoOrderFoundException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		if(stockPurchaseRepository.findByCustomerIdOrderByPurchaseDateDesc(customer).isEmpty()) {
			throw new NoOrderFoundException("customer has not purchased any stocks yet. Please purchase stocks");
		}
		return new ResponseDto("Successfull",300,stockPurchaseRepository.findByCustomerIdOrderByPurchaseDateDesc(customer));
	}

	@Override
	public ResponseDto confirmPurchaseStock(ConfirmPurchaseDto confirmPurchaseDto) throws ResourceNotFoundException {
		StockPurchase purchaseDetailsBefore=stockPurchaseRepository.findById(confirmPurchaseDto.getPurchaseId()).orElseThrow(()-> new ResourceNotFoundException("Stock Details not found to confirm"));

		StockTransaction stockTransactionDtails=stockTransactionRepository.findById(purchaseDetailsBefore.getStockId().getStockId()).orElseThrow(()->new ResourceNotFoundException("Stock Values are not defined"));
		
		DmateAccount accountDetails=dmateAccountRepository.findById(purchaseDetailsBefore.getCustomerId().getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Your Dmate Account Not Found"));
				
				if(accountDetails.getBalance()>stockTransactionDtails.getPerPrice()*purchaseDetailsBefore.getUnit()) {
					if(purchaseDetailsBefore.getPurchaseStatus()==1) {
						 throw new ResourceNotFoundException("Your have already confirm");
					}else {
					purchaseDetailsBefore.setPurchaseStatus(1);
					purchaseDetailsBefore.setPurchaseAmount(stockTransactionDtails.getPerPrice()*purchaseDetailsBefore.getUnit());
					accountDetails.setBalance(accountDetails.getBalance()-(stockTransactionDtails.getPerPrice()*purchaseDetailsBefore.getUnit()));
					
					stockPurchaseRepository.save(purchaseDetailsBefore);
				    dmateAccountRepository.save(accountDetails);
					}
			}else {
				 throw new ResourceNotFoundException("Your Dmate account does not have sufficient balance");
			}
		
		 return new ResponseDto("sucess", 200, purchaseDetailsBefore);
		
	}

}
