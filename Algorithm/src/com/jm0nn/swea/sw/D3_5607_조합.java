package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_5607_조합 {

	static final long MOD = 1_234_567_891L;

	static long[] factorial = new long[1000001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		factorial();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			long num = factorial[n];
			long denom = (factorial[r] * factorial[n - r]) % MOD;

			long ans = (num * pow(denom, MOD - 2)) % MOD;
			sb.append(ans).append('\n');
		}

		System.out.println(sb);
	}

	static void factorial() {
		long fac = 1L;

		for (int i = 1; i < 1000001; ++i) {
			fac = (fac * i) % MOD;
			factorial[i] = fac;
		}
	}

	static long pow(long n, long exp) {
		if (exp == 1)
			return n % MOD;

		long tmp = pow(n, exp / 2);

		if (exp % 2 == 1)
			return (((tmp * tmp) % MOD) * n) % MOD;
		else
			return (tmp * tmp) % MOD;
	}

}
