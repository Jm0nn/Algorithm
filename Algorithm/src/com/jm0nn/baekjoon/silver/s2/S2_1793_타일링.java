package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class S2_1793_타일링 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		BigInteger[] dp = new BigInteger[251];
		dp[0] = dp[1] = BigInteger.valueOf(1);

		for (int i = 2; i <= 250; i++)
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(BigInteger.valueOf(2)));

		String input;
		while ((input = br.readLine()) != null && input.length() > 0)
			sb.append(dp[Integer.parseInt(input)]).append('\n');

		System.out.println(sb);
	}
}
