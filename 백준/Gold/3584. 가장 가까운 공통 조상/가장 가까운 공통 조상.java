import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());

			int[] parent = new int[n + 1];
			boolean[] visit = new boolean[n + 1];

			for (int i = 1; i <= n; ++i)
				parent[i] = i;

			for (int i = 0; i < n - 1; ++i) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				parent[b] = a;
			}

			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			visit[u] = true;

			while (u != parent[u]) {
				u = parent[u];
				visit[u] = true;
			}

			while (true) {
				if (visit[v]) {
					sb.append(v).append('\n');
					break;
				}

				v = parent[v];
			}
		}

		System.out.println(sb);
	}

}