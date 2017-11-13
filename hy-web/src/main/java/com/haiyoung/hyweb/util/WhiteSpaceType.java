package com.haiyoung.hyweb.util;

public enum WhiteSpaceType {

	EMPTY_STR(""), NULL(null), NA_STR("N/A"), ZERO_STR("0"), BAR_STR("-");

	private final String value;

	WhiteSpaceType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}