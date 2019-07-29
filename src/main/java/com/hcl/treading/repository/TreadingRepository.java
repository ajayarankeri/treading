package com.hcl.treading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.treading.entity.StockTransaction;

@Repository
public interface TreadingRepository extends JpaRepository<StockTransaction, Long>{

}
