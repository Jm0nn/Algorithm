package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_1987_알파벳 {

	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int r, c, max;
	static char[][] board;
	static boolean[] visit = new boolean[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		board = new char[r][c];

		for (int i = 0; i < r; ++i) {
			String input = br.readLine();
			for (int j = 0; j < c; ++j)
				board[i][j] = input.charAt(j);
		}

		visit[board[0][0] - 'A'] = true;
		dfs(1, 0, 0);

		System.out.println(max);
	}

	static void dfs(int cnt, int x, int y) {
		if (max < cnt)
			max = cnt;

		for (int d = 0; d < 4; ++d) {
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];

			if (0 > nx || nx >= r || 0 > ny || ny >= c)
				continue;

			int alpha = board[nx][ny] - 'A';

			if (visit[alpha])
				continue;

			visit[alpha] = true;
			dfs(cnt + 1, nx, ny);
			visit[alpha] = false;
		}
	}

}
