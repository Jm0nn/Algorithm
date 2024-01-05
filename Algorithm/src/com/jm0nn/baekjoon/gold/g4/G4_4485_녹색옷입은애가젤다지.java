package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_4485_녹색옷입은애가젤다지 {

	static class Node implements Comparable<Node> {
		int x, y;
		int weight;
		Node next;

		public Node(int x, int y, int weight, Node next) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n, tc = 0;
		int INF = Integer.MAX_VALUE;
		int[][] cave;
		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		Node[][] list;

		while ((n = Integer.parseInt(br.readLine())) > 0) {
			sb.append("Problem ").append(++tc).append(": ");

			cave = new int[n][n];
			list = new Node[n][n];

			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; ++j)
					cave[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					for (int d = 0; d < 4; ++d) {
						int ni = i + deltas[d][0];
						int nj = j + deltas[d][1];

						if (0 > ni || ni >= n || 0 > nj || nj >= n)
							continue;

						list[i][j] = new Node(ni, nj, cave[ni][nj], list[i][j]);
					}
				}
			}

			int[][] dist = new int[n][n];
			boolean[][] visit = new boolean[n][n];

			for (int i = 0; i < n; ++i)
				Arrays.fill(dist[i], INF);

			dist[0][0] = cave[0][0];
			Queue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(0, 0, 0, null));

			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				int x = cur.x;
				int y = cur.y;

				if (x == n - 1 && y == n - 1)
					break;

				if (visit[x][y])
					continue;

				visit[x][y] = true;

				for (Node tmp = list[x][y]; tmp != null; tmp = tmp.next) {
					int nx = tmp.x;
					int ny = tmp.y;
					int nw = tmp.weight;

					if (dist[nx][ny] > dist[x][y] + nw) {
						dist[nx][ny] = dist[x][y] + nw;
						pq.offer(new Node(nx, ny, dist[nx][ny], null));
					}
				}
			}

			sb.append(dist[n - 1][n - 1]).append('\n');
		}

		System.out.println(sb);
	}

}
