package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로를 이동할 때 최대 k개의 벽을 부수고 이동하는 최단 경로를 구하는 문제
public class G1_16933_벽부수고이동하기3 {

	// 이동 방향
	static final int[][] deltas = { { 0, 0 }, { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int n, m, k; // 맵 크기, 벽을 부술 수 있는 횟수
	static int[][] map; // 맵 배열
	static boolean[][][] visit; // 방문 배열

	// 위치 클래스
	static class Pos {
		int r, c; // 위치
		int cnt; // 이동 거리
		int wall; // 벽을 부순 개수
		boolean isDay; // 낮인지 여부
		int d; // 직전 이동 방향

		public Pos(int r, int c, int cnt, int wall, boolean isDay, int d) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.wall = wall;
			this.isDay = isDay;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visit = new boolean[n][m][k + 1];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = s.charAt(j) - '0';
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Pos> queue = new ArrayDeque<>();

		// (0, 0) 시작, 낮부터 시작
		queue.offer(new Pos(0, 0, 1, 0, true, -1));
		for (int i = 0; i <= k; i++)
			visit[0][0][i] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			int curk = cur.wall; // 현재 벽을 부순 횟수

			// (n-1, m-1) 도착하면 거리 리턴
			if (cur.r == n - 1 && cur.c == m - 1)
				return cur.cnt;

			for (int d = 0; d < 5; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				// 배열을 벗어나면 다음 탐색
				if (0 > nr || nr >= n || 0 > nc || nc >= m)
					continue;

				if (map[nr][nc] == 1) { // 벽일 때
					if (d == 0 && cur.d != 0) {
						for (int i = curk; i <= k; i++)
							visit[nr][nc][i] = true;

						// 제자리라면 벽을 부수지 않음
						queue.offer(new Pos(nr, nc, cur.cnt + 1, curk, !cur.isDay, d));
					} else if (cur.isDay && curk < k && !visit[nr][nc][curk + 1]) {
						// 제자리가 아니면 낮이고, 벽을 더 부술 수 있고, 방문한 적 없어야 가능
						for (int i = curk + 1; i <= k; i++)
							visit[nr][nc][i] = true;

						// 벽을 부순 횟수 추가 후 큐에 넣음
						queue.offer(new Pos(nr, nc, cur.cnt + 1, curk + 1, !cur.isDay, d));
					}
				} else {
					if ((d == 0 && cur.d != 0) || !visit[nr][nc][curk]) {
						for (int i = curk; i <= k; i++)
							visit[nr][nc][i] = true;

						queue.offer(new Pos(nr, nc, cur.cnt + 1, curk, !cur.isDay, d));
					}
				}
			}
		}

		return -1; // 도착하지 못하면 -1 리턴
	}

}
