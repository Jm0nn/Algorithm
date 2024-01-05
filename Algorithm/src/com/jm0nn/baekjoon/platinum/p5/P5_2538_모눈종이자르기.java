package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5_2538_모눈종이자르기 {

	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int n, m, k;
	static int cnt = 1;
	static int max = Integer.MIN_VALUE;
	static int[] px, py;
	static int[][] paper;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		paper = new int[n][m];

		k = Integer.parseInt(br.readLine());
		px = new int[k];
		py = new int[k];

		st = new StringTokenizer(br.readLine());
		px[0] = Integer.parseInt(st.nextToken());
		py[0] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			px[i] = Integer.parseInt(st.nextToken());
			py[i] = Integer.parseInt(st.nextToken());

			if (px[i] == px[i - 1]) {
				if (py[i] > py[i - 1]) {
					for (int j = py[i - 1]; j < py[i]; j++)
						paper[px[i] - 1][j] = -1;
				} else {
					for (int j = py[i]; j < py[i - 1]; j++)
						paper[px[i]][j] = -1;
				}
			} else {
				if (px[i] > px[i - 1]) {
					for (int j = px[i - 1]; j < px[i]; j++)
						paper[j][py[i]] = -1;
				} else {
					for (int j = px[i]; j < px[i - 1]; j++)
						paper[j][py[i] - 1] = -1;
				}
			}
		}

		if (px[0] == px[k - 1]) {
			if (py[0] > py[k - 1]) {
				for (int j = py[k - 1]; j < py[0]; j++)
					paper[px[0] - 1][j] = -1;
			} else {
				for (int j = py[0]; j < py[k - 1]; j++)
					paper[px[0]][j] = -1;
			}
		} else {
			if (px[0] > px[k - 1]) {
				for (int j = px[k - 1]; j < px[0]; j++)
					paper[j][py[0]] = -1;
			} else {
				for (int j = px[0]; j < px[k - 1]; j++)
					paper[j][py[0] - 1] = -1;
			}
		}

//		for (int i = 1; i < n - 1; i++) {
//			int s = 0;
//			for (int j = 1; j < m - 1; j++) {
//				if (paper[i][j + 1] != -1 && paper[i][j] == -1) {
//					s = j;
//					break;
//				}
//			}
//			
//			int f = 0;
//			for (int j = s + 1; j < m - 1; j++) {
//				if (paper[i][j] == -1) {
//					f = j;
//					break;
//				}
//			}
//			
//			for (int j = s; j < f; j++)
//				paper[i][j] = -1;
//		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.printf("%3d", paper[i][j]);
			}
			System.out.println();
		}

	}
}
