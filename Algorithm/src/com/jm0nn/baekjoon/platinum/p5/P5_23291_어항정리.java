package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마법사 상어가 어항을 정리할 때 각 어항의 물고기 수 차이가 k 이하가 되게 하는 정리 횟수를 구하는 문제
public class P5_23291_어항정리 {

	static int n, k; // 어항의 수, 정리 조건
	static int[][] bowl, tmp; // 어항 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		bowl = new int[n][n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			bowl[0][i] = Integer.parseInt(st.nextToken());

		int cnt = 0; // 어항 정리 횟수

		while (true) {
			// 어항 속 물고기 수 비교
			int max = bowl[0][0];
			int min = bowl[0][0];

			for (int i = 1; i < n; i++) {
				if (max < bowl[0][i])
					max = bowl[0][i];
				if (min > bowl[0][i])
					min = bowl[0][i];
			}

			// 물고기 수의 최댓값과 최솟값의 차이가 k 이하면 어항 정리 멈춤
			if (max - min <= k)
				break;

			// 어항 정리 시작
			cnt++;

			// 물고기의 수가 최소인 어항에 물고기 한 마리씩 넣음
			for (int i = 0; i < n; i++) {
				if (bowl[0][i] == min)
					bowl[0][i]++;
			}

			// 공중 부양
			int row = 1;
			int col = 1;
			int idx = 0;

			while (true) {
				if (idx + col + row - 1 >= n)
					break;

				for (int i = 0; i < row; i++) {
					for (int j = idx; j < idx + col; j++) {
						bowl[idx - j + col][idx + col + i] = bowl[i][j];
						bowl[i][j] = 0;
					}
				}

				idx += col;

				if (row == col)
					row++;
				else
					col++;
			}

			// 물고기 수 조절
			tmp = new int[n][n];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					tmp[i][j] = bowl[i][j];

			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (bowl[i][j] == 0)
						continue;

					int ni = i + 1;
					int nj = j - 1;

					if (ni < n && bowl[ni][j] != 0) {
						if (bowl[i][j] > bowl[ni][j]) {
							int diff = (bowl[i][j] - bowl[ni][j]) / 5;
							tmp[i][j] -= diff;
							tmp[ni][j] += diff;
						} else {
							int diff = (bowl[ni][j] - bowl[i][j]) / 5;
							tmp[ni][j] -= diff;
							tmp[i][j] += diff;
						}
					}

					if (nj >= 0 && bowl[i][nj] != 0) {
						if (bowl[i][j] > bowl[i][nj]) {
							int diff = (bowl[i][j] - bowl[i][nj]) / 5;
							tmp[i][j] -= diff;
							tmp[i][nj] += diff;
						} else {
							int diff = (bowl[i][nj] - bowl[i][j]) / 5;
							tmp[i][nj] -= diff;
							tmp[i][j] += diff;
						}
					}
				}
			}

			bowl = tmp;

			// 어항 일렬로 놓기
			idx = 0;
			for (int j = 0; j < n; j++) {
				if (bowl[0][j] == 0)
					continue;

				for (int i = 0; i < n; i++) {
					if (bowl[i][j] == 0)
						continue;

					bowl[0][idx++] = bowl[i][j];

					if (i > 0)
						bowl[i][j] = 0;
				}
			}

			// 다시 공중부양
			for (int j = 0; j < n / 2; j++) {
				bowl[1][n - j - 1] = bowl[0][j];
				bowl[0][j] = 0;
			}

			for (int j = n / 2; j < n * 3 / 4; j++) {
				for (int i = 0; i < 2; i++) {
					bowl[3 - i][n * 3 / 2 - j - 1] = bowl[i][j];
					bowl[i][j] = 0;
				}
			}

			// 다시 물고기 수 조절
			tmp = new int[n][n];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					tmp[i][j] = bowl[i][j];

			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (bowl[i][j] == 0)
						continue;

					int ni = i + 1;
					int nj = j - 1;

					if (ni < n && bowl[ni][j] != 0) {
						if (bowl[i][j] > bowl[ni][j]) {
							int diff = (bowl[i][j] - bowl[ni][j]) / 5;
							tmp[i][j] -= diff;
							tmp[ni][j] += diff;
						} else {
							int diff = (bowl[ni][j] - bowl[i][j]) / 5;
							tmp[ni][j] -= diff;
							tmp[i][j] += diff;
						}
					}

					if (nj >= 0 && bowl[i][nj] != 0) {
						if (bowl[i][j] > bowl[i][nj]) {
							int diff = (bowl[i][j] - bowl[i][nj]) / 5;
							tmp[i][j] -= diff;
							tmp[i][nj] += diff;
						} else {
							int diff = (bowl[i][nj] - bowl[i][j]) / 5;
							tmp[i][nj] -= diff;
							tmp[i][j] += diff;
						}
					}
				}
			}

			bowl = tmp;

			// 어항 일렬로 놓기
			idx = 0;
			for (int j = 0; j < n; j++) {
				if (bowl[0][j] == 0)
					continue;

				for (int i = 0; i < n; i++) {
					if (bowl[i][j] == 0)
						continue;

					bowl[0][idx++] = bowl[i][j];

					if (i > 0)
						bowl[i][j] = 0;
				}
			}
		}

		System.out.println(cnt);
	}

}
