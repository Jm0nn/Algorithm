package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 흑백 영상을 압축하여 쿼드트리로 표현하는 문제
public class S1_1992_쿼드트리 {

	static int n; // 영상 크기
	static int[][] video; // 영상 배열

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		video = new int[n][n];

		int sum = 0; // 영상 배열 합
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				video[i][j] = line.charAt(j) - '0';
				sum += video[i][j];
			}
		}
		if (sum == 0)
			sb.append(0); // 영상이 모두 0이면 0만 출력
		else if (sum == n * n)
			sb.append(1); // 영상이 모두 1이면 1만 출력
		else
			cutVideo(0, 0, n); // 영상을 4등분

		System.out.println(sb);
	}

	// 영상 4등분
	static void cutVideo(int x, int y, int size) {
		int half = size / 2; // 영상 크기 절반
		// 앞뒤로 괄호 출력
		sb.append("(");
		cut(x, y, half); // 왼쪽 위
		cut(x, y + half, half); // 오른쪽 위
		cut(x + half, y, half); // 왼쪽 아래
		cut(x + half, y + half, half); // 오른쪽 아래
		sb.append(")");
	}

	// 4등분된 영상 확인
	static void cut(int x, int y, int size) {
		boolean isWhite = true;
		boolean isBlack = true;

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (video[i][j] == 0)
					isBlack = false;
				else
					isWhite = false;
			}
		}

		if (isWhite) // 모두 0이면
			sb.append(0); // 0 출력
		else if (isBlack) // 모두 1이면
			sb.append(1); // 1 출력
		else // 섞여있으면
			cutVideo(x, y, size); // 다시 4등분
	}
}
