import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Vertex {
		int to;
		int weight;

		Vertex(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static final int INF = 99999999;

	static int N, M, W;
	static int[] dist;
	static List<Vertex>[] list;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			dist = new int[N + 1];
			list = new List[N + 1];
			for (int i = 1; i <= N; ++i)
				list[i] = new ArrayList<>();

			int cnt = M + W;
			while (cnt-- > 0) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				if (cnt < W) {
					list[S].add(new Vertex(E, -T));
				} else {
					list[S].add(new Vertex(E, T));
					list[E].add(new Vertex(S, T));
				}
			}

			boolean cycle = false;
			for (int i = 1; i <= N; ++i) {
				if (bf(i)) {
					cycle = true;
					break;
				}
			}

			sb.append(cycle ? "YES\n" : "NO\n");
		}

		System.out.println(sb);
	}

	static boolean bf(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		boolean update = false;

		for (int i = 1; i < N; ++i) {
			update = false;

			for (int j = 1; j <= N; ++j) {
				for (Vertex v : list[j]) {
					if (dist[j] < INF && dist[v.to] > dist[j] + v.weight) {
						dist[v.to] = dist[j] + v.weight;
						update = true;
					}
				}
			}

			if (!update)
				break;
		}

		if (update) {
			for (int i = 1; i <= N; ++i) {
				for (Vertex v : list[i]) {
					if (dist[i] < INF && dist[v.to] > dist[i] + v.weight)
						return true;
				}
			}
		}

		return false;
	}

}