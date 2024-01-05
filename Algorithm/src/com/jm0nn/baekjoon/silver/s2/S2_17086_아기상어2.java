package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 아기상어로부터 가장 먼 거리를 구하는 문제
public class S2_17086_아기상어2 {

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };

	static int n, m; // 공간 크기
	static int max = Integer.MIN_VALUE; // 안전 거리의 최댓값
	static int[][] shark; // 공간 배열
	static boolean[][] visit; // 방문 배열

	// 좌표 클래스
	static class Pos {
		int r; // 행
		int c; // 열
		int cnt; // 시작 좌표로부터 거리

		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		shark = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				shark[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (shark[i][j] == 0)
					bfs(i, j); // 아기 상어가 없는 칸 탐색

		System.out.println(max);

		br.close();
	}

	// 시작 좌표를 넘겨받아서 탐색 시작
	static void bfs(int r, int c) {
		visit = new boolean[n][m]; // 방문 배열 초기화

		Queue<Pos> queue = new ArrayDeque<>();

		queue.offer(new Pos(r, c, 0));
		visit[r][c] = true; // 시작 좌표 방문 처리

		int safeDist = Integer.MAX_VALUE; // 안전 거리

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			// 아기 상어에 도달 시 안전거리 넘겨받고 반복문 탈출
			if (shark[cur.r][cur.c] == 1) {
				safeDist = cur.cnt;
				break;
			}

			// 8방향 탐색
			for (int d = 0; d < 8; d++) {
				// 새로운 좌표
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				// 새로운 좌표가 범위를 벗어나거나 방문했던 곳이라면 다음 좌표 탐색
				if (0 > nr || nr >= n || 0 > nc || nc >= m || visit[nr][nc])
					continue;

				visit[nr][nc] = true; // 방문 확인
				queue.offer(new Pos(nr, nc, cur.cnt + 1));
			}
		}

		// 안전 거리 갱신
		if (max < safeDist)
			max = safeDist;
	}

}
