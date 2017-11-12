package com.haiyoung.hyweb.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	private static Gson gson = new Gson();

	public static String toJson(Object object) {
		return toJson(object, false);
	}

	public static String toJson(Object object,
			boolean excludeFieldsWithoutExposeAnnotation) {
		if (excludeFieldsWithoutExposeAnnotation) {
			GsonBuilder bulider = new GsonBuilder();
			bulider.excludeFieldsWithoutExposeAnnotation();
			return bulider.create().toJson(object);
		}
		return gson.toJson(object);
	}

	public static <T> T fromJson(String json, Class<T> entityClass) {
		return gson.fromJson(json, entityClass);
	}

	public static <T> T fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}

}