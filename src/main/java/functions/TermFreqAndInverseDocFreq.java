package functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TermFreqAndInverseDocFreq {
	private static class CountWithFileName {
		private String fileName;
		private double count;
		private double tf_Idf;

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}

		public double getTf_Idf() {
			return tf_Idf;
		}

		public void setTf_Idf(double tf_Idf) {
			this.tf_Idf = tf_Idf;
		}
	}

	private static long totalNoOfWords;
	static HashMap<String, CountWithFileName> wordFileCountMap = new HashMap<String, CountWithFileName>();
	static HashMap<String, Double> wordFreqMap = new HashMap<String, Double>();

	public static void main(String[] args) throws IOException {
		TermFreqAndInverseDocFreq readFiles = new TermFreqAndInverseDocFreq();
		File folder = new File("D://Term_Frequency");
		File[] files = folder.listFiles();
		assert files != null;
		double fileSize = files.length;
		for (File file : files) {
			readFiles.buildWordFrequencyMap(file);
		}
		readFiles.buildNoOfDocumentsContainingWords(wordFreqMap, files);
		readFiles.calculateAndDisplayTF_IDFForWords(fileSize);
	}

	private void calculateAndDisplayTF_IDFForWords(double fileSize) {
		for (String s : wordFileCountMap.keySet()) {
			CountWithFileName countWithFileName = wordFileCountMap.get(s);
			double inverseTermFrequncy = Math.log(fileSize / countWithFileName.getCount());
			double termFrequency = wordFreqMap.get(s) / totalNoOfWords;
			countWithFileName.setTf_Idf(inverseTermFrequncy * termFrequency);
		}
		System.out.println("Total number of words in document : " + totalNoOfWords);
		for (Map.Entry<String, CountWithFileName> entry : wordFileCountMap.entrySet()) {
			System.out.println(entry.getKey() + " TF_IDF is : " + entry.getValue().getTf_Idf());
		}
	}

	private void buildWordFrequencyMap(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String s2;
		while ((line = br.readLine()) != null) {
			String[] string = line.split(" ");
			for (String s1 : string) {
				s2 = getTheOriginalString(s1);
				if (wordFreqMap.containsKey(s2)) {
					double count = wordFreqMap.get(s2);
					wordFreqMap.put(s2, ++count);
				} else {
					wordFreqMap.put(s2, 1D);
				}
				++totalNoOfWords;
			}
		}
	}

	private void buildNoOfDocumentsContainingWords(HashMap<String, Double> words, File[] files) {
		for (File file : files) {
			FileReader fr;
			String line;
			try {
				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while ((line = br.readLine()) != null) {
					for (String str : words.keySet()) {
						if (line.contains(str) && !ifForSameFile(str, file.getName())) {
							if (wordFileCountMap.containsKey(str)) {
								wordFileCountMap.get(str).setCount(wordFileCountMap.get(str).getCount() + 1);
								wordFileCountMap.get(str).setFileName(file.getName());
							} else {
								CountWithFileName countWithFileName = new CountWithFileName();
								countWithFileName.setCount(countWithFileName.getCount() + 1);
								countWithFileName.setFileName(file.getName());
								wordFileCountMap.put(str, countWithFileName);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static boolean ifForSameFile(String str, String fileName) {
		if (wordFileCountMap.containsKey(str)) {
			return wordFileCountMap.get(str).getFileName().equals(fileName);
		}
		return false;
	}

	private static String getTheOriginalString(String s1) {
		return s1.trim().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
	}

}
