import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		int[] vip = new int[m + 1];

		for (int i = 1; i <= m; i++)
			vip[i] = Integer.parseInt(br.readLine());

		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 2];

		int result = 1;
		for (int i = 1; i <= m; i++)
			result *= dp[vip[i] - vip[i - 1] - 1];
		result *= dp[n - vip[m]];

		System.out.println(result);

		br.close();
	}
}