package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 공기청정기를 돌린 후 미세먼지의 남은 양을 구하는 문제
public class G4_17144_미세먼지안녕 {

	// 위치 이동
	static final int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int r, c, t; // 방의 크기, 공기청정기 돌릴 시간
	static int[][] room; // 방 배열
	static int[] cleaner; // 공기청정기 위치

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		room = new int[r][c];
		cleaner = new int[2]; // 공기청정기는 두 칸을 차지함

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());

				// 공기청정기는 항상 1번 열에만 있으므로 행 번호만 입력받음
				if (room[i][j] == -1) {
					if (cleaner[0] == 0)
						cleaner[0] = i;
					else
						cleaner[1] = i;
				}
			}
		}

		// t초동안 공기청정기 돌림
		while (t-- > 0) {
			spreadDust();
			curculation();
		}

		// 미세먼지의 남은 양 출력
		System.out.println(totalDust());
	}

	// 미세먼지 확산
	static void spreadDust() {
		int[][] tmp = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {

				// 미세먼지가 없거나 공기청정기가 있는 칸은 넘어감
				if (room[i][j] <= 0) {
					tmp[i][j] += room[i][j];
					continue;
				}

				int cur = room[i][j]; // 현재 위치의 미세먼지 양
				int spread = cur / 5; // 확산되는 미세먼지 양

				tmp[i][j] += cur;

				// 확산되는 미세먼지가 없으면 넘어감
				if (spread == 0)
					continue;

				// 사방으로 미세먼지 확산
				for (int d = 0; d < 4; d++) {
					int nr = i + deltas[d][0];
					int nc = j + deltas[d][1];

					// 새로운 위치가 방 밖을 벗어나거나 공기청정기가 있는 곳이라면 넘어감
					if (0 > nr || nr >= r || 0 > nc || nc >= c || room[nr][nc] == -1)
						continue;

					tmp[nr][nc] += spread;
					tmp[i][j] -= spread;
				}
			}
		}

		room = tmp;
	}

	// 공기 순환
	static void curculation() {
		// 공기청정기 바로 윗쪽 좌표
		int cr = cleaner[0] - 1;
		int cc = 0;

		int d = 0; // 공기가 순환하는 방향의 역순

		while (true) {
			int nr = cr + deltas[d][0];
			int nc = cc + deltas[d][1];

			// 공기청정기 윗부분을 기준으로 시계방향으로 좌표 탐색
			if (0 > nr || nr > cleaner[0] || 0 > nc || nc >= c) {
				d = (d + 1) % 4;

				nr = cr + deltas[d][0];
				nc = cc + deltas[d][1];
			}

			// 위치가 공기청정기에 도달하면 종료
			if (room[nr][nc] == -1) {
				room[cr][cc] = 0;
				break;
			}

			// 앞쪽의 미세먼지를 가져옴
			room[cr][cc] = room[nr][nc];
			cr = nr;
			cc = nc;
		}

		// 공기청정기 바로 아랫쪽 좌표
		cr = cleaner[1] + 1;
		cc = 0;

		d = 2; // 윗쪽과 반대 방향으로 순환

		while (true) {
			int nr = cr + deltas[d][0];
			int nc = cc + deltas[d][1];

			// 공기청정기 아랫부분을 기준으로 반시계방향으로 좌표 탐색
			if (cleaner[1] > nr || nr >= r || 0 > nc || nc >= c) {
				d = (d + 3) % 4;

				nr = cr + deltas[d][0];
				nc = cc + deltas[d][1];
			}

			// 위치가 공기청정기에 도달하면 종료
			if (room[nr][nc] == -1) {
				room[cr][cc] = 0;
				break;
			}

			// 앞쪽의 미세먼지를 가져옴
			room[cr][cc] = room[nr][nc];
			cr = nr;
			cc = nc;
		}
	}

	// 방 안의 전체 미세먼지 양 계산
	static int totalDust() {

		// 공기청정기 값을 고려해 방 안의 수치를 모두 더함
		int sum = 2;

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				sum += room[i][j];

		return sum;
	}

}
