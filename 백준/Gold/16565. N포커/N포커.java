import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int MOD = 10007;

		int n = Integer.parseInt(br.readLine());

		int[][] nCk = new int[53][53];

		nCk[0][0] = 1;

		for (int i = 1; i <= 52; ++i) {
			nCk[i][0] = 1;

			for (int j = 1; j <= 52; ++j) {
				nCk[i][j] = (nCk[i - 1][j] + nCk[i - 1][j - 1]) % MOD;
			}
		}

		int ret = 0;

		for (int i = 1; i <= 13 && n - 4 * i >= 0; ++i) {
			if (i % 2 == 1)
				ret = (ret + nCk[52 - 4 * i][n - 4 * i] * nCk[13][i]) % MOD;
			else
				ret = (ret - nCk[52 - 4 * i][n - 4 * i] * nCk[13][i]) % MOD;
		}

		if (ret < 0)
			ret += MOD;

		System.out.println(ret);
	}

}