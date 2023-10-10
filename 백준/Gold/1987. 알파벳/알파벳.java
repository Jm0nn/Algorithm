import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int r, c, max;
	static char[][] board;
	static boolean[] visitAlpha = new boolean[26];
	static boolean[][] visitPos;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		board = new char[r][];
		visitPos = new boolean[r][c];

		for (int i = 0; i < r; ++i)
			board[i] = br.readLine().toCharArray();

		visitAlpha[board[0][0] - 'A'] = true;
		dfs(1, 0, 0);

		System.out.println(max);
	}

	static void dfs(int cnt, int x, int y) {
		if (max < cnt)
			max = cnt;

		for (int d = 0; d < 4; ++d) {
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];

			if (0 > nx || nx >= r || 0 > ny || ny >= c)
				continue;

			if (visitPos[nx][ny])
				continue;

			char c = board[nx][ny];

			if (visitAlpha[c - 'A'])
				continue;

			visitPos[nx][ny] = true;
			visitAlpha[c - 'A'] = true;
			dfs(cnt + 1, nx, ny);
			visitPos[nx][ny] = false;
			visitAlpha[c - 'A'] = false;
		}
	}

}