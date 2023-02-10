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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.dto.CurrentPriceChangeRequest;
import com.cathay.dto.CurrenyInserRequest;
import com.cathay.dto.CurrenyRequest;
import com.cathay.entity.CurrencyRate;
import com.cathay.enu.Currency;
import com.cathay.service.CurrentService;
import com.cathay.utils.CurrentPrice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author StevenWen
 *
 */
@RestController
@Tag(name = "幣別相關api")
public class CurrentPriceController {

	private final static Logger log = LoggerFactory.getLogger(CurrentPriceController.class);

	@Autowired
	private CurrentService currentServiceImpl;
	/**
	 * 呼叫 CoindeskAPI
	 * @return
	 */
	@Operation(summary = "取得CoindeskAPI", description = "取得CoindeskAPI")
	@GetMapping("/getCoindeskAPI")
	@ResponseBody
	public ResponseEntity<?> getApiData() {
		CurrentPrice currentPrice = currentServiceImpl.callApi();
		return ResponseEntity.status(HttpStatus.OK).body(currentPrice);
	}
	
	/**
	 * 呼叫 CoindeskAPI 並轉換格式
	 * @return
	 */
	@Operation(summary = "取得CoindeskAPI 經過轉換", description = "取得CoindeskAPI 經過轉換")
	@GetMapping("/V1/getCoindeskAPI")
	@ResponseBody
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
	@Operation(summary = "新增幣別", description = "新增幣別")
	@PostMapping("/create/currency")
	@ResponseBody
	public ResponseEntity<CurrencyRate> craete(@RequestBody @Validated CurrenyInserRequest currenyInserRequest) {
		CurrencyRate data = currenyInserRequest.CurrenyInserRequestToCurrentData(currenyInserRequest,currenyInserRequest.getCurrency());
		CurrencyRate  currentData= currentServiceImpl.insert(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(currentData);
	}
	/**
	 * 查詢
	 * @return
	 */
	@Operation(summary = "查詢幣別", description = "查詢幣別")
	@RequestMapping(path = {"/currency/{currency}","/currency/"},method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CurrencyRate>> read(@PathVariable(value = "currency",required = false) String currency) {
		log.info("取得 currency:{}", currency);
		List<CurrencyRate> currentPrice = currentServiceImpl.getByCurrency(currency);
		return ResponseEntity.status(HttpStatus.OK).body(currentPrice);
	}

	/**
	 * 修改
	 * @return
	 */
	@Operation(summary = "修改幣別", description = "修改幣別")
	@PostMapping("/update/currency/{currency}")
	@ResponseBody
	public ResponseEntity<Object> updateCurrentPrice(@PathVariable Currency currency,
			@RequestBody @Validated CurrenyRequest currentPriceRequest) {

		CurrencyRate data = currentPriceRequest.CurrenyRequestToCurrentData(currentPriceRequest,currency);
		CurrencyRate responseData;
		try {
			responseData = currentServiceImpl.updateByCurrency(data);
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新失敗:{}",e.getMessage());
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("更新失敗");

	}

	/**
	 * 刪除
	 * @return
	 */
	@Operation(summary = "刪除幣別", description = "刪除幣別")
	@DeleteMapping("/del/currency/{currency}")
	public ResponseEntity<?> delCurrentPrice(@PathVariable String currency) {
		CurrencyRate data=CurrencyRate.builder().currency(currency).build();
		currentServiceImpl.deleteByCurrency(data);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		 
	}

}
