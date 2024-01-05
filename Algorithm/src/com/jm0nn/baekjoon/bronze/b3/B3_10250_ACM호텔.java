package com.jm0nn.baekjoon.bronze.b3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_10250_ACM호텔 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			@SuppressWarnings("unused")
			int w = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			int front = n % h != 0 ? n % h : h;
			int back = n % h != 0 ? n / h + 1 : n / h;

			sb.append(front);

			if (back / 10 == 0)
				sb.append(0);

			sb.append(back).append('\n');
		}

		System.out.println(sb);
	}

}
