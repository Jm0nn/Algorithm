import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		BigInteger[] dp = new BigInteger[251];
		BigInteger two = BigInteger.valueOf(2);

		dp[0] = BigInteger.valueOf(1);
		dp[1] = BigInteger.valueOf(1);

		for (int i = 2; i <= 250; i++)
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(two));

		String input;
		while ((input = br.readLine()) != null && input.length() > 0) {
			int n = Integer.parseInt(input);
			sb.append(dp[n]).append('\n');
		}

		System.out.println(sb);
	}
}