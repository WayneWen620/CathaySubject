package com.cathay.utils;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({
"disclaimer",
"chartName"
,"time"
,"bpi"
	})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPrice {

	@JsonProperty("time")
	private CurrentPriceTime time;

	@JsonProperty("disclaimer")
	private String disclaimer;

	@JsonProperty("chartName")
	private String chartName;

	@JsonProperty("bpi")
	private Map<String,CurrentPriceBpi> bpi;

}
