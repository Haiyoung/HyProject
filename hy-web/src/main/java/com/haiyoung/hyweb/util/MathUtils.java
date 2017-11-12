package com.haiyoung.hyweb.util;

import java.util.List;

public class MathUtils {

	public static double sum(double[] array) {
		if (array == null || array.length == 0)
			return 0d;
		double sum = 0d;
		for (double a : array)
			sum += a;
		return sum;
	}

	public static double expectation(List<Double> array) {
		if (array == null || array.size() == 0)
			return 0;

		double sum = 0d;
		for (double x : array)
			sum += x;
		return sum / array.size();
	}

	public static double covariance(List<Double> arrayX, List<Double> arrayY) {
		if (arrayX == null || arrayY == null || arrayX.size() == 0
				|| arrayY.size() == 0 || arrayX.size() != arrayY.size())
			return 0;
		int n = arrayX.size();
		double sum = 0d;
		double expX = expectation(arrayX);
		double expY = expectation(arrayY);
		for (int i = 0; i < n; i++)
			sum += (arrayX.get(i) - expX) * (arrayY.get(i) - expY);
		return n > 1 ? sum / (n - 1) : sum;
	}

	public static double variance(List<Double> array) {
		if (array == null || array.size() == 0)
			return 0;
		int n = array.size();
		double sum = 0d;
		double exp = expectation(array);
		for (int i = 0; i < n; i++)
			sum += (array.get(i) - exp) * (array.get(i) - exp);
		return n > 1 ? (sum / (n - 1)) : sum;
	}

	public static double standardDeviation(List<Double> array) {
		return Math.sqrt(variance(array));
	}

}
