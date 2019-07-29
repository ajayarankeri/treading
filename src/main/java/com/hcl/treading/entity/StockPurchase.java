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
@Table(name="stock_purchase")
@Data
public class StockPurchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purchase_id")
	private Long purchaseId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="stock_id")
	private Stock stockId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customerId;
	
	@Column(name="unit")
	private int unit;

	@Column(name="purchase_amount")
	private Double purchaseAmount;
	
	@Column(name="purchase_status")
	private int purchaseStatus;
	
	@Column(name="fees")
	private Double fees;
	
	@Column(name="total_fee")
	private Double totalFee;
	
	@Column(name="purchase_date")
	private LocalDateTime purchaseDate;
	
}
