import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int from, to;
		double dist;

		Node(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		double[][] star = new double[n][2];

		parents = new int[n];
		Queue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < n; ++i) {
			parents[i] = i;

			StringTokenizer st = new StringTokenizer(br.readLine());

			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());

			for (int j = 0; j < i; ++j) {
				double dx = Math.abs(star[i][0] - star[j][0]);
				double dy = Math.abs(star[i][1] - star[j][1]);
				double dist = Math.sqrt(dx * dx + dy * dy);

				pq.offer(new Node(i, j, dist));
			}
		}

		double sum = 0;
		int cnt = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (union(node.from, node.to)) {
				sum += node.dist;

				if (++cnt == n - 1)
					break;
			}
		}

		System.out.println(String.format("%.2f", sum));
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

		parents[rootA] = rootB;
		return true;
	}
}