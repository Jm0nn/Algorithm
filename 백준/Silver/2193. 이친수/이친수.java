import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		long[][] dp = new long[n][2];

		dp[0][0] = 0;
		dp[0][1] = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					if (j == 1 && k == 1)
						continue;
					dp[i][k] += dp[i - 1][j];
				}
			}
		}

		System.out.println(dp[n - 1][0] + dp[n - 1][1]);

		sc.close();
	}
}