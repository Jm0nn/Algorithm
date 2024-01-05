package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_8458_원점으로집합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			sb.append('#').append(tc).append(' ');

			int n = Integer.parseInt(br.readLine());

			boolean even = true;
			boolean odd = true;
			int max = 0;

			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dist = Math.abs(x) + Math.abs(y);

				if (max < dist)
					max = dist;

				if (dist % 2 == 0)
					odd = false;
				else
					even = false;
			}

			if (!even && !odd) {
				sb.append("-1\n");
				continue;
			}

			int cnt = 0;
			while (max > 0) {
				++cnt;

				if (max > cnt) {
					max -= cnt;
				} else {
					int sum = max + cnt;
					if (sum % 2 == 0)
						max = 0;
					else
						max = 1;
				}
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}

}
