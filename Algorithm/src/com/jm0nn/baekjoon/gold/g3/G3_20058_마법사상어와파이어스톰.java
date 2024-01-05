package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 마법사가 파이어스톰을 시전했을 때 얼음의 합과 덩어리의 크기를 구하는 문제
public class G3_20058_마법사상어와파이어스톰 {

	// 좌표 이동
	static final int deltas[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static int n, q, l, size, max; // 입력받는 크기, 파이어스톰 시전 수, 파이어스톰 시전 단계, 격자 크기, 덩어리의 최댓값
	static int[][] ice; // 얼음 배열
	static boolean[][] visit; // 방문 배열
	static Queue<Pos> queue = new ArrayDeque<>(); // BFS를 위한 큐

	// 좌표 클래스
	static class Pos {
		int r, c; // 행, 열

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		size = (int) Math.pow(2, n);
		ice = new int[size][size];
		visit = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		while (q-- > 0) {
			l = Integer.parseInt(st.nextToken());
			spell(); // 파이어스톰 시전
		}

		int sum = 0; // 전체 얼음의 양
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sum += ice[i][j];
				// 가장 큰 덩어리의 크기 계산
				if (ice[i][j] > 0 && !visit[i][j])
					bfs(i, j);
			}
		}

		sb.append(sum).append('\n').append(max);

		System.out.println(sb);
	}

	// 파이어스톰
	static void spell() {
		// L 단계만큼 얼음 회전
		int s = (int) Math.pow(2, l);
		for (int i = 0; i < size; i += s) {
			for (int j = 0; j < size; j += s) {
				rotation(i, j, s);
			}
		}

		ice = melt(); // 회전이 끝난 후 얼음 녹음
	}

	// 얼음 회전, r: 왼쪽 위 행, c: 왼쪽 위 열, s: 회전할 배열 크기
	static void rotation(int r, int c, int s) {
		int[][] tmp = new int[s][s]; // 회전용 임시 배열
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				tmp[i][j] = ice[i + r][j + c];
			}
		}

		// 오른쪽으로 90도 회전
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				ice[i + r][j + c] = tmp[s - j - 1][i];
			}
		}
	}

	// 얼음 녹이기
	static int[][] melt() {
		int[][] tmp = new int[size][size]; // 녹은 얼음 임시 배열

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (ice[i][j] == 0) // 얼음이 없으면 넘어감
					continue;

				int count = 0; // 인접한 얼음 개수

				for (int d = 0; d < 4; d++) {
					int ni = i + deltas[d][0];
					int nj = j + deltas[d][1];

					// 좌표가 격자를 벗어나거나 얼음이 없으면 넘어감
					if (0 > ni || ni >= size || 0 > nj || nj >= size || ice[ni][nj] == 0)
						continue;

					count++;
				}

				if (count < 3) // 인접한 얼음이 3개 미만이면 얼음 -1
					tmp[i][j] = ice[i][j] - 1;
				else // 아니면 그대로
					tmp[i][j] = ice[i][j];
			}
		}

		return tmp;
	}

	// 가장 큰 덩어리의 크기 계산
	static void bfs(int r, int c) {
		queue.offer(new Pos(r, c));
		visit[r][c] = true;

		int count = 0; // 덩어리의 격자 개수

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			count++;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= size || 0 > nc || nc >= size || visit[nr][nc] || ice[nr][nc] == 0)
					continue;

				queue.offer(new Pos(nr, nc));
				visit[nr][nc] = true;
			}
		}

		// 기존 max와 비교하여 덩어리가 더 크면 max 갱신
		if (max < count)
			max = count;
	}
}
