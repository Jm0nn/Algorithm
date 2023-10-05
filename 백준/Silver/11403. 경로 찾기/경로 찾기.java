import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int INF = 99999;
		int n = Integer.parseInt(br.readLine());
		int[][] matrix = new int[n][n];

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; ++j) {
				int num = Integer.parseInt(st.nextToken());

				if (num == 0)
					num = INF;

				matrix[i][j] = num;

			}
		}

		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				if (i == k)
					continue;

				for (int j = 0; j < n; ++j) {
					if (k == j)
						continue;

					if (matrix[i][j] > matrix[i][k] + matrix[k][j])
						matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (matrix[i][j] == INF)
					sb.append("0 ");
				else
					sb.append("1 ");
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

}