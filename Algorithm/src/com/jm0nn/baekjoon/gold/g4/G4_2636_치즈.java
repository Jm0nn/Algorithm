package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 공기와 접한 부분의 치즈가 녹을 때 모든 치즈가 녹는 시간과 그 직전 남아 있는 치즈의 개수를 구하는 문제
public class G4_2636_치즈 {

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int r, c, cnt; // 배열의 크기, 치즈의 개수
	static int[][] cheese; // 치즈 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		cheese = new int[r + 1][c + 1];

		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= c; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] == 1)
					cnt++;
			}
		}

		int time = 0; // 지난 시간
		int before = cnt; // 현재 단계 직전에 남아 있는 치즈의 개수
		while (cnt != 0) {
			time++;
			before = cnt;
			bfs();
		}

		System.out.println(time + "\n" + before);
	}

	static void bfs() {
		Queue<int[]> melt = new ArrayDeque<>();
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[r + 1][c + 1];

		// 치즈 바깥 공기에서부터 bfs 시작
		q.offer(new int[] { 0, 0 });
		visit[0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			// 현재 위치가 치즈라면 녹을 치즈 큐에 넣고 다음 탐색
			if (cheese[cur[0]][cur[1]] == 1) {
				melt.offer(cur);
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + deltas[d][0];
				int nc = cur[1] + deltas[d][1];

				if (0 > nr || nr > r || 0 > nc || nc > c || visit[nr][nc])
					continue;

				q.offer(new int[] { nr, nc });
				visit[nr][nc] = true;
			}
		}

		cnt -= melt.size(); // 치즈 개수 갱신

		// 큐에 있는 치즈 녹임
		while (!melt.isEmpty()) {
			int[] cur = melt.poll();
			cheese[cur[0]][cur[1]] = 0;
		}
	}

}
