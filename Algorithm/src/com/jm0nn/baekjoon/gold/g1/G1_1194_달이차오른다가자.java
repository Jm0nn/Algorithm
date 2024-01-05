package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_1194_달이차오른다가자 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];
		int[] player = new int[4];

		for (int i = 0; i < n; ++i) {
			String input = br.readLine();
			for (int j = 0; j < m; ++j) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '0') {
					player[0] = i;
					player[1] = j;
					player[3] = 1 << 6;
				}
			}
		}

		boolean escape = false;
		int min = 0;
		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		int[][] visit = new int[n][m];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(player);
		visit[player[0]][player[1]] = player[3];

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			int cnt = cur[2];
			int key = cur[3];

			if (map[x][y] == '1') {
				min = cnt;
				escape = true;
				break;
			}

			for (int d = 0; d < 4; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				int nkey = key;

				if (0 > nx || nx >= n || 0 > ny || ny >= m)
					continue;

				if (map[nx][ny] == '#')
					continue;

				boolean flag = true;

				for (int i = 0; i < 7; ++i) {
					if (((visit[nx][ny] >> i) & 1) == 0 && ((nkey >> i) & 1) == 1) {
						flag = false;
					}
				}

				if (flag)
					continue;

				if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
					nkey |= 1 << map[nx][ny] - 'a';
				} else if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') {
					if (((nkey >> map[nx][ny] - 'A') & 1) != 1)
						continue;
				}

				visit[nx][ny] = nkey;

				queue.offer(new int[] { nx, ny, cnt + 1, nkey });
			}
		}

		System.out.println(escape ? min : -1);
	}

}
