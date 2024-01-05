package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_1238_파티 {

	static class Node implements Comparable<Node> {
		int end;
		int time;
		Node next;

		Node(int end, int time, Node next) {
			this.end = end;
			this.time = time;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		Node[] toHome = new Node[N + 1];
		Node[] toParty = new Node[N + 1];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			toParty[A] = new Node(B, T, toParty[A]);
			toHome[B] = new Node(A, T, toHome[B]);
		}

		int[] party = dijkstra(N, X, toParty);
		int[] home = dijkstra(N, X, toHome);

		int max = 0;
		for (int i = 1; i <= N; ++i) {
			if (max < party[i] + home[i])
				max = party[i] + home[i];
		}

		System.out.println(max);
	}

	static int[] dijkstra(int N, int X, Node[] graph) {
		int[] times = new int[N + 1];
		Arrays.fill(times, Integer.MAX_VALUE);

		times[X] = 0;
		boolean[] visit = new boolean[N + 1];
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(X, 0, null));

		int cnt = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int end = cur.end;

			if (visit[end])
				continue;

			visit[end] = true;

			for (Node n = graph[end]; n != null; n = n.next) {
				int newTime = cur.time + n.time;

				if (times[n.end] > newTime) {
					times[n.end] = newTime;
					pq.offer(new Node(n.end, newTime, null));
				}
			}

			if (++cnt == N)
				break;
		}

		return times;
	}
}
