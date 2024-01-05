package com.jm0nn.baekjoon.bronze.b5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B5_2754_학점계산 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		double n = 0.0;
		switch (s) {
		case "A+":
			n = 4.3;
			break;
		case "A0":
			n = 4.0;
			break;
		case "A-":
			n = 3.7;
			break;
		case "B+":
			n = 3.3;
			break;
		case "B0":
			n = 3.0;
			break;
		case "B-":
			n = 2.7;
			break;
		case "C+":
			n = 2.3;
			break;
		case "C0":
			n = 2.0;
			break;
		case "C-":
			n = 1.7;
			break;
		case "D+":
			n = 1.3;
			break;
		case "D0":
			n = 1.0;
			break;
		case "D-":
			n = 0.7;
			break;
		}
		System.out.println(n);
	}
}
