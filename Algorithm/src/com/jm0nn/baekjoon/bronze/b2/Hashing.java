package com.jm0nn.baekjoon.bronze.b2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hashing {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long r = 31;
		final long M = 1234567891;
		long[] R = new long[50];
		R[0] = 1;
		for (int i = 1; i < 50; i++)
			R[i] = (R[i - 1] * r) % M;
		long H = 0;
		int L = Integer.parseInt(br.readLine());
		String s = br.readLine();
		for (int i = 0; i < L; i++)
			H = (H + ((s.charAt(i) - 'a' + 1) * R[i]) % M) % M;
		System.out.println(H);
	}
}
