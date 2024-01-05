package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G2_27435_파도반수열2 {

	static final long MOD = 998_244_353L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			long n = Long.parseLong(br.readLine());
			sb.append(padovan(n)[2][1]).append("\n");
		}

		System.out.println(sb);
	}

	static long[][] padovan(long n) {
		long[][] BASE = new long[][] { { 1L, 0L, 0L }, { 0L, 1L, 0L }, { 0L, 0L, 1L } };
		long[][] Q = new long[][] { { 0L, 1L, 0L }, { 0L, 0L, 1L }, { 1L, 1L, 0L } };

		while (n > 0L) {
			if (n % 2L == 1L)
				BASE = mul(BASE, Q);
			Q = mul(Q, Q);
			n /= 2L;
		}

		return BASE;
	}

	static long[][] mul(long[][] m1, long[][] m2) {
		long[][] res = new long[3][3];

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				for (int k = 0; k < 3; ++k) {
					res[i][j] = (res[i][j] + (m1[i][k] * m2[k][j]) % MOD) % MOD;
				}
			}
		}

		return res;
	}

}
