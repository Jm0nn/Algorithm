import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int INF = 987654321;

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		int[] item = new int[n + 1];
		int[][] matrix = new int[n + 1][n + 1];

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (i == j)
					continue;
				matrix[i][j] = INF;
			}
		}

		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; ++i)
			item[i] = Integer.parseInt(st.nextToken());

		while (r-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			matrix[a][b] = l;
			matrix[b][a] = l;
		}

		for (int k = 1; k <= n; ++k) {
			for (int i = 1; i <= n; ++i) {
				if (i == k)
					continue;

				for (int j = 1; j <= n; ++j) {
					if (k == j || i == j)
						continue;

					if (matrix[i][j] > matrix[i][k] + matrix[k][j])
						matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= n; ++i) {
			int sum = 0;

			for (int j = 1; j <= n; ++j) {
				if (matrix[i][j] <= m)
					sum += item[j];
			}

			if (max < sum)
				max = sum;
		}

		System.out.println(max);
	}

}