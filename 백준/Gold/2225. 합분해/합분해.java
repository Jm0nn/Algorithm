import java.io.*;
import java.util.*;

public class Main {

	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[K + 1][N + 1];

		Arrays.fill(dp[1], 1);

		for (int i = 2; i <= K; ++i) {
			for (int j = 0; j <= N; ++j) {
				for (int k = j; k <= N; ++k) {
					dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % MOD;
				}
			}
		}

		System.out.println(dp[K][N]);
	}

}