package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G2_1039_교환 {

	static int k;
	static char[] n, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = st.nextToken().toCharArray();
		k = Integer.parseInt(st.nextToken());

		result = new char[n.length];
		Arrays.fill(result, '0');

		dfs(0);

		if (result[0] == '0') {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n.length; i++)
				sb.append(result[i]);
			System.out.println(sb);
		}
	}

	static void dfs(int depth) {
		if (n[0] == '0')
			return;

		if (depth == k) {
			for (int i = 0; i < n.length; i++) {
				if (n[i] < result[i])
					return;
				else if (n[i] > result[i])
					break;
			}

			for (int i = 0; i < n.length; i++)
				result[i] = n[i];

			return;
		}

		for (int i = 0; i < n.length - 1; i++) {
			for (int j = i + 1; j < n.length; j++) {
				swap(i, j);
				dfs(depth + 1);
				swap(i, j);
			}
		}
	}

	static void swap(int a, int b) {
		char tmp = n[a];
		n[a] = n[b];
		n[b] = tmp;
	}

}
