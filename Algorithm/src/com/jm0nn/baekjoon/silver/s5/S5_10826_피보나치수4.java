package com.jm0nn.baekjoon.silver.s5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class S5_10826_피보나치수4 {

	static BigInteger[] fibo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		BigInteger[] fibo = new BigInteger[n + 1];
		fibo[0] = BigInteger.valueOf(0);

		if (n > 0)
			fibo[1] = BigInteger.valueOf(1);
		
		for (int i = 2; i <= n; ++i)
			fibo[i] = fibo[i - 1].add(fibo[i - 2]);

		System.out.println(fibo[n]);
	}

}
