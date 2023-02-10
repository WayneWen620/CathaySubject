package com.cathay.service;

import java.util.List;

import com.cathay.entity.Current;
import com.cathay.utils.CurrentPrice;

public interface CurrentService {
	
	public Current insert(Current current);

	public CurrentPrice callApi();

	public List<Current> getByCurrency(String toCurrency);

	public void updateByCurrency(Current currency);

	public void deleteByCurrency(Current currency);

}
