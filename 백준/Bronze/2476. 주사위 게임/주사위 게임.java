import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int money;
		int ans = 0;

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());

			int d1 = Integer.parseInt(st.nextToken());
			int d2 = Integer.parseInt(st.nextToken());
			int d3 = Integer.parseInt(st.nextToken());

			if (d1 == d2 && d2 == d3) {
				money = 10000 + 1000 * d1;
			} else if (d1 == d2 || d1 == d3) {
				money = 1000 + 100 * d1;
			} else if (d2 == d3) {
				money = 1000 + 100 * d2;
			} else {
				money = 100 * Math.max(d1, Math.max(d2, d3));
			}

			ans = Math.max(money, ans);
		}

		System.out.println(ans);
	}
}