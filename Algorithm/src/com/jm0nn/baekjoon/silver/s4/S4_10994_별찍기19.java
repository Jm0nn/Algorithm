package com.jm0nn.baekjoon.silver.s4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S4_10994_별찍기19 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 사각형의 갯수
		char[][] map = star(N); // 별을 찍기 위한 재귀

		for (int i = 0; i < N * 4 - 3; i++) {
			for (int j = 0; j < N * 4 - 3; j++)
				sb.append(map[i][j]);
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	// 별을 찍는 메서드
	static char[][] star(int n) {
		char[][] small = new char[n * 4 - 3][n * 4 - 3]; // 배열 생성

		if (n == 1) { // 최소 크기
			small[0][0] = '*';
		} else {
			char[][] tmp = star(n - 1); // 1 단계 작은 배열 재귀로 생성
			for (int i = 0; i < n * 4 - 3; i++) {
				for (int j = 0; j < n * 4 - 3; j++) {
					if (1 < i && i < n * 4 - 5 && 1 < j && j < n * 4 - 5)
						small[i][j] = tmp[i - 2][j - 2]; // 배열 가운데에 1 단계 작은 배열 입력
					else if (i == 0 || j == 0 || i == n * 4 - 4 || j == n * 4 - 4)
						small[i][j] = '*'; // 배열 테두리 별 찍기
					else
						small[i][j] = ' '; // 테두리 바로 안쪽 빈 공간 찍기
				}
			}
		}

		return small; // 별이 찍힌 배열 리턴
	}
}
