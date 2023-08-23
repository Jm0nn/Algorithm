import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 그래프의 최소 스패닝 트리를 구하는 문제
public class Main {

	static int v, e; // 정점, 간선의 개수
	static int[] parents; // 서로소 그룹의 대표 정점
	static PriorityQueue<Edge> edgeList; // 간선 리스트

	// 간선 클래스
	static class Edge implements Comparable<Edge> {
		int from, to; // 두 정점
		int weight; // 가중치

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// 가중치의 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		parents = new int[v + 1];
		edgeList = new PriorityQueue<>();

		for (int i = 1; i <= v; i++)
			parents[i] = i;

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edgeList.offer(new Edge(from, to, weight));
		}

		int sum = 0; // 최소 스패닝 트리의 가중치
		int cnt = 0; // 연결된 간선의 개수

		while (!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();

			// 서로소 그룹을 만들었다면
			if (union(edge.from, edge.to)) {
				sum += edge.weight; // 가중치 합산
				if (++cnt == v - 1)
					break; // 트리가 완성되면 반복문 탈출
			}
		}

		System.out.println(sum);
	}

	// 서로소 그룹의 대표 찾기
	static int find(int a) {
		if (parents[a] == a)
			return a; // 대표가 자기 자신이면 자기 자신 리턴
		return parents[a] = find(parents[a]); // 대표를 갱신하고 리턴
	}

	// 서로소 그룹 만들기
	static boolean union(int a, int b) {
		int aRoot = find(a); // a 정점의 대표
		int bRoot = find(b); // b 정점의 대표

		// 대표가 같으면 false
		if (aRoot == bRoot)
			return false;

		// 대표가 다르면 서로소 그룹 합치고 true
		parents[bRoot] = aRoot;
		return true;
	}

}