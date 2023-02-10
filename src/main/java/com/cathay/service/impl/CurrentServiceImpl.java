package com.cathay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cathay.dto.CurrenyRequest;
import com.cathay.entity.CurrencyRate;
import com.cathay.respository.CurrencyRateRepository;
import com.cathay.service.CurrentService;
import com.cathay.utils.CurrentPrice;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrentServiceImpl implements CurrentService {
	@Autowired
	private CurrencyRateRepository currentRepository;

	@Override
	public CurrentPrice callApi() {
		CurrentPrice currentPrice = null;
		RestTemplate template = new RestTemplate();
		String response = template.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
		log.info("response:{}", response);
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			currentPrice = objectMapper.readValue(response, CurrentPrice.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentPrice;
	}
	public CurrencyRate insert(CurrencyRate current) {
		return currentRepository.save(current);
	}

	@Override
	public List<CurrencyRate> getByCurrency(String currency) {
		if(null==currency || "".equals(currency)) {
			return currentRepository.findAll();
		}
		return currentRepository.findByCurrency(currency);

	}

	@Override
	public CurrencyRate updateByCurrency(CurrencyRate currencyRate) {
		currentRepository.updateByCurrency(currencyRate.getRate(), currencyRate.getCurrencyName(), currencyRate.getMemo(), currencyRate.getUpdateBy(),currencyRate.getCurrency());
		List<CurrencyRate> currencyRateData=currentRepository.findByCurrency(currencyRate.getCurrency());
		return currencyRateData.get(0);
	}

	@Override
	public void deleteByCurrency(CurrencyRate currency) {
		currentRepository.delete(currency);
	}

}
