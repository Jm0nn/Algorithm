package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_14889_스타트와링크 {
	
	static int n;
	static int[][] s;
	static boolean[] start;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		s = new int[n][n];
		start = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				s[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(min);
		br.close();
	}

	static void dfs(int idx, int depth) {
		if (depth == n / 2) {
			int startSum = 0;
			int linkSum = 0;
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					if (start[i] && start[j])
						startSum += (s[i][j] + s[j][i]);
					else if (!start[i] && !start[j])
						linkSum += (s[i][j] + s[j][i]);
				}
			}
			int sub = Math.abs(startSum - linkSum);
			if (sub == 0) {
				System.out.println(sub);
				System.exit(0);
			}
			min = Math.min(min, sub);

		} else {
			for (int i = idx; i < n; i++) {
				if (!start[i]) {
					start[i] = true;
					dfs(i + 1, depth + 1);
					start[i] = false;
				}
			}
		}
	}
}
