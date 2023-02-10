package com.cathay.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cathay.entity.CurrencyRate;
import com.cathay.respository.CurrencyRateRepository;

@SpringBootTest
public class CurrentServiceImplTest {
	@Autowired
	private CurrentServiceImpl currentServiceImpl=new CurrentServiceImpl();
	@MockBean
	private CurrencyRateRepository currentRepository;

	@Test
	@DisplayName("呼叫api測試")
	public void CallApiTest() {
		currentServiceImpl.callApi();
	}

	@Test
	@DisplayName("新增測試")
	public void test_insert() {
		CurrencyRate current=CurrencyRate.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		Mockito.when(currentRepository.save(current)).thenReturn(current);
		currentServiceImpl.insert(any(CurrencyRate.class));
	}

	@Test
	@DisplayName("查詢全部測試")
	public void test_getByCurrency_all() {
		List<CurrencyRate> currents=new ArrayList<CurrencyRate>();
		CurrencyRate current=CurrencyRate.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		currents.add(current);
		current=CurrencyRate.builder().currency("HKD").currencyName("港幣").updateBy("Test123").rate(111).memo("22.1").build();
		currents.add(current);
		Mockito.when(currentRepository.findAll()).thenReturn(currents);
		currentServiceImpl.getByCurrency("");
        verify(currentRepository,times(1)).findAll();
        verify(currentRepository,times(0)).findByCurrency(any());
	}
	
	@Test
	@DisplayName("查詢一筆測試")
	public void test_getByCurrency_findOne() {
		List<CurrencyRate> currents=new ArrayList<CurrencyRate>();
		CurrencyRate current=CurrencyRate.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		currents.add(current);
		current=CurrencyRate.builder().currency("HKD").currencyName("港幣").updateBy("Test123").rate(111).memo("22.1").build();
		currents.add(current);
		Mockito.when(currentRepository.findAll()).thenReturn(currents);
		currentServiceImpl.getByCurrency("VHD");
        verify(currentRepository,times(0)).findAll();
        verify(currentRepository,times(1)).findByCurrency(any());
	}
	
	@Test
	@DisplayName("查詢更新測試")
	public void test_getByCurrency_updateByCurrency() {
		List<CurrencyRate> currencyRateList=new ArrayList<CurrencyRate>();
		CurrencyRate current=CurrencyRate.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		currencyRateList.add(current);
		Mockito.when(currentRepository.findByCurrency("VHD")).thenReturn(currencyRateList);
		currentServiceImpl.updateByCurrency(current);
		verify(currentRepository,times(1)).updateByCurrency(111, "越南盾", "1122", "Test123", "VHD");

	}
	
	@Test
	@DisplayName("查詢刪除測試")
	public void test_getByCurrency_deleteByCurrency() {
		CurrencyRate current=CurrencyRate.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		Mockito.when(currentRepository.save(current)).thenReturn(current);
		currentServiceImpl.deleteByCurrency(any(CurrencyRate.class));
		verify(currentRepository,times(1)).delete(any());

	}
}
