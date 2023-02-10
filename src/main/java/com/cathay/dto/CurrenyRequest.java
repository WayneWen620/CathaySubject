package com.cathay.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.cathay.entity.CurrencyRate;
import com.cathay.enu.Currency;

import lombok.Data;
@Data
public class CurrenyRequest {
	
	private Currency currency;
	@NotNull
	private String currencyName;
	private String memo;
	@NotNull
	private float rate;
	@NotNull
	private String updateBy;
	
	public CurrencyRate CurrenyRequestToCurrentData(CurrenyRequest currenyRequest, Currency currencyRequest) {
		if(currenyRequest==null) return null;

		return CurrencyRate.builder()
				.currency(currencyRequest.name())
				.currencyName(currenyRequest.getCurrencyName())
				.rate(currenyRequest.getRate()).createdBy(currenyRequest.getUpdateBy())
				.memo(currenyRequest.getMemo())
				.creationDate(new Date())
				.updateDate(new Date())
				.updateBy(currenyRequest.getUpdateBy()).build();

	}
}
