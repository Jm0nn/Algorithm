package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_1600_말이되고픈원숭이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int k = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		int[][] map = new int[h][w];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] horse = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
		int[][] monkey = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

		Queue<int[]> q = new ArrayDeque<>();
		int[][] visit = new int[h][w];

		q.offer(new int[] { 0, 0, 0, 0 });
		for (int i = 0; i <= k; ++i)
			visit[0][0] += 1 << i;

		int ans = -1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int move = cur[2];
			int cnt = cur[3];

			if (x == h - 1 && y == w - 1) {
				ans = move;
				break;
			}

			if (cnt < k) {
				for (int d = 0; d < 8; d++) {
					int nx = x + horse[d][0];
					int ny = y + horse[d][1];
					int ncnt = cnt + 1;

					if (0 > nx || nx >= h || 0 > ny || ny >= w)
						continue;

					if (map[nx][ny] == 1)
						continue;

					if (((visit[nx][ny] >> ncnt) & 1) == 1)
						continue;

					for (int i = ncnt; i <= k; ++i)
						visit[nx][ny] |= (1 << i);

					q.offer(new int[] { nx, ny, move + 1, ncnt });
				}
			}

			for (int d = 0; d < 4; d++) {
				int nx = x + monkey[d][0];
				int ny = y + monkey[d][1];

				if (0 > nx || nx >= h || 0 > ny || ny >= w)
					continue;

				if (map[nx][ny] == 1)
					continue;

				if (((visit[nx][ny] >> cnt) & 1) == 1)
					continue;

				for (int i = cnt; i <= k; ++i)
					visit[nx][ny] |= (1 << i);

				q.offer(new int[] { nx, ny, move + 1, cnt });
			}
		}

		System.out.println(ans);
	}

}
