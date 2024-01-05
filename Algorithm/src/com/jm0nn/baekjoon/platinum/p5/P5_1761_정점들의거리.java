package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P5_1761_정점들의거리 {

	static int n, h; // 정점의 개수, 트리의 높이
	static ArrayList<int[]>[] list; // 트리 내 정점의 연결 리스트
	static int[][] parent; // 정점 i의 j레벨 상위의 부모 노드
	static int[] dist; // 루트 노드로부터 거리
	static int[] depth; // 정점의 깊이

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; ++i)
			list[i] = new ArrayList<>();

		dist = new int[n + 1];

		for (int i = 0; i < n - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new int[] { b, c });
			list[b].add(new int[] { a, c });
		}

		// 트리가 이진 트리일 경우의 높이
		// 편향 트리라면 높이가 더 높을 것이고 (해당 문제에서는 나타나지 않음)
		// 노드 당 여러 개의 자식이 있을 경우 높이가 더 낮을 수 있음
		h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;

		depth = new int[n + 1];
		parent = new int[n + 1][h];

		init(1, 1, 0);
		fillParents();

		StringBuilder sb = new StringBuilder();

		int m = Integer.parseInt(br.readLine());

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int lca = LCA(a, b);

			// LCA를 통해 두 정점 간의 거리 계산
			sb.append(dist[a] + dist[b] - 2 * dist[lca]).append('\n');
		}

		System.out.println(sb);
	}

	// 트리의 간선 정보를 통해 depth, parent 정보 구함
	static void init(int cur, int h, int p) {
		depth[cur] = h;

		for (int[] next : list[cur]) {
			if (next[0] != p) {
				dist[next[0]] = dist[cur] + next[1];
				init(next[0], h + 1, cur);
				parent[next[0]][0] = cur;
			}
		}
	}

	// init을 통해 parents 배열의 나머지도 채워줌
	static void fillParents() {
		for (int i = 1; i < h; ++i) {
			for (int j = 1; j <= n; ++j) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];
			}
		}
	}

	// LCA 구하기
	static int LCA(int a, int b) {
		// a의 깊이를 더 크게 바꿔줌
		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}

		// a와 b의 높이를 맞춤
		for (int i = h - 1; i >= 0; --i) {
			if (Math.pow(2, i) <= depth[a] - depth[b])
				a = parent[a][i];
		}

		// a와 b가 같다면 깊이가 낮은 노드가 LCA
		if (a == b)
			return a;

		// 같은 높이에서 LCA 찾기
		for (int i = h - 1; i >= 0; --i) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}

		return parent[a][0];
	}

}
