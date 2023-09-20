import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		int[] start = {};
		boolean[][] visit = new boolean[n][m];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					visit[i][j] = true;
				} else if (map[i][j] == 2) {
					start = new int[] { i, j, 0 };
					visit[i][j] = true;
				} else {
					map[i][j] = -1;
				}
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		q.offer(start);

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			map[cur[0]][cur[1]] = cur[2];

			for (int d = 0; d < 4; ++d) {
				int nr = cur[0] + deltas[d][0];
				int nc = cur[1] + deltas[d][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= m || visit[nr][nc])
					continue;

				q.offer(new int[] { nr, nc, cur[2] + 1 });
				visit[nr][nc] = true;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j)
				sb.append(map[i][j]).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
}