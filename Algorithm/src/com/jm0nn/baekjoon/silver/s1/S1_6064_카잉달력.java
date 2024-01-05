package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class S1_6064_카잉달력 {

	static int M, N, x, y;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			sb.append(find()).append('\n');
		}

		System.out.println(sb);
	}

	static int find() {
		int k = x;
		int lcm = lcm();

		while (k <= lcm) {
			if ((k - x) % M == 0 && (k - y) % N == 0)
				return k;

			k += M;
		}

		return -1;
	}

	static int lcm() {
		BigInteger a = BigInteger.valueOf(M);
		BigInteger b = BigInteger.valueOf(N);

		return M * N / a.gcd(b).intValue();
	}

}
