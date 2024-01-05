package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 상어 중학교의 코딩 동아리에서 블록을 제거하는 게임을 만들었을 때 획득한 점수의 합을 구하는 문제
public class G2_21609_상어중학교 {

	static final int BLANK = -9; // 빈 칸

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int n, m, score; // 격자의 크기, 색상의 개수, 획득한 점수의 합
	static int stdr, stdc; // 제거할 블록의 기준 블록 좌표
	static int maxSize, maxRainbow; // 블록의 최대 개수, 최대 블록 내 무지개 블록의 개수
	static int[][] block; // 격자 배열
	static boolean[][] visit; // 방문 배열

	static Queue<Pos> queue = new ArrayDeque<>(); // BFS를 위한 큐

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

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		block = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				block[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		br.close();

		// 오토 플레이 시작
		while (true) {
			visit = new boolean[n][n]; // 방문 배열 초기화

			maxSize = 0; // 블록의 최대 개수 초기화

			// 기준 블록을 찾으며 블록 그룹 찾기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 기준 블록이 되는 일반 블록에서 블록 그룹 찾기
					if (block[i][j] > 0 && !visit[i][j]) {
						getGroup(i, j);
					}
				}
			}

			// 블록 그룹이 되려면 블록이 2개 이상 인접해야 함
			// 블록 그룹이 존재하지 않으면 오토 플레이 종료
			if (maxSize < 2)
				break;

			removeGroup(); // 최대 블록 그룹 제거
			gravity(); // 중력 작용
			rotation(); // 격자 회전
			gravity(); // 중력 작용
		}

		System.out.println(score);
	}

	// 블록 그룹 찾기
	static void getGroup(int r, int c) {
		// 기준 블록 큐에 넣고 방문 처리
		queue.offer(new Pos(r, c));
		visit[r][c] = true;

		int color = block[r][c]; // 기준 블록의 색
		int size = 0; // 블록 그룹 크기
		int rainbow = 0; // 블록 그룹 내 무지개 블록 개수

		while (!queue.isEmpty()) {
			Pos cur = queue.poll(); // 현재 좌표

			size++; // 크기 증가

			if (block[cur.r][cur.c] == 0) {
				rainbow++; // 현재 좌표가 무지개 블록이면 개수 증가
			}

			for (int d = 0; d < 4; d++) {
				// 새로운 좌표
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n || visit[nr][nc]) {
					continue;
				}

				// 새로운 좌표가 같은 색의 일반 블록이거나 무지개 블록일 때
				if (block[nr][nc] == color || block[nr][nc] == 0) {
					// 해당 블록 큐에 넣고 방문 처리
					queue.offer(new Pos(nr, nc));
					visit[nr][nc] = true;
				}
			}
		}

		// 기존 값에 비해 현재 찾은 블록 그룹의 크기가 더 크거나
		// 같은 크기에서 무지개 블록의 개수가 더 많거나
		// 같은 크기 및 같은 개수의 무지개 블록이면서 기준 블록의 행이 더 크거나
		// 모두 같을 때 기준 블록의 열이 가장 크면 값 갱신
		if (maxSize < size || (maxSize == size && maxRainbow <= rainbow)) {
			// 블록 그룹 크기 및 무지개 블록 개수 갱신
			maxSize = size;
			maxRainbow = rainbow;

			// 기준 블록 좌표 갱신
			stdr = r;
			stdc = c;
		}

		// 다음 블록 그룹 탐색을 위해 무지개 블록 방문 해제
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (block[i][j] == 0) {
					visit[i][j] = false;
				}
			}
		}
	}

	// 최대 블록 그룹 제거
	static void removeGroup() {
		queue.offer(new Pos(stdr, stdc)); // 기준 블록

		int color = block[stdr][stdc]; // 기준 블록의 색
		block[stdr][stdc] = BLANK; // 기준 블록 제거
		int size = 0; // 블록 그룹 크기

		while (!queue.isEmpty()) {
			Pos cur = queue.poll(); // 현재 좌표

			size++; // 크기 증가

			for (int d = 0; d < 4; d++) {
				// 새로운 좌표
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n) {
					continue;
				}

				// 새로운 좌표가 같은 색의 일반 블록이거나 무지개 블록일 때
				if (block[nr][nc] == color || block[nr][nc] == 0) {
					// 해당 블록 큐에 넣고 제거
					queue.offer(new Pos(nr, nc));
					block[nr][nc] = BLANK;
				}
			}
		}

		score += size * size; // 점수 더함
	}

	// 중력 작용
	static void gravity() {
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				// 블록이 없거나 검은색 블록이면 넘어감
				if (block[i][j] < 0)
					continue;

				int b = block[i][j]; // 현재 블록 색
				block[i][j] = BLANK; // 현재 블록 제거
				int nr = i; // 현재 행

				// 격자의 바닥 또는 검은색 블록을 만날 때까지 행 이동
				while (nr + 1 < n && block[nr + 1][j] == BLANK) {
					nr++;
				}

				block[nr][j] = b; // 새로운 좌표로 블록 이동
			}
		}
	}

	// 격자 회전
	static void rotation() {
		int[][] tmp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmp[n - j - 1][i] = block[i][j];
			}
		}
		block = tmp;
	}
}
