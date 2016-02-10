package chapter03.exercise.e14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

	public Integer calcSum(String filepath) throws IOException {
		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
			public Integer doSomthingWithReader(BufferedReader br) throws IOException {
				Integer sum = 0;
				String line = "";
				while ((line = br.readLine()) != null) {
					sum += Integer.valueOf(line);
				}
				return sum;
			}
		};
		return fileReaderTemplate(filepath, sumCallback);
	}
	
	public Integer calcMultifly(String filepath) throws IOException {
		BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
			public Integer doSomthingWithReader(BufferedReader br) throws IOException {
				Integer multiply = 1;
				String line = "";
				while ((line = br.readLine()) != null) {
					multiply *= Integer.valueOf(line);
				}
				return multiply;
			}
		};
		return fileReaderTemplate(filepath, multiplyCallback);
	}

	public Integer fileReaderTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			int ret = callback.doSomthingWithReader(br);
			return ret;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
					throw e;
				}
			}
		}
	}

}
