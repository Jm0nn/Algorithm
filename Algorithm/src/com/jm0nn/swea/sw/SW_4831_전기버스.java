package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이썬으로만 제출 가능한 문제...
public class SW_4831_전기버스 {

	static int k, n, m;
	static boolean[] station;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			station = new boolean[n];
			visit = new boolean[n];

			st = new StringTokenizer(br.readLine());
			while (m-- > 0)
				station[Integer.parseInt(st.nextToken())] = true;

			int cnt = 0;
			int idx = k;
			visit[0] = true;

			while (idx < n) {
				if (station[idx]) {
					visit[idx] = true;
					idx += k;
					cnt++;
				} else {
					idx--;
					if (visit[idx]) {
						cnt = 0;
						break;
					}
				}
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}
}
