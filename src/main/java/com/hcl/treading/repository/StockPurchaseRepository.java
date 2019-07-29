package com.hcl.treading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.treading.entity.StockPurchase;

@Repository
public interface StockPurchaseRepository extends JpaRepository<StockPurchase, Long>{

}
