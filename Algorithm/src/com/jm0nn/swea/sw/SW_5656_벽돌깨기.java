package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//구슬을 쏘아 벽돌을 깨트려 남은 벽돌의 최솟값을 구하는 문제
public class SW_5656_벽돌깨기 {

	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int n, w, h, min; // 떨어뜨리는 구슬의 수, 배열의 크기, 남는 벽돌의 수 최솟값
	static int[][] map; // 벽돌 배열
	static boolean[][] bomb; // 벽돌 터지는지 여부

	// 좌표 클래스
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new int[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			dfs(map, 0); // dfs로 구슬을 떨어뜨리는 위치 탐색

			sb.append(min).append('\n');
		}

		System.out.println(sb);
	}

	static void dfs(int[][] map, int depth) {
		// 구슬을 n개 떨어뜨렸을 때
		if (depth == n) {
			int cnt = 0; // 남은 벽돌의 개수

			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++)
					if (map[i][j] != 0)
						cnt++;

			// 남은 벽돌의 개수 최솟값 갱신
			if (min > cnt)
				min = cnt;

			return;
		}

		for (int i = 0; i < w; i++)
			dfs(drop(map, i), depth + 1);
	}

	// 구슬을 떨어뜨리기 (map: 현재 배열 상태, idx: 구슬 떨어뜨리는 열 좌표)
	static int[][] drop(int[][] map, int idx) {
		int[][] tmp = new int[h][w]; // 벽돌 깨진 후 배열

		// 배열 복사
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				tmp[i][j] = map[i][j];

		int r = 0;
		int c = idx;

		// 벽돌이 있는 좌표 위에서부터 탐색
		while (0 <= r && r < h) {
			if (tmp[r][c] != 0)
				break;
			r++;
		}

		// 해당 열에 벽돌이 없다면 현재 상태 그대로 리턴
		if (r == h)
			return tmp;

		bomb = new boolean[h][w]; // 깨질 벽돌 좌표
		Queue<Pos> queue = new ArrayDeque<>(); // bfs를 위한 큐

		// 최초 벽돌이 깨지는 좌표
		queue.offer(new Pos(r, c));
		bomb[r][c] = true;

		// bfs로 깨질 벽돌 위치 탐색
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			int range = tmp[cur.r][cur.c];

			for (int i = 1; i < range; i++) {
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + deltas[d][0] * i;
					int nc = cur.c + deltas[d][1] * i;

					if (0 > nr || nr >= h || 0 > nc || nc >= w)
						continue;

					if (bomb[nr][nc] || tmp[nr][nc] == 0)
						continue;

					queue.offer(new Pos(nr, nc));
					bomb[nr][nc] = true;
				}
			}
		}

		// 벽돌 깨트림
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (bomb[i][j])
					tmp[i][j] = 0;

		// 공중에 떠있는 벽돌 바닥으로 떨어짐
		for (int i = h - 2; i >= 0; i--) {
			for (int j = 0; j < w; j++) {
				if (tmp[i][j] == 0)
					continue;

				int num = tmp[i][j];
				tmp[i][j] = 0;

				int nr = i;

				while (nr + 1 < h) {
					if (tmp[nr + 1][j] != 0)
						break;
					nr++;
				}

				tmp[nr][j] = num;
			}
		}

		return tmp;
	}
}
