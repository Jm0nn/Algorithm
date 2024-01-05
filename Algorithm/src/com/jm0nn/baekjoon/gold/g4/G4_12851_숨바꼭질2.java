package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_12851_숨바꼭질2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] visit = new int[100001];
		Arrays.fill(visit, Integer.MAX_VALUE);
		Queue<int[]> queue = new ArrayDeque<>();

		visit[n] = 0;
		queue.offer(new int[] { n, 0 });

		int ans = 0;
		int cnt = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int move = cur[1];

			if (cnt > 0 && ans < move)
				break;

			if (x == k) {
				ans = move;
				++cnt;
			}

			for (int i = 0; i < 3; ++i) {
				int nx = x;

				switch (i) {
				case 0:
					nx *= 2;
					break;

				case 1:
					nx -= 1;
					break;

				case 2:
					nx += 1;
					break;
				}

				if (0 > nx || nx > 100000 || visit[nx] < move)
					continue;

				visit[nx] = move;
				queue.offer(new int[] { nx, move + 1 });
			}
		}

		System.out.println(ans);
		System.out.println(cnt);
	}

}
