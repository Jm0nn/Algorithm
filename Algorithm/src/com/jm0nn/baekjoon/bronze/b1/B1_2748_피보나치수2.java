package com.jm0nn.baekjoon.bronze.b1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1_2748_피보나치수2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		long[] fibo = new long[n + 1];
		
		fibo[1] = 1;
		
		for (int i = 2; i <= n; i++)
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		
		System.out.println(fibo[n]);
		
		br.close();
	}
}
