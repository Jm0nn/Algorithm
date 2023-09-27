import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		char[][] map = new char[r][c];
		int[] hedgehog = new int[3];

		Queue<int[]> water = new ArrayDeque<>();

		for (int i = 0; i < r; ++i) {
			String input = br.readLine();
			for (int j = 0; j < c; ++j) {
				char p = input.charAt(j);

				if (p == 'S') {
					hedgehog[0] = i;
					hedgehog[1] = j;
					p = '.';
				} else if (p == '*') {
					water.offer(new int[] { i, j });
				}

				map[i][j] = p;
			}
		}

		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

		boolean[][] visit = new boolean[r][c];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(hedgehog);
		visit[hedgehog[0]][hedgehog[1]] = true;

		int turn = 0;
		int ans = -1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cnt = cur[2];

			if (turn < cnt) {
				int size = water.size();

				while (size-- > 0) {
					int[] pos = water.poll();
					int x = pos[0];
					int y = pos[1];

					for (int d = 0; d < 4; ++d) {
						int nx = x + deltas[d][0];
						int ny = y + deltas[d][1];

						if (0 > nx || nx >= r || 0 > ny || ny >= c)
							continue;

						if (map[nx][ny] == '*' || map[nx][ny] == 'D' || map[nx][ny] == 'X')
							continue;

						map[nx][ny] = '*';
						water.offer(new int[] { nx, ny });
					}
				}

				turn = cnt;
			}

			int x = cur[0];
			int y = cur[1];

			if (map[x][y] == '*')
				continue;
			else if (map[x][y] == 'D') {
				ans = cnt;
				break;
			}

			for (int d = 0; d < 4; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (0 > nx || nx >= r || 0 > ny || ny >= c)
					continue;

				if (visit[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == '*')
					continue;

				visit[nx][ny] = true;
				queue.offer(new int[] { nx, ny, cnt + 1 });
			}
		}

		System.out.println(ans == -1 ? "KAKTUS" : ans);
	}

}