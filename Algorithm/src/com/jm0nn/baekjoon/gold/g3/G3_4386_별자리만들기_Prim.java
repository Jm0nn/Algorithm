package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_4386_별자리만들기_Prim {

	static class Node implements Comparable<Node> {
		int no;
		double dist;
		Node next;

		Node(int no, double dist, Node next) {
			this.no = no;
			this.dist = dist;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		double[][] star = new double[n][2];
		Node[] graph = new Node[n];

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());

			for (int j = 0; j < i; ++j) {
				double dx = Math.abs(star[i][0] - star[j][0]);
				double dy = Math.abs(star[i][1] - star[j][1]);
				double dist = Math.sqrt(dx * dx + dy * dy);

				graph[i] = new Node(j, dist, graph[i]);
				graph[j] = new Node(i, dist, graph[j]);
			}
		}

		boolean[] visit = new boolean[n];
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, null));
		double sum = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int no = cur.no;
			double dist = cur.dist;

			if (visit[no])
				continue;

			visit[no] = true;
			sum += dist;

			for (Node node = graph[no]; node != null; node = node.next) {
				if (!visit[node.no])
					pq.offer(node);
			}
		}

		System.out.println(String.format("%.2f", sum));
	}
}
