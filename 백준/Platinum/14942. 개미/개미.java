import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int no;
		int cost;
		Node next;

		public Node(int no, int cost, Node next) {
			this.no = no;
			this.cost = cost;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static int n;
	static int[] ant;
	static int[] depth;
	static boolean[] visit;
	static Node[] graph;
	static List<Integer>[] route;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		ant = new int[n + 1];
		depth = new int[n + 1];
		visit = new boolean[n + 1];
		graph = new Node[n + 1];
		route = new List[n + 1];

		for (int i = 1; i <= n; ++i)
			ant[i] = Integer.parseInt(br.readLine());

		for (int i = 0; i < n - 1; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a] = new Node(b, c, graph[a]);
			graph[b] = new Node(a, c, graph[b]);
		}

		dfs(1, 0, new ArrayList<>());

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; ++i) {
			int ans = 1;
			int dist = depth[i];

			for (int idx = 1; dist > ant[i]; ++idx) {
				int tmp = route[i].get(idx);
				dist = depth[i] - depth[tmp];
				ans = tmp;
			}

			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

	static void dfs(int num, int dist, List<Integer> list) {
		if (visit[num])
			return;

		visit[num] = true;

		depth[num] = dist;

		route[num] = new ArrayList<>(list);
		route[num].add(num);

		for (Node node = graph[num]; node != null; node = node.next)
			dfs(node.no, dist + node.cost, route[num]);
	}

}