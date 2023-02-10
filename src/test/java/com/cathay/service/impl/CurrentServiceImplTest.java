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

import com.cathay.entity.Current;
import com.cathay.respository.CurrentRepository;

@SpringBootTest
public class CurrentServiceImplTest {
	@Autowired
	private CurrentServiceImpl currentServiceImpl=new CurrentServiceImpl();
	@MockBean
	private CurrentRepository currentRepository;

	@Test
	@DisplayName("呼叫api測試")
	public void testCallApi() {
		currentServiceImpl.callApi();
	}

	@Test
	@DisplayName("新增測試")
	public void test_insert() {
		Current current=Current.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		Mockito.when(currentRepository.save(current)).thenReturn(current);
		currentServiceImpl.insert(any(Current.class));
	}

	@Test
	@DisplayName("查詢全部測試")
	public void test_getByCurrency_all() {
		List<Current> currents=new ArrayList<Current>();
		Current current=Current.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		currents.add(current);
		current=Current.builder().currency("HKD").currencyName("港幣").updateBy("Test123").rate(111).memo("22.1").build();
		currents.add(current);
		Mockito.when(currentRepository.findAll()).thenReturn(currents);
		currentServiceImpl.getByCurrency("");
        verify(currentRepository,times(1)).findAll();
        verify(currentRepository,times(0)).findByCurrency(any());
	}
	
	@Test
	@DisplayName("查詢一筆測試")
	public void test_getByCurrency_findOne() {
		List<Current> currents=new ArrayList<Current>();
		Current current=Current.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		currents.add(current);
		current=Current.builder().currency("HKD").currencyName("港幣").updateBy("Test123").rate(111).memo("22.1").build();
		currents.add(current);
		Mockito.when(currentRepository.findAll()).thenReturn(currents);
		currentServiceImpl.getByCurrency("VHD");
        verify(currentRepository,times(0)).findAll();
        verify(currentRepository,times(1)).findByCurrency(any());
	}
	
	@Test
	@DisplayName("查詢更新測試")
	public void test_getByCurrency_updateByCurrency() {
		Current current=Current.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		Mockito.when(currentRepository.save(current)).thenReturn(current);
		currentServiceImpl.updateByCurrency(any(Current.class));
		 verify(currentRepository,times(1)).save(any());

	}
	
	@Test
	@DisplayName("查詢刪除測試")
	public void test_getByCurrency_deleteByCurrency() {
		Current current=Current.builder().currency("VHD").currencyName("越南盾").updateBy("Test123").rate(111).memo("1122").build();
		Mockito.when(currentRepository.save(current)).thenReturn(current);
		currentServiceImpl.deleteByCurrency(any(Current.class));
		verify(currentRepository,times(1)).delete(any());

	}
}
