package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 트리의 루트가 1이고 트리 상에 연결된 두 정점이 주어질 때 각 노드의 부모 노드를 출력하는 문제
public class S2_11725_트리의부모찾기 {

	static int n; // 노드의 개수
	static int[] parent; // 해당 노드의 부모 노드
	static List<Integer>[] list; // 해당 관계를 가지는 노드의 리스트
	static boolean[] visit; // 방문 배열

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		parent = new int[n + 1];
		list = new ArrayList[n + 1];
		visit = new boolean[n + 1];

		for (int i = 1; i <= n; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 두 노드
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());

			list[n1].add(n2);
			list[n2].add(n1);
		}

		dfs(1); // 루트 노드부터 시작

		// 부모 노드 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++)
			sb.append(parent[i]).append('\n');

		System.out.println(sb);
	}

	// DFS로 모든 노드의 부모 노드 추적, p: 부모 노드
	static void dfs(int p) {
		visit[p] = true; // 부모 노드 방문 추가
		for (int i : list[p]) {
			if (visit[i])
				continue; // 방문한 노드는 넘어감
			parent[i] = p; // i의 부모 노드 p
			dfs(i); // i를 부모 노드로 하는 노드 추적
		}
	}

}
