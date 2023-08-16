import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, cnt;
	static boolean[][] pipe;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		pipe = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				if (s.charAt(j) == 'x')
					pipe[i][j] = true;
			}
		}

		for (int i = 0; i < R; i++)
			recur(i, 0);

		System.out.println(cnt);
	}

	static boolean recur(int r, int c) {
		if (c == C - 1) {
			cnt++;
			return true;
		}

		pipe[r][c] = true;

		for (int i = 0; i < 3; i++) {
			int nr = r - 1 + i;

			if (0 <= nr && nr < R && !pipe[nr][c + 1])
				if (recur(nr, c + 1))
					return true;
		}

		return false;
	}

}