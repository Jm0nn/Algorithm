package com.jm0nn.baekjoon.diamond.d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D5_18185_라면사기Small {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n + 2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			a[i] = Integer.parseInt(st.nextToken());

		int ans = 0;

		for (int i = 0; i < n; ++i) {
			int p5, p7;

			if (a[i + 1] > a[i + 2]) {
				p5 = Math.min(a[i], a[i + 1] - a[i + 2]);

				ans += p5 * 5;

				a[i] -= p5;
				a[i + 1] -= p5;

				p7 = Math.min(a[i], Math.min(a[i + 1], a[i + 2]));

				ans += p7 * 7;

				a[i] -= p7;
				a[i + 1] -= p7;
				a[i + 2] -= p7;
			} else {
				p7 = Math.min(a[i], Math.min(a[i + 1], a[i + 2]));

				ans += p7 * 7;

				a[i] -= p7;
				a[i + 1] -= p7;
				a[i + 2] -= p7;

				p5 = Math.min(a[i], a[i + 1]);

				ans += p5 * 5;

				a[i] -= p5;
				a[i + 1] -= p5;
			}

			ans += a[i] * 3;
		}

		System.out.println(ans);
	}

}
