package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G1_11689_GCDnk1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());

		long euler = n;

		for (long p = 2; p * p <= n; ++p) {
			if (n % p == 0) {
				euler -= euler / p;

				while (n % p == 0)
					n /= p;
			}
		}

		if (n > 1)
			euler -= euler / n;

		System.out.println(euler);
	}

}
