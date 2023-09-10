import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Planet {
		int idx;
		long x, y, z;

		Planet(int idx, long x, long y, long z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;

		Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		parents = new int[n];
		for (int i = 0; i < n; i++)
			parents[i] = i;

		Planet[] planet = new Planet[n];
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			planet[i] = new Planet(i, x, y, z);
		}

		Arrays.sort(planet, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Long.compare(o1.x, o2.x);
			}
		});

		for (int i = 1; i < n; i++)
			pq.offer(new Edge(planet[i - 1].idx, planet[i].idx, Math.abs(planet[i - 1].x - planet[i].x)));

		Arrays.sort(planet, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Long.compare(o1.y, o2.y);
			}
		});

		for (int i = 1; i < n; i++)
			pq.offer(new Edge(planet[i - 1].idx, planet[i].idx, Math.abs(planet[i - 1].y - planet[i].y)));

		Arrays.sort(planet, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Long.compare(o1.z, o2.z);
			}
		});

		for (int i = 1; i < n; i++)
			pq.offer(new Edge(planet[i - 1].idx, planet[i].idx, Math.abs(planet[i - 1].z - planet[i].z)));

		long sum = 0;
		int cnt = 0;

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (union(edge.from, edge.to)) {
				sum += edge.weight;
				if (++cnt == n - 1)
					break;
			}
		}

		System.out.println(sum);
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

}