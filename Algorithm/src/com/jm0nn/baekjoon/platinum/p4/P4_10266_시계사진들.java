package com.jm0nn.baekjoon.platinum.p4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 두 시계가 같은 시각을 나타내는지 맞추는 문제
public class P4_10266_시계사진들 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int SIZE = 360_000;

		int n = Integer.parseInt(br.readLine()); // 시계 바늘의 수
		int[] clock1 = new int[SIZE * 2]; // 시계1
		int[] clock2 = new int[SIZE]; // 시계2

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			int num = Integer.parseInt(st.nextToken());
			// 패턴이 2번 반복됨
			clock1[num] = 1;
			clock1[SIZE + num] = 1;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			int num = Integer.parseInt(st.nextToken());
			clock2[num] = 1;
		}

		// KMP 알고리즘
		int len1 = clock1.length;
		int len2 = clock2.length;

		int[] table = new int[len2];

		for (int i = 1, idx = 0; i < len2; ++i) {
			while (idx > 0 && clock2[i] != clock2[idx])
				idx = table[idx - 1];

			if (clock2[i] == clock2[idx])
				table[i] = ++idx;
		}

		boolean possible = false; // 같은 시계인지 여부

		for (int i = 0, idx = 0; i < len1; ++i) {
			while (idx > 0 && clock1[i] != clock2[idx])
				idx = table[idx - 1];

			if (clock1[i] == clock2[idx]) {
				if (idx == len2 - 1) {
					possible = true;
					break;
				} else {
					++idx;
				}
			}
		}

		System.out.println(possible ? "possible" : "impossible");
	}
}
