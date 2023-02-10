package com.cathay.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.cathay.entity.CurrencyRate;
import com.cathay.enu.Currency;

import lombok.Data;
@Data
public class CurrenyInserRequest {
	@NotNull
	private Currency currency;
	@NotNull
	private String currencyName;
	private String memo;
	@NotNull
	private float rate;
	@NotNull
	private String updateBy;
	
	public CurrencyRate CurrenyInserRequestToCurrentData(CurrenyInserRequest currenyInserRequest, Currency currencyRequest) {
		if(currenyInserRequest==null) return null;

		return CurrencyRate.builder()
				.currency(currencyRequest.name())
				.currencyName(currenyInserRequest.getCurrencyName())
				.rate(currenyInserRequest.getRate()).createdBy(currenyInserRequest.getUpdateBy())
				.memo(currenyInserRequest.getMemo())
				.creationDate(new Date())
				.updateDate(new Date())
				.updateBy(currenyInserRequest.getUpdateBy()).build();

	}
}
