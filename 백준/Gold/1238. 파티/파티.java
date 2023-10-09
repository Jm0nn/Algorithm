import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int end;
		int time;
		Node next;

		Node(int end, int time, Node next) {
			this.end = end;
			this.time = time;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}

	static final int INF = Integer.MAX_VALUE;

	static int N, M, X;
	static int[] toX;
	static int[] dist;
	static Node[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		toX = new int[N + 1];
		dist = new int[N + 1];
		graph = new Node[N + 1];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			graph[A] = new Node(B, T, graph[A]);
		}

		for (int i = 1; i <= N; ++i)
			toX[i] = dijkstra(i);

		dijkstra(X);

		int max = 0;
		for (int i = 1; i <= N; ++i) {
			if (max < toX[i] + dist[i])
				max = toX[i] + dist[i];
		}

		System.out.println(max);
	}

	static int dijkstra(int start) {
		Arrays.fill(dist, INF);

		dist[start] = 0;
		boolean[] visit = new boolean[N + 1];
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0, null));

		int cnt = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int end = cur.end;

			if (visit[end])
				continue;

			visit[end] = true;

			for (Node n = graph[end]; n != null; n = n.next) {
				int tmp = n.end;
				int time = n.time;

				if (dist[tmp] > dist[end] + time) {
					dist[tmp] = dist[end] + time;
					pq.offer(new Node(tmp, dist[tmp], null));
				}
			}

			if (++cnt == N)
				break;
		}

		return dist[X];
	}
}