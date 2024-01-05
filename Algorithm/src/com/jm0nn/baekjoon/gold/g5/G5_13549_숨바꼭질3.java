package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_13549_숨바꼭질3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		boolean[] visit = new boolean[100001];
		Queue<int[]> queue = new ArrayDeque<>();

		visit[n] = true;
		queue.offer(new int[] { n, 0 });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int cnt = cur[1];

			if (x == k) {
				System.out.println(cnt);
				break;
			}

			int t = x * 2;

			if (t <= 100000 && !visit[t]) {
				visit[t] = true;
				queue.offer(new int[] { t, cnt });
			}

			int b = x - 1;

			if (b >= 0 && !visit[b]) {
				visit[b] = true;
				queue.offer(new int[] { b, cnt + 1 });
			}

			int f = x + 1;

			if (f <= 100000 && !visit[f]) {
				visit[f] = true;
				queue.offer(new int[] { f, cnt + 1 });
			}

		}
	}

}
