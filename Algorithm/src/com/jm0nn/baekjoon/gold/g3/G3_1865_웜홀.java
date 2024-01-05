package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_1865_웜홀 {

	static class Node {
		int end;
		int time;
		Node next;

		Node(int end, int time, Node next) {
			this.end = end;
			this.time = time;
			this.next = next;
		}
	}

	static final int INF = 99999999;

	static int N, M, W;
	static int[] dist = new int[501];
	static Node[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			graph = new Node[N + 1];

			int cnt = M + W;
			while (cnt-- > 0) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				if (cnt < W) {
					graph[S] = new Node(E, -T, graph[S]);
				} else {
					graph[S] = new Node(E, T, graph[S]);
					graph[E] = new Node(S, T, graph[E]);
				}
			}

			sb.append(bf() ? "YES\n" : "NO\n");
		}

		System.out.println(sb);
	}

	static boolean bf() {
		for (int i = 1; i <= N; ++i)
			dist[i] = INF;

		for (int i = 1; i <= N; ++i) {
			boolean update = false;

			for (int start = 1; start <= N; ++start) {
				for (Node n = graph[start]; n != null; n = n.next) {
					int end = n.end;
					int time = n.time;

					if (dist[end] > dist[start] + time) {
						dist[end] = dist[start] + time;
						update = true;

						if (i == N)
							return true;
					}
				}
			}

			if (!update)
				break;
		}

		return false;
	}

}
