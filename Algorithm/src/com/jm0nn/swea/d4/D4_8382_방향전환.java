package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_8382_방향전환 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int dx = Math.abs(x2 - x1);
			int dy = Math.abs(y2 - y1);

			int mid = (dx + dy) / 2;
			int ans = mid * 2 + Math.abs(dx - mid) + Math.abs(dy - mid);

			sb.append(ans).append('\n');
		}

		System.out.println(sb);
	}
}
