package com.jm0nn.baekjoon.bronze.b3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B3_2522_별찍기12 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int M = N * 2 - 1;
		int mid = M / 2; // 별 모양 중간점

		int count = N - 2; // 별 카운트
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (j > count)
					sb.append('*');
			}
			sb.append('\n');

			// 한 행 출력이 끝난 후
			if (i < mid) // 별 모양의 아랫부분에서는 별 갯수 감소
				count--;
			else // 윗부분에서는 별 갯수 증가
				count++;
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
