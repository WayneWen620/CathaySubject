package com.cathay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentData {
	@Schema(description = "幣別(英文)")
	private String currencyCode;
	@Schema(description = "幣別(中文)")
	private String currencyName;
	@Schema(description = "匯率")
	private float rate;
}
