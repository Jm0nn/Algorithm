package com.jm0nn.baekjoon.platinum.p3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P3_24515_히히못가 {

	static int n;
	static int[] parent;
	static char[][] field;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		parent = new int[n * n];
		Arrays.fill(parent, -1);

		field = new char[n][n];

		for (int i = 0; i < n; ++i) {
			String input = br.readLine();
			for (int j = 0; j < n; ++j)
				field[i][j] = input.charAt(j);
		}

		int[][] deltas = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (field[i][j] == '.')
					continue;

				for (int d = 0; d < 4; ++d) {
					int ni = i + deltas[d][0];
					int nj = j + deltas[d][1];

					if (0 > ni || ni >= n || 0 > nj || nj >= n)
						continue;

					if (field[i][j] == field[ni][nj])
						union(i * n + j, ni * n + nj);
				}
			}
		}

		System.out.println(dijkstra());
	}

	static int find(int a) {
		return parent[a] < 0 ? a : (parent[a] = find(parent[a]));
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			parent[rootA] += parent[rootB];
			parent[rootB] = rootA;
		}
	}

	static int cnt(int a) {
		return -parent[find(a)];
	}

	static int dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
		int INF = Integer.MAX_VALUE;
		int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

		int[][] dist = new int[n][n];
		for (int i = 0; i < n; ++i)
			Arrays.fill(dist[i], INF);

		for (int x = 1; x < n; ++x)
			pq.offer(new int[] { dist[x][0] = cnt(x * n), x, 0 });

		for (int y = 1; y < n - 1; ++y)
			pq.offer(new int[] { dist[n - 1][y] = cnt((n - 1) * n + y), n - 1, y });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cDist = cur[0];
			int x = cur[1];
			int y = cur[2];

			if (dist[x][y] != cDist)
				continue;

			if (x == 0 || y == n - 1)
				return cDist;

			for (int d = 0; d < 8; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (0 > nx || nx >= n || 0 > ny || ny >= n)
					continue;

				if (field[nx][ny] == '.')
					continue;

				int cost = find(x * n + y) != find(nx * n + ny) ? cnt(nx * n + ny) : 0;

				if (dist[nx][ny] > cDist + cost)
					pq.offer(new int[] { dist[nx][ny] = cDist + cost, nx, ny });
			}
		}

		return 0;
	}

}
