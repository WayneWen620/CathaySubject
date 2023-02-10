package com.cathay.service;

import java.util.List;

import com.cathay.entity.CurrencyRate;
import com.cathay.utils.CurrentPrice;

public interface CurrentService {
	
	public CurrencyRate insert(CurrencyRate current);

	public CurrentPrice callApi();

	public List<CurrencyRate> getByCurrency(String toCurrency);

	public CurrencyRate updateByCurrency(CurrencyRate currencyRate);

	public void deleteByCurrency(CurrencyRate currency);

}
