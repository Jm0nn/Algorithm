package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프라인을 설치할 수 있는 최댓값을 구하는 문제
public class G2_3109_빵집 {

	static int R, C, cnt; // 행, 열, 파이프라인 개수
	static boolean[][] pipe; // 파이프라인 배열, 건물이 있거나 파이프가 연결되면 true

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		pipe = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				if (s.charAt(j) == 'x')
					pipe[i][j] = true; // 건물이 있는 곳 true
			}
		}

		for (int i = 0; i < R; i++)
			if (recur(i, 0)) // 행을 돌아가며 탐색
				cnt++; // 연결하면 개수 증가

		System.out.println(cnt);
	}

	// r: 현재 행, c: 현재 열
	static boolean recur(int r, int c) {
		if (c == C - 1)
			return true; // 마지막 행에 도착했다면 true

		pipe[r][c] = true; // 현재 지점 파이프라인 연결

		for (int i = 0; i < 3; i++) {
			int nr = r - 1 + i; // 다음 연결할 행

			if (0 <= nr && nr < R && !pipe[nr][c + 1])
				if (recur(nr, c + 1)) // 범위를 벗어나지 않고 연결할 수 있는 곳으로 탐색
					return true;
		}

		return false;
	}

}
