package com.cathay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cathay.entity.Current;
import com.cathay.respository.CurrentRepository;
import com.cathay.service.CurrentService;
import com.cathay.utils.CurrentPrice;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrentServiceImpl implements CurrentService {
	@Autowired
	private CurrentRepository currentRepository;

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
	public Current insert(Current current) {
		return currentRepository.save(current);
	}

	@Override
	public List<Current> getByCurrency(String currency) {
		if(null==currency || "".equals(currency)) {
			return currentRepository.findAll();
		}
		return currentRepository.findByCurrency(currency);

	}

	@Override
	public void updateByCurrency(Current currency) {
		currentRepository.save(currency);
	}

	@Override
	public void deleteByCurrency(Current currency) {
		currentRepository.delete(currency);
	}

}
