package com.jm0nn.baekjoon.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class I_INK {

	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static class Pos {
		int x, y, dist;

		Pos(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	static int I, N, K;
	static int inkCharge, jumpCnt;
	static Pos square;

	static char[] ink;
	static char[][] stage;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		I = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		ink = br.readLine().toCharArray();
		stage = new char[N][N];

		for (int i = 0; i < N; ++i) {
			String s = br.readLine();
			for (int j = 0; j < N; ++j) {
				char c = s.charAt(j);

				if (c == '@') {
					square = new Pos(i, j, 0);
					c = '.';
				}

				stage[i][j] = c;
			}
		}

		String command = br.readLine();
		for (int cmd = 0; cmd < K; ++cmd) {
			int x, y;

			switch (command.charAt(cmd)) {
			case 'U':
				x = square.x - 1;
				y = square.y;

				if (x < 0 || stage[x][y] != '.')
					break;

				square.x = x;
				break;

			case 'D':
				x = square.x + 1;
				y = square.y;

				if (x >= N || stage[x][y] != '.')
					break;

				square.x = x;
				break;

			case 'L':
				x = square.x;
				y = square.y - 1;

				if (y < 0 || stage[x][y] != '.')
					break;

				square.y = y;
				break;

			case 'R':
				x = square.x;
				y = square.y + 1;

				if (y >= N || stage[x][y] != '.')
					break;

				square.y = y;
				break;

			case 'j':
				++inkCharge;
				break;

			case 'J':
				jump();
				break;
			}
		}

		stage[square.x][square.y] = '@';

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j)
				sb.append(stage[i][j]);
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void jump() {
		char color = ink[(jumpCnt++) % I];

		boolean[][] visited = new boolean[N][N];
		Queue<Pos> q = new ArrayDeque<>();
		visited[square.x][square.y] = true;
		q.offer(square);

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			int x = cur.x;
			int y = cur.y;
			int dist = cur.dist;

			if (dist > inkCharge)
				break;

			if (stage[x][y] != '.')
				stage[x][y] = color;

			for (int d = 0; d < 4; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny])
					continue;

				visited[nx][ny] = true;
				q.offer(new Pos(nx, ny, dist + 1));
			}
		}

		inkCharge = 0;
	}

}
