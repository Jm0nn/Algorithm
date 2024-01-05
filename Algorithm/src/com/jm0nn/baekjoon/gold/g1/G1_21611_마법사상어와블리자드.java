package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 마법사 상어가 블리자드를 시전했을 때 폭발한 구슬 번호의 합을 구하는 문제
public class G1_21611_마법사상어와블리자드 {

	// 마법의 방향 (0: 제자리, 1: 위, 2: 아래, 3: 왼쪽, 4: 오른쪽)
	static final int[][] magic = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	// 위치 클래스
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// 구슬 클래스
	static class Marble extends Pos {
		int num;

		public Marble(int r, int c, int num) {
			super(r, c);
			this.num = num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 격자 크기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n]; // 격자 배열

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Stack<Pos> order = new Stack<>(); // 좌표 스택
		boolean[][] visit = new boolean[n][n]; // 방문 확인 배열

		int x = 0; // 행
		int y = 0; // 열
		int dir = 0; // 방향
		int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 좌표 이동

		while (!visit[x][y]) { // 격자를 다 돌때까지 반복
			// 새로운 좌표
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];

			// 새로운 좌표가 격자를 벗어나거나 이미 들른 곳이라면 방향을 바꿔서 좌표 재설정
			if (0 > nx || nx >= n || 0 > ny || ny >= n || visit[nx][ny]) {
				dir = (dir + 1) % 4;

				nx = x + deltas[dir][0];
				ny = y + deltas[dir][1];
			}

			// 방문 확인 후 스택에 좌표 정보 넣음
			visit[x][y] = true;
			order.push(new Pos(x, y));

			// 현재 좌표 갱신
			x = nx;
			y = ny;
		}

		Marble[] marble = new Marble[n * n]; // 구슬 배열
		int[][] number = new int[n][n]; // 칸 번호 배열

		// 격자에 칸 번호 배치 및 구슬 번호 저장
		for (int i = 0; i < n * n; i++) {
			Pos cur = order.pop();
			marble[i] = new Marble(cur.r, cur.c, map[cur.r][cur.c]);
			number[cur.r][cur.c] = i;
		}

		int sum = 0; // 폭발한 구슬 번호의 합

		// 블리자드 시전
		while (m-- > 0) {
			// 얼음 파편 던지기
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 거리

			// 중앙 좌표
			int r = n / 2;
			int c = n / 2;

			// 얼음 파편으로 구슬 깨짐
			while (s-- > 0) {
				r += magic[d][0];
				c += magic[d][1];

				int num = number[r][c];
				marble[num].num = 0;
			}

			// 구슬들이 이동하여 빈 칸 채움
			for (int i = 1; i < n * n - 1; i++) {
				if (marble[i].num != 0)
					continue;

				int end = i;

				for (int j = i + 1; j < n * n; j++) {
					if (marble[j].num != 0)
						break;
					end = j;
				}

				if (end + 1 == n * n)
					break;

				marble[i].num = marble[end + 1].num;
				marble[end + 1].num = 0;
			}

			// 구슬 폭발
			while (true) {
				boolean bomb = false; // 구슬 폭발이 일어났는지

				// 같은 번호의 구슬이 연속으로 4개 이상 있으면 폭발
				for (int i = 1; i < n * n; i++) {
					if (marble[i].num == 0)
						break;

					// 같은 번호의 구슬이 연속으로 몇개 나오는지 확인
					int num = marble[i].num;
					int end = i;

					for (int j = i + 1; j < n * n; j++) {
						if (marble[j].num != num)
							break;
						end = j;
					}

					// 같은 번호가 연속으로 4개 이상
					if (end - i + 1 > 3) {
						for (int j = i; j <= end; j++) {
							sum += marble[j].num;
							marble[j].num = 0;
						}

						bomb = true; // 폭발 확인
					}

					i = end;
				}

				// 폭발이 일어나지 않았다면 구슬 폭발 단계 종료
				if (!bomb)
					break;

				// 구슬들이 이동하여 빈 칸 채움
				for (int i = 1; i < n * n - 1; i++) {
					if (marble[i].num != 0)
						continue;

					int end = i;

					for (int j = i + 1; j < n * n; j++) {
						if (marble[j].num != 0)
							break;
						end = j;
					}

					if (end + 1 == n * n)
						break;

					marble[i].num = marble[end + 1].num;
					marble[end + 1].num = 0;
				}
			}

			// 구슬 변화
			int[] change = new int[n * n]; // 변화한 구슬 배열
			int idx = 1; // 인덱스

			for (int i = 1; i < n * n; i++) {
				if (marble[i].num == 0)
					break;

				// 연속한 번호의 구슬들이 두 개의 구슬로 변화함
				int end = i;

				for (int j = i + 1; j < n * n; j++) {
					if (marble[j].num != marble[i].num)
						break;
					end = j;
				}

				int cnt = end - i + 1;

				change[idx++] = cnt; // 첫 번째 구슬: 연속한 번호의 구슬의 개수
				change[idx++] = marble[i].num; // 두 번째 구슬: 구슬의 번호

				// 변화한 구슬의 개수가 격자를 벗어나면 종료
				if (idx == n * n)
					break;

				i = end;
			}

			// 변화한 구슬 배열 복사
			for (int i = 1; i < n * n; i++)
				marble[i].num = change[i];
		}

		System.out.println(sum);
	}

}
