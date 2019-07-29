package com.hcl.treading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.treading.entity.Stock;

@Repository

public interface StockRepository extends JpaRepository<Stock, Long>{



}
