import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int num;
		int cost;
		Node next;

		public Node(int num, int cost, Node next) {
			this.num = num;
			this.cost = cost;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		Node[] graph = new Node[n + 1];

		int m = Integer.parseInt(br.readLine());

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[s] = new Node(e, w, graph[s]);
		}

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int[] dist = new int[n + 1];
		boolean[] visit = new boolean[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		Queue<Node> pq = new PriorityQueue<>();
		dist[A] = 0;
		pq.offer(new Node(A, 0, null));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.num;

			if (num == B)
				break;

			if (visit[num])
				continue;

			visit[num] = true;

			for (Node node = graph[num]; node != null; node = node.next) {
				int x = node.num;
				int c = node.cost;

				if (dist[x] > dist[num] + c) {
					dist[x] = dist[num] + c;
					pq.offer(new Node(x, dist[x], null));
				}
			}
		}

		System.out.println(dist[B]);
	}

}