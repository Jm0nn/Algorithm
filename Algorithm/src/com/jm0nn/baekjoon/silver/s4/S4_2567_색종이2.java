package com.jm0nn.baekjoon.silver.s4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 색종이가 붙은 영역의 넓이를 구하는 문제
public class S4_2567_색종이2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 색종이를 붙일 배열, 붙어있으면 true, 그렇지 않으면 false
		boolean[][] paper = new boolean[101][101];

		int n = Integer.parseInt(br.readLine()); // 종이의 개수

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 색종이의 왼쪽 아래 좌표
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 색종이가 붙은 영역 true로 설정
			for (int j = x; j < x + 10; j++)
				Arrays.fill(paper[j], y, y + 10, true);
		}

		int count = 0; // 색종이가 붙은 영역의 테두리 길이

		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				// 색종이가 붙은 영역
				if (paper[i][j]) {
					// 왼쪽이 안붙어있다면 길이 증가
					if (i == 0 || !paper[i - 1][j])
						count++;

					// 오른쪽이 안붙어있다면 길이 증가
					if (i == 100 || !paper[i + 1][j])
						count++;

					// 아래가 안붙어있다면 길이 증가
					if (j == 0 || !paper[i][j - 1])
						count++;

					// 위가 안붙어있다면 길이 증가
					if (j == 100 || !paper[i][j + 1])
						count++;
				}
			}
		}

		System.out.println(count);

		br.close();
	}

}
