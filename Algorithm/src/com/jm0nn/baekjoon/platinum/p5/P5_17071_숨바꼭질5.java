package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5_17071_숨바꼭질5 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		boolean[][] visit = new boolean[500_001][2];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { n, 0, 0 });
		visit[n][0] = true;

		int nk = -1;
		int t = 0;
		int cnt = 0;
		int answer = -1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int move = cur[1];

			if (cnt < move) {
				cnt = move;

				nk = k;
				if ((k += ++t) > 500_000)
					break;
			}

			if (x == k || (cur[2] == 1 && nk <= x && x <= k)) {
				answer = move;
				break;
			}

			int nx = x + 1;
			if (nx <= 500_000 && !visit[nx][(move + 1) % 2]) {
				queue.offer(new int[] { nx, move + 1, 1 });
				visit[nx][(move + 1) % 2] = true;
			}

			nx = x - 1;
			if (nx >= 0 && !visit[nx][(move + 1) % 2]) {
				queue.offer(new int[] { nx, move + 1, 1 });
				visit[nx][(move + 1) % 2] = true;
			}

			nx = x * 2;
			if (nx <= 500_000 && (!visit[nx][0] || !visit[nx][1])) {
				queue.offer(new int[] { nx, move + 1, 0 });
				visit[nx][0] = true;
				visit[nx][1] = true;
			}
		}

		System.out.println(answer);
	}

}
