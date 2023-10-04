import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		boolean[][] visit = new boolean[n][m];

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; ++j) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 0)
					visit[i][j] = true;
			}
		}

		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		Queue<int[]> queue = new ArrayDeque<>();
		int island = 0;

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (visit[i][j])
					continue;

				++island;

				visit[i][j] = true;
				queue.offer(new int[] { i, j });

				while (!queue.isEmpty()) {
					int[] cur = queue.poll();
					int x = cur[0];
					int y = cur[1];
					map[x][y] = island;

					for (int d = 0; d < 4; ++d) {
						int nx = x + deltas[d][0];
						int ny = y + deltas[d][1];

						if (0 > nx || nx >= n || 0 > ny || ny >= m || visit[nx][ny])
							continue;

						visit[nx][ny] = true;
						queue.offer(new int[] { nx, ny });
					}
				}
			}
		}

		int[][] matrix = new int[island + 1][island + 1];
		for (int i = 1; i <= island; ++i)
			Arrays.fill(matrix[i], 99999);

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (map[i][j] == 0)
					continue;

				int num = map[i][j];

				for (int d = 0; d < 4; ++d) {
					int x = i + deltas[d][0];
					int y = j + deltas[d][1];

					int cnt = 0;
					int num2 = -1;

					while (0 <= x && x < n && 0 <= y && y < m) {
						if (map[x][y] != 0) {
							num2 = map[x][y];
							break;
						}

						x += deltas[d][0];
						y += deltas[d][1];
						++cnt;
					}

					if (num2 > 0 && num != num2 && cnt > 1 && matrix[num][num2] > cnt) {
						matrix[num][num2] = cnt;
						matrix[num2][num] = cnt;
					}
				}
			}
		}

		parents = new int[island + 1];

		for (int i = 1; i <= island; ++i)
			parents[i] = i;

		PriorityQueue<Edge> edgeList = new PriorityQueue<>();

		for (int i = 1; i <= island; ++i) {
			for (int j = 1; j <= island; ++j) {
				if (matrix[i][j] == 99999)
					continue;
				edgeList.add(new Edge(i, j, matrix[i][j]));
			}
		}

		int result = 0;
		int count = 0;

		while (!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++count == island - 1)
					break;
			}
		}

		System.out.println(count == island - 1 ? result : -1);
	}

	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
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