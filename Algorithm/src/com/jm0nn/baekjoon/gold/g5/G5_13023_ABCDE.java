package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_13023_ABCDE {

	static boolean right;
	static boolean[] visit;
	static List<Integer>[] friend;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		friend = new ArrayList[n];
		visit = new boolean[n];

		for (int i = 0; i < n; i++)
			friend[i] = new ArrayList<>();

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			friend[a].add(b);
			friend[b].add(a);
		}

		for (int i = 0; i < n; i++) {
			dfs(1, i);
			if (right)
				break;
		}

		System.out.println(right ? 1 : 0);
	}

	static void dfs(int depth, int idx) {
		if (depth == 5) {
			right = true;
			return;
		}

		visit[idx] = true;
		for (int i : friend[idx]) {
			if (!visit[i]) {
				dfs(depth + 1, i);
				if (right)
					return;
			}
		}
		visit[idx] = false;
	}

}
