package com.example.Public;

import java.util.Random;

public class RandomString {
	public char[] fails,passes;
	
	public RandomString(String convertString, int MAX) {
		Random r = new Random();

		int length = MAX - convertString.length();
		fails= new char[length];

		// Chen cac ki tu ngau nhien
		for (int i = 0; i < length; i++) {
			char randomChar = (char) (r.nextInt(26) + 'A');//A=65,B=66,C=67
			fails[i] = randomChar;
			convertString += randomChar;
		}

		passes = convertString.toCharArray();

		// Tron ngau nhien
		for (int k = 0; k < MAX * 2; k++) {
			int x = r.nextInt(passes.length - 1);
			int y = r.nextInt(passes.length - 1);

			char X = passes[x];

			passes[x] = passes[y];
			passes[y] = X;
		}
	}
	
}
