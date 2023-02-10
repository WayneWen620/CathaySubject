package com.cathay.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CurrencyRate implements Serializable{

	private static final long serialVersionUID = -8187345297195839518L;
	@Id
	@Column(name = "currency")
	@Schema(description = "幣別代碼")
	private String currency;
	
	@Column(name = "currency_name")
	@Schema(description = "幣別")
	private String currencyName;

	@Column(name = "rate")
	@Schema(description = "匯率")
	private float rate;

	@Column(name = "memo")
	@Schema(description = "備註")
	private String memo;

	@Column(name = "creation_date")
	@Schema(description = "建立時間")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	private Date creationDate;

	@Column(name = "created_by")
	@Schema(description = "建立人員")
	private String createdBy;

	@Column(name = "update_date")
	@Schema(description = "更新時間")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	private Date updateDate;

	@Column(name = "update_by")
	@Schema(description = "更新人員")
	private String updateBy;
}
