package com.jm0nn.baekjoon.silver.s5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_7568_덩치 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] person = new int[n][3];

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			person[i][0] = Integer.parseInt(st.nextToken());
			person[i][1] = Integer.parseInt(st.nextToken());

			for (int j = 0; j < i; ++j) {
				if (person[i][0] > person[j][0] && person[i][1] > person[j][1])
					++person[j][2];
				else if (person[i][0] < person[j][0] && person[i][1] < person[j][1])
					++person[i][2];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i)
			sb.append(++person[i][2]).append(' ');
		System.out.println(sb);
	}

}
