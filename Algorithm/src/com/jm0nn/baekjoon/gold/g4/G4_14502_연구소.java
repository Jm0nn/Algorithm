package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소에 바이러스가 퍼질 때 벽을 세워 안전 영역을 최대한 늘리는 문제
public class G4_14502_연구소 {

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int n, m; // 연구소 크기
	static int max; // 안전 영역 최댓값
	static int[][] lab; // 연구소 배열
	static boolean[][] visit; // 방문 배열
	static StringBuilder sb = new StringBuilder(); // 연구소 정보 스트링빌더

	// 위치 클래스
	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		lab = new int[n][m];

		// 스트링빌더에 연구소 정보 입력
		for (int i = 0; i < n; i++)
			sb.append(br.readLine().replace(" ", ""));

		getWall(0, 0); // 벽 세우기

		System.out.println(max);
	}

	// 벽 세우기
	static void getWall(int next, int cnt) {
		// 벽을 세 개 세우면
		if (cnt == 3) {
			// 연구소 배열 입력
			int idx = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					lab[i][j] = sb.charAt(idx++) - '0';
			spread(); // 바이러스 뿌리기
			return;
		}

		// 벽 세우기
		for (int i = next; i < n * m; i++) {
			if (sb.charAt(i) == '0') { // 빈 칸
				sb.replace(i, i + 1, "1");
				getWall(i + 1, cnt + 1); // 벽 한개 세우고 다음 벽 세우기
				sb.replace(i, i + 1, "0");
			}
		}
	}

	// 바이러스 뿌리기
	static void spread() {
		visit = new boolean[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (!visit[i][j] && lab[i][j] == 2)
					bfs(i, j); // 바이러스가 있는 곳부터 바이러스 퍼짐

		int cnt = 0; // 안전 영역 개수
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (lab[i][j] == 0)
					cnt++;

		// 안전 영역 최댓값 갱신
		if (max < cnt)
			max = cnt;
	}

	// 바이러스 퍼짐
	static void bfs(int r, int c) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		visit[r][c] = true;

		while (!q.isEmpty()) {
			Pos cur = q.poll();
			lab[cur.r][cur.c] = 2; // 큐에서 꺼낸 위치 바이러스 퍼짐

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= m)
					continue;

				if (lab[nr][nc] == 1 || visit[nr][nc])
					continue;

				q.offer(new Pos(nr, nc));
				visit[nr][nc] = true;
			}
		}
	}
}
