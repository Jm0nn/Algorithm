package com.jm0nn.baekjoon.silver.s4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S4_13015_별찍기23 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int frontBlank = 0;
		int midBlank = N * 2 - 3;
		int xBlank = N - 2;

		for (int i = 0; i < N * 2 - 1; i++) {
			if (i == 0 || i == N * 2 - 2) {
				for (int j = 0; j < N; j++)
					sb.append('*');

				for (int j = 0; j < midBlank; j++)
					sb.append(' ');

				for (int j = 0; j < N; j++)
					sb.append('*');
			} else {
				for (int j = 0; j < frontBlank; j++)
					sb.append(' ');

				sb.append('*');

				for (int j = 0; j < xBlank; j++)
					sb.append(' ');

				sb.append('*');

				if (midBlank > 0) {
					for (int j = 0; j < midBlank; j++)
						sb.append(' ');

					sb.append('*');
				}

				for (int j = 0; j < xBlank; j++)
					sb.append(' ');

				sb.append('*');
			}

			if (i < N - 1) {
				frontBlank++;
				midBlank -= 2;
			} else {
				frontBlank--;
				midBlank += 2;
			}

			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}

}
