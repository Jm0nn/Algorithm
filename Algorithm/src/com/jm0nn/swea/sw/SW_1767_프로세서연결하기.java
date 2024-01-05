package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_1767_프로세서연결하기 {

	// 탐색 방향 (0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽)
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int n, max, min; // 코어의 개수, 연결한 코어 개수의 최댓값, 연결한 전선 길이의 최솟값
	static int[][] cell; // 프로세서 배열
	static List<Core> cores; // 코어 리스트

	// 코어 클래스
	static class Core {
		int r, c; // 위치
		boolean[] search; // 탐색 가능한 방향인지 확인하는 배열

		Core(int r, int c) {
			this.r = r;
			this.c = c;

			search = new boolean[] { true, true, true, true };
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine());
			cell = new int[n][n];
			cores = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					cell[i][j] = Integer.parseInt(st.nextToken());
					if (cell[i][j] == 1)
						cores.add(new Core(i, j));
				}
			}

			// 연결이 불필요하거나 연결 불가능한 코어는 리스트에서 제거
			for (int i = cores.size() - 1; i >= 0; i--) {
				Core core = cores.get(i);
				int r = core.r;
				int c = core.c;

				// 테두리에 있는 코어는 연결이 불필요하므로 제거
				if (r == 0 || r == n - 1 || c == 0 || c == n - 1) {
					cores.remove(i);
					continue;
				}

				// 상하좌우 연결 가능한 방향 탐색
				while (r > 0) {
					if (cell[r-- - 1][c] == 1) {
						core.search[0] = false;
						break;
					}
				}

				r = core.r;

				while (c < n - 1) {
					if (cell[r][c++ + 1] == 1) {
						core.search[1] = false;
						break;
					}
				}

				c = core.c;

				while (r < n - 1) {
					if (cell[r++ + 1][c] == 1) {
						core.search[2] = false;
						break;
					}
				}

				r = core.r;

				while (c > 0) {
					if (cell[r][c-- - 1] == 1) {
						core.search[3] = false;
						break;
					}
				}

				// 연결 가능한 방향이 없으면 해당 코어 제거
				boolean delete = true;
				for (int j = 0; j < 4; j++) {
					if (core.search[j]) {
						delete = false;
						break;
					}
				}

				if (delete)
					cores.remove(i);
			}

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			// dfs로 연결 가능한 코어의 최댓값, 그 때 전선 길이의 최솟값을 구함
			dfs(cell, 0, 0);

			// 전선 길이의 최솟값 출력
			sb.append(min).append('\n');
		}

		System.out.println(sb);
	}

	// map: 현재 연결 상태, cnt: 탐색 횟수, connect: 연결한 코어 개수
	static void dfs(int[][] map, int cnt, int connect) {
		// 앞으로 모든 코어를 다 연결해도 max에 도달할 수 없으면 그 이상 탐색하지 않음
		if (cores.size() - cnt + connect < max)
			return;

		// 모든 코어를 탐색했을 때
		if (cnt == cores.size()) {
			// 연결한 코어 개수 최댓값 및 전선 길이의 최솟값 갱신
			if (max <= connect) {
				int line = 0;
				for (int i = 0; i < n; i++)
					for (int j = 0; j < n; j++)
						if (map[i][j] == 2)
							line++;

				// 지금 연결한 코어 개수가 max보다 많으면 max, min 둘 다 갱신
				if (max < connect) {
					max = connect;
					min = line;
				} else if (min > line) {
					min = line; // 코어 개수가 같으면 min만 갱신
				}
			}

			return;
		}

		Core core = cores.get(cnt);

		// 4방향으로 연결 시도 또는 연결 시도 하지 않음
		for (int d = 0; d < 4; d++) {
			if (!core.search[d])
				continue;

			// 해당 방향으로 전선 연결 시도
			int[][] tmp = setLine(map, core, d);

			// 연결 성공했을 때만 다음 코어 탐색
			if (tmp[0][0] != 9)
				dfs(tmp, cnt + 1, connect + 1);
		}

		// 현재 코어 연결하지 않고 다음 코어 탐색
		dfs(map, cnt + 1, connect);
	}

	// 전선 연결 시도
	static int[][] setLine(int[][] map, Core core, int d) {
		int[][] tmp = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				tmp[i][j] = map[i][j];

		int r = core.r;
		int c = core.c;

		// 연결한 코어 3으로 설정
		tmp[r][c] = 3;

		r += deltas[d][0];
		c += deltas[d][1];

		while (0 <= r && r < n && 0 <= c && c < n) {
			// 연결 실패하면 (0, 0)을 9로 설정
			if (tmp[r][c] != 0) {
				tmp[0][0] = 9;
				return tmp;
			}

			// 연결한 전선 2로 설정
			tmp[r][c] = 2;
			r += deltas[d][0];
			c += deltas[d][1];
		}

		return tmp;
	}

}
