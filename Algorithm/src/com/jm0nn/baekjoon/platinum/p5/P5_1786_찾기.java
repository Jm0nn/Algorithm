package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P5_1786_찾기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();

		int n = T.length;
		int m = P.length;

		int[] table = new int[m];

		for (int i = 1, j = 0; i < m; ++i) {
			while (j > 0 && P[i] != P[j])
				j = table[j - 1];

			if (P[i] == P[j])
				table[i] = ++j;
		}

		int cnt = 0;
		StringBuilder sb = new StringBuilder();

		for (int i = 0, j = 0; i < n; ++i) {
			while (j > 0 && T[i] != P[j])
				j = table[j - 1];

			if (T[i] == P[j]) {
				if (j == m - 1) {
					++cnt;
					sb.append(i - j + 1).append(' ');
					j = table[j];
				} else {
					++j;
				}
			}
		}

		System.out.println(cnt);
		System.out.println(sb);
	}

}
