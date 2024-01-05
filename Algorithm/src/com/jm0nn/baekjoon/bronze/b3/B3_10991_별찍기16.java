package com.jm0nn.baekjoon.bronze.b3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B3_10991_별찍기16 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 별 모양 층수

		int blank = N - 1; // 한 행의 앞 부분 빈 공간 갯수
		for (int i = 0; i < N; i++) {
			for (int j = blank--; j > 0; j--) // 한 행의 앞 부분 빈 공간 출력
				sb.append(' ');

			for (int j = 0; j < i * 2 + 1; j++) {
				if (j % 2 == 1) // 빈 공간 출력
					sb.append(' ');
				else // 별 출력
					sb.append('*');
			}

			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}
