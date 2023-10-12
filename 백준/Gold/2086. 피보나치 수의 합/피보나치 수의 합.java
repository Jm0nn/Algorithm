import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final long MOD = 1_000_000_000L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		long[][] res1 = fibo(a);
		long[][] res2 = fibo(b + 1);

		long ans = res2[0][0] - res1[0][0];

		if (ans < 0)
			ans += MOD;

		System.out.println(ans);
	}

	static long[][] fibo(long n) {
		long[][] BASE = new long[][] { { 1L, 0L }, { 0L, 1L } };
		long[][] Q = new long[][] { { 1L, 1L }, { 1L, 0L } };

		while (n > 0L) {
			if (n % 2L == 1L)
				BASE = mul(BASE, Q);
			Q = mul(Q, Q);
			n /= 2L;
		}

		return BASE;
	}

	static long[][] mul(long[][] m1, long[][] m2) {
		long[][] res = new long[2][2];

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 2; ++k) {
					res[i][j] = (res[i][j] + (m1[i][k] * m2[k][j]) % MOD) % MOD;
				}
			}
		}

		return res;
	}

}