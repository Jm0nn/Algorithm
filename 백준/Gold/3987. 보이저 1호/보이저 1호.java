import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		final String[] dir = {"U\n", "R\n", "D\n", "L\n"};

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] system = new char[N + 1][M + 1];

		for (int i = 1; i <= N; ++i) {
			String input = br.readLine();
			for (int j = 1; j <= M; ++j) {
				system[i][j] = input.charAt(j - 1);
			}
		}

		st = new StringTokenizer(br.readLine());
		int PR = Integer.parseInt(st.nextToken());
		int PC = Integer.parseInt(st.nextToken());

		int maxTime = 0;
		String ans1 = "";
		String ans2 = "";

		final int INF = Integer.MAX_VALUE;

		for (int d = 0; d < 4; ++d) {
			int[][] move = new int[N + 1][M + 1];

			for (int i = 1; i <= N; ++i) {
				Arrays.fill(move[i], -1);
			}

			int curD = d;
			int time = 0;
			int x = PR;
			int y = PC;

			while (0 < x && x <= N && 0 < y && y <= M) {
				if (move[x][y] == curD) {
					time = INF;
					break;
				}

				move[x][y] = curD;

				if (system[x][y] == '/') {
					switch (curD) {
						case 0:
							curD = 1;
							break;
						case 1:
							curD = 0;
							break;
						case 2:
							curD = 3;
							break;
						case 3:
							curD = 2;
							break;
					}
				} else if (system[x][y] == '\\') {
					switch (curD) {
						case 0:
							curD = 3;
							break;
						case 1:
							curD = 2;
							break;
						case 2:
							curD = 1;
							break;
						case 3:
							curD = 0;
							break;
					}
				} else if (system[x][y] == 'C') {
					break;
				}

				++time;

				x += deltas[curD][0];
				y += deltas[curD][1];
			}

			if (maxTime < time) {
				maxTime = time;
				ans1 = dir[d];
				if (maxTime == INF) {
					ans2 = "Voyager";
				} else {
					ans2 = String.valueOf(maxTime);
				}
			}
		}

		System.out.println(ans1 + ans2);
	}

}