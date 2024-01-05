package com.jm0nn.baekjoon.bronze.b5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B5_2741_N찍기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++)
			sb.append(i).append('\n');

		System.out.println(sb);
	}

}
