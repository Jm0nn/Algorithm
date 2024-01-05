package com.jm0nn.baekjoon.platinum.p4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 그래프에서 정점을 제거했을 때 그래프가 두 개 이상으로 나누어지는 정점을 구하는 문제
public class P4_11400_단절선 {

	// 노드 클래스
	static class Node implements Comparable<Node> {
		int from, to;
		Node next;

		Node(int from, int to, Node next) {
			this.from = from;
			this.to = to;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.from != o.from ? this.from - o.from : this.to - o.to;
		}
	}

	static int order; // 방문 순서
	static int[] visit; // 방문 순서 배열
	static Node[] graph; // 그래프

	static Queue<Node> cutEdge = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		visit = new int[V + 1];
		graph = new Node[V + 1];

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			// 간선 정보 그래프에 입력
			graph[A] = new Node(A, B, graph[A]);
			graph[B] = new Node(B, A, graph[B]);
		}

		// 방문하지 않은 정점이라면 dfs로 탐색
		for (int i = 1; i <= V; ++i) {
			if (visit[i] == 0)
				dfs(i, 0);
		}

		// 단절선 개수 및 리스트 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cutEdge.size()).append('\n');
		while (!cutEdge.isEmpty()) {
			Node node = cutEdge.poll();
			sb.append(node.from).append(' ').append(node.to).append('\n');
		}
		System.out.println(sb);
	}

	static int dfs(int cur, int parent) {
		visit[cur] = ++order; // 방문 순서 저장
		int ret = visit[cur];

		for (Node node = graph[cur]; node != null; node = node.next) {
			int next = node.to;

			// 방문했던 길은 제외
			if (next == parent)
				continue;

			// 탐색된 정점의 방문 순서 중 최솟값을 찾음
			if (visit[next] > 0) {
				ret = Math.min(ret, visit[next]);
				continue;
			}

			int prev = dfs(next, cur);

			// 자식 노드의 방문 순서가 현재 노드의 방문 순서보다 크다면 단절선
			if (prev > visit[cur]) {
				if (cur < next)
					cutEdge.offer(new Node(cur, next, null));
				else
					cutEdge.offer(new Node(next, cur, null));
			}

			ret = Math.min(ret, prev);
		}

		return ret;
	}

}
