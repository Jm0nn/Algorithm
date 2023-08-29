import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String input = "";
		while ((input = br.readLine()) != null && input.length() > 0) {
			int n = Integer.parseInt(input);
			BigInteger[] dp = new BigInteger[n + 1];
			BigInteger two = new BigInteger("2");

			if (n == 0) {
				sb.append("1\n");
				continue;
			}

			dp[0] = new BigInteger("1");
			dp[1] = new BigInteger("1");

			for (int i = 2; i <= n; i++)
				dp[i] = dp[i - 1].add(dp[i - 2].multiply(two));

			sb.append(dp[n].toString()).append('\n');
		}

		System.out.println(sb);
	}
}