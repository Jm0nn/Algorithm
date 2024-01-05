package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G1_1799_비숍 {

	static int n, maxB, maxW, black, white;
	static int[][] board;
	static List<int[]> blackList, whiteList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		blackList = new ArrayList<>();
		whiteList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) {
					if ((i + j) % 2 == 0)
						blackList.add(new int[] { i, j });
					else
						whiteList.add(new int[] { i, j });
				}
			}
		}

		black = blackList.size();
		white = whiteList.size();

		recurB(0, 0);
		recurW(0, 0);

		System.out.println(maxB + maxW);
	}

	static void recurB(int depth, int cnt) {
		if (black - depth + cnt < maxB)
			return;

		if (depth == black) {
			if (maxB < cnt)
				maxB = cnt;
			return;
		}

		int[] cur = blackList.get(depth);
		boolean possible = true;

		int nx = cur[0];
		int ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx--][ny--] == 2)
				possible = false;
		}

		nx = cur[0];
		ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx++][ny++] == 2)
				possible = false;
		}

		nx = cur[0];
		ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx--][ny++] == 2)
				possible = false;
		}

		nx = cur[0];
		ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx++][ny--] == 2)
				possible = false;
		}

		if (possible) {
			board[cur[0]][cur[1]] = 2;
			recurB(depth + 1, cnt + 1);
			board[cur[0]][cur[1]] = 1;
		}

		recurB(depth + 1, cnt);
	}

	static void recurW(int depth, int cnt) {
		if (white - depth + cnt < maxW)
			return;

		if (depth == white) {
			if (maxW < cnt)
				maxW = cnt;
			return;
		}

		int[] cur = whiteList.get(depth);
		boolean possible = true;

		int nx = cur[0];
		int ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx--][ny--] == 2)
				possible = false;
		}

		nx = cur[0];
		ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx++][ny++] == 2)
				possible = false;
		}

		nx = cur[0];
		ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx--][ny++] == 2)
				possible = false;
		}

		nx = cur[0];
		ny = cur[1];

		while (possible) {
			if (0 > nx || nx >= n || 0 > ny || ny >= n)
				break;

			if (board[nx++][ny--] == 2)
				possible = false;
		}

		if (possible) {
			board[cur[0]][cur[1]] = 2;
			recurW(depth + 1, cnt + 1);
			board[cur[0]][cur[1]] = 1;
		}

		recurW(depth + 1, cnt);
	}

}
