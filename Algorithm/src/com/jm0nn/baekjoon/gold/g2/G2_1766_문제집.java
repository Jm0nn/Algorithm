package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G2_1766_문제집 {

	static int n, m;
	static Node[] adjList;
	static int[] inDegree;

	static class Node {
		int vertex;
		Node link;

		Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adjList = new Node[n + 1];
		inDegree = new int[n + 1];

		int from, to;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}

		topologySort();

		System.out.println(sb);
	}

	static void topologySort() {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 1; i <= n; i++)
			if (inDegree[i] == 0)
				queue.offer(i);

		while (!queue.isEmpty()) {
			Integer curNode = queue.poll();
			sb.append(curNode).append(' ');
			for (Node temp = adjList[curNode]; temp != null; temp = temp.link)
				if (--inDegree[temp.vertex] == 0)
					queue.offer(temp.vertex);
		}
	}
}
