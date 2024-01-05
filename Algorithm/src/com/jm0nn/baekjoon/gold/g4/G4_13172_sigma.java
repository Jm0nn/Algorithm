package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_13172_sigma {

	static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int M = Integer.parseInt(br.readLine());
		long ans = 0;

		for (int i = 0; i < M; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long S = Long.parseLong(st.nextToken());

			ans = (ans + (S * mul(N, MOD - 2)) % MOD) % MOD;
		}

		System.out.println(ans);

	}

	static long mul(long n, long pow) {
		if (pow == 1)
			return n;

		long half = pow / 2;

		long tmp = mul(n, half);

		if (pow % 2 == 0)
			return (tmp * tmp) % MOD;
		else
			return (((tmp * tmp) % MOD) * n) % MOD;
	}

}
