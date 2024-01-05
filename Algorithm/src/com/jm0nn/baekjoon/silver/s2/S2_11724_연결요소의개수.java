package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_11724_연결요소의개수 {

	static int n, m;
	static int cnt;
	static Vertex[] graph;
	static boolean[] visited;

	static class Vertex {
		int num;
		Vertex next;

		public Vertex(int num, Vertex next) {
			this.num = num;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new Vertex[n + 1];
		visited = new boolean[n + 1];

		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a] = new Vertex(b, graph[a]);
			graph[b] = new Vertex(a, graph[b]);
		}

		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				cnt++;
				bfs(i);
			}
		}

		System.out.println(cnt);
	}

	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Vertex v = graph[cur]; v != null; v = v.next) {
				if (visited[v.num])
					continue;

				q.offer(v.num);
				visited[v.num] = true;
			}
		}
	}
}
