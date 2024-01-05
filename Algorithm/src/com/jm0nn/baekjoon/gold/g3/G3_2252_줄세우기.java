package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 일부 학생들의 키를 비교한 정보가 주어졌을 때 전체 학생들의 줄을 세우는 문제
public class G3_2252_줄세우기 {

	static int n, m; // 정점의 개수, 간선의 개수
	static Node[] adjList; // 인접 리스트
	static int[] inDegree; // 진입 차수

	// 정점 클래스
	static class Node {
		int vertex;
		Node link;

		Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adjList = new Node[n + 1]; // 정점 번호 1부터 시작
		inDegree = new int[n + 1]; // 인덱스 번호에 해당하는 정점의 진입 차수

		int from, to; // 연결된 정점 정보
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, adjList[from]); // 유향 그래프
			inDegree[to]++;
		}

		List<Integer> list = topologySort(); // 학생 줄세우기

		// 줄세운 학생 출력
		StringBuilder sb = new StringBuilder();
		for (Integer vertex : list)
			sb.append(vertex).append(' ');
		System.out.println(sb);
	}

	static List<Integer> topologySort() {
		// 위상 정렬된 결과 저장
		List<Integer> orderList = new ArrayList<>();

		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= n; i++)
			// 1. 진입 차수가 0인 노드(시작점)를 큐에 모두 넣는다.
			if (inDegree[i] == 0)
				queue.offer(i);

		while (!queue.isEmpty()) {
			// 2. 큐에서 진입 차수가 0인 노드를 꺼내어 자신과 인접한 노드의 간선을 제거한다.
			Integer curNode = queue.poll();
			orderList.add(curNode); // BFS 해야 할 일 처리

			for (Node temp = adjList[curNode]; temp != null; temp = temp.link)
				// 2. 인접한 노드의 진입 차수를 1 감소시킨다.
				if (--inDegree[temp.vertex] == 0)
					// 3. 간선 제거 후 진입차수가 0이 된 노드를 큐에 넣는다.
					queue.offer(temp.vertex);
		}

		return orderList;
	}
}
