import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			char[][] up = { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } };
			char[][] down = { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } };
			char[][] front = { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } };
			char[][] back = { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } };
			char[][] left = { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } };
			char[][] right = { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } };

			int n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			while (n-- > 0) {
				char[][] tmpA = new char[3][3];

				String s = st.nextToken();
				switch (s.charAt(0)) {
				case 'U':
					switch (s.charAt(1)) {
					case '+':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[j][2 - i] = up[i][j];
						up = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = front[0][i];
							front[0][i] = right[0][i];
							right[0][i] = back[0][i];
							back[0][i] = left[0][i];
							left[0][i] = tmpC;
						}
						break;

					case '-':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[2 - j][i] = up[i][j];
						up = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = front[0][i];
							front[0][i] = left[0][i];
							left[0][i] = back[0][i];
							back[0][i] = right[0][i];
							right[0][i] = tmpC;
						}
						break;
					}
					break;

				case 'D':
					switch (s.charAt(1)) {
					case '+':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[j][2 - i] = down[i][j];
						down = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = front[2][i];
							front[2][i] = left[2][i];
							left[2][i] = back[2][i];
							back[2][i] = right[2][i];
							right[2][i] = tmpC;
						}
						break;

					case '-':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[2 - j][i] = down[i][j];
						down = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = front[2][i];
							front[2][i] = right[2][i];
							right[2][i] = back[2][i];
							back[2][i] = left[2][i];
							left[2][i] = tmpC;
						}
						break;
					}
					break;

				case 'F':
					switch (s.charAt(1)) {
					case '+':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[j][2 - i] = front[i][j];
						front = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[2][i];
							up[2][i] = left[2 - i][2];
							left[2 - i][2] = down[2][i];
							down[2][i] = right[i][0];
							right[i][0] = tmpC;
						}
						break;

					case '-':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[2 - j][i] = front[i][j];
						front = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[2][i];
							up[2][i] = right[i][0];
							right[i][0] = down[2][i];
							down[2][i] = left[2 - i][2];
							left[2 - i][2] = tmpC;
						}
						break;
					}
					break;

				case 'B':
					switch (s.charAt(1)) {
					case '+':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[j][2 - i] = back[i][j];
						back = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[0][i];
							up[0][i] = right[i][2];
							right[i][2] = down[0][i];
							down[0][i] = left[2 - i][0];
							left[2 - i][0] = tmpC;
						}
						break;

					case '-':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[2 - j][i] = back[i][j];
						back = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[0][i];
							up[0][i] = left[2 - i][0];
							left[2 - i][0] = down[0][i];
							down[0][i] = right[i][2];
							right[i][2] = tmpC;
						}
						break;
					}
					break;

				case 'L':
					switch (s.charAt(1)) {
					case '+':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[j][2 - i] = left[i][j];
						left = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[i][0];
							up[i][0] = back[2 - i][2];
							back[2 - i][2] = down[2 - i][2];
							down[2 - i][2] = front[i][0];
							front[i][0] = tmpC;
						}
						break;

					case '-':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[2 - j][i] = left[i][j];
						left = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[i][0];
							up[i][0] = front[i][0];
							front[i][0] = down[2 - i][2];
							down[2 - i][2] = back[2 - i][2];
							back[2 - i][2] = tmpC;
						}
						break;
					}
					break;

				case 'R':
					switch (s.charAt(1)) {
					case '+':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[j][2 - i] = right[i][j];
						right = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[i][2];
							up[i][2] = front[i][2];
							front[i][2] = down[2 - i][0];
							down[2 - i][0] = back[2 - i][0];
							back[2 - i][0] = tmpC;
						}
						break;

					case '-':
						for (int i = 0; i < 3; i++)
							for (int j = 0; j < 3; j++)
								tmpA[2 - j][i] = right[i][j];
						right = tmpA;

						for (int i = 0; i < 3; i++) {
							char tmpC = up[i][2];
							up[i][2] = back[2 - i][0];
							back[2 - i][0] = down[2 - i][0];
							down[2 - i][0] = front[i][2];
							front[i][2] = tmpC;
						}
						break;
					}
					break;
				}
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++)
					sb.append(up[i][j]);
				sb.append('\n');
			}
		}

		System.out.println(sb);
	}
}