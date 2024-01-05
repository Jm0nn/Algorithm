package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로를 이동할 때 최대 1개의 벽을 부수고 이동하는 최단 경로를 구하는 문제
public class G3_2206_벽부수고이동하기 {

	// 이동 방향
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int n, m; // 맵 크기
	static int[][] map; // 맵 배열
	static boolean[][] visit1, visit2; // 방문 배열 (벽을 부수지 않았을 때, 부쉈을 때)

	// 위치 클래스
	static class Pos {
		int r, c; // 위치
		int cnt; // 이동 거리
		boolean wall; // 벽을 부쉈는지 여부

		public Pos(int r, int c, int cnt, boolean wall) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.wall = wall;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visit1 = new boolean[n][m];
		visit2 = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = s.charAt(j) - '0';
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Pos> queue = new ArrayDeque<>();

		// (0, 0) 시작
		queue.offer(new Pos(0, 0, 1, false));
		visit1[0][0] = true;
		visit2[0][0] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			// (n-1, m-1) 도착하면 거리 리턴
			if (cur.r == n - 1 && cur.c == m - 1)
				return cur.cnt;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= m)
					continue;

				if (cur.wall) { // 벽을 이미 부쉈을 때
					if (visit2[nr][nc] || map[nr][nc] == 1)
						continue;

					queue.offer(new Pos(nr, nc, cur.cnt + 1, cur.wall));
					visit2[nr][nc] = true;
				} else { // 벽을 아직 부수지 않았을 때
					if (visit1[nr][nc])
						continue;

					if (map[nr][nc] == 0) { // 해당 좌표가 벽이 아님
						queue.offer(new Pos(nr, nc, cur.cnt + 1, cur.wall));
						visit1[nr][nc] = true;
						visit2[nr][nc] = true;
					} else { // 해당 좌표가 벽임
						queue.offer(new Pos(nr, nc, cur.cnt + 1, true));
						visit2[nr][nc] = true;
					}
				}
			}
		}

		return -1; // 도착하지 못하면 -1 리턴
	}

}
