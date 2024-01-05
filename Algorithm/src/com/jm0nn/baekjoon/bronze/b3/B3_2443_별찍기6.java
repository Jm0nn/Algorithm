package com.jm0nn.baekjoon.bronze.b3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B3_2443_별찍기6 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N - i - 1; j++) {
				sb.append(' ');
			}
			for (int j = 0; j < i * 2 + 1; j++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
