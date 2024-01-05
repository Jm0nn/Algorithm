package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S3_9461_파도반수열 {

	static long[] p = new long[101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		p();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(p[n]).append("\n");
		}

		System.out.println(sb);
	}

	static void p() {
		p[1] = 1;
		p[2] = 1;
		p[3] = 1;
		p[4] = 2;
		p[5] = 2;
		for (int i = 6; i <= 100; i++) {
			p[i] = p[i - 1] + p[i - 5];
		}
	}

}
