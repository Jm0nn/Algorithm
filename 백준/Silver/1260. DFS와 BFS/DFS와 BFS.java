import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, v;
	static boolean[][] matrix;;
	static boolean[] visit;

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		matrix = new boolean[n + 1][n + 1];
		visit = new boolean[n + 1];

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			matrix[a][b] = true;
			matrix[b][a] = true;
		}

		dfs(v);
		sb.append('\n');
		bfs(v);

		System.out.println(sb);
	}

	static void dfs(int v) {
		visit[v] = true;
		sb.append(v).append(' ');
		for (int i = 1; i <= n; i++) {
			if (matrix[v][i] && !visit[i])
				dfs(i);
		}
	}

	static void bfs(int v) {
		Queue<Integer> queue = new ArrayDeque<>();
		visit = new boolean[n + 1];
		queue.offer(v);
		visit[v] = true;
		while (!queue.isEmpty()) {
			Integer cur = queue.poll();
			sb.append(cur).append(' ');
			for (int i = 1; i <= n; i++) {
				if (matrix[cur][i] && !visit[i]) {
					queue.offer(i);
					visit[i] = true;
				}
			}
		}
	}
}