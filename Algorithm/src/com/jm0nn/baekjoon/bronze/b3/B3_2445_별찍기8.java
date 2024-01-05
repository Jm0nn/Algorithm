package com.jm0nn.baekjoon.bronze.b3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B3_2445_별찍기8 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				sb.append('*');
			}
			for (int j = (N - i - 1) * 2; j > 0; j--) {
				sb.append(' ');
			}
			for (int j = 0; j <= i; j++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				sb.append('*');
			}
			for (int j = (N - i - 1) * 2; j > 0; j--) {
				sb.append(' ');
			}
			for (int j = 0; j <= i; j++) {
				sb.append('*');
			}
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}
