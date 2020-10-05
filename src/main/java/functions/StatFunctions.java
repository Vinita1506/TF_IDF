package functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatFunctions {

	public double mean(List<Integer> series) {
		double mean = 0.0;
		int sum = 0;
		for (int i = 0; i < series.size(); i++) {
			sum += series.get(i);
		}
		mean = sum / series.size();
		System.out.println("Mean is:" + mean);
		return mean;

	}

	public int mode(List<Integer> series) {
		Collections.sort(series);
		Object[] s2 = series.toArray();
		int maxCount = 0, maxValue = 0;
		for (int i = 0; i < s2.length; ++i) {
			int count = 0;
			for (int j = 0; j < s2.length; ++j) {
				if (s2[i] == s2[j])
					++count;
			}
			if (count > maxCount) {
				maxCount = count;
				maxValue = (int)s2[i];
			}

		}
		System.out.println("Mode is:" + maxValue);
		return maxValue;
	}

	public double median(List<Integer> series) {
		double median = 0;
		Collections.sort(series);
		if (series.size() % 2 == 0) {
			median = (series.get((series.size() - 1) / 2) + series.get(series.size() / 2)) / 2;

		} else {
			median = series.get((series.size() + 1) / 2);

		}
		System.out.println("Meadian is:" + median);
		return median;

	}

	public double variance(List<Integer> series) {
		double mean = mean(series);
		double tempVariable = 0;
		List<Double> variancelist = new ArrayList<Double>();
		for (int i = 0; i < series.size(); i++) {
			int value = series.get(i);
			double diff = mean - value;
			tempVariable = diff * diff;
			variancelist.add(tempVariable);
		}
		tempVariable = 0;
		for (int i = 0; i < variancelist.size(); i++) {
			tempVariable += (double) variancelist.get(i);
		}
		double result = tempVariable / variancelist.size();

		System.out.println("Variance is:" + result);
		return result;
	}

	public double standardDeviation(List<Integer> series) {
		double std = Math.sqrt(variance(series));
		System.out.println("Standard deviation is:" + std);
		return std;
	}

	public List<Double> outlier(List<Integer> series) {
		List<Integer> d1 = new ArrayList<Integer>();
		List<Integer> d2 = new ArrayList<Integer>();
		Collections.sort(series);
		if (series.size() % 2 == 0) {
			d1 = series.subList(0, series.size() / 2);
			d2 = series.subList(series.size() / 2, series.size());

		} else {
			d1 = series.subList(0, series.size() / 2);
			d2 = series.subList(series.size() / 2 + 1, series.size());
		}
		double q1 = median(d1);
		double q3 = median(d2);
		double iqr = q3 - q1;
		double lowerFence = q1 - 1.5 * iqr;
		System.out.println(lowerFence);
		double upperFence = q3 + 1.5 * iqr;
		System.out.println(upperFence);
		List<Double> outlier = new ArrayList<Double>();
		for (int i = 0; i < series.size(); i++) {
			if (series.get(i) < lowerFence || series.get(i) > upperFence)
				outlier.addAll(series.get(i), outlier);
		}
		System.out.println("Outlier is:" + outlier);
		return outlier;

	}

	public String createHistogram(List<Integer> series) {

		String output = "Element\tValue\tHistogram";
		Object[] s2 = series.toArray();
		for (int counter = 0; counter < s2.length; counter++) {
			output += "\n" + counter + "\t" + s2[counter] + "\t";

			for (int stars = 0; stars < (int)s2[counter]; stars++) {
				output += "*";
			}
		}
		System.out.println(output);
		return output;
	}
}
