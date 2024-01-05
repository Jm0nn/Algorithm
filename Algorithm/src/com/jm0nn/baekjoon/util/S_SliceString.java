package com.jm0nn.baekjoon.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_SliceString {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		StringBuilder dif = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			dif.append(st.nextToken());

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; ++i)
			dif.append(st.nextToken());

		String sdif = dif.toString();

		int k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; ++i)
			sdif = sdif.replace(st.nextToken(), "");

		@SuppressWarnings("unused")
		int s = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), sdif + " ");

		StringBuilder sb = new StringBuilder();
		while (st.hasMoreTokens())
			sb.append(st.nextToken()).append('\n');

		System.out.println(sb);
	}

}
