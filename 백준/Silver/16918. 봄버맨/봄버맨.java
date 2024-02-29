import java.io.*;
import java.util.*;

public class Main {

	static final int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

	static int R, N, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int[][] board = new int[R][C];

		for (int i = 0; i < R; ++i) {
			String input = br.readLine();
			for (int j = 0; j < C; ++j) {
				char c = input.charAt(j);

				if (c == '.') {
					board[i][j] = 0;
				} else {
					board[i][j] = 2;
				}
			}
		}

		while (--N > 0) {
			board = nextTime(board);
		}

		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				sb.append(board[i][j] == 0 ? '.' : 'O');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static int[][] nextTime(int[][] board) {
		int[][] ret = new int[R][C];

		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				ret[i][j] = ++board[i][j];
			}
		}

		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (board[i][j] > 3) {
					ret[i][j] = 0;

					for (int d = 0; d < 4; ++d) {
						int ni = i + deltas[d][0];
						int nj = j + deltas[d][1];

						if (0 <= ni && ni < R && 0 <= nj && nj < C) {
							ret[ni][nj] = 0;
						}
					}
				}
			}
		}

		return ret;
	}
}