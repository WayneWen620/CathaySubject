package com.cathay.utils;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonPropertyOrder({
"updated",
"updatedISO",
"updateduk"
	})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentPriceTime {

	@JsonProperty("updated")
	private String updated;
	
	@JsonProperty("updatedISO")
	private String updatedISO;
	
	@JsonProperty("updateduk")
	private String updateduk;
	
	
}
