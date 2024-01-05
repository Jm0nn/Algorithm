package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G2_15824_너봄에는캡사이신이맛있단다 {

	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] skovil = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			skovil[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(skovil);

		long ans = 0;

		for (int i = 0; i < n; ++i)
			ans = (ans + skovil[i] * (pow(i) - pow(n - i - 1)) % MOD) % MOD;

		if (ans < 0)
			ans += MOD;

		System.out.println(ans);
	}

	static long pow(int exp) {
		long base = 2;
		long ret = 1;

		while (exp > 0) {
			if (exp % 2 == 1)
				ret = ret * base % MOD;

			base = base * base % MOD;
			exp >>= 1;
		}

		return ret;
	}

}
