package com.cathay.enu;

public enum Currency {
	USD("美金"),
	GBP("英鎊"),
	EUR("歐元"),
	TWD("台幣"),
	HKD("港幣"),
	AUD("澳幣"),
	CAD("加拿大幣"),
	SGD("新加坡幣"),
	CHF("瑞士法朗幣"),
	JPY("日圓"),
	ZAR("南非幣"),
	SEK("瑞典幣"),
	NZD("紐元"),
	THB("泰幣"),
	PHP("菲國比索"),
	IDR("印尼幣"),
	KRW("韓元"),
	VND("越南盾"),
	MYR("馬來幣"),
	CNY("人民幣"),
	;
	
	private String desc;
	
	Currency(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
}


