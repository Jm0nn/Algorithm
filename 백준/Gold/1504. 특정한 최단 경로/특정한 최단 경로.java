import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int num;
		int weight;
		Node next;

		public Node(int num, int weight, Node next) {
			this.num = num;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static final int INF = 99999999;

	static int n;
	static Node[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		graph = new Node[n + 1];

		int e = Integer.parseInt(st.nextToken());

		while (e-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a] = new Node(b, c, graph[a]);
			graph[b] = new Node(a, c, graph[b]);
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int ans1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n);
		int ans2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n);

		if (ans1 >= INF && ans2 >= INF)
			System.out.println(-1);
		else
			System.out.println(Math.min(ans1, ans2));
	}

	static int dijkstra(int s, int e) {
		Queue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		boolean[] visit = new boolean[n + 1];
		Arrays.fill(dist, INF);

		dist[s] = 0;
		pq.offer(new Node(s, 0, null));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.num;

			if (num == e)
				break;

			if (visit[num])
				continue;

			visit[num] = true;

			for (Node node = graph[num]; node != null; node = node.next) {
				int x = node.num;
				int w = node.weight;

				if (dist[x] > dist[num] + w) {
					dist[x] = dist[num] + w;
					pq.offer(new Node(x, dist[x], null));
				}
			}
		}

		return dist[e];
	}

}