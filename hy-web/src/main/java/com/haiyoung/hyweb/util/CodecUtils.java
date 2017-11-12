package com.haiyoung.hyweb.util;

import java.util.UUID;

public class CodecUtils {

	public static String createUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String generateGUID() {
		return createUuid().replace("-", "");
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println(CodecUtils.generateGUID());
		}
	}

}