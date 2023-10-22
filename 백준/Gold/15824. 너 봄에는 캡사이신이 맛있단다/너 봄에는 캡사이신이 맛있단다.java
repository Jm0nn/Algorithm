import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] skovil = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			skovil[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(skovil);

		long ans = 0;

		for (int i = 0; i < n; ++i)
			ans = (ans + skovil[i] * (pow(i) - pow(n - i - 1)) % MOD) % MOD;

		if (ans < 0)
			ans += MOD;

		System.out.println(ans);
	}

	static long pow(int exp) {
		if (exp == 0)
			return 1;

		long half = pow(exp / 2);

		if (exp % 2 == 0)
			return half * half % MOD;
		else
			return half * half * 2 % MOD;
	}

}