package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S2_9658_돌게임4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		if (n % 7 == 1 || n % 7 == 3)
			System.out.println("CY");
		else
			System.out.println("SK");

		br.close();
	}
}
