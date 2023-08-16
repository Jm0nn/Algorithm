import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][][] worldCup;
	static int[][] comp;
	static int[] result = new int[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		worldCup = new int[4][6][3];
		comp = new int[6][3];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					worldCup[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		recur(0, 0, 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++)
			sb.append(result[i]).append(' ');

		System.out.println(sb);
	}

	static void recur(int cnt, int s1, int s2) {
		if (cnt == 15) {
			for (int i = 0; i < 4; i++) {
				boolean same = true;

				Loop: for (int j = 0; j < 6; j++) {
					for (int k = 0; k < 3; k++) {
						if (worldCup[i][j][k] != comp[j][k]) {
							same = false;
							break Loop;
						}
					}
				}

				if (same)
					result[i] = 1;
			}

			return;
		}

		for (int i = 0; i < 3; i++) {
			comp[s1][i] += 1;
			comp[s2][2 - i] += 1;

			if (s2 == 5)
				recur(cnt + 1, s1 + 1, s1 + 2);
			else
				recur(cnt + 1, s1, s2 + 1);

			comp[s1][i] -= 1;
			comp[s2][2 - i] -= 1;
		}

	}

}