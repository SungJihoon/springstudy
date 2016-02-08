package chapter03.exercise.e13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

	public int calcSum(String filepath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		Integer sum = 0;
		String line = "";
		while ((line = br.readLine()) != null) {
			sum += Integer.valueOf(line);
		}
		br.close();
		return sum;
	}

}
