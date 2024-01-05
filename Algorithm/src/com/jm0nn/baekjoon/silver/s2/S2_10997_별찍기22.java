package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2_10997_별찍기22 {

	// 별 찍기 진행 방향 (좌하우상)
	static final int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	static int N, bound;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		bound = N * 4 - 3; // 배열의 크기 설정
		map = new char[bound + 2][bound]; // 배열 설정

		for (int i = 0; i < map.length; i++)
			Arrays.fill(map[i], ' ');

		star(0, bound - 1, 0); // 오른쪽 위 구석부터 별 찍기 시작

		for (int i = 0; i < bound + 2; i++) {
			for (int j = 0; j < bound; j++) {
				if (i == 1 && j == 1)
					break;

				sb.append(map[i][j]);
			}

			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	// 별 찍는 좌표와 별 찍기 진행 방향을 넘겨줌
	static void star(int x, int y, int d) {
		map[x][y] = '*'; // 현재 좌표 별 찍기

		if (N == 1) // N이 1일 경우 별만 찍고 리턴
			return;

		// 현재 방향 한 칸 앞쪽
		int nx = x + dir[d][0];
		int ny = y + dir[d][1];

		// 현재 방향 두 칸 앞쪽
		int nnx = nx + dir[d][0];
		int nny = ny + dir[d][1];

		// 한 칸 앞쪽이 배열을 벗어날 경우
		if (0 > nx || nx >= bound + 2 || 0 > ny || ny >= bound) {
			d = (d + 1) % 4; // 새로운 방향

			// 새로운 방향으로 새로운 좌표
			nx = x + dir[d][0];
			ny = y + dir[d][1];

			star(nx, ny, d); // 다음 별 찍기

			// 두 칸 앞쪽이 이미 별이 찍힌 경우
		} else if ((0 <= nnx && nnx < bound + 2) && (0 <= nny && nny < bound) && map[nnx][nny] == '*') {
			d = (d + 1) % 4; // 새로운 방향

			// 새로운 방향으로 새로운 좌표
			nx = x + dir[d][0];
			ny = y + dir[d][1];

			star(nx, ny, d); // 다음 별 찍기

			// 한 칸 앞쪽이 이미 별이 찍혀 있다면
		} else if (map[nx][ny] == '*') {
			// 별 찍기를 종료해야 하므로 현재 좌표 빈 공간으로 만들고 메서드 종료
			map[x][y] = ' ';

			// 모든 조건에 맞지 않는 경우
		} else {
			// 방향을 바꾸지 않고 계속 별 찍기 진행
			star(nx, ny, d);
		}
	}
}
