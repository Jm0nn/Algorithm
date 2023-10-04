import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());

		long[] a = new long[n + 2];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			a[i] = Integer.parseInt(st.nextToken());

		long ans = 0;

		if (b < c) {
			for (int i = 0; i < n; ++i)
				ans += a[i] * b;
		} else {
			for (int i = 0; i < n; ++i) {
				long p1, p2;

				if (a[i + 1] > a[i + 2]) {
					p1 = Math.min(a[i], a[i + 1] - a[i + 2]);

					ans += p1 * (b + c);

					a[i] -= p1;
					a[i + 1] -= p1;

					p2 = Math.min(a[i], Math.min(a[i + 1], a[i + 2]));

					ans += p2 * (b + c * 2);

					a[i] -= p2;
					a[i + 1] -= p2;
					a[i + 2] -= p2;
				} else {
					p2 = Math.min(a[i], Math.min(a[i + 1], a[i + 2]));

					ans += p2 * (b + c * 2);

					a[i] -= p2;
					a[i + 1] -= p2;
					a[i + 2] -= p2;

					p1 = Math.min(a[i], a[i + 1]);

					ans += p1 * (b + c);

					a[i] -= p1;
					a[i + 1] -= p1;
				}

				ans += a[i] * b;
			}
		}

		System.out.println(ans);
	}

}