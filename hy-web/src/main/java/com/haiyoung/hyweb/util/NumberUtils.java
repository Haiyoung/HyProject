package com.haiyoung.hyweb.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {

	public static final double DEFAULT_ZERO_THRESHOLD = 0.0000001d;

	public static String formatDecimal(Double x) {
		return formatDecimal(x, null);
	}

	public static String formatDecimal(Double x, WhiteSpaceType type) {
		return formatDecimal(x, true, type);
	}

	public static String formatDecimal(Double x, boolean enableThousandsSeparator) {
		return formatDecimal(x, enableThousandsSeparator, null);
	}

	public static String formatDecimal(Double x, boolean enableThousandsSeparator, WhiteSpaceType type) {
		return formatDecimal(x, 2, enableThousandsSeparator, type);
	}

	public static String formatDecimal(Double x, int currency) {
		return formatDecimal(x, currency, null);
	}

	public static String formatDecimal(Double x, int currency, WhiteSpaceType type) {
		return formatDecimal(x, currency, currency, type);
	}

	public static String formatDecimal(Double x, int currency, boolean enableThousandsSeparator) {
		return formatDecimal(x, currency, enableThousandsSeparator, null);
	}

	public static String formatDecimal(Double x, int currency, boolean enableThousandsSeparator, WhiteSpaceType type) {
		return formatDecimal(x, currency, currency, enableThousandsSeparator, type);
	}

	public static String formatDecimal(Double x, int minBit, int maxBit) {
		return formatDecimal(x, minBit, maxBit, null);
	}

	public static String formatDecimal(Double x, int minBit, int maxBit, WhiteSpaceType type) {
		return formatDecimal(x, minBit, maxBit, true, type);
	}

	public static String formatDecimal(Double x, int minBit, int maxBit, boolean enableThousandsSeparator) {
		return formatDecimal(x, minBit, maxBit, enableThousandsSeparator, null);
	}

	public static String formatDecimal(Double x, int minBit, int maxBit, boolean enableThousandsSeparator,
			WhiteSpaceType type) {
		if (x == null || x.isInfinite() || x.isNaN() || minBit < 0 || maxBit < 0 || minBit > maxBit)
			return type != null ? type.value() : WhiteSpaceType.BAR_STR.value();
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		df.setGroupingSize(0);
		df.setMinimumFractionDigits(minBit);
		df.setMaximumFractionDigits(maxBit);
		if (enableThousandsSeparator)
			df.setGroupingSize(3);
		return df.format(x);
	}

	public static String formatPercent(Double x) {
		return formatPercent(x, null);
	}

	public static String formatPercent(Double x, WhiteSpaceType type) {
		return formatPercent(x, true, type);
	}

	public static String formatPercent(Double x, boolean enableThousandsSeparator) {
		return formatPercent(x, enableThousandsSeparator, null);
	}

	public static String formatPercent(Double x, boolean enableThousandsSeparator, WhiteSpaceType type) {
		return formatPercent(x, 2, enableThousandsSeparator, type);
	}

	public static String formatPercent(Double x, int currency) {
		return formatPercent(x, currency, null);
	}

	public static String formatPercent(Double x, int currency, WhiteSpaceType type) {
		return formatPercent(x, currency, currency, type);
	}

	public static String formatPercent(Double x, int currency, boolean enableThousandsSeparator) {
		return formatPercent(x, currency, enableThousandsSeparator, null);
	}

	public static String formatPercent(Double x, int currency, boolean enableThousandsSeparator, WhiteSpaceType type) {
		return formatPercent(x, currency, currency, enableThousandsSeparator, type);
	}

	public static String formatPercent(Double x, int minBit, int maxBit) {
		return formatPercent(x, minBit, maxBit, null);
	}

	public static String formatPercent(Double x, int minBit, int maxBit, WhiteSpaceType type) {
		return formatPercent(x, minBit, maxBit, true, type);
	}

	public static String formatPercent(Double x, int minBit, int maxBit, boolean enableThousandsSeparator) {
		return formatPercent(x, minBit, maxBit, enableThousandsSeparator, null);
	}

	public static String formatPercent(Double x, int minBit, int maxBit, boolean enableThousandsSeparator,
			WhiteSpaceType type) {
		if (x == null || x.isInfinite() || x.isNaN() || minBit < 0 || maxBit < 0 || minBit > maxBit)
			return type != null ? type.value() : WhiteSpaceType.BAR_STR.value();
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		df.setGroupingSize(0);
		df.setMinimumFractionDigits(minBit);
		df.setMaximumFractionDigits(maxBit);
		if (enableThousandsSeparator)
			df.setGroupingSize(3);
		return df.format(x * 100) + "%";
	}

	public static boolean equal(double x, double y) {
		return equal(x, y, DEFAULT_ZERO_THRESHOLD);
	}

	public static boolean lessThan(double x, double y) {
		return lessThan(x, y, DEFAULT_ZERO_THRESHOLD);
	}

	public static boolean moreThan(double x, double y) {
		return moreThan(x, y, DEFAULT_ZERO_THRESHOLD);
	}

	public static boolean lessThanOrEqual(double x, double y) {
		return lessThanOrEqual(x, y, DEFAULT_ZERO_THRESHOLD);
	}

	public static boolean moreThanOrEqual(double x, double y) {
		return moreThanOrEqual(x, y, DEFAULT_ZERO_THRESHOLD);
	}

	public static boolean equal(double x, double y, double zeroThreshold) {
		return Math.abs(x - y) <= Math.abs(zeroThreshold);
	}

	public static boolean lessThan(double x, double y, double zeroThreshold) {
		return x - y < -Math.abs(zeroThreshold);
	}

	public static boolean moreThan(double x, double y, double zeroThreshold) {
		return x - y > Math.abs(zeroThreshold);
	}

	public static boolean lessThanOrEqual(double x, double y, double zeroThreshold) {
		return x - y <= Math.abs(zeroThreshold);
	}

	public static boolean moreThanOrEqual(double x, double y, double zeroThreshold) {
		return x - y >= -Math.abs(zeroThreshold);
	}

	public static double roundHalfUp(Double value) {
		return roundHalfUp(value, 2);
	}

	public static double roundHalfUp(Double value, int num) {
		if (value == null || value.isNaN() || value.isInfinite())
			return 0d;
		BigDecimal bg = new BigDecimal(value);
		return bg.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}