package com.jm0nn.baekjoon.platinum.p4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그래프에서 정점을 제거했을 때 그래프가 두 개 이상으로 나누어지는 정점을 구하는 문제
public class P4_11266_단절점 {

	// 노드 클래스
	static class Node {
		int no;
		Node next;

		Node(int no, Node next) {
			this.no = no;
			this.next = next;
		}
	}

	static int order; // 방문 순서
	static int[] visit; // 방문 순서 배열
	static Node[] graph; // 그래프

	static boolean[] cutPoint; // 단절점 여부

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		visit = new int[V + 1];
		graph = new Node[V + 1];

		cutPoint = new boolean[V + 1];

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			// 간선 정보 그래프에 입력
			graph[A] = new Node(B, graph[A]);
			graph[B] = new Node(A, graph[B]);
		}

		// 방문하지 않은 정점이라면 dfs로 탐색
		for (int i = 1; i <= V; ++i) {
			if (visit[i] == 0)
				dfs(i, true);
		}

		int cnt = 0; // 단절점 개수
		StringBuilder sb = new StringBuilder(); // 단절점 리스트 출력
		for (int i = 1; i <= V; ++i) {
			if (cutPoint[i]) {
				++cnt;
				sb.append(i).append(' ');
			}
		}

		System.out.println(cnt);
		System.out.println(sb);
	}

	static int dfs(int cur, boolean root) {
		visit[cur] = ++order; // 방문 순서 저장
		int ret = visit[cur];

		int childCnt = 0; // 자식 노드의 개수

		for (Node node = graph[cur]; node != null; node = node.next) {
			int next = node.no;

			// 탐색된 정점의 방문 순서 중 최솟값을 찾음
			if (visit[next] > 0) {
				ret = Math.min(ret, visit[next]);
				continue;
			}

			++childCnt;

			int prev = dfs(next, false);

			// 현재 노드가 루트가 아니면서
			// 자식 노드들이 현재 노드를 거치지 않고
			// 방문 순서가 더 빠른 정점으로 갈 수 없다면 단절점
			if (!root && prev >= visit[cur])
				cutPoint[cur] = true;

			ret = Math.min(ret, prev);
		}

		// 현재 노드가 루트이면서 자식 개수가 2개 이상이면 단절점
		if (root && childCnt >= 2)
			cutPoint[cur] = true;

		return ret;
	}

}
