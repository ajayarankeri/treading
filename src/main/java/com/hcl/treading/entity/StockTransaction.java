package com.hcl.treading.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="trn_stock")
@Data
public class StockTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stock_transaction_id")
	private Long stockTransactionId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="stock_id")
	private Stock stockId;
	
	@Column(name="stock_open")
	private Float stockOpen;
	
	@Column(name="per_price")
	private Double perPrice;
	
	@Column(name="last_updated")
	private LocalDateTime lastUpdated;
	
	

}
