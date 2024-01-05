package com.jm0nn.baekjoon.platinum.p4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4_2514_자동분무기 {

	static int m, k;
	static int[][] field = new int[8][8];
	static int[][] nfield = new int[8][8];
	static char[][] spray = new char[8][8];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		m = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		for (int i = 0; i < 8; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++)
				field[i][j] = Integer.parseInt(st.nextToken()) - m;

			Arrays.fill(spray[i], '.');
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++)
					nfield[i][j] += field[i][k] + field[k][j];
				nfield[i][j] -= field[i][j];
				
					
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				sb.append(spray[i][j]).append(' ');
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}

}
