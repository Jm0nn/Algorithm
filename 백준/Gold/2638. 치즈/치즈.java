import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int n, m, cnt;
	static int[][] cheese;
	static boolean[][] visit;
	static Queue<int[]> bfs = new ArrayDeque<>();
	static Queue<int[]> melt = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		cheese = new int[n][m];
		visit = new boolean[n][m];

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; ++j) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] == 1)
					++cnt;
			}
		}

		cheese[0][0] = -1;

		int time = 0;
		while (cnt > 0) {
			setAir();
			melt();
			++time;
		}

		System.out.println(time);
	}

	static void setAir() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (cheese[i][j] == -1 && !visit[i][j]) {
					bfs.offer(new int[] { i, j });
					visit[i][j] = true;
				}
			}
		}

		while (!bfs.isEmpty()) {
			int[] cur = bfs.poll();
			int x = cur[0];
			int y = cur[1];

			for (int d = 0; d < 4; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (0 > nx || nx >= n || 0 > ny || ny >= m)
					continue;

				if (visit[nx][ny] || cheese[nx][ny] == 1)
					continue;

				bfs.offer(new int[] { nx, ny });
				visit[nx][ny] = true;
				cheese[nx][ny] = -1;
			}
		}
	}

	static void melt() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (cheese[i][j] < 1)
					continue;

				int air = 0;

				for (int d = 0; d < 4; ++d) {
					int ni = i + deltas[d][0];
					int nj = j + deltas[d][1];

					if (0 > ni || ni >= n || 0 > nj || nj >= m)
						continue;

					if (cheese[ni][nj] == -1)
						++air;
				}

				if (air >= 2)
					melt.offer(new int[] { i, j });
			}
		}

		while (!melt.isEmpty()) {
			int[] cur = melt.poll();
			int x = cur[0];
			int y = cur[1];
			cheese[x][y] = -1;
			--cnt;
		}
	}

}