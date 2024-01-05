package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4012_요리사 {

	static int n, min;
	static int[][] s;
	static boolean[] choice;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine());
			s = new int[n][n];
			choice = new boolean[n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					s[i][j] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;

			comb(0, 0);

			sb.append(min).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}

	static void comb(int cnt, int idx) {
		if (cnt == n / 2) {
			int aSum = 0;
			int bSum = 0;

			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					if (choice[i] && choice[j])
						aSum += (s[i][j] + s[j][i]);
					else if (!choice[i] && !choice[j])
						bSum += (s[i][j] + s[j][i]);
				}
			}

			int sub = Math.abs(aSum - bSum);

			if (sub < min)
				min = sub;

			return;
		}

		for (int i = idx; i < n; i++) {
			if (choice[i])
				continue;

			choice[i] = true;
			comb(cnt + 1, i + 1);
			choice[i] = false;
		}
	}

}
