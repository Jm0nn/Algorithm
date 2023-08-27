import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int r, c, cnt;
	static int[][] cheese;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		cheese = new int[r + 1][c + 1];

		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= c; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] == 1)
					cnt++;
			}
		}

		int time = 0;
		int before = cnt;
		while (cnt != 0) {
			time++;
			before = cnt;
			bfs();
		}

		System.out.println(time + "\n" + before);
	}

	static void bfs() {
		Queue<int[]> melt = new ArrayDeque<>();
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[r + 1][c + 1];

		q.offer(new int[] { 0, 0 });
		visit[0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cheese[cur[0]][cur[1]] == 1) {
				melt.offer(cur);
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + deltas[d][0];
				int nc = cur[1] + deltas[d][1];

				if (0 > nr || nr > r || 0 > nc || nc > c || visit[nr][nc])
					continue;

				q.offer(new int[] { nr, nc });
				visit[nr][nc] = true;
			}
		}

		cnt -= melt.size();

		while (!melt.isEmpty()) {
			int[] cur = melt.poll();
			cheese[cur[0]][cur[1]] = 0;
		}
	}

}