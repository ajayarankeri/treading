package com.hcl.treading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.treading.entity.Customer;
import com.hcl.treading.entity.StockPurchase;

@Repository
public interface StockPurchaseRepository extends JpaRepository<StockPurchase, Long>{

	List<StockPurchase> findByCustomerIdOrderByPurchaseDateDesc(Customer customer);

}
