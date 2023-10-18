import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int from;
		int to;

		Node(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Node o) {
			return this.to - o.to;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Node[] nodes = new Node[n];

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int o = Integer.parseInt(st.nextToken());

			if (h > o) {
				int tmp = h;
				h = o;
				o = tmp;
			}

			nodes[i] = new Node(h, o);
		}

		int d = Integer.parseInt(br.readLine());

		Arrays.sort(nodes);

		Queue<Integer> pq = new PriorityQueue<>();
		int cnt = 0;
		int max = 0;

		for (int i = 0; i < n; ++i) {
			int from = nodes[i].from;
			int to = nodes[i].to;

			while (!pq.isEmpty() && pq.peek() < to - d) {
				pq.poll();
				--cnt;
			}

			if (from >= to - d) {
				pq.offer(from);
				++cnt;
			}

			if (max < cnt)
				max = cnt;
		}

		System.out.println(max);
	}

}