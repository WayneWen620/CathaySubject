package com.cathay.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.cathay.entity.Current;
import com.cathay.enu.Currency;

import lombok.Data;
@Data
public class CurrentPriceRequest {
	@NotNull
	private Currency currency;
	@NotNull
	private String currencyName;
	private String memo;
	@NotNull
	private float rate;
	@NotNull
	private String updateBy;
	
	
	public Current CurrentPriceRequestToCurrentData(CurrentPriceRequest currentPriceRequest) {
		if(currentPriceRequest==null) return null;
			
		return Current.builder().currency(currentPriceRequest.getCurrency().name())
				.currencyName(currentPriceRequest.getCurrency().getDesc())
				.currencyName(currentPriceRequest.getCurrency().getDesc())
				.rate(currentPriceRequest.getRate()).createdBy(currentPriceRequest.getUpdateBy())
				.memo(currentPriceRequest.getMemo())
				.creationDate(new Date())
				.updateDate(new Date())
				.updateBy(currentPriceRequest.getUpdateBy()).build();

	}
}
