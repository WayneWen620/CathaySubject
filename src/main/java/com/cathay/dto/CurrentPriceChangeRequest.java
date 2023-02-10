package com.cathay.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cathay.utils.CurrentPrice;
import com.cathay.utils.CurrentPriceBpi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentPriceChangeRequest {
	private List<CurrentData> currentDataList;
	private Date updated;

	public CurrentPriceChangeRequest CurrentPriceToCurrentPriceChangeRequest(CurrentPrice currentPrice) {
		if(currentPrice==null) {
			return null;
		}
		List<CurrentData> data=new ArrayList<CurrentData>();
		if(currentPrice.getBpi()!=null) {
			Map<String, CurrentPriceBpi> currentList = new HashMap<String, CurrentPriceBpi>(currentPrice.getBpi());
			for (String key : currentList.keySet()) {
				CurrentPriceBpi currentPriceBpi = currentList.get(key);
				CurrentData currentData=CurrentData.builder().rate(currentPriceBpi.getRatefloat()).code(currentPriceBpi.getCode().name()).name(currentPriceBpi.getCode().getDesc()).build();
				data.add(currentData);
			}
		}
		CurrentPriceChangeRequest  changeRequest=CurrentPriceChangeRequest.builder().currentDataList(data).updated(new Date()).build();
		return changeRequest;
	}


}
