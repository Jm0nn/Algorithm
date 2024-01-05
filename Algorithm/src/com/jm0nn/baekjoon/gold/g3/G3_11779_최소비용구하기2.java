package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G3_11779_최소비용구하기2 {

	static class Node implements Comparable<Node> {
		int num;
		int cost;
		Node next;
		List<Integer> list;

		Node(int num, int cost, Node next, List<Integer> list) {
			this.num = num;
			this.cost = cost;
			this.next = next;
			this.list = list;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		Node[] graph = new Node[n + 1];

		int m = Integer.parseInt(br.readLine());

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[s] = new Node(e, c, graph[s], null);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>();
		List<Integer> list = new ArrayList<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visit = new boolean[n + 1];

		dist[start] = 0;
		list.add(start);
		pq.offer(new Node(start, 0, null, list));

		List<Integer> resList = null;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.num;
			List<Integer> curList = cur.list;

			if (num == end) {
				resList = curList;
				break;
			}

			if (visit[num])
				continue;

			visit[num] = true;

			for (Node node = graph[num]; node != null; node = node.next) {
				int x = node.num;
				int w = node.cost;
				List<Integer> tlist = new ArrayList<>(curList);

				if (dist[x] > dist[num] + w) {
					dist[x] = dist[num] + w;
					tlist.add(x);
					pq.offer(new Node(x, dist[x], null, tlist));
				}
			}
		}

		int cnt = resList.size();

		StringBuilder sb = new StringBuilder();
		sb.append(dist[end]).append('\n');
		sb.append(cnt).append('\n');
		for (int i = 0; i < cnt; ++i)
			sb.append(resList.get(i)).append(' ');
		System.out.println(sb);
	}

}
