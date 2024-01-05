package com.jm0nn.swea.d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class D2_1954_달팽이숫자 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 숫자 입력 방향 (우하좌상)
		int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		int T; // 테스트 케이스
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append('\n');

			int N = Integer.parseInt(br.readLine()); // 달팽이 크기

			if (N == 1) { // 달팽이의 크기가 1일 경우 바로 출력 후 다음 테스트케이스로 넘어감
				sb.append(1).append('\n');
				continue;
			}

			int[][] snail = new int[N][N]; // 달팽이 배열

			int x = 0; // 현재 x좌표
			int y = 0; // 현재 y좌표
			int num = 1; // 달팽이에 입력되는 수
			int d = 0; // 숫자 입력 방향

			while (true) {
				snail[x][y] = num++; // 현재 좌표에 수 입력

				// 현재 방향으로 새로운 좌표
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				// 새로운 좌표가 배열을 벗어나거나
				// 새로운 좌표에 입력이 이미 돼 있을 경우
				if (0 > nx || nx >= N || 0 > ny || ny >= N || snail[nx][ny] != 0) {
					d = (d + 1) % 4; // 방향 바꿈

					// 바꾼 방향으로 새로운 좌표
					nx = x + deltas[d][0];
					ny = y + deltas[d][1];
				}

				// 새로운 좌표에 입력이 이미 돼 있을 경우 입력이 완료되었으므로 반복문 탈출
				if (snail[nx][ny] != 0)
					break;

				// 현재 좌표 갱신
				x = nx;
				y = ny;
			}

			// 달팽이 출력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					sb.append(snail[i][j]).append(' ');
				sb.append('\n');
			}
		}

		System.out.println(sb);

		br.close();
	}
}
