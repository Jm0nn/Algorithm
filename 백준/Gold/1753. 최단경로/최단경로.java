import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Vertex implements Comparable<Vertex> {
		int to;
		int weight;
		Vertex next;

		Vertex(int to, int weight, Vertex next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());

		Vertex[] graph = new Vertex[V + 1];

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[u] = new Vertex(v, w, graph[u]);
		}

		int[] dist = new int[V + 1];
		boolean[] visit = new boolean[V + 1];

		int INF = Integer.MAX_VALUE;

		for (int i = 1; i <= V; ++i)
			Arrays.fill(dist, INF);

		dist[start] = 0;
		Queue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(start, 0, null));

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			int to = cur.to;

			if (visit[to])
				continue;

			visit[to] = true;

			for (Vertex tmp = graph[to]; tmp != null; tmp = tmp.next) {
				int x = tmp.to;
				int w = tmp.weight;

				if (dist[x] > dist[to] + w) {
					dist[x] = dist[to] + w;
					pq.offer(new Vertex(x, dist[x], null));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; ++i)
			sb.append(dist[i] < INF ? dist[i] : "INF").append('\n');
		System.out.println(sb);
	}

}