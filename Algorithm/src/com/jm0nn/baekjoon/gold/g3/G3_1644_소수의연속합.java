package com.jm0nn.baekjoon.gold.g3;

import java.io.*;

public class G3_1644_소수의연속합 {

	static final int MAX_NUM = 4_000_000;

	static boolean[] noPrime = new boolean[MAX_NUM + 1];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		prime();

		int n = Integer.parseInt(br.readLine());

		int left = 1;
		int right = 2;
		int sum = 0;
		int cnt = 0;

		while (left < right && right <= MAX_NUM) {
			if (sum < n) {
				while (right <= MAX_NUM && noPrime[right]) ++right;
				sum += right++;
			} else {
				if (sum == n) ++cnt;
				while (left < MAX_NUM && noPrime[++left]) ;
				sum -= left;
			}
		}

		System.out.println(cnt);
	}

	static void prime() {
		for (int i = 2; i * i <= MAX_NUM; ++i) {
			if (noPrime[i]) continue;

			for (int j = i + i; j <= MAX_NUM; j += i)
				noPrime[j] = true;
		}
	}

}
