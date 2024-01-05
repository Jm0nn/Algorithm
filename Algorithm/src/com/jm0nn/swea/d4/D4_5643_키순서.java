package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 두 학생의 키를 비교한 결과가 주어질 때 키가 몇 번째인지 알 수 있는 학생들이 몇 명인지 구하는 문제
public class D4_5643_키순서 {

	// 인접 리스트 노드
	static class Node {
		int num;
		Node next;

		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}

	static int n, m; // 학생의 수, 키를 비교하는 수
	static int[] comp; // i번 학생이 키를 비교한 횟수
	static boolean[] visit; // bfs 방문 배열
	static Node[] height; // 인접 리스트

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= t; ++tc) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());

			comp = new int[n + 1];
			height = new Node[n + 1];

			while (m-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// b 학생이 a 학생보다 키가 큼
				height[a] = new Node(b, height[a]);
			}

			// 1번부터 n번까지 학생들의 키를 비교함
			for (int i = 1; i <= n; ++i)
				bfs(i);

			int cnt = 0; // 순위를 구할 수 있는 학생의 수
			for (int i = 1; i <= n; ++i) {
				if (comp[i] == n + 1)
					++cnt; // 비교를 n+1번(n-1명의 학생 + 자기 자신 2번) 했다면 순위를 구할 수 있음
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}

	static void bfs(int i) {
		visit = new boolean[n + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		visit[i] = true;
		queue.offer(i);

		while (!queue.isEmpty()) {
			int num = queue.poll();

			// i번 학생과 num번 학생의 키를 비교함
			// 자기 자신과도 비교가 되기 때문에 총 비교 횟수가 n+1번이 되면 키의 순위를 구할 수 있음
			++comp[i];
			++comp[num];

			for (Node cur = height[num]; cur != null; cur = cur.next) {
				if (visit[cur.num])
					continue;

				visit[cur.num] = true;
				queue.offer(cur.num);
			}
		}
	}

}
