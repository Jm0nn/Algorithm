package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 게임판의 시작지점에서 목표지점까지 도달할 수 있는 경로의 개수를 구하는 문제
public class S1_1890_점프 {

	static int n; // 게임판 크기
	static int[][] board; // 게임판 배열
	static long[][] dp; // dp 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		dp = new long[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1; // dp -1로 초기화
			}
		}

		System.out.println(recur(0, 0));

		br.close();
	}

	static long recur(int r, int c) {

		// 목표에 도달했다면 1 리턴
		if (r == n - 1 && c == n - 1)
			return 1;

		// 현재 dp가 -1이 아니면 방문했던 곳이므로 현재 dp값 리턴
		if (dp[r][c] != -1)
			return dp[r][c];

		dp[r][c] = 0; // 현재 dp 0으로 초기화

		for (int d = 0; d < 2; d++) {

			// 새로운 좌표
			int nr = r;
			int nc = c;

			if (d == 0) // 아래로 이동
				nr += board[r][c];
			else // 우측으로 이동
				nc += board[r][c];

			// 새로운 좌표가 맵을 벗어나지 않으면 탐색
			if (nr < n && nc < n)
				dp[r][c] += recur(nr, nc); // 현재 좌표에 새로운 좌표로 다시 탐색하여 값을 더해줌
		}

		return dp[r][c]; // 현재 좌표 리턴
	}
}
