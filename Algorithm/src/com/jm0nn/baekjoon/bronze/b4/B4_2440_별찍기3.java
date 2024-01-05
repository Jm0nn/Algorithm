package com.jm0nn.baekjoon.bronze.b4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B4_2440_별찍기3 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - i; j++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
