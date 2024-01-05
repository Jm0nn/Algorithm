package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_2096_내려가기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] maxArr = new int[2][3];
		int[][] minArr = new int[2][3];

		int[] arr = new int[3];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; ++i)
			maxArr[0][i] = minArr[0][i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < n; ++i) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; ++j)
				arr[j] = Integer.parseInt(st.nextToken());

			for (int j = 0; j < 3; ++j) {
				int l = j - 1;

				if (l < 0)
					l = 0;

				int r = j + 1;

				if (r > 2)
					r = 2;

				maxArr[i % 2][j] = Math.max(maxArr[(i - 1) % 2][l] + arr[j],
						Math.max(maxArr[(i - 1) % 2][j] + arr[j], maxArr[(i - 1) % 2][r] + arr[j]));
				minArr[i % 2][j] = Math.min(minArr[(i - 1) % 2][l] + arr[j],
						Math.min(minArr[(i - 1) % 2][j] + arr[j], minArr[(i - 1) % 2][r] + arr[j]));
			}
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < 3; ++i) {
			max = Math.max(max, maxArr[1 - n % 2][i]);
			min = Math.min(min, minArr[1 - n % 2][i]);
		}

		System.out.printf("%d %d", max, min);
	}

}
