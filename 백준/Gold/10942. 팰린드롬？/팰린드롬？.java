import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n + 1];
		int[][] dp = new int[n + 1][n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; ++i) {
			dp[i][i] = 1;
		}

		for (int i = 1; i < n; ++i) {
			if (arr[i] == arr[i + 1]) {
				dp[i][i + 1] = 1;
			}
		}

		for (int i = n - 1; i > 0; --i) {
			for (int j = i + 2; j <= n; ++j) {
				if (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) {
					dp[i][j] = 1;
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(dp[s][e]).append('\n');
		}

		System.out.print(sb);
	}

}