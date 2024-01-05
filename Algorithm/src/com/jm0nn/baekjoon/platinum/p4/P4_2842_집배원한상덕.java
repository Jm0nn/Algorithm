package com.jm0nn.baekjoon.platinum.p4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 상덕이가 마을을 돌아다니며 우편을 배달할 때 피로도의 최솟값을 구하는 문제
public class P4_2842_집배원한상덕 {

	// 마을 이동 방향
	static final int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };

	static int n, kCnt, min; // 마을의 크기, 집의 개수, 피로도의 최솟값
	static char[][] village; // 마을 배열
	static int[][] height; // 높이 배열
	static boolean[][] visit; // 방문 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		village = new char[n][n];
		height = new int[n][n];
		visit = new boolean[n][n];

		// 높이의 최댓값, 최솟값
		int maxH = 0;
		int minH = 0;

		// 우체국의 좌표
		int px = -1;
		int py = -1;

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				char c = s.charAt(j);

				if (c == 'P') {
					// 우체국의 좌표 설정
					px = i;
					py = j;
				} else if (c == 'K') {
					// 집의 개수 계산
					kCnt++;
				}

				village[i][j] = c;
			}
		}

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int h = Integer.parseInt(st.nextToken());

				// 집의 위치를 통해 초기 높이의 최댓값, 최솟값 설정
				if (i == px && j == py) {
					maxH = minH = h;
				}

				height[i][j] = h;
			}
		}

		min = Integer.MAX_VALUE;

		visit[px][py] = true; // 우체국 방문 처리
		dfs(0, px, py, maxH, minH); // 깊이 우선 탐색

		System.out.println(min);
	}

	// cnt: 방문한 집의 개수, x, y: 현재 좌표, maxH, minH: 높이의 최댓값, 최솟값
	static void dfs(int cnt, int x, int y, int maxH, int minH) {
		if (cnt == kCnt) { // 집을 모두 들렀다면
			int diff = maxH - minH; // 현재 경로의 피로도
			if (min > diff) // 피로도의 최솟값 갱신
				min = diff;
			return;
		}

		for (int d = 0; d < 8; d++) {
			// 새로운 좌표
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];

			if (0 > nx || nx >= n || 0 > ny || ny >= n || visit[nx][ny])
				continue;

			int maxh = maxH;
			int minh = minH;

			// 높이의 최댓값 갱신
			if (maxh < height[nx][ny]) {
				maxh = height[nx][ny];
			}

			// 높이의 최솟값 갱신
			if (minh > height[nx][ny]) {
				minh = height[nx][ny];
			}

			visit[nx][ny] = true;
			if (village[nx][ny] == 'K') {
				// 집을 들렀다면 방문한 집의 개수 증가 후 다음 지점 탐색
				dfs(cnt + 1, nx, ny, maxh, minh);
			} else {
				// 집을 들르지 않았다면 그대로 다음 지점 탐색
				dfs(cnt, nx, ny, maxh, minh);
			}
			visit[nx][ny] = false;
		}
	}

}
