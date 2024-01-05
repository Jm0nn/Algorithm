package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1210_Ladder1 {

	static int[][] radder = new int[100][102]; // 사다리 인덱스를 벗어나지 않기 위해 열을 102로 설정

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 10; t++) {
			int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
			sb.append('#').append(tc).append(' ');

			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 1; j <= 100; j++) {
					radder[i][j] = Integer.parseInt(st.nextToken()); // 사다리 입력
				}
			}

			for (int i = 1; i <= 100; i++) {
				if (radder[0][i] == 1 && ride(i)) { // 사다리 시작지점에서 목표에 도달할 경우
					sb.append(i - 1).append('\n'); // 해당 인덱스 입력
					break;
				}
			}
		}

		System.out.println(sb);

		br.close();
	}

	static boolean ride(int idx) {
		int curIdx = idx; // 현재 열 인덱스

		for (int i = 1; i < 100; i++) {
			int left = curIdx - 1; // 현재 열 왼쪽
			int right = curIdx + 1; // 현재 열 오른쪽

			if (radder[i][left] == 1) { // 왼쪽으로 다리가 있으면
				while (radder[i][left] == 1)
					left--; // 다리가 없을 때까지 이동

				curIdx = left + 1; // 현재 열 인덱스 저장

			} else if (radder[i][right] == 1) { // 오른쪽으로 다리가 있으면
				while (radder[i][right] == 1)
					right++; // 다리가 없을 때까지 이동

				curIdx = right - 1; // 현재 열 인덱스 저장
			}
		}

		if (radder[99][curIdx] == 2)
			return true; // 도착 지점이 목표라면 true 리턴
		else
			return false; // 아니면 false 리턴
	}
}
