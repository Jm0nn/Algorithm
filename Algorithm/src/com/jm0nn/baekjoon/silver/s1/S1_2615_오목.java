package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_2615_오목 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] deltas = { { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 } }; // 바둑돌이 일렬로 놓인 방향 (우상, 우, 우하, 하)

		int[][] board = new int[21][21]; // 바둑판 배열, 배열을 벗어나지 않기 위해 테두리 추가

		for (int i = 1; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 1; j < 20; j++) {
				board[i][j] = Integer.parseInt(st.nextToken()); // 바둑판 입력
			}
		}

		br.close();

		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (board[i][j] != 0) { // 돌이 놓여 있다면
					int stone = board[i][j]; // 현재 놓인 돌

					for (int d = 0; d < deltas.length; d++) { // 각 방향으로 좌표 이동
						int count = 1; // 일렬로 놓인 갯수

						int ni = i; // 이동한 좌표
						int nj = j;

						while (true) {
							ni += deltas[d][0]; // 현재 방향으로 한 칸 이동
							nj += deltas[d][1];

							if (board[ni][nj] == stone)
								count++; // 같은 색으로 놓여 있으면 카운트 증가
							else
								break; // 바둑판을 벗어나거나 다른 색이라면 다음 방향으로 탐색
						}

						// 같은 색 돌이 5개가 연속으로 놓여 있고, 반대 방향으로 같은 돌이 놓여있지 않다면 (6개 이상 일렬로 놓여 있지 않다면)
						if (count == 5 && board[i - deltas[d][0]][j - deltas[d][1]] != stone) {
							System.out.println(stone);
							System.out.println(i + " " + j);

							System.exit(0); // 출력 후 프로그램 종료
						}
					}
				}
			}
		}

		System.out.println(0); // 탐색에 실패하면 0 출력 후 종료
	}
}
