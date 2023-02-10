package com.cathay.utils;

import com.cathay.enu.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({
"code",
"symbol"
,"rate"
,"description"
,"rate_float"
	})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPriceBpi {

	@JsonProperty("code")
	private Currency code;

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("rate")
	private String rate;

	@JsonProperty("description")
	private String description;

	@JsonProperty("rate_float")
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private float ratefloat;

}
