package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G2_2749_피보나치수3 {

	static final long MOD = 1_000_000L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());

		long[][] res = fibo(n);

		System.out.println(res[1][0]);
	}

	static long[][] fibo(long n) {
		long[][] BASE = new long[][] { { 1L, 0L }, { 0L, 1L } };
		long[][] Q = new long[][] { { 1L, 1L }, { 1L, 0L } };

		while (n > 0L) {
			if (n % 2L == 1L)
				BASE = mul(BASE, Q);
			Q = mul(Q, Q);
			n /= 2L;
		}

		return BASE;
	}

	static long[][] mul(long[][] m1, long[][] m2) {
		long[][] res = new long[2][2];

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 2; ++k) {
					res[i][j] = (res[i][j] + (m1[i][k] * m2[k][j]) % MOD) % MOD;
				}
			}
		}

		return res;
	}

}
