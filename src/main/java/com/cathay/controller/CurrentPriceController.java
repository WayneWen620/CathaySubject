package com.cathay.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.dto.CurrentPriceChangeRequest;
import com.cathay.dto.CurrentPriceRequest;
import com.cathay.entity.Current;
import com.cathay.service.CurrentService;
import com.cathay.utils.CurrentPrice;

/**
 * 
 * @author StevenWen
 *
 */
@RestController
public class CurrentPriceController {

	private final static Logger log = LoggerFactory.getLogger(CurrentPriceController.class);

	@Autowired
	private CurrentService currentServiceImpl;
	/**
	 * 呼叫 CoindeskAPI
	 * @return
	 */
	@GetMapping("/getCoindeskAPI")
	public ResponseEntity<?> getApiData() {
		CurrentPrice currentPrice = currentServiceImpl.callApi();
		return ResponseEntity.status(HttpStatus.OK).body(currentPrice);
	}
	
	/**
	 * 呼叫 CoindeskAPI 並轉換格式
	 * @return
	 */
	@GetMapping("/V1/getCoindeskAPI")
	public ResponseEntity<?> getApiChangeData() {
		CurrentPrice currentPrice = currentServiceImpl.callApi();
		CurrentPriceChangeRequest  request=new CurrentPriceChangeRequest();
		request=request.CurrentPriceToCurrentPriceChangeRequest(currentPrice);
		return ResponseEntity.status(HttpStatus.OK).body(request);
	}
	
	/**
	 * 新增
	 * @return
	 */
	@PostMapping("/create/currency")
	@ResponseBody
	public ResponseEntity<Current> craete(@RequestBody @Validated CurrentPriceRequest currentPriceRequest) {
		Current data = currentPriceRequest.CurrentPriceRequestToCurrentData(currentPriceRequest);
		Current  currentData= currentServiceImpl.insert(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(currentData);
	}
	/**
	 * 查詢
	 * @return
	 */
	@RequestMapping(path = {"/currency/{currency}","/currency/"})
	public ResponseEntity<List<Current>> read(@PathVariable(value = "currency",required = false) String currency) {
		log.info("取得 currency:{}", currency);
		List<Current> currentPrice = currentServiceImpl.getByCurrency(currency);
		return ResponseEntity.status(HttpStatus.OK).body(currentPrice);
	}

	/**
	 * 修改
	 * @return
	 */
	@PostMapping("/update/currency/{currency}")
	@ResponseBody
	public ResponseEntity<List<Current>> updateCurrentPrice(@PathVariable String currency,
			@RequestBody @Validated CurrentPriceRequest currentPriceRequest) {
		Current data = currentPriceRequest.CurrentPriceRequestToCurrentData(currentPriceRequest);
		data.setCurrency(currency);
		currentServiceImpl.updateByCurrency(data);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 刪除
	 * @return
	 */
	@DeleteMapping("/del/currency/{currency}")
	public ResponseEntity<?> delCurrentPrice(@PathVariable String currency) {
		Current data=Current.builder().currency(currency).build();
		currentServiceImpl.deleteByCurrency(data);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		 
	}

}
