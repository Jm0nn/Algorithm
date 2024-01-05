package com.jm0nn.baekjoon.unranked;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class U_2556_별찍기14 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append('*');
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}
