import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int no;
		int cost;

		Node(int no, int cost) {
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static final int INF = Integer.MAX_VALUE;

	static int[] dist; // s에서 i까지 최단 경로 길이
	static List<Node>[] graph; // 그래프
	static List<Integer>[] minRoute; // 최단 경로에서 node i로 가는 node 리스트
	static boolean[][] removedEdge; // i에서 j로 이동하는 경로가 제외됐는지

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String input;

		while (!(input = br.readLine()).equals("0 0")) {
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			graph = new List[n];
			minRoute = new List[n];
			dist = new int[n];
			removedEdge = new boolean[n][n];

			for (int i = 0; i < n; ++i) {
				graph[i] = new ArrayList<>();
				minRoute[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				graph[u].add(new Node(v, p));
			}

			dijkstra(s);
			removeEdge(d);
			almostDijkstra(n, s, d);

			sb.append(dist[d] < INF ? dist[d] : -1).append('\n');
		}

		System.out.println(sb);
	}

	// 최단 경로를 구하는 다익스트라
	static void dijkstra(int s) {
		Queue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		dist[s] = 0;
		pq.offer(new Node(s, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int no = cur.no;

			if (cur.cost > dist[no])
				continue;

			for (Node node : graph[no]) {
				int next = node.no;
				int cost = node.cost;

				// 최단 경로일 경우 해당 node로 가는 node를 list에 추가
				if (dist[next] > dist[no] + cost) {
					dist[next] = dist[no] + cost;
					minRoute[next] = new ArrayList<>();
					minRoute[next].add(no);
					pq.offer(new Node(next, dist[next]));
				} else if (dist[next] == dist[no] + cost) {
					minRoute[next].add(no);
				}
			}
		}
	}

	// node d로 이동하는 최단 경로 간선 제거
	static void removeEdge(int d) {
		for (int s : minRoute[d]) {
			if (!removedEdge[s][d]) {
				removedEdge[s][d] = true;
				removeEdge(s);
			}
		}
	}

	// 거의 최단 경로를 구하는 다익스트라
	static void almostDijkstra(int n, int s, int d) {
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[n];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		pq.offer(new Node(s, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int no = cur.no;

			if (no == d)
				break;

			if (visit[no])
				continue;

			visit[no] = true;

			for (Node node : graph[no]) {
				int next = node.no;
				int cost = node.cost;

				// 두 번째 다익스트라에서 최단 경로인 경우 넘어감
				if (removedEdge[no][next])
					continue;

				if (dist[next] > dist[no] + cost) {
					dist[next] = dist[no] + cost;
					pq.offer(new Node(next, dist[next]));
				}
			}
		}
	}

}