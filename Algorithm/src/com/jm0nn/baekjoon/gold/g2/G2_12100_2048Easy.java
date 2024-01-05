package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_12100_2048Easy {

	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}

		dfs(0, n, board, -1);

		System.out.println(max);

		br.close();
	}

	static void dfs(int depth, int n, int[][] before, int move) {

		if (depth == 5) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					max = Math.max(max, before[i][j]);

			return;
		}

		int[][] cur = new int[n][n];

		for (int d = 0; d < 4; d++) {

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					cur[i][j] = before[i][j];

			cur = move(n, cur, d);

			dfs(depth + 1, n, cur, d);
		}

	}

	static int[][] move(int n, int[][] board, int d) {

		switch (d) {
		case 0:
			for (int i = 0; i < n; i++) {
				int idx = 0;
				int block = 0;
				for (int j = 0; j < n; j++) {
					if (board[j][i] != 0) {
						if (block == board[j][i]) {
							board[idx - 1][i] = block * 2;
							block = 0;
							board[j][i] = 0;
						} else {
							block = board[j][i];
							board[j][i] = 0;
							board[idx][i] = block;
							idx++;
						}
					}
				}
			}
			break;

		case 1:
			for (int i = 0; i < n; i++) {
				int idx = n - 1;
				int block = 0;
				for (int j = n - 1; j >= 0; j--) {
					if (board[j][i] != 0) {
						if (block == board[j][i]) {
							board[idx + 1][i] = block * 2;
							block = 0;
							board[j][i] = 0;
						} else {
							block = board[j][i];
							board[j][i] = 0;
							board[idx][i] = block;
							idx--;
						}
					}
				}
			}
			break;

		case 2:
			for (int i = 0; i < n; i++) {
				int idx = 0;
				int block = 0;
				for (int j = 0; j < n; j++) {
					if (board[i][j] != 0) {
						if (block == board[i][j]) {
							board[i][idx - 1] = block * 2;
							block = 0;
							board[i][j] = 0;
						} else {
							block = board[i][j];
							board[i][j] = 0;
							board[i][idx] = block;
							idx++;
						}
					}
				}
			}
			break;

		case 3:
			for (int i = 0; i < n; i++) {
				int idx = n - 1;
				int block = 0;
				for (int j = n - 1; j >= 0; j--) {
					if (board[i][j] != 0) {
						if (block == board[i][j]) {
							board[i][idx + 1] = block * 2;
							block = 0;
							board[i][j] = 0;
						} else {
							block = board[i][j];
							board[i][j] = 0;
							board[i][idx] = block;
							idx--;
						}
					}
				}
			}
			break;
		}

		return board;
	}

}
