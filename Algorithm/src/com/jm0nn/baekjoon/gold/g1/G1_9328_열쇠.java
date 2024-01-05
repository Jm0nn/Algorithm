package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_9328_열쇠 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			char[][] map = new char[h][w];
			int[] player = new int[4];

			List<int[]> start = new ArrayList<>();

			int doc = 0;

			for (int i = 0; i < h; ++i) {
				String input = br.readLine();
				for (int j = 0; j < w; ++j) {
					map[i][j] = input.charAt(j);

					if (map[i][j] == '$')
						++doc;

					if ((i == 0 || i == h - 1 || j == 0 || j == w - 1) && map[i][j] != '*')
						start.add(new int[] { i, j });
				}
			}

			String first = br.readLine();
			if (!first.equals("0")) {
				int len = first.length();
				for (int i = 0; i < len; ++i)
					player[2] |= 1 << first.charAt(i) - 'a';
			}

			int max = 0;
			int play = start.size();

			for (int p = 0; p < play; ++p) {
				int[] s = start.get(p);
				player[0] = s[0];
				player[1] = s[1];

				if ('A' <= map[s[0]][s[1]] && map[s[0]][s[1]] <= 'Z') {
					if (((player[2] >> map[s[0]][s[1]] - 'A') & 1) != 1)
						continue;
				}

				int count = 0;
				int[][][] visit = new int[h][w][doc];
				Queue<int[]> queue = new ArrayDeque<>();
				queue.offer(player);
				visit[player[0]][player[1]][0] = player[2];

				while (!queue.isEmpty()) {
					int[] cur = queue.poll();
					int x = cur[0];
					int y = cur[1];
					int key = cur[2];
					int cnt = cur[3];

					if (count < cnt)
						count = cnt;

					for (int d = 0; d < 4; ++d) {
						int nx = x + deltas[d][0];
						int ny = y + deltas[d][1];
						int nkey = key;

						if (0 > nx || nx >= h || 0 > ny || ny >= w)
							continue;

						if (map[nx][ny] == '*')
							continue;

						boolean flag = true;

						for (int c = 0; c < cnt; ++c) {
							for (int i = 0; i < 26; ++i) {
								if (((visit[nx][ny][c] >> i) & 1) == 0 && ((nkey >> i) & 1) == 1) {
									flag = false;
								}
							}
						}

						if (flag)
							continue;

						if ('a' <= map[nx][ny] && map[nx][ny] <= 'z') {
							nkey |= 1 << map[nx][ny] - 'a';
						} else if ('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {
							if (((nkey >> map[nx][ny] - 'A') & 1) != 1)
								continue;
						}

						for (int c = 0; c < cnt; ++c)
							visit[nx][ny][c] = nkey;

						if (map[nx][ny] == '$')
							queue.offer(new int[] { nx, ny, nkey, cnt + 1 });
						else
							queue.offer(new int[] { nx, ny, nkey, cnt });
					}
				}

				if (max < count)
					max = count;
			}

			sb.append(max).append('\n');
		}

		System.out.println(sb);
	}

}
