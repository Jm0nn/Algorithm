import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int x, y;
		int weight;
		Node next;

		public Node(int x, int y, int weight, Node next) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n, tc = 0;
		int INF = Integer.MAX_VALUE;
		int[][] cave;
		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		Node[][] list;

		while ((n = Integer.parseInt(br.readLine())) > 0) {
			sb.append("Problem ").append(++tc).append(": ");

			cave = new int[n][n];
			list = new Node[n][n];

			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; ++j)
					cave[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					for (int d = 0; d < 4; ++d) {
						int ni = i + deltas[d][0];
						int nj = j + deltas[d][1];

						if (0 > ni || ni >= n || 0 > nj || nj >= n)
							continue;

						list[i][j] = new Node(ni, nj, cave[ni][nj], list[i][j]);
					}
				}
			}

			int[][] distance = new int[n][n];
			boolean[][] visit = new boolean[n][n];

			for (int i = 0; i < n; ++i)
				Arrays.fill(distance[i], INF);

			distance[0][0] = cave[0][0];

			int min = 0, x = 0, y = 0;

			for (int cnt = 0; cnt < n * n; ++cnt) {
				min = INF;
				x = -1;
				y = -1;

				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < n; ++j) {
						if (!visit[i][j] && distance[i][j] < min) {
							min = distance[i][j];
							x = i;
							y = j;
						}
					}
				}

				visit[x][y] = true;

				if (x == n - 1 && y == n - 1)
					break;

				for (Node tmp = list[x][y]; tmp != null; tmp = tmp.next) {
					if (!visit[tmp.x][tmp.y] && distance[tmp.x][tmp.y] > min + tmp.weight) {
						distance[tmp.x][tmp.y] = min + tmp.weight;
					}
				}
			}

			sb.append(distance[n - 1][n - 1]).append('\n');
		}

		System.out.println(sb);
	}

}