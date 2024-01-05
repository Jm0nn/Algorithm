package com.jm0nn.baekjoon.platinum.p3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 열혈강호 1에서 k 명의 사람은 한 명당 일을 두 개씩 할 수 있음
public class P3_11377_열혈강호3 {

	static int n, m, k;
	static List<Integer>[] edge;
	static int[] match;
	static boolean[] visit;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		edge = new List[n + 1];
		match = new int[m + 1];
		visit = new boolean[m + 1];

		for (int i = 1; i <= n; i++) {
			edge[i] = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			while (k-- > 0)
				edge[i].add(Integer.parseInt(st.nextToken()));
		}

		int cnt = 0;
		for (int i = 1; i <= n * 2; i++) {
			Arrays.fill(visit, false);
			if (i <= n) {
				if (dfs(i))
					cnt++;
			} else {
				if (dfs(i - n) && k > 0) {
					--k;
					++cnt;
				}
			}
		}

		System.out.println(cnt);
	}

	static boolean dfs(int from) {
		for (int i = 0; i < edge[from].size(); i++) {
			int to = edge[from].get(i);

			if (visit[to])
				continue;

			visit[to] = true;

			if (match[to] == 0 || dfs(match[to])) {
				match[to] = from;
				return true;
			}
		}

		return false;
	}
}
