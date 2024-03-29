package com.hcl.treading.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="mst_stock")
@Data
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stock_id")
	@JsonIgnore
	private Long stockId;
	
	@Column(name="stock_name")
	private String stockName;
	
	@JsonIgnore
	@Column(name="stock_type")
	private String stockType;


}
