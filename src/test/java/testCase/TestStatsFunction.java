package testCase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import functions.StatFunctions;

public class TestStatsFunction {
	List<Integer> series = new ArrayList<Integer>();
	StatFunctions statFunction = new StatFunctions();

	@BeforeTest()
	public void populateData() {
		series.addAll(Arrays.asList(1, 2, 3, 4, 1, 2, 1));
	}

	@Test
	public void testMean() {
		double actual = statFunction.mean(series);
		assertNotNull(actual);
		assertEquals(actual, 2);
	}

	@Test
	public void testMedian() {
		double actual = statFunction.median(series);
		assertNotNull(actual);
		assertEquals(actual, 2);

	}

	@Test
	public void testMode() {
		double actual = statFunction.mode(series);
		assertNotNull(actual);
		assertEquals(actual, 1);

	}

	@Test
	public void testVariance() {
		double actual = statFunction.variance(series);
		assertNotNull(actual);
		assertEquals(actual, 1.1428571428571428);
	}

	@Test
	public void testStddeviation() {
		double actual = statFunction.standardDeviation(series);
		assertNotNull(actual);
		assertEquals(actual, 1.0690449676496976);
	}

	@Test
	public void testHistogram() {
		String actual = statFunction.createHistogram(series);
		assertNotNull(actual);
	}

	@Test
	public void testOutlier() {
		List<Double> actual = statFunction.outlier(series);
		System.out.println(actual);
		assertNotNull(actual);

	}

}
