import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] rgb = new int[n][3];
		int[][] dp = new int[n][3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		final int INF = 1_111_111;
		int ans = INF;

		for (int k = 0; k < 3; ++k) {
			for (int i = 0; i < 3; ++i) {
				if (i == k) {
					dp[0][i] = rgb[0][i];
				} else {
					dp[0][i] = INF;
				}
			}

			for (int i = 1; i < n; ++i) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
			}

			for (int i = 0; i < 3; ++i) {
				if (i != k) {
					ans = Math.min(ans, dp[n - 1][i]);
				}
			}
		}

		System.out.println(ans);
	}
}