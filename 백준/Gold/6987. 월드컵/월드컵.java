import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][][] worldCup;
	static boolean[] result = new boolean[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		worldCup = new int[4][6][3];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					worldCup[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		for (int i = 0; i < 4; i++)
			recur(0, i, 0, 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			if (result[i])
				sb.append(1);
			else
				sb.append(0);
			sb.append(' ');
		}

		System.out.println(sb);
	}

	static void recur(int cnt, int group, int s1, int s2) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (worldCup[group][i][j] < 0)
					return;
			}
		}

		if (cnt == 15) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (worldCup[group][i][j] != 0)
						return;
				}
			}

			result[group] = true;
			return;
		}

		for (int i = 0; i < 3; i++) {
			worldCup[group][s1][i] -= 1;
			worldCup[group][s2][2 - i] -= 1;

			if (s2 == 5)
				recur(cnt + 1, group, s1 + 1, s1 + 2);
			else
				recur(cnt + 1, group, s1, s2 + 1);

			if (result[group])
				return;

			worldCup[group][s1][i] += 1;
			worldCup[group][s2][2 - i] += 1;
		}

	}

}