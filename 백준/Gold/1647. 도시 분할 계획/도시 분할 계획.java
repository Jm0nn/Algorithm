import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		parents = new int[n + 1];
		for (int i = 1; i <= n; ++i)
			parents[i] = i;

		Queue<Edge> pq = new PriorityQueue<>();

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			pq.offer(new Edge(a, b, c));
		}

		int cnt = 0;
		int sum = 0;

		while (!pq.isEmpty()) {
			if (cnt == n - 2)
				break;

			Edge cur = pq.poll();

			if (union(cur.from, cur.to)) {
				++cnt;
				sum += cur.weight;
			}
		}

		System.out.println(sum);
	}

	static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		parents[rootB] = rootA;
		return true;
	}

}