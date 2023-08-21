import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static final int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int n;
	static char[][] paint;
	static boolean[][] normal;
	static boolean[][] blind;

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		paint = new char[n][n];
		normal = new boolean[n][n];
		blind = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++)
				paint[i][j] = s.charAt(j);
		}

		int nCnt = 0;
		int bCnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!normal[i][j]) {
					bfsNormal(i, j, paint[i][j]);
					nCnt++;
				}

				if (!blind[i][j]) {
					bfsBlind(i, j, paint[i][j]);
					bCnt++;
				}
			}
		}

		System.out.println(nCnt + " " + bCnt);
	}

	static void bfsNormal(int r, int c, char color) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(r, c));
		normal[r][c] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n)
					continue;

				if (normal[nr][nc] || paint[nr][nc] != color)
					continue;

				queue.offer(new Pos(nr, nc));
				normal[nr][nc] = true;
			}
		}
	}

	static void bfsBlind(int r, int c, char color) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(r, c));
		blind[r][c] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n)
					continue;

				if (blind[nr][nc])
					continue;

				if (color == 'B') {
					if (paint[nr][nc] != 'B')
						continue;
				} else {
					if (paint[nr][nc] == 'B')
						continue;
				}

				queue.offer(new Pos(nr, nc));
				blind[nr][nc] = true;
			}
		}
	}

}