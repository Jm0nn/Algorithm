package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P5_4354_문자열제곱 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String s;

		while (!(s = br.readLine()).equals(".")) {
			sb.append(kmp(s)).append('\n');
		}

		System.out.println(sb);
	}

	static int kmp(String s) {
		StringBuilder sb = new StringBuilder(s);

		int slen = sb.length();
		int n = 1;

		loop: while (sb.length() > 0) {
			String a = sb.toString();
			int alen = a.length();

			int[] table = new int[alen];

			for (int i = 1, idx = 0; i < alen; i++) {
				while (idx > 0 && a.charAt(i) != a.charAt(idx))
					idx = table[idx - 1];

				if (a.charAt(i) == a.charAt(idx)) {
					idx++;
					table[i] = idx;
				}
			}

			boolean possible = false;

			for (int i = 0, idx = 0; i < slen; i++) {
				while (idx > 0 && s.charAt(i) != a.charAt(idx))
					idx = table[idx - 1];

				if (s.charAt(i) == a.charAt(idx)) {
					if (idx == alen - 1) {
						possible = true;
						break;
					} else {
						idx++;
					}
				}
			}

			if (!possible)
				break loop;

			n++;
			sb.setLength(alen / 2);
		}

		return n;
	}

}
