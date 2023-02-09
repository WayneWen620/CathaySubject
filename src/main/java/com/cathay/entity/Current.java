package com.cathay.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CURRENCY_RATE")
public class Current implements Serializable{

	private static final long serialVersionUID = -8187345297195839518L;
	@Id
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "currency_name")
	private String currencyName;

	@Column(name = "rate")
	private float rate;

	@Column(name = "memo")
	private String memo;

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "update_by")
	private String updateBy;
}
